package fontFace.settings;

// TODO clean or fix the class
public class ConstantProperties extends PropertyHandler {
	private static final long serialVersionUID = -444791124900548974L;

	protected final static ConstantProperties INSTANCE = new ConstantProperties();

	/** constants */
	protected final static String COMPANY_DIR_PATH = new StringBuffer(SystemProperties.USER_HOME).append(SystemProperties.FILE_SEPARATOR).append(".minilectro")
			.append(SystemProperties.FILE_SEPARATOR).toString();

	protected static final String APPLICATION_DIR_PATH = new StringBuffer(COMPANY_DIR_PATH).append("FontFace").append(SystemProperties.FILE_SEPARATOR).toString();

	protected static final String CONFIGURATION_FILE_PATH = APPLICATION_DIR_PATH + "config";

	public static final String SAMPLE_TEXT_FILE_PATH = APPLICATION_DIR_PATH + "sampleText";

	/** keys 1 */
	public static final String KEY_APPLICATION_NAME = "application.name";

	public static final String KEY_APPLICATION_VERSION = "application.version";

	public static final String KEY_APPLICATION_HOMEPAGE = "application.homepage";

	public static final String KEY_COMPANY_NAME = "company.name";

	// public static final String KEY_LANGUAGE = "language";

	/** keys 2 */
	public static final String KEY_OTHER_FONTS_FOLDER = "other.fonts.folder";

	public static final String KEY_AUTOLOAD_OTHER_FONTS = "autoload.other.fonts";

	public static final String KEY_SAMPLE_TEXT = "sample.text";

	public static final String KEY_SAMPLE_FONT_SIZE = "sample.font.size";

	public static final String KEY_PREVIEWLIST_TEXT = "previewlist.text";

	public static final String KEY_PREVIEWLIST_FONT_SIZE = "previewlist.font.size";

	public static final String KEY_LOOK_AND_FEEL = "look.and.feel";

	/** properties 1 */
	public final static String APPLICATION_NAME = INSTANCE.getProperty(KEY_APPLICATION_NAME);

	public final static String APPLICATION_VERSION = INSTANCE.getProperty(KEY_APPLICATION_VERSION);

	public final static String APPLICATION_HOMEPAGE = INSTANCE.getProperty(KEY_APPLICATION_HOMEPAGE);

	public final static String COMPANY_NAME = INSTANCE.getProperty(KEY_COMPANY_NAME);

	/** properties 2 */
	// public final static String LANGUAGE = INSTANCE.getProperty(KEY_LANGUAGE);
	// public static final String LAST_COVER_FOLDER = INSTANCE.getProperty(KEY_LAST_COVER_FOLDER);

	private ConstantProperties() {
		loadLocalProperties("config.properties");
	}

}
