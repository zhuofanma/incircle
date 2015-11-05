package incircle.api;

import incircle.domain.dao.EducationDao;
import incircle.domain.model.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhuofanma on 11/3/15.
 */
@RestController
@RequestMapping(value="/api/education")
public class EducationController {

    private EducationDao educationDao;
    @Autowired
    public void setEducationDao(EducationDao educationDao) {
        this.educationDao = educationDao;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    Education getEducation(@PathVariable Long id) {
        return educationDao.getEducation(id);
    }
}
