package robotDropTheThonrs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import item.LargeHealItem;
import item.SmallHealItem;
import particulerObject.ParticulerObjectMegamanX;
import sound.ManagerSound;

public class IntroEnemieRobotDropTheThorns extends ParticulerObjectMegamanX {

	private Random rd;

	private Animation canhQuat;
	private Animation idleLeft, idleRight;
	private Animation attackLeft, attackRight;
	private Animation bayDiLeft, bayDiRight;
	private Animation idleBayLeft, idleBayRight;

	private String[] listAction;
	private HashMap<String, Integer> listTimeForAction;
	private int currentAction;
	private long timeStartAction;

	private int xAttack;
	private int yAttack;
	private boolean isStayAtAttack;

	private int xRun;
	private int yRun;
	private boolean isStayAtRun;

	private boolean isShooting;
	private boolean isBan;

	public IntroEnemieRobotDropTheThorns(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0.1f, 110, 138, 200, 5);

		rd = new Random();

		canhQuat = new Animation(getGame().getInputData().getListAnimation().get("canhQuatRobotThaQuaCauGai"));
		idleLeft = new Animation(getGame().getInputData().getListAnimation().get("robotThaQuaCauGaiIdle"));
		idleRight = new Animation(getGame().getInputData().getListAnimation().get("robotThaQuaCauGaiIdle"));
		idleRight.daoNguoc();
		attackLeft = new Animation(getGame().getInputData().getListAnimation().get("robotThaQuaCauGaiAttack"));
		attackRight = new Animation(getGame().getInputData().getListAnimation().get("robotThaQuaCauGaiAttack"));
		attackRight.daoNguoc();
		bayDiLeft = new Animation(getGame().getInputData().getListAnimation().get("robotThaQuaCauGaiBayDi"));
		bayDiLeft.setIsRepeat(false);
		bayDiRight = new Animation(getGame().getInputData().getListAnimation().get("robotThaQuaCauGaiBayDi"));
		bayDiRight.daoNguoc();
		bayDiRight.setIsRepeat(false);
		idleBayLeft = new Animation(getGame().getInputData().getListAnimation().get("robotThaQuaCauGaiBayIdle"));
		idleBayRight = new Animation(getGame().getInputData().getListAnimation().get("robotThaQuaCauGaiBayIdle"));
		idleBayRight.daoNguoc();

		xAttack = (int) getX();
		yAttack = (int) getY();
		xRun = (int) getX() - 200;
		yRun = (int) getY() - 500;

		listAction = new String[2];
		listAction[0] = "RUN";
		listAction[1] = "ATTACK";

		listTimeForAction = new HashMap<String, Integer>();
		listTimeForAction.put("RUN", 4000);
		listTimeForAction.put("ATTACK", 4000);

		timeStartAction = System.currentTimeMillis();

		setxPrimaral(getX());
		setyPrimaral(getY());
		setBloodPrimaral(getBlood());
		setDirectorPrimaral(getDirector());

