package dao;

import java.util.ArrayList;
import java.util.List;

import main.Config;
import model.Occurrence;
import model.Word;

public class WordsListDAO extends RAFDAO<Word> {
	private static final int WORD_SIZE = 30;
	private RAFDAO<Occurrence> occDAO;
	
	public WordsListDAO(String path) throws Exception {
		super(path);
		this.tamRegistros = WordsListDAO.WORD_SIZE;
	}

	private void saveOccurrences(Word word) throws Exception {
		int size = 0;
		if(word.getOccurrencies() != null)
			size = word.getOccurrencies().size();
		
		this.occDAO.openFile();
		for(int j = 0; j < size; j++) {
			this.occDAO.appendData(word.getOccurrencies().get(j));
		}
		this.occDAO.closeFile();
	}
	
	private String getOccPath(String fileName) {
		return Config.FILES_PATH + "\\occ\\occ" + fileName + ".bin";
	}
	
	@Override
	public void appendData(Word word) {
		int pos = this.tamCabecalho + (this.numRegistros * this.tamRegistros);
		String occPath = null,
			   str = word.getString();

		try {
			this.file.seek(pos);
			this.file.writeUTF(str);
				
			occPath = this.getOccPath(str);
			this.occDAO = new OccurrenceDAO(occPath);
			
			this.saveOccurrences(word);
			
			file.seek(0);
			file.writeInt(++this.numRegistros);
		} catch (Exception e) {
			System.out.println("Não foi possível salvar a palavra! " + e.getMessage());
			System.exit(0);
		} 
	}

	@Override
	public Word getData(int key) {
		Word aux = null;
		
		if (key >= this.numRegistros)
			return null;
		
		int pos = this.tamCabecalho + (key * this.tamRegistros);
		
		try {
			aux = new Word();
			
			this.file.seek(pos);
			aux.setString(file.readUTF());
			
			this.occDAO = new OccurrenceDAO(this.getOccPath(aux.getString()));

			this.occDAO.openFile();
			aux.setOccurrencies(this.occDAO.getAllData());
			this.occDAO.closeFile();
		} catch (Exception e) {
			System.out.println("Não foi possível ler as palavras! \n" + e.getMessage());
			System.exit(0);
		}

		return aux;
	}

	@Override
	public List<Word> getAllData() {
		List<Word> wordsList = new ArrayList<Word>();
		Word aux;
		int pos;
		
		try {
			for(int i = 0; i < this.numRegistros - 1; i++) {
				pos = this.tamCabecalho + (i * this.tamRegistros);
				aux = new Word();
				pos += this.tamRegistros;
				file.seek(pos);
				aux.setString(file.readUTF());

				this.occDAO = new OccurrenceDAO(this.getOccPath(aux.getString()));
				
				this.occDAO.openFile();
				
				aux.setOccurrencies(this.occDAO.getAllData());
				wordsList.add(aux);
				
				this.occDAO.closeFile();
			}			
		} catch (Exception e) {
			System.out.println("Não foi possível ler as palavras! \n" + e.getMessage());
			System.exit(0);
		}

		return wordsList;
	}

	@Override
	public boolean setData(Word word, int key) {
		int pos = this.tamCabecalho + (key * this.tamRegistros);
		String occPath = null,
			   str = word.getString();
		
		if (key >= this.numRegistros) 
			return false;
		
		try {
			this.file.seek(pos);
			this.file.writeUTF(str);

			occPath = this.getOccPath(str);
			this.occDAO = new OccurrenceDAO(occPath);
			
			this.saveOccurrences(word);
		} catch (Exception e) {
			System.out.println("Não foi possível salvar a palavra! " + e.getMessage());
			System.exit(0);
		}		
		
		return true;
	}
}
