package mainPlayerMegamanX;

import java.awt.Graphics2D;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import whenDeath.WhenDeath;

public class WhenRockManDeath extends WhenDeath {

	private Animation dead;
	private Animation remove;

	public WhenRockManDeath(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0f);

		dead = new Animation(getGame().getInputData().getListAnimation().get("whenRockManDeath"));
		remove = new Animation(getGame().getInputData().getListAnimation().get("bigBangLarge"));
		remove.setIsRepeat(false);
	}

	@Override
	public void upload() {
	}

	@Override
	public void draw(Graphics2D g2) {

		if (!remove.isLastFrame()) {
			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - 30 - getGame().getCamera().getX()),
					(int) (getY() - 40 - getGame().getCamera().getY()));
		}

		dead.upload(System.nanoTime());

		if (dead.getCurrentFrame() == 1) {
			dead.draw(g2, (int) (getX() + 8 - getGame().getCamera().getX()),
					(int) (getY() + 8 - getGame().getCamera().getY()));
		} else if (dead.getCurrentFrame() >= 5 && dead.getCurrentFrame() <= 9) {
			dead.draw(g2, (int) (getX() + 12 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else if (dead.getCurrentFrame() == 10) {
			dead.draw(g2, (int) (getX() + 12 - getGame().getCamera().getX()),
					(int) (getY() + 6 - getGame().getCamera().getY()));
		} else if (dead.getCurrentFrame() >= 11 && dead.getCurrentFrame() <= 13) {
			dead.draw(g2, (int) (getX() + 20 - getGame().getCamera().getX()),
					(int) (getY() + 14 - getGame().getCamera().getY()));
		} else {
			dead.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
		}

		if (dead.isLastFrame()) {
			setComplete(true);
		}
	}

	public Animation getDead() {
		return dead;
	}

	public void setDead(Animation dead) {
		this.dead = dead;
	}

	public Animation getRemove() {
		return remove;
	}

	public void setRemove(Animation remove) {
		this.remove = remove;
	}

}
