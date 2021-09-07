package gameObject;

import gameWorld.GameWorldLoading;

public class GameObjectLoading extends GameObject {

	private GameWorldLoading game;

	public GameObjectLoading(float x, float y, GameWorldLoading game) {
		super(x, y, game);
		this.game = game;
	}

	public GameWorldLoading getGame() {
		return game;
	}

	public void setGame(GameWorldLoading game) {
		this.game = game;
	}

}
