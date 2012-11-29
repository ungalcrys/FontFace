package fontFace.components.sampleTextPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import fontFace.components.SampleTextPanel;
import fontFace.components.common.AbstractFontSizeCombo;

public class FontSizeCombo extends AbstractFontSizeCombo {
	private static final long serialVersionUID = 1510763378553013560L;

	private final SampleTextPanel sampleTextPanel;

	public FontSizeCombo(final SampleTextPanel sampleTextPanel) {
		this.sampleTextPanel = sampleTextPanel;
		addItemListener(new MyItemListener());
	}

	private class MyItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				updateSampleTextSize();
			}
		}
	}
	
	public void updateSampleTextSize() {
		String selectedItem = getSelectedItem().toString();
		if (selectedItem.equals("0"))
			setSelectedItem(EMPTY_SELECTION);
		else if (selectedItem.length() > 0) {
			try {
				Integer selectedValue = Integer.valueOf(selectedItem);
				sampleTextPanel.getTextPane().setFontSize(selectedValue);
			} catch (NumberFormatException ex) {
				setSelectedItem(DEFAULT_FONT_SIZES[1]);
			}
			sampleTextPanel.getTextPane().requestFocusInWindow();
		}
	}
}
