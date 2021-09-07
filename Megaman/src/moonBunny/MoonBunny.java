package moonBunny;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Random;

import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;
import healthBarEnemyMapleStory.HealBarEnemies;
import item.LargeHealItemMapleStory;
import item.SmallHealItemMapleStory;
import particulerObject.HumanMapleStory;
import whenDeath.WhenDeadMotLan;

public class MoonBunny extends HumanMapleStory {

	public static final int NORMAL = 0;
	public static final int ANGRY = 1;

	private int STAGE;

	private HealBarEnemies hb;

	private HashMap<String, Integer> listTime;
	private String[] arrayAction;
	private int currentAction;
	private long timeStart;

	private long timeStartBehurt;
	private boolean isShooting;

	private Random rd;

	private long timeSwitchDir;
	private boolean isSwich;

	private Animation rnl, rnr, ral, rar;
	private Animation il, ir;
	private Animation deadL, deadR;
	private Animation al, ar;
	private Animation bl, br;

	private int count = 0;

	private long timeStartAngry;

	public MoonBunny(float x, float y, GameWorldMapleStory game, int dir) {
		super(x, y, game, 0.1f, 94, 150, 100, 5);

		hb = new HealBarEnemies("Start Pixie", 100, 100, getX() - 20 - getGame().getCamera().getX(),
				getY() - getGame().getCamera().getY(), 160, 30);

		STAGE = NORMAL;
		setTeam(ENEMI_TEAM);
		setStage(ALIVE);

		setStage(getStage());

		setxPrimaral(getX());
		setyPrimaral(getY());
		setDirectorPrimaral(getDirector());
		setBloodPrimaral(getBlood());

		rnl = new Animation(getGame().getInputData().getListAnimation().get("mbrn"));
		rnr = new Animation(getGame().getInputData().getListAnimation().get("mbrn"));
		rnr.daoNguoc();

		ral = new Animation(getGame().getInputData().getListAnimation().get("mbra"));
		rar = new Animation(getGame().getInputData().getListAnimation().get("mbra"));
		rar.daoNguoc();

		il = new Animation(getGame().getInputData().getListAnimation().get("mbi"));
		ir = new Animation(getGame().getInputData().getListAnimation().get("mbi"));
		ir.daoNguoc();

		deadL = new Animation(getGame().getInputData().getListAnimation().get("mbdead"));
		deadR = new Animation(getGame().getInputData().getListAnimation().get("mbdead"));
		deadR.daoNguoc();

		al = new Animation(getGame().getInputData().getListAnimation().get("mba"));
		ar = new Animation(getGame().getInputData().getListAnimation().get("mba"));
		ar.daoNguoc();

		bl = new Animation(getGame().getInputData().getListAnimation().get("mbbehurt"));
		br = new Animation(getGame().getInputData().getListAnimation().get("mbbehurt"));
		br.daoNguoc();

		listTime = new HashMap<String, Integer>();
		listTime.put("NONE", 1000);
		listTime.put("RUN", 4000);
		listTime.put("ATTACK", 20000);

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
			case NORMAL:

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

				hb.upload("Moon Bunny", 100, getBlood(), getX() - 20 - getGame().getCamera().getX(),
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
					currentAction = 0;
					timeStart = System.currentTimeMillis();
				}
				break;

			case ANGRY:

				if (System.currentTimeMillis() - timeStartAngry > 5000) {
					STAGE = NORMAL;
					hb.setW(160);
				}

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

				hb.upload("Moon Bunny Angry", 100, getBlood(), getX() - 40 - getGame().getCamera().getX(),
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
					currentAction++;
					timeStart = System.currentTimeMillis();
				} else if (currentAction == 1) {
					run();
				} else if (currentAction == 2) {
					setSpeedX(0);
					if (getX() > getGame().getMario().getX() && getX() - getGame().getMario().getX() < 70) {
						setDirector(DIR_LEFT);
						attack();

					} else if (getX() < getGame().getMario().getX() && getGame().getMario().getX() - getX() < 130) {
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

		if (STAGE == ANGRY) {

			if (getDirector() == DIR_LEFT) {
				setSpeedX(-2);
			} else {
				setSpeedX(2);
			}

			if (getX() > getGame().getMario().getX() && getX() - getGame().getMario().getX() < 70) {
				currentAction++;
				timeStart = System.currentTimeMillis();
				setDirector(DIR_LEFT);
				attack();

			} else if (getX() < getGame().getMario().getX() && getGame().getMario().getX() - getX() < 130) {
				currentAction++;
				timeStart = System.currentTimeMillis();
				setDirector(DIR_RIGHT);
				attack();
			}
		} else {
			if (getDirector() == DIR_LEFT) {
				setSpeedX(-1);
			} else {
				setSpeedX(1);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (STAGE == NORMAL && getStage() == BEHURT) {
			hb.setW(200);
			setSpeedX(0);
			STAGE = ANGRY;
			timeStartAngry = System.currentTimeMillis();
		} else {

			if (getStage() == BEHURT) {

				if (getDirector() == DIR_LEFT) {
					bl.upload(System.nanoTime());
					bl.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - 10 - getGame().getCamera().getY()));

					if (bl.isLastFrame()) {
						if (timeStartBehurt == 0) {
							timeStartBehurt = System.currentTimeMillis();
						}
					}

				} else {
					br.upload(System.nanoTime());
					br.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - 10 - getGame().getCamera().getY()));
					if (br.isLastFrame()) {
						if (timeStartBehurt == 0) {
							timeStartBehurt = System.currentTimeMillis();
						}
					}
				}

			} else {
				if (arrayAction[currentAction].equals("NONE")) {
					if (getDirector() == DIR_LEFT) {
						il.upload(System.nanoTime());
						il.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() + 10 - getGame().getCamera().getY()));
					} else {
						ir.upload(System.nanoTime());
						ir.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() + 10 - getGame().getCamera().getY()));
					}
				} else if (arrayAction[currentAction].equals("RUN")) {
					if (STAGE == NORMAL) {
						if (getDirector() == DIR_LEFT) {
							rnl.upload(System.nanoTime());
							rnl.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 10 - getGame().getCamera().getY()));

						} else {
							rnr.upload(System.nanoTime());
							rnr.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 10 - getGame().getCamera().getY()));
						}
					} else {
						if (getDirector() == DIR_LEFT) {
							ral.upload(System.nanoTime());
							if (ral.getCurrentFrame() == 1) {
								ral.draw(g2, (int) (getX() + 4 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (ral.getCurrentFrame() == 2) {
								ral.draw(g2, (int) (getX() + 6 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (ral.getCurrentFrame() == 3 || ral.getCurrentFrame() == 4) {
								ral.draw(g2, (int) (getX() - 22 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (ral.getCurrentFrame() == 5) {
								ral.draw(g2, (int) (getX() + 12 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else {
								ral.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							}

						} else {
							rar.upload(System.nanoTime());
							if (rar.getCurrentFrame() == 3 || rar.getCurrentFrame() == 4) {
								rar.draw(g2, (int) (getX() - 20 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else {
								rar.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							}
						}
					}
				} else if (arrayAction[currentAction].equals("ATTACK")) {
					if (isShooting) {
						if (getDirector() == DIR_LEFT) {
							al.upload(System.nanoTime());
							if (al.getCurrentFrame() == 0) {
								al.draw(g2, (int) (getX() - 40 - getGame().getCamera().getX()),
										(int) (getY() + 10 - getGame().getCamera().getY()));
							} else if (al.getCurrentFrame() == 1) {
								al.draw(g2, (int) (getX() - 26 - getGame().getCamera().getX()),
										(int) (getY() + 10 - getGame().getCamera().getY()));
							} else if (al.getCurrentFrame() == 3) {
								al.draw(g2, (int) (getX() - 48 - getGame().getCamera().getX()),
										(int) (getY() + 10 - getGame().getCamera().getY()));
							} else if (al.getCurrentFrame() == 4) {
								al.draw(g2, (int) (getX() - 22 - getGame().getCamera().getX()),
										(int) (getY() + 10 - getGame().getCamera().getY()));
							} else if (al.getCurrentFrame() == 5) {
								al.draw(g2, (int) (getX() - 12 - getGame().getCamera().getX()),
										(int) (getY() + 10 - getGame().getCamera().getY()));
							} else {
								al.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() + 10 - getGame().getCamera().getY()));
							}

							if (al.getCurrentFrame() == 3 && count == 0) {
								getGame().getListBullet()
										.addObject(new PuchMoonBunny(getX() - 50, getY() + 80, getGame()));
							}
							if (al.isLastFrame()) {
								count = 0;
								isShooting = false;

								if ((getX() > getGame().getMario().getX() && getX() - getGame().getMario().getX() < 70)
										|| (getX() < getGame().getMario().getX()
												&& getGame().getMario().getX() - getX() < 130)) {
									currentAction = 2;
								} else {
									currentAction = 0;
								}
								timeStart = System.currentTimeMillis();
							}
						} else {
							ar.upload(System.nanoTime());

							ar.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 10 - getGame().getCamera().getY()));
							if (ar.getCurrentFrame() == 3 && count == 0) {
								getGame().getListBullet()
										.addObject(new PuchMoonBunny(getX() + 100, getY() + 80, getGame()));
							}
							if (ar.isLastFrame()) {
								count = 0;
								isShooting = false;

								if ((getX() > getGame().getMario().getX() && getX() - getGame().getMario().getX() < 70)
										|| (getX() < getGame().getMario().getX()
												&& getGame().getMario().getX() - getX() < 130)) {
									currentAction = 2;
								} else {
									currentAction = 0;
								}
								timeStart = System.currentTimeMillis();
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
		return new Rectangle(rect.x + 10, rect.y + 40, rect.width - 20, rect.height - 40);
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

		STAGE = NORMAL;

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
