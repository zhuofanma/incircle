package incircle.domain.dao;

import incircle.account.model.Account;
import incircle.domain.model.Work;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by zhuofanma on 11/4/15.
 */
public class WorkDaoImpl implements WorkDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Work getWork(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Work work = (Work) session.get(Work.class, id);
        session.getTransaction().commit();
        return work;
    }

//    @Override
    public Work createWork(Work work) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        System.out.println(work);
        System.out.println(work.getAccount());
        Account fetchedAccount = session.get(Account.class, work.getAccount().getId());
        work.setAccount(fetchedAccount);
        session.save(work);
        session.getTransaction().commit();
        return work;
    }
}
