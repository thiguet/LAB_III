package controller;

import java.util.List;

import main.Config;
import model.VideoGame;
import model.Word;
import services.InvertedIndex;
import services.VideoGameInvertedIndex;

public class MainMenuController implements IMainMenuController {
	private InvertedIndex<Word, VideoGame> indInvertido;	
	
	public MainMenuController() throws Exception {
		this.indInvertido = new VideoGameInvertedIndex(
							Config.VIDEO_GAMES_FILE_PATH,
							Config.WORDS_LIST_FILE_PATH,
							Config.HASH_FILE_PATH);
	}
	
	public void buildInvertedIndex() throws Exception {
		this.indInvertido.build();
	}
	
	public String[][] searchBtn(String str) throws Exception {
		String[][] rows;
		this.indInvertido = 
				new VideoGameInvertedIndex(
						Config.VIDEO_GAMES_FILE_PATH,
						Config.WORDS_LIST_FILE_PATH,
						Config.HASH_FILE_PATH);		
		List<VideoGame> videoGames = indInvertido.search(str);
		
		rows = new String[videoGames.size()][12];

		for(int i = 0; i < videoGames.size(); i++ ) {
			VideoGame videoGame = videoGames.get(i);

			rows[i][0] = i + "";
			rows[i][1] = videoGame.getRank() + "";
			rows[i][2] = videoGame.getName();
			rows[i][3] = videoGame.getPlatform();
			rows[i][4] = videoGame.getYear() + "";
			rows[i][5] = videoGame.getGenre();
			rows[i][6] = videoGame.getPublisher();
			rows[i][7] = videoGame.getNaSales() + "";
			rows[i][8] = videoGame.getEuSales() + "";
			rows[i][9] = videoGame.getJpSales() + "";
			rows[i][10] = videoGame.getOtherSales() + "";
			rows[i][11] = videoGame.getGlobalSales() + "";
		}	
		
		return rows;				
	}
}
