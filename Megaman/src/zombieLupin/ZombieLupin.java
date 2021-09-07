package zombieLupin;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;
import healthBarEnemyMapleStory.HealBarEnemies;
import item.LargeHealItemMapleStory;
import item.SmallHealItemMapleStory;
import particulerObject.HumanMapleStory;
import sound.ManagerSound;
import whenDeath.WhenDeadMotLan;

public class ZombieLupin extends HumanMapleStory {

	private HealBarEnemies hb;

	private HashMap<String, Integer> listTime;
	private String[] arrayAction;
	private int currentAction;
	private long timeStart;

	private long timeStartBehurt;
	private boolean isShooting;

	private Random rd;

	private Animation iL, iR;
	private Animation bL, bR;
	private Animation aL, aR;
	private Animation deadL, deadR;

	private int count = 0;

	public ZombieLupin(float x, float y, GameWorldMapleStory game) {
		super(x, y, game, 0.1f, 98, 128, 100, 5);

		hb = new HealBarEnemies("Zombie Lupin", 100, 100, getX() - 25 - getGame().getCamera().getX(),
				getY() - getGame().getCamera().getY(), 170, 30);

		setTeam(ENEMI_TEAM);
		setStage(ALIVE);

		setxPrimaral(getX());
		setyPrimaral(getY());
		setDirectorPrimaral(getDirector());
		setBloodPrimaral(getBlood());

		iL = new Animation(getGame().getInputData().getListAnimation().get("zli"));
		iR = new Animation(getGame().getInputData().getListAnimation().get("zli"));
		iR.daoNguoc();

		bL = new Animation(getGame().getInputData().getListAnimation().get("zlbehurt"));
		bR = new Animation(getGame().getInputData().getListAnimation().get("zlbehurt"));
		bR.daoNguoc();

		aL = new Animation(getGame().getInputData().getListAnimation().get("zla"));
		aR = new Animation(getGame().getInputData().getListAnimation().get("zla"));

		deadL = new Animation(getGame().getInputData().getListAnimation().get("zld"));
		deadR = new Animation(getGame().getInputData().getListAnimation().get("zld"));
		deadR.daoNguoc();

		aR.daoNguoc();

		listTime = new HashMap<String, Integer>();
		listTime.put("NONE", 1500);
		listTime.put("ATTACK", 20000);

		arrayAction = new String[2];
		arrayAction[0] = "NONE";
		arrayAction[1] = "ATTACK";

		currentAction = 0;
		timeStart = System.currentTimeMillis();

		rd = new Random();
	}

	@Override
	public void upload() {
		super.upload();

		if (getX() > getGame().getMario().getX() + getGame().getMario().getWidth()) {
			setDirector(DIR_LEFT);
		} else {
			setDirector(DIR_RIGHT);
		}

		hb.upload("Zombie Lupin", 100, getBlood(), getX() - 25 - getGame().getCamera().getX(),
				getY() - getGame().getCamera().getY());

		if (getStage() == BEHURT) {
			setSpeedX(0);
			if (System.currentTimeMillis() - timeStartBehurt > 300) {
				timeStartBehurt = 0;
				setStage(ALIVE);
			}
		} else {

			if (System.currentTimeMillis() - timeStart > listTime.get(arrayAction[currentAction])) {
				if (currentAction == arrayAction.length - 1) {
					currentAction = 0;
				} else {
					currentAction++;
				}
				timeStart = System.currentTimeMillis();
			}

			if (currentAction == 0) {
				idle();
			} else if (currentAction == 1) {
				attack();
				setSpeedX(0);
				if (getX() > getGame().getMario().getX() && getX() - getGame().getMario().getX() < 1000) {
					attack();

				} else if (getX() < getGame().getMario().getX() && getGame().getMario().getX() - getX() < 1000) {
					attack();
				} else {
					currentAction = 0;
					timeStart = System.currentTimeMillis();
				}
			}
		}
	}

	public void idle() {
		setSpeedX(0);
	}

	public void attack() {
		if (!isShooting) {
			isShooting = true;
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (getStage() == BEHURT) {
			if (getDirector() == DIR_LEFT) {
				bL.upload(System.nanoTime());
				bL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));

				if (bL.isLastFrame()) {
					if (timeStartBehurt == 0) {
						timeStartBehurt = System.currentTimeMillis();
					}
				}
			} else {
				bR.upload(System.nanoTime());
				bR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				if (bR.isLastFrame()) {
					if (timeStartBehurt == 0) {
						timeStartBehurt = System.currentTimeMillis();
					}
				}
			}
		} else {
			if (arrayAction[currentAction].equals("NONE")) {

				if (getDirector() == DIR_LEFT) {
					iL.upload(System.nanoTime());
					if (iL.getCurrentFrame() >= 4) {
						iL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					} else {
						iL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					}
				} else {
					iR.upload(System.nanoTime());
					iR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}
			} else if (isShooting) {
				if (getDirector() == DIR_LEFT) {
					aL.upload(System.nanoTime());

					if (aL.getCurrentFrame() == 5) {
						if (count == 0) {
							count++;
							getGame().getListBullet()
									.addObject(new BulletZombieLupin(getX(), getY() + 40, getGame(), getDirector()));
							ManagerSound.getInstance().getListSound().get("punch").start();
							try {
								ManagerSound.getInstance().khoiTaoHieuUng("punch");
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
								e.printStackTrace();
							}
						}
					}

					aL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));

					if (aL.isLastFrame()) {
						isShooting = false;
						currentAction = 0;
						count = 0;
						timeStart = System.currentTimeMillis();
					}

				} else {
					aR.upload(System.nanoTime());
					aR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
					if (aR.getCurrentFrame() == 5) {

						if (count == 0) {
							getGame().getListBullet()
									.addObject(new BulletZombieLupin(getX(), getY() + 40, getGame(), getDirector()));
							ManagerSound.getInstance().getListSound().get("punch").start();
							try {
								ManagerSound.getInstance().khoiTaoHieuUng("punch");
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
								e.printStackTrace();
							}
							count++;
						}
					}
					if (aR.isLastFrame()) {
						isShooting = false;
						currentAction = 0;
						count = 0;
						timeStart = System.currentTimeMillis();
					}
				}
			}
		}
		hb.draw(g2);

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 10, rect.y + 10, rect.width - 20, rect.height - 20);
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
			getGame().getListWhenDead().addWhenDead(new WhenDeadMotLan(getX(), getY() + 40, getGame(), deadL));
		} else {
			getGame().getListWhenDead().addWhenDead(new WhenDeadMotLan(getX(), getY() + 40, getGame(), deadR));
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

}
