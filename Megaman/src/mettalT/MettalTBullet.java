package mettalT;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import objectBullet.BulletMegamanX;

public class MettalTBullet extends BulletMegamanX {

	private Animation banLeft, banRight;
	private Animation removeLeft, removeRight;

	public MettalTBullet(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f, 32, 28, 1000, 5);

		banLeft = new Animation(getGame().getInputData().getListAnimation().get("mettalBullet"));
		banRight = new Animation(getGame().getInputData().getListAnimation().get("mettalBullet"));
		banLeft.daoNguoc();
		removeLeft = new Animation(getGame().getInputData().getListAnimation().get("mettalBulletRemove"));
		removeRight = new Animation(getGame().getInputData().getListAnimation().get("mettalBulletRemove"));
		removeRight.daoNguoc();

		setTeam(ENEMI_TEAM);
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
			if (getDirector() == DIR_LEFT) {
				removeLeft.upload(System.nanoTime());
				removeLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				if (removeLeft.isLastFrame()) {
					setStage(DEATH);
				}
			} else if (getDirector() == DIR_RIGHT) {
				removeRight.upload(System.nanoTime());
				removeRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				if (removeRight.isLastFrame()) {
					setStage(DEATH);
				}
			}
		} else if (isTrung()) {
			if (getDirector() == DIR_LEFT) {
				removeLeft.upload(System.nanoTime());
				removeLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				if (removeLeft.isLastFrame()) {
					setStage(DEATH);
				}
			} else if (getDirector() == DIR_RIGHT) {
				removeRight.upload(System.nanoTime());
				removeRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				if (removeRight.isLastFrame()) {
					setStage(DEATH);
				}
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
		return new Rectangle((int) (rect.x + 7), (int) (rect.y + 7), (int) (rect.width - 14), (int) (rect.height - 14));
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();
		g2.setColor(Color.green);
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
