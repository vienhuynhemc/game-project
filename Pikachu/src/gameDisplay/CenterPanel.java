package gameDisplay;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import gameObject.ButtonClick;
import gameObject.LongDepTrai;
import gameObject.Move;
import loadData.ManagerSound;

public class CenterPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;
	private ButtonClick[][] arrayButton;

	public CenterPanel(GamePanel gamePanel) {

		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));

		this.gamePanel = gamePanel;

	}

	public void upload() {
		this.arrayButton = gamePanel.getGameWorld().getArrayButton();

		for (int i = 1; i < arrayButton.length - 1; i++) {
			for (int j = 1; j < arrayButton[i].length - 1; j++) {
				arrayButton[i][j].upload();
			}
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		for (int i = 1; i < arrayButton.length - 1; i++) {
			for (int j = 1; j < arrayButton[i].length - 1; j++) {
				if (arrayButton[i][j].isVisible())
					arrayButton[i][j].draw(g2);
			}
		}
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 1; i < arrayButton.length - 1; i++) {
			for (int j = 1; j < arrayButton[i].length - 1; j++) {
				if (e.getSource() == arrayButton[i][j] && e.getButton() == 1) {
					if (gamePanel.getGameWorld().getCountClick() == 0) {
						gamePanel.getGameWorld().setCountClick(1);
						gamePanel.getGameWorld().getMoveLast().setX(i);
						gamePanel.getGameWorld().getMoveLast().setY(j);

						arrayButton[i][j].setPress(true);
					} else {

						if (!arrayButton[i][j].isPress()) { // nếu con này đc click rồi (isPres = true) thì ko làm đoạn
															// dưới , ngược lại thì làm

							gamePanel.getGameWorld().setCountClick(0);
							gamePanel.getGameWorld().getMoveNew().setX(i);
							gamePanel.getGameWorld().getMoveNew().setY(j);

							arrayButton[i][j].setPress(true);

							Move f = gamePanel.getGameWorld().getMoveLast();
							Move s = gamePanel.getGameWorld().getMoveNew();
							if (gamePanel.getGameWorld().isConnect()) {
								arrayButton[f.getX()][f.getY()].setVisible(false);
								arrayButton[s.getX()][s.getY()].setVisible(false);

								gamePanel.getGameWorld().hanhXuLevel();

								if (gamePanel.getGameWorld().isHetDuong()) {
									gamePanel.getGameWorld().taoMoiDuong();
								}

								ManagerSound.getInstance().getListSound().get("danLv12").start();
								try {
									ManagerSound.getInstance().khoiTaoHieuUng("danLv12");
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
									e1.printStackTrace();
								}
							} else {

								ManagerSound.getInstance().getListSound().get("tiepDat").start();
								try {
									ManagerSound.getInstance().khoiTaoHieuUng("tiepDat");
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
									e1.printStackTrace();
								}

								arrayButton[f.getX()][f.getY()].setPress(false);
								arrayButton[s.getX()][s.getY()].setPress(false);

								Move m1 = new Move(f.getX() - 1, f.getY() - 1);
								Move m2 = new Move(s.getX() - 1, s.getY() - 1);

								gamePanel.getListAnimation()
										.add(new LongDepTrai(gamePanel.getGameWorld(),
												m1.getY() * 52 + 30 + m1.getY() * 2 + 25,
												m1.getX() * 52 + 40 + m1.getX() * 2 + 25, 2));
								gamePanel.getListAnimation()
										.add(new LongDepTrai(gamePanel.getGameWorld(),
												m2.getY() * 52 + 30 + m2.getY() * 2 + 25,
												m2.getX() * 52 + 40 + m2.getX() * 2 + 25, 2));
							}
						} else {
							if (i == getGamePanel().getGameWorld().getMoveLast().getX()
									&& j == getGamePanel().getGameWorld().getMoveLast().getY()) {
								arrayButton[i][j].setPress(false);
								gamePanel.getGameWorld().setCountClick(0);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (int i = 1; i < arrayButton.length - 1; i++) {
			for (int j = 1; j < arrayButton[i].length - 1; j++) {
				if (e.getSource() == arrayButton[i][j]) {
					arrayButton[i][j].setIn(true);
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (int i = 1; i < arrayButton.length - 1; i++) {
			for (int j = 1; j < arrayButton[i].length - 1; j++) {
				if (e.getSource() == arrayButton[i][j]) {
					arrayButton[i][j].setIn(false);
				}
			}
		}
	}

	public ButtonClick[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonClick[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

}
