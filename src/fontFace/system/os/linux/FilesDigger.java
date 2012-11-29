package fontFace.system.os.linux;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fontFace.components.common.FileExtensionFilter;

public class FilesDigger {
	private ArrayList<File> folders;

	private ArrayList<String> files;

	public FilesDigger(List<String> folderPaths) {
		/** adding folders camed as param */
		folders = new ArrayList<File>();
		for (String path : folderPaths) {
			File folder = new File(path);
			if (folder.exists() && folder.canRead())
				folders.add(new File(path));
		}

		/** digg in folder structure */
		for (int i = 0; i < folders.size(); i++) {
			for (File file : folders.get(i).listFiles())
				if (file.isDirectory())
					folders.add(file);
		}

		/** remove possible duplicate folders */
		Collections.sort(folders);
		for (int i = 0; i < folders.size() - 1; i++) {
			if (folders.get(i).equals(folders.get(i + 1))) {
				folders.remove(i + 1);
				i -= 1;
			}
		}

		/** create fontFiles list */
		files = new ArrayList<String>();

		for (File dir : folders) {
			String[] fontFileNames = dir.list(FileExtensionFilter.FONTS);
			for (String name : fontFileNames) {
				files.add(dir.getAbsolutePath() + "/" + name);
			}
		}

		Collections.sort(files);
	}

	public ArrayList<String> getFiles() {
		return files;
	}

}
