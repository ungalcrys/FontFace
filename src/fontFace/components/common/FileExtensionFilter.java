package fontFace.components.common;

import java.io.File;
import java.io.FilenameFilter;

public class FileExtensionFilter implements FilenameFilter {

	public static final FileExtensionFilter FONTS = new FileExtensionFilter(new String[] { ".TTF", ".TTC", ".FON" });

	private final String[] extensions;

	public FileExtensionFilter(String[] extensions) {
		/** all extensions are uppercase */
		this.extensions = new String[extensions.length];
		for (int i = 0; i < extensions.length; i++)
			this.extensions[i] = extensions[i].toUpperCase();
	}

	@Override
	public boolean accept(File dir, String name) {
		name = name.toUpperCase();
		for (String extension : extensions) {
			if (name.endsWith(extension))
				return true;
		}
		return false;
	}

}
