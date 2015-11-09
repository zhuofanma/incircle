package incircle.domain.dao;

import incircle.account.model.Account;
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

    public Connection createConnection(Connection connection) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Account fetchedAccount1 = session.get(Account.class, connection.getAccount1().getId());
        Account fetchedAccount2 = session.get(Account.class, connection.getAccount2().getId());
        connection.setAccount1(fetchedAccount1);
        connection.setAccount2(fetchedAccount2);
        session.save(connection);
        session.getTransaction().commit();
        return connection;
    }

    public void deleteConnection(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Connection connection = (Connection)session.get(Connection.class, id);
        if (connection != null) {
            session.delete(connection);
            session.flush();
        }
        session.getTransaction().commit();
    }
}
