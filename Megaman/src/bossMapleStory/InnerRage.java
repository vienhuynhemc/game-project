package bossMapleStory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import blackHole.BlackHoleMapleStory;
import efforOpacity.DrawRectangleOpacity;
import gameEffect.Animation;
import gameWorld.GameWorld;
import gameWorld.GameWorldMapleStory;
import onScreen.InnerRageHealthBar;
import particulerObject.HumanMapleStory;
import sound.ManagerSound;

public class InnerRage extends HumanMapleStory {

	private boolean isPlayTutorial;

	private DrawRectangleOpacity drawRect;

	private HashMap<String, Integer> listTimeForAction;
	private String[] listAction;
	private int currentAction;
	private long timeStartAction;

	private InnerRageHealthBar thanhHpBoss;

	private boolean isReady;
	private boolean isXuatHien;
	private boolean isDrawThanhHp;

	private boolean isShooting;
	private long timeStartShooting;

	private Animation bl, br;
	private Animation il, ir;
	private Animation rl, rr;
	private Animation a1l, a1r;
	private Animation a2;

	private int directionAttack;
	private float xCu;
	private float yCu;

	private long timeStartBehurt;

	private boolean isDead;
	private boolean isDraw;
	private boolean isCreateBlackHole;

	private long timeSwitchDir;
	private boolean isSwich;

	private int count;

	private Random rd;

	public InnerRage(float x, float y, GameWorldMapleStory game) {
		super(x, y, game, 0, 139, 181, 400, 10);

		setDirector(DIR_LEFT);
		setTeam(ENEMI_TEAM);

		isPlayTutorial = true;

		drawRect = new DrawRectangleOpacity(Color.WHITE);

		thanhHpBoss = new InnerRageHealthBar(940, 130, getGame());

		bl = new Animation(getGame().getInputData().getListAnimation().get("irb"));
		br = new Animation(getGame().getInputData().getListAnimation().get("irb"));
		bl.daoNguoc();
		bl.setHeSo(1);
		br.setHeSo(1);

		il = new Animation(getGame().getInputData().getListAnimation().get("iri"));
		ir = new Animation(getGame().getInputData().getListAnimation().get("iri"));
		il.daoNguoc();
		il.setHeSo(1);
		ir.setHeSo(1);

		rl = new Animation(getGame().getInputData().getListAnimation().get("irr"));
		rr = new Animation(getGame().getInputData().getListAnimation().get("irr"));
		rl.daoNguoc();
		rl.setHeSo(1);
		rr.setHeSo(1);

		a1l = new Animation(getGame().getInputData().getListAnimation().get("ira1"));
		a1r = new Animation(getGame().getInputData().getListAnimation().get("ira1"));
		a1l.daoNguoc();
		a1l.setHeSo(1);
		a1r.setHeSo(1);

		a2 = new Animation(getGame().getInputData().getListAnimation().get("ira2"));
		a2.setHeSo(1);

		listAction = new String[11];
		listAction[0] = "NONE";
		listAction[1] = "RUN";
		listAction[2] = "ATTACK1";
		listAction[3] = "RUN";
		listAction[4] = "ATTACK2";
		listAction[5] = "RUN";
		listAction[6] = "NONE";
		listAction[7] = "ATTACK1";
		listAction[8] = "RUN";
		listAction[9] = "ATTACK1";
		listAction[10] = "ATTACK2";

		listTimeForAction = new HashMap<String, Integer>();
		listTimeForAction.put("NONE", 1500);
		listTimeForAction.put("RUN", 1500);
		listTimeForAction.put("ATTACK1", 20000);
		listTimeForAction.put("ATTACK2", 20000);
		currentAction = 0;

		isDraw = true;

		rd = new Random();

	}

