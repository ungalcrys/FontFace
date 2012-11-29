package fontFace.components.common;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fontFace.components.SampleTextPanel;


public class GlobalKeyListener implements KeyListener {
	public static GlobalKeyListener INSTANCE = new GlobalKeyListener();

	@Override
	public void keyTyped(KeyEvent keyevent) {
	}

	@Override
	public void keyPressed(KeyEvent keyevent) {
	}

	// TODO add change tab system.other fonts tab keyListener
	@Override
	public void keyReleased(KeyEvent keyevent) {
		SampleTextPanel.INSTANCE.getGlobalKeyListener().keyReleased(keyevent);
	}
}