package fontFace.components.common;

import java.awt.Dimension;

import fontFace.components.custom.CustomComboBox;
import fontFace.settings.CustomProperties;

public class AbstractFontSizeCombo extends CustomComboBox {

	private static final long serialVersionUID = 1510763378553013560L;

	public static final Object[] DEFAULT_FONT_SIZES = new Object[] { "", 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 22, 24, 26, 28, 32, 36, 40, 44, 48, 54, 60, 66, 72, 80, 88, 96 };

	public static final int NO_SELECTION_VALUE = 0;

	public static final String EMPTY_SELECTION = new String();

	public AbstractFontSizeCombo() {
		super(DEFAULT_FONT_SIZES, "Size");
		setEditable(true);
		ComponentHelper.setFixedSize(this, new Dimension(60, getPreferredSize().height));
	}

	public void setDefaults() {
		setSelectedItem(CustomProperties.SAMPLE_FONT_SIZE);
	}

}
