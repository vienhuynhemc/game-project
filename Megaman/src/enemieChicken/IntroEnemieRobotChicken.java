package enemieChicken;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import backGroundMap.AnimationBackGroundMap;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import item.LargeHealItem;
import item.SmallHealItem;
import particulerObject.ParticulerObjectMegamanX;
import robotBunkerShot.RobotBunkerShotBullet;
import sound.ManagerSound;

public class IntroEnemieRobotChicken extends ParticulerObjectMegamanX {

	private Animation idleLeft, idleRight;
	private Animation attack1Left, attack1Right;
	private Animation attack2Left, attack2Right;

	private boolean isShooting;
	private long timeForShooting;
	private long lastTimeShooting;

	private HashMap<String, Integer> listTime;
	private String[] arrayAction;
	private int currentAction;
	private long timeStart;

	private Random rd;

	private List<AnimationBackGroundMap> listAttack;
	private Animation smoke;

	private int dan;

	public IntroEnemieRobotChicken(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0.1f, 82, 90, 90, 5);

		idleLeft = new Animation(getGame().getInputData().getListAnimation().get("introEnemieRobotChickenIdle"));
		idleRight = new Animation(getGame().getInputData().getListAnimation().get("introEnemieRobotChickenIdle"));
		idleRight.daoNguoc();
		attack1Left = new Animation(
				getGame().getInputData().getListAnimation().get("introEnemieRobotChickenAttackBullet1"));
		attack1Right = new Animation(
				getGame().getInputData().getListAnimation().get("introEnemieRobotChickenAttackBullet1"));
		attack1Right.daoNguoc();
		attack2Left = new Animation(
				getGame().getInputData().getListAnimation().get("introEnemieRobotChickenAttackBullet2"));
		attack2Right = new Animation(
				getGame().getInputData().getListAnimation().get("introEnemieRobotChickenAttackBullet2"));
		attack2Right.daoNguoc();

		listTime = new HashMap<String, Integer>();
		listTime.put("ATTACKROCKET", 700);
		listTime.put("NONE", 1000);
		listTime.put("ATTACKDANPHAO", 2000);

		arrayAction = new String[4];
		arrayAction[0] = "NONE";
		arrayAction[1] = "ATTACKROCKET";
		arrayAction[2] = "NONE";
		arrayAction[3] = "ATTACKDANPHAO";

		currentAction = 0;
		timeForShooting = 400000000;

		rd = new Random();

		listAttack = new ArrayList<AnimationBackGroundMap>();
		smoke = new Animation(getGame().getInputData().getListAnimation().get("smoke"));

		setDirector(director);
		setTeam(ENEMI_TEAM);
		setStage(ALIVE);

		setxPrimaral(getX());
		setyPrimaral(getY());
		setDirectorPrimaral(getDirector());
		setBloodPrimaral(getBlood());

