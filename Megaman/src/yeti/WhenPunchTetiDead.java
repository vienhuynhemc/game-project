package yeti;

import java.awt.Graphics2D;
import java.io.IOException;

import effortMapleStory.EffortMapleStory;
import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;

public class WhenPunchTetiDead extends EffortMapleStory {

	private Animation anime;

	public WhenPunchTetiDead(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);

		anime = new Animation(getGame().getInputData().getListAnimation().get("byt"));
	}

	@Override
	public void upload() throws IOException {
		anime.upload(System.nanoTime());
		if (anime.isLastFrame())
			setComplete(true);
	}

	@Override
	public void draw(Graphics2D g2) {
		anime.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
	}

	public Animation getAnime() {
		return anime;
	}

	public void setAnime(Animation anime) {
		this.anime = anime;
	}

}
