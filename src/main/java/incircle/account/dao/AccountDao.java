package incircle.account.dao;

import incircle.account.model.Account;
import incircle.domain.model.Work;

import java.util.List;

public interface AccountDao {
	Account findByAccountName(String username);
	Account createAccount(Account account);
	Account updateAccount(Account account);
	void deleteAccount(Long id);
	List<Work> getAllWorks(Long id);
}