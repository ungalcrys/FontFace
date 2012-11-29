package fontFace.settings;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class SystemProperties {

	public static final String OS_NAME = System.getProperty("os.name");

	public static final String USER_NAME = System.getProperty("user.name");

	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	public static final String USER_HOME = System.getProperty("user.home");

	public static void openLink(String url) {
		Desktop desktop = Desktop.getDesktop();
		URI uri = URI.create(url);
		try {
			desktop.browse(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
