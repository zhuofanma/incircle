package incircle.account.dao;

import incircle.account.model.Account;
import incircle.account.model.AccountRole;
import incircle.domain.model.Connection;
import incircle.domain.model.Education;
import incircle.domain.model.Work;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.awt.image.RescaleOp;
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

	public AccountDaoImpl() {}

	// For the test purpose
	public AccountDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
	
	public Account updateAccount(Account account, Long id) {
		account.setId(id);
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Account fetchedAccount = session.get(Account.class, id);
		session.getTransaction().commit();
		account.setUsername(fetchedAccount.getUsername());
		account.setEnabled(fetchedAccount.isEnabled());
		if (account.getPassword() != null) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
		} else {
			account.setPassword(fetchedAccount.getPassword());
		}
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.update(account);
        session2.getTransaction().commit();
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
		Account account = (Account)session.get(Account.class, id);
		//TODO: to add error control, such as if account = null
		@SuppressWarnings("unchecked")
		List<Work> result = session.createCriteria(Work.class)
							.add(Restrictions.eq("account", account))
							.list();
		session.getTransaction().commit();
		return result;
	}

	public List<Education> getAllEducations(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Account account = (Account)session.get(Account.class, id);
		//TODO: to add error control, such as if account = null
		@SuppressWarnings("unchecked")
		List<Education> result = session.createCriteria(Education.class)
				.add(Restrictions.eq("account", account))
				.list();
		session.getTransaction().commit();
		return result;
	}

	public ArrayList<Long> getAllConnectionIds(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Account account = (Account)session.get(Account.class, id);
		@SuppressWarnings("unchecked")
		List<Connection> connectionList = session.createCriteria(Connection.class)
											.add(Restrictions.eq("account1", account))
											.list();
		ArrayList<Long> result = new ArrayList<Long>();
		for (Connection connection: connectionList) {
			result.add(connection.getAccount2().getId());
		}
		session.getTransaction().commit();
		return result;
	}
}