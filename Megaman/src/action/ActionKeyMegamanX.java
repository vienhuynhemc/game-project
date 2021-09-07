package action;

import java.awt.event.KeyEvent;

import gameWorld.GameWorld;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;

public class ActionKeyMegamanX extends ActionKeyForPlayer {

	public ActionKeyMegamanX(GameWorld game) {
		super(game);
	}

	public void keyPress(int key) {
		GameWorldMegamanX game = (GameWorldMegamanX) getMainGame();

		if (!isLock) {
			switch (key) {
			case KeyEvent.VK_LEFT:
				setPressLeft(true);
				if (!game.getRockMan().getIsDicking()) {
					game.getRockMan().setDirector(ParticulerObjectMegamanX.DIR_LEFT);
					game.getRockMan().run();
				}
				break;
			case KeyEvent.VK_RIGHT:
				setPressRight(true);
				if (!game.getRockMan().getIsDicking()) {
					game.getRockMan().setDirector(ParticulerObjectMegamanX.DIR_RIGHT);
					game.getRockMan().run();
				}
				break;
			case KeyEvent.VK_A:
				game.getRockMan().attack();
				break;
			case KeyEvent.VK_DOWN:
				setPressDown(true);
				if (game.getRockMan().getSpeedY() == 0) {
					game.getRockMan().dick();
				}
				break;
			case KeyEvent.VK_SPACE:
				if (!game.getRockMan().getIsDicking()) {
					game.getRockMan().jump();
				}
				break;
			case KeyEvent.VK_UP:
				setPressUp(true);
				game.getRockMan().up();
				break;
			default:
				break;
			}
		}

		if (game.getSTAGE() == GameWorldMegamanX.PLAYTUTORIAL
				|| game.getSTAGE() == GameWorldMegamanX.PLAYTUTORIALNOTRECTANGLE) {
			game.getListTutorial().get(0).skipTutorial();
		}
	}

	public void keyRelease(int key) {
		GameWorldMegamanX game = (GameWorldMegamanX) getMainGame();

		if (!isLock) {
			switch (key) {
			case KeyEvent.VK_LEFT:
				setPressLeft(false);
				if (game.getRockMan().getSpeedX() < 0)
					game.getRockMan().stopRun();
				break;
			case KeyEvent.VK_RIGHT:
				setPressRight(false);
				if (game.getRockMan().getSpeedX() > 0)
					game.getRockMan().stopRun();
				break;
			case KeyEvent.VK_A:
				game.getRockMan().stopAttack();
				break;
			case KeyEvent.VK_DOWN:
				setPressDown(false);
				game.getRockMan().stanUp();
				break;
			case KeyEvent.VK_UP:
				setPressUp(false);
				break;
			default:
				break;
			}
		}
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

}
