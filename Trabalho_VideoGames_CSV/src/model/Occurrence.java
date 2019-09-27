package model;

public class Occurrence {
	private int fileId;

	public Occurrence() {}
	
	public Occurrence(Integer i) {
		this.setFileId(i);
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
}
