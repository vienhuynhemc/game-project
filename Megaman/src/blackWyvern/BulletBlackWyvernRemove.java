package blackWyvern;

import java.awt.Graphics2D;
import java.io.IOException;

import effortMapleStory.EffortMapleStory;
import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;

public class BulletBlackWyvernRemove extends EffortMapleStory {

	private Animation d;

	public BulletBlackWyvernRemove(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);

		d = new Animation(getGame().getInputData().getListAnimation().get("bwbr"));
	}

	@Override
	public void draw(Graphics2D g2) {
		d.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
	}

	@Override
	public void upload() throws IOException {
		d.upload(System.nanoTime());
		if (d.isLastFrame())
			setComplete(true);
	}

}
