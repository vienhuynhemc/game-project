package giaoDien;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;

	public GameFrame() {

		setSize(510, 510);
		setTitle("Game Con Ran");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		add(gamePanel = new GamePanel(this));
		this.addKeyListener(gamePanel);
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
