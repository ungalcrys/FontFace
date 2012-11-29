package fontFace.components.sampleTextPanel;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import fontFace.components.SampleTextPanel;
import fontFace.components.common.AbstractFontSizeCombo;
import fontFace.components.common.GlobalKeyListener;

public class TextPane extends JTextPane {

	private static final long serialVersionUID = -997038104834651114L;

	private StyledDocument styledDoc;

	private final SampleTextPanel sampleTextPanel;

	// TODO enable/disable save/load buttons when text is modified
	public TextPane(SampleTextPanel sampleTextPanel) {
		this.sampleTextPanel = sampleTextPanel;
		setEditable(true);
		setBackground(Color.WHITE);
		addCaretListener(new MyCaretListener());
		addKeyListener(GlobalKeyListener.INSTANCE);
	}

	@Override
	public void setStyledDocument(StyledDocument doc) {
		super.setStyledDocument(doc);
		styledDoc = doc;
	}

	private AttributeSet getCharacterAttributes(int position) {
		return styledDoc.getCharacterElement(position - 1).getAttributes();
	}

	private void updateSelectionStyleControls() {
		boolean bold = true;
		boolean italic = true;
		int fontSize = (Integer) getCharacterAttributes(getSelectionStart() + 1).getAttribute(StyleConstants.FontSize);
		for (int i = getSelectionStart() + 1; i < getSelectionEnd() + 1; i++) {
			AttributeSet charAttributes = getCharacterAttributes(i);
			if ((Boolean) charAttributes.getAttribute(StyleConstants.Bold) == false)
				bold = false;
			if ((Boolean) charAttributes.getAttribute(StyleConstants.Italic) == false)
				italic = false;
			if ((Integer) charAttributes.getAttribute(StyleConstants.FontSize) != fontSize)
				fontSize = AbstractFontSizeCombo.NO_SELECTION_VALUE;
			if (bold == false && italic == false && fontSize == AbstractFontSizeCombo.NO_SELECTION_VALUE)
				break;
		}
		updateStyleControls(bold, italic, fontSize);
	}

	private void updatePositionStyleControls() {
		AttributeSet attributes = getCharacterAttributes(getCaretPosition());
		updateStyleControls((Boolean) attributes.getAttribute(StyleConstants.Bold), (Boolean) attributes.getAttribute(StyleConstants.Italic),
				(Integer) attributes.getAttribute(StyleConstants.FontSize));
	}

	private void updateStyleControls(Boolean bold, Boolean italic, Integer fontSize) {
		sampleTextPanel.getBoldStyleButton().setSelected(bold);
		sampleTextPanel.getItalicStyleButton().setSelected(italic);
		sampleTextPanel.getFontSizeCombo().setSelectedItem(fontSize);
	}

	public void updateFontFamily(String fontLogicalName) {
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setFontFamily(attributes, fontLogicalName);
		getStyledDocument().setCharacterAttributes(0, getText().length(), attributes, false);
	}

	public void setFontBold() {
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setBold(attributes, sampleTextPanel.isBoldStyle());
		setCharacterAttributes(attributes, false);
	}

	public void setFontItalic() {
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setItalic(attributes, sampleTextPanel.isItalicStyle());
		setCharacterAttributes(attributes, false);
	}

	public void setFontSize(int fontSize) {
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setFontSize(attributes, fontSize);
		setCharacterAttributes(attributes, false);
	}

	private class MyCaretListener implements CaretListener {

		@Override
		public void caretUpdate(CaretEvent e) {
			String selectedText = getSelectedText();
			if (selectedText != null)
				updateSelectionStyleControls();
			else
				updatePositionStyleControls();
		}
	}

}
