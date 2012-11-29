package fontFace.system.os;

import java.io.File;
import java.io.IOException;

import fontFace.components.common.FileExtensionFilter;
import fontFace.components.common.FontInformation;
import fontFace.settings.SystemProperties;

public class OsWindows7 extends AbstractOs {

	private static final String TEST_ADMIN_FILE = "test.admin";

	private static final String FONTS_SYSTEM_FOLDER = System.getenv("SystemRoot") + "\\Fonts";

	@Override
	public void installFont(FontInformation fontInfo) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isAdmin() {
		try {
			File file = new File(FONTS_SYSTEM_FOLDER + SystemProperties.FILE_SEPARATOR + TEST_ADMIN_FILE);
			file.createNewFile();
			file.delete();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public String[] getSystemFontPaths() {
		return new File(FONTS_SYSTEM_FOLDER).list(FileExtensionFilter.FONTS);
	}

	@Override
	public String getMySystemDirectoryPath() {
		return FONTS_SYSTEM_FOLDER;
	}

}
