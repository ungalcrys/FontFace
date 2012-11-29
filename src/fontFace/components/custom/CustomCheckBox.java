package fontFace.components.custom;

import java.awt.Font;

import javax.swing.JCheckBox;

public class CustomCheckBox extends JCheckBox {

	private static final long serialVersionUID = -3797682807214571609L;

	public CustomCheckBox(String label) {
		super(label);
		setFont(getFont().deriveFont(Font.PLAIN));
	}

}
