package mainPlayerMapleStory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import actionForHuman.AllAction;
import blackHole.BlackHoleMapleStory;
import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;
import particulerObject.HumanMapleStory;
import sound.ManagerSound;

public class Mario extends HumanMapleStory implements AllAction {

	private Random rd;

	private Animation idleLeft, idleRight;

	private List<Animation> run;
	private boolean isRun;
	private int currenRun;
	private Animation runALeft, runARight, runBLeft, runBRight, runCLeft, runCRight, runDLeft, runDRight, runELeft,
			runERight;
	private Animation roiLeft, roiRight;

	private List<Animation> jump;
	private int currenJump;
	private boolean jumpB;
	private Animation jumpALeft, jumpARight, jumpBLeft, jumpBRight, jumpCLeft, jumpCRight;

	private boolean isShooting;
	private int currentAttack;
	private long timeRelax;
	private List<Animation> attack;
	private Animation attack1Left, attack1Right, attack2Left, attack2Right, attack3Left, attack3Right;

	private Animation blackHoleHutL, blackHoleHutR;

	private List<Animation> behurt;
	private int currenBehur;
	private Animation behurtAL, behurtAR, behurtBL, behurtBR;

	private Animation deadL, deadR;

	private Animation lct;
	private Animation lcte;

	private boolean isXuatHien;
	private BlackHoleMapleStory bl;

	private boolean isEndCauThang;

	private boolean isRoiKhoiMatDat;

