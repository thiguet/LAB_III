package services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class ACSVService<E> {
	protected String pathToCSV;
	public String getPathToCSV() {
		return pathToCSV;
	}
	public void setPathToCSV(String pathToCSV) {
		this.pathToCSV = pathToCSV;
	}
	public abstract List<E> getData() throws FileNotFoundException, IOException;
	public abstract void exportCSVToBin(String binFilePath);
}
