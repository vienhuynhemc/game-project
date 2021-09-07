package metalAnchors;

import java.awt.Graphics2D;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import whenDeath.WhenDeath;

public class WhenMetalAnchorsDeath extends WhenDeath {

	private Animation remove;

	public WhenMetalAnchorsDeath(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f);

		remove = new Animation(getGame().getInputData().getListAnimation().get("bigBangLarge"));
		remove.setIsRepeat(false);
	}

	@Override
	public void upload() {
		if (remove.isLastFrame()) {
			setComplete(true);
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (!remove.isLastFrame()) {
			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - 50 - getGame().getCamera().getY()));
		}
	}

}
