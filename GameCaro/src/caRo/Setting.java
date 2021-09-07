package caRo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Setting extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSlider jslider1, jslider2, jsclider3, jsclider4;
	private JPanel mainPanel, p1, p2, p3, p4, p5, p6;
	private JLabel lbMusic, lbSoundEffor, lbThinkingTime, lbSize;
	private JButton btOk;
	private int size;
	private int thinkingTime;
	private int saveSize;
	private int saveThinkingTime;

	private GameWorld swing;

	public Setting(GameWorld swing) {
		this.swing = swing;

		taoSwing();

		actionWindown();

		size = swing.panel3.size;
		thinkingTime = swing.panel3.thinkingTime;

		action();

		setDisplay();

	}

	public void setDisplay() {
		setTitle("Cài đặt");
		setSize(650, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void actionWindown() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (swing.panel3.effor == false) {
					try {
						swing.getSound().musicPop();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
						ee.printStackTrace();
					}
				}
				if (swing.panel3.statrr == true) {
					int count = 0;
					for (int i = 0; i < swing.arr.length; i++) {
						for (int j = 0; j < swing.arr[i].length; j++) {
							if (swing.arr[i][j] != 0)
								count++;
						}
					}
					if (count != 0) {
						swing.panel3.t.start();
					}
				}
			}
		});
	}

	public void creatJslider1() {
		jslider1 = new JSlider(JSlider.VERTICAL, -14, 6, swing.getSound().js1);
		jslider1.setMajorTickSpacing(2);
		jslider1.setMinorTickSpacing(1);
		jslider1.setPaintTicks(true);
		jslider1.setPaintTrack(true);
		jslider1.setSnapToTicks(true);
		jslider1.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		jslider1.setPaintLabels(true);
		jslider1.setPreferredSize(new Dimension(80, 350));

		jslider1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (swing.panel3.effor == false) {
					try {
						swing.getSound().musicKeo();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
				float a = jslider1.getValue();
				FloatControl gainControl = (FloatControl) swing.getSound().clip
						.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(a);
				swing.getSound().js1 = (int) a;
				jslider1.setValue((int) a);
			}
		});

		p3.add(jslider1);
	}

	public void creatJslider2() {
		jslider2 = new JSlider(JSlider.VERTICAL, -30, 6, swing.getSound().js2);
		jslider2.setMajorTickSpacing(4);
		jslider2.setMinorTickSpacing(2);
		jslider2.setPaintTicks(true);
		jslider2.setPaintTrack(true);
		jslider2.setSnapToTicks(true);
		jslider2.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		jslider2.setPaintLabels(true);
		jslider2.setPreferredSize(new Dimension(80, 350));

		jslider2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (swing.panel3.effor == false) {
					try {
						swing.getSound().musicKeo();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
				float a = jslider2.getValue();
				FloatControl gainControl = (FloatControl) swing.getSound().oDanh
						.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(a);
				FloatControl gainControll = (FloatControl) swing.getSound().xDanh
						.getControl(FloatControl.Type.MASTER_GAIN);
				gainControll.setValue(a - 2);
				FloatControl gainControlll = (FloatControl) swing.getSound().ding
						.getControl(FloatControl.Type.MASTER_GAIN);
				gainControlll.setValue(a);
				FloatControl gainControllll = (FloatControl) swing.getSound().pop
						.getControl(FloatControl.Type.MASTER_GAIN);
				gainControllll.setValue(a);
				FloatControl gainControlllll = (FloatControl) swing.getSound().musicKeo
						.getControl(FloatControl.Type.MASTER_GAIN);
				gainControlllll.setValue(a);
				FloatControl gainControllllll = (FloatControl) swing.getSound().musicEnd
						.getControl(FloatControl.Type.MASTER_GAIN);
				gainControllllll.setValue(a);
				FloatControl gainControlllllll = (FloatControl) swing.getSound().goPhim
						.getControl(FloatControl.Type.MASTER_GAIN);
				gainControlllllll.setValue(a);
				swing.getSound().js2 = (int) a;
				jslider2.setValue((int) a);
			}
		});

		p3.add(jslider2);
	}

	public void creatJslider3() {
		p5.add(jsclider3 = new JSlider(SwingConstants.VERTICAL, 5, 105, (swing.panel3.thinkingTime / 10)));

		jsclider3.setMinorTickSpacing(5);
		jsclider3.setMajorTickSpacing(10);
		jsclider3.setPaintTicks(true);
		jsclider3.setPaintTrack(true);
		jsclider3.setSnapToTicks(true);
		jsclider3.setPreferredSize(new Dimension(100, 350));
		jsclider3.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		jsclider3.setPaintLabels(true);

		jsclider3.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (swing.panel3.effor == false) {
					try {
						swing.getSound().musicKeo();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
				int a = jsclider3.getValue();
				if (a == saveSize) {
					btOk.setEnabled(false);
				} else {
					btOk.setEnabled(true);
					int b = a * 10;
					thinkingTime = b;
				}
			}
		});
	}

	public void creatJslider4() {
		p5.add(jsclider4 = new JSlider(SwingConstants.VERTICAL, 20, 100, swing.panel3.size));

		jsclider4.setMinorTickSpacing(5);
		jsclider4.setMajorTickSpacing(10);
		jsclider4.setPaintTicks(true);
		jsclider4.setPaintTrack(true);
		jsclider4.setSnapToTicks(true);
		jsclider4.setPreferredSize(new Dimension(100, 350));
		jsclider4.setFont(new Font("Times New Roman", Font.ITALIC, 10));
		jsclider4.setPaintLabels(true);

		jsclider4.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (swing.panel3.effor == false) {
					try {
						swing.getSound().musicKeo();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
				int a = jsclider4.getValue();
				if (a == saveSize) {
					btOk.setEnabled(false);
				} else {
					btOk.setEnabled(true);
					size = a;
				}
			}
		});

	}

	public void p1() {
		mainPanel.add(p1 = new JPanel());
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.add(p3 = new JPanel());
		p1.add(p4 = new JPanel());
		p1.setBorder(new TitledBorder("Sound"));
		p4.add(lbMusic = new JLabel());
		p4.add(new JLabel("               "));
		p4.add(lbSoundEffor = new JLabel());
		lbMusic.setIcon(new ImageIcon("image/lbLoa1.png"));
		lbSoundEffor.setIcon(new ImageIcon("image/lbLoa2.png"));
	}

	public void p2() {
		mainPanel.add(p2 = new JPanel());
		p2.setBorder(new TitledBorder("Setting Game"));
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.add(p5 = new JPanel());
		p2.add(p6 = new JPanel());
		p6.add(new JLabel("       "));
		p6.add(lbThinkingTime = new JLabel());
		lbThinkingTime.setIcon(new ImageIcon("image/thinkingtime.png"));
		p6.add(lbSize = new JLabel());
		lbSize.setIcon(new ImageIcon("image/size.png"));
		p6.add(btOk = new JButton());
		btOk.setContentAreaFilled(false);
		btOk.setEnabled(false);
		btOk.setIcon(new ImageIcon("image/save.png"));
		btOk.setBorderPainted(false);
		btOk.setContentAreaFilled(false);
	}

	private void taoSwing() {

		add(mainPanel = new JPanel());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		p1();
		p2();
		creatJslider1();
		creatJslider2();
		creatJslider3();
		creatJslider4();

		getData();

		setBackGroundPanel();

		setBackGroundSlider();
	}

	public void getData() {
		saveSize = jsclider4.getValue();
		setSaveThinkingTime(jsclider3.getValue());
	}

	public void setBackGround() {
		p3.setBackground(Color.white);
		p4.setBackground(Color.white);
		p5.setBackground(Color.white);
		p6.setBackground(Color.white);
	}

	public void action() {
		btOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (swing.panel3.effor == false) {
					try {
						swing.getSound().musicDing();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
						e2.printStackTrace();
					}
				}

				int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu setting?", "Setting",
						JOptionPane.YES_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					if (swing.panel3.effor == false) {
						try {
							swing.getSound().musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
							ee.printStackTrace();
						}
					}
					setVisible(false);
					swing.setVisible(false);
					swing.getSound().nhacNen = true;
					swing.panel3.loa = true;
					swing.getSound().clip.stop();
					try {
						swing = new GameWorld(new JButton[size][size], thinkingTime);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e2) {
						e2.printStackTrace();
					}
					swing.panel3.setLoa(false);
					swing.getSound().nhacNen = false;
					swing.panel3.statrr = false;
					swing.panel3.btOK.setEnabled(true);
					swing.mnb.mnStart.setEnabled(true);
					swing.setVisible(true);
				} else {
					if (swing.panel3.effor == false) {
						try {
							swing.getSound().musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
							ee.printStackTrace();
						}
					}
				}
			}
		});
	}

	public void setBackGroundPanel() {
		p3.setBackground(new Color(255, 255, 224));
		p4.setBackground(new Color(255, 250, 205));
		p5.setBackground(new Color(255, 255, 224));
		p6.setBackground(new Color(255, 250, 205));
		p1.setBackground(new Color(255, 228, 181));
		p2.setBackground(new Color(255, 228, 181));
	}

	public void setBackGroundSlider() {
		jslider1.setForeground(Color.red);
		jslider2.setForeground(Color.red);
		jsclider3.setForeground(Color.red);
		jsclider4.setForeground(Color.red);
		jslider1.setBackground(new Color(255, 255, 251));
		jslider2.setBackground(new Color(255, 255, 251));
		jsclider3.setBackground(new Color(255, 255, 251));
		jsclider4.setBackground(new Color(255, 255, 251));
	}

	public int getSaveThinkingTime() {
		return saveThinkingTime;
	}

	public void setSaveThinkingTime(int saveThinkingTime) {
		this.saveThinkingTime = saveThinkingTime;
	}
}
