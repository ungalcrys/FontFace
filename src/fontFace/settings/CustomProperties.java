package fontFace.settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomProperties extends PropertyHandler {
	private static final long serialVersionUID = 8356997670122623922L;

	// TODO add translation
	// private final static String TRANSLATION_FOLDER_NAME = "lang";
	// private static final StringBuffer TRANSLATION_FOLDER_PATH = new
	// StringBuffer(SystemConfiguration.HOME_FOLDER_PATH).append(TRANSLATION_FOLDER_NAME).append(SystemProperties.FILE_SEPARATOR);

	private static CustomProperties INSTANCE = new CustomProperties();

	public final static String OTHER_FONTS_FOLDER = CustomProperties.getValue(ConstantProperties.KEY_OTHER_FONTS_FOLDER);

	public final static boolean AUTOLOAD_OTHER_FONTS = Boolean.parseBoolean(CustomProperties.getValue(ConstantProperties.KEY_AUTOLOAD_OTHER_FONTS));

	public final static String SAMPLE_TEXT = CustomProperties.getValue(ConstantProperties.KEY_SAMPLE_TEXT);

	public final static int SAMPLE_FONT_SIZE = Integer.parseInt(CustomProperties.getValue(ConstantProperties.KEY_SAMPLE_FONT_SIZE));

	public final static String PREVIEWLIST_TEXT = CustomProperties.getValue(ConstantProperties.KEY_PREVIEWLIST_TEXT);

	public final static int PREVIEWLIST_FONT_SIZE = Integer.parseInt(CustomProperties.getValue(ConstantProperties.KEY_PREVIEWLIST_FONT_SIZE));

	public final static String LOOK_AND_FEEL = CustomProperties.getValue(ConstantProperties.KEY_LOOK_AND_FEEL);

	private CustomProperties() {
		try {
			loadExternalProperties(ConstantProperties.CONFIGURATION_FILE_PATH);
		} catch (FileNotFoundException e) {
			new File(ConstantProperties.APPLICATION_DIR_PATH).mkdirs();
			try {
				new File(ConstantProperties.CONFIGURATION_FILE_PATH).createNewFile();
				loadExternalProperties(ConstantProperties.CONFIGURATION_FILE_PATH);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static String getValue(String key) {
		String property = INSTANCE.getProperty(key);
		if (property == null) {
			property = ConstantProperties.INSTANCE.getProperty(key);
		}
		return property;
	}

	public static void setValue(String key, String value) {
		INSTANCE.setProperty(key, value);
	}

	public static void saveToDisk() {
		INSTANCE.save();
	}

}
