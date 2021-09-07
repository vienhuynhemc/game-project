package appToTinh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JFrame {
	private JPanel mainPanel;
	private JLabel lb1, lb2, lb3, lb4, lb5;
	private JButton bt1;
	private Timer t1, t2, t3, t4, t5;

	public Main() {

		add(mainPanel = createMainPanel());

		t1 = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lb2.getY() == 100)
					t1.stop();
				else
					lb2.setBounds(100, lb2.getY() + 1, 500, 20);
			}
		});
		t1.start();

		t2 = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lb3.getX() == 120)
					t2.stop();
				else
					lb3.setBounds(lb3.getX() + 4, 150, 500, 20);
			}
		});
		t2.start();
		t3 = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bt1.getY() == 220)
					t3.stop();
				else
					bt1.setBounds(340, bt1.getY() - 1, 45, 30);
			}
		});
		t3.start();
		t4 = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lb4.getX() == 362)
					t4.stop();
				else
					lb4.setBounds(lb4.getX() + 3, 185, 20, 20);
			}
		});
		t4.start();
		t5 = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lb5.getX() == 360)
					t5.stop();
				else
					lb5.setBounds(lb5.getX() - 1, 195, 20, 20);
			}
		});
		t5.start();

		// music
		try {
			music();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		// HIển thị jfarme
		setTitle("Hey you! A gorgeous girl^^");
		setSize(510, 370);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int hoi = JOptionPane.showConfirmDialog(null, "Tấm lòng của anh đấy :(( , em nỡ ?",
						"Hix :( tell me why?", JOptionPane.YES_NO_OPTION);
				if (hoi == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Hì hì sao em tử chối tấm lòng anh được <3");
				} else {
					JOptionPane.showMessageDialog(null, "Hì hì em quyết định của em sáng suốt đấy ^^");
				}
			}
		});

	}

	private JPanel createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));

		// backGround
		lb1 = new JLabel();
		lb1.setIcon(new ImageIcon("image/hinh1.jpg"));
		mainPanel.add(lb1);

		// create label
		lb2 = new JLabel("ANH CÓ ĐIỀU NÀY MUỐN NÓI VỚI EM !!! ");
		lb3 = new JLabel("EM NHẤN NÚT MỞ GIÙM ANH VỚI NHÉ !!");

		// setlabel
		lb2.setBounds(100, -20, 500, 20);
		lb3.setBounds(-360, 150, 500, 20);
		lb2.setForeground((Color.LIGHT_GRAY));
		lb2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lb3.setForeground(Color.LIGHT_GRAY);
		lb3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		// action
		bt1 = new JButton(new ImageIcon("image/bt1.png"));
		bt1.setContentAreaFilled(false);
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ChuongTrinhChinh();
				setVisible(false);
			}
		});

		// button
		bt1.setBounds(340, 340, 45, 30);
		bt1.setToolTipText("Điều bí mật ^^");
		lb4 = new JLabel("|");
		lb5 = new JLabel("V");
		lb4.setForeground(Color.yellow);
		lb5.setForeground(Color.yellow);
		lb4.setBounds(-7, 185, 20, 20);
		lb5.setBounds(500, 195, 20, 20);
		lb1.add(bt1);
		lb1.add(lb2);
		lb1.add(lb3);
		lb1.add(lb4);
		lb1.add(lb5);

		return mainPanel;
	}

	public void music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/ido.wav"));
		AudioFormat fm = audio.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, fm);
		Clip clip = (Clip) AudioSystem.getLine(info);
		clip.open(audio);
		clip.start();
		clip.addLineListener(new LineListener() {
			@Override
			public void update(LineEvent event) {
				if (event.getType().equals(LineEvent.Type.STOP)) {
					try {
						music();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		new Main();
	}

}