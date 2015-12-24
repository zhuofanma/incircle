package incircle.config;

import incircle.config.resource.MacOSXResourcePath;
import incircle.config.resource.ResourcePaths;
import incircle.config.resource.WindowsResourcePath;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	public ResourcePaths resourcePaths;

	public WebConfig() {
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("win") >= 0) {
			resourcePaths = new WindowsResourcePath();
		} else if (OS.indexOf("mac") >= 0) {
			resourcePaths = new MacOSXResourcePath();
		}
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("images/**").addResourceLocations(resourcePaths.imageResourcePath());
		registry.addResourceHandler("videos/**").addResourceLocations(resourcePaths.videoResourcePath());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	}
}