	public Mario(float x, float y, GameWorldMapleStory game) {
		super(x, y, game, 0.1f, 44, 68, 100, 0);

		rd = new Random();

		setDirector(DIR_RIGHT);
		setTeam(LEGUE_TEAM);

		bl = new BlackHoleMapleStory(getX() - 150, getY() - 150, getGame());
		getGame().getListObject().addObject(bl);

		run = new ArrayList<Animation>();
		jump = new ArrayList<Animation>();
		attack = new ArrayList<Animation>();
		behurt = new ArrayList<Animation>();

		runALeft = new Animation(getGame().getInputData().getListAnimation().get("runA"));
		runALeft.daoNguoc();
		run.add(runALeft);
		runBLeft = new Animation(getGame().getInputData().getListAnimation().get("runB"));
		runBLeft.daoNguoc();
		run.add(runBLeft);
		runBLeft.setHeSo(2.2f);
		runCLeft = new Animation(getGame().getInputData().getListAnimation().get("runC"));
		runCLeft.daoNguoc();
		run.add(runCLeft);
		runCLeft.setHeSo(2.1f);
		runDLeft = new Animation(getGame().getInputData().getListAnimation().get("runD"));
		runDLeft.daoNguoc();
		run.add(runDLeft);
		runELeft = new Animation(getGame().getInputData().getListAnimation().get("runE"));
		runELeft.daoNguoc();
		run.add(runELeft);
		runELeft.setHeSo(2.2f);

		runARight = new Animation(getGame().getInputData().getListAnimation().get("runA"));
		run.add(runARight);
		runBRight = new Animation(getGame().getInputData().getListAnimation().get("runB"));
		run.add(runBRight);
		runBRight.setHeSo(2.2f);
		runCRight = new Animation(getGame().getInputData().getListAnimation().get("runC"));
		run.add(runCRight);
		runCRight.setHeSo(2.1f);
		runDRight = new Animation(getGame().getInputData().getListAnimation().get("runD"));
		run.add(runDRight);
		runERight = new Animation(getGame().getInputData().getListAnimation().get("runE"));
		run.add(runERight);
		runERight.setHeSo(2.2f);

		idleLeft = new Animation(getGame().getInputData().getListAnimation().get("idle"));
		idleLeft.daoNguoc();
		idleRight = new Animation(getGame().getInputData().getListAnimation().get("idle"));

		roiLeft = new Animation(getGame().getInputData().getListAnimation().get("roi"));
		roiLeft.daoNguoc();
		roiRight = new Animation(getGame().getInputData().getListAnimation().get("roi"));

		jumpALeft = new Animation(getGame().getInputData().getListAnimation().get("jumpA"));
		jumpALeft.daoNguoc();
		jump.add(jumpALeft);
		jumpALeft.setIsRepeat(false);
		jumpBLeft = new Animation(getGame().getInputData().getListAnimation().get("jumpB"));
		jumpBLeft.daoNguoc();
		jump.add(jumpBLeft);
		jumpBLeft.setIsRepeat(false);
		jumpCLeft = new Animation(getGame().getInputData().getListAnimation().get("jumpC"));
		jumpCLeft.daoNguoc();
		jump.add(jumpCLeft);
		jumpCLeft.setIsRepeat(false);

		jumpARight = new Animation(getGame().getInputData().getListAnimation().get("jumpA"));
		jump.add(jumpARight);
		jumpARight.setIsRepeat(false);
		jumpBRight = new Animation(getGame().getInputData().getListAnimation().get("jumpB"));
		jump.add(jumpBRight);
		jumpBRight.setIsRepeat(false);
		jumpCRight = new Animation(getGame().getInputData().getListAnimation().get("jumpC"));
		jump.add(jumpCRight);
		jumpCRight.setIsRepeat(false);

		attack1Left = new Animation(getGame().getInputData().getListAnimation().get("attack1"));
		attack.add(attack1Left);
		attack1Left.setIsRepeat(false);
		attack1Left.daoNguoc();
		attack2Left = new Animation(getGame().getInputData().getListAnimation().get("attack2"));
		attack.add(attack2Left);
		attack2Left.daoNguoc();
		attack2Left.setIsRepeat(false);
		attack3Left = new Animation(getGame().getInputData().getListAnimation().get("attack3"));
		attack.add(attack3Left);
		attack3Left.setIsRepeat(false);
		attack3Left.daoNguoc();

		attack1Right = new Animation(getGame().getInputData().getListAnimation().get("attack1"));
		attack.add(attack1Right);
		attack1Right.setIsRepeat(false);
		attack2Right = new Animation(getGame().getInputData().getListAnimation().get("attack2"));
		attack.add(attack2Right);
		attack2Right.setIsRepeat(false);
		attack3Right = new Animation(getGame().getInputData().getListAnimation().get("attack3"));
		attack.add(attack3Right);
		attack3Right.setIsRepeat(false);

		behurtAL = new Animation(getGame().getInputData().getListAnimation().get("behurtA"));
		behurtAL.daoNguoc();
		behurt.add(behurtAL);
		behurtBL = new Animation(getGame().getInputData().getListAnimation().get("behurtB"));
		behurtBL.daoNguoc();
		behurt.add(behurtBL);

		behurtAR = new Animation(getGame().getInputData().getListAnimation().get("behurtA"));
		behurt.add(behurtAR);
		behurtBR = new Animation(getGame().getInputData().getListAnimation().get("behurtB"));
		behurt.add(behurtBR);

		deadL = new Animation(getGame().getInputData().getListAnimation().get("dead"));
		deadL.daoNguoc();
		deadL.setIsRepeat(false);
		deadR = new Animation(getGame().getInputData().getListAnimation().get("dead"));
		deadR.setIsRepeat(false);

		lct = new Animation(getGame().getInputData().getListAnimation().get("leoCauThang"));
		lcte = new Animation(getGame().getInputData().getListAnimation().get("leoCauThangEnd"));

		blackHoleHutL = new Animation(getGame().getInputData().getListAnimation().get("blackHoleHut"));
		blackHoleHutR = new Animation(getGame().getInputData().getListAnimation().get("blackHoleHut"));
		blackHoleHutR.daoNguoc();
	}

