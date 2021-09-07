package yeti;

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

public class Yeti extends HumanMapleStory {

	public static final int SMALL = 0;
	public static final int LAGER = 1;

	private int STAGE;

	private HealBarEnemies hb;

	private HashMap<String, Integer> listTime;
	private String[] arrayAction;
	private int currentAction;
	private long timeStart;

	private long timeStartBehurt;

	private Random rd;

	private long timeSwitchDir;
	private boolean isSwich;

	private Animation deadL, deadR;
	private Animation behurtSL, behurtSR, behurtLL, behurtLR;
	private Animation idleSL, idleSR, idleLL, idleLR;
	private Animation jumpSL, jumpSR;
	private Animation runSL, runSR, runLL, runLR;
	private Animation yLL, yLR;
	private Animation aL, aR;

	private boolean isShooting;

	public Yeti(float x, float y, GameWorldMapleStory game, int dir, int stage) {
		super(x, y, game, 0.1f, 200, 214, 100, 5);

		hb = new HealBarEnemies("Yeti", 100, 100, getX() - getGame().getCamera().getX(),
				getY() - getGame().getCamera().getY() + 120, 110, 30);

		STAGE = stage;
		setTeam(ENEMI_TEAM);
		setStage(ALIVE);

		setStage(getStage());

		setxPrimaral(getX());
		setyPrimaral(getY());
		setDirectorPrimaral(getDirector());
		setBloodPrimaral(getBlood());

		behurtSL = new Animation(getGame().getInputData().getListAnimation().get("yetiBehurtS"));
		behurtSR = new Animation(getGame().getInputData().getListAnimation().get("yetiBehurtS"));
		behurtSR.daoNguoc();

		behurtLL = new Animation(getGame().getInputData().getListAnimation().get("yetiBehurtL"));
		behurtLR = new Animation(getGame().getInputData().getListAnimation().get("yetiBehurtL"));
		behurtLR.daoNguoc();

		idleSL = new Animation(getGame().getInputData().getListAnimation().get("yetiIdleS"));
		idleSR = new Animation(getGame().getInputData().getListAnimation().get("yetiIdleS"));
		idleSR.daoNguoc();
		idleLL = new Animation(getGame().getInputData().getListAnimation().get("yetiIdleL"));
		idleLR = new Animation(getGame().getInputData().getListAnimation().get("yetiIdleL"));
		idleLR.daoNguoc();

		runSL = new Animation(getGame().getInputData().getListAnimation().get("yetiRunS"));
		runSR = new Animation(getGame().getInputData().getListAnimation().get("yetiRunS"));
		runSR.daoNguoc();
		runLL = new Animation(getGame().getInputData().getListAnimation().get("yetiRunL"));
		runLR = new Animation(getGame().getInputData().getListAnimation().get("yetiRunL"));
		runLR.daoNguoc();

		jumpSL = new Animation(getGame().getInputData().getListAnimation().get("yetiJumpS"));
		jumpSR = new Animation(getGame().getInputData().getListAnimation().get("yetiJumpS"));
		jumpSR.daoNguoc();

		yLL = new Animation(getGame().getInputData().getListAnimation().get("yL"));
		yLR = new Animation(getGame().getInputData().getListAnimation().get("yL"));
		yLR.daoNguoc();

		deadL = new Animation(getGame().getInputData().getListAnimation().get("yetiDead"));
		deadR = new Animation(getGame().getInputData().getListAnimation().get("yetiDead"));
		deadR.daoNguoc();

		aL = new Animation(getGame().getInputData().getListAnimation().get("ytA"));
		aR = new Animation(getGame().getInputData().getListAnimation().get("ytA"));
		aR.daoNguoc();

		listTime = new HashMap<String, Integer>();
		listTime.put("NONE", 1000);
		listTime.put("RUN", 4000);
		listTime.put("JUMP", 20000);

		arrayAction = new String[3];
		arrayAction[0] = "NONE";
		arrayAction[1] = "RUN";
		arrayAction[2] = "JUMP";

		currentAction = 0;
		timeStart = System.currentTimeMillis();

		rd = new Random();
	}

