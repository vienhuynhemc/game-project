package mainPlayerMegamanX;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import objectBullet.BulletMegamanX;

public class BasicBullet extends BulletMegamanX {

	private Animation banLeft, banRight, remove;

	public BasicBullet(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f, 30, 16, 1000, 30);
		// dame cũ là 10

		banRight = new Animation(getGame().getInputData().getListAnimation().get("bulletBasic"));
		banLeft = new Animation(getGame().getInputData().getListAnimation().get("bulletBasic"));
		banLeft.daoNguoc();
		remove = new Animation(getGame().getInputData().getListAnimation().get("bulletBasicRemove"));

		if (getGame().getRockMan().getDirector() == DIR_LEFT) {
			if (getGame().getRockMan().isWallCling()) {
				setSpeedX(10);
				setDirector(DIR_RIGHT);
			} else if (!getGame().getRockMan().isWallCling()) {
				setSpeedX(-10);
				setDirector(DIR_LEFT);
			}
		} else if (getGame().getRockMan().getDirector() == DIR_RIGHT) {
			if (getGame().getRockMan().isWallCling()) {
				setSpeedX(-10);
				setDirector(DIR_LEFT);
			} else if (!getGame().getRockMan().isWallCling()) {
				setSpeedX(10);
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
			setSpeedX(0);
			setDame(0);
			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
			if (remove.isLastFrame()) {
				setStage(DEATH);
			}
		} else if (isTrung()) {
			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
			if (remove.isLastFrame()) {
				setStage(DEATH);
			}
		} else {
			if (getDirector() == DIR_LEFT) {
				banLeft.upload(System.nanoTime());
				banLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else if (getDirector() == DIR_RIGHT) {
				banRight.upload(System.nanoTime());
				banRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 2, rect.y + 1, rect.width - 4, rect.height - 2);
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

	public Animation getRemove() {
		return remove;
	}

	public void setRemove(Animation remove) {
		this.remove = remove;
	}

}
