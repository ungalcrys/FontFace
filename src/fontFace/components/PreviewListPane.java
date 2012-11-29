package fontFace.components;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import fontFace.components.previewListPane.PreviewList;
import fontFace.components.previewListPane.PreviewListToolbar;

// TODO add search bar
public class PreviewListPane extends JPanel {
	private static final long serialVersionUID = 1935256043120809898L;

	private PreviewList previewList;

	// private JLabel navInfoLabel;

	public static final PreviewListPane INSTANCE = new PreviewListPane();

	private SampleTextPanel sampleTextPanel;

	public PreviewListPane() {
		initComponents();
	}

	private void initComponents() {
		previewList = new PreviewList();
		sampleTextPanel = SampleTextPanel.INSTANCE;

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
		splitPane.setResizeWeight(0.5);
		splitPane.setTopComponent(previewList.getScrollPane());
		splitPane.setBottomComponent(sampleTextPanel);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(splitPane);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new PreviewListToolbar(this));
		add(panel);
	}

	public PreviewList getPreviewList() {
		return previewList;
	}

	public void setDefaults() {
		sampleTextPanel.setDefaults();
		previewList.setSelectedIndex(0);
	}

}