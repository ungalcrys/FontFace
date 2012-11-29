package fontFace.windows.dialogs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import fontFace.components.common.SpecialCharacters;
import fontFace.components.custom.CustomButton;
import fontFace.components.custom.CustomLabel;
import fontFace.components.custom.CustomLinkLabel;
import fontFace.resources.ImageResource;
import fontFace.resources.TextResource;


public class AboutDialog extends JDialog {
	private static final long serialVersionUID = -86744889959771641L;

	private Container contentPane;

	public AboutDialog(JFrame parent) {
		super(parent, "About");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);

		initComponents();

		pack();
		setLocationRelativeTo(parent);
		setResizable(false);
		setVisible(true);
	}

	private void initComponents() {
		JLabel logoLabel = new JLabel(new ImageResource("256x256.png"));

		JLabel labelName = new JLabel("FontFace");
		labelName.setFont(new Font("serif", Font.PLAIN, 22));

		CustomLabel labelVersion = new CustomLabel("Version: 0.1");
		CustomLabel linkWeb = new CustomLinkLabel("http://www.ungalcrys.ro/fontface/");
		CustomLabel labelCopyright = new CustomLabel(SpecialCharacters.COPYRIGHT + " Copyright SC Minilectro SRL 2010.");
		CustomLabel labelRights = new CustomLabel("All rights reserved.");

		CustomButton closeButton = new CustomButton("Close", "Close About Dialog");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(labelName);
		leftPanel.add(Box.createVerticalStrut(2));
		leftPanel.add(labelVersion);
		leftPanel.add(Box.createVerticalStrut(2));
		leftPanel.add(linkWeb);
		leftPanel.add(Box.createVerticalStrut(2));
		leftPanel.add(labelCopyright);
		leftPanel.add(Box.createVerticalStrut(2));
		leftPanel.add(labelRights);
		leftPanel.add(Box.createVerticalGlue());
		leftPanel.add(logoLabel);
		leftPanel.add(Box.createVerticalStrut(10));
		leftPanel.add(closeButton);
		leftPanel.add(Box.createVerticalStrut(10));

		JTextArea creditsTextArea = new JTextArea(new TextResource("credits.txt").getText());
		creditsTextArea.setBackground(UIManager.getColor("Panel.background"));
		creditsTextArea.setEditable(false);
		JScrollPane creditsScrollPane = new JScrollPane(creditsTextArea);
		creditsScrollPane.setBorder(new TitledBorder("Credits"));
		creditsScrollPane.setPreferredSize(new Dimension(500, 150));

		// TODO use a html document same as on www.utorrent.com
		// HTMLDocument doc = new HTMLDocument();
		// doc.setBase(getClass().getClassLoader().getResource("FontViewer/resources/texts/license.txt"));
		JTextArea licenseTextArea = new JTextArea(new TextResource("license.txt").getText());
		licenseTextArea.setBackground(UIManager.getColor("Panel.background"));
		licenseTextArea.setEditable(false);
		licenseTextArea.setLineWrap(true);
		JScrollPane licenseScrollPane = new JScrollPane(licenseTextArea);
		licenseScrollPane.setBorder(new TitledBorder("License"));
		licenseScrollPane.setPreferredSize(new Dimension(500, 300));

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(creditsScrollPane);
		rightPanel.add(Box.createVerticalStrut(10));
		rightPanel.add(licenseScrollPane);

		contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(Box.createHorizontalStrut(10));
		contentPane.add(leftPanel);
		contentPane.add(Box.createHorizontalStrut(20));
		contentPane.add(rightPanel);
	}

}