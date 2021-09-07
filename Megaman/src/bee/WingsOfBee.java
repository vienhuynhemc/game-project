package bee;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;

public class WingsOfBee extends ParticulerObjectMegamanX {

	private Animation animationLeft, animationRight;

	public WingsOfBee(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0f, 136, 62, 0, 1000);

		animationLeft = new Animation(getGame().getInputData().getListAnimation().get("wingsOfBee"));
		animationRight = new Animation(getGame().getInputData().getListAnimation().get("wingsOfBee"));
		animationRight.daoNguoc();
	}

	@Override
	public void upload() {
		if (getDirector() == DIR_LEFT) {
			animationLeft.upload(System.nanoTime());
		} else {
			animationRight.upload(System.nanoTime());
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (getDirector() == DIR_LEFT) {
			animationLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else {
			animationRight.draw(g2, (int) (getX() - 20 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
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

	public Animation getAnimationLeft() {
		return animationLeft;
	}

	public void setAnimationLeft(Animation animationLeft) {
		this.animationLeft = animationLeft;
	}

	public Animation getAnimationRight() {
		return animationRight;
	}

	public void setAnimationRight(Animation animationRight) {
		this.animationRight = animationRight;
	}

}
