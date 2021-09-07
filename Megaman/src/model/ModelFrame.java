package model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import controller.ControllerFrame;
import gameDisplay.PanelStart;
import sound.ManagerSound;
import view.GameFrame;
import view.GamePanel;

public class ModelFrame {

	private PanelStart panelStart;
	private JPanel mainPanel;

	private boolean isPressButton;

	private ControllerFrame controller;

	public ModelFrame(ControllerFrame controller) {
		this.controller = controller;
	}

	public void init() throws IOException {
		panelStart = new PanelStart(controller.getGameFrame());
		panelStart.setBounds(0, 0, GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.add(panelStart);
		controller.getGameFrame().add(mainPanel);

		controller.getGameFrame().setPanelGame(new GamePanel(controller.getGameFrame()));
		controller.getGameFrame().getPanelGame().setBounds(0, 0, GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
	}

	public void switchPanel() throws IOException {
		mainPanel.remove(panelStart);

		mainPanel.add(controller.getGameFrame().getPanelGame());
		controller.getGameFrame().getPanelGame().getController().getModel().setLoading(false);

		setPressButton(true);
		try {
			ManagerSound.getInstance().stop("nhacNenLoading");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		controller.getGameFrame().repaint();
	}

	public PanelStart getPanelStart() {
		return panelStart;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public ControllerFrame getController() {
		return controller;
	}

	public boolean isPressButton() {
		return isPressButton;
	}

	public void setPressButton(boolean isPressButton) {
		this.isPressButton = isPressButton;
	}

	public void veMenu() {
		try {
			ManagerSound.getInstance().stop("nhacNenMegamanX");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
		controller.getGameFrame().setVisible(false);
		try {
			new GameFrame();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

}
