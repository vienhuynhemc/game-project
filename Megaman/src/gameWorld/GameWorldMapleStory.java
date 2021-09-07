package gameWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import backGroundMap.BackGroundMapMapleStory;
import bain.Bain;
import blackWyvern.BlackWyvern;
import bossMapleStory.InnerRage;
import camera.CameraMapleStory;
import controller.ControllerGameWorld;
import darkStump.DarkStump;
import efforOpacity.DrawRectangleOpacity;
import itemTutorial.RectangleClose;
import loadData.InputDataMapbleStory;
import mainPlayerMapleStory.Mario;
import manager.BulletManagerMapleStory;
import manager.EffortMapleStoryManager;
import manager.ItemManagerMapleStory;
import manager.ParticulerManagerMapleStory;
import manager.WhenDeadMotLanManager;
import moonBunny.MoonBunny;
import onScreen.MarioHealthBar;
import particulerObject.ParticulerObjectMapleStory;
import physicalMap.PhysicalMapMapleStory;
import poisonFlower.PoisonFlower;
import sound.ManagerSound;
import startPixie.StarPixie;
import tool.ObjectStairs;
import tutorial.AfterBossMapleStoryDiedTutorial;
import tutorial.BossMapleStoryTutorial;
import tutorial.TutorialMapleStory;
import view.GameFrame;
import yeti.Yeti;
import zombieLupin.ZombieLupin;

public class GameWorldMapleStory extends GameWorld {

	private List<Integer> listSetStageAttackBoss;

	private RectangleClose rectangleKhepManHinh1;
	private RectangleClose rectangleKhepManHinh2;

	private ParticulerManagerMapleStory listObject;
	private BulletManagerMapleStory listBullet;
	private WhenDeadMotLanManager listWhenDead;
	private ItemManagerMapleStory listItem;
	private EffortMapleStoryManager listEF;

	private List<TutorialMapleStory> listTutorial;

	private DrawRectangleOpacity drawRectangeEnd;

	private MarioHealthBar thanhHpMario;

	private boolean isReady;

	private Mario mario;
	private ObjectStairs cauThang;

	private InnerRage boss;

	private boolean iStartGame;
	private boolean isCreateHp;

	public GameWorldMapleStory(ControllerGameWorld controller, String name) throws IOException {
		super(controller, name);

		setInputData(new InputDataMapbleStory(this));

		setPhysicalMap(new PhysicalMapMapleStory(0, 0, this));

		setBackGroundMap(new BackGroundMapMapleStory(0, 0, this));

		thanhHpMario = new MarioHealthBar(20, 200, this);

		setCamera(new CameraMapleStory(119, 920, this, GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT));

		setSTAGE(NORMAL);
		listObject = new ParticulerManagerMapleStory();
		listBullet = new BulletManagerMapleStory();
		listWhenDead = new WhenDeadMotLanManager();
		listItem = new ItemManagerMapleStory();
		listEF = new EffortMapleStoryManager();
		initObject();

		listTutorial = new ArrayList<TutorialMapleStory>();
		createTutorial();

		rectangleKhepManHinh1 = new RectangleClose(0, 0, this, ParticulerObjectMapleStory.DIR_LEFT);
		rectangleKhepManHinh2 = new RectangleClose(0, 290, this, ParticulerObjectMapleStory.DIR_RIGHT);
		rectangleKhepManHinh1.setSize(290);
		rectangleKhepManHinh2.setSize(310);
		rectangleKhepManHinh1.setCurrentSize(290);
		rectangleKhepManHinh2.setCurrentSize(310);
		rectangleKhepManHinh1.setStage(RectangleClose.KHEPLAI);
		rectangleKhepManHinh2.setStage(RectangleClose.KHEPLAI);

		createStageAttackBoss();

		drawRectangeEnd = new DrawRectangleOpacity(Color.WHITE);
		drawRectangeEnd.setChuyenStage(false);
	}

	public void createTutorial() {
		BossMapleStoryTutorial tutorialBoss = new BossMapleStoryTutorial(this, ATTACKBOSS);
		listTutorial.add(tutorialBoss);

		AfterBossMapleStoryDiedTutorial turtorialAfterBossDied = new AfterBossMapleStoryDiedTutorial(this,
				WINGAMESWITCHSTAGE);
		listTutorial.add(turtorialAfterBossDied);
	}

