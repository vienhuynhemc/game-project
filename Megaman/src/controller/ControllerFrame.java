package controller;

import java.io.IOException;

import model.ModelFrame;
import view.GameFrame;

public class ControllerFrame {

	private GameFrame gameFrame;
	private ModelFrame modelFrame;

	public ControllerFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		modelFrame = new ModelFrame(this);
	}

	public void veMenu() throws IOException {
		modelFrame.veMenu();
	}

	public void init() throws IOException {
		modelFrame.init();
	}

	public void switchPanel() throws IOException {
		modelFrame.switchPanel();
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public ModelFrame getModelFrame() {
		return modelFrame;
	}

}
