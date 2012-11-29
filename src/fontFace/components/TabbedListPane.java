package fontFace.components;

import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fontFace.components.tabbedListPane.FontNamesListTab;
import fontFace.components.tabbedListPane.OtherFontsTab;
import fontFace.components.tabbedListPane.SystemFontsTab;

public class TabbedListPane extends JTabbedPane {

	private static final long serialVersionUID = 8023361578332119779L;

	private int defaultWidth;

	public TabbedListPane() {
		super(TOP, SCROLL_TAB_LAYOUT);
		addTab("System Fonts", new SystemFontsTab());
		addTab("Other Fonts", new OtherFontsTab());
		addChangeListener(new MyChangeListener());

		defaultWidth = getUI().getTabBounds(this, 0).width + getUI().getTabBounds(this, 1).width + 20;
		Dimension preferredSize = new Dimension(defaultWidth, getPreferredSize().height);
		setPreferredSize(preferredSize);
		setMinimumSize(preferredSize);

		// TODO focus painted must be false for tabs
	}

	public FontNamesListTab getSelectedTab() {
		return (FontNamesListTab) getSelectedComponent();
	}

	public int getDefaultWidth() {
		return defaultWidth;
	}

	private class MyChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent changeevent) {
			MainPane.INSTANCE.repaintWhenTabChanged();
		}
	}

}
