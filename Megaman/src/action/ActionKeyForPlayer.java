package action;

import gameWorld.GameWorld;

public abstract class ActionKeyForPlayer implements ActionForPlayerMethod {

	private GameWorld mainGame;

	public boolean isLock;

	private boolean isPressLeft;
	private boolean isPressRight;
	private boolean isPressUp;
	private boolean isPressDown;

	public ActionKeyForPlayer(GameWorld game) {
		this.mainGame = game;
	}

	@Override
	public void unlock() {
		isLock = false;
	}

	@Override
	public void lock() {
		isLock = true;
	}

	public GameWorld getMainGame() {
		return mainGame;
	}

	public void setMainGame(GameWorld mainGame) {
		this.mainGame = mainGame;
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

	public boolean isPressLeft() {
		return isPressLeft;
	}

	public void setPressLeft(boolean isPressLeft) {
		this.isPressLeft = isPressLeft;
	}

	public boolean isPressRight() {
		return isPressRight;
	}

	public void setPressRight(boolean isPressRight) {
		this.isPressRight = isPressRight;
	}

	public boolean isPressUp() {
		return isPressUp;
	}

	public void setPressUp(boolean isPressUp) {
		this.isPressUp = isPressUp;
	}

	public boolean isPressDown() {
		return isPressDown;
	}

	public void setPressDown(boolean isPressDown) {
		this.isPressDown = isPressDown;
	}

}
