package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.RAFDAO;
import dao.VideoGameDAO;
import model.VideoGame;

public class VideoGameCSVService implements CSVService<VideoGame> {
	protected String pathToCSV;
	
	public VideoGameCSVService() {
		this.pathToCSV = ".\\data\\vgsales.csv";
	}
	
	public String getPathToCSV() {
		return pathToCSV;
	}
	
	public void setPathToCSV(String pathToCSV) {
		this.pathToCSV = pathToCSV;
	}
	
	@Override
	public List<VideoGame> getData() throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(this.pathToCSV));
		List<VideoGame> videoGames = new ArrayList<VideoGame>();
		VideoGame aux = null;
		String row;
		
		// Ignoring first line.
		csvReader.readLine();
		
		while ((row = csvReader.readLine()) != null) {
			// All comas that are not between quotes. 
			String regExp = "(,(?=(?:[^\"]*\"[^\"]*\")*[^\"]*\\Z))";
		    String[] line = row.split(regExp);
		    aux = new VideoGame();
		    aux.setVideoGame(line);
		    videoGames.add(aux);
	    }
		
		csvReader.close();		
		
		return videoGames;
	}

	@Override
	public void exportCSVToBin(String binFilePath) {
		try {
			RAFDAO<VideoGame> vgService = new VideoGameDAO(binFilePath);
			List<VideoGame> data = null;
		
			vgService.openFile();
			data = this.getData();
			
			for(VideoGame aux : data) {
				vgService.appendData(aux);
			}
			
			vgService.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}	
}
