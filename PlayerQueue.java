package project;

import java.util.Iterator;
import java.util.LinkedList;

public class PlayerQueue {
	
	//PriorityQueue<Song> songQueue = new PriorityQueue<Song>();
	LinkedList<Song> songList = new LinkedList<Song>();
	
	Iterator<Song> iterator;
	
	int currentSongIndex;
	
	public PlayerQueue() {
		this.iterator = songList.iterator();
		currentSongIndex = 0;
	}
	
	public void addSong(Song song) {
		songList.add(song);
	}
	
	public boolean removeSong(Song song) {
		try {
			songList.remove(song);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Couldn't remove song");
			return false;
		}
	}
	
	public Song getNextSong() {
		if(iterator.hasNext()) {
			currentSongIndex++;
			return iterator.next();
		}
		else {
			System.out.println("There is no other song in Queue.");
			return null;
		}
	}
	public Song getLastSong() {
		Iterator<Song> newiterator = songList.iterator();
		for(int z = 0; z < currentSongIndex-1; z++) {
			newiterator.next();
		}
		currentSongIndex--;
		return newiterator.next();		
	}
	
	public Song getCurrentSong() {
		return songList.get(currentSongIndex);
	}
	
}
