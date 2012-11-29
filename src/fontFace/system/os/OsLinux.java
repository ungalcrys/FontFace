package fontFace.system.os;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fontFace.components.common.FontInformation;
import fontFace.settings.SystemProperties;
import fontFace.system.os.linux.FilesDigger;

public class OsLinux extends AbstractOs {

	private String mySystemDirectoryPath;

	@Override
	public void installFont(FontInformation fontInfo) throws IOException {
		File dest = new File(getMySystemDirectoryPath() + "/" + fontInfo.getFileName());
		if (dest.exists() == false) {
			File src = new File(fontInfo.getPath());
			dest.createNewFile();
			copy(src, dest);
		}
	}

	@Override
	public boolean isAdmin() {
		return SystemProperties.USER_NAME.equals("root");
	}

	private static List<String> getSystemFontDirectories() {
		List<String> folders = new ArrayList<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("/etc/fonts/fonts.conf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
		Pattern pattern = Pattern.compile("<dir>.+?</dir>");
		if (scanner != null)
			while (scanner.hasNext()) {
				Matcher matcher = pattern.matcher(scanner.nextLine());
				while (matcher.find()) {
					String match = matcher.group();
					match = match.substring(5, match.length() - 6);
					match = match.replace("~", SystemProperties.USER_HOME);
					folders.add(match);
				}
			}

		return folders;
	}

	@Override
	public String[] getSystemFontPaths() {
		List<String> systemFontDirectories = OsLinux.getSystemFontDirectories();
		mySystemDirectoryPath = systemFontDirectories.get(0) + "/minilectro";

		ArrayList<String> filesPaths = new FilesDigger(systemFontDirectories).getFiles();
		return filesPaths.toArray(new String[filesPaths.size()]);
	}

	@Override
	public String getMySystemDirectoryPath() {
		return mySystemDirectoryPath;
	}

}
