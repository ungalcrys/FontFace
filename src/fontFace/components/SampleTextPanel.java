package fontFace.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledDocument;

import sun.font.FontManager;
import fontFace.components.common.FontInformation;
import fontFace.components.common.HasGlobalKeyListener;
import fontFace.components.custom.CustomButton;
import fontFace.components.custom.CustomToggleButton;
import fontFace.components.sampleTextPanel.FontSizeCombo;
import fontFace.components.sampleTextPanel.TextPane;
import fontFace.resources.ImageResource;
import fontFace.settings.ConstantProperties;

public class SampleTextPanel extends JPanel implements HasGlobalKeyListener {
	private static final long serialVersionUID = -2568967327029519662L;

	private FontInformation fontInfo;

	public static SampleTextPanel INSTANCE = new SampleTextPanel();

	private CustomButton saveButton;

	public KeyListener keyListener;

	private FontSizeCombo fontSizeCombo;

	private TextPane textPane;

	private FontStyleButton boldStyleB;

	private FontStyleButton italicStyleB;

	private CustomButton loadDefaultButton;

	private CustomButton loadButton;

	private SampleTextPanel() {
		initComponents();
		keyListener = new MyKeyListener();
	}

	public void setCurrentFont(FontInformation fontInfo) {
		this.fontInfo = new FontInformation(fontInfo);
		if (fontInfo.isSystemFont() == false)
			FontManager.registerFont(fontInfo.getFont());
		updateDisplay();

	}

	public FontSizeCombo getFontSizeCombo() {
		return fontSizeCombo;
	}

	public void updateDisplay() {
		textPane.updateFontFamily(fontInfo.toString());
	}

	private void initComponents() {

		boldStyleB = new FontStyleButton(new ImageResource("text_bold.png"), "Bold (CTRL+B)", Font.BOLD);
		boldStyleB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBoldStyle(boldStyleB.isSelected());
			}
		});

		italicStyleB = new FontStyleButton(new ImageResource("text_italic.png"), "Italic (CTRL+I)", Font.ITALIC);
		italicStyleB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setItalicStyle(italicStyleB.isSelected());
			}
		});

		fontSizeCombo = new FontSizeCombo(this);

		textPane = new TextPane(this);

		CustomButton selectAllB = new CustomButton(new ImageResource("magnet.png"), "Select all (CTRL+A)");
		selectAllB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textPane.requestFocusInWindow();
				textPane.selectAll();
			}
		});

		JPanel fontPropertiesPanel = new JPanel();
		fontPropertiesPanel.setLayout(new BoxLayout(fontPropertiesPanel, BoxLayout.X_AXIS));
		fontPropertiesPanel.add(boldStyleB);
		fontPropertiesPanel.add(Box.createHorizontalStrut(1));
		fontPropertiesPanel.add(italicStyleB);
		fontPropertiesPanel.add(Box.createHorizontalStrut(5));
		fontPropertiesPanel.add(fontSizeCombo);
		fontPropertiesPanel.add(Box.createHorizontalStrut(5));
		fontPropertiesPanel.add(selectAllB);
		fontPropertiesPanel.add(Box.createHorizontalGlue());

		saveButton = new CustomButton(new ImageResource("set_sample.png"), "Save current sample text");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				saveCustomSampleText();
			}
		});

		loadButton = new CustomButton(new ImageResource("load_sample.png"), "Load sample text.");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				loadCustomSampleText();
			}
		});

		loadDefaultButton = new CustomButton(new ImageResource("default_sample.png"), "Load default sample text.");
		loadDefaultButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				loadDefaultSampleText();
			}
		});

		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
		optionsPanel.add(fontPropertiesPanel);
		optionsPanel.add(Box.createHorizontalGlue());
		optionsPanel.add(saveButton);
		optionsPanel.add(Box.createHorizontalStrut(2));
		optionsPanel.add(loadButton);
		optionsPanel.add(Box.createHorizontalStrut(2));
		optionsPanel.add(loadDefaultButton);
		optionsPanel.setBorder(new EmptyBorder(10, 1, 2, 1));

		setLayout(new BorderLayout(2, 2));
		add(optionsPanel, BorderLayout.NORTH);
		add(new JScrollPane(textPane), BorderLayout.CENTER);
		setPreferredSize(new Dimension(320, 240));
	}

	private void saveCustomSampleText() {
		StyledDocument objToSave = textPane.getStyledDocument();
		File file = new File(ConstantProperties.SAMPLE_TEXT_FILE_PATH);
		try {
			if (file.exists() == false)
				file.createNewFile();
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
			outputStream.writeObject(objToSave);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadCustomSampleText() {
		File file = new File(ConstantProperties.SAMPLE_TEXT_FILE_PATH);
		if (file.exists())
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
				StyledDocument obj = (StyledDocument) inputStream.readObject();
				textPane.setStyledDocument(obj);
				formatTextPane();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		else {
			loadDefaultSampleText();
			formatTextPane();
		}
	}

	private void loadDefaultSampleText() {
		StyledDocument obj = null;
		URL resource = getClass().getClassLoader().getResource("fontFace/resources/texts/defaultSampleText");
		try {
			File file = new File(resource.toURI());
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
			obj = (StyledDocument) inputStream.readObject();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (obj != null) {
			textPane.setStyledDocument(obj);
			formatTextPane();
		}
	}

	public void setDefaults() {
		loadCustomSampleText();
		formatTextPane();
	}

	private void formatTextPane() {
		textPane.selectAll();
		// fontSizeCombo.updateSampleTextSize();
		textPane.select(0, 0);
		textPane.updateFontFamily(fontInfo.toString());
	}

	@Override
	public KeyListener getGlobalKeyListener() {
		return keyListener;
	}

	public TextPane getTextPane() {
		return textPane;
	}

	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent keyevent) {
		}

		@Override
		public void keyPressed(KeyEvent keyevent) {
		}

		@Override
		public void keyReleased(KeyEvent keyevent) {
			if (keyevent.getModifiers() == InputEvent.CTRL_MASK && keyevent.getKeyCode() == KeyEvent.VK_B) {
				boolean selected = !boldStyleB.isSelected();
				boldStyleB.setSelected(selected);
				setBoldStyle(selected);
			}
			if (keyevent.getModifiers() == InputEvent.CTRL_MASK && keyevent.getKeyCode() == KeyEvent.VK_I) {
				boolean selected = !italicStyleB.isSelected();
				italicStyleB.setSelected(selected);
				setItalicStyle(selected);
			}
		}
	}

	public class FontStyleButton extends CustomToggleButton {
		private static final long serialVersionUID = 1426017125172854437L;

		private final int fontStyle;

		private FontStyleButton(Icon icon, String toolTip, int fontStyle) {
			super(icon, toolTip);
			this.fontStyle = fontStyle;
		}

		public int getFontStyle() {
			return fontStyle;
		}
	}

	public boolean isBoldStyle() {
		return boldStyleB.isSelected();
	}

	public boolean isItalicStyle() {
		return italicStyleB.isSelected();
	}

	public void setBoldStyle(boolean selected) {
		textPane.setFontBold();
		textPane.requestFocusInWindow();
	}

	public void setItalicStyle(boolean selected) {
		textPane.setFontItalic();
		textPane.requestFocusInWindow();
	}

	public FontStyleButton getBoldStyleButton() {
		return boldStyleB;
	}

	public FontStyleButton getItalicStyleButton() {
		return italicStyleB;
	}

}