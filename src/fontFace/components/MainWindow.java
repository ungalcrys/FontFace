package fontFace.components;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import fontFace.resources.ImageResource;
import fontFace.settings.ConstantProperties;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = -2803817575490836385L;

	public static final MainWindow INSTANCE = new MainWindow();

	private MainWindow() {
		super(ConstantProperties.APPLICATION_NAME);
		setIconImage(new ImageResource("256x256.png").getImage());

		initComponents();

		setJMenuBar(MenuBar.INSTANCE);

		setDefaults();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(ToolBar.INSTANCE, BorderLayout.PAGE_START);
		contentPane.add(MainPane.INSTANCE, BorderLayout.CENTER);
	}

	public void setDefaults() {
		MainPane.INSTANCE.setDefaults();
	}

}