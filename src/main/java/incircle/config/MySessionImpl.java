package incircle.config;

import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class MySessionImpl extends LocalSessionFactoryBean{
	public void init() {
		System.out.println("\n");
		System.out.println("*************** SessionFactory is created! ***************");
		System.out.println("\n");
	}
	
	public void shutdown() {
		System.out.println("\n");
		System.out.println("*************** SessionFactory is shutting down! ***************");
		System.out.println("\n");
	}
}
