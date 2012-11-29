package fontFace.components.common;

import java.awt.Dimension;

import javax.swing.JComponent;

public class ComponentHelper {
	// public static final Insets DEFAULT_INSETS = new Insets(2, 10, 2, 10);

	public static void setFixedSize(JComponent component, Dimension size) {
		component.setPreferredSize(size);
		component.setMinimumSize(size);
		component.setMaximumSize(size);
		component.setSize(size);
	}
}
