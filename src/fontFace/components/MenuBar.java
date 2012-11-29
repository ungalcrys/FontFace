package fontFace.components;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fontFace.components.common.Looks;
import fontFace.settings.ConstantProperties;
import fontFace.settings.CustomProperties;
import fontFace.settings.SystemProperties;
import fontFace.windows.dialogs.AboutDialog;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = -3687616770476854302L;

	public static final MenuBar INSTANCE = new MenuBar();

	private JCheckBoxMenuItem listViewCBMI;

	private JMenuItem saveMI;

	private JMenuItem deleteMI;

	private JMenuItem refreshMI;

	private MenuBar() {
		saveMI = new JMenuItem("Save", 's');
		saveMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ToolBar.INSTANCE.installFont();
			}
		});

		deleteMI = new JMenuItem("Delete", 's');
		deleteMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		deleteMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ToolBar.INSTANCE.deleteFont();
			}
		});

		refreshMI = new JMenuItem("Refresh", 'r');
		refreshMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		refreshMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ToolBar.INSTANCE.refreshFontList();
			}
		});

		JMenuItem quitMI = new JMenuItem("Quit", 'q');
		quitMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		quitMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainWindow.INSTANCE.dispose();
			}
		});

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('f');
		fileMenu.add(saveMI);
		fileMenu.add(deleteMI);
		fileMenu.add(refreshMI);
		fileMenu.add(new JSeparator(JSeparator.HORIZONTAL));
		fileMenu.add(quitMI);

		/** Edit menu */
		JMenuItem filterMI = new JMenuItem("Filter", 'f');
		filterMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK));
		filterMI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ToolBar.INSTANCE.filterRequestFocus();
			}
		});

		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('e');
		editMenu.add(filterMI);

		listViewCBMI = new JCheckBoxMenuItem("List");
		listViewCBMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK));
		listViewCBMI.setMnemonic('l');
		listViewCBMI.setSelected(true);

		listViewCBMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (listViewCBMI.isSelected()) {
					MainPane.INSTANCE.setDefaultDividerLocation();
				} else {
					MainPane.INSTANCE.setDividerLocation(0);
				}
			}
		});

		JMenu lookAndFeelM = new JMenu("Look & Feel");
		lookAndFeelM.setMnemonic('o');
		ButtonGroup buttonGroup = new ButtonGroup();
		for (final LookAndFeel look : Looks.looks) {
			final JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(look.getID());
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (menuItem.isSelected())
						changeLookAndFeel(look);
				}
			});
			buttonGroup.add(menuItem);
			lookAndFeelM.add(menuItem);

			if (look.getID().equals(CustomProperties.LOOK_AND_FEEL)) {
				menuItem.setSelected(true);
			}
		}

		JMenu viewsM = new JMenu("View");
		viewsM.setMnemonic('v');
		viewsM.add(listViewCBMI);
		viewsM.add(lookAndFeelM);

		/** Help */
		JMenuItem helpContentsMI = new JMenuItem("Contents", 'c');
		helpContentsMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK));
		helpContentsMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SystemProperties.openLink(ConstantProperties.APPLICATION_HOMEPAGE);
			}
		});

		JMenuItem aboutMI = new JMenuItem("About", 'a');
		aboutMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new AboutDialog(MainWindow.INSTANCE);
			}
		});

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('h');
		helpMenu.add(helpContentsMI);
		helpMenu.add(aboutMI);

		add(fileMenu);
		add(editMenu);
		add(viewsM);
		add(helpMenu);
	}

	private void changeLookAndFeel(LookAndFeel look) {
		try {
			UIManager.setLookAndFeel(look);
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		if (UIManager.getLookAndFeel().equals(look)) {
			/** redraw frame */
			SwingUtilities.updateComponentTreeUI(MainWindow.INSTANCE);
			MainWindow.INSTANCE.pack();

			/** save option */
			CustomProperties.setValue(ConstantProperties.KEY_LOOK_AND_FEEL, look.getID());
			CustomProperties.saveToDisk();
		}
	}

	public void checkListview() {
		listViewCBMI.setSelected(true);
	}

	public void setSaveEnabled(boolean isEnabled) {
		saveMI.setEnabled(isEnabled);
	}

	public void setDeleteEnabled(boolean isEnabled) {
		deleteMI.setEnabled(isEnabled);
	}

	public void setRefreshEnabled(boolean isEnabled) {
		refreshMI.setEnabled(isEnabled);
	}
}
