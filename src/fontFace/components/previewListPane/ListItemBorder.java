package fontFace.components.previewListPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class ListItemBorder extends AbstractBorder {
	private static final long serialVersionUID = 45922069316719020L;

	protected Color lineColor;

	private final boolean selected;

	public ListItemBorder(Color color, boolean selected) {
		lineColor = color;
		this.selected = selected;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.setColor(lineColor);
		g.drawLine(x, y, width, y);
		if (selected)
			g.drawLine(x, height - 1, width, height - 1);
	}

	public Insets getBorderInsets(Component c) {
		return new Insets(1, 0, 0, 1);
	}

	public boolean isBorderOpaque() {
		return false;
	}

}