	private void createStageAttackBoss() {
		listSetStageAttackBoss = new ArrayList<Integer>();
		listSetStageAttackBoss.add(8700);
	}

	public void initObject() {

		DarkStump ds1 = new DarkStump(800, 1250, this, ParticulerObjectMapleStory.DIR_RIGHT);
		DarkStump ds2 = new DarkStump(3940, 1480, this, ParticulerObjectMapleStory.DIR_RIGHT);
		DarkStump ds3 = new DarkStump(7966, 1716, this, ParticulerObjectMapleStory.DIR_RIGHT);
		listObject.addObject(ds1);
		listObject.addObject(ds2);
		listObject.addObject(ds3);

		Yeti y1 = new Yeti(600, 1150, this, ParticulerObjectMapleStory.DIR_LEFT, 0);
		Yeti y2 = new Yeti(4962, 1396, this, ParticulerObjectMapleStory.DIR_LEFT, 0);
		Yeti y3 = new Yeti(8240, 1620, this, ParticulerObjectMapleStory.DIR_LEFT, 0);
		Yeti y4 = new Yeti(2530, 1366, this, ParticulerObjectMapleStory.DIR_LEFT, 0);
		listObject.addObject(y1);
		listObject.addObject(y2);
		listObject.addObject(y3);
		listObject.addObject(y4);

		StarPixie sp1 = new StarPixie(2362, 1174, this);
		StarPixie sp2 = new StarPixie(5866, 1464, this);
		StarPixie sp3 = new StarPixie(2530, 1466, this);
		listObject.addObject(sp1);
		listObject.addObject(sp2);
		listObject.addObject(sp3);

		MoonBunny mb1 = new MoonBunny(1412, 1194, this, ParticulerObjectMapleStory.DIR_LEFT);
		MoonBunny mb2 = new MoonBunny(7276, 950, this, ParticulerObjectMapleStory.DIR_LEFT);
		MoonBunny mb3 = new MoonBunny(4960, 1136, this, ParticulerObjectMapleStory.DIR_LEFT);
		listObject.addObject(mb1);
		listObject.addObject(mb2);
		listObject.addObject(mb3);

		PoisonFlower pf1 = new PoisonFlower(3940, 1480, this, ParticulerObjectMapleStory.DIR_LEFT);
		PoisonFlower pf2 = new PoisonFlower(7454, 934, this, ParticulerObjectMapleStory.DIR_LEFT);
		PoisonFlower pf3 = new PoisonFlower(6570, 1434, this, ParticulerObjectMapleStory.DIR_LEFT);
		listObject.addObject(pf1);
		listObject.addObject(pf2);
		listObject.addObject(pf3);

		Bain b1 = new Bain(6430, 1434, this, ParticulerObjectMapleStory.DIR_LEFT);
		Bain b2 = new Bain(3604, 1438, this, ParticulerObjectMapleStory.DIR_LEFT);
		Bain b3 = new Bain(3718, 910, this, ParticulerObjectMapleStory.DIR_LEFT);
		listObject.addObject(b1);
		listObject.addObject(b2);
		listObject.addObject(b3);

		BlackWyvern bw1 = new BlackWyvern(1860, 780, this, ParticulerObjectMapleStory.DIR_LEFT);
		BlackWyvern bw2 = new BlackWyvern(5000, 760, this, ParticulerObjectMapleStory.DIR_LEFT);
		BlackWyvern bw3 = new BlackWyvern(6000, 1020, this, ParticulerObjectMapleStory.DIR_LEFT);
		listObject.addObject(bw1);
		listObject.addObject(bw2);
		listObject.addObject(bw3);

		ZombieLupin zl1 = new ZombieLupin(3160, 1040, this);
		ZombieLupin zl2 = new ZombieLupin(4436, 1040, this);
		ZombieLupin zl3 = new ZombieLupin(5512, 1040, this);
		ZombieLupin zl4 = new ZombieLupin(6808, 940, this);
		listObject.addObject(zl1);
		listObject.addObject(zl2);
		listObject.addObject(zl3);
		listObject.addObject(zl4);

		cauThang = new ObjectStairs(6868, 1102, this);

		listObject.addObject(cauThang);

		boss = new InnerRage(9400, 1630, this);
		listObject.addObject(boss);

		mario = new Mario(368, 900, this);
		listObject.addObject(mario);
	}

