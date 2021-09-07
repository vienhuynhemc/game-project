package robotBunkerShot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import actionForHuman.Attack;
import actionForHuman.Grounding;
import actionForHuman.Jump;
import backGroundMap.AnimationBackGroundMap;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import item.LargeHealItem;
import item.SmallHealItem;
import particulerObject.HunmanMegamanX;
import sound.ManagerSound;

public class RobotBunkerShot extends HunmanMegamanX implements Attack, Grounding, Jump {

	private Animation dungImLeft, dungImRight;
	private Animation jumpLeft, jumpRight;
	private Animation giuongSungLeft, giuongSungRight;
	private Animation haSungLeft, haSungRight;

	private boolean isShooting;
	private long timeForShooting;
	private long lastTimeShooting;

	private HashMap<String, Integer> listTime;
	private String[] arrayAction;
	private int currentAction;
	private long timeStart;

	private Random rd;

	private boolean isGiuongSung;
	private int currentGiuongSung;

	private boolean isRoiKhoiMatDat;

	private List<AnimationBackGroundMap> listAttack;
	private Animation smoke;

	public RobotBunkerShot(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0.1f, 78, 64, 60, 5);

		dungImLeft = new Animation(getGame().getInputData().getListAnimation().get("robotBanLoCotDungIm"));
		dungImRight = new Animation(getGame().getInputData().getListAnimation().get("robotBanLoCotDungIm"));
		dungImRight.daoNguoc();
		jumpLeft = new Animation(getGame().getInputData().getListAnimation().get("robotBanLoCotJump"));
		jumpLeft.setIsRepeat(false);
		jumpRight = new Animation(getGame().getInputData().getListAnimation().get("robotBanLoCotJump"));
		jumpRight.daoNguoc();
		jumpRight.setIsRepeat(false);
		giuongSungLeft = new Animation(getGame().getInputData().getListAnimation().get("robotBanLoCotGiuongSung"));
		giuongSungRight = new Animation(getGame().getInputData().getListAnimation().get("robotBanLoCotGiuongSung"));
		giuongSungRight.daoNguoc();
		giuongSungLeft.setIsRepeat(false);
		giuongSungRight.setIsRepeat(false);
		haSungLeft = new Animation(getGame().getInputData().getListAnimation().get("robotBanLoCotHaSung"));
		haSungRight = new Animation(getGame().getInputData().getListAnimation().get("robotBanLoCotHaSung"));
		haSungRight.daoNguoc();
		haSungLeft.setIsRepeat(false);
		haSungRight.setIsRepeat(false);

		listAttack = new ArrayList<AnimationBackGroundMap>();
		smoke = new Animation(getGame().getInputData().getListAnimation().get("smoke"));

		setDirector(director);
		setTeam(ENEMI_TEAM);
		setStage(ALIVE);

		setxPrimaral(getX());
		setyPrimaral(getY());
		setDirectorPrimaral(getDirector());
		setBloodPrimaral(getBlood());

		listTime = new HashMap<String, Integer>();
		listTime.put("NONE", 1000);
		listTime.put("JUMP", 10000);
		listTime.put("GIUONGSUNG", 2000);
		listTime.put("ATTACK", 2100);
		listTime.put("HASUNG", 2000);

		arrayAction = new String[5];
		arrayAction[0] = "NONE";
		arrayAction[1] = "JUMP";
		arrayAction[2] = "GIUONGSUNG";
		arrayAction[3] = "ATTACK";
		arrayAction[4] = "HASUNG";

		currentAction = 0;
		timeForShooting = 700000000;

