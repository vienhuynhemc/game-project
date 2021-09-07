package zombieLupin;

import java.awt.Graphics2D;
import java.io.IOException;

import effortMapleStory.EffortMapleStory;
import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;

public class BulletZombieLupinRemove extends EffortMapleStory {

	private Animation d;

	public BulletZombieLupinRemove(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);

		d = new Animation(getGame().getInputData().getListAnimation().get("zlbr"));

	}

	@Override
	public void draw(Graphics2D g2) {
		d.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
	}

	@Override
	public void upload() throws IOException {
		d.upload(System.nanoTime());
		if (d.getCurrentFrame() > 1) {
			d.getListIgnoreImage().set(0, true);
			d.getListIgnoreImage().set(1, true);
			setY(getY() + 5);
		}

		if (getY() > getGame().getCamera().getY() + 700) {
			setComplete(true);
		}
	}

}
