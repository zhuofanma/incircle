package incircle.config.resource;

/**
 * Created by Julian on 12/23/2015.
 */
public class MacOSXResourcePath implements ResourcePaths {
	@Override
	public String imageFolderPath() {
		return "/Users/zhuofanma/incircle/images/";
	}

	@Override
	public String imageResourcePath() {
		return "file:///Users/zhuofanma/incircle/images/";
	}

	@Override
	public String videoFolderPath() {
		return "/Users/zhuofanma/incircle/videos/";
	}

	@Override
	public String videoResourcePath() {
		return "file:///Users/zhuofanma/incircle/videos/";
	}
}
