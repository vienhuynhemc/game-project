package gameObject;

import gameWorld.GameWorld;

public abstract class GameObject{

	private float x;
	private float y;
	private GameWorld game;

	public GameObject(float x, float y, GameWorld game) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

}
