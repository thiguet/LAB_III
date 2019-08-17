package persistence;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFileSerializable {
	private ObjectInputStream input;
	
	public void openFile(String file) throws IOException {
		input = new ObjectInputStream( new FileInputStream(file) );
	}
	
	public Object getData() throws IOException, ClassNotFoundException {
		Object data;
		try {
			data = input.readObject();
		}
			catch (EOFException endOfFileException) {
			return null;
		}
		return data;
	}
	public void closeFile() throws IOException {
		if ( input != null )
			input.close();
	}
}