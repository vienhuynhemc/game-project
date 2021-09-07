package mainPlayerMegamanX;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import actionForHuman.AllAction;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.HunmanMegamanX;
import particulerObject.ParticulerObjectMegamanX;
import sound.ManagerSound;
import tool.Abyss;
import tool.BossDoor;
import tool.Stairs;
import view.GameFrame;

public class RockMan extends HunmanMegamanX implements AllAction {

	private Random rd;

	private long lastTimeShooting;
	private long timeForShooting;
	private long timeBeginNapDan;
	private boolean isShooting;
	private boolean isNapDan;
	private boolean isBanDanLvCao;

	private boolean isWallCling;
	private boolean isWallKick;

	private Animation dungImLeft, dungImRight, dungBanLeft, dungBanRight, dungBanDanLvCaoLeft, dungBanDanLvCaoRight;
	private Animation runLeft, runRight, runAttackLeft, runAttackRight;
	private Animation jumpLeft, jumpRight, jumpAttackLeft, jumpAttackRight;
	private Animation dickLeft, dickRight, dickAttackLeft, dickAttackRight, dickAttackBulletHighLeft,
			dickAttackBulletHighRight;
	private Animation tiepDatLeft, tiepDatRight, tiepDatAttackRight, tiepDatAttackLeft;
	private Animation wallClingLeft, wallClingRight, wallClingAttackLeft, wallClingAttackRight;
	private Animation wallKickLeft, wallKickRight, wallKickAttackLeft, wallKickAttackRight;
	private Animation behurtLeft, behurtRight;

	private Animation startCauThang, endCauThangLeft, endCauThangRight, attackCauThangLeft, attackCauThangRight,
			leoCauThangLeft, leoCauThangRight;
	private Animation animeCauThangCurrently;
	private boolean isEndCauThang;

	private boolean isChamVucSau;

	private RockManEffort effort;

	private boolean isRoiKhoiMatDat;

	private Animation endgame;
	private boolean endGame;

	public RockMan(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f, 72, 94, 100, 0);

		rd = new Random();

		createAnimationForRocMan();

		effort = new RockManEffort(this);

		timeForShooting = 500000000;

		setSpeedY(5);

		setDirector(ParticulerObjectMegamanX.DIR_RIGHT);
		setTeam(LEGUE_TEAM);

