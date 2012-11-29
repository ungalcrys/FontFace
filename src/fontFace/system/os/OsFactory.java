package fontFace.system.os;

import fontFace.settings.SystemProperties;

public class OsFactory {

	private final static String WINDOWS_7 = "Windows 7";

	private final static String LINUX = "Linux";

	public static final AbstractOs MY_OS = getOs();

	private static AbstractOs getOs() {
		if (SystemProperties.OS_NAME.equals(WINDOWS_7))
			return new OsWindows7();
		else if (SystemProperties.OS_NAME.equals(LINUX))
			return new OsLinux();
		else
			return null;
	}

}
