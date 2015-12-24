package incircle.config.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by Julian on 12/12/2015.
 */
@Configuration
@Profile("production")
public class ProductionDataSourceConfig {
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/incircle");
        driverManagerDataSource.setUsername("god");
        driverManagerDataSource.setPassword("god");
        return driverManagerDataSource;
    }
}