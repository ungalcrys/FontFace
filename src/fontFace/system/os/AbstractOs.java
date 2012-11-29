package fontFace.system.os;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import fontFace.components.common.FontInformation;
import fontFace.settings.SystemProperties;

public abstract class AbstractOs {

	public abstract void installFont(FontInformation fontInformation) throws IOException;

	public boolean deleteFont(String fontPath) {
		return new File(fontPath).delete();
	}

	public FontInformation[] getSystemFonts() {
		String[] filesPaths = getSystemFontPaths();
		FontInformation[] fontInfos = new FontInformation[filesPaths.length];
		for (int i = 0; i < filesPaths.length; i++) {
			String filePath = filesPaths[i];
			int lastSeparator = filePath.lastIndexOf(SystemProperties.FILE_SEPARATOR);
			fontInfos[i] = new FontInformation(filePath.substring(lastSeparator + 1), filePath.substring(0, lastSeparator), true);
		}
		return fontInfos;
	}

	protected static void copy(File source, File dest) throws IOException {
		FileChannel in = null, out = null;
		MappedByteBuffer buf = null;
		try {
			in = new FileInputStream(source).getChannel();
			out = new FileOutputStream(dest).getChannel();
			buf = in.map(FileChannel.MapMode.READ_ONLY, 0, in.size());
			out.write(buf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}

	}

	public abstract boolean isAdmin();

	public abstract String[] getSystemFontPaths();

	public abstract String getMySystemDirectoryPath();

}
