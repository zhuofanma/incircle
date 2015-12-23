package incircle.config.datasource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by Julian on 12/12/2015.
 */
public interface DataSourceConfig {
    DriverManagerDataSource dataSource();
}

