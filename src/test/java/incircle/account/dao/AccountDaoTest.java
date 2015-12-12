package incircle.account.dao;

import incircle.config.HibernateUtil;
import incircle.account.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Julian on 12/10/2015.
 */
public class AccountDaoTest {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private AccountDao accountDao;
    private Account account;

    @Before
    public void setup() throws Exception {
        this.accountDao = new AccountDaoImpl(sessionFactory);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        this.account = new Account("shabi", "slslslsl", true);
        session.save(this.account);
        session.getTransaction().commit();
    }

    @After
    public void tearDown() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(this.account);
        session.flush();
        session.getTransaction().commit();
    }

    @Test
    public void testGetAccount() {
        assertNotNull(accountDao.findByAccountName("shabi"));
    }
}
