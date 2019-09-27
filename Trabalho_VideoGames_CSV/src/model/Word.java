package model;

import java.util.List;

public class Word {
	private String str;
	private List<Occurrence> occurrencies;

	public Word() {}
	
	public Word(String str) {
		if(!str.isEmpty())
			this.setString(str);
	}	
	
	public String getString() {
		return str;
	}

	public void setString(String word) {
		this.str = this.cleanWord(word);
	}

	public List<Occurrence> getOccurrencies() {
		return occurrencies;
	}

	public void setOccurrencies(List<Occurrence> occurrencies) {
		this.occurrencies = occurrencies;
	}
	
	private String cleanWord(String str) {
		return str.replaceAll("(\\W|(\\s+\\d{1}\\s+)|(\\s+\\w{1}\\s+))", "").toLowerCase();
	}
	
	public boolean isContainedIn(String str) {
		boolean isContainedIn = false;
		String aux[] = str.split(" "),
			   cleanStr;
				
		for(String s : aux) {
			cleanStr = this.cleanWord(s);
			if(this.str.equals(cleanStr)) {
				isContainedIn = true;
			}
		}
		
		return isContainedIn;
	}
}
