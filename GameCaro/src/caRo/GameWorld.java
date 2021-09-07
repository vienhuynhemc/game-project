package caRo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import menu.Menu;
import panel.Panel1;
import panel.Panel2;
import panel.Panel3;
import sound.AllSound;

public class GameWorld extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel mainPanel;

	public boolean turn = true;

	public Panel3 panel3;
	public Panel2 panel2;
	public Panel1 panel1;

	public boolean[][] arrEnd;
	public int[][] arr;
	public int[][] arrWin;
	public JButton[][] arrButton;

	public Menu mnb;

	public AllSound sound;
	public Setting setting;

	public int wait;

	public GameWorld(JButton[][] arrr, int waitTime)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		arrButton = arrr;
		arrEnd = new boolean[arrButton.length][arrButton[0].length];
		arr = new int[arrButton.length][arrButton[0].length];
		arrWin = new int[arrButton.length][arrButton[0].length];
		wait = waitTime;

		createSwing();

		sound = new AllSound(this);

		panel3.a = new int[arrButton.length * arrButton[0].length];
		panel3.b = new int[arrButton.length * arrButton[0].length];
		panel3.c = new int[arrButton.length * arrButton[0].length];
		panel3.d = new int[arrButton.length * arrButton[0].length];

		panel3.size = arrButton.length;
		panel3.thinkingTime = wait;

		createMenu();

		setDisplay();

	}

	public void setDisplay() {
		setTitle("Caro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1250, 730));
		setSize(1250, 730);
		setLocationRelativeTo(null);
	}

	public void createMenu() {
		setJMenuBar(mnb = new Menu(this));
	}

	public void createSwing() {

		ImageIcon chuot = new ImageIcon("image/chuot.png");
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(chuot.getImage(), new Point(0, 30), "chuột game"));

		add(mainPanel = new JPanel());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		addP2();

		addP1();

		addP3();
	}

	public void addP1() {
		panel1 = new Panel1(this);
	}

	public void addP3() {
		mainPanel.add(panel3 = new Panel3(this));
	}

	public void addP2() {
		mainPanel.add(panel2 = new Panel2(this));
	}

	public void createSetting() {
		setSetting(new Setting(this));
	}

	public int checkWinKhongChan() {
		int count = 0;
		// dọc

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				int b = arr[i][j];
				for (int k = j; k < arr[i].length; k++) {
					if (arr[i][k] == 0 || arr[i][k] != b) {
						if (count == 5) {
							return b + 10;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[i][k] == b) {
						arrWin[i][k] = 10;
						count++;
					}
				}
				if (count == 5) {
					return b + 10;
				} else {
					count = 0;
					for (int jj = 0; jj < arrWin.length; jj++) {
						for (int j2 = 0; j2 < arrWin[jj].length; j2++) {
							arrWin[jj][j2] = 0;
						}
					}
				}
			}
		}

		// ngang

		for (int j = 0; j < arr[0].length; j++) {
			for (int i = 0; i < arr.length; i++) {
				int a = arr[i][j];
				for (int k = i; k < arr.length; k++) {
					if (arr[k][j] == 0 || arr[k][j] != a) {
						if (count == 5) {
							return a + 20;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[k][j] == a) {
						count++;
						arrWin[k][j] = 10;
					}
				}
				if (count == 5)
					return a + 20;
				else {
					count = 0;
					for (int k = 0; k < arr.length; k++) {
						for (int k2 = 0; k2 < arr[k].length; k2++) {
							arrWin[k][k2] = 0;
						}
					}
				}

			}
		}

		// chéo

		// chéo nửa dưới từ trái qua phải

		int plus = -1;
		for (int i = 0; i < arr.length; i++) {
			plus++;
			for (int j = 0; j < arr.length - i; j++) {
				int a = arr[j + plus][j];
				for (int k = j; k < arr.length - i; k++) {
					if (arr[k + plus][k] == 0 || arr[k + plus][k] != a) {
						if (count == 5) {
							return a + 30;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[k + plus][k] == a) {
						count++;
						arrWin[k + plus][k] = 10;
					}
				}
				if (count == 5)
					return a + 30;
				else {
					count = 0;
					for (int k2 = 0; k2 < arr.length; k2++) {
						for (int k = 0; k < arr[k2].length; k++) {
							arrWin[k2][k] = 0;
						}
					}
				}
			}
		}
		plus = -1;

		// chéo nửa trên từ trái qua phải

		for (int i = 0; i < arr[0].length; i++) {
			plus++;
			for (int j = 0; j < arr.length - i; j++) {
				int a = arr[j][j + plus];
				for (int k = j; k < arr.length - i; k++) {
					if (arr[k][k + plus] == 0 || arr[k][k + plus] != a) {
						if (count == 5) {
							return a + 30;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[k][k + plus] == a) {
						count++;
						arrWin[k][k + plus] = 10;
					}
				}
				if (count == 5)
					return a + 30;
				else {
					count = 0;
					for (int k2 = 0; k2 < arr.length; k2++) {
						for (int k = 0; k < arr[k2].length; k++) {
							arrWin[k2][k] = 0;
						}
					}
				}
			}
		}
		plus = -1;

		// chéo nửa dưới từ phải qua trái

		for (int i = 0; i < arr.length; i++) {
			for (int j = arr[i].length - 1; j - i > 0; j--) {
				int a = arr[i + arr[0].length - j - 1][j];
				for (int k = j; k - i > 0; k--) {
					if (arr[i + arr[0].length - k - 1][k] == 0 || arr[i + arr[0].length - k - 1][k] != a) {
						if (count == 5) {
							return a + 40;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[i + arr[0].length - k - 1][k] == a) {
						count++;
						arrWin[i + arr[0].length - k - 1][k] = 10;
					}
				}
				if (count == 5)
					return a + 40;
				else {
					count = 0;
					for (int k2 = 0; k2 < arr.length; k2++) {
						for (int k = 0; k < arr[k2].length; k++) {
							arrWin[k2][k] = 0;
						}
					}
				}
			}
		}

		// chéo nửa trên từ phải qua trái

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				int a = arr[i - j][j];
				for (int k = j; k < i + 1; k++) {
					if (arr[i - k][k] == 0 || arr[i - k][k] != a) {
						if (count == 5) {
							return a + 40;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[i - k][k] == a) {
						count++;
						arrWin[i - k][k] = 10;
					}
				}
				if (count == 5)
					return a + 40;
				else {
					count = 0;
					for (int k2 = 0; k2 < arr.length; k2++) {
						for (int k = 0; k < arr[k2].length; k++) {
							arrWin[k2][k] = 0;
						}
					}
				}
			}
		}

		return -1;
	}

	public int checkWin() {
		int count = 0;

		// dọc

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				int b = arr[i][j];
				for (int k = j; k < arr[i].length; k++) {
					if (arr[i][k] == 0 || arr[i][k] != b) {
						if (count == 5) {
							if (j - 1 >= 0) {
								if (arr[i][j - 1] != 0 && arr[i][k] != 0 && arr[i][j - 1] != b && arr[i][k] != b)
									count = 0;
								break;
							} else
								return b + 10;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[i][k] == b) {
						arrWin[i][k] = 10;
						count++;
					}
				}
				if (count == 5) {
					return b + 10;
				} else {
					count = 0;
					for (int jj = 0; jj < arrWin.length; jj++) {
						for (int j2 = 0; j2 < arrWin[jj].length; j2++) {
							arrWin[jj][j2] = 0;
						}
					}
				}
			}
		}

		// ngang

		for (int j = 0; j < arr[0].length; j++) {
			for (int i = 0; i < arr.length; i++) {
				int a = arr[i][j];
				for (int k = i; k < arr.length; k++) {
					if (arr[k][j] == 0 || arr[k][j] != a) {
						if (count == 5) {
							if (i - 1 >= 0) {
								if (arr[i - 1][j] != 0 && arr[i - 1][j] != a && arr[k][j] != 0 && arr[k][j] != a) {
									count = 0;
									break;
								} else {
									return a + 20;
								}
							}
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[k][j] == a) {
						count++;
						arrWin[k][j] = 10;
					}
				}
				if (count == 5)
					return a + 20;
				else {
					count = 0;
					for (int k = 0; k < arr.length; k++) {
						for (int k2 = 0; k2 < arr[k].length; k2++) {
							arrWin[k][k2] = 0;
						}
					}
				}

			}
		}

		// chéo

		// chéo nửa dưới từ trái qua phải

		int plus = -1;
		for (int i = 0; i < arr.length; i++) {
			plus++;
			for (int j = 0; j < arr.length - i; j++) {
				int a = arr[j + plus][j];
				for (int k = j; k < arr.length - i; k++) {
					if (arr[k + plus][k] == 0 || arr[k + plus][k] != a) {
						if (count == 5) {
							if (j - 1 >= 0 && j + plus - 1 >= 0) {
								if (arr[j + plus - 1][j - 1] != 0 && arr[j + plus - 1][j - 1] != a
										&& arr[k + plus][k] != 0 && arr[k + plus][k] != a) {
									count = 0;
									break;
								}
							} else
								return a + 30;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[k + plus][k] == a) {
						count++;
						arrWin[k + plus][k] = 10;
					}
				}
				if (count == 5)
					return a + 30;
				else {
					count = 0;
					for (int k2 = 0; k2 < arr.length; k2++) {
						for (int k = 0; k < arr[k2].length; k++) {
							arrWin[k2][k] = 0;
						}
					}
				}
			}
		}
		plus = -1;

		// chéo nửa trên từ trái qua phải

		for (int i = 0; i < arr[0].length; i++) {
			plus++;
			for (int j = 0; j < arr.length - i; j++) {
				int a = arr[j][j + plus];
				for (int k = j; k < arr.length - i; k++) {
					if (arr[k][k + plus] == 0 || arr[k][k + plus] != a) {
						if (count == 5) {
							if (j - 1 >= 0 && j + plus - 1 >= 0) {
								if (arr[j - 1][j + plus - 1] != 0 && arr[j - 1][j + plus - 1] != a
										&& arr[k][k + plus] != 0 && arr[k][k + plus] != a) {
									count = 0;
									break;
								}
							} else
								return a + 30;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[k][k + plus] == a) {
						count++;
						arrWin[k][k + plus] = 10;
					}
				}
				if (count == 5)
					return a + 30;
				else {
					count = 0;
					for (int k2 = 0; k2 < arr.length; k2++) {
						for (int k = 0; k < arr[k2].length; k++) {
							arrWin[k2][k] = 0;
						}
					}
				}
			}
		}
		plus = -1;

		// chéo nửa dưới từ phải qua trái

		for (int i = 0; i < arr.length; i++) {
			for (int j = arr[i].length - 1; j - i > 0; j--) {
				int a = arr[i + arr[0].length - j - 1][j];
				for (int k = j; k - i > 0; k--) {
					if (arr[i + arr[0].length - k - 1][k] == 0 || arr[i + arr[0].length - k - 1][k] != a) {
						if (count == 5) {
							if ((i + arr[0].length - j - 1 - 1) >= 0 && j + 1 <= arr[0].length - 1) {
								if (arr[i + arr[0].length - j - 1 - 1][j + 1] != 0
										&& arr[i + arr[0].length - j - 1 - 1][j + 1] != a
										&& arr[i + arr[0].length - k - 1][k] != 0
										&& arr[i + arr[0].length - k - 1][k] != a) {
									count = 0;
									break;
								}
							} else
								return a + 40;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[i + arr[0].length - k - 1][k] == a) {
						count++;
						arrWin[i + arr[0].length - k - 1][k] = 10;
					}
				}
				if (count == 5)
					return a + 40;
				else {
					count = 0;
					for (int k2 = 0; k2 < arr.length; k2++) {
						for (int k = 0; k < arr[k2].length; k++) {
							arrWin[k2][k] = 0;
						}
					}
				}
			}
		}

		// chéo nửa trên từ phải qua trái

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				int a = arr[i - j][j];
				for (int k = j; k < i + 1; k++) {
					if (arr[i - k][k] == 0 || arr[i - k][k] != a) {
						if (count == 5) {
							if (i - j + 1 <= arr.length - 1 && j - 1 >= 0) {
								if (arr[i - j + 1][j - 1] != 0 && arr[i - j + 1][j - 1] != a && arr[i - k][k] != 0
										&& arr[i - k][k] != a) {
									count = 0;
									break;
								} else
									return a + 40;
							}
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (arr[i - k][k] == a) {
						count++;
						arrWin[i - k][k] = 10;
					}
				}
				if (count == 5)
					return a + 40;
				else {
					count = 0;
					for (int k2 = 0; k2 < arr.length; k2++) {
						for (int k = 0; k < arr[k2].length; k++) {
							arrWin[k2][k] = 0;
						}
					}
				}
			}
		}

		return -1;
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// statrr == true là khi ấn start mới đc chơi

		if (panel3.statrr == true) {
			for (int i = 0; i < arrButton.length; i++) {
				for (int j = 0; j < arrButton[i].length; j++) {
					if (e.getSource() == arrButton[i][j] && e.getButton() == 1 && arrEnd[i][j] == false) {
						if (turn == true) {
							// danh va click
							arrButton[i][j].setIcon(new ImageIcon("image/x.png"));
							turn = false;
							arrEnd[i][j] = false;
							arr[i][j] = 1;

							// to mau
							panel3.locationX = i;
							panel3.locationY = j;

							// ten
							panel3.tf1.setBackground(new Color(244, 163, 162));
							panel3.tf2.setBackground(Color.white);
							panel3.jb.setValue(100); // thinking time
							panel3.t.start();
							// music
							if (panel3.effor == false) {
								try {
									sound.musicXDanh();
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
									e1.printStackTrace();
								}
							}

							// undo
							panel3.a[panel3.undo] = i;
							panel3.b[panel3.undo] = j;
							panel3.undo++;
							if (panel3.undo > 0) {
								mnb.mnUndo.setEnabled(true);
								panel3.btUndo.setEnabled(true);
							}

							// redo
							panel3.btRedo.setEnabled(false);
							panel3.redo = 0;

						}
					}
				}
			}

			if (turn == false) {
//				Move bestMove = findBestMove(arr, turn);
//				int i = bestMove.getX();
//				int j = bestMove.getY();
				int i = 0;
				int j = 0;
				for (int j2 = 0; j2 < arr.length; j2++) {
					for (int k = 0; k < arr[j2].length; k++) {
						if (arr[j2][k] == 0) {
							i = j2;
							j = k;
						}
					}
				}
				// danh va click
				arrButton[i][j].setIcon(new ImageIcon("image/o.png"));
				arrEnd[i][j] = true;
				arr[i][j] = 2;
				turn = true;

				// to mau
				panel3.locationX = i;
				panel3.locationY = j;

				panel3.jb.setValue(100); // thinking time
				panel3.t.start();

				// ten
				panel3.tf2.setBackground(new Color(244, 163, 162));
				panel3.tf1.setBackground(Color.white);

				// music
				if (panel3.effor == false) {
					try {
						sound.musicODanh();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}

				// undo
				panel3.a[panel3.undo] = i;
				panel3.b[panel3.undo] = j;
				panel3.undo++;
				if (panel3.undo > 0) {
					mnb.mnUndo.setEnabled(true);
					panel3.btUndo.setEnabled(true);
				}

				// redo
				panel3.btRedo.setEnabled(false);
				panel3.redo = 0;
			}

			// mỗi lần kich hiện màu vàng

			panel3.setBackGroud(); // to hết màu thường xong tô màu vàng bằng dòng ở dưới
			arrButton[panel3.locationX][panel3.locationY].setBackground(new Color(240, 222, 69));

			// label đổi lượt

			if (turn == false) {
				panel2.lb2.setIcon(new ImageIcon("image/xLabel.png"));
			} else if (turn == true) {
				panel2.lb2.setIcon(new ImageIcon("image/oLabel.png"));
			}

			// Xử lý win

			int index = -1;
			if (panel3.chanHaiDau == false) {
				index = checkWin();
			} else if (panel3.chanHaiDau == true) {
				index = checkWinKhongChan();
			}
			if (index != -1) {

				// music
				if (panel3.effor == false) {
					try {
						sound.musicEndGame();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}

				panel3.t.stop();
				int check = index % 10;
				int check2 = index / 10;
				for (int k = 0; k < arrEnd.length; k++) {
					for (int k2 = 0; k2 < arrEnd[k].length; k2++) {
						arrEnd[k][k2] = true;
					}
				}

				for (int k = 0; k < arrWin.length; k++) {
					for (int k2 = 0; k2 < arrWin[k].length; k2++) {
						if (arrWin[k][k2] == 10) {
							if (check == 1) {
								if (check2 == 1) {
									arrButton[k][k2].setIcon(new ImageIcon("image/xWinNgang.png"));
									arrButton[k][k2].setBackground(new Color(240, 222, 69));
									arr[k][k2] = 0;
								} else if (check2 == 2) {
									arrButton[k][k2].setIcon(new ImageIcon("image/xWinDoc.png"));
									arrButton[k][k2].setBackground(new Color(240, 222, 69));
									arr[k][k2] = 0;
								} else if (check2 == 3) {
									arrButton[k][k2].setIcon(new ImageIcon("image/xWinCheoXuoi.png"));
									arr[k][k2] = 0;
								} else if (check2 == 4) {
									arrButton[k][k2].setIcon(new ImageIcon("image/xWinCheoNguoc.png"));
									arrButton[k][k2].setBackground(new Color(240, 222, 69));
									arr[k][k2] = 0;
								}
							} else if (check == 2) {
								if (check2 == 1) {
									arrButton[k][k2].setIcon(new ImageIcon("image/oWinNgang.png"));
									arrButton[k][k2].setBackground(new Color(240, 222, 69));
									arr[k][k2] = 0;
								} else if (check2 == 2) {
									arrButton[k][k2].setIcon(new ImageIcon("image/oWinDoc.png"));
									arrButton[k][k2].setBackground(new Color(240, 222, 69));
									arr[k][k2] = 0;
								} else if (check2 == 3) {
									arrButton[k][k2].setIcon(new ImageIcon("image/oWinCheoXuoi.png"));
									arrButton[k][k2].setBackground(new Color(240, 222, 69));
									arr[k][k2] = 0;
								} else if (check2 == 4) {
									arrButton[k][k2].setIcon(new ImageIcon("image/oWinCheoNguoc.png"));
									arrButton[k][k2].setBackground(new Color(240, 222, 69));
									arr[k][k2] = 0;
								}
							}
						}
					}
				}

				// có người win thì ẩn 2 button này

				panel3.btUndo.setEnabled(false);
				panel3.btRedo.setEnabled(false);
				panel3.btXinThua.setEnabled(false);

				// check xem x thằng hay o thằng x = 1; o =2;

				if (check == 1) {
					String s = panel3.nameS1 + " đã Win ván này ^^, giỏi quá ^^";
					int a = JOptionPane.showConfirmDialog(this, s, "END MATCH", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, new ImageIcon("image/x.png"));
					panel3.nguoiWin = true;
					panel3.xWin++;
					if (a == JOptionPane.OK_OPTION) {
						if (panel3.effor == false) {
							try {
								sound.musicPop();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
								ee.printStackTrace();
							}
						}
					} else {
						if (panel3.effor == false) {
							try {
								sound.musicPop();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
								ee.printStackTrace();
							}
						}
					}
					panel3.t.stop();
				} else if (check == 2) {
					String s = panel3.nameS2 + " đã Win ván này ^^, giỏi quá ^^";
					int a = JOptionPane.showConfirmDialog(this, s, "END MATCH", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, new ImageIcon("image/o.png"));
					panel3.nguoiWin = false;
					panel3.oWin++;
					if (a == JOptionPane.OK_OPTION) {
						if (panel3.effor == false) {
							try {
								sound.musicPop();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
								ee.printStackTrace();
							}
						}
					} else {
						if (panel3.effor == false) {
							try {
								sound.musicPop();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
								ee.printStackTrace();
							}
						}
					}
					panel3.t.stop();
				}
				panel3.choiMoi = true; // để bấm start nó ko hiện ra bảng hỏi yes no
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		// chạy hết process bảr

		if (panel3.endBar == true) {

			panel3.endBar = false;
			panel3.t.stop();
			if (turn == true) {

				// music
				if (panel3.effor == false) {
					try {
						sound.musicEndGame();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}

				String s = panel3.nameS1 + " đã thắng vì hết thời gian suy nghĩ mà " + panel3.nameS2 + " không dánh !";
				int a = JOptionPane.showConfirmDialog(this, s, "END MATCH", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon("image/x.png"));
				if (a == JOptionPane.OK_OPTION) {
					if (panel3.effor == false) {
						try {
							sound.musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
							ee.printStackTrace();
						}
					}
				} else {
					if (panel3.effor == false) {
						try {
							sound.musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
							ee.printStackTrace();
						}
					}
				}

				panel3.xWin++;
			} else {

				// music
				if (panel3.effor == false) {
					try {
						sound.musicEndGame();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
				}

				String s = panel3.nameS2 + " đã thắng vì hết thời gian suy nghĩ mà " + panel3.nameS1 + " không dánh !";
				int a = JOptionPane.showConfirmDialog(this, s, "END MATCH", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon("image/o.png"));
				if (a == JOptionPane.OK_OPTION) {
					if (panel3.effor == false) {
						try {
							sound.musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
							ee.printStackTrace();
						}
					}
				} else {
					if (panel3.effor == false) {
						try {
							sound.musicPop();
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
							ee.printStackTrace();
						}
					}
				}

			}
			for (int k = 0; k < arrEnd.length; k++) {
				for (int k2 = 0; k2 < arrEnd[k].length; k2++) {
					arrEnd[k][k2] = true;
				}
			}
			panel3.choiMoi = true;
		}

		if (panel3.statrr == true) {
			panel3.btOK.setEnabled(false);
			mnb.mnStart.setEnabled(false);
		} else if (panel3.statrr == false) {
			panel3.btOK.setEnabled(true);
			mnb.mnStart.setEnabled(true);
		}
		if (panel3.reset == true) {
			panel3.btReset.setEnabled(true);
		} else if (panel3.reset == false) {
			panel3.btReset.setEnabled(false);
		}

		// ngày tháng thêm thôi :V

		java.util.Date date = java.util.Calendar.getInstance().getTime();
		panel3.tf3.setText(date + "");
	}

	public boolean isMoveLeft(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 0)
					return true;
			}
		}
		return false;
	}

	public int checkWin(int[][] array) {
		int count = 0;

		// dọc

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				int b = array[i][j];
				for (int k = j; k < array[i].length; k++) {
					if (array[i][k] == 0 || array[i][k] != b) {
						if (count == 5) {
							if (j - 1 >= 0) {
								if (array[i][j - 1] != 0 && array[i][k] != 0 && array[i][j - 1] != b
										&& array[i][k] != b)
									count = 0;
								break;
							} else
								return b;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[i][k] == b) {
						count++;
					}
				}
				if (count == 5) {
					return b;
				} else {
					count = 0;
				}
			}
		}

		// ngang

		for (int j = 0; j < array[0].length; j++) {
			for (int i = 0; i < array.length; i++) {
				int a = array[i][j];
				for (int k = i; k < array.length; k++) {
					if (array[k][j] == 0 || array[k][j] != a) {
						if (count == 5) {
							if (i - 1 >= 0) {
								if (array[i - 1][j] != 0 && array[i - 1][j] != a && array[k][j] != 0
										&& array[k][j] != a) {
									count = 0;
									break;
								} else {
									return a;
								}
							}
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[k][j] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}

			}
		}

		// chéo

		// chéo nửa dưới từ trái qua phải

		int plus = -1;
		for (int i = 0; i < array.length; i++) {
			plus++;
			for (int j = 0; j < array.length - i; j++) {
				int a = array[j + plus][j];
				for (int k = j; k < array.length - i; k++) {
					if (array[k + plus][k] == 0 || array[k + plus][k] != a) {
						if (count == 5) {
							if (j - 1 >= 0 && j + plus - 1 >= 0) {
								if (array[j + plus - 1][j - 1] != 0 && array[j + plus - 1][j - 1] != a
										&& array[k + plus][k] != 0 && array[k + plus][k] != a) {
									count = 0;
									break;
								}
							} else
								return a;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[k + plus][k] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}
			}
		}
		plus = -1;

		// chéo nửa trên từ trái qua phải

		for (int i = 0; i < array[0].length; i++) {
			plus++;
			for (int j = 0; j < array.length - i; j++) {
				int a = array[j][j + plus];
				for (int k = j; k < array.length - i; k++) {
					if (array[k][k + plus] == 0 || array[k][k + plus] != a) {
						if (count == 5) {
							if (j - 1 >= 0 && j + plus - 1 >= 0) {
								if (array[j - 1][j + plus - 1] != 0 && array[j - 1][j + plus - 1] != a
										&& array[k][k + plus] != 0 && array[k][k + plus] != a) {
									count = 0;
									break;
								}
							} else
								return a;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[k][k + plus] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}
			}
		}
		plus = -1;

		// chéo nửa dưới từ phải qua trái

		for (int i = 0; i < array.length; i++) {
			for (int j = array[i].length - 1; j - i > 0; j--) {
				int a = array[i + array[0].length - j - 1][j];
				for (int k = j; k - i > 0; k--) {
					if (array[i + array[0].length - k - 1][k] == 0 || array[i + array[0].length - k - 1][k] != a) {
						if (count == 5) {
							if ((i + array[0].length - j - 1 - 1) >= 0 && j + 1 <= array[0].length - 1) {
								if (array[i + array[0].length - j - 1 - 1][j + 1] != 0
										&& array[i + array[0].length - j - 1 - 1][j + 1] != a
										&& array[i + array[0].length - k - 1][k] != 0
										&& array[i + array[0].length - k - 1][k] != a) {
									count = 0;
									break;
								}
							} else
								return a;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[i + array[0].length - k - 1][k] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}
			}
		}

		// chéo nửa trên từ phải qua trái

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				int a = array[i - j][j];
				for (int k = j; k < i + 1; k++) {
					if (array[i - k][k] == 0 || array[i - k][k] != a) {
						if (count == 5) {
							if (i - j + 1 <= array.length - 1 && j - 1 >= 0) {
								if (array[i - j + 1][j - 1] != 0 && array[i - j + 1][j - 1] != a && array[i - k][k] != 0
										&& array[i - k][k] != a) {
									count = 0;
									break;
								} else
									return a;
							}
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[i - k][k] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}
			}
		}

		return -1;
	}

	public int checkWinKhongChan(int[][] array) {
		int count = 0;
		// dọc

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				int b = array[i][j];
				for (int k = j; k < array[i].length; k++) {
					if (array[i][k] == 0 || array[i][k] != b) {
						if (count == 5) {
							return b;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[i][k] == b) {
						count++;
					}
				}
				if (count == 5) {
					return b;
				} else {
					count = 0;
				}
			}
		}

		// ngang

		for (int j = 0; j < array[0].length; j++) {
			for (int i = 0; i < array.length; i++) {
				int a = array[i][j];
				for (int k = i; k < array.length; k++) {
					if (array[k][j] == 0 || array[k][j] != a) {
						if (count == 5) {
							return a;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[k][j] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}
			}
		}

		// chéo

		// chéo nửa dưới từ trái qua phải

		int plus = -1;
		for (int i = 0; i < array.length; i++) {
			plus++;
			for (int j = 0; j < array.length - i; j++) {
				int a = array[j + plus][j];
				for (int k = j; k < array.length - i; k++) {
					if (array[k + plus][k] == 0 || array[k + plus][k] != a) {
						if (count == 5) {
							return a;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[k + plus][k] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}
			}
		}
		plus = -1;

		// chéo nửa trên từ trái qua phải

		for (int i = 0; i < array[0].length; i++) {
			plus++;
			for (int j = 0; j < array.length - i; j++) {
				int a = array[j][j + plus];
				for (int k = j; k < array.length - i; k++) {
					if (array[k][k + plus] == 0 || array[k][k + plus] != a) {
						if (count == 5) {
							return a;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[k][k + plus] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}
			}
		}
		plus = -1;

		// chéo nửa dưới từ phải qua trái

		for (int i = 0; i < array.length; i++) {
			for (int j = array[i].length - 1; j - i > 0; j--) {
				int a = array[i + array[0].length - j - 1][j];
				for (int k = j; k - i > 0; k--) {
					if (array[i + array[0].length - k - 1][k] == 0 || array[i + array[0].length - k - 1][k] != a) {
						if (count == 5) {
							return a;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[i + array[0].length - k - 1][k] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}
			}
		}

		// chéo nửa trên từ phải qua trái

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				int a = array[i - j][j];
				for (int k = j; k < i + 1; k++) {
					if (array[i - k][k] == 0 || array[i - k][k] != a) {
						if (count == 5) {
							return a;
						} else if (count != 5) {
							count = 0;
							break;
						}
					} else if (array[i - k][k] == a) {
						count++;
					}
				}
				if (count == 5)
					return a;
				else {
					count = 0;
				}
			}
		}

		return -1;
	}

	public int minimax(int board[][], boolean isMax) {
		int score = checkWin(board);
		if (score == 1)
			return -10;
		if (score == 2)
			return 10;
		if (isMoveLeft(board) == false)
			return 0;
		if (isMax == false) {
			int best = -1000;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (board[i][j] == 0) {
						board[i][j] = 2;
						best = Math.max(best, minimax(board, !isMax));
						board[i][j] = 0;
					}
				}
			}
			return best;
		} else {
			int best = 1000;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (board[i][j] == 0) {
						board[i][j] = 1;
						best = Math.min(best, minimax(board, !isMax));
						board[i][j] = 0;
					}
				}
			}
			return best;
		}
	}

	public Move findBestMove(int board[][], boolean turn) {
		int bestVal = -1000;
		Move bestMove = new Move(0, 0);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i][j] == 0) {
					board[i][j] = 2;
					int moveVal = minimax(board, !turn);
					System.out.println(moveVal);
					board[i][j] = 0;
					if (moveVal > bestVal) {
						bestMove.setX(i);
						bestMove.setY(j);
						bestVal = moveVal;
					}
				}
			}
		}

		return bestMove;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public Panel2 getPanel2() {
		return panel2;
	}

	public void setPanel2(Panel2 panel2) {
		this.panel2 = panel2;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public AllSound getSound() {
		return sound;
	}

	public void setSound(AllSound sound) {
		this.sound = sound;
	}

}
