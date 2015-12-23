package incircle.config.resource;

/**
 * Created by Julian on 12/23/2015.
 */
public class WindowsResourcePath implements ResourcePaths{
	@Override
	public String imageFolderPath() {
		return "C:/Users/Julian/Codes/Projects/public_images/";
	}

	@Override
	public String imageResourcePath() {
		return "file:///C:/Users/Julian/Codes/Projects/public_images/";
	}

	@Override
	public String videoFolderPath() {
		return "C:/Users/Julian/Codes/Projects/public_videos/";
	}

	@Override
	public String videoResourcePath() {
		return "file:///C:/Users/Julian/Codes/Projects/public_videos/";
	}
}
