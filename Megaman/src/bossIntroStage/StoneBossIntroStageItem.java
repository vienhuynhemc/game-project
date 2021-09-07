package bossIntroStage;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;

public class StoneBossIntroStageItem extends GameObjectMegamanX {

	private Animation anime;

	private int width;
	private int height;

	private float speedX;
	private float speedY;

	private float mass;

	private boolean isComplete;

	public StoneBossIntroStageItem(float x, float y, GameWorldMegamanX game, Animation anime, int width, int height,
			float speedX, float speedY, float mass) {
		super(x, y, game);
		this.width = width;
		this.height = height;

		this.speedX = speedX;
		this.speedY = speedY;

		this.mass = mass;

		this.anime = new Animation(anime);
	}

	@Override
	public void upload() {
		if (isOutCamera(getRectangleXuLyVaCham())) {
			isComplete = true;
		}

		setX(getX() + getSpeedX());
		setY(getY() + getSpeedY());

		setSpeedY(getSpeedY() + getMass());
	}

	public void draw(Graphics2D g2) {
		anime.upload(System.nanoTime());
		anime.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
	}

	public Rectangle getRectangleXuLyVaCham() {
		return new Rectangle((int) (getX()), (int) (getY()), width, height);
	}

	public boolean isOutCamera(Rectangle rect) {
		if (getGame().getCamera().getX() + getGame().getCamera().getWidth() < rect.getX())
			return true;
		if (rect.getX() + rect.getWidth() * 2 < getGame().getCamera().getX())
			return true;
		if (getGame().getCamera().getY() > rect.getY() + rect.getHeight() * 2)
			return true;
		if (getGame().getCamera().getY() + getGame().getCamera().getHeight() < rect.getY())
			return true;
		return false;
	}

	public Animation getAnime() {
		return anime;
	}

	public void setAnime(Animation anime) {
		this.anime = anime;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

}