		dan = 0;
	}

	@Override
	public void upload() {
		super.upload();

		if (timeStart == 0)
			timeStart = System.nanoTime();

		uploadDungTrenMatDatVaDIRECTOR();

		if (isShooting) {
			if (System.nanoTime() - lastTimeShooting > timeForShooting) {
				setShooting(false);
			}
		} else {
			if (currentAction == 1) {
				if ((getX() > getGame().getRockMan().getX() && getX() - getGame().getRockMan().getX() < 650)
						|| (getX() < getGame().getRockMan().getX() && getGame().getRockMan().getX() - getX() < 650)) {
					attackRocket();
					if (System.nanoTime() - timeStart > listTime.get("ATTACKROCKET") * 1000000) {
						currentAction++;
						timeStart = System.nanoTime();
					}
				} else {
					currentAction++;
					timeStart = System.nanoTime();
				}
			} else if (currentAction == 0 || currentAction == 2) {
				if (System.nanoTime() - timeStart > listTime.get("NONE") * 1000000) {
					currentAction++;
					timeStart = System.nanoTime();
				}
			} else if (currentAction == 3) {
				if ((getX() > getGame().getRockMan().getX() && getX() - getGame().getRockMan().getX() < 650)
						|| (getX() < getGame().getRockMan().getX() && getGame().getRockMan().getX() - getX() < 650)) {
					attackDanPhao();
					if (System.nanoTime() - timeStart > listTime.get("ATTACKDANPHAO") * 1000000) {
						currentAction = 0;
						timeStart = System.nanoTime();
					}
				} else {
					currentAction = 0;
					timeStart = System.nanoTime();
				}
			}
		}
	}

	public void attackRocket() {
		dan++;
		IntroEnemieChickenBullet bullet = new IntroEnemieChickenBullet(getX(), getY(), getGame(), getDirector());

		if (dan == 1) {
			bullet.setY(bullet.getY() + 15);
		} else if (dan == 2) {
			bullet.setY(bullet.getY() + 30);
			dan = 0;
		}

		if (getDirector() == DIR_LEFT) {
			bullet.setSpeedX(-2);
		} else if (getDirector() == DIR_RIGHT) {
			bullet.setSpeedX(2);
			bullet.setX(bullet.getX() + 60);
		}

		getGame().getListBullet().addObject(bullet);
		setShooting(true);
		lastTimeShooting = System.nanoTime();
	}

	public void attackDanPhao() {
		RobotBunkerShotBullet bullet1 = new RobotBunkerShotBullet(getX(), getY(), getGame());
		RobotBunkerShotBullet bullet2 = new RobotBunkerShotBullet(getX(), getY(), getGame());

		bullet1.setSpeedX((getGame().getRockMan().getX() - getX()) / 110);
		bullet1.setSpeedY(-6);
		bullet2.setSpeedX((getGame().getRockMan().getX() - getX()) / 110);
		bullet2.setSpeedY(-6);

		bullet1.setY(bullet1.getY() - 10);
		bullet2.setY(bullet2.getY() - 10);

		ManagerSound.getInstance().getListSound().get("locotban").start();
		try {
			ManagerSound.getInstance().khoiTaoHieuUng("locotban");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		if (getDirector() == DIR_LEFT) {
			bullet1.setX(bullet1.getX() + 18);
			bullet2.setX(bullet2.getX() + 36);
		} else if (getDirector() == DIR_RIGHT) {
			bullet1.setX(bullet1.getX() + 36);
			bullet2.setX(bullet2.getX() + 54);
		}

		AnimationBackGroundMap anime1 = new AnimationBackGroundMap(bullet1.getX(), bullet1.getY(), getGame(),
				new Animation(smoke), 28, 28);
		AnimationBackGroundMap anime2 = new AnimationBackGroundMap(bullet2.getX(), bullet2.getY(), getGame(),
				new Animation(smoke), 28, 28);

		listAttack.add(anime1);
		listAttack.add(anime2);

		getGame().getListBullet().addObject(bullet1);
		getGame().getListBullet().addObject(bullet2);

		setShooting(true);
		lastTimeShooting = System.nanoTime();
	}

	public void uploadDungTrenMatDatVaDIRECTOR() {
		if (getX() > getGame().getRockMan().getX()) {
			setDirector(DIR_LEFT);
		} else {
			setDirector(DIR_RIGHT);
		}

		Rectangle rect = getGame().getPhysicalMap().vaChamMatDat(getRectangleXuLiVaCham());
		if (rect == null) {
			setY(getY() + getSpeedY());
			setSpeedY(getSpeedY() + getMass());
		} else {
			setSpeedY(0);
			setY(rect.y - getHeight() + 1);
		}
	}

	@Override
	public void getToDeath() {
		ManagerSound.getInstance().getListSound().get("bang").start();
		try {
			ManagerSound.getInstance().khoiTaoHieuUng("bang");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		WhenIntroEnemieRobotChickenDeath dead = new WhenIntroEnemieRobotChickenDeath(getX(), getY(), getGame());
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

		if (currentAction == 1) {
			if (getDirector() == DIR_LEFT) {
				if (dan == 1) {
					attack1Left.upload(System.nanoTime());
					attack1Left.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				} else {
					attack2Left.upload(System.nanoTime());
					attack2Left.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}
			} else if (getDirector() == DIR_RIGHT) {
				if (dan == 1) {
					attack1Right.upload(System.nanoTime());
					attack1Right.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				} else {
					attack2Right.upload(System.nanoTime());
					attack2Right.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}
			}
		} else {
			if (getDirector() == DIR_LEFT) {
				idleLeft.upload(System.nanoTime());
				idleLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else {
				idleRight.upload(System.nanoTime());
				idleRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}

			for (int i = 0; i < listAttack.size(); i++) {
				if (!listAttack.get(i).isOutCamera()) {
					listAttack.get(i).upload();
					listAttack.get(i).draw(g2);
					if (listAttack.get(i).getAnime().isLastFrame()) {
						listAttack.remove(i);
					}
				}
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 14, rect.y, rect.width - 28, rect.height);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public long getTimeForShooting() {
		return timeForShooting;
	}

	public void setTimeForShooting(long timeForShooting) {
		this.timeForShooting = timeForShooting;
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

	public Animation getAttack1Left() {
		return attack1Left;
	}

	public void setAttack1Left(Animation attack1Left) {
		this.attack1Left = attack1Left;
	}

	public Animation getAttack1Right() {
		return attack1Right;
	}

	public void setAttack1Right(Animation attack1Right) {
		this.attack1Right = attack1Right;
	}

	public Animation getAttack2Left() {
		return attack2Left;
	}

	public void setAttack2Left(Animation attack2Left) {
		this.attack2Left = attack2Left;
	}

	public Animation getAttack2Right() {
		return attack2Right;
	}

	public void setAttack2Right(Animation attack2Right) {
		this.attack2Right = attack2Right;
	}

	public long getLastTimeShooting() {
		return lastTimeShooting;
	}

	public void setLastTimeShooting(long lastTimeShooting) {
		this.lastTimeShooting = lastTimeShooting;
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

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

	public List<AnimationBackGroundMap> getListAttack() {
		return listAttack;
	}

	public void setListAttack(List<AnimationBackGroundMap> listAttack) {
		this.listAttack = listAttack;
	}

	public Animation getSmoke() {
		return smoke;
	}

	public void setSmoke(Animation smoke) {
		this.smoke = smoke;
	}

	public int getDan() {
		return dan;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}

}
