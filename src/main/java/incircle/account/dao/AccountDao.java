package incircle.account.dao;

import incircle.account.model.Account;

public interface AccountDao {
	Account findByAccountName(String username);
	Account createAccount(Account account);
	Account updateAccount(Account account);
	void deleteAccount(Long id);
}