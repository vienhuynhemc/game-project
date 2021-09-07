package robotWallCling;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import objectBullet.BulletMegamanX;

public class RobotWallClingBullet extends BulletMegamanX {

	private Animation bullet;

	private int directorRun;

	private long timeReturn;
	private long timeStart;

	private boolean isComplete;

	public RobotWallClingBullet(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f, 14, 14, 1000, 5);

		bullet = new Animation(getGame().getInputData().getListAnimation().get("bulletRobotWallCling"));

		timeReturn = 2000;
		timeStart = System.currentTimeMillis();

		setTeam(ENEMI_TEAM);
	}

	@Override
	public void upload() {
		super.upload();

		if (directorRun == DIR_LEFT) {
			setSpeedY(1);
		} else {
			setSpeedY(-1);
		}

		if (getDirector() == DIR_LEFT) {
			setSpeedX(-2);
		} else {
			setSpeedX(2);
		}

		if (System.currentTimeMillis() - timeStart > timeReturn) {
			if (!isComplete) {
				if (directorRun == DIR_LEFT) {
					setDirectorRun(DIR_RIGHT);
				} else {
					setDirectorRun(DIR_LEFT);
				}
				if (getDirector() == DIR_LEFT) {
					setDirector(DIR_RIGHT);
				} else {
					setDirector(DIR_LEFT);
				}
				timeStart = System.currentTimeMillis();
				isComplete = true;
			} else {
				setStage(DEATH);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		bullet.upload(System.nanoTime());
		if (bullet.getCurrentFrame() == 1) {
			bullet.draw(g2, (int) (getX() - 16 - getGame().getCamera().getX()),
					(int) (getY() - 4 - getGame().getCamera().getY()));
		} else if (bullet.getCurrentFrame() == 2) {
			bullet.draw(g2, (int) (getX() - 4 - getGame().getCamera().getX()),
					(int) (getY() - 16 - getGame().getCamera().getY()));
		} else if (bullet.getCurrentFrame() == 3) {
			bullet.draw(g2, (int) (getX() - 8 - getGame().getCamera().getX()),
					(int) (getY() - 8 - getGame().getCamera().getY()));
		} else {
			bullet.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();

		if (bullet.getCurrentFrame() == 0) {
			return rect;
		} else if (bullet.getCurrentFrame() == 1) {
			Rectangle r = new Rectangle(rect.x - 4, rect.y - 4, rect.width + 8, rect.height + 8);
			return r;
		} else if (bullet.getCurrentFrame() == 2) {
			Rectangle r = new Rectangle(rect.x - 4, rect.y - 4, rect.width + 8, rect.height + 8);
			return r;
		} else if (bullet.getCurrentFrame() == 3) {
			Rectangle r = new Rectangle(rect.x - 2, rect.y - 2, rect.width + 4, rect.height + 4);
			return r;
		}

		return rect;
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle r = getRectangleXuLiVaCham();

		g2.setColor(Color.black);
		g2.drawRect((int) (r.x - getGame().getCamera().getX()), (int) (r.y - getGame().getCamera().getY()), r.width,
				r.height);
	}

	@Override
	public void getToDeath() {
	}

	public Animation getBullet() {
		return bullet;
	}

	public void setBullet(Animation bullet) {
		this.bullet = bullet;
	}

	public int getDirectorRun() {
		return directorRun;
	}

	public void setDirectorRun(int directorRun) {
		this.directorRun = directorRun;
	}

	public long getTimeReturn() {
		return timeReturn;
	}

	public void setTimeReturn(long timeReturn) {
		this.timeReturn = timeReturn;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
