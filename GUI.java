package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JList;

public class GUI {

	private JFrame frame;
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public void createGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					frame.setTitle("Easy Music Player");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);		
		
		JButton playPauseB = new JButton("Play/Pause");
		playPauseB.setBounds(173, 187, 101, 23);
		panel.add(playPauseB);
		
		JButton nextSongB = new JButton("Next Song");
		nextSongB.setBounds(305, 187, 101, 23);
		panel.add(nextSongB);
		
		JLabel songLabel = new JLabel("Current Song:");
		songLabel.setBounds(26, 162, 82, 14);
		panel.add(songLabel);
		
		JLabel songIndicator = new JLabel("Song Name");
		songIndicator.setBounds(118, 162, 292, 14);
		panel.add(songIndicator);
		
		JProgressBar songProgress = new JProgressBar();
		songProgress.setBounds(26, 236, 380, 14);
		panel.add(songProgress);
		
		JLabel progressLabel = new JLabel("Song Progress");
		progressLabel.setBounds(26, 221, 101, 14);
		panel.add(progressLabel);
		
		JButton previousSongB = new JButton("Previous Song");
		previousSongB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				decreaseProgress(songProgress);
			}
		});
		previousSongB.setBounds(26, 187, 114, 23);
		panel.add(previousSongB);
		
		JButton addSong = new JButton("Add Song");
		addSong.setBounds(19, 11, 89, 23);
		panel.add(addSong);
		
		JList<Song> songList = new JList<Song>();
		songList.setBounds(26, 45, 380, 104);
		panel.add(songList);
		
		JLabel songStatus = new JLabel("Song Status:");
		songStatus.setBounds(173, 211, 101, 14);
		panel.add(songStatus);
		

	}
	public void decreaseProgress(JProgressBar prog) {
		if(prog.getValue()<=0) {
			prog.setValue(0);
		}
		else {
			prog.setValue(prog.getValue() - 1);
		}
	}
}
