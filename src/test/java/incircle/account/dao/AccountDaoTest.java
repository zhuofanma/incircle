package incircle.account.dao;

import incircle.config.HibernateUtil;
import incircle.account.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

    @Test
    public void testUpdateAccount() {
        Account account2 = new Account("wocao", "wocao", true);
        account2.setJumpwill(true);
        account2.setJumpdate(new Date());
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(account2);
        session.getTransaction().commit();
        Long id = accountDao.findByAccountName("wocao").getId();
        Account newAccount = new Account();
        newAccount.setJumpwill(false);
        Account account3 = accountDao.updateAccount(newAccount, id);
        assertNull(account3.getJumpdate());
    }
}
