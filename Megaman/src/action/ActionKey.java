package action;

import gameWorld.GameWorld;

public class ActionKey {

	private GameWorld mainGame;

	private ActionKeyMegamanX actionKeyForMegamanX;
	private ActionKeyMapleStory actionKeyForMapbleStory;

	public ActionKey(GameWorld game) {

		this.mainGame = game;
		if (mainGame.getName().equals("Megaman X")) {
			actionKeyForMegamanX = new ActionKeyMegamanX(mainGame);
		} else if (mainGame.getName().equals("Maple Story")) {
			actionKeyForMapbleStory = new ActionKeyMapleStory(mainGame);
		}

	}

	public void keyPress(int key) {
		if (mainGame.getName().equals("Megaman X")) {
			actionKeyForMegamanX.keyPress(key);
		} else if (mainGame.getName().equals("Maple Story")) {
			actionKeyForMapbleStory.keyPress(key);
		}
	}

	public void keyRelease(int key) {
		if (mainGame.getName().equals("Megaman X")) {
			actionKeyForMegamanX.keyRelease(key);
		} else if (mainGame.getName().equals("Maple Story")) {
			actionKeyForMapbleStory.keyRelease(key);
		}
	}

	public GameWorld getMainGame() {
		return mainGame;
	}

	public void setMainGame(GameWorld mainGame) {
		this.mainGame = mainGame;
	}

	public ActionKeyMegamanX getActionKeyForMegamanX() {
		return actionKeyForMegamanX;
	}

	public void setActionKeyForMegamanX(ActionKeyMegamanX actionKeyForMegamanX) {
		this.actionKeyForMegamanX = actionKeyForMegamanX;
	}

	public ActionKeyMapleStory getActionKeyForMapbleStory() {
		return actionKeyForMapbleStory;
	}

	public void setActionKeyForMapbleStory(ActionKeyMapleStory actionKeyForMapbleStory) {
		this.actionKeyForMapbleStory = actionKeyForMapbleStory;
	}

}
