package gameObject;

import gameWorld.GameWorldMapleStory;

public abstract class GameObjectMapleStory extends GameObject {

	private GameWorldMapleStory game;

	public GameObjectMapleStory(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);
		this.game = game;
	}

	@Override
	public GameWorldMapleStory getGame() {
		return game;
	}

	public void setGame(GameWorldMapleStory game) {
		this.game = game;
	}

}
