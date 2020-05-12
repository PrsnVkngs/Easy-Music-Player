package project;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PlayerMethods {
	
	Queue<Song> songQueue = new PriorityQueue<Song>();
	
	Iterator<Song> iterator;
	
	public PlayerMethods() {
		iterator = songQueue.iterator();
		
	}
	
	public void addSong(Song song) {
		songQueue.add(song);
	}
	
	public boolean removeSong(Song song) {
		try {
			songQueue.remove(song);
			return true;
		}
		catch(Exception e) {
			System.out.println("Couldn't remove song");
			return false;
		}
	}
	
	public Song getNextSong() {
		return iterator.next();
	}
	
}
