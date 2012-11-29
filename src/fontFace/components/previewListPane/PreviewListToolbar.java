package fontFace.components.previewListPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fontFace.components.PreviewListPane;
import fontFace.components.common.AbstractFontSizeCombo;
import fontFace.components.custom.CustomButton;
import fontFace.components.custom.CustomTextField;
import fontFace.resources.ImageResource;
import fontFace.settings.CustomProperties;

public class PreviewListToolbar extends JPanel {
	private static final long serialVersionUID = -7443891471998978877L;

	private final PreviewListPane previewListPanel;

	private CustomTextField labelTF;

	private LabelChangeActionListener labelChangedListener;

	private AbstractFontSizeCombo fontSizeCombo;

	public PreviewListToolbar(PreviewListPane previewListPanel) {
		this.previewListPanel = previewListPanel;
		// setBorder(new EmptyBorder(5, 2, 3, 0));
		setBorder(new EmptyBorder(8, 3, 3, 3));

		initComponents();
		setMaximumSize(new Dimension(getMaximumSize().width, 500));
	}

	private void initComponents() {
		fontSizeCombo = new AbstractFontSizeCombo();
		fontSizeCombo.setSelectedItem(CustomProperties.PREVIEWLIST_FONT_SIZE);
		fontSizeCombo.addItemListener(new FontSizeListener());

		CustomButton charsB = new CustomButton(new ImageResource("special_char.png"), "Autofill with special characters");
		charsB.addActionListener(new SpecialCharsListener());
		CustomButton numberB = new CustomButton(new ImageResource("number.png"), "Autofill with numbers");
		numberB.addActionListener(new NumbersListener());
		CustomButton upperCaseB = new CustomButton(new ImageResource("upper_case.png"), "Autofill with upper case alphabet");
		upperCaseB.addActionListener(new UpperCaseListener());
		CustomButton smallCaseB = new CustomButton(new ImageResource("small_case.png"), "Autofill with small case alphabet");
		smallCaseB.addActionListener(new SmallCaseListener());
		CustomButton toggleCaseB = new CustomButton(new ImageResource("text_smallcaps.png"), "Change text case");
		toggleCaseB.addActionListener(new ToggleCaseListener());
		CustomButton clearB = new CustomButton(new ImageResource("edit-clear.png"), "Clear text");
		clearB.addActionListener(new ClearListener());
		labelTF = new CustomTextField(20);
		labelChangedListener = new LabelChangeActionListener();
		labelTF.addActionListener(labelChangedListener);

		CustomButton changeB = new CustomButton("Change", "Change labels");
		changeB.addActionListener(labelChangedListener);
		labelTF.setPreferredSize(changeB.getPreferredSize());

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		int spacing = 2;
		add(fontSizeCombo);
		add(Box.createHorizontalStrut(spacing * 2));
		add(charsB);
		add(Box.createHorizontalStrut(spacing));
		add(numberB);
		add(Box.createHorizontalStrut(spacing));
		add(upperCaseB);
		add(Box.createHorizontalStrut(spacing));
		add(smallCaseB);
		add(Box.createHorizontalStrut(spacing));
		add(toggleCaseB);
		add(Box.createHorizontalStrut(spacing));
		add(clearB);
		add(Box.createHorizontalStrut(spacing));
		add(labelTF);
		add(Box.createHorizontalStrut(spacing));
		add(changeB);
		add(Box.createHorizontalGlue());
	}

	class LabelChangeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			previewListPanel.getPreviewList().setItemsLabel(labelTF.getText());
		}
	}

	class ToggleCaseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = labelTF.getText();
			if (text.length() > 0) {
				if (Character.isLowerCase(labelTF.getText().charAt(0)))
					text = text.toUpperCase();
				else
					text = text.toLowerCase();
				labelTF.setText(text);
				labelChangedListener.actionPerformed(e);
			}
		}
	}

	class ClearListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			labelTF.setText(new String());
			labelChangedListener.actionPerformed(e);
		}
	}

	class SmallCaseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			labelTF.setText("abcdefghijklmnopqrstuvwxyz");
			labelChangedListener.actionPerformed(e);
		}
	}

	class UpperCaseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			labelTF.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			labelChangedListener.actionPerformed(e);
		}
	}

	class SpecialCharsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			labelTF.setText("`~!@#$%^&*() -_=+[]{}\\|;:'\",<.>/?");
			labelChangedListener.actionPerformed(e);
		}
	}

	class NumbersListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			labelTF.setText("1234567890");
			labelChangedListener.actionPerformed(e);
		}
	}

	class FontSizeListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			String fontSize = fontSizeCombo.getSelectedItem().toString();
			if (e.getStateChange() == ItemEvent.SELECTED && fontSize.length() > 0) {
				previewListPanel.getPreviewList().setFontSize(Integer.valueOf(fontSize));
				// previewListPanel.getPreviewList().updateScrollPosition();
			}
		}
	}

}
