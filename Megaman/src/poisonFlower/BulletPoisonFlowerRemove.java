package poisonFlower;

import java.awt.Graphics2D;
import java.io.IOException;

import effortMapleStory.EffortMapleStory;
import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;

public class BulletPoisonFlowerRemove extends EffortMapleStory {

	private Animation d;

	public BulletPoisonFlowerRemove(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);

		d = new Animation(getGame().getInputData().getListAnimation().get("pfbr"));
	}

	@Override
	public void draw(Graphics2D g2) {
		if (d.getCurrentFrame() == 0) {
			d.draw(g2, (int) (getX() - 50 - getGame().getCamera().getX()),
					(int) (getY() - 15 - getGame().getCamera().getY()));
		} else {
			d.draw(g2, (int) (getX() - 15 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		}
	}

	@Override
	public void upload() throws IOException {
		d.upload(System.nanoTime());
		if (d.getCurrentFrame() > 0) {
			setY(getY() + 5);
		}
		if (d.isLastFrame())
			setComplete(true);
	}

}
