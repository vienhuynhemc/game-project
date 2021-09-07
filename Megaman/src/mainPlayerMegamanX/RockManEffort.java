package mainPlayerMegamanX;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import backGroundMap.AnimationBackGroundMap;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;
import sound.ManagerSound;

public class RockManEffort {

	private Animation removeWallKick;
	private Animation smoke;
	private long timeForSmoke;
	private List<AnimationBackGroundMap> listDraw1Lan;

	private Animation effortBulletRockManLv2, effortBulletRockManLv3;
	private Animation tichTuEffortBulletRockManLv4, effortBulletRockManLv4;
	private boolean isReadyForBulletLv4;
	private int countBulletLv4;
	private Animation startBulletRockManLv1Right, startBulletRockManLv1Left;
	private Animation startBulletRockManLv2Right, startBulletRockManLv2Left;
	private Animation startBulletRockManLv4Right, startBulletRockManLv4Left;
	private Animation effortBulletRockManLv4Left, effortBulletRockManLv4Right;

	private Animation xuatHienLeft, xuatHienRight;
	private boolean isUnlock;
	private Animation anime;

	private RockMan rockman;

	public RockManEffort(RockMan rockman) {
		this.rockman = rockman;

		countBulletLv4 = 0;

		removeWallKick = new Animation(rockman.getGame().getInputData().getListAnimation().get("bulletBasicRemove"));
		smoke = new Animation(rockman.getGame().getInputData().getListAnimation().get("smoke"));
		timeForSmoke = 0;
		listDraw1Lan = Collections.synchronizedList(new ArrayList<AnimationBackGroundMap>());

		effortBulletRockManLv2 = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("napDanBulletRockManLv2"));
		effortBulletRockManLv3 = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("napDanBulletRockManLv3"));
		tichTuEffortBulletRockManLv4 = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("effortNapDanBulletRockManLv4"));
		tichTuEffortBulletRockManLv4.setIsRepeat(false);
		effortBulletRockManLv4 = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("napDanBulletRockManLv4"));
		startBulletRockManLv1Right = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("startBulletRockManLv1"));
		startBulletRockManLv1Left = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("startBulletRockManLv1"));
		startBulletRockManLv1Left.daoNguoc();
		startBulletRockManLv2Right = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("startBulletRockManLv2"));
		startBulletRockManLv2Left = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("startBulletRockManLv2"));
		startBulletRockManLv2Left.daoNguoc();
		startBulletRockManLv4Right = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("startBulletRockManLv4"));
		startBulletRockManLv4Left = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("startBulletRockManLv4"));
		startBulletRockManLv4Left.daoNguoc();
		effortBulletRockManLv4Left = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("effortStartBulletRockManLv4"));
		effortBulletRockManLv4Left.daoNguoc();
		effortBulletRockManLv4Right = new Animation(
				rockman.getGame().getInputData().getListAnimation().get("effortStartBulletRockManLv4"));

		xuatHienLeft = (new Animation(rockman.getGame().getInputData().getListAnimation().get("xuatHien")));
		xuatHienRight = new Animation(rockman.getGame().getInputData().getListAnimation().get("xuatHien"));
		xuatHienRight.daoNguoc();

		anime = xuatHienLeft;

	}

	public void upload() {
		if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
			if (rockman.getGame().getAction().getActionKeyForMegamanX().isPressLeft() && rockman.isWallCling()) {
				if (timeForSmoke == 0) {
					timeForSmoke = System.nanoTime();
				} else {
					if (System.nanoTime() - timeForSmoke > 250000000) {
						timeForSmoke = System.nanoTime();
						AnimationBackGroundMap anime = new AnimationBackGroundMap(rockman.getX(),
								rockman.getY() + rockman.getHeight(), rockman.getGame(), new Animation(smoke), 28, 28);
						synchronized (listDraw1Lan) {
							listDraw1Lan.add(anime);
						}
					}
				}
			}
		} else if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
			if (rockman.getGame().getAction().getActionKeyForMegamanX().isPressRight() && rockman.isWallCling()) {
				if (timeForSmoke == 0) {
					timeForSmoke = System.nanoTime();
				} else {
					if (System.nanoTime() - timeForSmoke > 250000000) {
						timeForSmoke = System.nanoTime();
						AnimationBackGroundMap anime = new AnimationBackGroundMap(
								rockman.getX() + rockman.getWidth() - 30, rockman.getY() + rockman.getHeight(),
								rockman.getGame(), new Animation(smoke), 28, 28);
						synchronized (listDraw1Lan) {
							listDraw1Lan.add(anime);
						}
					}
				}
			}
		}
	}

	public void addRemoveWallKick() {
		AnimationBackGroundMap anime;

		if (getRockman().getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
			anime = new AnimationBackGroundMap(rockman.getX() + 40, rockman.getY() + rockman.getHeight() - 60,
					rockman.getGame(), new Animation(removeWallKick), 30, 30);
		} else {
			anime = new AnimationBackGroundMap(rockman.getX(), rockman.getY() + rockman.getHeight() - 60,
					rockman.getGame(), new Animation(removeWallKick), 30, 30);
		}
		synchronized (listDraw1Lan) {
			listDraw1Lan.add(anime);
		}
	}

	public int getDirectorStartBullet() {
		int director = ParticulerObjectMegamanX.DIR_LEFT;

		if (rockman.getGame().getRockMan().getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
			if (rockman.getGame().getRockMan().isWallCling()) {
				director = ParticulerObjectMegamanX.DIR_RIGHT;
			} else if (!rockman.getGame().getRockMan().isWallCling()) {
				director = ParticulerObjectMegamanX.DIR_LEFT;
			}
		} else if (rockman.getGame().getRockMan().getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
			if (rockman.getGame().getRockMan().isWallCling()) {
				director = ParticulerObjectMegamanX.DIR_LEFT;
			} else if (!rockman.getGame().getRockMan().isWallCling()) {
				director = ParticulerObjectMegamanX.DIR_RIGHT;
			}
		}

		return director;
	}

	public void addStartBullet(int level) {
		if (level == 1) {

			int director = getDirectorStartBullet();

			Animation animation;
			if (director == ParticulerObjectMegamanX.DIR_LEFT)
				animation = startBulletRockManLv1Left;
			else
				animation = startBulletRockManLv1Right;
			AnimationBackGroundMap anime = new AnimationBackGroundMap(rockman.getX(), rockman.getY(), rockman.getGame(),
					new Animation(animation), 40, 52);

			if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
				if (rockman.isWallCling()) {
					anime.setX(anime.getX() - 56);
					anime.setY(anime.getY() + 22);
				} else if (!rockman.isWallCling()) {
					anime.setX(anime.getX() + rockman.getWidth());
				}
			} else if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
				if (rockman.isWallCling()) {
					anime.setX(anime.getX() + rockman.getWidth() + 22);
					anime.setY(anime.getY() + 22);
				} else {
					anime.setX(anime.getX() - 20);
				}
			}
			if (rockman.getSpeedX() != 0) {
				if (rockman.getSpeedX() > 0)
					anime.setX(anime.getX() + 20);
				else if (rockman.getSpeedX() < 0)
					anime.setX(anime.getX() - 20);
			}
			if (rockman.getIsDicking()) {
				anime.setY(anime.getY() + 25);
				if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT)
					anime.setX(anime.getX() - 20);
				else if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_RIGHT)
					anime.setX(anime.getX() + 20);
			}
			if (rockman.isLeoCauThang()) {
				anime.setY(anime.getY() + 8);
				if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
					anime.setX(anime.getX() - rockman.getWidth() + 15);
				}
			}
			anime.setY(anime.getY() + 5);

			synchronized (listDraw1Lan) {
				listDraw1Lan.add(anime);
			}
		} else if (level == 3 || level == 4) {

			int director = getDirectorStartBullet();

			Animation animation;
			Animation effort;
			if (director == ParticulerObjectMegamanX.DIR_LEFT) {
				animation = startBulletRockManLv4Left;
				effort = effortBulletRockManLv4Left;
			} else {
				animation = startBulletRockManLv4Right;
				effort = effortBulletRockManLv4Right;
			}
			AnimationBackGroundMap anime = new AnimationBackGroundMap(rockman.getX(), rockman.getY(), rockman.getGame(),
					new Animation(animation), 46, 56);
			AnimationBackGroundMap animeEffort = new AnimationBackGroundMap(rockman.getX(), rockman.getY() - 40,
					rockman.getGame(), new Animation(effort), 64, 156);

			if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
				if (rockman.isWallCling()) {
					anime.setX(anime.getX() - 100);
					anime.setY(anime.getY() + 22);
				} else if (!rockman.isWallCling()) {
					anime.setX(anime.getX() + rockman.getWidth());
					animeEffort.setX(animeEffort.getX() + 20);
				}
			} else if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
				if (rockman.isWallCling()) {
					anime.setX(anime.getX() + rockman.getWidth() + 22);
					anime.setY(anime.getY() + 22);
				} else {
					anime.setX(anime.getX() - 20);
				}
			}
			if (rockman.getSpeedX() != 0) {
				if (rockman.getSpeedX() > 0)
					anime.setX(anime.getX() + 20);
				else if (rockman.getSpeedX() < 0)
					anime.setX(anime.getX() - 20);
			}
			if (rockman.getIsDicking()) {
				anime.setY(anime.getY() + 25);
				animeEffort.setY(animeEffort.getY() + 15);
				if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT)
					anime.setX(anime.getX() - 20);
				else if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_RIGHT)
					anime.setX(anime.getX() + 20);
			}
			if (rockman.isLeoCauThang()) {
				anime.setY(anime.getY() + 8);
				if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
					anime.setX(anime.getX() - rockman.getWidth() + 15);
				}
			}
			anime.setY(anime.getY() + 5);

			synchronized (listDraw1Lan) {
				listDraw1Lan.add(animeEffort);
				listDraw1Lan.add(anime);
			}
		} else if (level == 2) {
			int director = getDirectorStartBullet();

			Animation animation;
			if (director == ParticulerObjectMegamanX.DIR_LEFT)
				animation = startBulletRockManLv2Left;
			else
				animation = startBulletRockManLv2Right;
			AnimationBackGroundMap anime = new AnimationBackGroundMap(rockman.getX(), rockman.getY(), rockman.getGame(),
					new Animation(animation), 48, 48);

			if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
				if (rockman.isWallCling()) {
					anime.setX(anime.getX() - 56);
					anime.setY(anime.getY() + 22);
				} else if (!rockman.isWallCling()) {
					anime.setX(anime.getX() + rockman.getWidth());
				}
			} else if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
				if (rockman.isWallCling()) {
					anime.setX(anime.getX() + rockman.getWidth() + 22);
					anime.setY(anime.getY() + 22);
				} else {
					anime.setX(anime.getX() - 20);
				}
			}
			if (rockman.getSpeedX() != 0) {
				if (rockman.getSpeedX() > 0)
					anime.setX(anime.getX() + 20);
				else if (rockman.getSpeedX() < 0)
					anime.setX(anime.getX() - 20);
			}
			if (rockman.getIsDicking()) {
				anime.setY(anime.getY() + 25);
				if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT)
					anime.setX(anime.getX() - 20);
				else if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_RIGHT)
					anime.setX(anime.getX() + 20);
			}
			if (rockman.isLeoCauThang()) {
				anime.setY(anime.getY() + 8);
				if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_LEFT) {
					anime.setX(anime.getX() - rockman.getWidth() + 15);
				}
			}
			anime.setY(anime.getY() + 5);

			synchronized (listDraw1Lan) {
				listDraw1Lan.add(anime);
			}
		}
	}

	public void drawListDraw1Lan(Graphics2D g2) {
		synchronized (listDraw1Lan) {
			for (int i = 0; i < listDraw1Lan.size(); i++) {
				if (!listDraw1Lan.get(i).isOutCamera()) {
					listDraw1Lan.get(i).upload();
					listDraw1Lan.get(i).draw(g2);
					if (listDraw1Lan.get(i).getAnime().isLastFrame()) {
						listDraw1Lan.remove(i);
					}
				}
			}
		}
	}

	public void drawXuatHien(Graphics2D g2, int director) {
		if (director == ParticulerObjectMegamanX.DIR_LEFT) {
			anime = xuatHienLeft;
		} else {
			anime = xuatHienRight;
		}
		if (!anime.isLastFrame()) {
			anime.upload(System.nanoTime());
			int locationX = (int) rockman.getX();
			int posCenterImage = anime.getListImage().get(anime.getCurrentFrame()).getImage().getWidth() / 2;
			int posCenterRockMan = (int) (rockman.getWidth() / 2);
			if (posCenterImage > posCenterRockMan) {
				int data = posCenterImage - posCenterRockMan;
				locationX = locationX - data;
			} else {
				int data = posCenterRockMan - posCenterImage;
				locationX = locationX + data;
			}
			if (anime.getCurrentFrame() == 12 || anime.getCurrentFrame() == 13)
				anime.draw(g2, (int) (locationX - 30 - rockman.getGame().getCamera().getX()),
						(int) (rockman.getY() - rockman.getGame().getCamera().getY()) - 16);
			else
				anime.draw(g2, (int) (locationX - 30 - rockman.getGame().getCamera().getX()),
						(int) (rockman.getY() - rockman.getGame().getCamera().getY()));

			if (anime.getCurrentFrame() > 0) {
				Rectangle rect = getRockman().getGame().getPhysicalMap()
						.vaChamMatDat(getRockman().getRectangleXuLiVaCham());
				if (rect == null) {
					getRockman().setY(getRockman().getY() + getRockman().getSpeedY());
					getRockman().setSpeedY(getRockman().getSpeedY() + getRockman().getMass());
				} else {
					getRockman().setSpeedY(0);
					getRockman().setY(rect.y - getRockman().getHeight() + 1);
				}
			} else {
				getRockman().setY(getRockman().getY() + getRockman().getSpeedY());
				getRockman().setSpeedY(getRockman().getSpeedY() + getRockman().getMass());
			}

			ManagerSound.getInstance().getListSound().get("menuPress").start();

		} else {
			ManagerSound.getInstance().getListSound().get("menuPress").setFramePosition(0);
			isUnlock = true;
			rockman.getGame().setSTAGE(GameWorldMegamanX.HOIHP);
		}
	}

	public void unlock() {
		rockman.getGame().getCamera().unlock();
		rockman.getGame().getAction().getActionKeyForMegamanX().unlock();
		isUnlock = true;
	}

	public void lock() {
		rockman.getGame().getCamera().lock();
		rockman.getGame().getCamera().lock();
	}

	public void drawNapDan(Graphics2D g2) {
		if (rockman.isNapDan()) {
			if (System.nanoTime() - rockman.getTimeBeginNapDan() > 200000000
					&& System.nanoTime() - rockman.getTimeBeginNapDan() < 1200000000) {
				if (rockman.getIsDicking()) {
					if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
						effortBulletRockManLv2.upload(System.nanoTime());
						effortBulletRockManLv2.draw(g2,
								(int) (rockman.getX() + 10 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() + 15 - rockman.getGame().getCamera().getY()));
					} else {
						effortBulletRockManLv2.upload(System.nanoTime());
						effortBulletRockManLv2.draw(g2,
								(int) (rockman.getX() - 10 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() + 15 - rockman.getGame().getCamera().getY()));
					}
				} else {
					effortBulletRockManLv2.upload(System.nanoTime());
					effortBulletRockManLv2.draw(g2, (int) (rockman.getX() - 10 - rockman.getGame().getCamera().getX()),
							(int) (rockman.getY() - rockman.getGame().getCamera().getY()));
				}
			} else if (System.nanoTime() - rockman.getTimeBeginNapDan() > 1200000000
					&& System.nanoTime() - rockman.getTimeBeginNapDan() < 2100000000) {
				if (rockman.getIsDicking()) {
					if (rockman.getDirector() == ParticulerObjectMegamanX.DIR_RIGHT) {
						effortBulletRockManLv3.upload(System.nanoTime());
						effortBulletRockManLv3.draw(g2,
								(int) (rockman.getX() + 10 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() + 15 - rockman.getGame().getCamera().getY()));
					} else {
						effortBulletRockManLv3.upload(System.nanoTime());
						effortBulletRockManLv3.draw(g2,
								(int) (rockman.getX() - 10 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() + 15 - rockman.getGame().getCamera().getY()));
					}
				} else {
					effortBulletRockManLv3.upload(System.nanoTime());
					effortBulletRockManLv3.draw(g2, (int) (rockman.getX() - 10 - rockman.getGame().getCamera().getX()),
							(int) (rockman.getY() - rockman.getGame().getCamera().getY()));
				}
			} else if (System.nanoTime() - rockman.getTimeBeginNapDan() > 2100000000) {

				if (!tichTuEffortBulletRockManLv4.isLastFrame()) {
					tichTuEffortBulletRockManLv4.upload(System.nanoTime());
					if (tichTuEffortBulletRockManLv4.getCurrentFrame() == 6) {
						tichTuEffortBulletRockManLv4.draw(g2,
								(int) (rockman.getX() - 45 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() - 40 - rockman.getGame().getCamera().getY()));
					} else if (tichTuEffortBulletRockManLv4.getCurrentFrame() == 7
							&& tichTuEffortBulletRockManLv4.getCurrentFrame() == 8) {
						tichTuEffortBulletRockManLv4.draw(g2,
								(int) (rockman.getX() - 32 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() - 40 - rockman.getGame().getCamera().getY()));
					} else {
						tichTuEffortBulletRockManLv4.draw(g2,
								(int) (rockman.getX() - 50 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() - 40 - rockman.getGame().getCamera().getY()));
					}
				} else if (tichTuEffortBulletRockManLv4.isLastFrame() && countBulletLv4 < 7) {
					countBulletLv4++;
					tichTuEffortBulletRockManLv4.reset();
				} else {

					isReadyForBulletLv4 = true;

					effortBulletRockManLv4.upload(System.nanoTime());
					if (effortBulletRockManLv4.getCurrentFrame() == 0) {
						effortBulletRockManLv4.draw(g2,
								(int) (rockman.getX() + 24 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() - rockman.getGame().getCamera().getY()));
					} else if (effortBulletRockManLv4.getCurrentFrame() == 1) {
						effortBulletRockManLv4.draw(g2,
								(int) (rockman.getX() + 12 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() - rockman.getGame().getCamera().getY()));
					} else if (effortBulletRockManLv4.getCurrentFrame() == 2) {
						effortBulletRockManLv4.draw(g2,
								(int) (rockman.getX() - 6 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() - rockman.getGame().getCamera().getY()));
					} else if (effortBulletRockManLv4.getCurrentFrame() == 3) {
						effortBulletRockManLv4.draw(g2,
								(int) (rockman.getX() - 20 - rockman.getGame().getCamera().getX()),
								(int) (rockman.getY() - rockman.getGame().getCamera().getY()));
					}
				}
			}
		}
	}

	public void resetBulletLv4() {
		tichTuEffortBulletRockManLv4.reset();
		isReadyForBulletLv4 = false;
		countBulletLv4 = 0;
	}

	public boolean isReadyForBulletLv4() {
		return isReadyForBulletLv4;
	}

	public void setReadyForBulletLv4(boolean isReadyForBulletLv4) {
		this.isReadyForBulletLv4 = isReadyForBulletLv4;
	}

	public Animation getTichTuEffortBulletRockManLv4() {
		return tichTuEffortBulletRockManLv4;
	}

	public void setTichTuEffortBulletRockManLv4(Animation tichTuEffortBulletRockManLv4) {
		this.tichTuEffortBulletRockManLv4 = tichTuEffortBulletRockManLv4;
	}

	public Animation getRemoveWallKick() {
		return removeWallKick;
	}

	public void setRemoveWallKick(Animation removeWallKick) {
		this.removeWallKick = removeWallKick;
	}

	public Animation getSmoke() {
		return smoke;
	}

	public void setSmoke(Animation smoke) {
		this.smoke = smoke;
	}

	public long getTimeForSmoke() {
		return timeForSmoke;
	}

	public void setTimeForSmoke(long timeForSmoke) {
		this.timeForSmoke = timeForSmoke;
	}

	public List<AnimationBackGroundMap> getListDraw1Lan() {
		return listDraw1Lan;
	}

	public void setListDraw1Lan(List<AnimationBackGroundMap> listDraw1Lan) {
		this.listDraw1Lan = listDraw1Lan;
	}

	public RockMan getRockman() {
		return rockman;
	}

	public void setRockman(RockMan rockman) {
		this.rockman = rockman;
	}

	public boolean isUnlock() {
		return isUnlock;
	}

	public void setUnlock(boolean isUnlock) {
		this.isUnlock = isUnlock;
	}

	public Animation getEffortBulletRockManLv2() {
		return effortBulletRockManLv2;
	}

	public void setEffortBulletRockManLv2(Animation effortBulletRockManLv2) {
		this.effortBulletRockManLv2 = effortBulletRockManLv2;
	}

	public Animation getEffortBulletRockManLv3() {
		return effortBulletRockManLv3;
	}

	public void setEffortBulletRockManLv3(Animation effortBulletRockManLv3) {
		this.effortBulletRockManLv3 = effortBulletRockManLv3;
	}

	public Animation getEffortBulletRockManLv4() {
		return effortBulletRockManLv4;
	}

	public void setEffortBulletRockManLv4(Animation effortBulletRockManLv4) {
		this.effortBulletRockManLv4 = effortBulletRockManLv4;
	}

	public int getCountBulletLv4() {
		return countBulletLv4;
	}

	public void setCountBulletLv4(int countBulletLv4) {
		this.countBulletLv4 = countBulletLv4;
	}

	public Animation getStartBulletRockManLv1Right() {
		return startBulletRockManLv1Right;
	}

	public void setStartBulletRockManLv1Right(Animation startBulletRockManLv1Right) {
		this.startBulletRockManLv1Right = startBulletRockManLv1Right;
	}

	public Animation getStartBulletRockManLv1Left() {
		return startBulletRockManLv1Left;
	}

	public void setStartBulletRockManLv1Left(Animation startBulletRockManLv1Left) {
		this.startBulletRockManLv1Left = startBulletRockManLv1Left;
	}

	public Animation getStartBulletRockManLv2Right() {
		return startBulletRockManLv2Right;
	}

	public void setStartBulletRockManLv2Right(Animation startBulletRockManLv2Right) {
		this.startBulletRockManLv2Right = startBulletRockManLv2Right;
	}

	public Animation getStartBulletRockManLv2Left() {
		return startBulletRockManLv2Left;
	}

	public void setStartBulletRockManLv2Left(Animation startBulletRockManLv2Left) {
		this.startBulletRockManLv2Left = startBulletRockManLv2Left;
	}

	public Animation getStartBulletRockManLv4Right() {
		return startBulletRockManLv4Right;
	}

	public void setStartBulletRockManLv4Right(Animation startBulletRockManLv4Right) {
		this.startBulletRockManLv4Right = startBulletRockManLv4Right;
	}

	public Animation getStartBulletRockManLv4Left() {
		return startBulletRockManLv4Left;
	}

	public void setStartBulletRockManLv4Left(Animation startBulletRockManLv4Left) {
		this.startBulletRockManLv4Left = startBulletRockManLv4Left;
	}

	public Animation getEffortBulletRockManLv4Left() {
		return effortBulletRockManLv4Left;
	}

	public void setEffortBulletRockManLv4Left(Animation effortBulletRockManLv4Left) {
		this.effortBulletRockManLv4Left = effortBulletRockManLv4Left;
	}

	public Animation getEffortBulletRockManLv4Right() {
		return effortBulletRockManLv4Right;
	}

	public void setEffortBulletRockManLv4Right(Animation effortBulletRockManLv4Right) {
		this.effortBulletRockManLv4Right = effortBulletRockManLv4Right;
	}

	public Animation getXuatHienLeft() {
		return xuatHienLeft;
	}

	public void setXuatHienLeft(Animation xuatHienLeft) {
		this.xuatHienLeft = xuatHienLeft;
	}

	public Animation getXuatHienRight() {
		return xuatHienRight;
	}

	public void setXuatHienRight(Animation xuatHienRight) {
		this.xuatHienRight = xuatHienRight;
	}

	public Animation getAnime() {
		return anime;
	}

	public void setAnime(Animation anime) {
		this.anime = anime;
	}

}
