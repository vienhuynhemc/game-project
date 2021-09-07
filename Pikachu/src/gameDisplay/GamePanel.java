package gameDisplay;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gameObject.GameWorld;
import gameObject.Line;
import gameObject.LongDepTrai;
import loadData.ManagerSound;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private GameWorld gameWorld;

	private TopPanel topPanel;
	private CenterPanel centerPanel;

	private Thread thread;

	private BufferedImage mainImage;

	private List<Line> listLine;
	private List<LongDepTrai> listAnimation;

	private GameFrame game;

	public GamePanel(GameFrame game) throws IOException {
		this.game = game;
		setLayout(null);

		listLine = new ArrayList<Line>();
		listAnimation = new ArrayList<LongDepTrai>();

		mainImage = new BufferedImage(1000, 650, BufferedImage.TYPE_INT_ARGB);

		add(topPanel = new TopPanel(this));
//		setbounds x,y,w,h
		topPanel.setBounds(0, 0, 1000, 50);
		add(centerPanel = new CenterPanel(this));
		centerPanel.setBounds(0, 50, 1000, 550);

		gameWorld = new GameWorld(this);

		ManagerSound.getInstance().getListSound().get("nhacNenMapleStory").start();

		thread = new Thread(this);
		thread.start();

	}

	public void upload() throws IOException {

		topPanel.upload();
		getGameWorld().getBackGround().upload();
		if (!topPanel.isPause()) {
			centerPanel.upload();

			gameWorld.upload();

			for (int i = 0; i < listLine.size(); i++) {
				listLine.get(i).upload();
				if (listLine.get(i).isComplete()) {
					listLine.remove(i);
				}
			}

			for (int i = 0; i < listAnimation.size(); i++) {
				listAnimation.get(i).upload();
				if (listAnimation.get(i).getAnime().isLastFrame()) {
					listAnimation.remove(i);
				}
			}

			if (gameWorld.getTimer().isEnd()) {
				gameWorld.getTimer().setEnd(false);
				int a = JOptionPane.showConfirmDialog(null, "You lost,play again?", "NOtification",
						JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					gameWorld.newGame();
				} else {
					System.exit(0);
				}
			}

			if (gameWorld.isEnd()) {

				getTopPanel().getTimer().setStop(true);
				int a = JOptionPane.showConfirmDialog(null, "Bạn thật tuyệt vời =)) chơi lại không nào?", "Win",
						JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.OK_OPTION) {
					gameWorld.newGame();
				} else {
					System.exit(0);
				}
			}
		}
	}

	public void draw() {
		Graphics2D g2 = (Graphics2D) mainImage.getGraphics();

		getGameWorld().getBackGround().draw(g2);

		if (!topPanel.isPause()) {

			centerPanel.draw(g2);
			topPanel.draw(g2);

		}
		if (listLine.size() > 0) {
			for (int i = 0; i < listLine.size(); i++) {
				listLine.get(i).draw(g2);
			}
		}

		if (listAnimation.size() > 0) {
			for (int i = 0; i < listAnimation.size(); i++) {
				listAnimation.get(i).draw(g2);
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(mainImage, 0, 0, this);
	}

	@Override
	public void run() {
		while (true) {
			try {

				upload();
				draw();

				repaint();

				Thread.sleep(1000 / 80);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	public List<Line> getListLine() {
		return listLine;
	}

	public void setListLine(List<Line> listLine) {
		this.listLine = listLine;
	}

	public TopPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(TopPanel topPanel) {
		this.topPanel = topPanel;
	}

	public CenterPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(CenterPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public BufferedImage getMainImage() {
		return mainImage;
	}

	public void setMainImage(BufferedImage mainImage) {
		this.mainImage = mainImage;
	}

	public GameFrame getGame() {
		return game;
	}

	public void setGame(GameFrame game) {
		this.game = game;
	}

	public List<LongDepTrai> getListAnimation() {
		return listAnimation;
	}

	public void setListAnimation(List<LongDepTrai> listAnimation) {
		this.listAnimation = listAnimation;
	}

}
