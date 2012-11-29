package fontFace.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler extends Properties {

	private static final long serialVersionUID = -1511291053549892927L;

	private String propertyFilepath;

	protected void loadLocalProperties(String filename) {
		InputStream inputStream = getClass().getResourceAsStream(filename);
		loadInputStream(inputStream);
//		try {
//			inputStream.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	protected void loadExternalProperties(String propertyFilepath) throws FileNotFoundException {
		this.propertyFilepath = propertyFilepath;
		loadInputStream(new FileInputStream(propertyFilepath));
	}

	private void loadInputStream(InputStream inputStream) {
		try {
			load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			store(new FileOutputStream(propertyFilepath), "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
