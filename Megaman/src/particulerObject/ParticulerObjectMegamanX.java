package particulerObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import bossIntroStage.BossIntroStage;
import gameEffect.Animation;
import gameObject.Draw;
import gameObject.GameObjectMegamanX;
import gameObject.Upload;
import gameWorld.GameWorldMegamanX;
import subBossIntroStage.SubBossIntroStage;

public abstract class ParticulerObjectMegamanX extends GameObjectMegamanX implements Upload, Draw {

	private int director;
	public static final int DIR_LEFT = 0;
	public static final int DIR_RIGHT = 1;

	private int stage;
	public static final int ALIVE = 0;
	public static final int BEHURT = 1;
	public static final int NOBEHURT = 2;
	public static final int FEY = 3;
	public static final int DEATH = 4;
	public static final int SWITCHMAPLESTORY = 5;
	private long timeBeginBehurt;
	private long timeForBehurt;

	private int team;
	public static final int LEGUE_TEAM = 0;
	public static final int ENEMI_TEAM = 1;

	private float mass;
	private float width;
	private float height;
	private float speedX;
	private float speedY;

	private int blood;
	private int dame;

	private float xPrimaral;
	private float yPrimaral;
	private int directorPrimaral;
	private int bloodPrimaral;

	public ParticulerObjectMegamanX(float x, float y, GameWorldMegamanX game, float mass, float width, float height,
			int blood, int dame) {
		super(x, y, game);
		this.mass = mass;
		this.dame = dame;
		this.width = width;
		this.height = height;
		this.blood = blood;

		setTimeForBehurt(2000);
		setTimBeginBehurt(0);
	}

	public Rectangle getRectangleSizeObject() {
		Rectangle rect = new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
		return rect;
	}

	public void drawRectangleSizeObject(Graphics2D g2) {
		Rectangle rect = getRectangleSizeObject();
		g2.setColor(Color.green);
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	public boolean isOutCamera() {
		if (getGame().getCamera().getX() + getGame().getCamera().getWidth() < getX())
			return true;
		if (getX() + getWidth() * 2 < getGame().getCamera().getX())
			return true;
		if (getGame().getCamera().getY() > getY() + getHeight() * 2)
			return true;
		if (getGame().getCamera().getY() + getGame().getCamera().getHeight() < getY())
			return true;
		return false;
	}

	@Override
	public void upload() {
		if (timeBeginBehurt == 0)
			timeBeginBehurt = System.nanoTime();

		switch (stage) {
		case ALIVE:
			if (getBlood() <= 30) {
				setStage(FEY);
			}
			if (getBlood() <= 0) {
				getToDeath();
				if (this.getClass() != BossIntroStage.class) {
					setStage(DEATH);
				}
			}
			break;
		case BEHURT:

			break;
		case NOBEHURT:
			if (System.nanoTime() - timeBeginBehurt > timeForBehurt * 1000000) {
				if (getBlood() <= 30 && getBlood() > 0) {
					setStage(FEY);
				} else if (getBlood() > 30) {
					setStage(ALIVE);
				} else {
					getToDeath();
					setStage(DEATH);
				}
			}
			break;
		case FEY:
			if (getBlood() > 30) {
				setStage(ALIVE);
			}
			if (getBlood() <= 0) {
				getToDeath();
				if (this.getClass() != BossIntroStage.class && this.getClass() != SubBossIntroStage.class) {
					setStage(DEATH);
				}
			}
			break;
		case DEATH:
			break;

		default:
			break;
		}
	}

	public void drawAndUploadHanhDong(Graphics2D g2, Animation currentlyAnimation) {
		currentlyAnimation.upload(System.nanoTime());
		currentlyAnimation.draw(g2, (int) (getX() - getGame().getCamera().getX()),
				(int) (getY() - getGame().getCamera().getY()));
	}

	public void drawHanhDongRight(Graphics2D g2, Animation currentlyAnimation) {
		currentlyAnimation.draw(g2, (int) (getX() - getGame().getCamera().getX()),
				(int) (getY() - getGame().getCamera().getY()));
	}

	public void drawHanhDongLeft(Graphics2D g2, Animation currentlyAnimation) {
		currentlyAnimation.draw(g2, (int) (getX() - 20 - getGame().getCamera().getX()),
				(int) (getY() - getGame().getCamera().getY()));
	}

	public abstract Rectangle getRectangleXuLiVaCham();

	public abstract void drawRectangleXuLiVaCham(Graphics2D g2);

	public abstract void getToDeath();

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
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

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public long getTimBeginBehurt() {
		return timeBeginBehurt;
	}

	public void setTimBeginBehurt(long timBeginBehurt) {
		this.timeBeginBehurt = timBeginBehurt;
	}

	public long getTimeForBehurt() {
		return timeForBehurt;
	}

	public void setTimeForBehurt(long timeForBehurt) {
		this.timeForBehurt = timeForBehurt;
	}

	public int getDirector() {
		return director;
	}

	public void setDirector(int director) {
		this.director = director;
	}

	public int getDame() {
		return dame;
	}

	public void setDame(int dame) {
		this.dame = dame;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public long getTimeBeginBehurt() {
		return timeBeginBehurt;
	}

	public void setTimeBeginBehurt(long timeBeginBehurt) {
		this.timeBeginBehurt = timeBeginBehurt;
	}

	public float getxPrimaral() {
		return xPrimaral;
	}

	public void setxPrimaral(float xPrimaral) {
		this.xPrimaral = xPrimaral;
	}

	public float getyPrimaral() {
		return yPrimaral;
	}

	public void setyPrimaral(float yPrimaral) {
		this.yPrimaral = yPrimaral;
	}

	public int getDirectorPrimaral() {
		return directorPrimaral;
	}

	public void setDirectorPrimaral(int directorPrimaral) {
		this.directorPrimaral = directorPrimaral;
	}

	public int getBloodPrimaral() {
		return bloodPrimaral;
	}

	public void setBloodPrimaral(int bloodPrimaral) {
		this.bloodPrimaral = bloodPrimaral;
	}

	public static int getDirLeft() {
		return DIR_LEFT;
	}

	public static int getDirRight() {
		return DIR_RIGHT;
	}

	public static int getAlive() {
		return ALIVE;
	}

	public static int getBehurt() {
		return BEHURT;
	}

	public static int getNobehurt() {
		return NOBEHURT;
	}

	public static int getFey() {
		return FEY;
	}

	public static int getDeath() {
		return DEATH;
	}

	public static int getLegueTeam() {
		return LEGUE_TEAM;
	}

	public static int getEnemiTeam() {
		return ENEMI_TEAM;
	}

}
