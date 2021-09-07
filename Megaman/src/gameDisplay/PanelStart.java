package gameDisplay;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;

import sound.ManagerSound;
import view.ButtonPanel;
import view.GameFrame;

public class PanelStart extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel backGroundPanel;
	private ButtonPanel buttonPanel;

	private LableBackGround lableB;
	private GameFrame game;

	public PanelStart(GameFrame game) throws IOException {

		this.game = game;

		ManagerSound.getInstance().getListSound().get("nhacNenLoading").start();

		setLayout(null);

		setPreferredSize(new Dimension(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT));

		createButton();

		createBackGround();

	}

	private void createBackGround() throws IOException {
		add(backGroundPanel = new JPanel());
		backGroundPanel.add(lableB = new LableBackGround());
		backGroundPanel.setBounds(0, -10, GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
	}

	private void createButton() throws IOException {
		add(buttonPanel = new ButtonPanel(this));
		buttonPanel.setBounds(0, 0, GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
		addMouseListener(buttonPanel);
		addMouseMotionListener(buttonPanel);
	}

	public JPanel getBackGround() {
		return backGroundPanel;
	}

	public void setBackGround(JPanel backGround) {
		this.backGroundPanel = backGround;
	}

	public LableBackGround getLableB() {
		return lableB;
	}

	public void setLableB(LableBackGround lableB) {
		this.lableB = lableB;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPanel getBackGroundPanel() {
		return backGroundPanel;
	}

	public void setBackGroundPanel(JPanel backGroundPanel) {
		this.backGroundPanel = backGroundPanel;
	}

	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public GameFrame getGame() {
		return game;
	}

	public void setGame(GameFrame game) {
		this.game = game;
	}

}
