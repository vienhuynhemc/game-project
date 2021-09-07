package mettalT;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import actionForHuman.Attack;
import actionForHuman.Grounding;
import actionForHuman.Jump;
import actionForHuman.Run;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import item.LargeHealItem;
import item.SmallHealItem;
import particulerObject.HunmanMegamanX;
import sound.ManagerSound;

public class MettalT extends HunmanMegamanX implements Grounding, Jump, Run, Attack {

	private Animation dickLeft, dickRight;
	private Animation standUpLeft, standUpRight;
	private Animation runLeft, runRight;
	private Animation attackLeft, attackRight;
	private Animation tiepDatLeft, tiepDatRight;
	private Animation jumpLeft, jumpRight;

	private HashMap<String, Integer> listTimeForAction;
	private String[] arrayAction;
	private int currentAction;
	private Long timeBeginAction;

	private boolean isReady;

	private boolean isShooting;

	private boolean isCheck;

	private long timeBulletStart;

	private MettalTBullet bullet;

	private Random rd;

	private boolean isRoiKhoiMatDat;

	public MettalT(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0.1f, 54, 62, 60, 5);

		dickLeft = new Animation(getGame().getInputData().getListAnimation().get("mettalTDicking"));
		dickRight = new Animation(getGame().getInputData().getListAnimation().get("mettalTDicking"));
		dickRight.daoNguoc();
		standUpLeft = (new Animation(getGame().getInputData().getListAnimation().get("mettalTStandUp")));
		standUpRight = new Animation(getGame().getInputData().getListAnimation().get("mettalTStandUp"));
		standUpRight.daoNguoc();
		runLeft = new Animation(getGame().getInputData().getListAnimation().get("mettalRun"));
		runRight = new Animation(getGame().getInputData().getListAnimation().get("mettalRun"));
		runRight.daoNguoc();
		jumpLeft = new Animation(getGame().getInputData().getListAnimation().get("mettalJump"));
		jumpLeft.setIsRepeat(false);
		jumpRight = new Animation(getGame().getInputData().getListAnimation().get("mettalJump"));
		jumpRight.daoNguoc();
		jumpRight.setIsRepeat(false);
		tiepDatLeft = new Animation(getGame().getInputData().getListAnimation().get("mettalTiepDat"));
		tiepDatRight = new Animation(getGame().getInputData().getListAnimation().get("mettalTiepDat"));
		tiepDatRight.daoNguoc();
		attackLeft = new Animation(getGame().getInputData().getListAnimation().get("mettalAttack"));
		attackRight = new Animation(getGame().getInputData().getListAnimation().get("mettalAttack"));
		attackRight.daoNguoc();

		arrayAction = new String[3];
		arrayAction[0] = "RUN";
		arrayAction[1] = "ATTACK";
		arrayAction[2] = "JUMP";

		listTimeForAction = new HashMap<String, Integer>();
		listTimeForAction.put("RUN", 1500);
		listTimeForAction.put("ATTACK", 3000);
		listTimeForAction.put("JUMP", 3000);

		setDicking(true);
		setIsJumping(false);

		setDirector(director);
		setTeam(ENEMI_TEAM);

		setxPrimaral(getX());
		setyPrimaral(getY());
		setDirectorPrimaral(getDirector());
		setBloodPrimaral(getBlood());