		setIsJumping(false);
		setDicking(false);
		setShooting(false);
		setWallCling(false);
		setWallKick(false);
	}

	public void createAnimationForRocMan() {

		endgame = new Animation(getGame().getInputData().getListAnimation().get("endgame"));
		endgame.setIsRepeat(false);

		behurtLeft = new Animation(getGame().getInputData().getListAnimation().get("behurt"));
		behurtLeft.setIsRepeat(false);
		behurtRight = new Animation(getGame().getInputData().getListAnimation().get("behurt"));
		behurtRight.daoNguoc();
		behurtRight.setIsRepeat(false);
		dungImLeft = new Animation(getGame().getInputData().getListAnimation().get("dungIm"));
		dungImRight = new Animation(getGame().getInputData().getListAnimation().get("dungIm"));
		dungImRight.daoNguoc();
		dungBanLeft = new Animation(getGame().getInputData().getListAnimation().get("dungBan"));
		dungBanRight = new Animation(getGame().getInputData().getListAnimation().get("dungBan"));
		dungBanRight.daoNguoc();
		dungBanDanLvCaoLeft = new Animation(getGame().getInputData().getListAnimation().get("dungBanDanLvCao"));
		dungBanDanLvCaoRight = new Animation(getGame().getInputData().getListAnimation().get("dungBanDanLvCao"));
		dungBanDanLvCaoRight.daoNguoc();
		runLeft = new Animation(getGame().getInputData().getListAnimation().get("run"));
		runRight = new Animation(getGame().getInputData().getListAnimation().get("run"));
		runRight.daoNguoc();
		runAttackLeft = new Animation(getGame().getInputData().getListAnimation().get("runAttack"));
		runAttackRight = new Animation(getGame().getInputData().getListAnimation().get("runAttack"));
		runAttackRight.daoNguoc();
		jumpLeft = new Animation(getGame().getInputData().getListAnimation().get("jump"));
		jumpRight = new Animation(getGame().getInputData().getListAnimation().get("jump"));
		jumpRight.daoNguoc();
		jumpLeft.setIsRepeat(false);
		jumpRight.setIsRepeat(false);
		jumpAttackLeft = new Animation(getGame().getInputData().getListAnimation().get("jumpAttack"));
		jumpAttackRight = new Animation(getGame().getInputData().getListAnimation().get("jumpAttack"));
		jumpAttackRight.daoNguoc();
		jumpAttackLeft.setIsRepeat(false);
		jumpAttackRight.setIsRepeat(false);
		tiepDatLeft = new Animation(getGame().getInputData().getListAnimation().get("tiepDat"));
		tiepDatRight = new Animation(getGame().getInputData().getListAnimation().get("tiepDat"));
		tiepDatRight.daoNguoc();
		tiepDatAttackLeft = new Animation(getGame().getInputData().getListAnimation().get("tiepDatAttack"));
		tiepDatAttackRight = new Animation(getGame().getInputData().getListAnimation().get("tiepDatAttack"));
		tiepDatAttackRight.daoNguoc();
		dickLeft = new Animation(getGame().getInputData().getListAnimation().get("dick"));
		dickRight = new Animation(getGame().getInputData().getListAnimation().get("dick"));
		dickRight.daoNguoc();
		dickLeft.setIsRepeat(false);
		dickRight.setIsRepeat(false);
		dickAttackLeft = new Animation(getGame().getInputData().getListAnimation().get("dickAttack"));
		dickAttackRight = new Animation(getGame().getInputData().getListAnimation().get("dickAttack"));
		dickAttackRight.daoNguoc();
		dickAttackBulletHighLeft = new Animation(
				getGame().getInputData().getListAnimation().get("dickAttackBulletHigh"));
		dickAttackBulletHighRight = new Animation(
				getGame().getInputData().getListAnimation().get("dickAttackBulletHigh"));
		dickAttackBulletHighRight.daoNguoc();
		wallClingLeft = new Animation(getGame().getInputData().getListAnimation().get("wallCling"));
		wallClingLeft.setIsRepeat(false);
		wallClingRight = new Animation(getGame().getInputData().getListAnimation().get("wallCling"));
		wallClingRight.setIsRepeat(false);
		wallClingRight.daoNguoc();
		wallKickLeft = new Animation(getGame().getInputData().getListAnimation().get("wallKick"));
		wallKickLeft.setIsRepeat(false);
		wallKickRight = new Animation(getGame().getInputData().getListAnimation().get("wallKick"));
		wallKickRight.setIsRepeat(false);
		wallKickRight.daoNguoc();
		wallClingAttackLeft = new Animation(getGame().getInputData().getListAnimation().get("wallClingAttack"));
		wallClingAttackLeft.setIsRepeat(false);
		wallClingAttackRight = new Animation(getGame().getInputData().getListAnimation().get("wallClingAttack"));
		wallClingAttackRight.setIsRepeat(false);
		wallClingAttackRight.daoNguoc();
		wallKickAttackLeft = new Animation(getGame().getInputData().getListAnimation().get("wallKickAttack"));
		wallKickAttackLeft.setIsRepeat(false);
		wallKickAttackRight = new Animation(getGame().getInputData().getListAnimation().get("wallKickAttack"));
		wallKickAttackRight.setIsRepeat(false);
		wallKickAttackRight.daoNguoc();

		startCauThang = new Animation(getGame().getInputData().getListAnimation().get("startCauThang"));
		startCauThang.setIsRepeat(false);
		endCauThangLeft = new Animation(getGame().getInputData().getListAnimation().get("endCauThang"));
		endCauThangRight = new Animation(getGame().getInputData().getListAnimation().get("endCauThang"));
		endCauThangRight.daoNguoc();
		attackCauThangLeft = new Animation(getGame().getInputData().getListAnimation().get("attackCauThang"));
		attackCauThangRight = new Animation(getGame().getInputData().getListAnimation().get("attackCauThang"));
		attackCauThangRight.daoNguoc();
		leoCauThangLeft = new Animation(getGame().getInputData().getListAnimation().get("leoCauThangLeft"));
		leoCauThangRight = new Animation(getGame().getInputData().getListAnimation().get("leoCauThangRight"));
		animeCauThangCurrently = startCauThang;
	}

	@Override
	public void upload() {
		if (!endGame) {
			if (getStage() != SWITCHMAPLESTORY) {
				boolean isTrue = false;
				for (int i = 0; i < getGame().getListDungCu().getListDungCu().size(); i++) {
					if (getGame().getListDungCu().getListDungCu().get(i).getClass() == Abyss.class) {
						if (getGame().getListDungCu().getListDungCu().get(i)
								.isVaCham(getRectangleXuLiVaCham()) != null) {
							setChamVucSau(true);
							isTrue = true;
						}
					}
					if (getGame().getListDungCu().getListDungCu().get(i).getClass() == BossDoor.class) {
						if (getGame().getListDungCu().getListDungCu().get(i)
								.isVaCham(getRectangleXuLiVaCham()) != null) {
							if (getGame().getSTAGE() != GameWorldMegamanX.ATTACKBOSS) {
								BossDoor bossDoor = (BossDoor) getGame().getListDungCu().getListDungCu().get(i);
								bossDoor.setStage(BossDoor.OPEN);
								getGame().getListDungCu().getListDungCu().remove(i);
								getGame().getListDungCu().getListDungCu().add(bossDoor);
							}
						}
					}
				}
				if (isChamVucSau) {
					if (isOutCamera()) {
						isChamVucSau = false;
						getToDeath();
						setStage(DEATH);
					}
				}
				if (!isTrue && isChamVucSau) {
					setChamVucSau(false);
				}

				if (effort.getAnime().getCurrentFrame() > 1) {
					super.upload();
				}

				if (getX() <= getGame().getCamera().getLimitX()) {
					setSpeedX(0);
					setX(getGame().getCamera().getLimitX());
				}

				if (getSpeedY() == 0) {
					tiepDat();
				} else {
					isRoiKhoiMatDat = true;
				}

				if (getSpeedX() != 0 || getSpeedY() != 0) {
					isBanDanLvCao = false;
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
									ManagerSound.getInstance().getListSound().get("rmbehurt").start();
									ManagerSound.getInstance().getListSound().get("rmbehurt").setFramePosition(0);
								}
							}
						}
					}
				}

				if (isShooting) {
					if (System.nanoTime() - lastTimeShooting > timeForShooting)
						isShooting = false;
				}

				effort.upload();
			} else {
				if (getY() >= 1000) {
					setX(getX() + getSpeedX());
					setY(getY() + getSpeedY());
					if (getY() == 1000) {
						getGame().getDrawRectangeEnd().setColor(Color.BLACK);
						getGame().getDrawRectangeEnd().setNow(0);
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (endGame) {
			setMass(0);
			endgame.upload(System.nanoTime());
			if (endgame.getCurrentFrame() == 5) {
				endgame.draw(g2, (int) (getX() - 10 - getGame().getCamera().getX()),
						(int) (getY() - 60 - getGame().getCamera().getY()));
			} else if (endgame.getCurrentFrame() == 6 || endgame.getCurrentFrame() == 7
					|| endgame.getCurrentFrame() == 8) {
				endgame.draw(g2, (int) (getX() - 10 - getGame().getCamera().getX()),
						(int) (getY() - 60 - getGame().getCamera().getY()));
			} else if (endgame.getCurrentFrame() == 9) {
				endgame.draw(g2, (int) (getX() + 30 - getGame().getCamera().getX()),
						(int) (getY() - 60 - getGame().getCamera().getY()));
			} else {
				endgame.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 60 - getGame().getCamera().getY()));
			}
			if (endgame.isLastFrame()) {
				if (!isOutCamera()) {
					setY(getY() - 7);
				}
			}

			if (endgame.getCurrentFrame() == 0) {
				ManagerSound.getInstance().getListSound().get("menuPress").start();
			}

		} else

		{
			if (getStage() != SWITCHMAPLESTORY) {
				if (!effort.isUnlock()) {
					effort.drawXuatHien(g2, DIR_RIGHT);
				} else {
					if (getStage() == ALIVE || getStage() == NOBEHURT || getStage() == FEY) {

						if (getStage() == NOBEHURT && System.nanoTime() % 3 == 2) {
						} else {
							if (isLeoCauThang()) {
								if (!startCauThang.isLastFrame()) {
									startCauThang.upload(System.nanoTime());
									startCauThang.draw(g2, (int) (getX() - getGame().getCamera().getX()),
											(int) (getY() - getGame().getCamera().getY()));
								} else {
									if (getGame().getAction().getActionKeyForMegamanX().isPressUp()) {
										animeCauThangCurrently = leoCauThangLeft;
										if (isShooting) {
											if (getDirector() == DIR_LEFT) {
												attackCauThangLeft.upload(System.nanoTime());
												attackCauThangLeft.draw(g2,
														(int) (getX() - 20 - getGame().getCamera().getX()),
														(int) (getY() - getGame().getCamera().getY()));
											} else if (getDirector() == DIR_RIGHT) {
												attackCauThangRight.upload(System.nanoTime());
												attackCauThangRight.draw(g2,
														(int) (getX() - getGame().getCamera().getX()),
														(int) (getY() - getGame().getCamera().getY()));
											}
										} else {
											leoCauThangLeft.upload(System.nanoTime());
											leoCauThangLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										}
									} else if (getGame().getAction().getActionKeyForMegamanX().isPressDown()) {
										animeCauThangCurrently = leoCauThangRight;
										if (isShooting) {
											if (getDirector() == DIR_LEFT) {
												attackCauThangLeft.upload(System.nanoTime());
												attackCauThangLeft.draw(g2,
														(int) (getX() - 20 - getGame().getCamera().getX()),
														(int) (getY() - getGame().getCamera().getY()));
											} else if (getDirector() == DIR_RIGHT) {
												attackCauThangRight.upload(System.nanoTime());
												attackCauThangRight.draw(g2,
														(int) (getX() - getGame().getCamera().getX()),
														(int) (getY() - getGame().getCamera().getY()));
											}
										} else {
											leoCauThangRight.upload(System.nanoTime());
											leoCauThangRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										}
									} else {
										if (isShooting) {
											if (getDirector() == DIR_LEFT) {
												attackCauThangLeft.upload(System.nanoTime());
												attackCauThangLeft.draw(g2,
														(int) (getX() - 20 - getGame().getCamera().getX()),
														(int) (getY() - getGame().getCamera().getY()));
											} else if (getDirector() == DIR_RIGHT) {
												attackCauThangRight.upload(System.nanoTime());
												attackCauThangRight.draw(g2,
														(int) (getX() - getGame().getCamera().getX()),
														(int) (getY() - getGame().getCamera().getY()));
											}
										} else if (isEndCauThang) {
											if (getDirector() == DIR_LEFT) {
												endCauThangLeft.upload(System.nanoTime());
												endCauThangLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
														(int) (getY() - getGame().getCamera().getY()));
												if (endCauThangLeft.isLastFrame()) {
													setY(getY() - 50);
													setLeoCauThang(false);
													isEndCauThang = false;
													endCauThangLeft.reset();
												}
											} else if (getDirector() == DIR_RIGHT) {
												endCauThangRight.upload(System.nanoTime());
												endCauThangRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
														(int) (getY() - getGame().getCamera().getY()));
												if (endCauThangRight.isLastFrame()) {
													setY(getY() - 50);
													setLeoCauThang(false);
													isEndCauThang = false;
													endCauThangRight.reset();
												}
											}
										} else {
											animeCauThangCurrently.draw(g2,
													(int) (getX() - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										}
									}
								}
							} else if (getIsDicking()) {
								if (isBanDanLvCao) {
									if (getDirector() == DIR_LEFT) {
										dickAttackBulletHighLeft.upload(System.nanoTime());
										if (dickAttackBulletHighLeft.getCurrentFrame() == 2
												|| dickAttackBulletHighLeft.getCurrentFrame() == 3) {
											dickAttackBulletHighLeft.draw(g2,
													(int) (getX() - getGame().getCamera().getX()),
													(int) (getY() + 6 - getGame().getCamera().getY()));
										} else {
											dickAttackBulletHighLeft.draw(g2,
													(int) (getX() - getGame().getCamera().getX()),
													(int) (getY() + 22 - getGame().getCamera().getY()));
										}
										if (dickAttackBulletHighLeft.isLastFrame())
											setBanDanLvCao(false);
									} else if (getDirector() == DIR_RIGHT) {
										dickAttackBulletHighRight.upload(System.nanoTime());
										if (dickAttackBulletHighRight.getCurrentFrame() == 2
												|| dickAttackBulletHighRight.getCurrentFrame() == 3) {
											dickAttackBulletHighRight.draw(g2,
													(int) (getX() - getGame().getCamera().getX()),
													(int) (getY() + 6 - getGame().getCamera().getY()));
										} else {
											dickAttackBulletHighRight.draw(g2,
													(int) (getX() - getGame().getCamera().getX()),
													(int) (getY() + 22 - getGame().getCamera().getY()));
										}
										if (dickAttackBulletHighRight.isLastFrame())
											setBanDanLvCao(false);
									}
								} else {
									if (getDirector() == DIR_LEFT) {
										if (isShooting) {
											dickLeft.upload(System.nanoTime());
											dickAttackLeft.setCurrentFrame(dickLeft.getCurrentFrame());
											if (dickAttackLeft.getCurrentFrame() == 0) {
												setY(getY() + 28);
												drawHanhDongLeft(g2, dickAttackLeft);
												setY(getY() - 28);
											} else if (dickAttackLeft.getCurrentFrame() == 1) {
												setY(getY() + 28);
												drawHanhDongLeft(g2, dickAttackLeft);
												setY(getY() - 28);
											}
										} else if (!isShooting) {
											if (dickLeft.getCurrentFrame() == 0) {
												setY(getY() + 28);
												drawAndUploadHanhDong(g2, dickLeft);
												setY(getY() - 28);
											} else if (dickLeft.getCurrentFrame() == 1) {
												setY(getY() + 28);
												drawAndUploadHanhDong(g2, dickLeft);
												setY(getY() - 28);
											}
										}
									} else if (getDirector() == DIR_RIGHT) {
										if (isShooting) {
											dickRight.upload(System.nanoTime());
											dickAttackRight.setCurrentFrame(dickRight.getCurrentFrame());
											if (dickAttackRight.getCurrentFrame() == 0) {
												setY(getY() + 28);
												drawHanhDongRight(g2, dickAttackRight);
												setY(getY() - 28);
											} else if (dickAttackRight.getCurrentFrame() == 1) {
												setY(getY() + 28);
												drawHanhDongRight(g2, dickAttackRight);
												setY(getY() - 28);
											}
										} else if (!isShooting) {
											if (dickRight.getCurrentFrame() == 0) {
												setY(getY() + 28);
												drawAndUploadHanhDong(g2, dickRight);
												setY(getY() - 28);
											} else if (dickRight.getCurrentFrame() == 1) {
												setY(getY() + 28);
												drawAndUploadHanhDong(g2, dickRight);
												setY(getY() - 28);
											}
										}
									}
								}
							} else if (getIsJumping() == true && getSpeedY() == 0) {
								if (getDirector() == DIR_LEFT) {
									if (isShooting) {
										tiepDatLeft.upload(System.nanoTime());
										tiepDatAttackLeft.setCurrentFrame(tiepDatLeft.getCurrentFrame());
										drawHanhDongLeft(g2, tiepDatAttackLeft);
										if (tiepDatAttackLeft.isLastFrame())
											tiepDat();
									} else if (!isShooting) {
										drawAndUploadHanhDong(g2, tiepDatLeft);
										if (tiepDatLeft.isLastFrame())
											tiepDat();
									}
								} else if (getDirector() == DIR_RIGHT) {
									if (isShooting) {
										tiepDatRight.upload(System.nanoTime());
										tiepDatAttackRight.setCurrentFrame(tiepDatRight.getCurrentFrame());
										drawHanhDongRight(g2, tiepDatAttackRight);
										if (tiepDatAttackRight.isLastFrame())
											tiepDat();
									} else if (!isShooting) {
										drawAndUploadHanhDong(g2, tiepDatRight);
										if (tiepDatRight.isLastFrame())
											tiepDat();
									}
								}
							} else if (getSpeedX() == 0 && getSpeedY() == 0) {
								if (isBanDanLvCao) {
									if (getDirector() == DIR_LEFT) {
										if (dungBanDanLvCaoLeft.getCurrentFrame() == 2
												|| dungBanDanLvCaoLeft.getCurrentFrame() == 3) {
											dungBanDanLvCaoLeft.upload(System.nanoTime());
											dungBanDanLvCaoLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
													(int) (getY() - 4 - getGame().getCamera().getY()));
										} else if (dungBanDanLvCaoLeft.getCurrentFrame() == 4) {
											dungBanDanLvCaoLeft.upload(System.nanoTime());
											dungBanDanLvCaoLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
													(int) (getY() - 8 - getGame().getCamera().getY()));
										} else {
											drawAndUploadHanhDong(g2, dungBanDanLvCaoLeft);
										}
										if (dungBanDanLvCaoLeft.isLastFrame())
											setBanDanLvCao(false);
									} else if (getDirector() == DIR_RIGHT) {
										if (dungBanDanLvCaoRight.getCurrentFrame() == 2
												|| dungBanDanLvCaoRight.getCurrentFrame() == 3) {
											dungBanDanLvCaoRight.upload(System.nanoTime());
											dungBanDanLvCaoRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
													(int) (getY() - 4 - getGame().getCamera().getY()));
										} else if (dungBanDanLvCaoRight.getCurrentFrame() == 4) {
											dungBanDanLvCaoRight.upload(System.nanoTime());
											dungBanDanLvCaoRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
													(int) (getY() - 8 - getGame().getCamera().getY()));
										} else {
											drawAndUploadHanhDong(g2, dungBanDanLvCaoRight);
										}
										if (dungBanDanLvCaoRight.isLastFrame())
											setBanDanLvCao(false);
									}
								} else {
									if (getDirector() == DIR_LEFT) {
										if (isShooting) {
											drawAndUploadHanhDong(g2, dungBanLeft);
										} else if (!isShooting) {
											drawAndUploadHanhDong(g2, dungImLeft);
										}
									} else if (getDirector() == DIR_RIGHT) {
										if (isShooting) {
											drawAndUploadHanhDong(g2, dungBanRight);
										} else if (!isShooting) {
											drawAndUploadHanhDong(g2, dungImRight);
										}
									}
								}
							} else if (getSpeedX() != 0 && getSpeedY() == 0) {
								if (getDirector() == DIR_LEFT) {
									if (isShooting) {
										runLeft.upload(System.nanoTime());
										runAttackLeft.setCurrentFrame(runLeft.getCurrentFrame());
										drawHanhDongLeft(g2, runAttackLeft);
										if (runAttackLeft.getCurrentFrame() == 1) {
											runAttackLeft.getListIgnoreImage().set(0, true);
											runAttackLeft.getListIgnoreImage().set(1, true);
										}
									} else if (!isShooting) {
										drawAndUploadHanhDong(g2, runLeft);
										if (runLeft.getCurrentFrame() == 1) {
											runLeft.getListIgnoreImage().set(0, true);
											runLeft.getListIgnoreImage().set(1, true);
										}
									}
								} else if (getDirector() == DIR_RIGHT) {
									if (isShooting) {
										runRight.upload(System.nanoTime());
										runAttackRight.setCurrentFrame(runRight.getCurrentFrame());
										drawHanhDongRight(g2, runAttackRight);
										if (runAttackRight.getCurrentFrame() == 1) {
											runAttackRight.getListIgnoreImage().set(0, true);
											runAttackRight.getListIgnoreImage().set(1, true);
										}
									} else if (!isShooting) {
										runRight.upload(System.nanoTime());
										if (runRight.getCurrentFrame() == 3) {
											runRight.draw(g2, (int) (getX() - 10 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 4) {
											runRight.draw(g2, (int) (getX() - 24 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 5) {
											runRight.draw(g2, (int) (getX() - 24 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 6) {
											runRight.draw(g2, (int) (getX() - 6 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 7) {
											runRight.draw(g2, (int) (getX() + 12 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 8) {
											runRight.draw(g2, (int) (getX() + 18 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 9) {
											runRight.draw(g2, (int) (getX() + 2 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 10) {
											runRight.draw(g2, (int) (getX() - 8 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 11) {
											runRight.draw(g2, (int) (getX() - 12 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 12) {
											runRight.draw(g2, (int) (getX() - 12 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 13) {
											runRight.draw(g2, (int) (getX() - 4 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 14) {
											runRight.draw(g2, (int) (getX() + 8 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else if (runRight.getCurrentFrame() == 15) {
											runRight.draw(g2, (int) (getX() + 18 - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										} else {
											runRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
													(int) (getY() - getGame().getCamera().getY()));
										}
										if (runRight.getCurrentFrame() == 1) {
											runRight.getListIgnoreImage().set(0, true);
											runRight.getListIgnoreImage().set(1, true);
										}
									}
								}
							} else if (getSpeedY() != 0 && getIsJumping()) {
								if (isWallKick) {
									if (getDirector() == DIR_LEFT) {
										if (isShooting) {
											wallKickLeft.upload(System.nanoTime());
											wallKickAttackLeft.setCurrentFrame(wallKickLeft.getCurrentFrame());
											drawHanhDongRight(g2, wallClingAttackLeft);
											if (wallKickAttackLeft.isLastFrame())
												isWallKick = false;
										} else {
											drawAndUploadHanhDong(g2, wallKickLeft);
											if (wallKickLeft.isLastFrame())
												isWallKick = false;
										}
									} else if (getDirector() == DIR_RIGHT) {
										if (isShooting) {
											wallKickRight.upload(System.nanoTime());
											wallKickAttackRight.setCurrentFrame(wallKickRight.getCurrentFrame());
											drawHanhDongLeft(g2, wallKickAttackRight);
											if (wallKickAttackRight.isLastFrame())
												isWallKick = false;
										} else {
											drawAndUploadHanhDong(g2, wallKickRight);
											if (wallKickRight.isLastFrame())
												isWallKick = false;
										}
									}
								} else {
									if (getDirector() == DIR_LEFT) {
										Rectangle rect = new Rectangle((int) (getX() + getSpeedX()), (int) getY(),
												(int) getWidth(), (int) getHeight());
										if (getGame().getAction().getActionKeyForMegamanX().isPressLeft()
												&& getGame().getPhysicalMap().vaChamBenTrai(rect) != null) {
											setSpeedY(1);
											setWallCling(true);
											if (isShooting) {
												wallClingLeft.upload(System.nanoTime());
												wallClingAttackLeft.setCurrentFrame(wallClingLeft.getCurrentFrame());
												drawHanhDongRight(g2, wallClingAttackLeft);
											} else if (!isShooting) {
												drawAndUploadHanhDong(g2, wallClingLeft);
											}
											setIsJumping(false);
										} else {
											if (isShooting) {
												jumpLeft.upload(System.nanoTime());
												jumpAttackLeft.setCurrentFrame(jumpLeft.getCurrentFrame());
												drawHanhDongLeft(g2, jumpAttackLeft);
											} else if (!isShooting) {
												drawAndUploadHanhDong(g2, jumpLeft);
											}
										}
									} else if (getDirector() == DIR_RIGHT) {
										Rectangle rect = new Rectangle((int) (getX() + getSpeedX()), (int) getY(),
												(int) getWidth(), (int) getHeight());
										if (getGame().getAction().getActionKeyForMegamanX().isPressRight()
												&& getGame().getPhysicalMap().vaChamBenPhai(rect) != null) {
											setSpeedY(1);
											setWallCling(true);
											if (isShooting) {
												wallClingRight.upload(System.nanoTime());
												wallClingAttackRight.setCurrentFrame(wallClingRight.getCurrentFrame());
												drawHanhDongLeft(g2, wallClingAttackRight);
											} else if (!isShooting) {
												drawAndUploadHanhDong(g2, wallClingRight);
											}
											setIsJumping(false);
										} else {
											if (isShooting) {
												jumpRight.upload(System.nanoTime());
												jumpAttackRight.setCurrentFrame(jumpRight.getCurrentFrame());
												drawHanhDongRight(g2, jumpAttackRight);
											} else if (!isShooting) {
												drawAndUploadHanhDong(g2, jumpRight);
											}
										}
									}
								}
							} else if (getSpeedY() != 0) {
								if (getDirector() == DIR_LEFT) {
									Rectangle rect = new Rectangle((int) (getX() + getSpeedX()), (int) getY(),
											(int) getWidth(), (int) getHeight());
									if (getGame().getAction().getActionKeyForMegamanX().isPressLeft()
											&& getGame().getPhysicalMap().vaChamBenTrai(rect) != null) {
										setSpeedY(1);
										setWallCling(true);
										if (isShooting) {
											wallClingLeft.upload(System.nanoTime());
											wallClingAttackLeft.setCurrentFrame(wallClingLeft.getCurrentFrame());
											drawHanhDongRight(g2, wallClingAttackLeft);
										} else if (!isShooting) {
											drawAndUploadHanhDong(g2, wallClingLeft);
										}
									} else if (getGame().getPhysicalMap().vaChamBenTrai(rect) == null) {
										if (isShooting) {
											jumpAttackLeft.setCurrentFrame(jumpAttackLeft.getListImage().size() - 1);
											drawAndUploadHanhDong(g2, jumpAttackLeft);
										} else {
											jumpLeft.setCurrentFrame(jumpLeft.getListImage().size() - 1);
											drawAndUploadHanhDong(g2, jumpLeft);
										}
									}
								} else if (getDirector() == DIR_RIGHT) {
									Rectangle rect = new Rectangle((int) (getX() + getSpeedX()), (int) getY(),
											(int) getWidth(), (int) getHeight());
									if (getGame().getAction().getActionKeyForMegamanX().isPressRight()
											&& getGame().getPhysicalMap().vaChamBenPhai(rect) != null) {
										setSpeedY(1);
										setWallCling(true);
										if (isShooting) {
											wallClingRight.upload(System.nanoTime());
											wallClingAttackRight.setCurrentFrame(wallClingRight.getCurrentFrame());
											drawHanhDongLeft(g2, wallClingAttackRight);
										} else if (!isShooting) {
											drawAndUploadHanhDong(g2, wallClingRight);
										}
									} else if (getGame().getPhysicalMap().vaChamBenTrai(rect) == null) {
										if (isShooting) {
											jumpAttackLeft.setCurrentFrame(jumpAttackRight.getListImage().size() - 1);
											drawAndUploadHanhDong(g2, jumpAttackRight);
										} else {
											jumpRight.setCurrentFrame(jumpRight.getListImage().size() - 1);
											drawAndUploadHanhDong(g2, jumpRight);
										}
									}
								}
							}
						}
					} else if (getStage() == BEHURT) {
						if (getDirector() == DIR_LEFT) {
							drawAndUploadHanhDong(g2, behurtLeft);
							if (behurtLeft.isLastFrame()) {
								setStage(NOBEHURT);
								behurtLeft.reset();
							}
						} else if (getDirector() == DIR_RIGHT) {
							drawAndUploadHanhDong(g2, behurtRight);
							if (behurtRight.isLastFrame()) {
								setStage(NOBEHURT);
								behurtRight.reset();
							}
						}
					}
				}

				effort.drawNapDan(g2);

				effort.drawListDraw1Lan(g2);
			} else {
				if (getDirector() == DIR_LEFT) {
					behurtLeft.setCurrentFrame(1);
					behurtLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				} else if (getDirector() == DIR_RIGHT) {
					behurtRight.setCurrentFrame(1);
					behurtRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
				}
			}
		}
	}

	@Override
	public void attack() {
		if (!isNapDan()) {
			setNapDan(true);
			timeBeginNapDan = System.nanoTime();
		}
	}

	@Override
	public void stopAttack() {
		if (isNapDan) {
			long dataTime = System.nanoTime() - timeBeginNapDan;
			if (dataTime > 50000000 && dataTime < 400000000) {
				effort.addStartBullet(1);

				setShooting(true);
				if (getSpeedY() == 0)
					setWallCling(false);
				lastTimeShooting = System.nanoTime();
				BasicBullet bullet = new BasicBullet(getX(), getY(), getGame());
				if (getDirector() == DIR_RIGHT) {
					if (isWallCling) {
						bullet.setX(bullet.getX() - 56);
						bullet.setY(bullet.getY() + 22);
					} else if (!isWallCling) {
						bullet.setX(bullet.getX() + getWidth());
					}
				} else if (getDirector() == DIR_LEFT) {
					if (isWallCling) {
						bullet.setX(bullet.getX() + getWidth() + 22);
						bullet.setY(bullet.getY() + 22);
					} else {
						bullet.setX(bullet.getX() - 10);
					}
				}
				if (getSpeedX() != 0) {
					if (getSpeedX() > 0)
						bullet.setX(bullet.getX() + 20);
					else if (getSpeedX() < 0)
						bullet.setX(bullet.getX() - 20);
				}
				if (getIsDicking()) {
					bullet.setY(bullet.getY() + 28);
					if (getDirector() == DIR_LEFT)
						bullet.setX(bullet.getX() - 20);
					else if (getDirector() == DIR_RIGHT)
						bullet.setX(bullet.getX() + 20);
				}
				if (isLeoCauThang()) {
					bullet.setY(bullet.getY() + 8);
					if (getDirector() == DIR_LEFT) {
						bullet.setX(bullet.getX() - getWidth() + 15);
					}
				}
				bullet.setY(bullet.getY() + 22);
				getGame().getListBullet().getListObject().add(bullet);
				setNapDan(false);
				dungBanLeft.reset();
				dungBanRight.reset();
				attackCauThangLeft.reset();
				attackCauThangRight.reset();

				ManagerSound.getInstance().getListSound().get("danLv12").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("danLv12");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			} else if (dataTime > 200000000 && dataTime < 1200000000) {
				effort.addStartBullet(2);

				setShooting(true);
				if (getSpeedY() == 0)
					setWallCling(false);
				lastTimeShooting = System.nanoTime();
				RockManLv2Bullet bullet = new RockManLv2Bullet(getX(), getY() + 10, getGame());
				if (getDirector() == DIR_RIGHT) {
					if (isWallCling) {
						bullet.setX(bullet.getX() - 56);
						bullet.setY(bullet.getY() + 22);
					} else if (!isWallCling) {
						bullet.setX(bullet.getX() + getWidth());
					}
				} else if (getDirector() == DIR_LEFT) {
					if (isWallCling) {
						bullet.setX(bullet.getX() + getWidth() + 22);
						bullet.setY(bullet.getY() + 22);
					} else {
						bullet.setX(getX() - 20);
					}
				}
				if (getSpeedX() != 0) {
					if (getSpeedX() > 0)
						bullet.setX(bullet.getX() + 20);
					else if (getSpeedX() < 0)
						bullet.setX(bullet.getX() - 20);
				}
				if (getIsDicking()) {
					bullet.setY(bullet.getY() + 28);
					if (getDirector() == DIR_LEFT)
						bullet.setX(bullet.getX() - 20);
					else if (getDirector() == DIR_RIGHT)
						bullet.setX(bullet.getX() + 20);
				}
				if (isLeoCauThang()) {
					bullet.setY(bullet.getY() + 8);
				}
				getGame().getListBullet().getListObject().add(bullet);
				setNapDan(false);
				dungBanLeft.reset();
				dungBanRight.reset();
				attackCauThangLeft.reset();
				attackCauThangRight.reset();
				ManagerSound.getInstance().getListSound().get("danLv12").start();
				ManagerSound.getInstance().getListSound().get("danLv12").setFramePosition(0);
			} else if (dataTime > 1200000000 && !effort.isReadyForBulletLv4()) {
				effort.addStartBullet(3);

				setShooting(true);
				if (getSpeedY() == 0)
					setWallCling(false);
				lastTimeShooting = System.nanoTime();
				RockManLv3Bullet bullet = new RockManLv3Bullet(getX(), getY(), getGame());
				if (getDirector() == DIR_RIGHT) {
					if (isWallCling) {
						bullet.setX(bullet.getX() - 80);
						bullet.setY(bullet.getY() + 22);
					} else if (!isWallCling) {
						bullet.setX(bullet.getX() + getWidth());
					}
				} else if (getDirector() == DIR_LEFT) {
					if (isWallCling) {
						bullet.setX(bullet.getX() + getWidth() + 22);
						bullet.setY(bullet.getY() + 22);
					} else {
						bullet.setX(getX() - 70);
					}
				}
				if (getSpeedX() != 0) {
					if (getSpeedX() > 0)
						bullet.setX(bullet.getX() + 20);
					else if (getSpeedX() < 0)
						bullet.setX(bullet.getX() - 20);
				}
				if (getIsDicking()) {
					bullet.setY(bullet.getY() + 28);
					if (getDirector() == DIR_LEFT)
						bullet.setX(bullet.getX() - 20);
					else if (getDirector() == DIR_RIGHT)
						bullet.setX(bullet.getX() + 20);
				}
				if (isLeoCauThang()) {
					bullet.setY(bullet.getY() + 8);
				}
				getGame().getListBullet().getListObject().add(bullet);
				setNapDan(false);
				dungBanLeft.reset();
				dungBanRight.reset();
				attackCauThangLeft.reset();
				attackCauThangRight.reset();
				if (getSpeedX() == 0 && getSpeedY() == 0)
					setBanDanLvCao(true);
				ManagerSound.getInstance().getListSound().get("danLv3").start();
				ManagerSound.getInstance().getListSound().get("danLv3").setFramePosition(0);
			} else if (effort.isReadyForBulletLv4()) {
				effort.addStartBullet(4);

				RockManLv4Bullet bullet = new RockManLv4Bullet(getX(), getY() - 30, getGame());
				if (getDirector() == DIR_RIGHT) {
					if (isWallCling) {
						bullet.setX(bullet.getX() - 100);
						bullet.setY(bullet.getY() + 12);
					} else if (!isWallCling) {
						bullet.setX(bullet.getX() + getWidth());
					}
				} else if (getDirector() == DIR_LEFT) {
					if (isWallCling) {
						bullet.setX(bullet.getX() + getWidth() + 22);
						bullet.setY(bullet.getY() + 12);
					} else {
						bullet.setX(getX() - 70);
					}
				}
				if (getSpeedX() != 0) {
					if (getSpeedX() > 0)
						bullet.setX(bullet.getX() + 20);
					else if (getSpeedX() < 0)
						bullet.setX(bullet.getX() - 20);
				}
				if (getIsDicking()) {
					bullet.setY(bullet.getY() + 5);
					if (getDirector() == DIR_LEFT)
						bullet.setX(bullet.getX() - 20);
					else if (getDirector() == DIR_RIGHT)
						bullet.setX(bullet.getX() + 20);
				}
				if (isLeoCauThang()) {
					bullet.setY(bullet.getY() + 8);
				}
				getGame().getListBullet().getListObject().add(bullet);

				setShooting(true);
				if (getSpeedY() == 0)
					setWallCling(false);
				lastTimeShooting = System.nanoTime();
				setNapDan(false);
				dungBanLeft.reset();
				dungBanRight.reset();
				attackCauThangLeft.reset();
				attackCauThangRight.reset();
				if (getSpeedX() == 0 && getSpeedY() == 0)
					setBanDanLvCao(true);
				ManagerSound.getInstance().getListSound().get("danLv4").start();
				ManagerSound.getInstance().getListSound().get("danLv4").setFramePosition(0);
			}
			effort.resetBulletLv4();
		}
	}

	@Override
	public void run() {

		if (isLeoCauThang()) {
			if (getGame().getAction().getActionKeyForMegamanX().isPressLeft()) {
				setDirector(DIR_LEFT);
			} else if (getGame().getAction().getActionKeyForMegamanX().isPressRight()) {
				setDirector(DIR_RIGHT);
			}
		}

		if (!isWallKick) {
			if (getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
				if (getX() > getGame().getCamera().getLimitX()) {
					setSpeedX(-2);
				}
			} else if (getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
				if (getX() + getWidth() < getGame().getCamera().getLimitX2() + GameFrame.GAME_WIDTH - 20) {
					setSpeedX(2);
				}
			}
		} else {
			if (getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
				if (getX() > getGame().getCamera().getLimitX()) {
					setSpeedX(-3);
				}
			} else if (getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
				if (getX() + getWidth() < getGame().getCamera().getLimitX2() + GameFrame.GAME_WIDTH - 20) {
					setSpeedX(3);
				}
			}
		}
	}

	@Override
	public void stopRun() {
		setSpeedX(0);
		runLeft.reset();
		runRight.reset();
		runLeft.getListIgnoreImage().set(0, false);
		runLeft.getListIgnoreImage().set(1, false);
		runRight.getListIgnoreImage().set(0, false);
		runRight.getListIgnoreImage().set(1, false);
		wallClingLeft.reset();
		wallClingRight.reset();
	}

	@Override
	public void dick() {
		Stairs cauThang = getGame().getListDungCu().isVaChamCauThang(getRectangleXuLiVaCham());
		if (isLeoCauThang()) {
			setY(getY() + 5);
			if (cauThang != null) {
				if ((getY() + getHeight()) - (cauThang.getY() + cauThang.getHeight()) > 50) {
					setLeoCauThang(false);
				}
			}
		} else if (cauThang != null && !isLeoCauThang()) {
			setLeoCauThang(true);
			setSpeedX(0);
			setSpeedY(0);
			setX(cauThang.getX() + 5);
			setY(getY() + 60);
		} else {
			setDicking(true);
			setSpeedX(0);
		}
	}

	public void up() {
		Stairs cauThang = getGame().getListDungCu().isVaChamCauThang(getRectangleXuLiVaCham());
		if (isLeoCauThang() && !isEndCauThang) {
			setY(getY() - 5);
			if (cauThang.getY() - getY() > 30) {
				isEndCauThang = true;
			}
		} else if (!isLeoCauThang() && cauThang != null) {
			if ((getY() + getWidth()) - cauThang.getY() > 30) {
				setLeoCauThang(true);
				setSpeedX(0);
				setSpeedY(0);
				setX(cauThang.getX() + 5);
				setY(getY());
			}
		}
	}

	@Override
	public void stanUp() {
		if (!isLeoCauThang()) {
			setDicking(false);
			dickLeft.reset();
			dickRight.reset();
		}
	}

	@Override
	public void jump() {

		if (isLeoCauThang()) {
			setLeoCauThang(false);
		}

		if (!getIsJumping()) {
			if (isWallCling) {
				if (getSpeedY() != 0) {
					setWallKick(true);
					int futureY = checkChamTranNha(80, getRectangleXuLiVaCham());
					if (getDirector() == DIR_LEFT) {
						effort.addRemoveWallKick();
						setX(getX() + 30);
					}
					if (getDirector() == DIR_RIGHT) {
						effort.addRemoveWallKick();
						setX(getX() - 30);
					}
					setY(futureY);
					setSpeedY(0);
				} else {
					int futureY = checkChamTranNha(5, getRectangleXuLiVaCham());
					setY(futureY);
					setSpeedY(-5);
				}
				ManagerSound.getInstance().getListSound().get("wallCling").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("wallCling");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			} else if (getSpeedY() == 0) {
				ManagerSound.getInstance().getListSound().get("rmjump").start();
				ManagerSound.getInstance().getListSound().get("rmjump").setFramePosition(0);
				int futureY = checkChamTranNha(5, getRectangleXuLiVaCham());
				setY(futureY);
				setSpeedY(-5);
			}
			setIsJumping(true);
			setWallCling(false);
			wallClingLeft.reset();
			wallClingRight.reset();
			wallKickLeft.reset();
			wallKickRight.reset();
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
	public void tiepDat() {
		setIsJumping(false);
		setWallCling(false);
		setWallKick(false);
		jumpLeft.reset();
		jumpRight.reset();
		wallClingLeft.reset();
		wallClingRight.reset();
		wallKickLeft.reset();
		wallClingRight.reset();
		if (isRoiKhoiMatDat) {
			isRoiKhoiMatDat = false;
			ManagerSound.getInstance().getListSound().get("tiepDat").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("tiepDat");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		if (!getIsDicking()) {
			Rectangle rect = getRectangleSizeObject();
			Rectangle r = new Rectangle(rect.x + 10, rect.y, rect.width - 20, rect.height);
			return r;
		} else {
			Rectangle rect = new Rectangle((int) getX(), (int) (getY() + 30), (int) getWidth(),
					(int) (getHeight() - 30));
			return rect;
		}
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		g2.setColor(Color.yellow);
		Rectangle rect = getRectangleXuLiVaCham();
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	@Override
	public void getToDeath() {

		if (getStage() != DEATH) {
			int count = 0;
			while (count < 30) {
				if (System.nanoTime() % 2 == 0) {
					WhenRockManDeath whenDeath = new WhenRockManDeath(getX() - 200 + rd.nextInt(400),
							getY() - 200 + rd.nextInt(400), getGame());
					getGame().getListWhenDeath().addWhenDeath(whenDeath);
				} else {
					WhenRockManDeath whenDeath = new WhenRockManDeath(getX() - 200 - rd.nextInt(400),
							getY() - 200 - rd.nextInt(400), getGame());
					getGame().getListWhenDeath().addWhenDeath(whenDeath);
				}
				count++;
			}
			ManagerSound.getInstance().getListSound().get("rmdead").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("rmdead");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
			getGame().getController().getModel().setTimeStart(System.currentTimeMillis());
			getGame().setSTAGE(GameWorldMegamanX.ENDGAME);
		}

	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public boolean isNapDan() {
		return isNapDan;
	}

	public void setNapDan(boolean isNapDan) {
		this.isNapDan = isNapDan;
	}

	public boolean isWallCling() {
		return isWallCling;
	}

	public void setWallCling(boolean isWallCling) {
		this.isWallCling = isWallCling;
	}

	public boolean isWallKick() {
		return isWallKick;
	}

	public void setWallKick(boolean isWallKick) {
		this.isWallKick = isWallKick;
	}

	public boolean isBanDanLvCao() {
		return isBanDanLvCao;
	}

	public void setBanDanLvCao(boolean isBanDanLvCao) {
		this.isBanDanLvCao = isBanDanLvCao;
	}

	public long getLastTimeShooting() {
		return lastTimeShooting;
	}

	public void setLastTimeShooting(long lastTimeShooting) {
		this.lastTimeShooting = lastTimeShooting;
	}

	public long getTimeForShooting() {
		return timeForShooting;
	}

	public void setTimeForShooting(long timeForShooting) {
		this.timeForShooting = timeForShooting;
	}

	public long getTimeBeginNapDan() {
		return timeBeginNapDan;
	}

	public void setTimeBeginNapDan(long timeBeginNapDan) {
		this.timeBeginNapDan = timeBeginNapDan;
	}

	public Animation getDungImLeft() {
		return dungImLeft;
	}

	public void setDungImLeft(Animation dungImLeft) {
		this.dungImLeft = dungImLeft;
	}

	public Animation getDungImRight() {
		return dungImRight;
	}

	public void setDungImRight(Animation dungImRight) {
		this.dungImRight = dungImRight;
	}

	public Animation getDungBanLeft() {
		return dungBanLeft;
	}

	public void setDungBanLeft(Animation dungBanLeft) {
		this.dungBanLeft = dungBanLeft;
	}

	public Animation getDungBanRight() {
		return dungBanRight;
	}

	public void setDungBanRight(Animation dungBanRight) {
		this.dungBanRight = dungBanRight;
	}

	public Animation getDungBanDanLvCaoLeft() {
		return dungBanDanLvCaoLeft;
	}

	public void setDungBanDanLvCaoLeft(Animation dungBanDanLvCaoLeft) {
		this.dungBanDanLvCaoLeft = dungBanDanLvCaoLeft;
	}

	public Animation getDungBanDanLvCaoRight() {
		return dungBanDanLvCaoRight;
	}

	public void setDungBanDanLvCaoRight(Animation dungBanDanLvCaoRight) {
		this.dungBanDanLvCaoRight = dungBanDanLvCaoRight;
	}

	public Animation getRunLeft() {
		return runLeft;
	}

	public void setRunLeft(Animation runLeft) {
		this.runLeft = runLeft;
	}

	public Animation getRunRight() {
		return runRight;
	}

	public void setRunRight(Animation runRight) {
		this.runRight = runRight;
	}

	public Animation getRunAttackLeft() {
		return runAttackLeft;
	}

	public void setRunAttackLeft(Animation runAttackLeft) {
		this.runAttackLeft = runAttackLeft;
	}

	public Animation getRunAttackRight() {
		return runAttackRight;
	}

	public void setRunAttackRight(Animation runAttackRight) {
		this.runAttackRight = runAttackRight;
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

	public Animation getJumpAttackLeft() {
		return jumpAttackLeft;
	}

	public void setJumpAttackLeft(Animation jumpAttackLeft) {
		this.jumpAttackLeft = jumpAttackLeft;
	}

	public Animation getJumpAttackRight() {
		return jumpAttackRight;
	}

	public void setJumpAttackRight(Animation jumpAttackRight) {
		this.jumpAttackRight = jumpAttackRight;
	}

	public Animation getDickLeft() {
		return dickLeft;
	}

	public void setDickLeft(Animation dickLeft) {
		this.dickLeft = dickLeft;
	}

	public Animation getDickRight() {
		return dickRight;
	}

	public void setDickRight(Animation dickRight) {
		this.dickRight = dickRight;
	}

	public Animation getDickAttackLeft() {
		return dickAttackLeft;
	}

	public void setDickAttackLeft(Animation dickAttackLeft) {
		this.dickAttackLeft = dickAttackLeft;
	}

	public Animation getDickAttackRight() {
		return dickAttackRight;
	}

	public void setDickAttackRight(Animation dickAttackRight) {
		this.dickAttackRight = dickAttackRight;
	}

	public Animation getDickAttackBulletHighLeft() {
		return dickAttackBulletHighLeft;
	}

	public void setDickAttackBulletHighLeft(Animation dickAttackBulletHighLeft) {
		this.dickAttackBulletHighLeft = dickAttackBulletHighLeft;
	}

	public Animation getDickAttackBulletHighRight() {
		return dickAttackBulletHighRight;
	}

	public void setDickAttackBulletHighRight(Animation dickAttackBulletHighRight) {
		this.dickAttackBulletHighRight = dickAttackBulletHighRight;
	}

	public Animation getTiepDatLeft() {
		return tiepDatLeft;
	}

	public void setTiepDatLeft(Animation tiepDatLeft) {
		this.tiepDatLeft = tiepDatLeft;
	}

	public Animation getTiepDatRight() {
		return tiepDatRight;
	}

	public void setTiepDatRight(Animation tiepDatRight) {
		this.tiepDatRight = tiepDatRight;
	}

	public Animation getTiepDatAttackRight() {
		return tiepDatAttackRight;
	}

	public void setTiepDatAttackRight(Animation tiepDatAttackRight) {
		this.tiepDatAttackRight = tiepDatAttackRight;
	}

	public Animation getTiepDatAttackLeft() {
		return tiepDatAttackLeft;
	}

	public void setTiepDatAttackLeft(Animation tiepDatAttackLeft) {
		this.tiepDatAttackLeft = tiepDatAttackLeft;
	}

	public Animation getWallClingLeft() {
		return wallClingLeft;
	}

	public void setWallClingLeft(Animation wallClingLeft) {
		this.wallClingLeft = wallClingLeft;
	}

	public Animation getWallClingRight() {
		return wallClingRight;
	}

	public void setWallClingRight(Animation wallClingRight) {
		this.wallClingRight = wallClingRight;
	}

	public Animation getWallClingAttackLeft() {
		return wallClingAttackLeft;
	}

	public void setWallClingAttackLeft(Animation wallClingAttackLeft) {
		this.wallClingAttackLeft = wallClingAttackLeft;
	}

	public Animation getWallClingAttackRight() {
		return wallClingAttackRight;
	}

	public void setWallClingAttackRight(Animation wallClingAttackRight) {
		this.wallClingAttackRight = wallClingAttackRight;
	}

	public Animation getWallKickLeft() {
		return wallKickLeft;
	}

	public void setWallKickLeft(Animation wallKickLeft) {
		this.wallKickLeft = wallKickLeft;
	}

	public Animation getWallKickRight() {
		return wallKickRight;
	}

	public void setWallKickRight(Animation wallKickRight) {
		this.wallKickRight = wallKickRight;
	}

	public Animation getWallKickAttackLeft() {
		return wallKickAttackLeft;
	}

	public void setWallKickAttackLeft(Animation wallKickAttackLeft) {
		this.wallKickAttackLeft = wallKickAttackLeft;
	}

	public Animation getWallKickAttackRight() {
		return wallKickAttackRight;
	}

	public void setWallKickAttackRight(Animation wallKickAttackRight) {
		this.wallKickAttackRight = wallKickAttackRight;
	}

	public Animation getBehurtLeft() {
		return behurtLeft;
	}

	public void setBehurtLeft(Animation behurtLeft) {
		this.behurtLeft = behurtLeft;
	}

	public Animation getBehurtRight() {
		return behurtRight;
	}

	public void setBehurtRight(Animation behurtRight) {
		this.behurtRight = behurtRight;
	}

	public Animation getStartCauThang() {
		return startCauThang;
	}

	public void setStartCauThang(Animation startCauThang) {
		this.startCauThang = startCauThang;
	}

	public Animation getEndCauThangLeft() {
		return endCauThangLeft;
	}

	public void setEndCauThangLeft(Animation endCauThangLeft) {
		this.endCauThangLeft = endCauThangLeft;
	}

	public Animation getEndCauThangRight() {
		return endCauThangRight;
	}

	public void setEndCauThangRight(Animation endCauThangRight) {
		this.endCauThangRight = endCauThangRight;
	}

	public Animation getAttackCauThangLeft() {
		return attackCauThangLeft;
	}

	public void setAttackCauThangLeft(Animation attackCauThangLeft) {
		this.attackCauThangLeft = attackCauThangLeft;
	}

	public Animation getAttackCauThangRight() {
		return attackCauThangRight;
	}

	public void setAttackCauThangRight(Animation attackCauThangRight) {
		this.attackCauThangRight = attackCauThangRight;
	}

	public Animation getLeoCauThangLeft() {
		return leoCauThangLeft;
	}

	public void setLeoCauThangLeft(Animation leoCauThangLeft) {
		this.leoCauThangLeft = leoCauThangLeft;
	}

	public Animation getLeoCauThangRight() {
		return leoCauThangRight;
	}

	public void setLeoCauThangRight(Animation leoCauThangRight) {
		this.leoCauThangRight = leoCauThangRight;
	}

	public Animation getAnimeCauThangCurrently() {
		return animeCauThangCurrently;
	}

	public void setAnimeCauThangCurrently(Animation animeCauThangCurrently) {
		this.animeCauThangCurrently = animeCauThangCurrently;
	}

	public boolean isEndCauThang() {
		return isEndCauThang;
	}

	public void setEndCauThang(boolean isEndCauThang) {
		this.isEndCauThang = isEndCauThang;
	}

	public RockManEffort getEffort() {
		return effort;
	}

	public void setEffort(RockManEffort effort) {
		this.effort = effort;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

	public boolean isChamVucSau() {
		return isChamVucSau;
	}

	public void setChamVucSau(boolean isChamVucSau) {
		this.isChamVucSau = isChamVucSau;
	}

	public boolean isRoiKhoiMatDat() {
		return isRoiKhoiMatDat;
	}

	public void setRoiKhoiMatDat(boolean isRoiKhoiMatDat) {
		this.isRoiKhoiMatDat = isRoiKhoiMatDat;
	}

	public Animation getEndgame() {
		return endgame;
	}

	public void setEndgame(Animation endgame) {
		this.endgame = endgame;
	}

	public boolean isEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

}
