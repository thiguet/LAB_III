package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public abstract class RAFDAO<E> implements FileService {
	protected RandomAccessFile file;
	private String filePath;
	protected int regsAmount;
	protected int regsBytesSize;
	protected int headerBytesSize;

	protected final String INVALID_KEY 		 = "A chave informada não é válida!",
						   INVALID_FILE_PATH = "O diretório e arquivo informado não é valido!",
						   FILE_NOT_FOUND 	 = "Não foi possível encontrar o arquivo!";	
	
	public RAFDAO (String filePath) throws Exception {
		if(filePath.trim().isEmpty())
			throw new Exception(this.INVALID_FILE_PATH);

		this.filePath 	  = filePath;
		this.file 	      = null;
		
		this.regsAmount = 0;
		this.headerBytesSize = (Integer.SIZE / 8);
		
		this.openFile();
		this.closeFile();
	}

	@Override
	public void openFile() {
		File f = new File(this.filePath);
		try {
			if (!f.exists()) {
				this.regsAmount = 0;
				this.initHeader();
			} else {
				this.file = new RandomAccessFile(f, "rw");
				this.setNumRegistros();
			}
		} catch (FileNotFoundException e) {
			System.out.println(this.FILE_NOT_FOUND + e.getMessage());
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	@Override
	public void closeFile() {
		try {
			file.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar o arquivo! \n" + e.getMessage());
			System.exit(0);
		}
	}
	
	public void removeFile() {
		this.file = null;
		File file = new File(filePath); 
         
        if(file.delete()) { 
            System.out.println("File deleted successfully"); 
        } else { 
            System.out.println("Failed to delete the file"); 
        } 
	}

	private void initHeader() throws IOException {
		File f = new File(this.filePath);
		if (!f.exists()) {
			this.file = new RandomAccessFile(f, "rw");
			this.initHeader();
		} 
	
		this.file.seek(0);
		this.file.writeInt(0);
	}
	
	public int getRegsAmout() {
		return regsAmount;
	}

	public int getTamRegistros() {
		return regsBytesSize;
	}

	public int getTamCabecalho() {
		return headerBytesSize;
	}
	
	private void setNumRegistros() throws Exception {
		this.file.seek(0);
		this.regsAmount = this.file.readInt();	
	}
	
	protected int getLastFilePosition() {
		return this.headerBytesSize + (this.regsAmount * this.regsBytesSize);
	}
	
	protected int getFilePosition(int key) throws Exception {
		if (key >= this.regsAmount || key < 0) 
			throw new Exception(this.INVALID_KEY);		
		
		return this.headerBytesSize + (key * this.regsBytesSize);
	}	
	
	public abstract void appendData(E data) throws Exception;	
	public abstract E getData(int key) throws Exception;			
	public abstract boolean setData(E data, int key) throws Exception;			
	public abstract List<E> getAllData() throws Exception;		
}