		rd = new Random();
	}

	@Override
	public void upload() {
		super.upload();

		if (getX() > getGame().getRockMan().getX())
			setDirector(DIR_LEFT);
		else
			setDirector(DIR_RIGHT);

		if (!isReady) {
			if (getX() > getGame().getRockMan().getX() && getX() - getGame().getRockMan().getX() < 700) {
				setDicking(false);
				setTimeBeginAction(System.currentTimeMillis());
			} else if (getX() < getGame().getRockMan().getX() && getGame().getRockMan().getX() - getX() < 700) {
				setDicking(false);
				setTimeBeginAction(System.currentTimeMillis());
			}
		}

		if (getSpeedY() != 0) {
			isRoiKhoiMatDat = true;
		}

		if (!getIsDicking() && isReady) {
			if (currentAction == 0) {
				tiepDat();
				run();
				if (System.currentTimeMillis() - timeBeginAction > listTimeForAction.get("RUN")) {
					timeBeginAction = System.currentTimeMillis();
					currentAction++;
				}
			} else if (currentAction == 1) {
				attack();
				if (System.currentTimeMillis() - timeBeginAction > listTimeForAction.get("ATTACK")) {
					timeBeginAction = System.currentTimeMillis();
					currentAction++;
				}
			} else if (currentAction == 2) {
				if (!getIsJumping())
					jump();
				if (System.currentTimeMillis() - timeBeginAction > listTimeForAction.get("JUMP")) {
					timeBeginAction = System.currentTimeMillis();
					currentAction = 0;
				}
			}
		}

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		if (getIsDicking()) {
			Rectangle rectXuLiVaCham = new Rectangle(rect.x, rect.y + 16, rect.width - 6, rect.height - 16);
			return rectXuLiVaCham;
		} else {
			Rectangle rectXuLiVaCham = new Rectangle(rect.x, rect.y + 10, rect.width - 8, rect.height - 10);
			return rectXuLiVaCham;
		}
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		g2.setColor(Color.yellow);
		Rectangle rect = getRectangleXuLiVaCham();
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
		ManagerSound.getInstance().getListSound().get("bang").start();
		try {
			ManagerSound.getInstance().khoiTaoHieuUng("bang");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		WhenMettalTDeath whenDeath = new WhenMettalTDeath(getX(), getY(), getGame());
		getGame().getListWhenDeath().addWhenDeath(whenDeath);
		int a = rd.nextInt(100);
		if (a <= 9) {
			LargeHealItem item = new LargeHealItem(getX(), getY(), getGame());
			item.setTimeStart(System.currentTimeMillis());
			getGame().getListItem().addItem(item);
		} else if (a >= 10 && a <= 29) {
			SmallHealItem item = new SmallHealItem(getX(), getY(), getGame());
			item.setTimeStart(System.currentTimeMillis());
			getGame().getListItem().addItem(item);
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (getIsDicking()) {
			if (getDirector() == DIR_LEFT) {
				drawAndUploadHanhDong(g2, dickLeft);
			} else if (getDirector() == DIR_RIGHT) {
				drawHanhDongRight(g2, dickRight);
			}
		} else if (!getIsDicking() && !isReady) {
			if (getDirector() == DIR_LEFT) {
				drawAndUploadHanhDong(g2, standUpLeft);
				if (standUpLeft.isLastFrame())
					isReady = true;
			} else if (getDirector() == DIR_RIGHT) {
				drawAndUploadHanhDong(g2, standUpRight);
				if (standUpRight.isLastFrame())
					isReady = true;
			}
		} else if (currentAction == 0) {
			if (getDirector() == DIR_LEFT) {
				runLeft.upload(System.nanoTime());
				runLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 10 - getGame().getCamera().getY()));
			} else if (getDirector() == DIR_RIGHT) {
				runRight.upload(System.nanoTime());
				runRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 10 - getGame().getCamera().getY()));
			}
		} else if (currentAction == 1) {
			if (getDirector() == DIR_LEFT) {
				attackLeft.upload(System.nanoTime());
				attackLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 10 - getGame().getCamera().getY()));
				if (attackLeft.isLastFrame()) {
					currentAction++;
					timeBeginAction = System.currentTimeMillis();
				}
			} else if (getDirector() == DIR_RIGHT) {
				attackRight.upload(System.nanoTime());
				attackRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 10 - getGame().getCamera().getY()));
				if (attackRight.isLastFrame()) {
					currentAction++;
					timeBeginAction = System.currentTimeMillis();
				}
			}
		} else if (currentAction == 2) {
			if (getIsJumping() && getSpeedY() == 0) {
				if (getDirector() == DIR_LEFT) {
					tiepDatLeft.upload(System.nanoTime());
					tiepDatLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - 10 - getGame().getCamera().getY()));
					if (tiepDatLeft.isLastFrame()) {
						currentAction = 0;
						timeBeginAction = System.currentTimeMillis();
					}
				} else if (getDirector() == DIR_RIGHT) {
					tiepDatRight.upload(System.nanoTime());
					tiepDatRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - 10 - getGame().getCamera().getY()));
					if (tiepDatRight.isLastFrame()) {
						currentAction = 0;
						timeBeginAction = System.currentTimeMillis();
					}
				}
			} else {
				if (getDirector() == DIR_LEFT) {
					jumpLeft.upload(System.nanoTime());
					jumpLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - 10 - getGame().getCamera().getY()));
				} else if (getDirector() == DIR_RIGHT) {
					jumpRight.upload(System.nanoTime());
					jumpRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - 10 - getGame().getCamera().getY()));
				}
			}
		}
	}

	@Override
	public void attack() {
		setSpeedX(0);
		setSpeedY(0);
		if (!isShooting) {
			bullet = new MettalTBullet(getX(), getY(), getGame());
			bullet.setY(bullet.getY() + 15);

			if (getDirector() == DIR_LEFT) {
				bullet.setDirector(DIR_LEFT);
				bullet.setSpeedX(-5);
			} else if (getDirector() == DIR_RIGHT) {
				bullet.setX(bullet.getX() + getWidth());
				bullet.setDirector(DIR_RIGHT);
				bullet.setSpeedX(5);
			}

			timeBulletStart = System.nanoTime();
			isShooting = true;
			isCheck = true;
		}
		if (System.nanoTime() - timeBulletStart > 400000000 && isCheck == true) {
			getGame().getListBullet().addObject(bullet);
			isCheck = false;

			ManagerSound.getInstance().getListSound().get("danLv12").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("danLv12");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		if (getDirector() == DIR_LEFT)
			setSpeedX(-1);
		else
			setSpeedX(1);
	}

	@Override
	public void jump() {
		setIsJumping(true);
		setSpeedY(-4);
		setY(getY() - 2);
		if (getDirector() == DIR_LEFT)
			setSpeedX(-1);
		else
			setSpeedX(1);
	}

	@Override
	public void tiepDat() {
		if (isRoiKhoiMatDat) {
			isRoiKhoiMatDat = false;
			ManagerSound.getInstance().getListSound().get("tiepDat").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("tiepDat");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		isShooting = false;
		setIsJumping(false);
		jumpLeft.reset();
		jumpRight.reset();
		tiepDatLeft.reset();
		tiepDatRight.reset();
	}

	public Animation getStandUpLeft() {
		return standUpLeft;
	}

	public void setStandUpLeft(Animation standUpLeft) {
		this.standUpLeft = standUpLeft;
	}

	public Long getTimeBeginAction() {
		return timeBeginAction;
	}

	public void setTimeBeginAction(Long timeBeginAction) {
		this.timeBeginAction = timeBeginAction;
	}

	public HashMap<String, Integer> getListTimeForAction() {
		return listTimeForAction;
	}

	public void setListTimeForAction(HashMap<String, Integer> listTimeForAction) {
		this.listTimeForAction = listTimeForAction;
	}

	public String[] getArrayAction() {
		return arrayAction;
	}

	public void setArrayAction(String[] arrayAction) {
		this.arrayAction = arrayAction;
	}

	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public Animation getRunLeft() {
		return runLeft;
	}

	public void setRunLeft(Animation runLeft) {
		this.runLeft = runLeft;
	}

	public Animation getRunRight() {
		return runRight;
	}

	public void setRunRight(Animation runRight) {
		this.runRight = runRight;
	}

	public Animation getAttackLeft() {
		return attackLeft;
	}

	public void setAttackLeft(Animation attackLeft) {
		this.attackLeft = attackLeft;
	}

	public Animation getTiepDatLeft() {
		return tiepDatLeft;
	}

	public void setTiepDatLeft(Animation tiepDatLeft) {
		this.tiepDatLeft = tiepDatLeft;
	}

	public Animation getDickLeft() {
		return dickLeft;
	}

	public void setDickLeft(Animation dickLeft) {
		this.dickLeft = dickLeft;
	}

	public Animation getDickRight() {
		return dickRight;
	}

	public void setDickRight(Animation dickRight) {
		this.dickRight = dickRight;
	}

	public Animation getStandUpRight() {
		return standUpRight;
	}

	public void setStandUpRight(Animation standUpRight) {
		this.standUpRight = standUpRight;
	}

	public Animation getAttackRight() {
		return attackRight;
	}

	public void setAttackRight(Animation attackRight) {
		this.attackRight = attackRight;
	}

	public Animation getTiepDatRight() {
		return tiepDatRight;
	}

	public void setTiepDatRight(Animation tiepDatRight) {
		this.tiepDatRight = tiepDatRight;
	}

	public Animation getJumpLeft() {
		return jumpLeft;
	}

	public void setJumpLeft(Animation jumpLeft) {
		this.jumpLeft = jumpLeft;
	}

	public Animation getJumpRight() {
		return jumpRight;
	}

	public void setJumpRight(Animation jumpRight) {
		this.jumpRight = jumpRight;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public long getTimeBulletStart() {
		return timeBulletStart;
	}

	public void setTimeBulletStart(long timeBulletStart) {
		this.timeBulletStart = timeBulletStart;
	}

	public MettalTBullet getBullet() {
		return bullet;
	}

	public void setBullet(MettalTBullet bullet) {
		this.bullet = bullet;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

}
