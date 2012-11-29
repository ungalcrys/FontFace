package fontFace.windows.dialogs;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import fontFace.components.MainWindow;

public class Messages {
	private static final String ERROR = "Error";

	private static final String INFO = "Information";

	public static void showError(Component owner, String message) {
		JOptionPane.showMessageDialog(SwingUtilities.getRoot(owner), message, ERROR, JOptionPane.ERROR_MESSAGE, null);
	}

	public static void showError(String message) {
		JOptionPane.showMessageDialog(MainWindow.INSTANCE, message, ERROR, JOptionPane.ERROR_MESSAGE, null);
	}

	public static void showInfo(String message) {
		JOptionPane.showMessageDialog(MainWindow.INSTANCE, message, INFO, JOptionPane.INFORMATION_MESSAGE, null);
	}

	// TODO: investigate SwingUtilities class

	// public static void showError(Component owner, String message, Exception e) {
	// e.printStackTrace();
	// showError(owner, message);
	// }
}