	@Override
	public void upload() {
		super.upload();

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

			switch (STAGE) {
			case SMALL:

				if (isSwich) {
					if (System.currentTimeMillis() - timeSwitchDir > 10000) {
						if (getDirector() == DIR_LEFT) {
							setDirector(DIR_RIGHT);
						} else {
							setDirector(DIR_LEFT);
						}
						isSwich = false;
					}
				} else {
					timeSwitchDir = System.currentTimeMillis();
					isSwich = true;
				}

				hb.upload("Yeti", 100, getBlood(), getX() - getGame().getCamera().getX(),
						getY() - getGame().getCamera().getY() + 130);

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
					run();
				} else if (currentAction == 2) {
					jump();
					if (getSpeedY() == 0 && getIsJumping()) {
						currentAction = 0;
						setIsJumping(false);
						timeStart = System.currentTimeMillis();
					}
				}
				break;

			case LAGER:

				if (!isShooting) {
					if (isSwich) {
						if (System.currentTimeMillis() - timeSwitchDir > 10000) {
							if (getDirector() == DIR_LEFT) {
								setDirector(DIR_RIGHT);
							} else {
								setDirector(DIR_LEFT);
							}
							isSwich = false;
						}
					} else {
						timeSwitchDir = System.currentTimeMillis();
						isSwich = true;
					}
				}

				hb.upload("Yeti Lager", 200, getBlood(), getX() + 30 - getGame().getCamera().getX(),
						getY() - getGame().getCamera().getY());

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
					run();
				} else if (currentAction == 2) {
					setSpeedX(0);
					if (getX() > getGame().getMario().getX() && getX() - getGame().getMario().getX() < 200) {
						setDirector(DIR_LEFT);
						attack();

					} else if (getX() < getGame().getMario().getX() && getGame().getMario().getX() - getX() < 300) {
						setDirector(DIR_RIGHT);
						attack();
					} else {
						currentAction = 0;
						timeStart = System.currentTimeMillis();
					}
				}

				break;

