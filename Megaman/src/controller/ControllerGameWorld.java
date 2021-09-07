package controller;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import model.ModelGameWorld;
import view.GamePanel;

public class ControllerGameWorld {

	private GamePanel game;
	private ModelGameWorld model;

	public ControllerGameWorld(GamePanel gamePanel) throws IOException {

		this.game = gamePanel;
		model = new ModelGameWorld(this);
	}

	public void upload() throws IOException {
		model.upload();
	}

	public void draw(Graphics2D g2) {
		model.draw(g2);
	}

	public void actionPress(KeyEvent e) {
		model.actionPress(e);
	}

	public void actionRelease(KeyEvent e) {
		model.actionRelease(e);
	}

	public GamePanel getGame() {
		return game;
	}

	public void setGame(GamePanel game) {
		this.game = game;
	}

	public ModelGameWorld getModel() {
		return model;
	}

	public void setModel(ModelGameWorld model) {
		this.model = model;
	}

}
