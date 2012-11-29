package fontFace.components.custom;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class CustomButton extends JButton {

	private static final long serialVersionUID = 8090004573341624681L;

	public CustomButton(Icon icon, String toolTip) {
		super(icon);
		setToolTipText(toolTip);
		setFocusPainted(false);

		int height = getPreferredSize().height;
		Dimension standardSize = new Dimension(height + 1, height);
		setPreferredSize(standardSize);
		setMaximumSize(standardSize);
		setMinimumSize(standardSize);
	}

	public CustomButton(String text, String toolTip) {
		super(text);
		setToolTipText(toolTip);
		setFocusPainted(false);
	}

	public CustomButton(String text, String toolTip, ActionListener actionListener) {
		this(text, toolTip);
		addActionListener(actionListener);
	}

}
