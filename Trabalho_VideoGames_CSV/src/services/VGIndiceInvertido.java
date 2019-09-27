package services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.HashTableDAO;
import dao.VideoGameDAO;
import dao.WordsListDAO;
import main.Config;
import model.HashObj;
import model.Occurrence;
import model.VideoGame;
import model.Word;
import util.StopWord;
import util.StringHash;

public class VGIndiceInvertido extends IndiceInvertido<VideoGame, Word, StringHash> {	
	public VGIndiceInvertido  (String vgFilePath, String wordsFilePath, String hashFilePath) {
		try {
			this.model = new VideoGameDAO(vgFilePath);
			this.word  = new WordsListDAO(wordsFilePath);
			this.hash  = new HashTableDAO(hashFilePath); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void genWordList() {
		this.saveWordsList(this.filterWords());
	} 
	
	private void saveWordsList(List<Word> wordsList) {
		this.word.openFile();
		
		wordsList.stream().forEach(
			word -> {
				this.word.appendData(word);
			}
		);	
		
		this.word.closeFile();
	}

	private List<Word> filterWords() {
		Set<String> strList = new HashSet<String>();
		List<Word> wordsList = new ArrayList<Word>();
		String name 		= null;
		Word wAux 			= null; 
		
		this.model.openFile();
			
		try {
			int max = this.model.getNumRegistros();
			
			for(int i = 0 ; i < max; i++) {
				name = this.model.getData(i).getName();				
				
				System.out.println(name);
				
				for(String str : name.split(" |\\/|\\-")) { // Split Video Games Names by spaces, bars or the minus symbol (-)
					
					if(!StopWord.isStopWord(str)) {  		 // Check if it is not a StopWord.
						str = this.cleanWord(str);
						if(this.isValid(str)) { 			 // Saves only if it's a valid word.
							String strAux = str;
							if(strList.contains(str)) {
								wAux = wordsList.stream()
												.filter( w -> w.getString().equals(strAux))
												.findFirst().get();

								wAux.getOccurrencies().add(new Occurrence(i));
								wAux.setString(str);
							} else {
								strList.add(str);	
								wAux = new Word();
								wAux.setOccurrencies(new ArrayList<Occurrence>());
								wAux.getOccurrencies().add(new Occurrence(i));
								wAux.setString(str);
								wordsList.add(wAux);
							}
						}
					}
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
		this.model.closeFile();

		return wordsList;
	}
	
	private String cleanWord(String str) {
		return str.replaceAll("(\\W|(\\s+\\d{1}\\s+)|(\\s+\\w{1}\\s+))", "").toLowerCase();
	}
		
	public boolean isValid(String str) {
		return ( str != null   && 
				!str.isEmpty() && 
				 str.length () > 1);	
	}
	
	@Override
	public void buildIndiceInvertido() {
		this.hashalizeWordsList();		
	}

	private void hashalizeWordsList() {
		this.model.openFile();
		this.word.openFile();
		
		List<Word> wordsList = this.word.getAllData();
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

	public void searchStr(String str) {
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
							VideoGame vgAux = this.model.getData(occ.getFileId());
							System.out.println(vgAux.getName());
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
}
