package fontFace.components.tabbedListPane;

import java.awt.GridLayout;

import fontFace.components.common.FontInformation;
import fontFace.system.os.OsFactory;

public class SystemFontsTab extends FontNamesListTab {
	private static final long serialVersionUID = -3837289606190729395L;

	public SystemFontsTab() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridLayout());
		add(createScrollPane());
		createFontList();
	}

	@Override
	protected FontInformation[] getFontInfos() {
		return OsFactory.MY_OS.getSystemFonts();
	}

	@Override
	public boolean isSystemFonts() {
		return true;
	}

	@Override
	public boolean isCurrentLocationValid() {
		return true;
	}
}