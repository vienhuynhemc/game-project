package physicalMap;

import java.io.IOException;

import gameWorld.GameWorldMegamanX;

public class PhysicalMapMegamanX extends PhysicalMap {

	private GameWorldMegamanX game;

	public PhysicalMapMegamanX(float x, float y, GameWorldMegamanX gameWorld) throws IOException {
		super(x, y, gameWorld);

		this.game = gameWorld;
		setArray(getGame().getInputData().getPhysicalMap());
		setSize(30);
	}

	public GameWorldMegamanX getGame() {
		return game;
	}

	public void setGame(GameWorldMegamanX game) {
		this.game = game;
	}

	@Override
	public void upload() throws IOException {
	}

}
