package control;

import java.awt.event.KeyEvent;

import model.GameWorld;

public class Action {

	private GameWorld game;

	public Action(GameWorld game) {
		this.game = game;
	}

	public void keyPress(int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:
			game.up();
			break;

		case KeyEvent.VK_RIGHT:
			game.right();
			break;

		case KeyEvent.VK_LEFT:
			game.left();
			break;

		case KeyEvent.VK_DOWN:
			game.down();
			break;

		default:
			break;
		}
	}

}
