package giaoDien;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gameObject.GameWorld;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameWorld game;

	private Thread thread;

	private BufferedImage imageLayout;

	private GameFrame gameFrame;

	public GamePanel(GameFrame gameFrame) {

		this.gameFrame = gameFrame;

		game = new GameWorld();

		imageLayout = new BufferedImage(getGameFrame().getWidth(), getGameFrame().getHeight(),
				BufferedImage.TYPE_INT_RGB);

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(imageLayout, 0, 0, this);
	}

	public void upload() {
		getGame().upload();
	}

	public void draw() {
		Graphics2D g2 = (Graphics2D) imageLayout.getGraphics();

		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getGameFrame().getWidth(), getGameFrame().getHeight());

		getGame().draw(g2);
	}

	@Override
	public void run() {
		while (true) {
			try {

				upload();

				draw();

				repaint();

				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		getGame().getKeyAction().keyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public BufferedImage getImageLayout() {
		return imageLayout;
	}

	public void setImageLayout(BufferedImage imageLayout) {
		this.imageLayout = imageLayout;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

}
