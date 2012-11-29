package fontFace.components.custom;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CustomToggleButton extends JToggleButton {

	private static final long serialVersionUID = 5307164125948417185L;

	private CustomToggleButton() {
		setFocusPainted(false);
		// setRolloverEnabled(true);
		setPainted(false);

		addMouseListener(new MyMouseListener());
		addChangeListener(new MyChangeListener());
	}

	public CustomToggleButton(Icon icon, String toolTip) {
		this();
		setIcon(icon);
		setToolTipText(toolTip);

		int height = getPreferredSize().height;
		setPreferredSize(new Dimension(height + 1, height));
	}

	public CustomToggleButton(String text, String toolTip) {
		this();
		setText(text);
		setToolTipText(toolTip);
	}

	private void setPainted(boolean painted) {
		setBorderPainted(painted);
		setContentAreaFilled(painted);
	}

	private class MyMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent mouseevent) {
		}

		@Override
		public void mouseEntered(MouseEvent mouseevent) {
			setPainted(true);
		}

		@Override
		public void mouseExited(MouseEvent mouseevent) {
			if (isSelected() == false) {
				setPainted(false);
			}
		}

		@Override
		public void mousePressed(MouseEvent mouseevent) {
			setPainted(true);
		}

		@Override
		public void mouseReleased(MouseEvent mouseevent) {
		}
	}

	private class MyChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent changeevent) {
			if (isSelected()) {
				setPainted(true);
			} else {
				setPainted(false);
			}
		}
	}

}
