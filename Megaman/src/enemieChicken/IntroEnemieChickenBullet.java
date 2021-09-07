package enemieChicken;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import objectBullet.BulletMegamanX;

public class IntroEnemieChickenBullet extends BulletMegamanX {

	private Animation danLeft, danRight;;
	private Animation remove;

	private int directorRun;

	public IntroEnemieChickenBullet(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0f, 46, 14, 10, 5);

		setDirector(director);

		danLeft = new Animation(getGame().getInputData().getListAnimation().get("introEnemieRobotChickenBullet"));
		danRight = new Animation(getGame().getInputData().getListAnimation().get("introEnemieRobotChickenBullet"));
		danRight.daoNguoc();
		remove = new Animation(getGame().getInputData().getListAnimation().get("bigBangSmall"));

		setTeam(ENEMI_TEAM);
	}

	@Override
	public void upload() {
		super.upload();

		if (isTrung()) {
			setDame(0);
			setSpeedX(0);
			setSpeedY(0);
		}

		run();

		for (int i = 0; i < getGame().getListBullet().getListObject().size(); i++) {
			if (getGame().getListBullet().getListObject().get(i).getTeam() == LEGUE_TEAM) {
				if (getGame().getListBullet().getListObject().get(i).getRectangleXuLiVaCham()
						.intersects(getRectangleXuLiVaCham())) {
					if (getGame().getListBullet().getListObject().get(i).getDame() <= getBlood()) {
						getGame().getListBullet().getListObject().get(i).setStage(DEATH);
						setBlood(getBlood() - getGame().getListBullet().getListObject().get(i).getDame());
					}
				}
			}
		}

		if (getBlood() <= 0) {
			setTrung(true);
		}

	}

	public void run() {

		if (getY() > (getGame().getRockMan().getY() + getGame().getRockMan().getHeight() / 2)) {
			setDirectorRun(DIR_RIGHT);
		} else {
			setDirectorRun(DIR_LEFT);
		}

		if (getDirectorRun() == DIR_LEFT) {
			setSpeedY(1);
		} else {
			setSpeedY(-1);
		}

		setX(getX() + getSpeedX());
		setY(getY() + getSpeedY());
	}

	@Override
	public void draw(Graphics2D g2) {

		if (isChamTuong()) {

			setDame(0);
			setSpeedX(0);
			setSpeedY(0);

			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - 60 - getGame().getCamera().getY()));

			if (remove.isLastFrame())
				setStage(DEATH);
		} else if (isTrung()) {

			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - 60 - getGame().getCamera().getY()));

			if (remove.isLastFrame())
				setStage(DEATH);
		} else {
			if (getDirector() == DIR_LEFT) {
				drawAndUploadHanhDong(g2, danLeft);
			} else if (getDirector() == DIR_RIGHT) {
				drawAndUploadHanhDong(g2, danRight);
			}
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

	public Animation getDanLeft() {
		return danLeft;
	}

	public void setDanLeft(Animation danLeft) {
		this.danLeft = danLeft;
	}

	public Animation getDanRight() {
		return danRight;
	}

	public void setDanRight(Animation danRight) {
		this.danRight = danRight;
	}

	public Animation getRemove() {
		return remove;
	}

	public void setRemove(Animation remove) {
		this.remove = remove;
	}

	public int getDirectorRun() {
		return directorRun;
	}

	public void setDirectorRun(int directorRun) {
		this.directorRun = directorRun;
	}

}
