package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public abstract class RAFDAO<E> {
	protected RandomAccessFile file;
	private String filePath;
	protected int numRegistros;
	protected int tamRegistros;
	protected int tamCabecalho;

	public RAFDAO (String path) throws Exception {
		if(path.isEmpty())
			throw new Exception("O diret�rio e arquivo informado n�o � valido!");

		this.filePath = path;
		this.file = null;
		
		this.numRegistros = 0;
		this.tamCabecalho = (Integer.SIZE / 8); // Number of registers
	
		this.openFile();
		this.setNumRegistros();
		this.closeFile();
	}

	public int getNumRegistros() {
		return numRegistros;
	}

	public int getTamRegistros() {
		return tamRegistros;
	}

	public int getTamCabecalho() {
		return tamCabecalho;
	}
	
	private void setNumRegistros() {
		try {
			this.file.seek(0);
			this.numRegistros = file.readInt();
		} catch (IOException e) {
			try {
				this.file.seek(0);
				this.file.writeInt(0);
			} catch (IOException e1) {
				System.out.println("N�o foi poss�vel ler, nem salvar o cabe�alho! \n" + e1.getMessage());
				System.exit(0);
			}			
		}
	}
	
	public void openFile() {
		File f = new File(this.filePath);
		try {
			if (!f.exists()) 
				this.numRegistros = 0;
			
			file = new RandomAccessFile(f, "rw");
		} catch (FileNotFoundException e) {
			System.out.println("N�o foi poss�vel encontrar o arquivo! \n" + e.getMessage());
			System.exit(0);
		}
		
		if (this.numRegistros == 0)
			this.setNumRegistros();
	}
	
	public void closeFile() {
		try {
			file.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar o arquivo! \n" + e.getMessage());
			System.exit(0);
		}
	}

	public abstract void appendData(E data);	
	public abstract E getData(int key);			
	public abstract boolean setData(E data, int key);			
	public abstract List<E> getAllData();		
}
