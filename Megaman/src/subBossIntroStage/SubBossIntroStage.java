package subBossIntroStage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import actionForHuman.Attack;
import actionForHuman.Grounding;
import actionForHuman.Jump;
import bossIntroStage.WhenBossDeath;
import efforOpacity.DrawRectangleOpacity;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.HunmanMegamanX;
import sound.ManagerSound;

public class SubBossIntroStage extends HunmanMegamanX implements Jump, Attack, Grounding {

	private boolean isPlayTutorial;
	private boolean isXuatHien;

	private Animation xuatHien;
	private Animation idleLeft, idleRight;
	private Animation attackLeft, attackRight;
	private Animation jumpLeft, jumpRight;
	private Animation jumpAttackLeft, jumpAttackRight;
	private Animation deadLeft, deadRight;

	private HashMap<String, Integer> listTimeForAction;
	private String[] listAction;
	private int currentAction;
	private long timeStartAction;

	private boolean isShooting;
	private long timeStartShooting;

	private boolean isReady;

	private WhenBossDeath whenDeath;
	private DrawRectangleOpacity drawRect;
	private boolean isDraw;
	private boolean isDeath;

	private boolean isRoiKhoiMatDat;

	public SubBossIntroStage(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f, 74, 90, 250, 5);

		xuatHien = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageXuatHien"));
		idleLeft = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageIdle"));
		idleRight = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageIdle"));
		idleRight.daoNguoc();
		attackLeft = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageAttack"));
		attackLeft.setIsRepeat(false);
		attackRight = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageAttack"));
		attackRight.daoNguoc();
		attackRight.setIsRepeat(false);
		jumpLeft = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageJump"));
		jumpLeft.setIsRepeat(false);
		jumpRight = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageJump"));
		jumpRight.daoNguoc();
		jumpRight.setIsRepeat(false);
		jumpAttackLeft = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageJumpAttack"));
		jumpAttackLeft.setIsRepeat(false);
		jumpAttackRight = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageJumpAttack"));
		jumpAttackRight.daoNguoc();
		jumpAttackRight.setIsRepeat(false);
		deadLeft = new Animation(getGame().getInputData().getListAnimation().get("subBossDeath"));
		deadRight = new Animation(getGame().getInputData().getListAnimation().get("subBossDeath"));
		deadRight.daoNguoc();

		setDirector(DIR_LEFT);
		setTeam(ENEMI_TEAM);

		isPlayTutorial = true;
		isXuatHien = false;

		whenDeath = new WhenBossDeath(getX(), getY(), getGame());
		drawRect = new DrawRectangleOpacity(Color.WHITE);

