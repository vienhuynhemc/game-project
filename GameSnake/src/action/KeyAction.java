package action;

import java.awt.event.KeyEvent;

import conRan.Snake;
import gameObject.GameWorld;

public class KeyAction {

	private GameWorld game;

	public KeyAction(GameWorld game) {
		this.game = game;
	}

	public void keyPress(KeyEvent e) {
		if (!game.isClick()) {
			game.setClick(true);
			game.setTimStartClick(System.nanoTime());
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				if (getGame().getSnake().getDircetion() != Snake.DIR_DOWN) {
					getGame().getSnake().setDircetion(Snake.DIR_UP);
				}
				break;
			case KeyEvent.VK_DOWN:
				if (getGame().getSnake().getDircetion() != Snake.DIR_UP) {
					getGame().getSnake().setDircetion(Snake.DIR_DOWN);
				}
				break;
			case KeyEvent.VK_LEFT:
				if (getGame().getSnake().getDircetion() != Snake.DIR_RIGHT) {
					getGame().getSnake().setDircetion(Snake.DIR_LEFT);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (getGame().getSnake().getDircetion() != Snake.DIR_LEFT) {
					getGame().getSnake().setDircetion(Snake.DIR_RIGHT);
				}
				break;

			default:
				break;
			}
		}
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

}
