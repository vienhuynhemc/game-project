package bossIntroStage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;

import actionForHuman.Grounding;
import actionForHuman.Jump;
import actionForHuman.Run;
import bee.Bee;
import blackHole.BlackHole;
import efforOpacity.DrawRectangleOpacity;
import gameEffect.Animation;
import gameWorld.GameWorld;
import gameWorld.GameWorldMegamanX;
import metalAnchors.MetalAnchors;
import onScreen.BossHealthBar;
import particulerObject.HunmanMegamanX;
import scorpion.Scorpions;
import sound.ManagerSound;
import view.GameFrame;

public class BossIntroStage extends HunmanMegamanX implements Run, Jump, Grounding {

	private Animation xuatHienUp, xuatHien;
	private Animation idleLeft, idleRight;
	private Animation jumpLeft, jumpRight;
	private Animation truotLeft, truotRight;
	private Animation nemLeft, nemRight;
	private Animation dam;
	private Animation langXuongDat;
	private Animation viCaLeft, viCaRight;
	private Animation cauNguyenLeft, cauNguyenRight;
	private Animation deadLeft, deadRight;

	private boolean isPlayTutorial;

	private boolean isCreateSanDau;
	private boolean isChanDong;
	private boolean isXuatHien;
	private long timeForXuatHien;
	private long timeStartXuatHien;
	private AnimationItemBossIntroStage animationXuatHien;

	private HashMap<String, Integer> listTimeForAction;
	private String[] listAction;
	private int currentAction;
	private long timeStartAction;

	private boolean isShooting;
	private long timeStartShooting;

	private boolean isTruot;

	private boolean isNem;

	private boolean isDam;

	private boolean isRoiKhoiMatDat;

	private boolean isLanXuongDat;
	private boolean isViCa;
	private boolean isSpace;
	private long timeSpace;

	private boolean isTroiLen;

	private boolean isCauNguyen;
	private boolean isTaoBoCap;
	private Scorpions boCap;

	private boolean isTaoTacKe;
	private SkillSummonGecko skillGecKo;

	private boolean isTaoOng;
	private Bee bee;

	private BossHealthBar thanhHpBoss;
	private boolean isDrawThanhHp;

	private boolean isReady;

	private WhenBossDeath whenDeath;
	private DrawRectangleOpacity drawRect;
	private boolean isDraw;
	private boolean isDeath;

	private boolean isCreateBlackHole;

	public BossIntroStage(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f, 110, 186, 400, 10);

		idleLeft = new Animation(getGame().getInputData().getListAnimation().get("bossIdle"));
		idleRight = new Animation(getGame().getInputData().getListAnimation().get("bossIdle"));
		idleRight.daoNguoc();
		xuatHienUp = new Animation(getGame().getInputData().getListAnimation().get("bossXuatHienUp"));
		xuatHien = new Animation(getGame().getInputData().getListAnimation().get("bossXuatHien"));
		xuatHien.setIsRepeat(false);
		jumpLeft = new Animation(getGame().getInputData().getListAnimation().get("bossJump"));
		jumpRight = new Animation(getGame().getInputData().getListAnimation().get("bossJump"));
		jumpRight.daoNguoc();
		truotLeft = new Animation(getGame().getInputData().getListAnimation().get("bossTruot"));
		truotRight = new Animation(getGame().getInputData().getListAnimation().get("bossTruot"));
		truotRight.daoNguoc();
		nemLeft = new Animation(getGame().getInputData().getListAnimation().get("bossNem"));
		nemRight = new Animation(getGame().getInputData().getListAnimation().get("bossNem"));
		nemRight.daoNguoc();
		dam = new Animation(getGame().getInputData().getListAnimation().get("bossDam"));
		langXuongDat = new Animation(getGame().getInputData().getListAnimation().get("lanXuongDat"));
		langXuongDat.setIsRepeat(false);
		viCaLeft = new Animation(getGame().getInputData().getListAnimation().get("viCa"));
		viCaRight = new Animation(getGame().getInputData().getListAnimation().get("viCa"));
		viCaRight.daoNguoc();
		cauNguyenLeft = new Animation(getGame().getInputData().getListAnimation().get("cauNguyen"));
		cauNguyenRight = new Animation(getGame().getInputData().getListAnimation().get("cauNguyen"));
		cauNguyenRight.daoNguoc();
		deadLeft = new Animation(getGame().getInputData().getListAnimation().get("bossDeath"));
		deadRight = new Animation(getGame().getInputData().getListAnimation().get("bossDeath"));
		deadRight.daoNguoc();

		setDirector(DIR_LEFT);
		setTeam(ENEMI_TEAM);

		isPlayTutorial = true;
		isXuatHien = false;

		timeForXuatHien = 3000;
		animationXuatHien = new AnimationItemBossIntroStage(getX() + 50, getY() - 60, getGame());

		thanhHpBoss = new BossHealthBar(940, 130, getGame());

