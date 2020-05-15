package project;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * 
 * @author oskar
 *
 */

public class AudioPlayer implements Runnable{
	
	private final int BUFFER_SIZE = 128000;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;
	//private String status;
	//private long currentFrame;
	private String songPath;
	private boolean paused = false;
	//private boolean isCurrentSong = false;
	Thread songThread;
	int songTime;
	
	
	public AudioPlayer(String fileName) {
		this.songPath = fileName;
		
		songThread = new Thread() {
			public void run() {
				playSoundH();
			}
		};
		
	}
	
	
	public void playSound() {
			if(songThread.isAlive()) {
				System.out.println("Thread is alive already.");
			}
			else {
				songThread.start();
			}
	}
	
	
	
	private void playSoundH() {
		
		String fileName = songPath;
		try {
			soundFile = new File(fileName);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			audioStream = AudioSystem.getAudioInputStream(soundFile);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		audioFormat = audioStream.getFormat();
		
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);
		}
		catch (LineUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		}
		catch (Exception e ) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		sourceLine.start();
		
		int songTime = 0;
		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		//sourceLine.stop();
		
		while(nBytesRead != -1) {
			try {
				this.paused = GUI.isPaused();
				if(!paused) {
					sourceLine.start();					
					nBytesRead = audioStream.read(abData, 0, abData.length);
				}
				else {
					sourceLine.stop();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				//@SupressWarnings("unused");
				int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
			}
		}
		sourceLine.drain();
		sourceLine.close();
	}
	
	public int getSongTime() {
		return songTime/100;
	}
	
	/**
	 * 
	 * @param p is true or false depending on whether or not you want to pause the song. True pauses the audio, while false resumes it.
	 */
	public void getIfPaused() {
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
//	
//	// Method to play the audio 
//		public void play() 
//		{ 
//			//start the clip 
//			sourceLine.start(); 
//			
//			status = "play"; 
//		} 
//		
//		// Method to pause the audio 
//		public void pause() 
//		{ 
//			if (status.equals("paused")) 
//			{ 
//				System.out.println("audio is already paused"); 
//				return; 
//			} 
//			this.currentFrame = 
//			this.sourceLine.getMicrosecondPosition(); 
//			sourceLine.stop(); 
//			status = "paused"; 
//		} 
//		
//		// Method to resume the audio 
//		public void resumeAudio() throws UnsupportedAudioFileException, 
//									IOException, LineUnavailableException 
//		{ 
//			if (status.equals("play")) 
//			{ 
//				System.out.println("Audio is already "+ 
//				"being played"); 
//				return; 
//			} 
//			sourceLine.close(); 
//			resetAudioStream(); 
//			((Clip) sourceLine).setMicrosecondPosition(currentFrame); 
//			this.play(); 
//		} 
//		
//		// Method to restart the audio 
//		public void restart() throws IOException, LineUnavailableException, 
//												UnsupportedAudioFileException 
//		{ 
//			sourceLine.stop(); 
//			sourceLine.close(); 
//			resetAudioStream(); 
//			currentFrame = 0L; 
//			((Clip) sourceLine).setMicrosecondPosition(0); 
//			this.play(); 
//		} 
//		
//		// Method to stop the audio 
//		public void stop() throws UnsupportedAudioFileException, 
//		IOException, LineUnavailableException 
//		{ 
//			currentFrame = 0L; 
//			sourceLine.stop(); 
//			sourceLine.close(); 
//		} 
//		
//		// Method to jump over a specific part 
//		public void jump(long c) throws UnsupportedAudioFileException, IOException, 
//															LineUnavailableException 
//		{ 
//			if (c > 0 && c < ( sourceLine).getMicrosecondPosition()) 
//			{ 
//				sourceLine.stop(); 
//				sourceLine.close(); 
//				resetAudioStream(); 
//				currentFrame = c; 
//				((Clip) sourceLine).setMicrosecondPosition(c); 
//				this.play(); 
//			} 
//		} 
//		
//		// Method to reset audio stream 
//		public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
//												LineUnavailableException 
//		{ 
//			audioStream = AudioSystem.getAudioInputStream( 
//			new File(songPath).getAbsoluteFile()); 
//			sourceLine.open(audioFormat); 
//			((Clip) sourceLine).loop(Clip.LOOP_CONTINUOUSLY); 
//		} 
	
}
