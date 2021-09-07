package gecko;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import bossIntroStage.AppearEnemieSummon;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;
import view.GameFrame;

public class Gecko extends ParticulerObjectMegamanX {

	private Animation xuatHienLeft, xuatHienRight;
	private Animation idleLeft, idleRight;
	private Animation attackLeft, attackRight;

	private boolean isXuatHien;
	private boolean isCreateItemAppear;
	private AppearEnemieSummon apper;

	private boolean isShooting;
	private boolean isCreateBullet;
	private long timeStartShooting;

	private boolean isComplete;

	private GeckoTongue bullet;

	public Gecko(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0f, 96, 178, 600, 5);

		xuatHienLeft = new Animation(getGame().getInputData().getListAnimation().get("tacKeXuatHien"));
		xuatHienRight = new Animation(getGame().getInputData().getListAnimation().get("tacKeXuatHien"));
		xuatHienRight.daoNguoc();
		idleLeft = new Animation(getGame().getInputData().getListAnimation().get("tacKeIdle"));
		idleRight = new Animation(getGame().getInputData().getListAnimation().get("tacKeIdle"));
		idleRight.daoNguoc();
		attackLeft = new Animation(getGame().getInputData().getListAnimation().get("tacKeAttack"));
		attackLeft.setIsRepeat(false);
		attackRight = new Animation(getGame().getInputData().getListAnimation().get("tacKeAttack"));
		attackRight.daoNguoc();
		attackRight.setIsRepeat(false);

		Rectangle rect = getRectangleSizeObject();
		apper = new AppearEnemieSummon(rect.x + rect.width / 2, rect.y + rect.height / 2, getGame(), rect);

		setTeam(ENEMI_TEAM);
		setDirector(director);

		if (getX() >= getGame().getCamera().getLimitX2() + GameFrame.GAME_WIDTH - 100) {
			setX(getX() - getWidth() - 50);
		}
	}

	@Override
	public void upload() {

		super.upload();

		if (isXuatHien) {
			if (!isShooting) {
				isShooting = true;
				timeStartShooting = System.currentTimeMillis();
			} else {

				if (!isCreateBullet && (attackLeft.isLastFrame() || attackRight.isLastFrame())) {

					if (getDirector() == DIR_LEFT) {
						bullet = new GeckoTongue(getX() - 5, getY() + 25, getGame(), getDirector());
						getGame().getListObject().addObject(bullet);
					} else {
						bullet = new GeckoTongue(getX() + getWidth() - 20, getY() + 25, getGame(), getDirector());
						getGame().getListObject().addObject(bullet);
					}

					isCreateBullet = true;
				} else {
					if (bullet.isComplete()) {
						isComplete = true;
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (!isXuatHien) {

			if (getDirector() == DIR_LEFT) {
				if (isCreateItemAppear) {
					xuatHienLeft.upload(System.nanoTime());
				} else {
					apper.upload();
					apper.draw(g2);
					if (apper.isComplete()) {
						isCreateItemAppear = true;
					}
				}
				xuatHienLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				if (xuatHienLeft.isLastFrame()) {
					isXuatHien = true;
				}
			} else if (getDirector() == DIR_RIGHT) {
				if (isCreateItemAppear) {
					xuatHienRight.upload(System.nanoTime());
				} else {
					apper.upload();
					apper.draw(g2);
					if (apper.isComplete()) {
						isCreateItemAppear = true;
					}
				}
				xuatHienRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				if (xuatHienRight.isLastFrame()) {
					isXuatHien = true;
				}
			}

		} else {

			if (isShooting) {
				if (getDirector() == DIR_LEFT) {
					attackLeft.upload(System.nanoTime());
					attackLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				} else if (getDirector() == DIR_RIGHT) {
					attackRight.upload(System.nanoTime());
					attackRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}
			} else if (getDirector() == DIR_LEFT) {
				idleLeft.upload(System.nanoTime());
				idleLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else if (getDirector() == DIR_RIGHT) {
				idleRight.upload(System.nanoTime());
				idleRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();

		return new Rectangle((int) (rect.x + 10), (int) (rect.y + 10), rect.width - 20, rect.height - 20);
	}

	public Rectangle getRectangleCenter() {
		Rectangle rect = getRectangleXuLiVaCham();

		return new Rectangle(rect.x + 20, rect.y + 20, rect.width - 40, rect.height - 100);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();

		g2.setColor(Color.YELLOW);
		g2.fillRect(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
		WhenGeckoDeath whenDeath = new WhenGeckoDeath(getX(), getY(), getGame(), getDirector());
		getGame().getListWhenDeath().addWhenDeath(whenDeath);
	}

	public Animation getXuatHienLeft() {
		return xuatHienLeft;
	}

	public void setXuatHienLeft(Animation xuatHienLeft) {
		this.xuatHienLeft = xuatHienLeft;
	}

	public Animation getXuatHienRight() {
		return xuatHienRight;
	}

	public void setXuatHienRight(Animation xuatHienRight) {
		this.xuatHienRight = xuatHienRight;
	}

	public Animation getIdleLeft() {
		return idleLeft;
	}

	public void setIdleLeft(Animation idleLeft) {
		this.idleLeft = idleLeft;
	}

	public Animation getIdleRight() {
		return idleRight;
	}

	public void setIdleRight(Animation idleRight) {
		this.idleRight = idleRight;
	}

	public Animation getAttackLeft() {
		return attackLeft;
	}

	public void setAttackLeft(Animation attackLeft) {
		this.attackLeft = attackLeft;
	}

	public Animation getAttackRight() {
		return attackRight;
	}

	public void setAttackRight(Animation attackRight) {
		this.attackRight = attackRight;
	}

	public boolean isXuatHien() {
		return isXuatHien;
	}

	public void setXuatHien(boolean isXuatHien) {
		this.isXuatHien = isXuatHien;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public long getTimeStartShooting() {
		return timeStartShooting;
	}

	public void setTimeStartShooting(long timeStartShooting) {
		this.timeStartShooting = timeStartShooting;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public boolean isCreateBullet() {
		return isCreateBullet;
	}

	public void setCreateBullet(boolean isCreateBullet) {
		this.isCreateBullet = isCreateBullet;
	}

	public boolean isCreateItemAppear() {
		return isCreateItemAppear;
	}

	public void setCreateItemAppear(boolean isCreateItemAppear) {
		this.isCreateItemAppear = isCreateItemAppear;
	}

	public AppearEnemieSummon getApper() {
		return apper;
	}

	public void setApper(AppearEnemieSummon apper) {
		this.apper = apper;
	}

	public GeckoTongue getBullet() {
		return bullet;
	}

	public void setBullet(GeckoTongue bullet) {
		this.bullet = bullet;
	}

}