		whenDeath = new WhenBossDeath(getX(), getY(), getGame());
		drawRect = new DrawRectangleOpacity(Color.WHITE);

		listAction = new String[65];
		listAction[0] = "NEMATTACK";
		listAction[1] = "NONE";
		listAction[2] = "TRUOTATTACK";
		listAction[3] = "NONE";
		listAction[4] = "NEMATTACK";
		listAction[5] = "NONE";
		listAction[6] = "TRIEUHOITACKE";
		listAction[7] = "NONE";
		listAction[8] = "DAMATTACK";
		listAction[9] = "NONE";
		listAction[10] = "NEMATTACK";
		listAction[11] = "NONE";
		listAction[12] = "LANGXUONGDAT";
		listAction[13] = "TROILEN";
		listAction[14] = "NONE";
		listAction[15] = "TRIEUHOIBOCAP";
		listAction[16] = "NONE";
		listAction[17] = "NEMATTACK";
		listAction[18] = "NONE";
		listAction[19] = "NEMATTACK";
		listAction[20] = "NONE";
		listAction[21] = "TRUOTATTACK";
		listAction[22] = "NONE";
		listAction[23] = "DAMATTACK";
		listAction[24] = "NONE";
		listAction[25] = "TRUOTATTACK";
		listAction[26] = "NONE";
		listAction[27] = "NEMATTACK";
		listAction[28] = "NONE";
		listAction[29] = "LANGXUONGDAT";
		listAction[30] = "TROILEN";
		listAction[31] = "NONE";
		listAction[32] = "TRIEUHOIONG";
		listAction[33] = "NONE";
		listAction[34] = "DAMATTACK";
		listAction[35] = "NONE";
		listAction[36] = "TRUOTATTACK";
		listAction[37] = "NONE";
		listAction[38] = "NEMATTACK";
		listAction[39] = "NONE";
		listAction[40] = "DAMATTACK";
		listAction[41] = "NONE";
		listAction[42] = "LANGXUONGDAT";
		listAction[43] = "TROILEN";
		listAction[44] = "NONE";
		listAction[45] = "TRIEUHOIBOCAP";
		listAction[46] = "NONE";
		listAction[47] = "DAMATTACK";
		listAction[48] = "NONE";
		listAction[49] = "TRUOTATTACK";
		listAction[50] = "NONE";
		listAction[51] = "NEMATTACK";
		listAction[52] = "NONE";
		listAction[53] = "LANGXUONGDAT";
		listAction[54] = "TROILEN";
		listAction[55] = "NONE";
		listAction[56] = "TRIEUHOIONG";
		listAction[57] = "NONE";
		listAction[58] = "NEMATTACK";
		listAction[59] = "NONE";
		listAction[60] = "TRUOTATTACK";
		listAction[61] = "NONE";
		listAction[62] = "NEMATTACK";
		listAction[63] = "NONE";
		listAction[64] = "TRIEUHOITACKE";

