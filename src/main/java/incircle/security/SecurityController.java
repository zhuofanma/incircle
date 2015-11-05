package incircle.security;


import incircle.account.dao.AccountDao;
import incircle.account.model.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class SecurityController {
	final Logger log = Logger.getLogger(SecurityController.class.getName());

	private AccountDao accountDao;
	@Autowired
	public void setAccountDao(AccountDao userDao) {
		this.accountDao = userDao;
	}
//
//	@RequestMapping("/account")
//	public Principal account(Principal account) {
//		return account;
//	}

	@RequestMapping(value = "/signupAccount", method = RequestMethod.POST)
	public void account(@RequestParam("username") String username, @RequestParam("password") String password) {
		System.out.println(username + " " + password);
		Account account = new Account(username, password, true);
		accountDao.updateAccount(account, false);
	}
}
