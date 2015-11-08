package incircle.api;

import incircle.account.dao.AccountDao;
import incircle.account.model.Account;
import incircle.domain.dao.WorkDao;
import incircle.domain.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuofanma on 11/4/15.
 */

@RestController
@RequestMapping(value="/api/account")
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

    @RequestMapping(value="/{username}", method= RequestMethod.POST)
    Account createAccount(@RequestBody Account account) {
        return accountDao.updateAccount(account, false);
    }
}
