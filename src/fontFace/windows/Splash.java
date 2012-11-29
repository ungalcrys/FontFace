package fontFace.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fontFace.settings.ConstantProperties;

// TODO remove
public class Splash extends JWindow {
	private static final long serialVersionUID = 2549276643385787828L;

	public Splash() {
		initComponents(new ImageIcon(this.getClass().getClassLoader().getResource("FontViewer/resources/icons/Logo.gif")));

		setSize(400, 150);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initComponents(ImageIcon splashImage) {
		JLabel jLabel1 = new JLabel(splashImage);
		jLabel1.setBackground(new Color(255, 255, 255));
		jLabel1.setOpaque(true);

		JLabel jLabel2 = new JLabel("Version " + ConstantProperties.APPLICATION_VERSION);
		jLabel2.setBackground(new Color(0, 0, 0));
		jLabel2.setForeground(new Color(255, 255, 255));
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setOpaque(true);

		JPanel jPanel1 = new JPanel();
		jPanel1.setLayout(new BorderLayout());
		jPanel1.add(jLabel1, BorderLayout.CENTER);
		jPanel1.add(jLabel2, BorderLayout.SOUTH);

		// JTextArea jTextArea1 = new TextAreaFromFile("notice.txt");
		// jTextArea1.setBackground(new Color(204, 255, 204));
		// jTextArea1.setForeground(new Color(102, 102, 102));

		JPanel jPanel2 = new JPanel();
		jPanel2.setLayout(new GridLayout(1, 2, 0, 1));
		jPanel2.setBackground(new Color(102, 102, 102));
		jPanel2.setBorder(new LineBorder(new Color(102, 102, 102), 1, true));
		jPanel2.add(jPanel1);
		// jPanel2.add(jTextArea1);

		getContentPane().add(jPanel2, BorderLayout.CENTER);

		pack();
	}

}