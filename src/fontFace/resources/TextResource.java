package fontFace.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class TextResource {
	private static final int END_OF_DOC = -1;

	private StringBuffer text;

	public TextResource(String fileName) {
		URL resource = TextResource.class.getClassLoader().getResource("fontFace/resources/texts/" + fileName);
		InputStream input = null;
		try {
			input = resource.openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (input != null) {
			text = new StringBuffer();

			InputStreamReader reader = new InputStreamReader(input);
			try {
				int charCode = reader.read();
				while (charCode != END_OF_DOC) {
					text.append((char) charCode);
					charCode = reader.read();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public String getText() {
		return text.toString();
	}
}
