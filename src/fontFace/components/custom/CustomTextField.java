package fontFace.components.custom;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class CustomTextField extends JTextField {
	private static final long serialVersionUID = -4594436348876682598L;

	public CustomTextField(int columns) {
		super(columns);
		// setMaximumSize(new Dimension(getMaximumSize().width, getPreferredSize().height));
	}

	public CustomTextField(int columns, String toolTip) {
		this(columns);
		setToolTipText(toolTip);
	}

	public CustomTextField(int columns, String toolTip, ActionListener actionListener) {
		this(columns, toolTip);
		addActionListener(actionListener);
	}

}