	@Override
	public void upload() {

		if (getGame().getListTutorial().size() == 1) {
			isXuatHien = true;
		}

		if (!isDead) {
			if (listAction[currentAction].compareTo("ATTACK1") != 0
					&& listAction[currentAction].compareTo("ATTACK2") != 0) {
				super.upload();
			}
			if (getStage() == NOBEHURT) {
				setSpeedX(0);
				setSpeedY(0);
				if (listAction[currentAction].compareTo("ATTACK1") != 0
						&& listAction[currentAction].compareTo("ATTACK2") != 0) {
					if (System.currentTimeMillis() - timeStartBehurt > 300) {
						timeStartBehurt = 0;
						setStage(ALIVE);
					}
				}
			} else {

				if (!isOutCamera() && isPlayTutorial && getGame().getSTAGE() == GameWorldMapleStory.ATTACKBOSS) {
					getGame().setSTAGE(GameWorldMapleStory.PLAYTUTORIAL);
					isPlayTutorial = false;
				}

				if (isXuatHien) {
					isDrawThanhHp = true;
					isReady = true;
				}

				if (isDrawThanhHp) {
					thanhHpBoss.upload();
				}

				if (!thanhHpBoss.isReady()) {
					getGame().getAction().getActionKeyForMapbleStory().lock();
					timeStartAction = System.currentTimeMillis();
				}

				if (isReady) {

					if (thanhHpBoss.isReady()) {

						Rectangle r = getRectangleSizeObject();
						Rectangle rr = new Rectangle(r.x - 10, r.y - 10, r.width + 20, r.height + 20);
						if (getGame().getPhysicalMap().vaChamBenTrai(rr) != null) {
							setDirector(DIR_RIGHT);
						}

						if (getGame().getPhysicalMap().vaChamBenPhai(rr) != null) {
							setDirector(DIR_LEFT);
						}

						if (isSwich) {
							if (System.currentTimeMillis() - timeSwitchDir > 5000) {
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

						if (getGame().getAction().getActionKeyForMapbleStory().isLock) {
							getGame().getAction().getActionKeyForMapbleStory().unlock();
						}
						if (System.currentTimeMillis() - timeStartAction > listTimeForAction
								.get(listAction[currentAction])) {

							timeStartAction = System.currentTimeMillis();
							if (currentAction == listAction.length - 1) {
								currentAction = 0;
							} else {
								currentAction++;
							}

						} else {
							switch (listAction[currentAction]) {

							case "NONE":
								idle();
								break;

							case "RUN":
								run();
								break;
							case "ATTACK1":
								attack1();
								break;
							case "ATTACK2":
								attack2();
								break;

							default:
								break;

							}
						}
					}

					if (isShooting) {
						if (System.nanoTime() - timeStartShooting > 300000000) {
							isShooting = false;
						}
					}

				}
			}
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
			setSpeedX(+1);
		}
	}

	public void attack1() {

		if (!isShooting) {

			float xNow = getGame().getMario().getX() - 170;
			float yNow = getGame().getMario().getY() - 75;
			directionAttack = DIR_LEFT;
			if (xNow < 30 * 290) {
				directionAttack = DIR_RIGHT;
				xNow = getGame().getMario().getX() + 113;
			}

			xCu = getX();
			yCu = getY();

			setX(xNow);
			setY(yNow);

			setSpeedX(0);
			setSpeedY(0);
			setStage(NOBEHURT);
			isShooting = true;

			ManagerSound.getInstance().getListSound().get("skill1boss").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("skill1boss");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}

		}
	}

	public void attack2() {

		if (!isShooting) {

			float xNow = 30 * 291;
			float yNow = getY();

			int a = rd.nextInt(800);
			int b = rd.nextInt(150);

			xCu = getX();
			yCu = getY();

			setX(xNow + a);
			setY(yNow + b);

			setSpeedX(0);
			setSpeedY(0);
			setStage(NOBEHURT);
			isShooting = true;

			ManagerSound.getInstance().getListSound().get("skill2").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("skill2");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void draw(Graphics2D g2) {

		if (isDead) {
			if (!isDraw) {
				isDraw = true;
				setTeam(LEGUE_TEAM);
			}

			drawRect.upload();
			drawRect.draw(g2);
			if (drawRect.getStage() == DrawRectangleOpacity.END) {
				if (!isCreateBlackHole) {

					isCreateBlackHole = true;

					BlackHoleMapleStory blackHole = new BlackHoleMapleStory(9100, 1300, getGame());
					getGame().getListObject().getListObject().add(0, blackHole);

					ManagerSound.getInstance().getListSound().get("blackHole").start();

					getGame().setSTAGE(GameWorld.PLAYTUTORIALNOTRECTANGLE);

					getGame().getDrawRectangeEnd().setColor(Color.BLACK);

					this.setStage(DEATH);
				}
			}
		}

		if (!isDead) {
			if (isDraw) {
				if (getStage() == BEHURT) {
					if (getDirector() == DIR_LEFT) {
						bl.upload(System.nanoTime());
						bl.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
						if (bl.isLastFrame()) {
							setStage(NOBEHURT);
							if (timeStartBehurt == 0) {
								timeStartBehurt = System.currentTimeMillis();
							}
						}
					} else {
						br.upload(System.nanoTime());
						br.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
						if (br.isLastFrame()) {
							setStage(NOBEHURT);
							if (timeStartBehurt == 0) {
								timeStartBehurt = System.currentTimeMillis();
							}
						}
					}
				} else {
					if (listAction[currentAction].equals("NONE")) {
						if (getDirector() == DIR_LEFT) {
							il.upload(System.nanoTime());
							il.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else {
							ir.upload(System.nanoTime());
							ir.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						}
					} else if (listAction[currentAction].equals("RUN")) {
						if (getDirector() == DIR_LEFT) {
							rl.upload(System.nanoTime());
							rl.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else {
							rr.upload(System.nanoTime());
							rr.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						}
					} else if (listAction[currentAction].equals("ATTACK1")) {
						if (directionAttack == DIR_LEFT) {
							a1r.upload(System.nanoTime());
							a1r.draw(g2, (int) (getX() - 178 - getGame().getCamera().getX()),
									(int) (getY() - 87 - getGame().getCamera().getY()));
							if (a1r.getCurrentFrame() == 8) {
								getGame().getListEF().addEF(new CutEffort(getX() - 143, getY() - 62, getGame()));
								if (count == 0) {
									getGame().getListBullet().addObject(new Cut(getX() + 170, getY() + 45, getGame()));
									count++;
								}
							}

							if (a1r.isLastFrame()) {
								isShooting = false;
								if (currentAction == listAction.length - 1) {
									currentAction = 0;
								} else {
									currentAction++;
								}
								timeStartAction = System.currentTimeMillis();

								setX(xCu);
								setY(yCu);

								count = 0;
							}
						} else {
							a1l.upload(System.nanoTime());
							a1l.draw(g2, (int) (getX() - 456 - getGame().getCamera().getX()),
									(int) (getY() - 87 - getGame().getCamera().getY()));
							if (a1l.getCurrentFrame() == 8) {
								getGame().getListEF().addEF(new CutEffort(getX() - 421, getY() - 62, getGame()));
								if (count == 0) {
									getGame().getListBullet().addObject(new Cut(getX() - 113, getY() + 35, getGame()));
									count++;
								}
							}

							if (a1l.isLastFrame()) {
								isShooting = false;
								if (currentAction == listAction.length - 1) {
									currentAction = 0;
								} else {
									currentAction++;
								}
								timeStartAction = System.currentTimeMillis();

								setX(xCu);
								setY(yCu);

								count = 0;
							}
						}
					} else if (listAction[currentAction].equals("ATTACK2")) {

						a2.upload(System.nanoTime());
						a2.draw(g2, (int) (getX() - 496 - getGame().getCamera().getX()),
								(int) (getY() - 502 - getGame().getCamera().getY()));

						if (a2.getCurrentFrame() == 0) {
							getGame().getListEF()
									.addEF(new ScratchEffort(getX() + 15, getY() + getWidth() - 50, getGame()));

						}

						if (a2.getCurrentFrame() == 14) {
							if (count == 0) {
								getGame().getListBullet().addObject(new Scratch(getX() - 213, getY() - 78, getGame()));
								getGame().getListBullet().addObject(new Scratch(getX() - 9, getY() - 281, getGame()));
								getGame().getListBullet().addObject(new Scratch(getX() + 241, getY() + 104, getGame()));
								getGame().getListBullet().addObject(new Scratch(getX() + 310, getY() - 187, getGame()));
								getGame().getListBullet().addObject(new Scratch(getX() + 268, getY() - 340, getGame()));
								count += 4;
							}
						}

						if (a2.getCurrentFrame() == 15) {
							if (count == 4) {
								getGame().getListBullet().addObject(new Scratch(getX() - 222, getY() + 65, getGame()));
								getGame().getListBullet().addObject(new Scratch(getX() - 256, getY() - 347, getGame()));
								getGame().getListBullet().addObject(new Scratch(getX() + 474, getY() - 106, getGame()));
								count += 3;
							}
						}

						if (a2.getCurrentFrame() == 16) {
							if (count == 7) {
								getGame().getListBullet().addObject(new Scratch(getX() - 449, getY() + 3, getGame()));
								getGame().getListBullet().addObject(new Scratch(getX() + 417, getY() + 31, getGame()));
								count += 2;
							}
						}
						if (a2.isLastFrame()) {
							isShooting = false;
							if (currentAction == listAction.length - 1) {
								currentAction = 0;
							} else {
								currentAction++;
							}
							timeStartAction = System.currentTimeMillis();

							setX(xCu);
							setY(yCu);

							count = 0;
						}
					}
				}
			}
		}

		if (isDrawThanhHp)

		{
			thanhHpBoss.draw(g2);
		}

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();

		return new Rectangle(rect.x + 20, rect.y + 20, rect.width - 40, rect.height - 40);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();

		g2.setColor(Color.YELLOW);
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
		isDead = true;
		setTeam(LEGUE_TEAM);
		if (getDirector() == DIR_LEFT) {
			getGame().getListEF().addEF(new WhenInnerRageDeadLeft(getX(), getY(), getGame()));
		} else {
			getGame().getListEF().addEF(new WhenInnerRageDead(getX(), getY(), getGame()));
		}
	}

}
