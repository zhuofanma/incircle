package incircle.config;

import incircle.account.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ServiceConfig {
	@Autowired
	private DaoConfig daoConfig;
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl();
		userDetailsServiceImpl.setAccountDao(daoConfig.accountDao());
		return userDetailsServiceImpl;
	}
}
