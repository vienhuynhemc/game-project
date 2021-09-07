package sound;

import java.io.File;
import java.io.IOException;

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
import javax.swing.ImageIcon;

import caRo.GameWorld;

public class AllSound {
	public boolean nhacNen = false;
	public Clip clip;
	public Clip xDanh;
	public Clip oDanh;
	public Clip ding;
	public Clip musicEnd;
	public Clip pop;
	public Clip goPhim;
	public Clip musicKeo;
	public int js1 = 0;
	public int js2 = -8;
	private GameWorld swing;

	public AllSound(GameWorld swing) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		this.swing = swing;
		music();
		musicDing();
		musicPop();
		musicEndGame();
		musicGoPhim();
		musicKeo();
		musicODanh();
		musicXDanh();
	}

	public void music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File file;
		AudioInputStream audio;
		AudioFormat fomat;
		DataLine.Info info;
		file = new File("sound/sound.wav");
		audio = AudioSystem.getAudioInputStream(file);
		fomat = audio.getFormat();
		info = new DataLine.Info(Clip.class, fomat);
		setClip((Clip) AudioSystem.getLine(info));
		getClip().open(audio);

		getClip().addLineListener(new LineListener() {
			@Override
			public void update(LineEvent event) {
				if (event.getType() == LineEvent.Type.STOP) {
					try {
						music();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						e.printStackTrace();
					}
				}
			}
		});

		FloatControl gainControl = (FloatControl) getClip().getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) js1);

		if (!swing.panel3.isLoa()) {
			getClip().start();
		}
	}

	public void nhacNenGame() {
		if (swing.panel3.effor == false) {
			try {
				musicPop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		if (nhacNen == false) {
			swing.panel3.btNhacNen.setIcon(new ImageIcon("image/onSound.png"));
			nhacNen = true;
			swing.panel3.setLoa(true);
			swing.panel3.btNhacNen.setToolTipText("Nhấn để bậc nhạc nền");
			swing.getSound().getClip().stop();
		} else if (nhacNen == true) {
			swing.panel3.btNhacNen.setIcon(new ImageIcon("image/offSound.png"));
			nhacNen = false;
			swing.panel3.setLoa(false);
			try {
				swing.getSound().music();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
			swing.panel3.btNhacNen.setToolTipText("Nhấn để tắt nhạc nền");
		}
	}

	public void musicEndGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/endGame.wav"));
		AudioFormat f = audio.getFormat();
		DataLine.Info i = new DataLine.Info(Clip.class, f);
		musicEnd = (Clip) AudioSystem.getLine(i);
		musicEnd.open(audio);
		FloatControl gainControl = (FloatControl) musicEnd.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) js2);
		musicEnd.start();
	}

	public void musicDing() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File file = new File("sound/ding.wav");
		AudioInputStream audio = AudioSystem.getAudioInputStream(file);
		AudioFormat format = audio.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		ding = (Clip) AudioSystem.getLine(info);
		ding.open(audio);
		FloatControl gainControl = (FloatControl) ding.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) js2);
		ding.start();
	}

	public void musicKeo() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File file = new File("sound/keo.wav");
		AudioInputStream audio = AudioSystem.getAudioInputStream(file);
		AudioFormat format = audio.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		musicKeo = (Clip) AudioSystem.getLine(info);
		musicKeo.open(audio);
		FloatControl gainControl = (FloatControl) musicKeo.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) js2);
		musicKeo.start();
	}

	public void musicPop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File file = new File("sound/pop.wav");
		AudioInputStream audio = AudioSystem.getAudioInputStream(file);
		AudioFormat format = audio.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		pop = (Clip) AudioSystem.getLine(info);
		pop.open(audio);
		FloatControl gainControl = (FloatControl) pop.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) js2);
		pop.start();
	}

	public void musicGoPhim() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		File file = new File("sound/goPhim.wav");
		AudioInputStream audio = AudioSystem.getAudioInputStream(file);
		AudioFormat format = audio.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		goPhim = (Clip) AudioSystem.getLine(info);
		goPhim.open(audio);
		FloatControl gainControl = (FloatControl) goPhim.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) js2);
		goPhim.start();
	}

	public void musicODanh() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/oDanh.wav"));
		AudioFormat fm = audio.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, fm);
		oDanh = (Clip) AudioSystem.getLine(info);
		oDanh.open(audio);
		FloatControl gainControl = (FloatControl) oDanh.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue((float) js2);
		oDanh.start();
	}

	public void musicXDanh() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/xDanh.wav"));
		AudioFormat fomat = audio.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, fomat);
		xDanh = (Clip) AudioSystem.getLine(info);
		xDanh.open(audio);
		FloatControl change = (FloatControl) xDanh.getControl(FloatControl.Type.MASTER_GAIN);
		change.setValue((float) js2 - 2);
		xDanh.start();
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}
}
