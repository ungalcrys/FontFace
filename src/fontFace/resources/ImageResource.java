package fontFace.resources;

import javax.swing.ImageIcon;

public class ImageResource extends ImageIcon {
	private static final long serialVersionUID = -1899666056113058421L;

	public ImageResource(String imageName) {
		super(ImageResource.class.getClassLoader().getResource("fontFace/resources/img/" + imageName));
	}

}
