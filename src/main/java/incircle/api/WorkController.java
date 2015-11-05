package incircle.api;

import incircle.domain.dao.WorkDao;
import incircle.domain.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhuofanma on 11/4/15.
 */

@RestController
@RequestMapping(value="/api/work")
public class WorkController {
    private WorkDao workDao;
    @Autowired
    public void setWorkDao(WorkDao workDao) {
        this.workDao = workDao;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    Work getWork(@PathVariable Long id) {
        return workDao.getWork(id);
    }
}
