package dao;

import model.Occurrence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OccurrenceDAO extends RAFDAO<Occurrence> {

	public OccurrenceDAO(String path) throws Exception {
		super(path);
		this.tamRegistros = (Integer.SIZE / 8);
	}

	@Override
	public void appendData(Occurrence data) {
		int pos = this.tamCabecalho + (this.numRegistros * this.tamRegistros);

		try {
			this.file.seek(pos);
			this.file.writeInt(data.getFileId());
			this.file.seek(0);
			this.file.writeInt(++this.numRegistros);
		} catch (Exception e) {
			System.out.println("Não foi possível salvar as ocorrências! " + e.getMessage());
			System.exit(0);
			e.printStackTrace();
		}
	}

	@Override
	public Occurrence getData(int key) {
		Occurrence occ = null;
		if (key >= this.numRegistros) 
			return null;
		
		int pos = this.tamCabecalho + (key * this.tamRegistros);
		
		try {
			for(int i = 0; i < this.numRegistros; i++) {
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
			for(int i = 0; i < this.numRegistros; i++) {
				pos = this.tamCabecalho + (i * this.tamRegistros);
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
		int pos = this.tamCabecalho + (key * this.tamRegistros);

		if(key >= this.numRegistros)
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