			default:
				break;
			}
		}
	}

	public void attack() {
		if (!isShooting) {
			isShooting = true;
		}
	}

	public void idle() {
		setSpeedX(0);
		setSpeedY(0);
	}

	public void run() {
		if (getDirector() == DIR_LEFT) {
			setSpeedX(-1);
		} else {
			setSpeedX(1);
		}
	}

	public void jump() {
		if (!getIsJumping()) {
			setIsJumping(true);
			setY(getY() - 5);
			setSpeedY(-5);
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (getBlood() <= 40 && STAGE == SMALL) {
			hb.setW(150);
			setSpeedX(0);
			if (getDirector() == DIR_LEFT) {
				yLL.upload(System.nanoTime());
				yLL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 10 - getGame().getCamera().getY()));

				if (yLL.isLastFrame()) {
					setBlood(200);
					STAGE = LAGER;
				}

			} else {
				yLR.upload(System.nanoTime());
				yLR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 10 - getGame().getCamera().getY()));
				if (yLR.isLastFrame()) {
					setBlood(200);
					STAGE = LAGER;
				}
			}

		} else {

			if (getStage() == BEHURT) {

				if (STAGE == SMALL) {
					if (getDirector() == DIR_LEFT) {
						behurtSL.upload(System.nanoTime());
						behurtSL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() + 100 - getGame().getCamera().getY()));

						if (behurtSL.isLastFrame()) {
							if (timeStartBehurt == 0) {
								timeStartBehurt = System.currentTimeMillis();
							}
						}

					} else {
						behurtSR.upload(System.nanoTime());
						behurtSR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() + 100 - getGame().getCamera().getY()));
						if (behurtSR.isLastFrame()) {
							if (timeStartBehurt == 0) {
								timeStartBehurt = System.currentTimeMillis();
							}
						}
					}
				} else {
					if (getDirector() == DIR_LEFT) {
						behurtLL.upload(System.nanoTime());
						behurtLL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - 10 - getGame().getCamera().getY()));

						if (behurtLL.isLastFrame()) {
							if (timeStartBehurt == 0) {
								timeStartBehurt = System.currentTimeMillis();
							}
						}

					} else {
						behurtLR.upload(System.nanoTime());
						behurtLR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - 10 - getGame().getCamera().getY()));
						if (behurtLR.isLastFrame()) {
							if (timeStartBehurt == 0) {
								timeStartBehurt = System.currentTimeMillis();
							}
						}
					}
				}

			} else {
				if (arrayAction[currentAction].equals("NONE")) {
					if (STAGE == SMALL) {
						if (getDirector() == DIR_LEFT) {
							idleSL.upload(System.nanoTime());
							if (idleSL.getCurrentFrame() >= 4) {
								idleSL.draw(g2, (int) (getX() - 50 - getGame().getCamera().getX()),
										(int) (getY() + 80 - getGame().getCamera().getY()));
							} else {
								idleSL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() + 80 - getGame().getCamera().getY()));
							}
						} else {
							idleSR.upload(System.nanoTime());
							idleSR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 80 - getGame().getCamera().getY()));
						}
					} else {
						if (getDirector() == DIR_LEFT) {
							idleLL.upload(System.nanoTime());
							if (idleLL.getCurrentFrame() >= 4) {
								idleLL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else {
								idleLL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							}
						} else {
							idleLR.upload(System.nanoTime());
							idleLR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						}
					}
				} else if (arrayAction[currentAction].equals("RUN")) {
					if (STAGE == SMALL) {
						if (getDirector() == DIR_LEFT) {
							runSL.upload(System.nanoTime());
							runSL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 80 - getGame().getCamera().getY()));

						} else {
							runSR.upload(System.nanoTime());
							runSR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 80 - getGame().getCamera().getY()));
						}
					} else {
						if (getDirector() == DIR_LEFT) {
							runLL.upload(System.nanoTime());
							runLL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));

						} else {
							runLR.upload(System.nanoTime());
							if (runLR.getCurrentFrame() == 0) {
								runLR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (runLR.getCurrentFrame() == 1) {
								runLR.draw(g2, (int) (getX() - 16 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (runLR.getCurrentFrame() == 2) {
								runLR.draw(g2, (int) (getX() + 8 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (runLR.getCurrentFrame() == 3) {
								runLR.draw(g2, (int) (getX() - 34 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							}
						}
					}
				} else if (arrayAction[currentAction].equals("JUMP")) {
					if (STAGE == SMALL) {
						if (getDirector() == DIR_LEFT) {
							jumpSL.upload(System.nanoTime());
							jumpSL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 120 - getGame().getCamera().getY()));

						} else {
							jumpSR.upload(System.nanoTime());
							jumpSR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 120 - getGame().getCamera().getY()));
						}
					} else {
						if (isShooting) {
							if (getDirector() == DIR_LEFT) {
								aL.upload(System.nanoTime());
								if (aL.getCurrentFrame() == 6) {
									aL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - 160 - getGame().getCamera().getY()));
								} else if (aL.getCurrentFrame() == 7) {
									aL.draw(g2, (int) (getX() - 26 - getGame().getCamera().getX()),
											(int) (getY() - 160 - getGame().getCamera().getY()));
								} else if (aL.getCurrentFrame() == 8 || aL.getCurrentFrame() == 9) {
									aL.draw(g2, (int) (getX() - 32 - getGame().getCamera().getX()),
											(int) (getY() - 160 - getGame().getCamera().getY()));
								} else if (aL.getCurrentFrame() == 10) {
									aL.draw(g2, (int) (getX() - 188 - getGame().getCamera().getX()),
											(int) (getY() - 160 - getGame().getCamera().getY()));
									PunchYeti p = new PunchYeti(getX() - 100, getY(), getGame());
									getGame().getListBullet().addObject(p);
									ManagerSound.getInstance().getListSound().get("bang").start();
									try {
										ManagerSound.getInstance().khoiTaoHieuUng("bang");
									} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
										e.printStackTrace();
									}
								} else if (aL.getCurrentFrame() == 11) {
									aL.draw(g2, (int) (getX() - 202 - getGame().getCamera().getX()),
											(int) (getY() - 160 - getGame().getCamera().getY()));
								} else if (aL.getCurrentFrame() == 12) {
									aL.draw(g2, (int) (getX() - 208 - getGame().getCamera().getX()),
											(int) (getY() - 160 - getGame().getCamera().getY()));
								} else if (aL.getCurrentFrame() == 13) {
									aL.draw(g2, (int) (getX() - 194 - getGame().getCamera().getX()),
											(int) (getY() - 160 - getGame().getCamera().getY()));
								} else if (aL.getCurrentFrame() == 14) {
									aL.draw(g2, (int) (getX() - 118 - getGame().getCamera().getX()),
											(int) (getY() - 160 - getGame().getCamera().getY()));
								} else {
									aL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								}
								if (aL.isLastFrame()) {
									currentAction = 0;
									timeStart = System.currentTimeMillis();
									isShooting = false;
								}
							} else {
								aR.upload(System.nanoTime());
								if (aR.getCurrentFrame() >= 6) {

									aR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - 160 - getGame().getCamera().getY()));
									if (aR.getCurrentFrame() == 10) {
										PunchYeti p = new PunchYeti(getX() + 150, getY(), getGame());
										getGame().getListBullet().addObject(p);
										ManagerSound.getInstance().getListSound().get("bang")
												.start();
										try {
											ManagerSound.getInstance().khoiTaoHieuUng("bang");
										} catch (UnsupportedAudioFileException | IOException
												| LineUnavailableException e) {
											e.printStackTrace();
										}
									}
								} else {
									aR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								}
								if (aR.isLastFrame()) {
									currentAction = 0;
									timeStart = System.currentTimeMillis();
									isShooting = false;
								}
							}
						}
					}
				}
			}
		}

		hb.draw(g2);

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		if (STAGE == SMALL) {
			return new Rectangle(rect.x + 10, rect.y + 140, rect.width - 140, rect.height - 140);
		} else {
			return new Rectangle(rect.x + 20, rect.y, rect.width - 40, rect.height);
		}
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

	public int getSTAGE() {
		return STAGE;
	}

	public void setSTAGE(int sTAGE) {
		STAGE = sTAGE;
	}

	public HealBarEnemies getHb() {
		return hb;
	}

	public void setHb(HealBarEnemies hb) {
		this.hb = hb;
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

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

	public long getTimeSwitchDir() {
		return timeSwitchDir;
	}

	public void setTimeSwitchDir(long timeSwitchDir) {
		this.timeSwitchDir = timeSwitchDir;
	}

	public boolean isSwich() {
		return isSwich;
	}

	public void setSwich(boolean isSwich) {
		this.isSwich = isSwich;
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

	public Animation getBehurtSL() {
		return behurtSL;
	}

	public void setBehurtSL(Animation behurtSL) {
		this.behurtSL = behurtSL;
	}

	public Animation getBehurtSR() {
		return behurtSR;
	}

	public void setBehurtSR(Animation behurtSR) {
		this.behurtSR = behurtSR;
	}

	public Animation getBehurtLL() {
		return behurtLL;
	}

	public void setBehurtLL(Animation behurtLL) {
		this.behurtLL = behurtLL;
	}

	public Animation getBehurtLR() {
		return behurtLR;
	}

	public void setBehurtLR(Animation behurtLR) {
		this.behurtLR = behurtLR;
	}

	public Animation getIdleSL() {
		return idleSL;
	}

	public void setIdleSL(Animation idleSL) {
		this.idleSL = idleSL;
	}

	public Animation getIdleSR() {
		return idleSR;
	}

	public void setIdleSR(Animation idleSR) {
		this.idleSR = idleSR;
	}

	public Animation getIdleLL() {
		return idleLL;
	}

	public void setIdleLL(Animation idleLL) {
		this.idleLL = idleLL;
	}

	public Animation getIdleLR() {
		return idleLR;
	}

	public void setIdleLR(Animation idleLR) {
		this.idleLR = idleLR;
	}

	public Animation getJumpSL() {
		return jumpSL;
	}

	public void setJumpSL(Animation jumpSL) {
		this.jumpSL = jumpSL;
	}

	public Animation getJumpSR() {
		return jumpSR;
	}

	public void setJumpSR(Animation jumpSR) {
		this.jumpSR = jumpSR;
	}

	public Animation getRunSL() {
		return runSL;
	}

	public void setRunSL(Animation runSL) {
		this.runSL = runSL;
	}

	public Animation getRunSR() {
		return runSR;
	}

	public void setRunSR(Animation runSR) {
		this.runSR = runSR;
	}

	public Animation getRunLL() {
		return runLL;
	}

	public void setRunLL(Animation runLL) {
		this.runLL = runLL;
	}

	public Animation getRunLR() {
		return runLR;
	}

	public void setRunLR(Animation runLR) {
		this.runLR = runLR;
	}

	public Animation getyLL() {
		return yLL;
	}

	public void setyLL(Animation yLL) {
		this.yLL = yLL;
	}

	public Animation getyLR() {
		return yLR;
	}

	public void setyLR(Animation yLR) {
		this.yLR = yLR;
	}

	public static int getSmall() {
		return SMALL;
	}

	public static int getLager() {
		return LAGER;
	}

	public Animation getaL() {
		return aL;
	}

	public void setaL(Animation aL) {
		this.aL = aL;
	}

	public Animation getaR() {
		return aR;
	}

	public void setaR(Animation aR) {
		this.aR = aR;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

}
