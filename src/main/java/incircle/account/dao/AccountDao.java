package incircle.account.dao;

import incircle.account.model.Account;
import incircle.domain.model.Education;
import incircle.domain.model.Work;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface AccountDao {
	Account findByAccountName(String username);
	Account createAccount(Account account);
	Account updateAccount(Account account, Long id);
	void deleteAccount(Long id);
	List<Work> getAllWorks(Long id);
	List<Education> getAllEducations(Long id);
	ArrayList<Long> getAllConnectionIds(Long id);
}