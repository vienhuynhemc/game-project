package run;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import view.GameFrame;

public class StartGame {

	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		new GameFrame();
	}
}
