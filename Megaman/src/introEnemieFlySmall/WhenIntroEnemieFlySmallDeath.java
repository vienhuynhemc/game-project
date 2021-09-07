package introEnemieFlySmall;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;
import whenDeath.WhenDeath;

public class WhenIntroEnemieFlySmallDeath extends WhenDeath {

	private Animation animation1, animation2;

	private Animation remove;

	private Random random;

	public WhenIntroEnemieFlySmallDeath(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f);

		remove = new Animation(getGame().getInputData().getListAnimation().get("bigBangLarge"));
		remove.setIsRepeat(false);

		random = new Random();

		float[] arraySpeedX = { -2, -1, 0, 1, 2 };
		float[] arraySpeedY = { -1, -2, -3 };

		Rectangle rect1 = new Rectangle((int) x, (int) y, 58, 16);
		Rectangle rect2 = new Rectangle((int) x, (int) y, 50, 46);

		getListIgnoreRect().add(false);
		getListIgnoreRect().add(false);

		getListIgnoreDrawRect().add(false);
		getListIgnoreDrawRect().add(false);

		animation1 = new Animation(getGame().getInputData().getListAnimation().get("introEnemieFlySmallThanTren"));
		animation2 = new Animation(getGame().getInputData().getListAnimation().get("introEnemieFlySmallThanDuoi"));

		getListRectangle().add(rect1);
		getListRectangle().add(rect2);

		getListSpeedX().add(arraySpeedX[random.nextInt(arraySpeedX.length)]);
		getListSpeedX().add(arraySpeedX[random.nextInt(arraySpeedX.length)]);

		getListSpeedY().add(arraySpeedY[random.nextInt(arraySpeedY.length)]);
		getListSpeedY().add(arraySpeedY[random.nextInt(arraySpeedY.length)]);

		getListStage().put(0, ParticulerObjectMegamanX.ALIVE);
		getListStage().put(1, ParticulerObjectMegamanX.ALIVE);
	}

	@Override
	public void upload() {
		super.upload();
	}

	@Override
	public void draw(Graphics2D g2) {
		if (!remove.isLastFrame()) {
			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - 50 - getGame().getCamera().getY()));
		}

		if (!getListIgnoreDrawRect().get(0)) {
			if (getListStage().get(0) == ParticulerObjectMegamanX.FEY && System.nanoTime() % 3 == 2) {
			} else {
				animation1.upload(System.nanoTime());
				animation1.draw(g2, (int) (getListRectangle().get(0).getX() - getGame().getCamera().getX()),
						(int) (getListRectangle().get(0).getY() - getGame().getCamera().getY()));
			}
		}
		if (!getListIgnoreDrawRect().get(1)) {
			if (getListStage().get(1) == ParticulerObjectMegamanX.FEY && System.nanoTime() % 3 == 2) {
			} else {
				animation2.upload(System.nanoTime());
				animation2.draw(g2, (int) (getListRectangle().get(1).getX() - getGame().getCamera().getX()),
						(int) (getListRectangle().get(0).getY() - getGame().getCamera().getY()));
			}
		}
	}

	public Animation getAnimation1() {
		return animation1;
	}

	public void setAnimation1(Animation animation1) {
		this.animation1 = animation1;
	}

	public Animation getAnimation2() {
		return animation2;
	}

	public void setAnimation2(Animation animation2) {
		this.animation2 = animation2;
	}

	public Animation getRemove() {
		return remove;
	}

	public void setRemove(Animation remove) {
		this.remove = remove;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

}