		setDirector(director);
		setTeam(ENEMI_TEAM);
	}

	@Override
	public void upload() {
		super.upload();

		setX(getX() + getSpeedX());
		setY(getY() + getSpeedY());

		uploadHuong();

		if (getX() == xRun && getY() == yRun)
			isStayAtRun = true;
		else
			isStayAtRun = false;

		if (getX() == xAttack && getY() == yAttack)
			isStayAtAttack = true;
		else
			isStayAtAttack = false;

		switch (currentAction) {
		case 0:

			isBan = false;

			if (isStayAtRun) {
				setSpeedX(0);
				setSpeedY(0);
			}
			if (getX() > xRun)
				setSpeedX(-2);
			else if (getX() < xRun)
				setSpeedX(+2);
			if (getY() > yRun)
				setSpeedY(-5);
			else if (getY() < yRun)
				setSpeedY(5);

			if (System.currentTimeMillis() - timeStartAction > listTimeForAction.get(listAction[0])) {
				timeStartAction = System.currentTimeMillis();
				currentAction++;
			}
			break;
		case 1:
			if (!isStayAtAttack) {
				if (getX() > xAttack)
					setSpeedX(-2);
				else if (getX() < xAttack)
					setSpeedX(+2);
				if (getY() > yAttack)
					setSpeedY(-5);
				else if (getY() < yAttack)
					setSpeedY(5);
				break;
			} else {
				setSpeedX(0);
				setSpeedY(0);
				attack();
			}

			if (System.currentTimeMillis() - timeStartAction > listTimeForAction.get(listAction[1])) {
				timeStartAction = System.currentTimeMillis();
				currentAction = 0;
			}
			break;

		default:
			break;
		}

	}

	public void attack() {
		isShooting = true;

		if (!isBan) {
			isBan = true;
			Thorns quaCauGai = new Thorns(getX() + 20, getY() + 50, getGame(), DIR_RIGHT);
			getGame().getListBullet().addObject(quaCauGai);
		}

		bayDiLeft.reset();
		bayDiRight.reset();

	}

	private void uploadHuong() {
		switch (currentAction) {
		case 1:
			if (getX() > xAttack)
				setDirector(DIR_LEFT);
			else
				setDirector(DIR_RIGHT);

			break;
		case 0:
			if (getX() > xRun)
				setDirector(DIR_LEFT);
			else
				setDirector(DIR_RIGHT);
			break;
		default:
			break;
		}

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 20, rect.y + 10, rect.width - 40, rect.height - 70);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();

		g2.setColor(Color.yellow);
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
		WhenIntroEnemieRobotDropTheThornsDeath dead = new WhenIntroEnemieRobotDropTheThornsDeath(getX(), getY(),
				getGame());
		getGame().getListWhenDeath().addWhenDeath(dead);
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

		drawCanhQuat(g2);

		switch (currentAction) {
		case 0:

			boolean isCompleteXepCanh = false;
			if (getDirector() == DIR_LEFT) {
				if (bayDiLeft.isLastFrame())
					isCompleteXepCanh = true;
			} else if (getDirector() == DIR_RIGHT) {
				if (bayDiRight.isLastFrame())
					isCompleteXepCanh = true;
			}

			if (isCompleteXepCanh) {
				if (getDirector() == DIR_LEFT) {
					idleBayLeft.upload(System.nanoTime());
					idleBayLeft.draw(g2, (int) (getX() + 7 - getGame().getCamera().getX()),
							(int) (getY() + 14 - getGame().getCamera().getY()));
				} else if (getDirector() == DIR_RIGHT) {
					idleBayRight.upload(System.nanoTime());
					idleBayRight.draw(g2, (int) (getX() + 7 - getGame().getCamera().getX()),
							(int) (getY() + 14 - getGame().getCamera().getY()));
				}
			} else {
				setSpeedX(0);
				setSpeedY(0);
				if (getDirector() == DIR_LEFT) {
					bayDiLeft.upload(System.nanoTime());
					if (bayDiLeft.getCurrentFrame() == 1) {
						bayDiLeft.draw(g2, (int) (getX() + 3 - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					} else {
						bayDiLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					}
				} else if (getDirector() == DIR_RIGHT) {
					if (bayDiRight.getCurrentFrame() == 1) {
						bayDiRight.draw(g2, (int) (getX() + 3 - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					} else {
						bayDiRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					}
				}
			}
			break;
		case 1:
			if (isShooting) {
				if (getDirector() == DIR_LEFT) {
					attackLeft.upload(System.nanoTime());
					if (attackLeft.getCurrentFrame() == 0) {
						attackLeft.draw(g2, (int) (getX() + 17 - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					} else if (attackLeft.getCurrentFrame() == 1) {
						attackLeft.draw(g2, (int) (getX() + 13 - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					} else if (attackLeft.getCurrentFrame() == 2) {
						attackLeft.draw(g2, (int) (getX() + 5 - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					}
					if (attackLeft.isLastFrame()) {
						isShooting = false;
						isBan = false;
						timeStartAction = System.currentTimeMillis();
						currentAction = 0;
					}
				} else if (getDirector() == DIR_RIGHT) {
					attackRight.upload(System.nanoTime());
					if (attackRight.getCurrentFrame() == 0) {
						attackRight.draw(g2, (int) (getX() + 17 - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					} else if (attackRight.getCurrentFrame() == 1) {
						attackRight.draw(g2, (int) (getX() + 13 - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					} else if (attackRight.getCurrentFrame() == 2) {
						attackRight.draw(g2, (int) (getX() + 5 - getGame().getCamera().getX()),
								(int) (getY() + 14 - getGame().getCamera().getY()));
					}
					if (attackRight.isLastFrame()) {
						isShooting = false;
						isBan = false;
						timeStartAction = System.currentTimeMillis();
						currentAction = 0;
					}
				}
			} else {
				if (getDirector() == DIR_LEFT) {
					idleLeft.upload(System.nanoTime());
					idleLeft.draw(g2, (int) (getX() + 13 - getGame().getCamera().getX()),
							(int) (getY() + 14 - getGame().getCamera().getY()));
				} else if (getDirector() == DIR_RIGHT) {
					idleRight.upload(System.nanoTime());
					idleRight.draw(g2, (int) (getX() + 13 - getGame().getCamera().getX()),
							(int) (getY() + 14 - getGame().getCamera().getY()));
				}
			}
		default:
			break;
		}
	}

	private void drawCanhQuat(Graphics2D g2) {
		canhQuat.upload(System.nanoTime());

		if (canhQuat.getCurrentFrame() == 0) {
			canhQuat.draw(g2, (int) (getX() + 15 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else if (canhQuat.getCurrentFrame() == 3) {
			canhQuat.draw(g2, (int) (getX() + 47 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else if (canhQuat.getCurrentFrame() == 1 || canhQuat.getCurrentFrame() == 5) {
			canhQuat.draw(g2, (int) (getX() + 24 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else {
			canhQuat.draw(g2, (int) (getX() + 32 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		}
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

	public Animation getCanhQuat() {
		return canhQuat;
	}

	public void setCanhQuat(Animation canhQuat) {
		this.canhQuat = canhQuat;
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

	public Animation getBayDiLeft() {
		return bayDiLeft;
	}

	public void setBayDiLeft(Animation bayDiLeft) {
		this.bayDiLeft = bayDiLeft;
	}

	public Animation getBayDiRight() {
		return bayDiRight;
	}

	public void setBayDiRight(Animation bayDiRight) {
		this.bayDiRight = bayDiRight;
	}

	public Animation getIdleBayLeft() {
		return idleBayLeft;
	}

	public void setIdleBayLeft(Animation idleBayLeft) {
		this.idleBayLeft = idleBayLeft;
	}

	public Animation getIdleBayRight() {
		return idleBayRight;
	}

	public void setIdleBayRight(Animation idleBayRight) {
		this.idleBayRight = idleBayRight;
	}

	public String[] getListAction() {
		return listAction;
	}

	public void setListAction(String[] listAction) {
		this.listAction = listAction;
	}

	public HashMap<String, Integer> getListTimeForAction() {
		return listTimeForAction;
	}

	public void setListTimeForAction(HashMap<String, Integer> listTimeForAction) {
		this.listTimeForAction = listTimeForAction;
	}

	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}

	public long getTimeStartAction() {
		return timeStartAction;
	}

	public void setTimeStartAction(long timeStartAction) {
		this.timeStartAction = timeStartAction;
	}

	public int getxAttack() {
		return xAttack;
	}

	public void setxAttack(int xAttack) {
		this.xAttack = xAttack;
	}

	public int getyAttack() {
		return yAttack;
	}

	public void setyAttack(int yAttack) {
		this.yAttack = yAttack;
	}

	public boolean isStayAtAttack() {
		return isStayAtAttack;
	}

	public void setStayAtAttack(boolean isStayAtAttack) {
		this.isStayAtAttack = isStayAtAttack;
	}

	public int getxRun() {
		return xRun;
	}

	public void setxRun(int xRun) {
		this.xRun = xRun;
	}

	public int getyRun() {
		return yRun;
	}

	public void setyRun(int yRun) {
		this.yRun = yRun;
	}

	public boolean isStayAtRun() {
		return isStayAtRun;
	}

	public void setStayAtRun(boolean isStayAtRun) {
		this.isStayAtRun = isStayAtRun;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public boolean isBan() {
		return isBan;
	}

	public void setBan(boolean isBan) {
		this.isBan = isBan;
	}

}
