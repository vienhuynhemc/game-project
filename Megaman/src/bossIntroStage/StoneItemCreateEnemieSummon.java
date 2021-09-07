package bossIntroStage;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;

public class StoneItemCreateEnemieSummon extends ParticulerObjectMegamanX {

	private Animation animation;
	private boolean isComplete;

	public StoneItemCreateEnemieSummon(float x, float y, GameWorldMegamanX game, int index, float xDen, float yDen) {
		super(x, y, game, 0f, 0, 0, 10, 0);

		switch (index) {
		case 0:
			animation = new Animation(getGame().getInputData().getListAnimation().get("itemStore1"));
			setWidth(84);
			setHeight(78);
			break;
		case 1:
			animation = new Animation(getGame().getInputData().getListAnimation().get("itemStore2"));
			setWidth(54);
			setHeight(55);

			break;
		case 2:
			animation = new Animation(getGame().getInputData().getListAnimation().get("itemStore3"));
			setWidth(54);
			setHeight(22);
			break;
		default:
			break;
		}

		if (xDen > getX()) {
			setDirector(DIR_RIGHT);
		} else {
			setDirector(DIR_LEFT);
		}

		setTeam(ENEMI_TEAM);

		initSpeed(xDen, yDen);
	}

	@Override
	public void draw(Graphics2D g2) {
		animation.upload(System.nanoTime());
		animation.draw(g2, (int) (getX() - getGame().getCamera().getX()),
				(int) (getY() - getGame().getCamera().getY()));
	}

	private void initSpeed(float x, float y) {
		float spaceX = x - getX();
		float spaceY = y - getY();

		float boiSo = spaceX / 4;

		if (getDirector() == DIR_RIGHT) {
			setSpeedX(4);
		} else if (getDirector() == DIR_LEFT) {
			setSpeedX(-4);
		}

		while (boiSo > 40 || boiSo < -40) {
			boiSo /= 2;
			if (getDirector() == DIR_RIGHT) {
				setSpeedX(getSpeedX() * 2);
			} else if (getDirector() == DIR_LEFT) {
				setSpeedX(getSpeedX() * 2);
			}
		}

		float speedY = spaceY / boiSo;
		if (speedY > 0) {
			speedY *= -1;
		}

		if (speedY == 0) {
			speedY = -5;
		}

		setSpeedY(speedY);
	}

	@Override
	public void upload() {
		if (!isComplete) {
			setX(getX() + getSpeedX());
			setY(getY() + getSpeedY());
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		return getRectangleSizeObject();
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		drawRectangleSizeObject(g2);
	}

	@Override
	public void getToDeath() {
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
