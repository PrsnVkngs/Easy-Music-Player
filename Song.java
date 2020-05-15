package project;

public class Song {
	
	private String songName;
	private String path;
	private String songAlbum;
	private AudioPlayer playerObject;
	private int songLength = 381;
	
	public Song(String songName, String album, String path) {
		this.songName = songName;
		this.songAlbum = album;
		this.path = path;
		playerObject = new AudioPlayer(path);
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
	
	public AudioPlayer getAudioPlayer() {
		return playerObject;
	}
	
	public int getSongLength() {
		return songLength;
	}
	
	

}
