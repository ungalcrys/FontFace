package fontFace.windows.dialogs.components;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {
	private static final long serialVersionUID = 580549894387529708L;

	private static final int[][] MARGIN_TYPE = new int[][] { new int[] { 15, 10 }, new int[] { 10, 30 } };

	private static final int SPACING_DEFAULT = 5;

	public static final int TITLE = 0;

	public static final int ITEM = 1;

	private static final int TOP = 0;

	private static final int LEFT = 1;

	public OptionPanel(JPanel parent, int type, Component[] components) {
		parent.add(Box.createVerticalStrut(MARGIN_TYPE[type][TOP]));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalStrut(MARGIN_TYPE[type][LEFT]));
		for (Component component : components) {
			add(component);
			add(Box.createHorizontalStrut(SPACING_DEFAULT));
		}
		add(Box.createHorizontalGlue());
		parent.add(this);
	}

}