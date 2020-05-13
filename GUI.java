package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class GUI {

	// protected static final Component Song = null;
	private JFrame frame;
	// JLabel[] songLabels;
	Map<String, Song> songsInQueue = new HashMap<String, Song>();
	private JTable songTable;
	private static boolean isPaused = false;
	
	
	public static boolean isSongPlaying() {
		return isPaused;
	}

	/**
	 * Launch the application.
	 */
	public void createGUI() {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		frame.setTitle("Easy Music Player");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PlayerQueue songQueue = new PlayerQueue();
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel songLabel = new JLabel("Current Song:");
		songLabel.setBounds(26, 152, 82, 14);
		panel.add(songLabel);

		JLabel songIndicator = new JLabel("Song Name");
		songIndicator.setBounds(114, 152, 292, 14);
		panel.add(songIndicator);

		JProgressBar songProgress = new JProgressBar();
		songProgress.setBounds(26, 236, 380, 14);
		panel.add(songProgress);

		JLabel progressLabel = new JLabel("Song Progress");
		progressLabel.setBounds(26, 221, 101, 14);
		panel.add(progressLabel);

		JButton previousSongB = new JButton("Previous Song");
		previousSongB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				decreaseProgress(songProgress);
			}
		});

		JButton nextSongB = new JButton("Next Song");
		nextSongB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				increaseProgress(songProgress);
			}
		});
		nextSongB.setBounds(305, 177, 101, 23);
		panel.add(nextSongB);

		previousSongB.setBounds(26, 177, 114, 23);
		panel.add(previousSongB);

		JLabel songStatus = new JLabel("Song Status:");
		songStatus.setBounds(173, 211, 147, 14);
		panel.add(songStatus);

		JButton playPauseB = new JButton("Play/Pause");
		playPauseB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (songStatus.getText().equals("Song Status: Playing")) {
					songStatus.setText("Song Status: Paused");
					isPaused = true;
				} else if (songStatus.getText().equals("Song Status: Paused")) {
					songStatus.setText("Song Status: Playing");
					isPaused = false;
				} else {
					songStatus.setText("Song Status: Playing");
				}
			}
		});
		playPauseB.setBounds(173, 177, 101, 23);
		panel.add(playPauseB);

		songTable = new JTable();
		songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel songTableModel = new DefaultTableModel(
				new Object[][] {
					{"Song Title", "Album", "Path"},
				},
				new String[] {
					"Song Title", "Album", "Path"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		songTable.setModel(songTableModel);
		songTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		songTable.setBounds(26, 45, 380, 96);
		panel.add(songTable);

		JButton addSong = new JButton("Add Song");
		addSong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String songName = JOptionPane.showInputDialog(frame, "What is the song's name?", null);
				String songAlbum = JOptionPane.showInputDialog(frame, "What is the song Album?", null);
				String songPath = JOptionPane.showInputDialog(frame, "Enter the path to the song WAV file:", null);

				songsInQueue.put(songName, new Song(songName, songAlbum, songPath));
				
				songTableModel.addRow(new String[] {songName, songAlbum, songPath});
				
				songQueue.addSong(new Song(songName, songAlbum, songPath));
				
			}
		});
		addSong.setBounds(26, 11, 89, 23);
		panel.add(addSong);

	}
	
	
	
	public JLabel createNewSongLabel(String name, String album, String path) {
		return new JLabel(name + "\t" + album + "\t" + path);
	}

	public void decreaseProgress(JProgressBar prog) {
		if (prog.getValue() <= 0) {
			prog.setValue(0);
		} else {
			prog.setValue(prog.getValue() - 1);
		}
	}

	public void increaseProgress(JProgressBar prog) {
		if (prog.getValue() >= 100) {
			prog.setValue(100);
		} else {
			prog.setValue(prog.getValue() + 1);
		}
	}
}
