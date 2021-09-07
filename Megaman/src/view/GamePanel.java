package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

import controller.ControllerGameWorld;
import gameObject.Upload;

public class GamePanel extends JPanel implements Runnable, KeyListener, Upload {

	/**
	 * Panel Chính
	 */
	private static final long serialVersionUID = 1L;

	private Thread thread;

	private BufferedImage mainImage;

	private GameFrame game;

	private ControllerGameWorld controller;

	public GamePanel(GameFrame game) throws IOException {

		this.game = game;

		controller = new ControllerGameWorld(this);

		thread = new Thread(this);

		mainImage = new BufferedImage(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);

		startGame();
	}

	@Override
	public void upload() throws IOException {
		controller.upload();
	}

	public void draw() {
		Graphics2D g2 = (Graphics2D) mainImage.getGraphics();
		controller.draw(g2);
	}

	public void startGame() {
		thread.start();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(mainImage, 0, 0, this);
	}

	@Override
	public void run() {
		int FPS = 360;
		long beginTime = System.nanoTime();
		long timeForOneFrame = 1000000000 / FPS;
		while (true) {
			long timeSleep = timeForOneFrame - (System.nanoTime() - beginTime);

			draw();
			try {
				upload();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			repaint();

			try {
				if (timeSleep > 0) {
					Thread.sleep(timeSleep / 1000000);
				} else
					Thread.sleep(timeForOneFrame / 2000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			beginTime = System.nanoTime();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		controller.actionPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		controller.actionRelease(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public BufferedImage getMainImage() {
		return mainImage;
	}

	public void setMainImage(BufferedImage mainImage) {
		this.mainImage = mainImage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GameFrame getGame() {
		return game;
	}

	public void setGame(GameFrame game) {
		this.game = game;
	}

	public ControllerGameWorld getController() {
		return controller;
	}

}
