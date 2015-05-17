package ee.tkasekamp.vickywaranalyzer.service;

import java.io.IOException;

public interface UtilService {
	/**
	 * Try to find the install and save game folders.
	 * 
	 * @throws IOException
	 */
	public void guessFolders() throws IOException;

	public void writePathsToFile() throws IOException;

	public String getSaveGameFolder();

	public String getInstallFolder();

	public void setSaveGameFolder(String pathToFolder);

	public void setInstallFolder(String pathToFolder);

	public void reset();

}
