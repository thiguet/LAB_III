package services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CSVService<DATA> {
	public abstract List<DATA> getData() throws FileNotFoundException, IOException;
	public abstract void exportCSVToBin(String binFilePath);
}
