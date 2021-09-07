package enemieChicken;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;
import whenDeath.WhenDeath;

public class WhenIntroEnemieRobotChickenDeath extends WhenDeath {

	private Random random;

	private Animation animation1, animation2, animation3, animation4;

	private Animation remove;

	public WhenIntroEnemieRobotChickenDeath(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f);

		remove = new Animation(getGame().getInputData().getListAnimation().get("bigBangLarge"));
		remove.setIsRepeat(false);

		random = new Random();

		float[] arraySpeedX = { -2, -1, 0, 1, 2 };
		float[] arraySpeedY = { -1, -2, -3 };

		getListSpeedX().add(arraySpeedX[random.nextInt(arraySpeedX.length)]);
		getListSpeedX().add(arraySpeedX[random.nextInt(arraySpeedX.length)]);
		getListSpeedX().add(arraySpeedX[random.nextInt(arraySpeedX.length)]);
		getListSpeedX().add(arraySpeedX[random.nextInt(arraySpeedX.length)]);

		getListSpeedY().add(arraySpeedY[random.nextInt(arraySpeedY.length)]);
		getListSpeedY().add(arraySpeedY[random.nextInt(arraySpeedY.length)]);
		getListSpeedY().add(arraySpeedY[random.nextInt(arraySpeedY.length)]);
		getListSpeedY().add(arraySpeedY[random.nextInt(arraySpeedY.length)]);

		Rectangle rect1 = new Rectangle((int) x, (int) y, 30, 32);
		Rectangle rect2 = new Rectangle((int) x, (int) y, 16, 24);
		Rectangle rect3 = new Rectangle((int) x, (int) y, 38, 16);
		Rectangle rect4 = new Rectangle((int) x, (int) y, 14, 26);

		getListRectangle().add(rect1);
		getListRectangle().add(rect2);
		getListRectangle().add(rect3);
		getListRectangle().add(rect4);

		getListIgnoreRect().add(false);
		getListIgnoreRect().add(false);
		getListIgnoreRect().add(false);
		getListIgnoreRect().add(false);

		getListIgnoreDrawRect().add(false);
		getListIgnoreDrawRect().add(false);
		getListIgnoreDrawRect().add(false);
		getListIgnoreDrawRect().add(false);

		getListStage().put(0, ParticulerObjectMegamanX.ALIVE);
		getListStage().put(1, ParticulerObjectMegamanX.ALIVE);
		getListStage().put(2, ParticulerObjectMegamanX.ALIVE);
		getListStage().put(3, ParticulerObjectMegamanX.ALIVE);

		animation1 = new Animation(
				getGame().getInputData().getListAnimation().get("introEnemieRobotChickenWhenDeath1"));
		animation2 = new Animation(
				getGame().getInputData().getListAnimation().get("introEnemieRobotChickenWhenDeath2"));
		animation3 = new Animation(
				getGame().getInputData().getListAnimation().get("introEnemieRobotChickenWhenDeath3"));
		animation4 = new Animation(
				getGame().getInputData().getListAnimation().get("introEnemieRobotChickenWhenDeath4"));
	}

	@Override
	public void upload() {
		super.upload();
	}

	@Override
	public void draw(Graphics2D g2) {
		if (!remove.isLastFrame()) {
			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - 30 - getGame().getCamera().getX()),
					(int) (getY() - 40 - getGame().getCamera().getY()));
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
		if (!getListIgnoreDrawRect().get(2)) {
			if (getListStage().get(2) == ParticulerObjectMegamanX.FEY && System.nanoTime() % 3 == 2) {
			} else {
				animation3.upload(System.nanoTime());
				animation3.draw(g2, (int) (getListRectangle().get(2).getX() - getGame().getCamera().getX()),
						(int) (getListRectangle().get(0).getY() - getGame().getCamera().getY()));
			}
		}
		if (!getListIgnoreDrawRect().get(3)) {
			if (getListStage().get(3) == ParticulerObjectMegamanX.FEY && System.nanoTime() % 3 == 2) {
			} else {
				animation4.upload(System.nanoTime());
				animation4.draw(g2, (int) (getListRectangle().get(3).getX() - getGame().getCamera().getX()),
						(int) (getListRectangle().get(0).getY() - getGame().getCamera().getY()));
			}
		}
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
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

	public Animation getAnimation3() {
		return animation3;
	}

	public void setAnimation3(Animation animation3) {
		this.animation3 = animation3;
	}

	public Animation getAnimation4() {
		return animation4;
	}

	public void setAnimation4(Animation animation4) {
		this.animation4 = animation4;
	}

	public Animation getRemove() {
		return remove;
	}

	public void setRemove(Animation remove) {
		this.remove = remove;
	}

}
