package incircle.api;

import incircle.domain.dao.EducationDao;
import incircle.domain.model.Education;
import incircle.domain.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuofanma on 11/3/15.
 */
@RestController
@RequestMapping(value="/api/educations")
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

    @RequestMapping(value="/", method= RequestMethod.POST)
    Education createEducation(@RequestBody Education education) {
        return educationDao.createEducation(education);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteEducation(@PathVariable Long id) {
        educationDao.deleteEducation(id);
    }
}
