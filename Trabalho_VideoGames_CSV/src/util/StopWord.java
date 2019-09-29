package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.Config;

public class StopWord {
	private StopWord() {}
	
	private static final String PATH_TO_CSV = Config.FILES_PATH + "stopWords.txt";
	
	public static boolean isStopWord(String word) throws IOException {
		BufferedReader fileReader = new BufferedReader(new FileReader(StopWord.PATH_TO_CSV));
		String  row, 
				lowerWord;
		boolean isStopWord = false;	
		
		lowerWord = word.toLowerCase(); // Ignore case sensitive issues.
		
		while ((row = fileReader.readLine()) != null && !isStopWord) {
			String regExp = ",";
		    String[] stopWords = row.split(regExp);
		    for(String aux : stopWords) {
		    	if(lowerWord.equals(aux)) 
		    		isStopWord = true;
		    }
		}
		
		fileReader.close();		
		
		return isStopWord;
	}
}
