package physicalMap;

import java.awt.Rectangle;
import java.io.IOException;

import gameWorld.GameWorldMapleStory;

public class PhysicalMapMapleStory extends PhysicalMap {

	private GameWorldMapleStory game;

	public PhysicalMapMapleStory(float x, float y, GameWorldMapleStory gameWorld) throws IOException {
		super(x, y, gameWorld);

		this.game = gameWorld;
		setArray(getGame().getInputData().getPhysicalMap());
		setSize(30);
	}

	public GameWorldMapleStory getGame() {
		return game;
	}

	public void setGame(GameWorldMapleStory game) {
		this.game = game;
	}

	@Override
	public void upload() throws IOException {
		if (!getGame().getMario().isLeoCauThang()) {
			Rectangle rect = game.getMario().getRectangleSizeObject();

			int[][] arrayPhysicalMapMapleStory = getArray();
			int size = getSize();

			int posX1 = rect.x / size - 4;
			int posX2 = (rect.x + rect.width) / size + 4;
			int posY1 = (rect.y + rect.height) / size - 4;
			int posY2 = posY1 + 8;

			if (posX1 < 0)
				posX1 = 0;
			if (posY1 < 0)
				posY1 = 0;
			if (posX2 > arrayPhysicalMapMapleStory[0].length - 1)
				posX2 = arrayPhysicalMapMapleStory[0].length - 1;
			if (posY2 > arrayPhysicalMapMapleStory.length - 1)
				posY2 = arrayPhysicalMapMapleStory.length - 1;
			for (int i = posY1; i <= posY2; i++) {
				for (int j = posX1; j <= posX2; j++) {
					if (arrayPhysicalMapMapleStory[i][j] == 1) {
						Rectangle r = new Rectangle((int) (j * size + getX()), (int) (i * size + getY()), size, size);
						if (r.y + r.height < getGame().getMario().getY() + rect.height) {
							arrayPhysicalMapMapleStory[i][j] = 2;
						}
					}
					if (arrayPhysicalMapMapleStory[i][j] == 2) {
						Rectangle r = new Rectangle((int) (j * size + getX()), (int) (i * size + getY()), size, size);
						if (r.y > getGame().getMario().getY() + rect.height) {
							arrayPhysicalMapMapleStory[i][j] = 1;
						} else if (r.x > getGame().getMario().getX() + rect.width + 50
								|| r.x < getGame().getMario().getX() - 50) {
							arrayPhysicalMapMapleStory[i][j] = 1;
						}
					}
				}
			}
		}
	}

}