		listAction = new String[5];
		listAction[0] = "ATTACK";
		listAction[1] = "JUMP";
		listAction[2] = "NONE";
		listAction[3] = "JUMPATTACK";
		listTimeForAction = new HashMap<String, Integer>();
		listTimeForAction.put("ATTACK", 3000);
		listTimeForAction.put("JUMP", 2000);
		listTimeForAction.put("NONE", 1000);
		listTimeForAction.put("JUMPATTACK", 3000);
		currentAction = 0;

	}

	@Override
	public void jump() {
		if (!getIsJumping()) {
			setY(getY() - 5);
			setSpeedY(-5);

			if (getDirector() == DIR_LEFT) {
				setSpeedX(-3);
			} else {
				setSpeedX(3);
			}

			setJumping(true);
		}
	}

	@Override
	public void tiepDat() {

		if (isRoiKhoiMatDat) {
			isRoiKhoiMatDat = false;
			ManagerSound.getInstance().getListSound().get("tiepDat").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("tiepDat");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

		jumpLeft.reset();
		jumpRight.reset();
		jumpAttackLeft.reset();
		jumpAttackRight.reset();

		setSpeedX(0);

		setIsJumping(false);
	}

	@Override
	public void attack() {
		if (!isShooting) {

			attackLeft.reset();
			attackRight.reset();
			jumpAttackLeft.reset();
			jumpAttackRight.reset();

			SubBossIntroStageBullet bullet = new SubBossIntroStageBullet(getX(), getY(), getGame());

			bullet.setY(bullet.getY() + 20);
			if (getDirector() == DIR_LEFT) {
				bullet.setX(bullet.getX() - 10);
			} else {
				if (getIsJumping()) {
					bullet.setX(bullet.getX() + getWidth() - 20);
				} else {
					bullet.setX(bullet.getX() + getWidth() + 10);
				}
			}

			getGame().getListBullet().addObject(bullet);
			ManagerSound.getInstance().getListSound().get("danLv12").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("danLv12");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
			isShooting = true;
			timeStartShooting = System.nanoTime();
		}
	}

	@Override
	public void upload() {
		if (!isDeath) {
			if (!isOutCamera() && isPlayTutorial && getGame().getSTAGE() == GameWorldMegamanX.ATTACKBOSS) {
				getGame().setSTAGE(GameWorldMegamanX.PLAYTUTORIAL);
			}

			if (getX() > getGame().getRockMan().getX()) {
				setDirector(DIR_LEFT);
			} else {
				setDirector(DIR_RIGHT);
			}

			if (!isPlayTutorial) {
				super.upload();
			}

			if (isReady) {

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

				switch (currentAction) {
				case 0:
					attack();
					if (System.currentTimeMillis() - timeStartAction > listTimeForAction.get(listAction[0])) {
						timeStartAction = System.currentTimeMillis();
						currentAction++;
					}

					break;
				case 1:
					jump();
					if (System.currentTimeMillis() - timeStartAction > listTimeForAction.get(listAction[0])) {
						timeStartAction = System.currentTimeMillis();
						currentAction++;
					}

					break;
				case 2:

					if (System.currentTimeMillis() - timeStartAction > listTimeForAction.get(listAction[0])) {
						timeStartAction = System.currentTimeMillis();
						currentAction++;
					}

					break;
				case 3:
					jump();
					attack();
					if (System.currentTimeMillis() - timeStartAction > listTimeForAction.get(listAction[0])) {
						timeStartAction = System.currentTimeMillis();
						currentAction = 0;
					}

					break;

				default:
					break;
				}
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 5, rect.y + 10, rect.width - 10, rect.height - 10);
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
		setTeam(LEGUE_TEAM);
	}

	@Override
	public void draw(Graphics2D g2) {

		if (isDraw && drawRect.getStage() == DrawRectangleOpacity.UP) {
			if (getDirector() == DIR_LEFT) {
				deadLeft.upload(System.nanoTime());
				deadLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else if (getDirector() == DIR_RIGHT) {
				deadRight.upload(System.nanoTime());
				deadRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		}

		if (isDeath) {
			if (!isDraw) {
				isDraw = true;
			}

			if (drawRect.getStage() == DrawRectangleOpacity.UP) {
				whenDeath.upload();
			} else {
				setTeam(LEGUE_TEAM);
			}

			drawRect.upload();
			drawRect.draw(g2);
			if (drawRect.getStage() == DrawRectangleOpacity.END) {
				this.setStage(DEATH);
				getGame().setSTAGE(GameWorldMegamanX.NORMAL);
			}
		}

		if (!isDraw) {
			if (!isPlayTutorial) {
				if (isXuatHien && getSpeedY() != 0 && !getIsJumping()) {
					xuatHien.upload(System.nanoTime());
					xuatHien.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
					if (xuatHien.isLastFrame()) {
						isXuatHien = true;
					}
				}

				if (getSpeedY() == 0 && getSpeedX() == 0) {
					if (!isShooting) {
						if (getDirector() == DIR_LEFT) {
							idleLeft.upload(System.nanoTime());
							idleLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (getDirector() == DIR_RIGHT) {
							idleRight.upload(System.nanoTime());
							idleRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						}
					} else if (isShooting) {
						if (getDirector() == DIR_LEFT) {
							attackLeft.upload(System.nanoTime());
							if (attackLeft.getCurrentFrame() == 0) {
								attackLeft.draw(g2, (int) (getX() - 10 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (attackLeft.getCurrentFrame() == 1) {
								attackLeft.draw(g2, (int) (getX() - 26 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							} else if (attackLeft.getCurrentFrame() == 2) {
								attackLeft.draw(g2, (int) (getX() - 6 - getGame().getCamera().getX()),
										(int) (getY() - getGame().getCamera().getY()));
							}
						} else if (getDirector() == DIR_RIGHT) {
							attackRight.upload(System.nanoTime());
							attackRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						}
					}
				} else if (getIsJumping()) {
					if (!isShooting) {
						if (getDirector() == DIR_LEFT) {
							jumpLeft.upload(System.nanoTime());
							jumpLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (getDirector() == DIR_RIGHT) {
							jumpRight.upload(System.nanoTime());
							jumpRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						}
					} else {
						if (getDirector() == DIR_LEFT) {
							jumpAttackLeft.getListIgnoreImage().set(0, true);
							jumpAttackLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						} else if (getDirector() == DIR_RIGHT) {
							jumpAttackRight.getListIgnoreImage().set(0, true);
							jumpAttackRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - getGame().getCamera().getY()));
						}
					}
				}
			}
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

	public Animation getXuatHien() {
		return xuatHien;
	}

	public void setXuatHien(Animation xuatHien) {
		this.xuatHien = xuatHien;
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

}
