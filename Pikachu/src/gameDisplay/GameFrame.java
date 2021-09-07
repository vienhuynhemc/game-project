package gameDisplay;

import java.io.IOException;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int GAME_WIDH = 1000;
	public static final int GAME_HEIGH = 650;

	private GamePanel mainPanel;

	public GameFrame() throws IOException {

		add(mainPanel = new GamePanel(this));

		setSize(GAME_WIDH, GAME_HEIGH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public GamePanel getMainPanel() {
		return mainPanel;
	}

	public GamePanel setMainPanel(GamePanel mainPanel) {
		this.mainPanel = mainPanel;
		return mainPanel;
	}

}
