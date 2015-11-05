package incircle.account.dao;

import incircle.account.model.Account;
import incircle.account.model.AccountRole;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
	
	private SessionFactory sessionFactory;
	
	private AccountRoleDao accountRoleDao;
	
	private PasswordEncoder passwordEncoder;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void setAccountRoleDao(AccountRoleDao accountRoleDao) {
		this.accountRoleDao = accountRoleDao;
	}
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@SuppressWarnings("unchecked")
	public Account findByAccountName(String username) {
		Account account = null;
		List<Account> accounts = new ArrayList<Account>();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		accounts = session.createQuery("from Account where username=?").setParameter(0, username)
				.list();
		if (accounts.size() > 0) {
			account = accounts.get(0);
			Hibernate.initialize(account.getAccountRole());
		}
		session.getTransaction().commit();
		return account;
	}
	
	public Account updateAccount(Account account, boolean update) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        if (update)
        	session.update(account);
        else
        	session.save(account);
        session.getTransaction().commit();
        AccountRole accountRole = new AccountRole(account, "USER");
        accountRoleDao.updateRole(accountRole, update);
        return account;
	}

}