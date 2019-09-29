package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.HashTableDAO;
import dao.RAFDAO;
import dao.VideoGameDAO;
import dao.WordsListDAO;
import main.Config;
import model.HashObj;
import model.Occurrence;
import model.VideoGame;
import model.Word;
import util.StopWord;
import util.StringHash;

public class VideoGameInvertedIndex implements InvertedIndex<Word, VideoGame> {
	private final String SPLIT_REGEX = " |\\/|\\-";
	private CSVService<VideoGame> readCSVService;
	
	protected RAFDAO<VideoGame> model;
	protected RAFDAO<Word> word;
	protected RAFDAO<StringHash> hash;
	
	public VideoGameInvertedIndex  (String vgFilePath, String wordsFilePath, String hashFilePath) throws Exception {
		this.readCSVService = new VideoGameCSVService();
		this.model = new VideoGameDAO(vgFilePath);
		this.word  = new WordsListDAO(wordsFilePath);
		this.hash  = new HashTableDAO(hashFilePath); 
	}

	@Override
	public void build() {
		try {
			this.model.removeFile();
			this.word.removeFile();
			this.hash.removeFile();
			
			this.readCSVService.exportCSVToBin(Config.FILES_PATH + "\\vg.bin");
		
			List<Word> wordsList = this.generateWordsList();
			this.saveWordsList(wordsList);
			this.buildHashTable(wordsList);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public List<Word> generateWordsList() {
		Set<String> strSet   = new HashSet<String>();
		List<Word> wordsList = new ArrayList<Word>();
		String strAux		 = null;
		
		this.model.openFile();
			
		try {
			int max = this.model.getRegsAmout();
			
			for(int fileId = 0 ; fileId < max; fileId++) {
				strAux = this.model.getData(fileId).getName();	
				List<String> validStrings = this.getValidWordsFromString(strAux);
				
				int validStringsLength = validStrings.size();
				
				for(int j = 0; j < validStringsLength; j++) {
					String str = validStrings.get(j);
					Word wAux;
					
					if(strSet.contains(str)) {
						wAux = this.getWordFromWordList(wordsList, str);
					} else {
						strSet.add(str);	
						wAux = this.getNewWord(str);
						wordsList.add(wAux);
					}
					
					wAux.getOccurrencies().add(new Occurrence(fileId));
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
		this.model.closeFile();

		return wordsList;	
	}
	
	private List<String> getValidWordsFromString(String analyzedString) throws IOException {
		List<String> stringsList = new ArrayList<String>();

		for(String aux : analyzedString.split(this.SPLIT_REGEX)) {
			if(!StopWord.isStopWord(aux)) {  
				aux = this.cleanWord(aux);
				if(this.isValid(aux)) { 
					stringsList.add(aux);
				}
			}
		}		
		
		return stringsList;
	}
	
	private Word getWordFromWordList(List<Word> wordsList, String str) {
		return  wordsList.stream()
					 	 .filter( w -> w.getString().equals(str))
					 	 .findFirst().get();		
	}
	
	private Word getNewWord(String str) {
		Word wAux = new Word();
		
		wAux.setOccurrencies(new ArrayList<Occurrence>());
		wAux.setString(str);
		
		return wAux; 
	}
	
	private String cleanWord(String str) {
		return str.replaceAll("(\\W|(\\s+\\d{1}\\s+)|(\\s+\\w{1}\\s+))", "").toLowerCase();
	}
		
	public boolean isValid(String str) {
		return ( str != null   && 
				!str.isEmpty() && 
				 str.length () > 1);	
	}

	public void searchStr(String str) throws Exception {
		this.hash.openFile();
		this.model.openFile();
		this.word.openFile();
		
		StringHash strHash = this.hash.getData(0);
		HashObj hashObj = new HashObj();
		hashObj.setStr(this.cleanWord(str));
		try {
			hashObj = strHash.find(hashObj);
			if(hashObj != null) {
				Word aux = this.word.getData(hashObj.getFileId());
				if(aux != null)
					aux .getOccurrencies()
						.stream()
						.forEach( occ -> {
							try {
								VideoGame vgAux = this.model.getData(occ.getFileId());
								System.out.println(vgAux.getName());
							} catch (Exception e) {
								e.printStackTrace();
							}
	  					});
			} else {
				System.out.println("O nome informado não foi encontrado na lista!");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.hash.closeFile();
		this.model.closeFile();
		this.word.closeFile();
	}

	@Override
	public void saveWordsList(List<Word> wordsList) {
		this.word.openFile();
		wordsList.stream().forEach( word -> {
			try {
				this.word.appendData(word);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});	
		this.word.closeFile();
	}

	@Override
	public void buildHashTable(List<Word> wordsList) throws Exception {
		this.model.openFile();
		this.word.openFile();
		
		StringHash hashTable = new StringHash (Config.HASH_PREFERRED_SIZE);
		Word wAux = null;

		for(int i = 0; i < wordsList.size(); i++ ) {
			try {
				wAux = wordsList.get(i);
				
				HashObj aux = new HashObj();
				aux.setStr(wAux.getString()); 
				aux.setFileId(i + 1); 

				hashTable.insert(aux);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.word.closeFile();
		this.model.closeFile();
		
		this.hash.appendData(hashTable);
	}

	@Override
	public List<VideoGame> search(String searchStr) throws Exception {	
		List<VideoGame> videoGames = new ArrayList<VideoGame>();
		
		this.hash.openFile();
		this.model.openFile();
		this.word.openFile();
		
		StringHash strHash = this.hash.getData(0);
		HashObj hashObj = new HashObj();
		hashObj.setStr(this.cleanWord(searchStr));
		try {
			hashObj = strHash.find(hashObj);
			if(hashObj != null) {
				Word aux = this.word.getData(hashObj.getFileId() - 1);
				if(aux != null)
					aux .getOccurrencies()
						.stream()
						.forEach( occ -> {
							try {
								videoGames.add(this.model.getData(occ.getFileId()));
							} catch (Exception e) {
								e.printStackTrace();
							}
	  					});
			} else {
				System.out.println("O nome informado não foi encontrado na lista!");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.hash.closeFile();
		this.model.closeFile();
		this.word.closeFile();
		
		return videoGames;
	}
}