	@Override
	public void upload() throws IOException {

		listWhenDead.upload();
		listItem.upLoad();
		listEF.upload();
		if (isReady) {

			for (int i = 0; i < listSetStageAttackBoss.size(); i++) {
				Integer index = listSetStageAttackBoss.get(i);
				if (mario.getX() > index) {
					setSTAGE(GameWorldMapleStory.ATTACKBOSS);
					listSetStageAttackBoss.remove(i);
					getCamera().setLimitX(index - 50);
				}
			}

			switch (getSTAGE()) {
			case NORMAL:

				if (iStartGame) {
					thanhHpMario.upload();
					if (!isCreateHp) {
						ManagerSound.getInstance().getListSound().get("hoiHp").start();
						isCreateHp = true;
					}
				}

				listObject.upload();
				listBullet.upload();
				getCamera().upload();
				getPhysicalMap().upload();
				break;
			case PAUSE:

				break;

			case ATTACKBOSS:

				getPhysicalMap().getArray()[58][289] = 3;
				getPhysicalMap().getArray()[60][289] = 3;
				getPhysicalMap().getArray()[59][289] = 3;
				getPhysicalMap().getArray()[57][289] = 3;
				getPhysicalMap().getArray()[56][289] = 3;
				getPhysicalMap().getArray()[55][289] = 3;
				getPhysicalMap().getArray()[54][289] = 3;
				getPhysicalMap().getArray()[53][289] = 3;
				getPhysicalMap().getArray()[52][289] = 3;
				getPhysicalMap().getArray()[51][289] = 3;
				getPhysicalMap().getArray()[50][289] = 3;
				getPhysicalMap().getArray()[49][289] = 3;
				getPhysicalMap().getArray()[48][289] = 3;
				getPhysicalMap().getArray()[47][289] = 3;
				getPhysicalMap().getArray()[46][289] = 3;

				listObject.upload();
				listBullet.upload();

				getCamera().upload();
				getPhysicalMap().upload();

				boss.upload();

				thanhHpMario.upload();

				break;
			case PLAYTUTORIAL:

				mario.upload();

				boss.upload();

				getAction().getActionKeyForMapbleStory().setLock(true);
				mario.setSpeedX(0);

				if (getMario().getX() - getCamera().getX() > 150) {
					getCamera().setX(getCamera().getX() + 1);
				} else {
					getCamera().setLock(true);
					getCamera().setLimitX((int) getCamera().getX());
				}

				if (!rectangleKhepManHinh1.isComplete() && rectangleKhepManHinh1.getCount() == 0) {
					rectangleKhepManHinh1.upload();
					rectangleKhepManHinh2.upload();
				} else {

					rectangleKhepManHinh1.setCount(1);
					if (!listTutorial.get(0).isComplete()) {
						listTutorial.get(0).upload();
					} else if (listTutorial.get(0).isComplete()) {
						if (rectangleKhepManHinh1.getCount() == 1) {
							rectangleKhepManHinh1.setStage(RectangleClose.KHEPLAI);
							rectangleKhepManHinh2.setStage(RectangleClose.KHEPLAI);
							rectangleKhepManHinh1.setComplete(false);
							rectangleKhepManHinh1.setComplete(false);

							rectangleKhepManHinh1.upload();
							rectangleKhepManHinh2.upload();

							listTutorial.get(0).removeRectBackGround();
							listTutorial.get(0).upload();

							if (rectangleKhepManHinh1.isComplete()) {
								rectangleKhepManHinh1 = new RectangleClose(0, 0, this,
										ParticulerObjectMapleStory.DIR_LEFT);
								rectangleKhepManHinh2 = new RectangleClose(0, GameFrame.GAME_HEIGHT - 40, this,
										ParticulerObjectMapleStory.DIR_RIGHT);

								setSTAGE(listTutorial.get(0).getSTAGE());
								listTutorial.remove(0);

								getAction().getActionKeyForMapbleStory().unlock();
								getCamera().unlock();
							}
						}
					}
				}

				listBullet.upload();
				break;
			case WINGAMESWITCHSTAGE:
				getMario().setStage(ParticulerObjectMapleStory.SWITCHMEGAMAN);

				mario.upload();
				listBullet.upload();

				if (getMario().getBlackHoleHutL().getHeSo() > 0) {
					getMario().getBlackHoleHutL().setHeSo(getMario().getBlackHoleHutL().getHeSo() - 0.005f);
					getMario().getBlackHoleHutR().setHeSo(getMario().getBlackHoleHutR().getHeSo() - 0.005f);
				}

				if (getMario().getY() <= 1464) {

					if (drawRectangeEnd.getNow() == 255) {
						drawRectangeEnd.setStage(DrawRectangleOpacity.LOW);
					}

					if (drawRectangeEnd.getStage() == DrawRectangleOpacity.LOW) {
						getController().getModel().getMegamanX().getRockMan()
								.setStage(ParticulerObjectMapleStory.ALIVE);
						getController().getModel().getMegamanX().setSTAGE(WINALLGAME);

						try {
							ManagerSound.getInstance().stop("nhacNenMapleStory");
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							e.printStackTrace();
						}
						ManagerSound.getInstance().getListSound().get("nhacNenMegamanX").start();
						getController().getModel().getMegamanX().getDrawRectangeEnd().setStage(DrawRectangleOpacity.UP);
						getController().getModel().getMegamanX().getDrawRectangeEnd().setNow(0);
						getController().getModel().getMegamanX().getDrawRectangeEnd().setColor(Color.WHITE);
						getController().getModel().getMegamanX().getDrawRectangeEnd().setChuyenStage(true);
						getController().getModel().setCurrentStage(1);
					}
				}
				break;

			case PLAYTUTORIALNOTRECTANGLE:
				getAction().getActionKeyForMapbleStory().setLock(true);
				mario.setSpeedX(0);
				mario.setSpeedY(0);

				mario.upload();

				if (!listTutorial.get(0).isComplete()) {
					listTutorial.get(0).upload();
				} else if (listTutorial.get(0).isComplete()) {

					listTutorial.get(0).removeRectBackGround();
					listTutorial.get(0).upload();

					setSTAGE(listTutorial.get(0).getSTAGE());
					listTutorial.remove(0);

					getAction().getActionKeyForMapbleStory().unlock();
					getCamera().unlock();

					float spaceX = 9264 - getMario().getX();
					float spaceY = 1464 - getMario().getY();

					getMario().setSpeedY(-1);

					float speedX = spaceX / spaceY;
					if (getMario().getX() > 9264) {
						if (speedX > 0) {
							speedX *= -1;
						}
					} else {
						if (speedX < 0) {
							speedX *= -1;
						}
					}
					getMario().setSpeedX(speedX);

					getAction().getActionKeyForMapbleStory().lock();

				}
				break;

			case HOIHP:
				thanhHpMario.setStart(false);

				if (thanhHpMario.getConSoLamChoRockManKhongTheUpLoad() == 0) {
					getMario().upload();
				}
				ManagerSound.getInstance().getListSound().get("hoiHp").start();
				if (ManagerSound.getInstance().getListSound().get("hoiHp").getFramePosition() == ManagerSound
						.getInstance().getListSound().get("hoiHp").getFrameLength()) {
					try {
						ManagerSound.getInstance().khoiTaoHieuUng("hoiHp");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						e.printStackTrace();
					}
				}

				thanhHpMario.upload();

				break;
			case ENDGAME:
				listObject.upload();
				listBullet.upload();
				listItem.upLoad();
				drawRectangeEnd.upload();
				break;

			case BOSSDEATH:

				break;
			default:
				break;
			}

		} else {
			rectangleKhepManHinh1.upload();
			rectangleKhepManHinh2.upload();
			if (rectangleKhepManHinh1.getCurrentSize() == 0) {
				isReady = true;
				rectangleKhepManHinh1 = new RectangleClose(0, 0, this, ParticulerObjectMapleStory.DIR_LEFT);
				rectangleKhepManHinh2 = new RectangleClose(0, GameFrame.GAME_HEIGHT - 40, this,
						ParticulerObjectMapleStory.DIR_RIGHT);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		getBackGroundMap().draw(g2);
		listWhenDead.draw(g2);
		listItem.draw(g2);

		if (!isReady) {
			rectangleKhepManHinh1.draw(g2);
			rectangleKhepManHinh2.draw(g2);
		} else {
			listObject.draw(g2);
			listBullet.draw(g2);

			listObject.draw(g2);
			listBullet.draw(g2);
			listItem.draw(g2);

			if (getSTAGE() == ENDGAME) {
				drawRectangeEnd.upload();
				drawRectangeEnd.draw(g2);
			}

			if (getSTAGE() == BOSSDEATH) {
				drawRectangeEnd.upload();
				drawRectangeEnd.draw(g2);
				if (drawRectangeEnd.getStage() == DrawRectangleOpacity.END) {
					getAction().getActionKeyForMapbleStory().unlock();
					setSTAGE(NORMAL);
				}
			}

			if (getSTAGE() == PLAYTUTORIAL || getSTAGE() == PLAYTUTORIALNOTRECTANGLE) {
				if (listTutorial.size() != 0) {
					listTutorial.get(0).draw(g2);
				}

				if (getSTAGE() == PLAYTUTORIAL) {
					rectangleKhepManHinh1.draw(g2);
					rectangleKhepManHinh2.draw(g2);
				}
			}

			if (getSTAGE() == WINGAMESWITCHSTAGE && getMario().getY() <= 1464) {
				drawRectangeEnd.upload();
				drawRectangeEnd.draw(g2);
			}
		}

		listEF.draw(g2);
		thanhHpMario.draw(g2);

		if (getSTAGE() == ENDGAME) {
			drawRectangeEnd.draw(g2);
		}

	}

	public Mario getMario() {
		return mario;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}

	public ParticulerManagerMapleStory getListObject() {
		return listObject;
	}

	public void setListObject(ParticulerManagerMapleStory listObject) {
		this.listObject = listObject;
	}

	public RectangleClose getRectangleKhepManHinh1() {
		return rectangleKhepManHinh1;
	}

	public void setRectangleKhepManHinh1(RectangleClose rectangleKhepManHinh1) {
		this.rectangleKhepManHinh1 = rectangleKhepManHinh1;
	}

	public RectangleClose getRectangleKhepManHinh2() {
		return rectangleKhepManHinh2;
	}

	public void setRectangleKhepManHinh2(RectangleClose rectangleKhepManHinh2) {
		this.rectangleKhepManHinh2 = rectangleKhepManHinh2;
	}

	public BulletManagerMapleStory getListBullet() {
		return listBullet;
	}

	public void setListBullet(BulletManagerMapleStory listBullet) {
		this.listBullet = listBullet;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public WhenDeadMotLanManager getListWhenDead() {
		return listWhenDead;
	}

	public void setListWhenDead(WhenDeadMotLanManager listWhenDead) {
		this.listWhenDead = listWhenDead;
	}

	public ItemManagerMapleStory getListItem() {
		return listItem;
	}

	public boolean isiStartGame() {
		return iStartGame;
	}

	public void setiStartGame(boolean iStartGame) {
		this.iStartGame = iStartGame;
	}

	public void setListItem(ItemManagerMapleStory listItem) {
		this.listItem = listItem;
	}

	public EffortMapleStoryManager getListEF() {
		return listEF;
	}

	public void setListEF(EffortMapleStoryManager listEF) {
		this.listEF = listEF;
	}

	public MarioHealthBar getThanhHpMario() {
		return thanhHpMario;
	}

	public void setThanhHpMario(MarioHealthBar thanhHpMario) {
		this.thanhHpMario = thanhHpMario;
	}

	public ObjectStairs getCauThang() {
		return cauThang;
	}

	public void setCauThang(ObjectStairs cauThang) {
		this.cauThang = cauThang;
	}

	public List<Integer> getListSetStageAttackBoss() {
		return listSetStageAttackBoss;
	}

	public void setListSetStageAttackBoss(List<Integer> listSetStageAttackBoss) {
		this.listSetStageAttackBoss = listSetStageAttackBoss;
	}

	public InnerRage getBoss() {
		return boss;
	}

	public void setBoss(InnerRage boss) {
		this.boss = boss;
	}

	public List<TutorialMapleStory> getListTutorial() {
		return listTutorial;
	}

	public void setListTutorial(List<TutorialMapleStory> listTutorial) {
		this.listTutorial = listTutorial;
	}

	public DrawRectangleOpacity getDrawRectangeEnd() {
		return drawRectangeEnd;
	}

	public void setDrawRectangeEnd(DrawRectangleOpacity drawRectangeEnd) {
		this.drawRectangeEnd = drawRectangeEnd;
	}

}
