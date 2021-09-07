package bossMapleStory;

import java.awt.Graphics2D;
import java.io.IOException;

import effortMapleStory.EffortMapleStory;
import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;

public class ScratchEffort extends EffortMapleStory {

	private Animation anime;

	public ScratchEffort(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);

		anime = new Animation(getGame().getInputData().getListAnimation().get("ira2r"));
		anime.setHeSo(1f);
	}

	@Override
	public void draw(Graphics2D g2) {
		anime.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
	}

	@Override
	public void upload() throws IOException {
		anime.upload(System.nanoTime());
		if (anime.isLastFrame()) {
			setComplete(true);
		}
	}

}
