package fontFace.components.previewListPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;

import fontFace.components.common.FontInformation;
import fontFace.components.custom.CustomLabel;
import fontFace.settings.CustomProperties;

public class ListItem extends JPanel {

	private static final long serialVersionUID = -4056643979272579264L;

	private final FontInformation fontInfo;

	private CustomLabel fontPreviewLabel;

	private boolean selected;

	private final PreviewList previewList;

	private int index;

	private Color borderColor;

	private Font font;

	public ListItem(FontInformation fontInfo, int itemIndex, final PreviewList fontPreviewList) {
		this.index = itemIndex;
		this.previewList = fontPreviewList;
		this.fontInfo = fontInfo;

		setFocusable(true);
		addKeyListener(new MyKeyListener());
		addMouseListener(new MyMouseListener());

		selected = false;
		paintSelected();

		initComponents();
	}

	private void initComponents() {
		fontPreviewLabel = new CustomLabel(fontInfo.toString());
		if (fontInfo.isSupported() == false)
			fontPreviewLabel.setForeground(Color.RED);
		font = fontInfo.getFont().deriveFont(Font.PLAIN, CustomProperties.PREVIEWLIST_FONT_SIZE);
		fontPreviewLabel.setFont(font);
		CustomLabel fontPathLabel = new CustomLabel(fontInfo.getPath());

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(10));
		add(fontPreviewLabel);
		add(fontPathLabel);
		add(Box.createVerticalStrut(10));
	}

	public void setFontSize(float fontSize) {
		fontPreviewLabel.setFont(font.deriveFont(fontSize));
	}

	public void setSelected(boolean isSelected) {
		selected = isSelected;
		paintSelected();
	}

	private void paintSelected() {
		setBackground(UIManager.getColor("List.selectionBackground"));
		setOpaque(selected);
		if (selected)
			borderColor = UIManager.getColor("List.dropLineColor");
		else
			borderColor = Color.WHITE;
		repaint();
		setBorder(new ListItemBorder(borderColor, selected));
	}

	public void retractIndex() {
		index -= 1;
	}

	class MyMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (selected == false) {
				setSelected(true);
				previewList.setSelectedIndex(index);
			}
			requestFocusInWindow();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}

	private class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent keyevent) {
		}

		@Override
		public void keyPressed(KeyEvent keyevent) {
			int code = keyevent.getKeyCode();
			if (code == KeyEvent.VK_DOWN) {
				previewList.moveDown();
			} else if (code == KeyEvent.VK_UP) {
				previewList.moveUp();
			}
		}

		@Override
		public void keyReleased(KeyEvent keyevent) {
		}
	}

	public void setLabel(String text) {
		if (text.length() > 0)
			fontPreviewLabel.setText(text);
		else
			fontPreviewLabel.setText(fontInfo.toString());
	}

	public FontInformation getFontInfo() {
		return fontInfo;
	}

}
