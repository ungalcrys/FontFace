package fontFace.components.previewListPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import fontFace.components.SampleTextPanel;
import fontFace.components.tabbedListPane.FontNamesListTab;

public class PreviewList extends JPanel {

	private static final long serialVersionUID = 4647532588715641159L;

	private ListItem selectedItem;

	private FontNamesListTab fontListTab;

	private int selectedIndex;

	private ListItem[] listItems;

	private JScrollPane scrollPane;

	public PreviewList() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);

		scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(50);
		scrollPane.getViewport().setView(this);
		scrollPane.setPreferredSize(new Dimension(500, 300));
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void displayFonts() {
		int fontsCount = fontListTab.getItemsCount();
		listItems = new ListItem[fontsCount];
		for (int i = 0; i < fontsCount; i++) {
			ListItem listItem = new ListItem(fontListTab.getItem(i), i, this);
			listItems[i] = listItem;
			add(listItem);
		}
	}

	public void setFontsList(FontNamesListTab fontsNameList) {
		fontListTab = fontsNameList;
		selectedItem = null;
		removeAll();
		if (fontListTab.getItemsCount() > 0) {
			displayFonts();
			setSelectedIndex(0);
		}
		revalidate();
	}

	public void setFontSize(int size) {
		for (ListItem listItem : listItems)
			listItem.setFontSize(size);
		revalidate();
		repaint();
		// TODO fix problem, id doesn't update the position on font size changing
		updateScrollPosition();
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public ListItem getSelectedItem() {
		return selectedItem;
	}

	public ListItem getItem(int n) {
		return (ListItem) super.getComponent(n);
	}

	public void setSelectedIndex(int index) {
		selectedIndex = index;
		if (selectedItem != null)
			selectedItem.setSelected(false);
		selectedItem = getItem(index);
		selectedItem.setSelected(true);

		if (fontListTab.getSelectedIndex() != index)
			fontListTab.selectItem(index);

		SampleTextPanel.INSTANCE.setCurrentFont(getSelectedItem().getFontInfo());

		updateScrollPosition();
	}

	/** is hardcoded, up/down/outofsight.matbe cand be done something to make it easier to understand */
	public void updateScrollPosition() {
		JScrollBar vScrollBar = scrollPane.getVerticalScrollBar();
		int vscrollBarValue = vScrollBar.getValue();
		int itemY = selectedItem.getY();
		int itemHeight = selectedItem.getBounds().height;
		int scrollPaneHeight = scrollPane.getBounds().height - scrollPane.getInsets().top - scrollPane.getInsets().bottom;
		int limit = vscrollBarValue + scrollPaneHeight;
		if (itemY + itemHeight > limit && itemY < limit + 1) {
			/** down */
			vScrollBar.setValue(vscrollBarValue + itemY + itemHeight - limit);
		} else if (itemY < vscrollBarValue + 1 && itemY + 2 * itemHeight > vscrollBarValue) {
			/** up */
			vScrollBar.setValue(itemY);
		} else if ((itemY < limit && itemY > vscrollBarValue) == false) {
			/** out of sight */
			vScrollBar.setValue(itemY - (scrollPaneHeight - itemHeight) / 2);
		}
	}

	@Override
	protected boolean processKeyBinding(KeyStroke keystroke, KeyEvent keyevent, int i, boolean flag) {
		return true;
	}

	protected void moveDown() {
		if (getComponentCount() > selectedIndex + 1)
			setSelectedIndex(selectedIndex + 1);
	}

	protected void moveUp() {
		if (selectedIndex > 0)
			setSelectedIndex(selectedIndex - 1);
	}

	public void setItemsLabel(String text) {
		for (ListItem item : listItems)
			item.setLabel(text);
	}

	public void removeSelectedFont() {
		String path = getSelectedItem().getFontInfo().getPath();
		if (new File(path).delete())
			removeSelectedItem();
	}

	private void removeSelectedItem() {
		int selectedIndex = getSelectedIndex();
		remove(selectedIndex);

		/** removes the element from the listItems */
		System.arraycopy(listItems, selectedIndex + 1, listItems, selectedIndex, listItems.length - selectedIndex - 1);

		/** corrects the next buttons indexes */
		int componentCount = getComponentCount();
		for (int i = selectedIndex; i < componentCount; i++) {
			listItems[i].retractIndex();
		}

		if (componentCount > 0) {
			if (selectedIndex < componentCount) {
				setSelectedIndex(selectedIndex);
			} else {
				setSelectedIndex(selectedIndex - 1);
			}
		}
		fontListTab.removeFontItem(selectedIndex);

		revalidate();
		repaint();

	}

}
