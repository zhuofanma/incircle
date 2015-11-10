package incircle.domain.dao;

import incircle.domain.model.Work;

import java.util.List;

/**
 * Created by zhuofanma on 11/4/15.
 */
public interface WorkDao {
    Work getWork(Long id);
    Work createWork(Work work);
    Work updateWork(Work work);
    void deleteWork(Long id);
}
