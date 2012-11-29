package fontFace.components.custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fontFace.settings.SystemProperties;

public class CustomLinkLabel extends CustomLabel {

	private static final long serialVersionUID = -3540142238928612480L;

	private final String text;

	private final String url;

	public CustomLinkLabel(String text, String url) {
		super(text);
		this.text = text;
		this.url = url;
		setFont(getFont().deriveFont(Font.PLAIN));
		addMouseListener(new MyMouseListener());

		setForeground(Color.BLUE);
	}

	public CustomLinkLabel(String text) {
		this(text, text);
	}

	private class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			SystemProperties.openLink(url);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			setForeground(Color.BLUE);
			setText("<html><u>" + text + "</u></html>");
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			setForeground(Color.BLUE);
			setText(text);
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

	}
}
