package dao;

import java.util.ArrayList;
import java.util.List;

import main.Config;
import model.Occurrence;
import model.Word;

public class WordsListDAO extends RAFDAO<Word> {
	private static final int WORD_MAX_SIZE = 30;
	private RAFDAO<Occurrence> occDAO;
	
	public WordsListDAO(String path) throws Exception {
		super(path);
		this.regsBytesSize = WordsListDAO.WORD_MAX_SIZE;
	}

	private void saveWordOccurrences(Word word) throws Exception {
		int occSize = 0;

		occSize = word.getOccurrencies().size();
		
		this.occDAO.openFile();
		for(int j = 0; j < occSize; j++) {
			this.occDAO.appendData(word.getOccurrencies().get(j));
		}
		this.occDAO.closeFile();
	}
	
	private String getOccPath(String fileName) {
		return Config.FILES_PATH + 
				"\\occ\\occ_" 	 + 
				fileName 		 + 
				".bin";
	}

	
	@Override
	public void appendData(Word word) throws Exception {
		int pos = this.getLastFilePosition();
		
		String str = word.getString(),
			   occFilePath = this.getOccPath(str);

		this.file.seek(pos);
		this.file.writeUTF(str);
			
		this.occDAO = new OccurrenceDAO(occFilePath);
		this.saveWordOccurrences(word);
		
		file.seek(0);
		file.writeInt(++this.regsAmount);
	}

	@Override
	public Word getData(int key) throws Exception {
		Word aux = null;
		
		if (key >= this.regsAmount)
			return null;
		
		int pos = this.headerBytesSize + (key * this.regsBytesSize);
		
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
			for(int i = 0; i < this.regsAmount - 1; i++) {
				pos = this.headerBytesSize + (i * this.regsBytesSize);
				aux = new Word();
				pos += this.regsBytesSize;
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
		int pos = this.headerBytesSize + (key * this.regsBytesSize);
		String occPath = null,
			   str = word.getString();
		
		if (key >= this.regsAmount) 
			return false;
		
		try {
			this.file.seek(pos);
			this.file.writeUTF(str);

			occPath = this.getOccPath(str);
			this.occDAO = new OccurrenceDAO(occPath);
			
			this.saveWordOccurrences(word);
		} catch (Exception e) {
			System.out.println("Não foi possível salvar a palavra! " + e.getMessage());
			System.exit(0);
		}		
		
		return true;
	}
}
