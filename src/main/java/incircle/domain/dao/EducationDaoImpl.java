package incircle.domain.dao;

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
}
