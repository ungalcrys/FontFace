package fontFace.components.tabbedListPane;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fontFace.components.PreviewListPane;
import fontFace.components.SampleTextPanel;
import fontFace.components.common.FontInformation;
import fontFace.components.common.GlobalKeyListener;
import fontFace.components.previewListPane.PreviewList;
import fontFace.windows.dialogs.Messages;

public abstract class FontNamesListTab extends JPanel {

	private static final long serialVersionUID = 727222207199980376L;

	private MyListModel listModel;

	protected JList list;

	private FontInformation[] fontInfos;

	public FontNamesListTab() {
		listModel = new MyListModel();
		fontInfos = new FontInformation[0];
	}

	public int getItemsCount() {
		return listModel.size();
	}

	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}

	public FontInformation getItem(int itemNumber) {
		return listModel.getElement(itemNumber);
	}

	public void selectItem(int index) {
		list.setSelectedIndex(index);
		SampleTextPanel.INSTANCE.setCurrentFont(listModel.getElement(index));
	}

	public void displayElements(String filter) {
		listModel.removeAllElements();
		if (filter == null || filter.length() == 0)
			for (FontInformation fontInfo : fontInfos)
				listModel.addElement(fontInfo);
		else {
			String lowerCaseFilter = filter.toLowerCase();
			for (FontInformation fontInfo : fontInfos) {
				if (fontInfo.toString().toLowerCase().contains(lowerCaseFilter))
					listModel.addElement(fontInfo);
			}
		}
	}

	public void createFontList() {
		fontInfos = getFontInfos();
		if (fontInfos.length == 0)
			Messages.showInfo("This folder does not contain any fonts.");
		else
			displayElements(null);
		list.setModel(listModel);
		list.addKeyListener(GlobalKeyListener.INSTANCE);
	}

	protected class MyListModel extends DefaultListModel {
		private static final long serialVersionUID = -396512200494141651L;

		public FontInformation getElement(int index) {
			return ((FontInformation) super.getElementAt(index));
		}
	}

	protected JScrollPane createScrollPane() {
		list = new JList();
		list.setPrototypeCellValue("1234567890");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(list.getFont().deriveFont(Font.PLAIN));
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int index = list.getSelectedIndex();
					list.ensureIndexIsVisible(index);
					PreviewList previewList = PreviewListPane.INSTANCE.getPreviewList();
					if (index != -1 && previewList.getSelectedIndex() != index)
						previewList.setSelectedIndex(index);
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(list);
		return scrollPane;
	}

	public void removeFontItem(int index) {
		int selectedIndex = getSelectedIndex();

		listModel.remove(index);

		if (list.getSelectedIndex() == -1 && list.getComponentCount() > 0) {
			list.setSelectedIndex(selectedIndex);
		}
	}

	protected abstract FontInformation[] getFontInfos();

	public abstract boolean isSystemFonts();

	public abstract boolean isCurrentLocationValid();

}