		listTimeForAction = new HashMap<String, Integer>();
		listTimeForAction.put("TRUOTATTACK", 3000);
		listTimeForAction.put("NEMATTACK", 3000);
		listTimeForAction.put("DAMATTACK", 3000);
		listTimeForAction.put("LANGXUONGDAT", 6000);
		listTimeForAction.put("TROILEN", 3000);
		listTimeForAction.put("TRIEUHOIBOCAP", 20000);
		listTimeForAction.put("TRIEUHOITACKE", 20000);
		listTimeForAction.put("TRIEUHOIONG", 20000);
		listTimeForAction.put("NONE", 1200);
		currentAction = 0;
	}

	public void xuatHien() {
		if (timeStartXuatHien == 0) {
			timeStartXuatHien = System.currentTimeMillis();
		}
		if (System.currentTimeMillis() - timeStartXuatHien < timeForXuatHien) {
			animationXuatHien.upload();
		} else if (!isPlayTutorial && getGame().getSTAGE() == GameWorldMegamanX.ATTACKBOSS && !isReady) {

			if (!isChanDong) {
				getGame().getCamera().chanDong();
				isChanDong = true;
			} else {

				if (getY() > 920) {
					setY(getY() - 2);
				}
			}
		}

		if (animationXuatHien.getListRectangle().size() > 0) {
			for (int i = 0; i < animationXuatHien.getListRectangle().size(); i++) {
				animationXuatHien.getListRectangle().get(i).upload();
			}
		}
	}

	@Override
	public void upload() {

		if (!isDeath) {
			if (isCreateSanDau) {
				xuatHien();
			} else if (getGame().getSTAGE() == GameWorldMegamanX.ATTACKBOSS && !isPlayTutorial) {
				getGame().getBackGroundMap().getSanDauFinalBoss().setStart(true);
				getGame().getCamera().chanDong();

				if (getGame().getBackGroundMap().getSanDauFinalBoss().isComplete()) {
					isCreateSanDau = true;
				}
			}

			if (!isOutCamera() && isPlayTutorial && getGame().getSTAGE() == GameWorldMegamanX.ATTACKBOSS) {
				getGame().setSTAGE(GameWorldMegamanX.PLAYTUTORIAL);
				isPlayTutorial = false;
			}

			if (!thanhHpBoss.isReady()) {
				getGame().getAction().getActionKeyForMegamanX().lock();
				timeStartAction = System.currentTimeMillis();
			}

			if (isXuatHien) {
				isDrawThanhHp = true;
				isReady = true;
			}

			if (isDrawThanhHp) {
				thanhHpBoss.upload();
			}

			if (!isTruot) {
				if (getX() > getGame().getRockMan().getX()) {
					setDirector(DIR_LEFT);
				} else {
					setDirector(DIR_RIGHT);
				}
			}

			if (isReady) {

//				if (!isDam && !isLanXuongDat) {
//					if (getSpeedY() > 2) {
//						setSpeedY(2);
//					}
//				} else {
//					if (getSpeedY() > 3) {
//						setSpeedY(3);
//					}
//				}

				if (!isNem && !isCauNguyen) {
					super.upload();
				}

				if (thanhHpBoss.isReady()) {
					if (System.currentTimeMillis() - timeStartAction > listTimeForAction
							.get(listAction[currentAction])) {

						timeStartAction = System.currentTimeMillis();
						if (currentAction == listAction.length - 1) {
							currentAction = 0;
						} else {
							currentAction++;
						}

						isTruot = false;

					} else {
						switch (listAction[currentAction]) {
						case "TRUOTATTACK":
							if (!isTruot) {
								jump();
							}
							if (getSpeedY() >= 0) {
								isTruot = true;
								setStage(NOBEHURT);
								int limitX = 8750;
								int limitX2 = getGame().getCamera().getLimitX2() + GameFrame.getGameWidth() - 150;
								int direction = 0;

								if (getDirector() == DIR_LEFT) {
									direction = 0;
								} else {
									direction = 1;
								}

								if (getSpeedY() == 0) {
									if (direction == 0) {
										setX(getX() - 3);
									} else if (direction == 1) {
										setX(getX() + 3);
									}

									if (getX() <= limitX && direction == 0) {
										setDirector(DIR_RIGHT);
									} else if (getX() >= limitX2 && direction == 1) {
										setDirector(DIR_LEFT);
									}
								}
							}
							break;

						case "NONE":

							isTruot = false;

							isNem = false;

							isDam = false;

							isTroiLen = false;

							setStage(ALIVE);
							resetActionTruot();

							nemLeft.reset();
							nemRight.reset();

							break;

						case "NEMATTACK":

							if (!isNem) {
								jump();
							}

							if (getSpeedY() == 0) {
								isNem = true;
							}

							if (isNem) {
								if (getDirector() == DIR_LEFT) {
									if (nemLeft.isLastFrame()) {
										isNem = false;

										timeStartAction = System.currentTimeMillis();
										if (currentAction == listAction.length - 1) {
											currentAction = 0;
										} else {
											currentAction++;
										}

										if (!isShooting) {
											isShooting = true;
											timeStartShooting = System.nanoTime();
											MetalAnchors moneo = new MetalAnchors(getX(), getY() + 50, getGame(),
													getGame().getRockMan().getX(), getGame().getRockMan().getY());
											getGame().getListObject().addObject(moneo);
										}

										isNem = false;
									}
								} else {
									if (nemRight.isLastFrame()) {
										isNem = false;

										timeStartAction = System.currentTimeMillis();
										if (currentAction == listAction.length - 1) {
											currentAction = 0;
										} else {
											currentAction++;
										}

										if (!isShooting) {
											isShooting = true;
											timeStartShooting = System.nanoTime();
											MetalAnchors moneo = new MetalAnchors(getX() + getWidth(), getY() + 50,
													getGame(), getGame().getRockMan().getX(),
													getGame().getRockMan().getY());
											getGame().getListObject().addObject(moneo);
										}

										isNem = false;
									}
								}
							}

							break;
						case "DAMATTACK":

							float xDen = getGame().getRockMan().getX();
							float yDen = 920;

							if (getY() > yDen && !isDam) {

								if (getX() > xDen) {
									setSpeedX(-7);
								} else {
									setSpeedX(+7);
								}

								setY(getY() - 2);
								setSpeedY(-1);
							} else {

								isDam = true;

								setSpeedX(0);

								if (getSpeedY() == 0 && getY() > 1000) {
									timeStartAction = System.currentTimeMillis();
									if (currentAction == listAction.length - 1) {
										currentAction = 0;
									} else {
										currentAction++;
									}

									isDam = false;

									int count = 0;
									animationXuatHien.setTimeForCreate(0);
									while (count < 10) {
										animationXuatHien.setX(getX() + 30);
										animationXuatHien.setY(getY() + getWidth());
										animationXuatHien.upload();
										count++;
									}
									animationXuatHien.setTimeForCreate(50000000);

									getGame().getCamera().chanDong();
								}

							}
							break;

						case "LANGXUONGDAT":

							if (isViCa && !isSpace) {
								if (System.currentTimeMillis() - timeSpace > 2000) {
									isSpace = true;
								}
							} else if (isViCa && isSpace) {
								setStage(NOBEHURT);
								int limitX = 8750;
								int limitX2 = getGame().getCamera().getLimitX2() + GameFrame.getGameWidth() - 150;
								int direction = 0;

								if (getDirector() == DIR_LEFT) {
									direction = 0;
								} else {
									direction = 1;
								}

								if (getSpeedY() == 0) {
									if (direction == 0) {
										setX(getX() - 3);
									} else if (direction == 1) {
										setX(getX() + 3);
									}

									if (getX() <= limitX && direction == 0) {
										setDirector(DIR_RIGHT);
									} else if (getX() >= limitX2 && direction == 1) {
										setDirector(DIR_LEFT);
									}
								}
								animationXuatHien.setX(getX() + 30);
								animationXuatHien.setY(getY() + getWidth());
								animationXuatHien.upload();
							} else {
								float x = getGame().getRockMan().getX();
								float y = 920;

								if (getY() > y && !isLanXuongDat) {

									if (getX() > x) {
										setSpeedX(-7);
									} else {
										setSpeedX(+7);
									}

									setY(getY() - 2);
									setSpeedY(-1);
								} else {

									isLanXuongDat = true;

									setSpeedX(0);

									if (getSpeedY() == 0 && getY() > 1000 && langXuongDat.isLastFrame()) {

										isLanXuongDat = false;
										isViCa = true;
										timeSpace = System.currentTimeMillis();

										int count = 0;
										animationXuatHien.setTimeForCreate(0);
										while (count < 10) {
											animationXuatHien.setX(getX() + 30);
											animationXuatHien.setY(getY() + getWidth());
											animationXuatHien.upload();
											count++;
										}
										animationXuatHien.setTimeForCreate(50000000);
									}
								}
							}
							break;

						case "TROILEN":

							isViCa = false;
							isSpace = false;
							langXuongDat.reset();

							if (!isTroiLen) {
								int count = 0;
								animationXuatHien.setTimeForCreate(0);
								while (count < 10) {
									animationXuatHien.setX(getX() + 30);
									animationXuatHien.setY(getY() + getWidth());
									animationXuatHien.upload();
									count++;
								}
								animationXuatHien.setTimeForCreate(50000000);

								setSpeedY(-6);
								setY(getY() - 6);

								isTroiLen = true;
							}

							if (getSpeedY() == 0 && getY() > 1000) {
								isTroiLen = false;

								timeStartAction = System.currentTimeMillis();
								if (currentAction == listAction.length - 1) {
									currentAction = 0;
								} else {
									currentAction++;
								}
							}
							break;

						case "TRIEUHOIBOCAP":
							if (!isCauNguyen) {
								if (!isCauNguyen) {
									jump();
								}

								if (getSpeedY() == 0) {
									isCauNguyen = true;
								}
							} else {
								if (!isTaoBoCap) {
									isTaoBoCap = true;

									boCap = new Scorpions(getGame().getRockMan().getX(), 880, getGame(), getDirector());
									getGame().getListObject().addObject(boCap);

									ManagerSound.getInstance().getListSound().get("bossOng")
											.start();
									ManagerSound.getInstance().getListSound().get("bossOng")
											.setFramePosition(0);

								} else {
									if (boCap.isComplete()) {
										boCap.setBlood(0);
										isTaoBoCap = false;
										isCauNguyen = false;

										timeStartAction = System.currentTimeMillis();
										if (currentAction == listAction.length - 1) {
											currentAction = 0;
										} else {
											currentAction++;
										}
									}
								}
							}
							break;

						case "TRIEUHOITACKE":
							if (!isCauNguyen) {
								if (!isCauNguyen) {
									jump();
								}

								if (getSpeedY() == 0) {
									isCauNguyen = true;
								}
							} else {
								if (!isTaoTacKe) {
									isTaoTacKe = true;

									skillGecKo = new SkillSummonGecko(this);
									ManagerSound.getInstance().getListSound().get("bossTacKe")
											.start();
									ManagerSound.getInstance().getListSound().get("bossTacKe")
											.setFramePosition(0);
								} else {

									skillGecKo.upload();

									if (skillGecKo.isComplete()) {

										isTaoTacKe = false;
										isCauNguyen = false;

										timeStartAction = System.currentTimeMillis();
										if (currentAction == listAction.length - 1) {
											currentAction = 0;
										} else {
											currentAction++;
										}
									}
								}
							}
							break;

						case "TRIEUHOIONG":
							if (!isCauNguyen) {
								if (!isCauNguyen) {
									jump();
								}

								if (getSpeedY() == 0) {
									isCauNguyen = true;
								}
							} else {
								if (!isTaoOng) {
									isTaoOng = true;

									float x = getGame().getRockMan().getX() - 400;
									if (x < getGame().getCamera().getLimitX() + 100) {
										x = getGame().getRockMan().getX() + 400;
									}

									bee = new Bee(x, 900, getGame(), getDirector());
									getGame().getListObject().addObject(bee);
									ManagerSound.getInstance().getListSound().get("bossOng")
											.start();
									ManagerSound.getInstance().getListSound().get("bossOng")
											.setFramePosition(0);
								} else {
									if (bee.isComplete()) {
										bee.setBlood(0);
										isTaoOng = false;
										isCauNguyen = false;

										timeStartAction = System.currentTimeMillis();
										if (currentAction == listAction.length - 1) {
											currentAction = 0;
										} else {
											currentAction++;
										}
									}
								}
							}
							break;

						default:
							break;

						}
					}
				}

				if (getSpeedY() == 0) {
					tiepDat();
				} else {
					isRoiKhoiMatDat = true;
				}

				if (isShooting) {
					if (System.nanoTime() - timeStartShooting > 300000000) {
						isShooting = false;
					}
				}

			}
		}

	}

	private void resetActionTruot() {
		truotLeft.getListIgnoreImage().set(0, false);
		truotLeft.getListIgnoreImage().set(1, false);
		truotLeft.getListIgnoreImage().set(2, false);
		truotLeft.getListIgnoreImage().set(3, false);
		truotLeft.getListIgnoreImage().set(4, false);
		truotLeft.setCurrentFrame(0);

		truotRight.getListIgnoreImage().set(0, false);
		truotRight.getListIgnoreImage().set(1, false);
		truotRight.getListIgnoreImage().set(2, false);
		truotRight.getListIgnoreImage().set(3, false);
		truotRight.getListIgnoreImage().set(4, false);
		truotRight.setCurrentFrame(0);
	}

	@Override
	public void draw(Graphics2D g2) {

		if (animationXuatHien.getListRectangle().size() > 0) {
			animationXuatHien.draw(g2);
		}

		if (isDraw && drawRect.getStage() == DrawRectangleOpacity.UP) {
			if (getDirector() == DIR_LEFT) {
				deadLeft.upload(System.nanoTime());
				if (deadLeft.getCurrentFrame() == 1) {
					deadLeft.draw(g2, (int) (getX() - 32 - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
					deadLeft.getListIgnoreImage().set(0, true);
					deadLeft.getListIgnoreImage().set(1, true);
				} else {
					deadLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}
			} else if (getDirector() == DIR_RIGHT) {
				deadRight.upload(System.nanoTime());
				if (deadRight.getCurrentFrame() == 1) {
					deadRight.draw(g2, (int) (getX() - 32 - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
					deadRight.getListIgnoreImage().set(0, true);
					deadRight.getListIgnoreImage().set(1, true);
				} else {
					deadRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}
			}
		}

		if (isDeath) {
			if (!isDraw) {
				isDraw = true;
				setTeam(LEGUE_TEAM);
			}

			if (drawRect.getStage() == DrawRectangleOpacity.UP) {
				whenDeath.upload();
			}

			drawRect.upload();
			drawRect.draw(g2);
			if (drawRect.getStage() == DrawRectangleOpacity.END) {
				if (!isCreateBlackHole) {

					isCreateBlackHole = true;

					BlackHole blackHole = new BlackHole(9176, 860, getGame());
					ManagerSound.getInstance().getListSound().get("blackHole").start();
					getGame().getListObject().getListObject().add(0, blackHole);

					getGame().getWhenDead().setX(9176);
					getGame().getWhenDead().setY(860);

					getGame().setSTAGE(GameWorld.PLAYTUTORIALNOTRECTANGLE);

					getGame().getDrawRectangeEnd().setColor(Color.BLACK);

					this.setStage(DEATH);
				}
			}
		}

		if (!isDraw) {
			if (isXuatHien) {

				if (isTruot) {

					if (getDirector() == DIR_LEFT) {
						if (truotRight.getCurrentFrame() >= 4) {
							truotRight.upload(System.nanoTime());
							truotLeft.setCurrentFrame(truotRight.getCurrentFrame());
						} else {
							truotLeft.upload(System.nanoTime());
						}

						if (truotLeft.getCurrentFrame() == 0) {
							truotLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (truotLeft.getCurrentFrame() == 1) {
							truotLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (truotLeft.getCurrentFrame() == 2) {
							truotLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (truotLeft.getCurrentFrame() == 3) {
							truotLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (truotLeft.getCurrentFrame() == 4) {
							truotLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else {
							truotLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 130 - getGame().getCamera().getY()));
						}

						if (truotLeft.getCurrentFrame() == 4) {
							truotLeft.getListIgnoreImage().set(0, true);
							truotLeft.getListIgnoreImage().set(1, true);
							truotLeft.getListIgnoreImage().set(2, true);
							truotLeft.getListIgnoreImage().set(3, true);
							truotLeft.getListIgnoreImage().set(4, true);
						}

						if (truotLeft.getCurrentFrame() >= 4) {
							animationXuatHien.setX(getX());
							animationXuatHien.setY(getY() + 140);
							animationXuatHien.upload();
						}

					} else if (getDirector() == DIR_RIGHT) {
						if (truotLeft.getCurrentFrame() >= 4) {
							truotLeft.upload(System.nanoTime());
							truotRight.setCurrentFrame(truotLeft.getCurrentFrame());
						} else {
							truotRight.upload(System.nanoTime());
						}

						if (truotRight.getCurrentFrame() == 0) {
							truotRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (truotRight.getCurrentFrame() == 1) {
							truotRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (truotRight.getCurrentFrame() == 2) {
							truotRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (truotRight.getCurrentFrame() == 3) {
							truotRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (truotRight.getCurrentFrame() == 4) {
							truotRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else {
							truotRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() + 130 - getGame().getCamera().getY()));
						}

						if (truotRight.getCurrentFrame() == 4) {
							truotRight.getListIgnoreImage().set(0, true);
							truotRight.getListIgnoreImage().set(1, true);
							truotRight.getListIgnoreImage().set(2, true);
							truotRight.getListIgnoreImage().set(3, true);
							truotRight.getListIgnoreImage().set(4, true);
						}

						if (truotRight.getCurrentFrame() >= 4) {
							animationXuatHien.setX(getX() + getWidth());
							animationXuatHien.setY(getY() + 140);
							animationXuatHien.upload();
						}

					}

				} else if (isNem) {

					if (getDirector() == DIR_LEFT) {
						nemLeft.upload(System.nanoTime());
						nemLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					} else if (getDirector() == DIR_RIGHT) {
						nemRight.upload(System.nanoTime());
						nemRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					}

				}

				else if (isDam || (isLanXuongDat && getSpeedY() != 0)) {

					dam.upload(System.nanoTime());
					dam.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));

				}

				else if (isLanXuongDat && getSpeedY() == 0) {
					langXuongDat.upload(System.nanoTime());
					langXuongDat.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}

				else if (isViCa) {
					if (isSpace) {
						if (getDirector() == DIR_LEFT) {
							viCaLeft.upload(System.nanoTime());
							viCaLeft.draw(g2, (int) (getX() + 40 - getGame().getCamera().getX()),
									(int) (getY() + 140 - getGame().getCamera().getY()));
						} else if (getDirector() == DIR_RIGHT) {
							viCaRight.upload(System.nanoTime());
							viCaRight.draw(g2, (int) (getX() + 40 - getGame().getCamera().getX()),
									(int) (getY() + 140 - getGame().getCamera().getY()));
						}
					} else {
						langXuongDat.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					}

				}

				else if (isCauNguyen) {

					if (getDirector() == DIR_LEFT) {
						cauNguyenLeft.upload(System.nanoTime());
						cauNguyenLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
						if (cauNguyenLeft.getCurrentFrame() == 2) {
							cauNguyenLeft.getListIgnoreImage().set(0, true);
							cauNguyenLeft.getListIgnoreImage().set(1, true);
						}
					} else if (getDirector() == DIR_RIGHT) {
						cauNguyenRight.upload(System.nanoTime());
						cauNguyenRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
						if (cauNguyenRight.getCurrentFrame() == 2) {
							cauNguyenRight.getListIgnoreImage().set(0, true);
							cauNguyenRight.getListIgnoreImage().set(1, true);
						}
					}

				}

				else if (getSpeedX() == 0 && getSpeedY() == 0) {

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
						jumpLeft.getListIgnoreImage().set(0, true);
						jumpLeft.getListIgnoreImage().set(1, true);
						jumpLeft.upload(System.nanoTime());
						jumpLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					} else if (getDirector() == DIR_RIGHT) {
						jumpRight.getListIgnoreImage().set(0, true);
						jumpRight.getListIgnoreImage().set(1, true);
						jumpRight.upload(System.nanoTime());
						jumpRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					}

				} else if (getIsJumping()) {

					if (getDirector() == DIR_LEFT) {
						jumpLeft.upload(System.nanoTime());
						jumpLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
						if (jumpLeft.getCurrentFrame() == 1) {
							jumpLeft.getListIgnoreImage().set(0, true);
							jumpLeft.getListIgnoreImage().set(1, true);
						}
					} else if (getDirector() == DIR_RIGHT) {
						jumpRight.upload(System.nanoTime());
						jumpRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
						if (jumpRight.getCurrentFrame() == 1) {
							jumpRight.getListIgnoreImage().set(0, true);
							jumpRight.getListIgnoreImage().set(1, true);
						}
					}

				}
			} else {

				if (getY() > 920) {

					xuatHienUp.upload(System.nanoTime());
					xuatHienUp.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				} else {
					xuatHien.upload(System.nanoTime());
					xuatHien.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
					if (xuatHien.isLastFrame()) {
						isXuatHien = true;
					}
				}

			}

			if (isDrawThanhHp) {
				thanhHpBoss.draw(g2);
			}
		}
	}

	@Override
	public void run() {
		if (getDirector() == DIR_LEFT) {
			setSpeedX(-5);
		} else {
			setSpeedX(5);
		}
	}

	@Override
	public void jump() {
		if (!getIsJumping()) {
			setY(getY() - 7);
			setSpeedY(-7);

			setJumping(true);
		}
	}

	@Override
	public void tiepDat() {

		if (isRoiKhoiMatDat) {
			isRoiKhoiMatDat = false;
		}

		setSpeedX(0);

		setIsJumping(false);

		jumpLeft.reset();
		jumpRight.reset();
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();

		if (isTruot) {
			if (getDirector() == DIR_LEFT) {
				return new Rectangle(rect.x - 10, rect.y + 140, rect.width + 35, rect.height - 140);
			} else {
				return new Rectangle(rect.x + 5, rect.y + 140, rect.width + 70, rect.height - 140);
			}
		} else if (isViCa) {
			return new Rectangle(rect.x + 33, rect.y + 140, rect.width - 66, rect.height - 140);
		}

		return new Rectangle(rect.x + 20, rect.y + 40, rect.width - 40, rect.height - 40);
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
		isDeath = true;
		whenDeath.setX(getX());
		whenDeath.setY(getY());

		while (!animationXuatHien.getListRectangle().isEmpty()) {
			animationXuatHien.getListRectangle().remove(0);
		}
	}

	public boolean isPlayTutorial() {
		return isPlayTutorial;
	}

	public void setPlayTutorial(boolean isPlayTutorial) {
		this.isPlayTutorial = isPlayTutorial;
	}

	public boolean isXuatHien() {
		return isXuatHien;
	}

	public void setXuatHien(boolean isXuatHien) {
		this.isXuatHien = isXuatHien;
	}

	public HashMap<String, Integer> getListTimeForAction() {
		return listTimeForAction;
	}

	public void setListTimeForAction(HashMap<String, Integer> listTimeForAction) {
		this.listTimeForAction = listTimeForAction;
	}

	public String[] getListAction() {
		return listAction;
	}

	public void setListAction(String[] listAction) {
		this.listAction = listAction;
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

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public long getTimeStartShooting() {
		return timeStartShooting;
	}

	public void setTimeStartShooting(long timeStartShooting) {
		this.timeStartShooting = timeStartShooting;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public Animation getXuatHienUp() {
		return xuatHienUp;
	}

	public void setXuatHienUp(Animation xuatHienUp) {
		this.xuatHienUp = xuatHienUp;
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

	public long getTimeForXuatHien() {
		return timeForXuatHien;
	}

	public void setTimeForXuatHien(long timeForXuatHien) {
		this.timeForXuatHien = timeForXuatHien;
	}

	public long getTimeStartXuatHien() {
		return timeStartXuatHien;
	}

	public void setTimeStartXuatHien(long timeStartXuatHien) {
		this.timeStartXuatHien = timeStartXuatHien;
	}

	public AnimationItemBossIntroStage getAnimationXuatHien() {
		return animationXuatHien;
	}

	public void setAnimationXuatHien(AnimationItemBossIntroStage animationXuatHien) {
		this.animationXuatHien = animationXuatHien;
	}

	public Animation getXuatHien() {
		return xuatHien;
	}

	public void setXuatHien(Animation xuatHien) {
		this.xuatHien = xuatHien;
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

	public Animation getTruotLeft() {
		return truotLeft;
	}

	public void setTruotLeft(Animation truotLeft) {
		this.truotLeft = truotLeft;
	}

	public Animation getTruotRight() {
		return truotRight;
	}

	public void setTruotRight(Animation truotRight) {
		this.truotRight = truotRight;
	}

	public Animation getNemLeft() {
		return nemLeft;
	}

	public void setNemLeft(Animation nemLeft) {
		this.nemLeft = nemLeft;
	}

	public Animation getNemRight() {
		return nemRight;
	}

	public void setNemRight(Animation nemRight) {
		this.nemRight = nemRight;
	}

	public Animation getDam() {
		return dam;
	}

	public void setDam(Animation dam) {
		this.dam = dam;
	}

	public Animation getLangXuongDat() {
		return langXuongDat;
	}

	public void setLangXuongDat(Animation langXuongDat) {
		this.langXuongDat = langXuongDat;
	}

	public Animation getViCaLeft() {
		return viCaLeft;
	}

	public void setViCaLeft(Animation viCaLeft) {
		this.viCaLeft = viCaLeft;
	}

	public Animation getViCaRight() {
		return viCaRight;
	}

	public void setViCaRight(Animation viCaRight) {
		this.viCaRight = viCaRight;
	}

	public Animation getCauNguyenLeft() {
		return cauNguyenLeft;
	}

	public void setCauNguyenLeft(Animation cauNguyenLeft) {
		this.cauNguyenLeft = cauNguyenLeft;
	}

	public Animation getCauNguyenRight() {
		return cauNguyenRight;
	}

	public void setCauNguyenRight(Animation cauNguyenRight) {
		this.cauNguyenRight = cauNguyenRight;
	}

	public boolean isCreateSanDau() {
		return isCreateSanDau;
	}

	public void setCreateSanDau(boolean isCreateSanDau) {
		this.isCreateSanDau = isCreateSanDau;
	}

	public boolean isChanDong() {
		return isChanDong;
	}

	public void setChanDong(boolean isChanDong) {
		this.isChanDong = isChanDong;
	}

	public boolean isTruot() {
		return isTruot;
	}

	public void setTruot(boolean isTruot) {
		this.isTruot = isTruot;
	}

	public boolean isNem() {
		return isNem;
	}

	public void setNem(boolean isNem) {
		this.isNem = isNem;
	}

	public boolean isDam() {
		return isDam;
	}

	public void setDam(boolean isDam) {
		this.isDam = isDam;
	}

	public boolean isLanXuongDat() {
		return isLanXuongDat;
	}

	public void setLanXuongDat(boolean isLanXuongDat) {
		this.isLanXuongDat = isLanXuongDat;
	}

	public boolean isViCa() {
		return isViCa;
	}

	public void setViCa(boolean isViCa) {
		this.isViCa = isViCa;
	}

	public boolean isSpace() {
		return isSpace;
	}

	public void setSpace(boolean isSpace) {
		this.isSpace = isSpace;
	}

	public long getTimeSpace() {
		return timeSpace;
	}

	public void setTimeSpace(long timeSpace) {
		this.timeSpace = timeSpace;
	}

	public boolean isTroiLen() {
		return isTroiLen;
	}

	public void setTroiLen(boolean isTroiLen) {
		this.isTroiLen = isTroiLen;
	}

	public boolean isCauNguyen() {
		return isCauNguyen;
	}

	public void setCauNguyen(boolean isCauNguyen) {
		this.isCauNguyen = isCauNguyen;
	}

	public boolean isTaoBoCap() {
		return isTaoBoCap;
	}

	public void setTaoBoCap(boolean isTaoBoCap) {
		this.isTaoBoCap = isTaoBoCap;
	}

	public Scorpions getBoCap() {
		return boCap;
	}

	public void setBoCap(Scorpions boCap) {
		this.boCap = boCap;
	}

	public BossHealthBar getThanhHpBoss() {
		return thanhHpBoss;
	}

	public void setThanhHpBoss(BossHealthBar thanhHpBoss) {
		this.thanhHpBoss = thanhHpBoss;
	}

	public boolean isDrawThanhHp() {
		return isDrawThanhHp;
	}

	public void setDrawThanhHp(boolean isDrawThanhHp) {
		this.isDrawThanhHp = isDrawThanhHp;
	}

	public Animation getDeadLeft() {
		return deadLeft;
	}

	public void setDeadLeft(Animation deadLeft) {
		this.deadLeft = deadLeft;
	}

	public Animation getDeadRight() {
		return deadRight;
	}

	public void setDeadRight(Animation deadRight) {
		this.deadRight = deadRight;
	}

	public boolean isTaoTacKe() {
		return isTaoTacKe;
	}

	public void setTaoTacKe(boolean isTaoTacKe) {
		this.isTaoTacKe = isTaoTacKe;
	}

	public SkillSummonGecko getSkillGecKo() {
		return skillGecKo;
	}

	public void setSkillGecKo(SkillSummonGecko skillGecKo) {
		this.skillGecKo = skillGecKo;
	}

	public boolean isTaoOng() {
		return isTaoOng;
	}

	public void setTaoOng(boolean isTaoOng) {
		this.isTaoOng = isTaoOng;
	}

	public Bee getBee() {
		return bee;
	}

	public void setBee(Bee bee) {
		this.bee = bee;
	}

	public WhenBossDeath getWhenDeath() {
		return whenDeath;
	}

	public void setWhenDeath(WhenBossDeath whenDeath) {
		this.whenDeath = whenDeath;
	}

	public DrawRectangleOpacity getDrawRect() {
		return drawRect;
	}

	public void setDrawRect(DrawRectangleOpacity drawRect) {
		this.drawRect = drawRect;
	}

	public boolean isDraw() {
		return isDraw;
	}

	public void setDraw(boolean isDraw) {
		this.isDraw = isDraw;
	}

	public boolean isDeath() {
		return isDeath;
	}

	public void setDeath(boolean isDeath) {
		this.isDeath = isDeath;
	}

	public boolean isCreateBlackHole() {
		return isCreateBlackHole;
	}

	public void setCreateBlackHole(boolean isCreateBlackHole) {
		this.isCreateBlackHole = isCreateBlackHole;
	}

}
