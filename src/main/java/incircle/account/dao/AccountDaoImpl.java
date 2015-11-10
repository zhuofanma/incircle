package incircle.account.dao;

import incircle.account.model.Account;
import incircle.account.model.AccountRole;
import incircle.domain.model.Work;
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
//			Hibernate.initialize(account.getAccountRole());
		}
		session.getTransaction().commit();
		return account;
	}

	public Account createAccount(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(account);
		session.getTransaction().commit();
		AccountRole accountRole = new AccountRole(account, "USER");
		accountRoleDao.updateRole(accountRole, false);
		return account;
	}
	
	public Account updateAccount(Account account) {
		if (account.getPassword() != null) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
		} else {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			String fetchedPassword = ((Account) session.get(Account.class, account.getId())).getPassword();
			session.getTransaction().commit();
			account.setPassword(fetchedPassword);
		}
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(account);
        session.getTransaction().commit();
//        AccountRole accountRole = new AccountRole(account, "USER");
//        accountRoleDao.updateRole(accountRole, update);
        return account;
	}

	public void deleteAccount(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Account account = (Account)session.get(Account.class, id);
		if (account != null) {
			session.delete(account);
			session.flush();
		}
		session.getTransaction().commit();
	}

	public List<Work> getAllWorks(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Work> result = session.createQuery("")
	}

}