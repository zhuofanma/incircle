package incircle.api;

import incircle.domain.dao.WorkDao;
import incircle.domain.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuofanma on 11/4/15.
 */

@RestController
@RequestMapping(value="/api/works")
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

    @RequestMapping(value="", method= RequestMethod.POST)
    Work createWork(@RequestBody Work work) {
        return workDao.createWork(work);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Work updateWork(@RequestBody Work work, @PathVariable Long id) {
        return workDao.updateWork(work);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public void deleteWork(@PathVariable Long id) {
        workDao.deleteWork(id);
    }
}
