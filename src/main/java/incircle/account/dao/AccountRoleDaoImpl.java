package incircle.account.dao;

import incircle.account.model.AccountRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AccountRoleDaoImpl implements AccountRoleDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public AccountRole updateRole(AccountRole accountRole, boolean update) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
        if (update)
        	session.update(accountRole);
        else
        	session.save(accountRole);
        session.getTransaction().commit();
        return accountRole;
		
	}

}
