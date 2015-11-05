package incircle.account.dao;

import incircle.account.model.Account;

public interface AccountDao {
	
	Account findByAccountName(String username);
	
	Account updateAccount(Account account, boolean update);

}