package bossMapleStory;

import java.awt.Graphics2D;
import java.io.IOException;

import effortMapleStory.EffortMapleStory;
import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;

public class WhenInnerRageDead extends EffortMapleStory {

	private Animation anime;

	public WhenInnerRageDead(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);

		anime = new Animation(getGame().getInputData().getListAnimation().get("ird"));
		anime.setHeSo(1);
	}

	@Override
	public void upload() throws IOException {
		anime.upload(System.nanoTime());
		if (anime.isLastFrame())
			setComplete(true);
	}

	@Override
	public void draw(Graphics2D g2) {
		anime.draw(g2, (int) (getX() - 77 - getGame().getCamera().getX()),
				(int) (getY() - 25 - getGame().getCamera().getY()));
	}

	public Animation getAnime() {
		return anime;
	}

	public void setAnime(Animation anime) {
		this.anime = anime;
	}

}
