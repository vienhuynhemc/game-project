package gameObject;

public abstract class GameObject {

	private int floatX;
	private int floatY;
	private GameWorld game;

	public GameObject(int floatX, int floatY, GameWorld game) {
		this.floatX = floatX;
		this.floatY = floatY;
		this.game = game;
	}

	public abstract void upload();

	public int getFloatX() {
		return floatX;
	}

	public void setFloatX(int floatX) {
		this.floatX = floatX;
	}

	public int getFloatY() {
		return floatY;
	}

	public void setFloatY(int floatY) {
		this.floatY = floatY;
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

}
