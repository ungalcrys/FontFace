package fontFace.components;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import fontFace.components.custom.CustomButton;
import fontFace.components.custom.CustomLabel;
import fontFace.components.custom.CustomTextField;
import fontFace.resources.ImageResource;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 3962506116889263764L;

	private CustomButton minusB;

	private CustomButton plusB;

	private CustomButton refreshB;

	public static final ToolBar INSTANCE = new ToolBar();

	private CustomTextField filterTF;

	private ToolBar() {
		super(JToolBar.HORIZONTAL);
		setFloatable(false);

		initComponents();
	}

	private void initComponents() {
		plusB = new CustomButton(new ImageResource("plus.png"), "Save to system fonts (CTRL+S)");
		plusB.addActionListener(new InstallActionListener());
		minusB = new CustomButton(new ImageResource("minus.png"), "Delete (Del)");
		minusB.addActionListener(new DeleteActionListener());
		refreshB = new CustomButton(new ImageResource("refresh.png"), "Refresh (F5)");
		refreshB.addActionListener(new RefreshActionListener());
		filterTF = new CustomTextField(10, "Search (CTRL+F)");
		filterTF.setMaximumSize(filterTF.getPreferredSize());
		filterTF.addCaretListener(new SearchListener());

		// setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(plusB);
		add(minusB);
		add(new JToolBar.Separator());
		add(refreshB);
		add(new JToolBar.Separator());
		// TODO solve filter align to right
		// add(Box.createHorizontalGlue());
		add(new CustomLabel("Filter "));
		add(filterTF);
	}

	public void setPlusEnabled(boolean isEnabled) {
		plusB.setEnabled(isEnabled);
		MenuBar.INSTANCE.setSaveEnabled(isEnabled);
	}

	public void setMinusEnabled(boolean isEnabled) {
		minusB.setEnabled(isEnabled);
		MenuBar.INSTANCE.setDeleteEnabled(isEnabled);
	}

	public void setRefreshEnabled(boolean isEnabled) {
		refreshB.setEnabled(isEnabled);
		MenuBar.INSTANCE.setRefreshEnabled(isEnabled);
	}

	public void filterRequestFocus() {
		filterTF.requestFocusInWindow();
	}

	public void installFont() {
		MainPane.INSTANCE.installSelectedFont();
	}

	public void deleteFont() {
		MainPane.INSTANCE.removeSelectedFont();
	}

	public void refreshFontList() {
		MainWindow.INSTANCE.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setRefreshEnabled(false);
		MainPane.INSTANCE.createFontList();
		setRefreshEnabled(true);
		MainWindow.INSTANCE.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	class InstallActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			installFont();
		}

	}

	class DeleteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			deleteFont();
		}

	}

	class RefreshActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			refreshFontList();
		}
	}

	class SearchListener implements CaretListener {
		private String lastSearch;

		@Override
		public void caretUpdate(CaretEvent e) {
			String search = filterTF.getText();
			if (search.equals(lastSearch) == false) {
				lastSearch = search;
				MainPane.INSTANCE.filter(search);
			}
		}
	}

}
