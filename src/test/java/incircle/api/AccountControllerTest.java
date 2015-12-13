package incircle.api;

import incircle.App;
import incircle.account.dao.AccountDao;
import incircle.account.model.Account;
import incircle.config.SecurityConfig;
import incircle.domain.model.Work;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import static com.oracle.util.Checksums.update;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

/**
 * Created by Julian on 12/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SecurityConfig.class, App.class})
@WebAppConfiguration
public class AccountControllerTest {
    private MockMvc mockMvc;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AccountDao accountDao;

    private Account account;


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .apply(springSecurity()).build();
        if (accountDao.findByAccountName("baichi") == null) {
            account = new Account("baichi", "baichi", true);
            account.setJumpwill(true);
            account.setJumpdate(new Date());
            accountDao.createAccount(account);
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetAccount() throws Exception {
        mockMvc.perform(get("/api/accounts/baichi")).andExpect(status().isOk());
    }

    @Test
    public void testCreateAccount() throws Exception {
        String accountJson = this.json(new Account("happy", "happy", true));
        accountJson = accountJson.substring(0, accountJson.length()-1) + ",\"password\":\"happy\"" + "}";
        mockMvc.perform(
                post("/api/accounts/").with(httpBasic("baichi", "baichi"))
                        .with(csrf())
                        .content(accountJson).contentType(contentType)
        ).andExpect(status().isOk());
        assertNotNull(accountDao.findByAccountName("happy"));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Long id = accountDao.findByAccountName("baichi").getId();
        Account newAccount = new Account();
        newAccount.setJumpwill(false);
        String accountJson = this.json(newAccount);
        mockMvc.perform(
                put("/api/accounts/" + id.toString())
                .content(accountJson).contentType(contentType)
        ).andExpect(status().isOk());
        assertNull(accountDao.findByAccountName("baichi").getJumpdate());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
