package gameObject;

import gameWorld.GameWorldMegamanX;

public abstract class GameObjectMegamanX extends GameObject {

	private GameWorldMegamanX game;

	public GameObjectMegamanX(float x, float y, GameWorldMegamanX game) {
		super(x, y, game);
		this.game = game;
	}

	public abstract void upload();

	@Override
	public GameWorldMegamanX getGame() {
		return game;
	}

	public void setGame(GameWorldMegamanX game) {
		this.game = game;
	}

}
