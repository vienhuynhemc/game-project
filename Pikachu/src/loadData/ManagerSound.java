package loadData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ManagerSound {

	private static ManagerSound manager = new ManagerSound();

	private HashMap<String, Clip> listSound;

	private boolean isOn;

	private ManagerSound() {
		listSound = new HashMap<String, Clip>();

		try {
			init();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		isOn = true;

		soundNhacNen();
	}

	public static synchronized ManagerSound getInstance() {
		return manager;
	}

	public void stop(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip clip = listSound.get(fileName);
		clip.stop();
		clip.close();
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File("data/sound/" + fileName + ".wav"));
		AudioFormat f = audio.getFormat();
		DataLine.Info i = new DataLine.Info(Clip.class, f);
		Clip music = (Clip) AudioSystem.getLine(i);
		music.open(audio);
		listSound.put(fileName, music);
		listSound.get(fileName).addLineListener(new LineListener() {
			@Override
			public void update(LineEvent event) {
				if (event.getType() == LineEvent.Type.STOP) {
					listSound.get(fileName).stop();
					listSound.get(fileName).setFramePosition(0);
					listSound.get(fileName).start();
				}
			}
		});
		FloatControl gainControl = (FloatControl) listSound.get(fileName).getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) -10f);
	}

	public void khoiTaoHieuUng(String fileName)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File("data/sound/" + fileName + ".wav"));
		AudioFormat f = audio.getFormat();
		DataLine.Info i = new DataLine.Info(Clip.class, f);
		Clip music = (Clip) AudioSystem.getLine(i);
		music.open(audio);
		listSound.put(fileName, music);
	}

	public void stopHieuUng(String fileName)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Clip clip = listSound.get(fileName);
		clip.stop();
		clip.close();
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File("data/sound/" + fileName + ".wav"));
		AudioFormat f = audio.getFormat();
		DataLine.Info i = new DataLine.Info(Clip.class, f);
		Clip music = (Clip) AudioSystem.getLine(i);
		music.open(audio);
		listSound.put(fileName, music);
		listSound.get(fileName).addLineListener(new LineListener() {
			@Override
			public void update(LineEvent event) {
				if (event.getType() == LineEvent.Type.STOP) {
					listSound.get(fileName).stop();
					listSound.get(fileName).setFramePosition(0);
					listSound.get(fileName).start();
				}
			}
		});
	}

	public void soundNhacNen() {

		listSound.get("nhacNenMapleStory").addLineListener(new LineListener() {
			@Override
			public void update(LineEvent event) {
				if (event.getType() == LineEvent.Type.STOP) {
					listSound.get("nhacNenMapleStory").stop();
					listSound.get("nhacNenMapleStory").setFramePosition(0);
					listSound.get("nhacNenMapleStory").start();
				}
			}
		});
	}

	public void init() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		BufferedReader r = new BufferedReader(new FileReader(new File("data/sound/loadSound.txt")));
		String line = null;
		while (true) {
			line = r.readLine();
			if (line == null)
				break;
			String[] arr = line.split(" ");
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File("data/sound/" + arr[0]));
			AudioFormat f = audio.getFormat();
			DataLine.Info i = new DataLine.Info(Clip.class, f);
			Clip music = (Clip) AudioSystem.getLine(i);
			music.open(audio);
			listSound.put(arr[1], music);
		}
		r.close();
	}

	public HashMap<String, Clip> getListSound() {
		return listSound;
	}

	public void setListSound(HashMap<String, Clip> listSound) {
		this.listSound = listSound;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

}
