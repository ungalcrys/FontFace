package fontFace.components.tabbedListPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fontFace.components.MainPane;
import fontFace.components.MainWindow;
import fontFace.components.ToolBar;
import fontFace.components.common.FileExtensionFilter;
import fontFace.components.common.FontInformation;
import fontFace.components.custom.CustomButton;
import fontFace.components.custom.CustomCheckBox;
import fontFace.components.custom.CustomLabel;
import fontFace.components.custom.CustomTextField;
import fontFace.settings.ConstantProperties;
import fontFace.settings.CustomProperties;
import fontFace.windows.dialogs.Messages;

public class OtherFontsTab extends FontNamesListTab {

	private static final long serialVersionUID = -8229547719256591255L;

	private CustomTextField locationTextField;

	private String currentLocation;

	private CustomCheckBox autoloadCB;

	public OtherFontsTab() {
		initComponents();

		if (CustomProperties.AUTOLOAD_OTHER_FONTS) {
			setCurrentLocation(CustomProperties.OTHER_FONTS_FOLDER);
			autoloadCB.setSelected(CustomProperties.AUTOLOAD_OTHER_FONTS);
		}
	}

	@Override
	protected FontInformation[] getFontInfos() {
		String[] fontNames = new File(currentLocation).list(FileExtensionFilter.FONTS);
		FontInformation fontInfos[] = new FontInformation[fontNames.length];
		for (int i = 0; i < fontNames.length; i++) {
			fontInfos[i] = new FontInformation(fontNames[i], currentLocation, false);
		}
		return fontInfos;
	}

	private void setCurrentLocation(String location) {
		currentLocation = location;
		locationTextField.setText(location);
		createFontList();
	}

	private void recreateFontsList(String location) {
		setCurrentLocation(location);
		MainPane.INSTANCE.repaintWhenTabChanged();
		ToolBar.INSTANCE.setRefreshEnabled(true);
	}

	private void initComponents() {
		locationTextField = new CustomTextField(10, "Enter the location where fonts you wish to view are stored here", new SetPathActionListener());
		JPanel locationPanel = new JPanel();
		locationPanel.setMaximumSize(new Dimension(locationPanel.getMaximumSize().width, locationPanel.getPreferredSize().height + 4));
		locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.X_AXIS));
		locationPanel.add(new CustomLabel("Location:"));
		locationPanel.add(Box.createHorizontalStrut(3));
		locationPanel.add(locationTextField);
		locationPanel.add(Box.createHorizontalStrut(3));
		locationPanel.add(new CustomButton("Browse", "Browse for font directory", new BrowseActionListener()));

		autoloadCB = new CustomCheckBox("Autoload");
		autoloadCB.addActionListener(new AutoloadActionListener());
		JPanel autoloadPanel = new JPanel();
		autoloadPanel.setLayout(new BoxLayout(autoloadPanel, BoxLayout.X_AXIS));
		autoloadPanel.add(autoloadCB);
		autoloadPanel.add(Box.createHorizontalGlue());

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(locationPanel);
		add(new JScrollPane(createScrollPane()));
		add(autoloadPanel);
	}

	@Override
	public boolean isSystemFonts() {
		return false;
	}

	@Override
	public boolean isCurrentLocationValid() {
		return currentLocation != null;
	}

	private class BrowseActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			JFileChooser fileChooser;
			try {
				fileChooser = new JFileChooser(new File(CustomProperties.OTHER_FONTS_FOLDER));
			} catch (NullPointerException e) {
				fileChooser = new JFileChooser();
			}
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.showOpenDialog(MainWindow.INSTANCE);
			File file = fileChooser.getSelectedFile();

			/** no file is selected (cancel button pressed) => path=null */
			if (file != null) {
				recreateFontsList(file.getPath());
				CustomProperties.setValue(ConstantProperties.KEY_OTHER_FONTS_FOLDER, currentLocation);
				CustomProperties.saveToDisk();
			}

		}
	}

	private class SetPathActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			File f = new File(locationTextField.getText());
			if (f.exists()) {
				if (f.isDirectory()) {
					recreateFontsList(f.getPath());
				} else
					Messages.showError("The location does not point to a directory.");
			} else
				Messages.showError("Directory does not exist.");
		}
	}

	private class AutoloadActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			CustomProperties.setValue(ConstantProperties.KEY_AUTOLOAD_OTHER_FONTS, Boolean.toString(autoloadCB.isSelected()));
			CustomProperties.saveToDisk();
		}
	}

}