package incircle.config;

import incircle.account.dao.AccountDao;
import incircle.account.dao.AccountDaoImpl;
import incircle.account.dao.AccountRoleDao;
import incircle.account.dao.AccountRoleDaoImpl;
import incircle.domain.dao.EducationDao;
import incircle.domain.dao.EducationDaoImpl;
import incircle.domain.dao.WorkDao;
import incircle.domain.dao.WorkDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

	@Autowired
	private DatabaseConfig databaseConfig;
	
	@Autowired
	private SecurityConfig securityConfig;
	
	@Bean
	public AccountDao accountDao() {
		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
		accountDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		accountDaoImpl.setAccountRoleDao(accountRoleDao());
		accountDaoImpl.setPasswordEncoder(securityConfig.passwordEncoder());
		return accountDaoImpl;
		
	}
	
	@Bean
	public AccountRoleDao accountRoleDao() {
		AccountRoleDaoImpl accountRoleDaoImpl = new AccountRoleDaoImpl();
		accountRoleDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		return accountRoleDaoImpl;
		
	}
	
	@Bean
	public EducationDao educationDao() {
		EducationDaoImpl educationDaoImpl = new EducationDaoImpl();
		educationDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		return educationDaoImpl;

	}

	@Bean
	public WorkDao workDao() {
		WorkDaoImpl workDaoImpl = new WorkDaoImpl();
		workDaoImpl.setSessionFactory(databaseConfig.sessionFactory());
		return workDaoImpl;

	}
}
