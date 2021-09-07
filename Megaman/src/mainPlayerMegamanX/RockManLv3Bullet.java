package mainPlayerMegamanX;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import objectBullet.BulletMegamanX;

public class RockManLv3Bullet extends BulletMegamanX {

	private Animation banLeft, banRight, removeLeft, removeRight;

	public RockManLv3Bullet(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f, 122, 60, 1000, 120);
		// dame cũ là 60

		banLeft = new Animation(getGame().getInputData().getListAnimation().get("bulletRockManLv3"));
		banRight = new Animation(getGame().getInputData().getListAnimation().get("bulletRockManLv3"));
		banLeft.daoNguoc();
		removeLeft = new Animation(getGame().getInputData().getListAnimation().get("bulletRockManLv3Remove"));
		removeRight = new Animation(getGame().getInputData().getListAnimation().get("bulletRockManLv3Remove"));
		removeLeft.daoNguoc();

		if (getGame().getRockMan().getDirector() == DIR_LEFT) {
			if (getGame().getRockMan().isWallCling()) {
				setSpeedX(7);
				setDirector(DIR_RIGHT);
			} else if (!getGame().getRockMan().isWallCling()) {
				setSpeedX(-7);
				setDirector(DIR_LEFT);
			}
		} else if (getGame().getRockMan().getDirector() == DIR_RIGHT) {
			if (getGame().getRockMan().isWallCling()) {
				setSpeedX(-7);
				setDirector(DIR_LEFT);
			} else if (!getGame().getRockMan().isWallCling()) {
				setSpeedX(7);
				setDirector(DIR_RIGHT);
			}
		}

		setTeam(LEGUE_TEAM);
	}

	@Override
	public void upload() {
		super.upload();
		if (isTrung()) {
			setDame(0);
			setSpeedX(0);
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (isChamTuong()) {

			setDame(0);
			setSpeedX(0);

			if (getDirector() == DIR_RIGHT) {
				removeRight.upload(System.nanoTime());
				removeRight.draw(g2, (int) (getX() - getGame().getCamera().getX() + 80),
						(int) (getY() - getGame().getCamera().getY()));

				if (removeRight.isLastFrame())
					setStage(DEATH);
			} else {
				removeLeft.upload(System.nanoTime());
				removeLeft.draw(g2, (int) (getX() - getGame().getCamera().getX() - 80),
						(int) (getY() - getGame().getCamera().getY()));

				if (removeLeft.isLastFrame())
					setStage(DEATH);
			}
		} else if (isTrung()) {
			if (getDirector() == DIR_RIGHT) {
				removeRight.upload(System.nanoTime());
				removeRight.draw(g2, (int) (getX() - getGame().getCamera().getX() + 80),
						(int) (getY() - getGame().getCamera().getY()));

				if (removeRight.isLastFrame())
					setStage(DEATH);
			} else {
				removeLeft.upload(System.nanoTime());
				removeLeft.draw(g2, (int) (getX() - getGame().getCamera().getX() - 80),
						(int) (getY() - getGame().getCamera().getY()));

				if (removeLeft.isLastFrame())
					setStage(DEATH);
			}
		} else {
			if (getSpeedX() > 0) {
				banRight.upload(System.nanoTime());
				banRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else {
				banLeft.upload(System.nanoTime());
				banLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 10, rect.y + 5, rect.width - 20, rect.height - 10);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
	}

	public Animation getBanLeft() {
		return banLeft;
	}

	public void setBanLeft(Animation banLeft) {
		this.banLeft = banLeft;
	}

	public Animation getBanRight() {
		return banRight;
	}

	public void setBanRight(Animation banRight) {
		this.banRight = banRight;
	}

	public Animation getRemoveLeft() {
		return removeLeft;
	}

	public void setRemoveLeft(Animation removeLeft) {
		this.removeLeft = removeLeft;
	}

	public Animation getRemoveRight() {
		return removeRight;
	}

	public void setRemoveRight(Animation removeRight) {
		this.removeRight = removeRight;
	}

}