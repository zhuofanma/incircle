package incircle.api;

import incircle.domain.dao.ConnectionDao;
import incircle.domain.model.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhuofanma on 11/7/15.
 */
@RestController
@RequestMapping(value="/api/connection")
public class ConnectionController {

    private ConnectionDao connectionDao;
    @Autowired
    public void setConnectionDao(ConnectionDao connectionDao) {this.connectionDao = connectionDao;}

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    Connection getConnection(@PathVariable Long id) {
        return connectionDao.getConnection(id);
    }
}
