package incircle.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

	@Bean
    public SessionFactory sessionFactory() {
		System.out.println("\n");
		System.out.println("Create sessionFactory");
		SessionFactory factory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
		System.out.println("Factory exists: ");
		System.out.println(factory != null);
		return factory;
    }
}