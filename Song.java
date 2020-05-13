package project;

public class Song {
	
	private String songName;
	private String path;
	private String songAlbum;
	
	public Song(String songName, String album, String path) {
		this.songName = songName;
		this.songAlbum = album;
		this.path = path;
		
	}
	
	public String getSongName() {
		return songName;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getAlbum() {
		return songAlbum;
	}
	
	@Override
	public String toString() {
		return songName + "\t" + songAlbum + "\t" + path;
	}

}
