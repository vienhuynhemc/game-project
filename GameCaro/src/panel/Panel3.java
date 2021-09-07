package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import caRo.GameWorld;

public class Panel3 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel p12, p13, p15, p21, p22, p14, p17, p16, p11, p18, p19, p20, p10;
	public JLabel lbTime, lbX, lbO;
	public JTextField tf1, tf2, tf3;

	public GameWorld game;
	public JProgressBar jb;

	public JButton btNhacNen;
	public JButton btEffor;
	public JButton btUndo;
	public JButton btRedo;
	public JButton btXinThua;
	public JButton btOK;
	public JButton btReset;

	public boolean endBar = false;

	public JCheckBox check;

	public Timer t;

	public ActionListener aciton;
	public boolean chanHaiDau = true;

	public boolean effor = false;

	public boolean choiMoi = false;

	public int[] a;
	public int[] b;
	public int undo = 0;

	public int redo = 0;
	public int[] c;
	public int[] d;

	public int xWin = 0;
	public int oWin = 0;

	public boolean statrr = false;
	public boolean reset = false;
	public String nameS1 = "", nameS2 = "";
	public boolean nguoiWin = false;

	public int locationX = 0;
	public int locationY = 0;

	public boolean loa = false;

	public int size;
	public int thinkingTime;

	public Panel3(GameWorld game) {

		this.game = game;

		setMain();

		creatJprogressBar();

		p10();

		creatTimer();

		p11();

		p12();

		p13();

		p15();

		p14();

		p16();

		p17();

		action();

		setColorAndBorder();

		setSize();

	}

	public void creatTimer() {
		aciton = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jb.getValue() > 0) {
					jb.setValue(jb.getValue() - 1);
				} else {
					endBar = true;
				}
			}
		};
		t = new Timer(game.wait, aciton);
	}

	public void p12() {
		add(p12 = new JPanel());
		p12.setLayout(new FlowLayout());
		p12.add(btUndo = new JButton());
		p12.add(btRedo = new JButton());
		btUndo.setIcon(new ImageIcon("image/undo.png"));
		btRedo.setIcon(new ImageIcon("image/redo.png"));
		btUndo.setBorderPainted(false);
		btUndo.setContentAreaFilled(false);
		btRedo.setBorderPainted(false);
		btRedo.setContentAreaFilled(false);
		btUndo.setToolTipText("Hủy bỏ ( trở về quá khứ 1 bước ) (Ctrl Z))");
		btRedo.setToolTipText("Làm lại ( Đi đến tương lai 1 bước ) (Ctrl Y))");
		btRedo.setEnabled(false);
		btUndo.setEnabled(false);
	}

	public void p13() {
		add(p13 = new JPanel());
		p13.add(jb);
		p13.setLayout(new FlowLayout());
		p13.add(lbTime = new JLabel());
		lbTime.setIcon(new ImageIcon("image/thinkingtime.png"));
		lbTime.setPreferredSize(new Dimension(170, 50));
		lbTime.setToolTipText("Thời gian suy nghĩ của mỗi lượt đánh");
	}

	public void p15() {
		add(p15 = new JPanel());
		p15.setLayout(new BoxLayout(p15, BoxLayout.Y_AXIS));
		p15.add(p21 = new JPanel());
		p15.add(p22 = new JPanel());
		JLabel score = new JLabel();
		score.setForeground(new Color(80, 80, 0));
		p21.add(score);
		score.setIcon(new ImageIcon("image/score.png"));
		score.setPreferredSize(new Dimension(190, 18));
		p22.add(new JLabel(new ImageIcon("image/x.png")));
		p22.add(lbX = new JLabel(":  0"));
		p22.add(new JLabel(new ImageIcon("image/o.png")));
		p22.add(lbO = new JLabel(":  0"));
		lbX.setPreferredSize(new Dimension(50, 20));
		lbO.setPreferredSize(new Dimension(50, 20));
	}

	public void p14() {
		add(p14 = new JPanel());
		p14.setLayout(new FlowLayout());
		p14.add(btEffor = new JButton());
		btEffor.setIcon(new ImageIcon("image/offEffor.png"));
		btEffor.setContentAreaFilled(false);
		btEffor.setBorderPainted(false);
		btEffor.setToolTipText("Nhấn để tắt nhạc hiệu ứng");
		p14.add(btNhacNen = new JButton());
		btNhacNen.setIcon(new ImageIcon("image/offSound.png"));
		btNhacNen.setBorderPainted(false);
		btNhacNen.setContentAreaFilled(false);
		btNhacNen.setToolTipText("Nhấn để tắt nhạc nền");
	}

	public void p17() {
		add(p17 = new JPanel());
		p17.setLayout(new FlowLayout());
		p17.add(tf3 = new JTextField());
		tf3.setPreferredSize(new Dimension(210, 40));
		tf3.setBorder(null);
		tf3.setEditable(false);
		tf3.setBackground(Color.white);
		tf3.setFont(new Font("Times New Roman", Font.ITALIC, 15));
	}

	public void p16() {
		add(p16 = new JPanel());
		p16.setLayout(new FlowLayout());
		p16.add(btXinThua = new JButton());
		btXinThua.setIcon(new ImageIcon("image/xinThua.png"));
		btXinThua.setContentAreaFilled(false);
		btXinThua.setBorderPainted(false);
		btXinThua.setEnabled(false);
	}

	public void p11() {
		add(p11 = new JPanel());
		p11.setLayout(new BoxLayout(p11, BoxLayout.Y_AXIS));
		p11.add(p18 = new JPanel());
		p11.add(p19 = new JPanel());
		p11.add(p20 = new JPanel());
		JLabel lbName = new JLabel();
		lbName.setIcon(new ImageIcon("image/name.png"));
		p18.add(lbName);
		lbName.setForeground(new Color(80, 80, 0));
		lbName.setPreferredSize(new Dimension(190, 32));
		p19.add(new JLabel(new ImageIcon("image/x.png")));
		p19.add(tf1 = new JTextField(16));
		p20.add(new JLabel(new ImageIcon("image/o.png")));
		p20.add(tf2 = new JTextField(16));
		tf1.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		tf2.setFont(new Font("Times New Roman", Font.ITALIC, 14));
	}

	public void p10() {
		add(p10 = new JPanel());
		p10.setLayout(new FlowLayout());
		p10.add(btOK = new JButton());
		btOK.setIcon(new ImageIcon("image/start.png"));
		btOK.setBorderPainted(false);
		btOK.setToolTipText("Nhấn để bắt đầu chơi ( Phải điền 2 tên người chơi mới nhấn nha ^^)");
		btOK.setContentAreaFilled(false);
		p10.add(btReset = new JButton());
		p10.add(check = new JCheckBox("Luật chặn 2 đầu"));
		btReset.setIcon(new ImageIcon("image/reset.png"));
		btReset.setToolTipText("Nhấn để đặt lại trò chơi");
		btReset.setBorderPainted(false);
		btReset.setContentAreaFilled(false);
		btReset.setEnabled(false);
	}

	public void creatJprogressBar() {
		jb = new JProgressBar(0, 100);
		jb.setValue(100);
		jb.setToolTipText("Thời gian suy nghĩ của mỗi lượt đánh");
		jb.setBackground(Color.white);
		jb.setForeground(Color.green);
		jb.setPreferredSize(new Dimension(200, 20));
		jb.setEnabled(false);
		jb.setBorderPainted(false);
	}

	public void setMain() {
		setPreferredSize(new Dimension(200, 710));
		setBackground(new Color(255, 234, 184));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public void action() {
		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					game.getSound().musicPop();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
				if (chanHaiDau == false) {
					chanHaiDau = true;
				} else if (chanHaiDau == true) {
					chanHaiDau = false;
				}
			}
		});

		tf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (effor == false) {
					try {
						game.getSound().musicGoPhim();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					startGame();
				}
			}
		});
		tf2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (effor == false) {
					try {
						game.getSound().musicGoPhim();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					startGame();
				}
			}
		});
		btOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		btReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetGame();
			}
		});
		btUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionUndo();
			}
		});
		btRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionRedo();
			}
		});

		btNhacNen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getSound().nhacNenGame();
			}
		});
		btEffor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hieuUngAmTham();
			}
		});
		btXinThua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dinggggggggg
				if (effor == false) {
					try {
						game.getSound().musicDing();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
						ee.printStackTrace();
					}
				}

				t.stop();
				int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn xin thua không?", "Đầu hàng",
						JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					if (effor == false) {
						try {
							game.getSound().musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							e1.printStackTrace();
						}
					}
					int countt = 0;
					for (int i = 0; i < game.arr.length; i++) {
						for (int j = 0; j < game.arr[i].length; j++) {
							if (game.arr[i][j] != 0) {
								countt++;
							}
						}
					}
					if (countt == 0) {
						int aa = JOptionPane.showConfirmDialog(null, "Chưa đánh con nào thì sao xin thua ?:V",
								"Xin thua", JOptionPane.DEFAULT_OPTION);
						if (aa == JOptionPane.OK_OPTION) {
							if (effor == false) {
								try {
									game.getSound().musicPop();
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
									e1.printStackTrace();
								}
							}
						} else {
							if (effor == false) {
								try {
									game.getSound().musicPop();
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
									e1.printStackTrace();
								}
							}
						}
					} else if (countt != 0) {
						if (effor == false) {
							try {
								game.getSound().musicEndGame();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								e1.printStackTrace();
							}
						}

						if (game.turn == false) {
							oWin++;
							nguoiWin = false;
							choiMoi = true;
							newMatch();
						} else if (game.turn == true) {
							xWin++;
							nguoiWin = true;
							choiMoi = true;
							newMatch();
						}
					}
				} else {
					if (effor == false) {
						try {
							game.getSound().musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
							ee.printStackTrace();
						}
					}
				}
			}
		});
	}

	public void setColorAndBorder() {
		p10.setBorder(new BevelBorder(BevelBorder.RAISED));
		p10.setBackground(new Color(255, 234, 184));
		p11.setBorder(new BevelBorder(BevelBorder.LOWERED));
		p11.setBackground(new Color(255, 234, 184));
		p12.setBorder(new BevelBorder(BevelBorder.RAISED));
		p12.setBackground(new Color(255, 234, 184));
		p13.setBorder(new BevelBorder(BevelBorder.LOWERED));
		p13.setBackground(new Color(255, 234, 184));
		p14.setBorder(new BevelBorder(BevelBorder.RAISED));
		p14.setBackground(new Color(255, 234, 184));
		p15.setBorder(new BevelBorder(BevelBorder.LOWERED));
		p15.setBackground(new Color(255, 234, 184));
		p16.setBorder(new BevelBorder(BevelBorder.RAISED));
		p16.setBackground(new Color(255, 234, 184));
		p17.setBorder(new BevelBorder(BevelBorder.LOWERED));
		p17.setBackground(new Color(255, 234, 184));
		p18.setBackground(new Color(255, 234, 184));
		p19.setBackground(new Color(255, 234, 184));
		p20.setBackground(new Color(255, 234, 184));
		p21.setBackground(new Color(255, 234, 184));
		p22.setBackground(new Color(255, 234, 184));

		check.setBackground(new Color(255, 234, 184));

		lbTime.setForeground(new Color(80, 80, 0));

	}

	public void setSize() {
		p10.setPreferredSize(new Dimension(200, 70));
		p12.setPreferredSize(new Dimension(200, 50));
		p13.setPreferredSize(new Dimension(200, 70));
		p14.setPreferredSize(new Dimension(200, 45));
		p16.setPreferredSize(new Dimension(200, 75));
		p17.setPreferredSize(new Dimension(200, 20));
		p19.setPreferredSize(new Dimension(200, 10));
		p18.setPreferredSize(new Dimension(200, 10));
		p20.setPreferredSize(new Dimension(200, 20));
		p21.setPreferredSize(new Dimension(200, 5));
		p22.setPreferredSize(new Dimension(200, 10));
	}

	public void startGame() {
		if (effor == false) {
			try {
				game.getSound().musicPop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		String s1 = tf1.getText();
		String s2 = tf2.getText();
		nameS1 = s1;
		nameS2 = s2;

		if (s1.compareTo("") != 0 && s2.compareTo("") != 0) { // đủ 2 tên thì chạy
			btOK.setEnabled(false);
			btReset.setEnabled(true);
			tf1.setEditable(false);
			tf2.setEditable(false);
			statrr = true;
			reset = true;
			setBackGroud();
			for (int i = 0; i < game.arr.length; i++) {
				for (int j = 0; j < game.arr[i].length; j++) {
					game.arrEnd[i][j] = false;
					game.arrButton[i][j].setEnabled(true);
				}
			}
			game.panel2.btNewGame.setIcon(new ImageIcon("image/newGame.png"));
			game.mnb.mnNewMatch.setEnabled(true);
			game.mnb.mnReset.setEnabled(true);
			btXinThua.setEnabled(true);
			check.setEnabled(false);
		} else if (s1.compareTo("") != 0 && s2.compareTo("") == 0) {
			tf2.setBackground(Color.red);
			Timer time = new Timer(3000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					tf2.setBackground(Color.white);
				}
			});
			time.start();
			int a = JOptionPane.showConfirmDialog(null, "Tên của người chơi 2 đâu ??:D??", "Chấm hỏi?",
					JOptionPane.DEFAULT_OPTION);
			if (a == JOptionPane.OK_OPTION) {
				if (effor == false) {
					try {
						game.getSound().musicPop();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
						ee.printStackTrace();
					}
				}
			} else {
				if (effor == false) {
					try {
						game.getSound().musicPop();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
						ee.printStackTrace();
					}
				}
			}

		} else if (s1.compareTo("") == 0 && s2.compareTo("") != 0) {
			tf1.setBackground(Color.red);
			Timer time = new Timer(3000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					tf1.setBackground(Color.white);
				}
			});
			time.start();

			int a = JOptionPane.showConfirmDialog(null, "Tên của người chơi 1 đâu ??:D??", "Chấm hỏi?",
					JOptionPane.DEFAULT_OPTION);
			if (a == JOptionPane.OK_OPTION) {
				if (effor == false) {
					try {
						game.getSound().musicPop();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
						ee.printStackTrace();
					}
				}
			} else {
				if (effor == false) {
					try {
						game.getSound().musicPop();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
						ee.printStackTrace();
					}
				}
			}
		} else {
			tf1.setBackground(Color.red);
			tf2.setBackground(Color.red);
			Timer time = new Timer(3000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					tf1.setBackground(Color.white);
					tf2.setBackground(Color.white);
				}
			});
			time.start();
			int a = JOptionPane.showConfirmDialog(null, "Tên của 2 người chơi đâu ??:D??", "Chấm hỏi?",
					JOptionPane.DEFAULT_OPTION);
			if (a == JOptionPane.OK_OPTION) {
				if (effor == false) {
					try {
						game.getSound().musicPop();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
						ee.printStackTrace();
					}
				}
			} else {
				if (effor == false) {
					try {
						game.getSound().musicPop();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
						ee.printStackTrace();
					}
				}
			}
		}
	}

	public void resetGame() {

		// dinggggggggg

		if (effor == false) {
			try {
				game.getSound().musicDing();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

		t.stop(); // dừng progcess bar

		// hiện bảng hỏi yes no

		int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo mới lại game?", "Rest !",
				JOptionPane.YES_NO_OPTION);
		if (a == JOptionPane.YES_OPTION) {
			if (effor == false) {
				try {
					game.getSound().musicPop();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < game.arr.length; i++) {
				for (int j = 0; j < game.arr[i].length; j++) {
					game.arrButton[i][j].setBackground(new Color(240, 240, 240));
					game.arrButton[i][j].setIcon(null);
				}
			}

			// reset

			btOK.setEnabled(true);
			statrr = false;
			btXinThua.setEnabled(false);
			btReset.setEnabled(false);
			game.panel2.btNewGame.setIcon(null);
			nameS1 = "";
			nameS2 = "";
			reset = false;
			xWin = 0;
			oWin = 0;
			tf1.setText("");
			tf2.setText("");
			tf1.setEditable(true);
			tf2.setEditable(true);
			lbX.setText(":  0");
			lbO.setText(":  0");
			tf1.setBackground(Color.white);
			tf2.setBackground(Color.white);
			jb.setValue(100);
			game.mnb.mnNewMatch.setEnabled(false);
			game.mnb.mnStart.setEnabled(true);
			game.mnb.mnReset.setEnabled(false);
			redo = 0;
			undo = 0;
			btRedo.setEnabled(false);
			btUndo.setEnabled(false);
			check.setEnabled(true);
		} else {
			if (effor == false) {
				try {
					game.getSound().musicPop();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
			int count = 0;
			for (int i = 0; i < game.arr.length; i++) {
				for (int j = 0; j < game.arr[i].length; j++) {
					if (game.arr[i][j] != 0)
						count++;
				}
			}
			if (count != 0) {
				t.start();
			}
		}
	}

	public void actionRedo() {
		if (effor == false) {
			try {
				game.getSound().musicPop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		redo--;
		int x = c[redo];
		int y = d[redo];
		a[undo] = x;
		b[undo] = y;
		undo++;
		if (undo >= 1) {
			btUndo.setEnabled(true);
			game.mnb.mnUndo.setEnabled(true);
		}

		if (redo >= 0) {
			locationX = c[redo];
			locationY = d[redo];
			setBackGroud();
			game.arrButton[locationX][locationY].setBackground(new Color(240, 222, 69)); // to màu vàng
		}

		if (game.turn == false) {
			game.arrButton[x][y].setIcon(new ImageIcon("image/x.png"));
			game.arr[x][y] = 1;
			game.arrEnd[x][y] = false;
			game.turn = true;
		} else if (game.turn == true) {
			game.arrButton[x][y].setIcon(new ImageIcon("image/o.png"));
			game.arr[x][y] = 2;
			game.arrEnd[x][y] = false;
			game.turn = false;
		}

		if (redo == 0) {
			game.mnb.mnRedo.setEnabled(false);
			btRedo.setEnabled(false);
		}
	}

	public void actionUndo() {
		if (effor == false) {
			try {
				game.getSound().musicPop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		if (choiMoi == false) {
			undo--;
			c[redo] = a[undo];
			d[redo] = b[undo];
			redo++;
			if (redo >= 1) {
				game.mnb.mnRedo.setEnabled(true);
				btRedo.setEnabled(true);
			}
			int x = a[undo];
			int y = b[undo];
			game.arrButton[x][y].setIcon(null);
			game.arrEnd[x][y] = false;
			game.arr[x][y] = 0;

			if (undo - 1 >= 0) {
				int x2 = a[undo - 1];
				int y2 = b[undo - 1];
				locationX = x2;
				locationY = y2;
				setBackGroud();
				game.arrButton[x2][y2].setBackground(new Color(240, 222, 69));
			}

			if (game.turn == false) {
				game.turn = true;
				game.panel2.lb2.setIcon(new ImageIcon("image/oLabel.png"));
				tf2.setBackground(Color.white);
				tf1.setBackground(new Color(244, 163, 162));
			} else if (game.turn == true) {
				game.turn = false;
				game.panel2.lb2.setIcon(new ImageIcon("image/xLabel.png"));
				tf2.setBackground(new Color(244, 163, 162));
				tf1.setBackground(Color.white);
			}
			jb.setValue(100);
			t.start();

			if (undo == 0) {
				game.mnb.mnUndo.setEnabled(false);
				btUndo.setEnabled(false);
				if (x % 2 == 0) {
					if (y % 2 == 0) {
						game.arrButton[x][y].setBackground(new Color(255, 240, 245));
					} else {
						game.arrButton[x][y].setBackground(new Color(240, 248, 255));
					}
				} else {
					if (y % 2 == 0) {
						game.arrButton[x][y].setBackground(new Color(240, 248, 255));
					} else {
						game.arrButton[x][y].setBackground(new Color(255, 240, 245));
					}
				}
			}
		}
	}

	public void hieuUngAmTham() {

		if (effor == false) {
			try {
				game.getSound().musicPop();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}
		if (effor == false) {
			btEffor.setIcon(new ImageIcon("image/onEffor.png"));
			effor = true;
			btEffor.setToolTipText("Nhấn để bật nhạc hiệu ứng");
		} else if (effor == true) {
			btEffor.setIcon(new ImageIcon("image/offEffor.png"));
			effor = false;
			btEffor.setToolTipText("Nhấn để tắt nhạc hiệu ứng");
		}
	}

	public void newMatch() {

		if (effor == false) {
			try {
				game.getSound().musicDing();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

		if (statrr == true) { // có start rồi mới có new match
			if (choiMoi == true) {

				// đặt lại undo and redo

				undo = 0;
				redo = 0;
				btRedo.setEnabled(false);
				btUndo.setEnabled(false);
				btXinThua.setEnabled(true);
				// rest

				clear();
				jb.setValue(100);
			} else if (choiMoi == false) {
				t.stop(); // dừng progcess bar

				// hiển thị hỏi yes no

				int a = JOptionPane.showConfirmDialog(null, "Bạn có muốn chơi mới lại ?", "New Game",
						JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					if (effor == false) {
						try {
							game.getSound().musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							e.printStackTrace();
						}
					}

					// rest

					clear();
					jb.setValue(100);

					// đặt lại undo and redo

					undo = 0;
					redo = 0;
					btXinThua.setEnabled(true);
					btRedo.setEnabled(false);
					btUndo.setEnabled(false);
				} else {
					if (effor == false) {
						try {
							game.getSound().musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							e.printStackTrace();
						}
					}
					int count = 0;
					for (int i = 0; i < game.arr.length; i++) {
						for (int j = 0; j < game.arr[i].length; j++) {
							if (game.arr[i][j] != 0)
								count++;
						}
					}
					if (count != 0) {
						t.start();
					}
				}
			}
		}
	}

	private void clear() {
		for (int i = 0; i < game.arr.length; i++) {
			for (int j = 0; j < game.arr[i].length; j++) {
				game.arrButton[i][j].setIcon(null); // mất hình
				game.arr[i][j] = 0; // đặt lại hết cờ
				game.arrEnd[i][j] = false; // cho click đc
				game.arrWin[i][j] = 0; // đặt lại đường win
				choiMoi = false;
				setBackGroud();
			}
		}

		// sử lý khi đánh label2 nó cho biết người tiếp theo đánh là ai

		if (nguoiWin == false) {
			game.turn = false;
			game.panel2.lb2.setIcon(new ImageIcon("image/xLabel.png"));
		} else if (nguoiWin == true) {
			game.turn = true;
			game.panel2.lb2.setIcon(new ImageIcon("image/oLabel.png"));
		}

		// jprocessbar

		jb.setValue(0);
		t.stop();
		endBar = false;

		// set lại luôn ván thắng

		lbX.setText(":  " + xWin);
		lbO.setText(":  " + oWin);
	}

	public boolean isLoa() {
		return loa;
	}

	public void setLoa(boolean loa) {
		this.loa = loa;
	}

	public void setBackGroud() {

		for (int i = 0; i < game.arrButton.length; i++) {
			for (int j = 0; j < game.arrButton[i].length; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						game.arrButton[i][j].setBackground(new Color(255, 240, 245));
					} else {
						game.arrButton[i][j].setBackground(new Color(240, 248, 255));
					}
				} else {
					if (j % 2 == 0) {
						game.arrButton[i][j].setBackground(new Color(240, 248, 255));
					} else {
						game.arrButton[i][j].setBackground(new Color(255, 240, 245));
					}
				}
			}
		}
	}

	public void exit() {

		if (effor == false) {
			try {
				game.getSound().musicDing();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

		t.stop(); // dừng lại phát
		int a = JOptionPane.showConfirmDialog(null, "Bạn có chắc là muốn thoát game ?", "Question?",
				JOptionPane.YES_NO_OPTION);
		if (a == JOptionPane.YES_OPTION) {
			if (effor == false) {
				try {
					game.getSound().musicPop();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
			System.exit(0);
		} else {
			if (effor == false) {
				try {
					game.getSound().musicPop();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		}
		int count = 0;
		for (int i = 0; i < game.arr.length; i++) {
			for (int j = 0; j < game.arr[i].length; j++) {
				if (game.arr[i][j] != 0)
					count++;
			}
		}
		if (count != 0) {
			t.start();
		}
	}
}
