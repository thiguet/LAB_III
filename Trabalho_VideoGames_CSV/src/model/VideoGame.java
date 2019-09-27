package model;

public class VideoGame {
	private int rank, year;
	private String name, platform, genre, publisher;
	private Double naSales, euSales, ipSales, jpSales, otherSales, globalSales;
	
	public VideoGame() {}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPlatform() {
		return platform;
	}
	
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public double getNaSales() {
		return naSales;
	}
	
	public void setNaSales(double naSales) {
		this.naSales = naSales;
	}
	
	public double getEuSales() {
		return euSales;
	}
	
	public void setEuSales(double euSales) {
		this.euSales = euSales;
	}
	
	public double getIpSales() {
		return ipSales;
	}
	
	public void setIpSales(double ipSales) {
		this.ipSales = ipSales;
	}
	
	public double getJpSales() {
		return jpSales;
	}
	
	public void setJpSales(double jpSales) {
		this.jpSales = jpSales;
	}
	
	public double getOtherSales() {
		return otherSales;
	}
	
	public void setOtherSales(double otherSales) {
		this.otherSales = otherSales;
	}
	
	public double getGlobalSales() {
		return globalSales;
	}
	
	public void setGlobalSales(double globalSales) {
		this.globalSales = globalSales;
	}
	
	public void setVideoGame(String[] strArr) {
		Integer rank = 0,
				year = 0;
		try {
			rank = Integer.parseInt(strArr[0]);
		} catch (NumberFormatException e) {}
		
		try {
			year = Integer.parseInt(strArr[3]);
		} catch (NumberFormatException e) {}
		
		this.setRank((int)rank);
		this.setName(strArr[1]);
		this.setPlatform(strArr[2]);
		this.setYear((int)year);
		this.setGenre(strArr[4]);
		this.setPublisher(strArr[5]);
		this.setNaSales(Double.parseDouble(strArr[6]));
		this.setEuSales(Double.parseDouble(strArr[7]));
		this.setJpSales(Double.parseDouble(strArr[8]));
		this.setOtherSales(Double.parseDouble(strArr[9]));
		this.setGlobalSales(Double.parseDouble(strArr[10]));
	}
}
