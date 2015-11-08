package incircle.domain.dao;

import incircle.domain.model.Connection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by zhuofanma on 11/7/15.
 */
public class ConnectionDaoImpl implements ConnectionDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Connection getConnection(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Connection connection = (Connection) session.get(Connection.class, id);
        session.getTransaction().commit();
        return connection;
    }
}
