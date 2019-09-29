package main;

public class Config {
	public static final String FILES_PATH 			 = ".\\data\\";
	public static final String VIDEO_GAMES_FILE_PATH = Config.FILES_PATH + "vg.bin";
	public static final String WORDS_LIST_FILE_PATH  = Config.FILES_PATH + "words.bin";
	public static final String HASH_FILE_PATH 		 = Config.FILES_PATH + "hash.bin";
	
	public static final int HASH_PREFERRED_SIZE   	 = 16573;
	
	private Config() {}
}
