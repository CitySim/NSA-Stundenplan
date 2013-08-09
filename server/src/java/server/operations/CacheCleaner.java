package server.operations;

import java.io.File;
import java.util.ArrayList;

public class CacheCleaner {
	public void cleanCache() {

		final ArrayList<File> filesToDelete = new ArrayList<File>();

		filesToDelete.add(new File(System.getProperty("user.home")
				+ System.getProperty("file.separator") + "stundenplan.pdf"));

		filesToDelete.add(new File(System.getProperty("user.home")
				+ System.getProperty("file.separator") + "stundenplan.png"));

		this.deleteFile(filesToDelete);

	}

	private void deleteFile(final ArrayList<File> filesToDelete) {

		for (final File file : filesToDelete) {
			try {
				if (!file.isDirectory()) {
					file.delete();
				}
			} catch (final Exception e) {
				// noting to do.
			}
		}
	}
}
