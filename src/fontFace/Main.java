package fontFace;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fontFace.components.MainWindow;
import fontFace.components.common.Looks;
import fontFace.settings.CustomProperties;

public class Main {
    public static void main(String args[]) {
        String lookId = CustomProperties.LOOK_AND_FEEL;
        if (lookId == null)
            lookId = Looks.looks[0].getID();
        try {
            UIManager.setLookAndFeel(Looks.getLook(lookId));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame.setDefaultLookAndFeelDecorated(true);

        MainWindow.INSTANCE.setVisible(true);
    }
}