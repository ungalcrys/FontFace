package fontFace.components.custom;

import java.awt.Font;

import javax.swing.JLabel;

public class CustomLabel extends JLabel {

	private static final long serialVersionUID = 1394803057733266599L;

	public CustomLabel(String text) {
		super(text);
		setFont(getFont().deriveFont(Font.PLAIN));
	}

	public CustomLabel(String text, int fontStyle) {
		super(text);
		setFont(getFont().deriveFont(fontStyle));
	}

}
