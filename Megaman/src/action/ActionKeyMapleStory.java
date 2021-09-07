package action;

import java.awt.event.KeyEvent;

import gameWorld.GameWorld;
import gameWorld.GameWorldMapleStory;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMapleStory;
import particulerObject.ParticulerObjectMegamanX;

public class ActionKeyMapleStory extends ActionKeyForPlayer {

	public ActionKeyMapleStory(GameWorld mainGame) {
		super(mainGame);
	}

	public void keyPress(int key) {
		GameWorldMapleStory game = (GameWorldMapleStory) getMainGame();

		if (!isLock) {
			switch (key) {
			case KeyEvent.VK_LEFT:
				setPressLeft(true);
				if (!game.getMario().getIsDicking() && game.getMario().getStage() != ParticulerObjectMapleStory.DEATH) {
					game.getMario().setDirector(ParticulerObjectMegamanX.DIR_LEFT);
					game.getMario().run();
				}
				break;
			case KeyEvent.VK_RIGHT:
				setPressRight(true);
				if (!game.getMario().getIsDicking() && game.getMario().getStage() != ParticulerObjectMapleStory.DEATH) {
					game.getMario().setDirector(ParticulerObjectMegamanX.DIR_RIGHT);
					game.getMario().run();
				}
				break;
			case KeyEvent.VK_A:
				game.getMario().attack();
				break;
			case KeyEvent.VK_DOWN:
				setPressDown(true);
				if (game.getMario().getSpeedY() == 0) {
					game.getMario().dick();
				}
				break;
			case KeyEvent.VK_SPACE:
				if (!game.getMario().getIsDicking()) {
					game.getMario().jump();
				}
				break;
			case KeyEvent.VK_UP:
				setPressUp(true);
				game.getMario().up();
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
		GameWorldMapleStory game = (GameWorldMapleStory) getMainGame();
		if (!isLock) {
			switch (key) {
			case KeyEvent.VK_LEFT:
				setPressLeft(false);
				if (game.getMario().getSpeedX() < 0)
					game.getMario().stopRun();
				break;
			case KeyEvent.VK_RIGHT:
				setPressRight(false);
				if (game.getMario().getSpeedX() > 0)
					game.getMario().stopRun();
				break;
			case KeyEvent.VK_A:
				game.getMario().stopAttack();
				break;
			case KeyEvent.VK_DOWN:
				setPressDown(false);
				game.getMario().stanUp();
				break;
			case KeyEvent.VK_UP:
				setPressUp(false);
				break;
			default:
				break;
			}
		}
	}

	public void lock() {
		isLock = true;
	}

	public void unlock() {
		isLock = false;
	}

}
