package darkStump;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Random;

import actionForHuman.Run;
import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;
import healthBarEnemyMapleStory.HealBarEnemies;
import item.LargeHealItemMapleStory;
import item.SmallHealItemMapleStory;
import particulerObject.HumanMapleStory;
import whenDeath.WhenDeadMotLan;

public class DarkStump extends HumanMapleStory implements Run {

	private Animation idleL, idleR;
	private Animation runL, runR;
	private Animation beHurtL, beHurtR;
	private Animation deadL, deadR;

	private boolean isShooting;
	private boolean isRun;
	private float xStart;

	private HashMap<String, Integer> listTime;
	private String[] arrayAction;
	private int currentAction;
	private long timeStart;

	private long timeStartBehurt;

	private HealBarEnemies hb;

	private Random rd;

	public DarkStump(float x, float y, GameWorldMapleStory game, int dir) {
		super(x, y, game, 0.1f, 124, 102, 100, 5);

		hb = new HealBarEnemies("DarkStump", 100, 100, getX() - getGame().getCamera().getX(),
				getY() - getGame().getCamera().getY(), 150, 30);

		idleL = new Animation(getGame().getInputData().getListAnimation().get("darkStumpIdle"));
		idleR = new Animation(getGame().getInputData().getListAnimation().get("darkStumpIdle"));
		idleR.daoNguoc();

		runL = new Animation(getGame().getInputData().getListAnimation().get("darkStumpRun"));
		runR = new Animation(getGame().getInputData().getListAnimation().get("darkStumpRun"));
		runR.daoNguoc();

		beHurtL = new Animation(getGame().getInputData().getListAnimation().get("darkStumpBehurt"));
		beHurtR = new Animation(getGame().getInputData().getListAnimation().get("darkStumpBehurt"));
		beHurtR.daoNguoc();

		deadL = new Animation(getGame().getInputData().getListAnimation().get("darkStumpDead"));
		deadR = new Animation(getGame().getInputData().getListAnimation().get("darkStumpDead"));
		deadR.daoNguoc();

		setDirector(dir);
		setTeam(ENEMI_TEAM);
		setStage(ALIVE);

		setxPrimaral(getX());
		setyPrimaral(getY());
		setDirectorPrimaral(getDirector());
		setBloodPrimaral(getBlood());

		listTime = new HashMap<String, Integer>();
		listTime.put("NONE", 1000);
		listTime.put("RUN", 4000);
		listTime.put("ATTACK", 4000);

		arrayAction = new String[3];
		arrayAction[0] = "NONE";
		arrayAction[1] = "RUN";
		arrayAction[2] = "ATTACK";

		currentAction = 0;
		timeStart = System.currentTimeMillis();

		rd = new Random();
	}

	@Override
	public void upload() {
		super.upload();

		hb.upload("DarkStump", 100, getBlood(), getX() - getGame().getCamera().getX(),
				getY() - getGame().getCamera().getY());

		Rectangle r = getRectangleSizeObject();
		Rectangle rr = new Rectangle(r.x - 10, r.y - 10, r.width + 20, r.height + 20);
		if (getGame().getPhysicalMap().vaChamBenTrai(rr) != null) {
			setDirector(DIR_RIGHT);
		}

		if (getGame().getPhysicalMap().vaChamBenPhai(rr) != null) {
			setDirector(DIR_LEFT);
		}

		if (getStage() == BEHURT) {
			setSpeedX(0);
			setSpeedY(0);
			if (System.currentTimeMillis() - timeStartBehurt > 300) {
				timeStartBehurt = 0;
				setStage(ALIVE);
			}
		} else {

			if (currentAction == 0) {
				idle();
				if (System.currentTimeMillis() - timeStart > listTime.get("NONE")) {
					currentAction++;
					timeStart = System.currentTimeMillis();
				}
			} else if (currentAction == 1) {
				if (System.currentTimeMillis() - timeStart > listTime.get("RUN")) {
					currentAction++;
					timeStart = System.currentTimeMillis();
					if (getDirector() == DIR_LEFT) {
						setDirector(DIR_RIGHT);
					} else {
						setDirector(DIR_LEFT);
					}
				}
				run();
			} else if (currentAction == 2) {
				if ((getX() > getGame().getMario().getX() && getX() - getGame().getMario().getX() < 300)
						|| (getX() < getGame().getMario().getX() && getGame().getMario().getX() - getX() < 300)) {
					if (getX() < getGame().getMario().getX()) {
						setDirector(DIR_RIGHT);
					} else {
						setDirector(DIR_LEFT);
					}
					if (!isShooting) {
						for (int i = 0; i < runL.getListTimeForFrameImage().size(); i++) {
							runL.getListTimeForFrameImage().set(i, (double) 100000000);
							runR.getListTimeForFrameImage().set(i, (double) 100000000);
						}
						if (getGame().getMario().getX() > getX()) {
							xStart = getX() + 500;
						} else {
							xStart = getX() - 500;
						}
						isShooting = true;
					}
				} else {
					if (System.currentTimeMillis() - timeStart > listTime.get("ATTACK")) {
						currentAction = 0;
						timeStart = System.currentTimeMillis();
					}
				}
				attack();
			}
		}
	}

