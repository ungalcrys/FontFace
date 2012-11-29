package fontFace.components.common;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import fontFace.settings.SystemProperties;

public class FontInformation {
	public static final String INVALID_FONT_FORMAT = "<invalid font format>";

	private final String fileName;

	private final String location;

	private final String path;

	private Font font;

	private final boolean systemFont;

	private String err;

	// public static final FontInformation INVALID = new FontInformation("N/A");
	// private FontInformation(String invalidMessage) {
	// this.fileName = null;
	// this.location = null;
	// this.path = invalidMessage;
	// this.systemFont = false;
	// }

	public FontInformation(String fileName, String location, boolean systemFont) {
		this.fileName = fileName;
		this.location = location;
		this.systemFont = systemFont;
		this.path = new StringBuffer(location).append(SystemProperties.FILE_SEPARATOR).append(fileName).toString();
		try {
			this.font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
		} catch (FontFormatException e) {
			this.font = new Font("monospaced", 0, 0);
			this.err = INVALID_FONT_FORMAT;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FontInformation(FontInformation fontInfo) {
		this(fontInfo.getFileName(), fontInfo.getLocation(), fontInfo.isSystemFont());
	}

	/**
	 * 
	 * @return the parent directory path of the font file
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 
	 * @return the font file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sums the <b>location</b> and the <b>fileName</b>. The result is computed only once, in the constructor. After
	 * that it will return the same value per instance.
	 * 
	 * @return the path of the font file
	 */
	public String getPath() {
		return path;
	}

	public Font getFont() {
		return font;
	}

	@Override
	public String toString() {
		if (err != null)
			return "???";
		return font.getName();
	}

	public boolean isSystemFont() {
		return systemFont;
	}

	public boolean isSupported() {
		return err == null;
	}

}
