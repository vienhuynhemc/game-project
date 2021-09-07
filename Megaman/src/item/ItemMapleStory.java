package item;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameObject.GameObjectMapleStory;
import gameWorld.GameWorldMapleStory;
import particulerObject.ParticulerObjectMapleStory;

public abstract class ItemMapleStory extends GameObjectMapleStory {

	private int width;
	private int height;
	private float mass;
	private float speedY;

	private boolean isComplete;

	public int stage;

	private int timeForAlive;
	private int timeForFey;

	private long timeStart;

	public ItemMapleStory(float x, float y, GameWorldMapleStory game, int width, int height, float mass, float speedY) {
		super(x, y, game);
		this.width = width;
		this.height = height;
		this.mass = mass;
		this.speedY = speedY;

		setStage(ParticulerObjectMapleStory.ALIVE);

		timeForAlive = 10000;
		timeForFey = 3000;
	}

	public void upload() {
		if (getStage() == ParticulerObjectMapleStory.ALIVE) {
			if (System.currentTimeMillis() - timeStart > timeForAlive) {
				timeStart = System.currentTimeMillis();
				setStage(ParticulerObjectMapleStory.FEY);
			}
		} else if (getStage() == ParticulerObjectMapleStory.FEY) {
			if (System.currentTimeMillis() - timeStart > timeForFey) {
				isComplete = true;
			}
		}

		Rectangle rect = getGame().getPhysicalMap().vaChamMatDat(getRectangleObject());
		if (rect != null) {
			setSpeedY(0);
			setY(rect.y - getHeight() + 1);
		} else {
			setY(getY() + getSpeedY());
			setSpeedY(getSpeedY() + getMass());
		}
	}

	public boolean isOutCamera() {
		if (getGame().getCamera().getX() + getGame().getCamera().getWidth() < getX())
			return true;
		if (getX() + getWidth() < getGame().getCamera().getX())
			return true;
		if (getGame().getCamera().getY() > getY() + getHeight())
			return true;
		if (getGame().getCamera().getY() + getGame().getCamera().getHeight() < getY())
			return true;
		return false;
	}

	public Rectangle getRectangleObject() {
		return new Rectangle((int) getX(), (int) getY(), getWidth(), getHeight());
	}

	public abstract Rectangle getRectangleXuLiVaCham();

	public abstract void drawRectangleXuLiVaCham(Graphics2D g2);

	public abstract void draw(Graphics2D g2);

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

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getTimeForAlive() {
		return timeForAlive;
	}

	public void setTimeForAlive(int timeForAlive) {
		this.timeForAlive = timeForAlive;
	}

	public int getTimeForFey() {
		return timeForFey;
	}

	public void setTimeForFey(int timeForFey) {
		this.timeForFey = timeForFey;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

}
