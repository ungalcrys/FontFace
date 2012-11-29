package fontFace.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JSplitPane;
import javax.swing.plaf.basic.BasicSplitPaneUI;

import fontFace.components.tabbedListPane.FontNamesListTab;
import fontFace.system.os.OsFactory;

public class MainPane extends JSplitPane {

	private static final long serialVersionUID = -7423598180391279849L;

	private TabbedListPane tabbedPane;

	public static final MainPane INSTANCE = new MainPane();

	private PreviewListPane previewListPane;

	private MainPane() {
		super(JSplitPane.HORIZONTAL_SPLIT, true);

		tabbedPane = new TabbedListPane();
		previewListPane = PreviewListPane.INSTANCE;

		setLeftComponent(tabbedPane);
		setRightComponent(previewListPane);

		((BasicSplitPaneUI) getUI()).getDivider().addMouseListener(new DividerMoveListener());
	}

	public FontNamesListTab getSelectedTab() {
		return tabbedPane.getSelectedTab();
	}

	public void setDefaults() {
		repaintWhenTabChanged();
		previewListPane.setDefaults();
	}

	public void repaintWhenTabChanged() {
		previewListPane.getPreviewList().setFontsList(tabbedPane.getSelectedTab());

		boolean currentLocationValid = getSelectedTab().isCurrentLocationValid();
		ToolBar.INSTANCE.setRefreshEnabled(currentLocationValid);
		if (currentLocationValid) {
			boolean isAdmin = OsFactory.MY_OS.isAdmin();
			boolean isSystemFonts = getSelectedTab().isSystemFonts();
			ToolBar.INSTANCE.setPlusEnabled(isSystemFonts == false && isAdmin);
			ToolBar.INSTANCE.setMinusEnabled(isSystemFonts == false || isAdmin);
		}
	}

	public void removeSelectedFont() {
		previewListPane.getPreviewList().removeSelectedFont();
	}

	public void createFontList() {
		FontNamesListTab selectedTab = tabbedPane.getSelectedTab();
		selectedTab.createFontList();
		previewListPane.getPreviewList().setFontsList(selectedTab);
	}

	public void installSelectedFont() {
		try {
			OsFactory.MY_OS.installFont(previewListPane.getPreviewList().getSelectedItem().getFontInfo());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void filter(String filter) {
		tabbedPane.getSelectedTab().displayElements(filter);
		previewListPane.getPreviewList().setFontsList(tabbedPane.getSelectedTab());
	}

	public void setDefaultDividerLocation() {
		setDividerLocation(tabbedPane.getDefaultWidth());
	}

	private class DividerMoveListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			MenuBar.INSTANCE.checkListview();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

}
