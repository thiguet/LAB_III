package services;

import java.util.List;

public interface InvertedIndex<WORD, MODEL> {
	public abstract void build() throws Exception;
	public abstract List<WORD> generateWordsList() throws Exception;
	public abstract void saveWordsList (List<WORD> wordsList) throws Exception;
	public abstract void buildHashTable(List<WORD> wordsList) throws Exception;
	public abstract List<MODEL> search(String searchStr) throws Exception;
}
