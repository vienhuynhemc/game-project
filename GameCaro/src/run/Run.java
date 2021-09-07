package run;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

import caRo.GameWorld;

public class Run {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		int wait = 200;
		JButton[][] arr = new JButton[20][20];
		GameWorld test = new GameWorld(arr, wait);
		test.setVisible(true);
		
	}

}