		rd = new Random();
	}

	@Override
	public void upload() {
		super.upload();

		if (timeStart == 0)
			timeStart = System.nanoTime();

		if (getX() > getGame().getRockMan().getX()) {
			setDirector(DIR_LEFT);
		} else {
			setDirector(DIR_RIGHT);
		}

		if (getSpeedY() != 0) {
			isRoiKhoiMatDat = true;
		}

		if (isShooting) {
			if (System.nanoTime() - lastTimeShooting > timeForShooting) {
				setShooting(false);
			}
		} else {
			if (currentAction == 0) {
				if (System.nanoTime() - timeStart > listTime.get("NONE") * 1000000) {
					currentAction++;
					timeStart = System.nanoTime();
				}
			} else if (currentAction == 1) {
				jump();
				if (getSpeedY() == 0) {
					setSpeedX(0);
					currentAction++;
					timeStart = System.nanoTime();
				}
			} else if (currentAction == 2) {
				if ((getX() > getGame().getRockMan().getX() && getX() - getGame().getRockMan().getX() < 500)
						|| (getX() < getGame().getRockMan().getX() && getGame().getRockMan().getX() - getX() < 500)) {
					if (System.nanoTime() - timeStart > listTime.get("GIUONGSUNG") * 1000000) {
						currentAction++;
						timeStart = System.nanoTime();
					}
				} else {
					currentAction++;
					timeStart = System.nanoTime();
				}
			} else if (currentAction == 3) {
				if (isGiuongSung) {
					if (!isShooting) {
						if ((getX() > getGame().getRockMan().getX() && getX() - getGame().getRockMan().getX() < 500)
								|| (getX() < getGame().getRockMan().getX()
										&& getGame().getRockMan().getX() - getX() < 500))
							attack();
					}
					if (System.nanoTime() - timeStart > listTime.get("ATTACK") * 1000000) {
						currentAction++;
						timeStart = System.nanoTime();
					}
				} else {
					currentAction++;
					timeStart = System.nanoTime();
				}
			} else if (currentAction == 4) {
				if (isGiuongSung) {
					if (System.nanoTime() - timeStart > listTime.get("HASUNG") * 1000000) {
						currentAction = 0;
						setIsJumping(false);
						timeStart = System.nanoTime();
					}
				} else {
					currentAction = 0;
					setIsJumping(false);
					timeStart = System.nanoTime();
				}
			}
		}
	}

	public boolean isGiuongSungMax() {
		if (getDirector() == DIR_LEFT) {
			if (getX() - getGame().getRockMan().getX() < 150) {
				return true;
			}
		} else if (getDirector() == DIR_RIGHT) {
			if (getGame().getRockMan().getX() - getX() < 150) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void draw(Graphics2D g2) {

		if (getCurrentAction() == 2) {
			if ((getX() > getGame().getRockMan().getX() && getX() - getGame().getRockMan().getX() < 500)
					|| (getX() < getGame().getRockMan().getX() && getGame().getRockMan().getX() - getX() < 500)) {
				if (getDirector() == DIR_LEFT) {
					giuongSungLeft.upload(System.nanoTime());
					if (giuongSungLeft.getCurrentFrame() == 0) {
						giuongSungLeft.draw(g2, (int) (getX() + 6 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY() - 32));
					} else if (giuongSungLeft.getCurrentFrame() == 1) {
						giuongSungLeft.draw(g2, (int) (getX() + 12 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY() - 32));
					} else if (giuongSungLeft.getCurrentFrame() == 2) {
						giuongSungLeft.draw(g2, (int) (getX() + 22 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY() - 32));
					} else if (giuongSungLeft.getCurrentFrame() == 3) {
						giuongSungLeft.draw(g2, (int) (getX() + 26 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY() - 32));
						setGiuongSung(true);
					}
					if (isGiuongSungMax()) {
						if (giuongSungLeft.isLastFrame()) {
							currentAction++;
							timeStart = System.nanoTime();
							setGiuongSung(true);
							currentGiuongSung = 3;
						}
					} else if (giuongSungLeft.getCurrentFrame() == 2) {
						currentAction++;
						timeStart = System.nanoTime();
						setGiuongSung(true);
						currentGiuongSung = 2;
					}
				} else if (getDirector() == DIR_RIGHT) {
					giuongSungRight.upload(System.nanoTime());
					giuongSungRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY() - 32));
					if (isGiuongSungMax()) {
						if (giuongSungRight.isLastFrame()) {
							currentAction++;
							timeStart = System.nanoTime();
							setGiuongSung(true);
							currentGiuongSung = 3;
						}
					} else if (giuongSungRight.getCurrentFrame() == 2) {
						currentAction++;
						timeStart = System.nanoTime();
						setGiuongSung(true);
						currentGiuongSung = 2;
					}
				}
			}
		} else if (getCurrentAction() == 4 && isGiuongSung) {
			if (getDirector() == DIR_LEFT) {
				if (currentGiuongSung == 2) {
					haSungLeft.setCurrentFrame(1);
					currentGiuongSung = 0;
				}
				haSungLeft.upload(System.nanoTime());
				if (haSungLeft.getCurrentFrame() == 4) {
					haSungLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
					setGiuongSung(false);
				} else {
					if (haSungLeft.getCurrentFrame() == 0) {
						haSungLeft.draw(g2, (int) (getX() + 26 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY() - 32));
					} else if (haSungLeft.getCurrentFrame() == 1) {
						haSungLeft.draw(g2, (int) (getX() + 22 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY() - 32));
					} else if (haSungLeft.getCurrentFrame() == 2) {
						haSungLeft.draw(g2, (int) (getX() + 12 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY() - 32));
					} else if (haSungLeft.getCurrentFrame() == 3) {
						haSungLeft.draw(g2, (int) (getX() + 6 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY() - 32));
						setGiuongSung(true);
					}
				}
			} else if (getDirector() == DIR_RIGHT) {
				if (currentGiuongSung == 2) {
					haSungRight.setCurrentFrame(1);
					currentGiuongSung = 0;
				}
				haSungRight.upload(System.nanoTime());
				if (haSungRight.getCurrentFrame() == 4) {
					haSungRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY()));
					setGiuongSung(false);
				} else {
					haSungRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY() - 32));
				}
			}
		} else if (getSpeedX() == 0 && getSpeedY() == 0) {
			if (isGiuongSung) {
				if (getDirector() == DIR_LEFT) {
					giuongSungLeft.setCurrentFrame(currentGiuongSung);
					giuongSungLeft.draw(g2, (int) (getX() + 26 - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY() - 32));
				} else if (getDirector() == DIR_RIGHT) {
					giuongSungRight.setCurrentFrame(currentGiuongSung);
					giuongSungRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
							(int) (getY() - getGame().getCamera().getY() - 32));
				}
			} else {
				if (getDirector() == DIR_LEFT) {
					drawAndUploadHanhDong(g2, dungImLeft);
				} else if (getDirector() == DIR_RIGHT) {
					drawAndUploadHanhDong(g2, dungImRight);
				}
			}
		} else if (getSpeedX() == 0 && getSpeedY() != 0) {
			if (getDirector() == DIR_LEFT) {
				jumpLeft.setCurrentFrame(jumpLeft.getListImage().size() - 1);
				drawAndUploadHanhDong(g2, jumpLeft);
			} else if (getDirector() == DIR_RIGHT) {
				jumpRight.setCurrentFrame(jumpRight.getListImage().size() - 1);
				drawAndUploadHanhDong(g2, jumpRight);
			}
		} else if (getIsJumping() && getSpeedY() == 0) {
			tiepDat();
		} else if (getIsJumping()) {
			if (getDirector() == DIR_LEFT) {
				drawAndUploadHanhDong(g2, jumpLeft);
			} else if (getDirector() == DIR_RIGHT) {
				drawAndUploadHanhDong(g2, jumpRight);
			}
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

	@Override
	public void jump() {
		if (!getIsJumping()) {

			setSpeedY(-5);
			setY(getY() - 2);
			setIsJumping(true);

			if (getDirector() == DIR_LEFT) {
				setSpeedX(-2);
			} else if (getDirector() == DIR_RIGHT) {
				setSpeedX(+2);
			}

			giuongSungLeft.reset();
			giuongSungRight.reset();
			haSungLeft.reset();
			haSungRight.reset();
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
		setSpeedX(0);
		setSpeedY(0);
		jumpLeft.reset();
		jumpRight.reset();
	}

	@Override
	public void attack() {
		RobotBunkerShotBullet bullet = new RobotBunkerShotBullet(getX() + 20, getY() - 70, getGame());

		AnimationBackGroundMap anime = new AnimationBackGroundMap(bullet.getX(), bullet.getY(), getGame(),
				new Animation(smoke), 28, 28);

		anime.setY(anime.getY() + 15);

		if (currentGiuongSung == 2) {
			if (getDirector() == DIR_LEFT) {
				bullet.setX(bullet.getX() - 10);
			} else if (getDirector() == DIR_RIGHT) {
				bullet.setX(bullet.getX() + 45);
				anime.setX(anime.getX() + 30);
			}
		} else if (currentGiuongSung == 3) {
			if (getDirector() == DIR_LEFT) {
				bullet.setX(bullet.getX() + 15);
				anime.setX(anime.getX() + 15);
			}
		}

		listAttack.add(anime);

		bullet.setSpeedX((getGame().getRockMan().getX() - getX()) / 110);
		bullet.setSpeedY(-4);
		getGame().getListBullet().addObject(bullet);

		setShooting(true);
		lastTimeShooting = System.nanoTime();

		ManagerSound.getInstance().getListSound().get("locotban").start();
		try {
			ManagerSound.getInstance().khoiTaoHieuUng("locotban");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		if (getDirector() == DIR_LEFT) {
			rect.x = rect.x + 20;
			rect.width = rect.width - 20;
		} else if (getDirector() == DIR_RIGHT) {
			rect.width = rect.width - 20;
		}
		return rect;
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();
		g2.setColor(Color.RED);
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	@Override
	public void getToDeath() {

		ManagerSound.getInstance().getListSound().get("bang").start();
		try {
			ManagerSound.getInstance().khoiTaoHieuUng("bang");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		WhenRobotBunkerShotDeath getToDeath = new WhenRobotBunkerShotDeath(getX(), getY(), getGame());
		getGame().getListWhenDeath().addWhenDeath(getToDeath);
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

	public boolean isGiuongSung() {
		return isGiuongSung;
	}

	public void setGiuongSung(boolean isGiuongSung) {
		this.isGiuongSung = isGiuongSung;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
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

	public Animation getGiuongSungLeft() {
		return giuongSungLeft;
	}

	public void setGiuongSungLeft(Animation giuongSungLeft) {
		this.giuongSungLeft = giuongSungLeft;
	}

	public Animation getGiuongSungRight() {
		return giuongSungRight;
	}

	public void setGiuongSungRight(Animation giuongSungRight) {
		this.giuongSungRight = giuongSungRight;
	}

	public Animation getHaSungLeft() {
		return haSungLeft;
	}

	public void setHaSungLeft(Animation haSungLeft) {
		this.haSungLeft = haSungLeft;
	}

	public Animation getHaSungRight() {
		return haSungRight;
	}

	public void setHaSungRight(Animation haSungRight) {
		this.haSungRight = haSungRight;
	}

	public long getLastTimeShooting() {
		return lastTimeShooting;
	}

	public void setLastTimeShooting(long lastTimeShooting) {
		this.lastTimeShooting = lastTimeShooting;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public int getCurrentGiuongSung() {
		return currentGiuongSung;
	}

	public void setCurrentGiuongSung(int currentGiuongSung) {
		this.currentGiuongSung = currentGiuongSung;
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

}
