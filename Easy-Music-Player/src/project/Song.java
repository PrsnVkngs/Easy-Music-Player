package project;

public class Song {
	
	private String songName;
	private String path;
	private Integer songLength;
	
	public Song(String path) {
		this.path = path;
	}
	
	public String getSongName() {
		return songName;
	}
	
	public String getPath() {
		return path;
	}
	
	public Integer getSongLength() {
		return songLength;
	}

}
