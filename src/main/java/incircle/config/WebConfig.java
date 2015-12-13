package incircle.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	private final static Logger log = Logger.getLogger(WebConfig.class.getName());
	public final static String imageFolerPath = "C:/Users/Julian/Codes/Projects/public_images/";
	public final static String videoFolerPath = "C:/Users/Julian/Codes/Projects/public_videos/";
	public final static String imageResourcePath = "file:///C:/Users/Julian/Codes/Projects/public_images/";
	public final static String videoResourcePath = "file:///C:/Users/Julian/Codes/Projects/public_videos/";

//	public final static String imageFolerPath = "/vagrant/Projects/public_images/";
//	public final static String imageResourcePath = "file:///vagrant/Projects/public_images/";

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("images/**").addResourceLocations(imageResourcePath);
		registry.addResourceHandler("videos/**").addResourceLocations(videoResourcePath);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	}
}
