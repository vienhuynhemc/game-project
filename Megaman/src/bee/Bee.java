package bee;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import bossIntroStage.AppearEnemieSummon;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;
import view.GameFrame;

public class Bee extends ParticulerObjectMegamanX {

	private Animation xuatHienLeft, xuatHienRight;
	private Animation idleLeft, idleRight;
	private Animation attackLeft, attackRight;

	private boolean isXuatHien;
	private AppearEnemieSummon appear;
	private boolean isCreateAppear;

	private boolean isShooting;
	private boolean isCreateBullet;
	private long timeStartShooting;

	private int countAttack;

	private boolean isComplete;

	private WingsOfBee wing;

	public Bee(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0f, 120, 176, 600, 5);

		xuatHienLeft = new Animation(getGame().getInputData().getListAnimation().get("beeXuatHien"));
		xuatHienRight = new Animation(getGame().getInputData().getListAnimation().get("beeXuatHien"));
		xuatHienRight.daoNguoc();
		idleLeft = new Animation(getGame().getInputData().getListAnimation().get("beeIdle"));
		idleRight = new Animation(getGame().getInputData().getListAnimation().get("beeIdle"));
		idleRight.daoNguoc();
		attackLeft = new Animation(getGame().getInputData().getListAnimation().get("beeAttack"));
		attackLeft.setIsRepeat(false);
		attackRight = new Animation(getGame().getInputData().getListAnimation().get("beeAttack"));
		attackRight.daoNguoc();
		attackRight.setIsRepeat(false);

		wing = new WingsOfBee(getX(), getY() + 20, getGame());

		Rectangle rect = getRectangleSizeObject();
		appear = new AppearEnemieSummon(rect.x + rect.width / 2, rect.y + rect.height / 2, getGame(), rect);

		setTeam(ENEMI_TEAM);
		setDirector(director);

		if (getX() >= getGame().getCamera().getLimitX2() + GameFrame.GAME_WIDTH - 100) {
			setX(getX() - getWidth() - 50);
		}
	}

	@Override
	public void upload() {
		super.upload();

		wing.setDirector(getDirector());

		if (isXuatHien) {
			if (!isShooting) {
				isShooting = true;
				timeStartShooting = System.currentTimeMillis();
			} else {

				if (!isCreateBullet && (attackLeft.isLastFrame() || attackRight.isLastFrame())) {

					if (getDirector() == DIR_LEFT) {
						LittleBee bullet1 = new LittleBee(getX() - 50, getY(), getGame());
						LittleBee bullet2 = new LittleBee(getX() - 50, getY() + 50, getGame());
						LittleBee bullet3 = new LittleBee(getX() - 50, getY() + 100, getGame());
						getGame().getListObject().addObject(bullet1);
						getGame().getListObject().addObject(bullet2);
						getGame().getListObject().addObject(bullet3);
					} else {
						LittleBee bullet1 = new LittleBee(getX() + getWidth() + 50, getY(), getGame());
						LittleBee bullet2 = new LittleBee(getX() + getWidth() + 50, getY() + 50, getGame());
						LittleBee bullet3 = new LittleBee(getX() + getWidth() + 50, getY() + 100, getGame());
						getGame().getListObject().addObject(bullet1);
						getGame().getListObject().addObject(bullet2);
						getGame().getListObject().addObject(bullet3);
					}

					countAttack++;

					isCreateBullet = true;
				}

				if (System.currentTimeMillis() - timeStartShooting > 2000) {
					isShooting = false;
					attackLeft.reset();
					attackRight.reset();

					isCreateBullet = false;
				}
			}

		}

		if (countAttack == 4) {
			isComplete = true;
		}
	}

	public Rectangle getRectangleCenter() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 40, rect.y + 20, rect.width - 80, rect.height - 40);
	}

	@Override
	public void draw(Graphics2D g2) {

		if (!isXuatHien) {

			if (getDirector() == DIR_LEFT) {
				if (isCreateAppear) {
					xuatHienLeft.upload(System.nanoTime());
				} else {
					appear.upload();
					appear.draw(g2);
					if (appear.isComplete()) {
						isCreateAppear = true;
					}
				}
				xuatHienLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				if (xuatHienLeft.isLastFrame()) {
					isXuatHien = true;
				}
			} else if (getDirector() == DIR_RIGHT) {

				if (isCreateAppear) {
					xuatHienRight.upload(System.nanoTime());
				} else {
					appear.upload();
					appear.draw(g2);
					if (appear.isComplete()) {
						isCreateAppear = true;
					}
				}
				xuatHienRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				if (xuatHienRight.isLastFrame()) {
					isXuatHien = true;
				}
			}

		} else {

			wing.upload();
			wing.draw(g2);

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

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();

		g2.setColor(Color.YELLOW);
		g2.fillRect(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
		WhenBeeDeath whenDeath = new WhenBeeDeath(getX(), getY(), getGame(), getDirector());
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

	public int getCountAttack() {
		return countAttack;
	}

	public void setCountAttack(int countAttack) {
		this.countAttack = countAttack;
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

	public AppearEnemieSummon getAppear() {
		return appear;
	}

	public void setAppear(AppearEnemieSummon appear) {
		this.appear = appear;
	}

	public boolean isCreateAppear() {
		return isCreateAppear;
	}

	public void setCreateAppear(boolean isCreateAppear) {
		this.isCreateAppear = isCreateAppear;
	}

	public WingsOfBee getWing() {
		return wing;
	}

	public void setWing(WingsOfBee wing) {
		this.wing = wing;
	}

}
