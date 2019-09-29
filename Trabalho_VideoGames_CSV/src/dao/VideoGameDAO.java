package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.VideoGame;

public class VideoGameDAO extends RAFDAO<VideoGame> {
	private static final int NAME_MAX_SIZE = 120;
	private static final int PLATFORM_MAX_SIZE = 50;
	private static final int GENRE_MAX_SIZE = 60;
	private static final int PUBLISHER_MAX_SIZE = 80;

	public VideoGameDAO (String path) throws Exception {
		super(path);
		this.regsBytesSize = 
				(Integer.SIZE / 8) + // Rank 
				NAME_MAX_SIZE      + // Name     
				PLATFORM_MAX_SIZE  + // Platform
				(Integer.SIZE / 8) + // Year
				GENRE_MAX_SIZE     + // Genre
				PUBLISHER_MAX_SIZE + // Publisher
				Double.SIZE        + // NA Sales
				Double.SIZE        + // EU Sales
				Double.SIZE        + // JP Sales
				Double.SIZE        + // Other Sales
				Double.SIZE;         // Global Sales
	}

	public void appendData(VideoGame videoGame) {
		int pos = this.headerBytesSize + (this.regsAmount * this.regsBytesSize);

		try {
			int posName 		= pos + (Integer.SIZE / 8),
				posPlatform 	= posName + NAME_MAX_SIZE,
				posYear 		= posPlatform + PLATFORM_MAX_SIZE,
				posGenre 		= posYear + (Integer.SIZE / 8),
				posPublisher 	= posGenre + GENRE_MAX_SIZE,
				posNaSales 		= posPublisher + PUBLISHER_MAX_SIZE;
			
			file.seek(pos);
			file.writeInt(videoGame.getRank());
			file.writeUTF(videoGame.getName());			
			file.seek(posPlatform);
			file.writeUTF(videoGame.getPlatform());
			file.seek(posYear);
			file.writeInt(videoGame.getYear());
			file.writeUTF(videoGame.getGenre());
			file.seek(posPublisher);
			file.writeUTF(videoGame.getPublisher());
			file.seek(posNaSales);
			file.writeDouble(videoGame.getNaSales());
			file.writeDouble(videoGame.getEuSales());
			file.writeDouble(videoGame.getJpSales());
			file.writeDouble(videoGame.getOtherSales());
			file.writeDouble(videoGame.getGlobalSales());
						
			file.seek(0);
			this.regsAmount += 1;
			file.writeInt(this.regsAmount);
		} catch (IOException e) {
			System.out.println("Não foi possível salvar o vídeo game! " + e.getMessage());
			System.exit(0);
		}
	}
	
	public VideoGame getData(int key) {
		VideoGame aux = null;
		
		if (key >= this.regsAmount)
			return null;
		
		int pos = this.headerBytesSize + (key * this.regsBytesSize);
			
		try {
			aux = new VideoGame();
			
			int posPlatform  	= pos + (Integer.SIZE / 8) + NAME_MAX_SIZE,
				posYear 	 	= posPlatform + PLATFORM_MAX_SIZE,
				posGenre 	 	= posYear + (Integer.SIZE / 8),
				posPublisher 	= posGenre + GENRE_MAX_SIZE,
				posNaSales 	 	= posPublisher + PUBLISHER_MAX_SIZE;
			
			file.seek(pos);
			aux.setRank(file.readInt());
			aux.setName(file.readUTF());
			file.seek(posPlatform);
			aux.setPlatform(file.readUTF());
			file.seek(posYear);
			aux.setYear(file.readInt());
			aux.setGenre(file.readUTF());
			file.seek(posPublisher);
			aux.setPublisher(file.readUTF());
			file.seek(posNaSales);
			aux.setNaSales(file.readDouble());
			aux.setEuSales(file.readDouble());
			aux.setJpSales(file.readDouble());
			aux.setOtherSales(file.readDouble());
			aux.setGlobalSales(file.readDouble());
		} catch (IOException e) {
			System.out.println("Não foi possível ler os vídeo games! \n" + e.getMessage());
			System.exit(0);
		}

		return aux;
	}

	@Override
	public List<VideoGame> getAllData() {
		List<VideoGame> videoGames = new ArrayList<VideoGame>();
		VideoGame aux = null;
		
		int max = this.regsAmount;
		int pos,
			posPlatform,
			posYear,
			posGenre, 
			posPublisher,
			posNaSales;
			
		try {
			aux = new VideoGame();
			for(int i = 0;  i < max; i++) {
				pos 			= this.headerBytesSize + (i * this.regsBytesSize);
				posPlatform  	= pos + (Integer.SIZE / 8) + NAME_MAX_SIZE;
				posYear 	 	= posPlatform + PLATFORM_MAX_SIZE;
				posGenre 	 	= posYear + (Integer.SIZE / 8);
				posPublisher 	= posGenre + GENRE_MAX_SIZE;
				posNaSales 	 	= posPublisher + PUBLISHER_MAX_SIZE;
				
				file.seek(pos);
				aux.setRank(file.readInt());
				aux.setName(file.readUTF());
				file.seek(posPlatform);
				aux.setPlatform(file.readUTF());
				file.seek(posYear);
				aux.setYear(file.readInt());
				aux.setGenre(file.readUTF());
				file.seek(posPublisher);
				aux.setPublisher(file.readUTF());
				file.seek(posNaSales);
				aux.setNaSales(file.readDouble());
				aux.setEuSales(file.readDouble());
				aux.setJpSales(file.readDouble());
				aux.setOtherSales(file.readDouble());
				aux.setGlobalSales(file.readDouble());
				
				videoGames.add(aux);
			}
		} catch (IOException e) {
			System.out.println("Não foi possível ler os vídeo games! \n" + e.getMessage());
			System.exit(0);
		}

		return videoGames;
	}

	@Override
	public boolean setData(VideoGame data, int key) {
		return false;
	}
}
