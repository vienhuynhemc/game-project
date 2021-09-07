package robotWallCling;

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

public class RobotWallCing extends ParticulerObjectMegamanX {

	private Animation runLeft, runRight;
	private Animation attackLeft, attackRight;

	private String[] listAction;
	private HashMap<String, Integer> listTimeForAction;

	private int currentAction;

	private long timeStart;

	private Random rd;

	private int directorRun;

	private boolean isShooting;

	private int[] hp;

	private boolean isHp;

	public RobotWallCing(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0.1f, 60, 74, 60, 5);

		rd = new Random();

		hp = new int[1];
		hp[0] = 10000;

		runLeft = new Animation(getGame().getInputData().getListAnimation().get("robotWallClingRun"));
		runRight = new Animation(getGame().getInputData().getListAnimation().get("robotWallClingRun"));
		runRight.daoNguoc();

		attackLeft = new Animation(getGame().getInputData().getListAnimation().get("robotWallClingAttack"));
		attackLeft.setIsRepeat(false);
		attackRight = new Animation(getGame().getInputData().getListAnimation().get("robotWallClingAttack"));
		attackRight.setIsRepeat(false);
		attackRight.daoNguoc();

		listAction = new String[3];
		listAction[0] = "NONE";
		listAction[1] = "ATTACK";
		listAction[2] = "RUN";

		listTimeForAction = new HashMap<String, Integer>();
		listTimeForAction.put("NONE", 1000);
		listTimeForAction.put("ATTACK", 4000);
		listTimeForAction.put("RUN", 4000);

		directorRun = DIR_LEFT;

		setCurrentAction(0);

		setTeam(ENEMI_TEAM);
		setDirector(director);

		setxPrimaral(getX());
		setyPrimaral(getY());
		setDirectorPrimaral(getDirector());
		setBloodPrimaral(getBlood());

		setRd(new Random());
	}

	@Override
	public void upload() {
		super.upload();

		setY(getY() + getSpeedY());

		if (timeStart == 0)
			timeStart = System.currentTimeMillis();

		if (getGame().getPhysicalMap().vaChamTrenDau(getRectangleSizeObject()) != null) {
			setDirectorRun(DIR_LEFT);
		}
		if (getGame().getPhysicalMap().vaChamMatDat(getRectangleSizeObject()) != null) {
			setDirectorRun(DIR_RIGHT);
		}

		if (currentAction == 0) {
			none();
			if (System.currentTimeMillis() - timeStart > listTimeForAction.get("NONE")) {
				timeStart = System.currentTimeMillis();
				currentAction++;
			}
		} else if (currentAction == 1) {
			if (!isShooting) {
				attack();
			}
			if (System.currentTimeMillis() - timeStart > listTimeForAction.get("ATTACK")) {
				timeStart = System.currentTimeMillis();
				currentAction++;
			}
		} else if (currentAction == 2) {
			run();
			if (System.currentTimeMillis() - timeStart > listTimeForAction.get("RUN")) {
				timeStart = System.currentTimeMillis();
				currentAction = 0;
			}
		}
	}

	public void none() {
		if (isHp) {
			setBlood(hp[0]);
			hp[0] = 10000;
			isHp = false;
		}
		isShooting = false;
		setSpeedY(0);
		attackLeft.reset();
		attackRight.reset();
	}

	public void run() {
		if (isHp) {
			setBlood(hp[0]);
			hp[0] = 10000;
			isHp = false;
		}
		if (getDirectorRun() == DIR_LEFT) {
			setSpeedY(1);
		} else if (getDirectorRun() == DIR_RIGHT) {
			setSpeedY(-1);
		}
	}

	public void attack() {
		if (!isShooting) {
			int a = getBlood();
			setBlood(hp[0]);
			hp[0] = a;
			isHp = true;

			RobotWallClingBullet bullet1 = new RobotWallClingBullet(getX(), getY(), getGame());
			RobotWallClingBullet bullet2 = new RobotWallClingBullet(getX(), getY() + getHeight(), getGame());
			bullet1.setDirector(getDirector());
			bullet1.setDirectorRun(DIR_RIGHT);
			bullet2.setDirector(getDirector());
			bullet2.setDirectorRun(DIR_LEFT);

			if (getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
				bullet1.setX(bullet1.getX() + getWidth());
				bullet2.setX(bullet2.getX() + getWidth());
			}

			getGame().getListBullet().addObject(bullet1);
			getGame().getListBullet().addObject(bullet2);
			isShooting = true;

			ManagerSound.getInstance().getListSound().get("danLv3").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("danLv3");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 18, rect.y + 18, rect.width - 18, rect.height - 36);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
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
		WhenRobotWallClingDeath dead = new WhenRobotWallClingDeath(getX(), getY(), getGame());
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

		if (getCurrentAction() == 0) {
			if (getDirector() == DIR_LEFT) {
				runLeft.upload(System.nanoTime());
				runLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else if (getDirector() == DIR_RIGHT) {
				runRight.upload(System.nanoTime());
				runRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		} else if (getCurrentAction() == 1) {
			if (getDirector() == DIR_LEFT) {
				attackLeft.upload(System.nanoTime());
				if (attackLeft.getCurrentFrame() == 2) {
					attackLeft.draw(g2, (int) (getX() - 2 - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				} else if (attackLeft.getCurrentFrame() == 3) {
					attackLeft.draw(g2, (int) (getX() - 20 - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				} else if (attackLeft.getCurrentFrame() == 4) {
					attackLeft.draw(g2, (int) (getX() - 10 - getGame().getCamera().getX()),
							(int) (getY() - 4 - getGame().getCamera().getY()));
				} else {
					attackLeft.draw(g2, (int) (getX() - 6 - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}
			} else if (getDirector() == DIR_RIGHT) {
				attackRight.upload(System.nanoTime());
				attackRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		} else if (getCurrentAction() == 2) {
			if (getDirector() == DIR_LEFT) {
				runLeft.upload(System.nanoTime());
				runLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else if (getDirector() == DIR_RIGHT) {
				runRight.upload(System.nanoTime());
				runRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		}
	}

	public Animation getRunLeft() {
		return runLeft;
	}

	public void setRunLeft(Animation runLeft) {
		this.runLeft = runLeft;
	}

	public Animation getAttackLeft() {
		return attackLeft;
	}

	public void setAttackLeft(Animation attackLeft) {
		this.attackLeft = attackLeft;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

	public int getDirectorRun() {
		return directorRun;
	}

	public void setDirectorRun(int directorRun) {
		this.directorRun = directorRun;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public Animation getRunRight() {
		return runRight;
	}

	public void setRunRight(Animation runRight) {
		this.runRight = runRight;
	}

	public Animation getAttackRight() {
		return attackRight;
	}

	public void setAttackRight(Animation attackRight) {
		this.attackRight = attackRight;
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

	public int[] getHp() {
		return hp;
	}

	public void setHp(int[] hp) {
		this.hp = hp;
	}

	public boolean isHp() {
		return isHp;
	}

	public void setHp(boolean isHp) {
		this.isHp = isHp;
	}

}
