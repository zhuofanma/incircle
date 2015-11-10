package incircle.domain.dao;

import incircle.account.model.Account;
import incircle.domain.model.Education;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by zhuofanma on 11/3/15.
 */
public class EducationDaoImpl implements EducationDao{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Education getEducation(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Education education = (Education) session.get(Education.class, id);
        session.getTransaction().commit();
        return education;
    }

    public Education createEducation(Education education) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Account fetchedAccount = session.get(Account.class, education.getAccount().getId());
        education.setAccount(fetchedAccount);
        session.save(education);
        session.getTransaction().commit();
        return education;
    }

    public Education updateEducation(Education education) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        //TODO: use session.load instead of session.get
        Account fetchedAccount = (session.get(Education.class, education.getId())).getAccount();
        session.getTransaction().commit();
        education.setAccount(fetchedAccount);
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(education);
        session.getTransaction().commit();
        return education;
    }

    public void deleteEducation(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Education education = (Education)session.get(Education.class, id);
        if (education != null) {
            session.delete(education);
            session.flush();
        }
        session.getTransaction().commit();
    }
}
