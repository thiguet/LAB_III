package dao;

import model.Occurrence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OccurrenceDAO extends RAFDAO<Occurrence> {
	protected final String INVALID_KEY = "A chave informada para acessar as ocorrêcias não é válida!";
	
	public OccurrenceDAO(String path) throws Exception {
		super(path);
		this.regsBytesSize = (Integer.SIZE / 8);
	}

	@Override
	public void appendData(Occurrence data) throws Exception {
		int pos = this.headerBytesSize + (this.regsAmount * this.regsBytesSize);

		this.file.seek(pos);
		this.file.writeInt(data.getFileId());
		this.file.seek(0);
		this.file.writeInt(++this.regsAmount);
	}

	@Override
	public Occurrence getData(int key) throws Exception {
		Occurrence occ = null;

		int pos = this.getFilePosition(key);
		
		try {
			for(int i = 0; i < this.regsAmount; i++) {
				file.seek(pos);
				occ = new Occurrence();
				occ.setFileId(file.readInt());
			}			
		} catch (IOException e) {
			System.out.println("Não foi possível ler as ocorrências! \n" + e.getMessage());
			System.exit(0);
		}

		return occ;
	}

	@Override
	public List<Occurrence> getAllData() {
		List<Occurrence> occ = new ArrayList<Occurrence>();
		Occurrence aux;
		int pos;
		
		try {
			for(int i = 0; i < this.regsAmount; i++) {
				pos = this.headerBytesSize + (i * this.regsBytesSize);
				file.seek(pos);
				aux = new Occurrence();
				aux.setFileId(file.readInt());
				occ.add(aux);
			}			
		} catch (IOException e) {
			System.out.println("Não foi possível ler as ocorrências! \n" + e.getMessage());
			System.exit(0);
		}

		return occ;
	}

	@Override
	public boolean setData(Occurrence data, int key) {
		int pos = this.headerBytesSize + (key * this.regsBytesSize);

		if(key >= this.regsAmount)
			return false;
		
		try {
			this.file.seek(pos);
			this.file.writeInt(data.getFileId());
			
		} catch (Exception e) {
			System.out.println("Não foi possível salvar a ocorrência! " + e.getMessage());
			System.exit(0);
			e.printStackTrace();
		}
		
		return true;
	}
}
