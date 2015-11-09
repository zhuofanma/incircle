package incircle.domain.dao;

import incircle.domain.model.Education;

/**
 * Created by zhuofanma on 11/3/15.
 */
public interface EducationDao {
    Education getEducation(Long id);
    Education createEducation(Education education);
    void deleteEducation(Long id);
}
