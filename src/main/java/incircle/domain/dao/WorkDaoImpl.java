package incircle.domain.dao;

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
}
