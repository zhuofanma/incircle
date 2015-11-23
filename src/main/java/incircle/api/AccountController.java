package incircle.api;

import incircle.account.dao.AccountDao;
import incircle.account.model.Account;
import incircle.domain.dao.WorkDao;
import incircle.domain.model.Education;
import incircle.domain.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuofanma on 11/4/15.
 */

@RestController
@RequestMapping(value="/api/accounts")
public class AccountController {
    private AccountDao accountDao;
    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @RequestMapping(value="/{username}", method= RequestMethod.GET)
    Account getAccount(@PathVariable String username) {
        return accountDao.findByAccountName(username);
    }

    @RequestMapping(value = "/{id}/works", method = RequestMethod.GET)
    List<Work> getAllWorks(@PathVariable Long id) {
        return accountDao.getAllWorks(id);
    }

    @RequestMapping(value = "/{id}/educations", method = RequestMethod.GET)
    List<Education> getAllEducations(@PathVariable Long id) {
        return accountDao.getAllEducations(id);
    }

    @RequestMapping(value = "/{id}/connections", method = RequestMethod.GET)
    ArrayList<Long> getAllConnectionIds(@PathVariable Long id) {
        return accountDao.getAllConnectionIds(id);
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    Account createAccount(@RequestBody Account account) {
        return accountDao.createAccount(account);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.PUT)
    Account updateAccount(@RequestBody Account account, @PathVariable Long id) {
        return accountDao.updateAccount(account, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable Long id) {
        accountDao.deleteAccount(id);
    }

}