	public void attack() {
		if (isShooting) {

			if (Math.abs(getX() - xStart) > 10) {
				if (getX() > xStart) {
					setDirector(DIR_LEFT);
				} else {
					setDirector(DIR_RIGHT);
				}
			} else {
				timeStart = System.currentTimeMillis();
				currentAction = 0;
			}

			if (getDirector() == DIR_LEFT) {
				setSpeedX(-3);
			} else {
				setSpeedX(3);
			}
		}

	}

	public void run() {
		if (isRun) {
			if (getDirector() == DIR_LEFT) {
				setSpeedX(-1);
			} else {
				setSpeedX(1);
			}
		}
		if (!isRun) {
			isRun = true;
			xStart = getX();
		}
	}

	public void idle() {
		setSpeedX(0);
		if (isShooting) {
			isShooting = false;
			for (int i = 0; i < runL.getListTimeForFrameImage().size(); i++) {
				runL.getListTimeForFrameImage().set(i, (double) 150000000);
				runR.getListTimeForFrameImage().set(i, (double) 150000000);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		hb.draw(g2);

		if (getStage() == BEHURT) {

			if (getDirector() == DIR_LEFT) {
				beHurtL.upload(System.nanoTime());
				beHurtL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 10 - getGame().getCamera().getY()));

				if (beHurtL.isLastFrame()) {
					if (timeStartBehurt == 0) {
						timeStartBehurt = System.currentTimeMillis();
					}
				}
			} else {
				beHurtR.upload(System.nanoTime());
				beHurtR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 10 - getGame().getCamera().getY()));
				if (beHurtR.isLastFrame()) {
					if (timeStartBehurt == 0) {
						timeStartBehurt = System.currentTimeMillis();
					}
				}
			}

		} else {

			if (currentAction == 0) {
				if (getDirector() == DIR_LEFT) {
					idleL.upload(System.nanoTime());
					idleL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() + 5 - getGame().getCamera().getY()));
				} else {
					idleR.upload(System.nanoTime());
					idleR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() + 5 - getGame().getCamera().getY()));
				}
			} else if (currentAction == 1 || currentAction == 2) {
				if (getDirector() == DIR_LEFT) {
					runL.upload(System.nanoTime());
					runL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				} else {
					runR.upload(System.nanoTime());
					runR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 20, rect.y + 5, rect.width - 40, rect.height - 10);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();
		g2.setColor(Color.BLACK);
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
		if (getDirector() == DIR_LEFT) {
			getGame().getListWhenDead().addWhenDead(new WhenDeadMotLan(getX(), getY(), getGame(), deadL));
		} else {
			getGame().getListWhenDead().addWhenDead(new WhenDeadMotLan(getX(), getY(), getGame(), deadR));
		}

		int a = rd.nextInt(100);
		if (a <= 9) {
			LargeHealItemMapleStory item = new LargeHealItemMapleStory(getX(), getY(), getGame());
			item.setTimeStart(System.currentTimeMillis());
			getGame().getListItem().addItem(item);
		} else if (a >= 10 && a <= 29) {
			SmallHealItemMapleStory item = new SmallHealItemMapleStory(getX(), getY(), getGame());
			item.setTimeStart(System.currentTimeMillis());
			getGame().getListItem().addItem(item);
		}

	}

	public Animation getIdleL() {
		return idleL;
	}

	public void setIdleL(Animation idleL) {
		this.idleL = idleL;
	}

	public Animation getIdleR() {
		return idleR;
	}

	public void setIdleR(Animation idleR) {
		this.idleR = idleR;
	}

	public Animation getRunL() {
		return runL;
	}

	public void setRunL(Animation runL) {
		this.runL = runL;
	}

	public Animation getRunR() {
		return runR;
	}

	public void setRunR(Animation runR) {
		this.runR = runR;
	}

	public Animation getBeHurtL() {
		return beHurtL;
	}

	public void setBeHurtL(Animation beHurtL) {
		this.beHurtL = beHurtL;
	}

	public Animation getBeHurtR() {
		return beHurtR;
	}

	public void setBeHurtR(Animation beHurtR) {
		this.beHurtR = beHurtR;
	}

	public Animation getDeadL() {
		return deadL;
	}

	public void setDeadL(Animation deadL) {
		this.deadL = deadL;
	}

	public Animation getDeadR() {
		return deadR;
	}

	public void setDeadR(Animation deadR) {
		this.deadR = deadR;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public float getxStart() {
		return xStart;
	}

	public void setxStart(float xStart) {
		this.xStart = xStart;
	}

	public HashMap<String, Integer> getListTime() {
		return listTime;
	}

	public void setListTime(HashMap<String, Integer> listTime) {
		this.listTime = listTime;
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

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public long getTimeStartBehurt() {
		return timeStartBehurt;
	}

	public void setTimeStartBehurt(long timeStartBehurt) {
		this.timeStartBehurt = timeStartBehurt;
	}

	public HealBarEnemies getHb() {
		return hb;
	}

	public void setHb(HealBarEnemies hb) {
		this.hb = hb;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

}