	@Override
	public void upload() {
		if (getStage() != SWITCHMEGAMAN) {
			super.upload();

			if (getSpeedY() == 0) {
				if (!isXuatHien) {
					if (getY() > 1000) {
						getGame().getListObject().removeObject(bl);
						try {
							ManagerSound.getInstance().stopHieuUng("blackHole");
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							e.printStackTrace();
						}
						isXuatHien = true;
						getGame().setiStartGame(true);
					}
				}
				tiepDat();
			} else {
				isRoiKhoiMatDat = true;
			}

			if (getSpeedX() == 0 && getSpeedY() == 0) {
				isRun = false;
			}

			if (getStage() == ALIVE || getStage() == FEY) {
				for (int i = 0; i < getGame().getListObject().getListObject().size(); i++) {
					if (!getGame().getListObject().getListObject().get(i).isOutCamera() && getGame().getListObject()
							.getListObject().get(i).getRectangleXuLiVaCham().intersects(getRectangleXuLiVaCham())) {
						if (getGame().getListObject().getListObject().get(i).getTeam() != getTeam()) {
							setBlood(getBlood() - getGame().getListObject().getListObject().get(i).getDame());
							if (getBlood() > 0) {
								setStage(BEHURT);
								setTimBeginBehurt(System.nanoTime());
								currenBehur = rd.nextInt(behurt.size() / 2);
								ManagerSound.getInstance().getListSound().get("rmbehurt").start();
								ManagerSound.getInstance().getListSound().get("rmbehurt").setFramePosition(0);
								ManagerSound.getInstance().getListSound().get("punchTrung").start();
								try {
									ManagerSound.getInstance().khoiTaoHieuUng("punchTrung");
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		} else {
			if (getY() >= 1464) {
				setX(getX() + getSpeedX());
				setY(getY() + getSpeedY());
				if (getY() == 1464) {
					getGame().getDrawRectangeEnd().setColor(Color.BLACK);
					getGame().getDrawRectangeEnd().setNow(0);
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (getStage() != SWITCHMEGAMAN) {

			if (isLeoCauThang()) {
				if (getStage() == BEHURT) {
					setStage(NOBEHURT);
				}

				if (!isEndCauThang) {
					if (getStage() == NOBEHURT && System.nanoTime() % 3 == 2) {
					} else {
						lct.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					}
				} else {
					if (getStage() == NOBEHURT && System.nanoTime() % 3 == 2) {
					} else {
						lcte.upload(System.nanoTime());
						lcte.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
						if (lcte.isLastFrame()) {
							isEndCauThang = false;
							setLeoCauThang(false);
							setY(getY() - 20);
						}
					}
				}

			} else {
				if (getStage() == DEATH) {
					deadL.upload(System.nanoTime());
					if (getDirector() == DIR_LEFT) {
						deadL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - 5 - getGame().getCamera().getY()));
					} else {
						deadR.setCurrentFrame(deadL.getCurrentFrame());
						deadR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - 5 - getGame().getCamera().getY()));
					}
				} else {
					if (getStage() == NOBEHURT && System.nanoTime() % 3 == 2) {
					} else {
						if (getStage() == BEHURT) {
							if (getBlood() <= 0) {
								getToDeath();
								setStage(DEATH);
							} else {
								behurt.get(currenBehur).upload(System.nanoTime());
								getBehurtA().setCurrentFrame(behurt.get(currenBehur).getCurrentFrame());
								if (currenBehur == 1) {
									if (getBehurtA().getCurrentFrame() < 6) {
										getBehurtA().draw(g2, (int) (getX() - getGame().getCamera().getX()),
												(int) (getY() + 10 - getGame().getCamera().getY()));
									} else {
										getBehurtA().draw(g2, (int) (getX() - getGame().getCamera().getX()),
												(int) (getY() - 10 - getGame().getCamera().getY()));
									}
								} else {
									getBehurtA().draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - 10 - getGame().getCamera().getY()));
								}
								if (getBehurtA().isLastFrame())
									setStage(NOBEHURT);
							}
						} else if (isShooting) {
							attack.get(currentAttack).upload(System.nanoTime());
							getAttack().setCurrentFrame(attack.get(currentAttack).getCurrentFrame());
							if (currentAttack == 2) {
								if (getDirector() == DIR_LEFT) {
									if (getAttack().getCurrentFrame() == 1 || getAttack().getCurrentFrame() == 5
											|| getAttack().getCurrentFrame() == 9) {
										getAttack().draw(g2, (int) (getX() - 82 - getGame().getCamera().getX()),
												(int) (getY() - 10 - getGame().getCamera().getY()));
									} else if (getAttack().getCurrentFrame() == 2 || getAttack().getCurrentFrame() == 6
											|| getAttack().getCurrentFrame() == 10) {
										getAttack().draw(g2, (int) (getX() - 86 - getGame().getCamera().getX()),
												(int) (getY() - 10 - getGame().getCamera().getY()));
									} else if (getAttack().getCurrentFrame() == 3 || getAttack().getCurrentFrame() == 7
											|| getAttack().getCurrentFrame() == 11) {
										getAttack().draw(g2, (int) (getX() - 88 - getGame().getCamera().getX()),
												(int) (getY() - 9 - getGame().getCamera().getY()));
									} else {
										getAttack().draw(g2, (int) (getX() - 50 - getGame().getCamera().getX()),
												(int) (getY() - 10 - getGame().getCamera().getY()));
									}
								} else {
									if (getAttack().getCurrentFrame() == 1 || getAttack().getCurrentFrame() == 5
											|| getAttack().getCurrentFrame() == 9 || getAttack().getCurrentFrame() == 2
											|| getAttack().getCurrentFrame() == 6
											|| getAttack().getCurrentFrame() == 10) {
										getAttack().draw(g2, (int) (getX() - 38 - getGame().getCamera().getX()),
												(int) (getY() - 10 - getGame().getCamera().getY()));
									} else if (getAttack().getCurrentFrame() == 3 || getAttack().getCurrentFrame() == 7
											|| getAttack().getCurrentFrame() == 11) {
										getAttack().draw(g2, (int) (getX() - 42 - getGame().getCamera().getX()),
												(int) (getY() - 9 - getGame().getCamera().getY()));
									} else {
										getAttack().draw(g2, (int) (getX() - 24 - getGame().getCamera().getX()),
												(int) (getY() - 10 - getGame().getCamera().getY()));
									}
								}
							} else if (currentAttack == 0) {
								if (getDirector() == DIR_LEFT) {
									getAttack().draw(g2, (int) (getX() - 20 - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								} else {
									getAttack().draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								}
							} else {
								if (getDirector() == DIR_LEFT) {
									getAttack().draw(g2, (int) (getX() - 15 - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								} else {
									getAttack().draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								}
							}
							if (getAttack().isLastFrame()) {
								isShooting = false;
								if (currentAttack != 2) {
									currentAttack++;
								} else {
									currentAttack = 0;
									for (int i = 0; i < attack.size(); i++) {
										attack.get(i).reset();
									}
								}
							}
						} else if (getSpeedY() == 0 && getSpeedX() != 0) {
							getRun().upload(System.nanoTime());
							getRun().draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (getSpeedY() == 0 && getSpeedX() == 0) {
							if (getDirector() == DIR_LEFT) {
								idleLeft.upload(System.nanoTime());
								idleLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (getDirector() == DIR_RIGHT) {
								idleRight.upload(System.nanoTime());
								idleRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							}
						} else if (getSpeedY() != 0 && !getIsJumping()) {
							if (getDirector() == DIR_LEFT) {
								roiLeft.upload(System.nanoTime());
								roiLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (getDirector() == DIR_RIGHT) {
								roiRight.upload(System.nanoTime());
								roiRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							}
						} else if (getSpeedY() != 0 && getIsJumping()) {
							if (getJump().isLastFrame() && getSpeedY() >= 0) {
								if (getDirector() == DIR_LEFT) {
									roiLeft.upload(System.nanoTime());
									roiLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								} else if (getDirector() == DIR_RIGHT) {
									roiRight.upload(System.nanoTime());
									roiRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								}
							} else {
								if (currenJump == 2) {
									jump.get(currenJump).upload(System.nanoTime());
									getJump().setCurrentFrame(jump.get(currenJump).getCurrentFrame());
									getJump().draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								} else {
									jump.get(currenJump).upload(System.nanoTime());
									getJump().setCurrentFrame(jump.get(currenJump).getCurrentFrame());
									getJump().draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								}
							}
						}
					}
				}
			}
		} else {
			if (getDirector() == DIR_LEFT) {
				blackHoleHutL.upload(System.nanoTime());
				blackHoleHutL.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else {
				blackHoleHutR.upload(System.nanoTime());
				blackHoleHutR.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		}
	}

	public Animation getRun() {
		if (!isRun) {
			isRun = true;
			currenRun = rd.nextInt(run.size() / 2);
		}

		if (getDirector() == DIR_LEFT) {
			return run.get(currenRun);
		} else {
			return run.get(currenRun + run.size() / 2);
		}
	}

	public Animation getBehurtA() {
		if (getDirector() == DIR_LEFT) {
			return behurt.get(currenBehur);
		} else {
			return behurt.get(currenBehur + behurt.size() / 2);
		}
	}

	public Animation getAttack() {
		if (getDirector() == DIR_LEFT) {
			return attack.get(currentAttack);
		} else {
			return attack.get(currentAttack + attack.size() / 2);
		}
	}

	public Animation getJump() {
		if (!jumpB) {
			currenJump = rd.nextInt(jump.size() / 2);
			jumpB = true;
		}

		if (getDirector() == DIR_LEFT) {
			return jump.get(currenJump);
		} else {
			return jump.get(currenJump + jump.size() / 2);
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 5, rect.y + 2, rect.width - 10, rect.height - 4);
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
		ManagerSound.getInstance().getListSound().get("rmdead").start();
		try {
			ManagerSound.getInstance().khoiTaoHieuUng("rmdead");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		getGame().getController().getModel().setTimeStart(System.currentTimeMillis());
		getGame().setSTAGE(GameWorldMapleStory.ENDGAME);
	}

	@Override
	public void attack() {

		if (!isShooting && getSpeedY() == 0) {
			setSpeedX(0);
			isShooting = true;

			float x = getX();
			float y = getY();

			if (getDirector() == DIR_LEFT) {
				x = x - 80;
			} else {
				x = x + getWidth() + 20;
			}

			if (currentAttack != 2) {
				BulletDamLv1 bl1 = new BulletDamLv1(x, y, getGame(), getDirector());
				getGame().getListBullet().addObject(bl1);
				ManagerSound.getInstance().getListSound().get("punch").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("punch");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			} else {
				BulletDamLv3 bl3 = new BulletDamLv3(x, y, getGame(), getDirector());
				getGame().getListBullet().addObject(bl3);
				ManagerSound.getInstance().getListSound().get("punch").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("punch");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}

			if (System.nanoTime() - timeRelax > 1000000000) {
				currentAttack = 0;
				for (int i = 0; i < attack.size(); i++) {
					attack.get(i).reset();
				}
			}
		}

		timeRelax = System.nanoTime();

	}

	@Override
	public void dick() {
		boolean a = getGame().getCauThang().getRectangleXuLiVaCham().intersects(getRectangleXuLiVaCham());
		if (!a) {
			setLeoCauThang(false);
		} else if (isLeoCauThang()) {
			lct.upload(System.nanoTime());
			setY(getY() + 5);
			if (a) {
				if ((getY() + getHeight())
						- (getGame().getCauThang().getY() + getGame().getCauThang().getHeight()) > 50) {
					setLeoCauThang(false);
				}
			}
		} else if (a && !isLeoCauThang()) {
			setLeoCauThang(true);
			setSpeedX(0);
			setSpeedY(0);
			setX(getGame().getCauThang().getX() + 30);
			setY(getY() + 60);
		} else {
			setSpeedX(0);
		}
	}

	public void up() {
		boolean a = getGame().getCauThang().getRectangleXuLiVaCham().intersects(getRectangleXuLiVaCham());
		if (isLeoCauThang() && !isEndCauThang) {
			setY(getY() - 5);
			lct.upload(System.nanoTime());
			if (getGame().getCauThang().getY() - getY() > 30) {
				isEndCauThang = true;
			}
		} else if (!isLeoCauThang() && a) {
			if ((getY() + getWidth()) - getGame().getCauThang().getY() > 30) {
				setLeoCauThang(true);
				setSpeedX(0);
				setSpeedY(0);
				setX(getGame().getCauThang().getX() + 30);
				setY(getY() - 30);
			}
		}
	}

	@Override
	public void tiepDat() {

		if (isRoiKhoiMatDat) {
			isRoiKhoiMatDat = false;
			ManagerSound.getInstance().getListSound().get("tiepDat").start();
			ManagerSound.getInstance().getListSound().get("tiepDat").setFramePosition(0);
		}

		setIsJumping(false);
		jumpB = false;
		jumpALeft.reset();
		jumpARight.reset();
		jumpBLeft.reset();
		jumpBRight.reset();
		jumpCLeft.reset();
		jumpCRight.reset();
	}

	@Override
	public void jump() {
		if (getStage() != BEHURT) {
			if (isLeoCauThang()) {
				setLeoCauThang(false);
			}

			if (isXuatHien && getGame().isiStartGame() && getStage() != DEATH) {
				if (!getIsJumping() && !isShooting) {
					int futureY = checkChamTranNha(5, getRectangleXuLiVaCham());
					setY(futureY);
					setSpeedY(-6);
					setIsJumping(true);
				}
			}

			ManagerSound.getInstance().getListSound().get("rmjump").start();
			ManagerSound.getInstance().getListSound().get("rmjump").setFramePosition(0);
		}
	}

	public int checkChamTranNha(int y, Rectangle rect) {
		for (int i = rect.y - 1; i >= rect.y - y; i--) {
			Rectangle r = new Rectangle(rect.x, i, rect.width, rect.height);
			if (getGame().getPhysicalMap().vaChamTrenDau(r) != null)
				return r.y + 1;
		}
		return rect.y - y + 1;
	}

	@Override
	public void run() {
		if (getStage() != BEHURT) {
			if (isLeoCauThang()) {
				if (getGame().getAction().getActionKeyForMapbleStory().isPressLeft()) {
					setDirector(DIR_LEFT);
				} else if (getGame().getAction().getActionKeyForMapbleStory().isPressRight()) {
					setDirector(DIR_RIGHT);
				}
			}

			if (isXuatHien && getGame().isiStartGame() && getStage() != DEATH) {
				if (!isShooting) {
					if (getDirector() == DIR_LEFT) {
						setSpeedX(-3);
					} else if (getDirector() == DIR_RIGHT) {
						setSpeedX(+3);
					}
				}
			}
		}
	}

	@Override
	public void stanUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopAttack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopRun() {
		setSpeedX(0);
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
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

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public int getCurrenRun() {
		return currenRun;
	}

	public void setCurrenRun(int currenRun) {
		this.currenRun = currenRun;
	}

	public Animation getRunALeft() {
		return runALeft;
	}

	public void setRunALeft(Animation runALeft) {
		this.runALeft = runALeft;
	}

	public Animation getRunARight() {
		return runARight;
	}

	public void setRunARight(Animation runARight) {
		this.runARight = runARight;
	}

	public Animation getRunBLeft() {
		return runBLeft;
	}

	public void setRunBLeft(Animation runBLeft) {
		this.runBLeft = runBLeft;
	}

	public Animation getRunBRight() {
		return runBRight;
	}

	public void setRunBRight(Animation runBRight) {
		this.runBRight = runBRight;
	}

	public Animation getRunCLeft() {
		return runCLeft;
	}

	public void setRunCLeft(Animation runCLeft) {
		this.runCLeft = runCLeft;
	}

	public Animation getRunCRight() {
		return runCRight;
	}

	public void setRunCRight(Animation runCRight) {
		this.runCRight = runCRight;
	}

	public Animation getRunDLeft() {
		return runDLeft;
	}

	public void setRunDLeft(Animation runDLeft) {
		this.runDLeft = runDLeft;
	}

	public Animation getRunDRight() {
		return runDRight;
	}

	public void setRunDRight(Animation runDRight) {
		this.runDRight = runDRight;
	}

	public Animation getRunELeft() {
		return runELeft;
	}

	public void setRunELeft(Animation runELeft) {
		this.runELeft = runELeft;
	}

	public Animation getRunERight() {
		return runERight;
	}

	public void setRunERight(Animation runERight) {
		this.runERight = runERight;
	}

	public Animation getRoiLeft() {
		return roiLeft;
	}

	public void setRoiLeft(Animation roiLeft) {
		this.roiLeft = roiLeft;
	}

	public Animation getRoiRight() {
		return roiRight;
	}

	public void setRoiRight(Animation roiRight) {
		this.roiRight = roiRight;
	}

	public int getCurrenJump() {
		return currenJump;
	}

	public void setCurrenJump(int currenJump) {
		this.currenJump = currenJump;
	}

	public boolean isJumpB() {
		return jumpB;
	}

	public void setJumpB(boolean jumpB) {
		this.jumpB = jumpB;
	}

	public Animation getJumpALeft() {
		return jumpALeft;
	}

	public void setJumpALeft(Animation jumpALeft) {
		this.jumpALeft = jumpALeft;
	}

	public Animation getJumpARight() {
		return jumpARight;
	}

	public void setJumpARight(Animation jumpARight) {
		this.jumpARight = jumpARight;
	}

	public Animation getJumpBLeft() {
		return jumpBLeft;
	}

	public void setJumpBLeft(Animation jumpBLeft) {
		this.jumpBLeft = jumpBLeft;
	}

	public Animation getJumpBRight() {
		return jumpBRight;
	}

	public void setJumpBRight(Animation jumpBRight) {
		this.jumpBRight = jumpBRight;
	}

	public Animation getJumpCLeft() {
		return jumpCLeft;
	}

	public void setJumpCLeft(Animation jumpCLeft) {
		this.jumpCLeft = jumpCLeft;
	}

	public Animation getJumpCRight() {
		return jumpCRight;
	}

	public void setJumpCRight(Animation jumpCRight) {
		this.jumpCRight = jumpCRight;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public int getCurrentAttack() {
		return currentAttack;
	}

	public void setCurrentAttack(int currentAttack) {
		this.currentAttack = currentAttack;
	}

	public long getTimeRelax() {
		return timeRelax;
	}

	public void setTimeRelax(long timeRelax) {
		this.timeRelax = timeRelax;
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

	public Animation getAttack3Left() {
		return attack3Left;
	}

	public void setAttack3Left(Animation attack3Left) {
		this.attack3Left = attack3Left;
	}

	public Animation getAttack3Right() {
		return attack3Right;
	}

	public void setAttack3Right(Animation attack3Right) {
		this.attack3Right = attack3Right;
	}

	public void setBehurt(List<Animation> behurt) {
		this.behurt = behurt;
	}

	public int getCurrenBehur() {
		return currenBehur;
	}

	public void setCurrenBehur(int currenBehur) {
		this.currenBehur = currenBehur;
	}

	public Animation getBehurtAL() {
		return behurtAL;
	}

	public void setBehurtAL(Animation behurtAL) {
		this.behurtAL = behurtAL;
	}

	public Animation getBehurtAR() {
		return behurtAR;
	}

	public void setBehurtAR(Animation behurtAR) {
		this.behurtAR = behurtAR;
	}

	public Animation getBehurtBL() {
		return behurtBL;
	}

	public void setBehurtBL(Animation behurtBL) {
		this.behurtBL = behurtBL;
	}

	public Animation getBehurtBR() {
		return behurtBR;
	}

	public void setBehurtBR(Animation behurtBR) {
		this.behurtBR = behurtBR;
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

	public boolean isXuatHien() {
		return isXuatHien;
	}

	public void setXuatHien(boolean isXuatHien) {
		this.isXuatHien = isXuatHien;
	}

	public BlackHoleMapleStory getBl() {
		return bl;
	}

	public void setBl(BlackHoleMapleStory bl) {
		this.bl = bl;
	}

	public void setRun(List<Animation> run) {
		this.run = run;
	}

	public void setJump(List<Animation> jump) {
		this.jump = jump;
	}

	public void setAttack(List<Animation> attack) {
		this.attack = attack;
	}

	public List<Animation> getBeHurt() {
		return behurt;
	}

	public Animation getBlackHoleHutL() {
		return blackHoleHutL;
	}

	public void setBlackHoleHutL(Animation blackHoleHutL) {
		this.blackHoleHutL = blackHoleHutL;
	}

	public Animation getBlackHoleHutR() {
		return blackHoleHutR;
	}

	public void setBlackHoleHutR(Animation blackHoleHutR) {
		this.blackHoleHutR = blackHoleHutR;
	}

	public Animation getLct() {
		return lct;
	}

	public void setLct(Animation lct) {
		this.lct = lct;
	}

	public Animation getLcte() {
		return lcte;
	}

	public void setLcte(Animation lcte) {
		this.lcte = lcte;
	}

	public boolean isEndCauThang() {
		return isEndCauThang;
	}

	public void setEndCauThang(boolean isEndCauThang) {
		this.isEndCauThang = isEndCauThang;
	}

}
