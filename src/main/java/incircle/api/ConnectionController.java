package incircle.api;

import incircle.domain.dao.ConnectionDao;
import incircle.domain.model.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuofanma on 11/7/15.
 */
@RestController
@RequestMapping(value="/api/connections")
public class ConnectionController {

    private ConnectionDao connectionDao;
    @Autowired
    public void setConnectionDao(ConnectionDao connectionDao) {this.connectionDao = connectionDao;}

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    Connection getConnection(@PathVariable Long id) {
        return connectionDao.getConnection(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    Connection createConnection(@RequestBody Connection connection) {
        return connectionDao.createConnection(connection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteConnection(@PathVariable Long id) {
        connectionDao.deleteConnection(id);
    }
}
