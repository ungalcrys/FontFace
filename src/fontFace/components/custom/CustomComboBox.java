package fontFace.components.custom;

import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class CustomComboBox extends JComboBox {
	private static final long serialVersionUID = -2251643974511245107L;

	public CustomComboBox(Object[] items, String toolTip) {
		super(items);
		setToolTipText(toolTip);
	}

	public CustomComboBox(Object[] items, String toolTip, ItemListener itemListener) {
		this(items, toolTip);
		addItemListener(itemListener);
	}

}
