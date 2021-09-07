package view;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import controller.ControllerFrame;

public class GameFrame extends JFrame {

	/**
	 * Khung Chơi Game Version 1.0
	 */

	private static final long serialVersionUID = 219070669723582699L;

	private GamePanel panelGame;

	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = 600;

	private ControllerFrame controller;

	public GameFrame() throws IOException, LineUnavailableException, UnsupportedAudioFileException {

		controller = new ControllerFrame(this);
		init();

		this.setResizable(false);
		this.addKeyListener(panelGame);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Megamen 1.0");
		this.setSize(getGAME_WIDTH(), getGAME_HEIGHT());
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void veMenu() throws IOException {
		controller.veMenu();
	}

	public void init() throws IOException {
		controller.init();
	}

	public void switchPanel() throws IOException {
		controller.switchPanel();
	}

	public int getGAME_WIDTH() {
		return GAME_WIDTH;
	}

	public int getGAME_HEIGHT() {
		return GAME_HEIGHT;
	}

	public static int getGameWidth() {
		return GAME_WIDTH;
	}

	public static int getGameHeight() {
		return GAME_HEIGHT;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GamePanel getPanelGame() {
		return panelGame;
	}

	public void setPanelGame(GamePanel panelGame) {
		this.panelGame = panelGame;
	}

	public ControllerFrame getController() {
		return controller;
	}

}
