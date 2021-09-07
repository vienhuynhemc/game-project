package gameWorld;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import backGroundMap.BackGroundMapMegamanX;
import blackHole.BlackHole;
import bossIntroStage.BossIntroStage;
import bossIntroStage.WhenBossDeath;
import camera.CameraMegamanX;
import controller.ControllerGameWorld;
import efforOpacity.DrawRectangleOpacity;
import enemieChicken.IntroEnemieRobotChicken;
import frontMap.FrontMap;
import introEnemieFlySmall.IntroEnemieFlySmall;
import itemTutorial.RectangleClose;
import loadData.InputDataMegamanX;
import mainPlayerMegamanX.RockMan;
import manager.BulletManagerMegamanX;
import manager.ItemManager;
import manager.ParticulerManagerMegamanX;
import manager.ToolManager;
import manager.WhenDeathManager;
import mettalT.MettalT;
import onScreen.RockManHealthBar;
import particulerObject.ParticulerObjectMegamanX;
import physicalMap.PhysicalMapMegamanX;
import robotBunkerShot.RobotBunkerShot;
import robotDropTheThonrs.IntroEnemieRobotDropTheThorns;
import robotWallCling.RobotWallCing;
import sound.ManagerSound;
import subBossIntroStage.SubBossIntroStage;
import tool.Abyss;
import tool.BossDoor;
import tool.Stairs;
import tutorial.AfterBossDiedTutorial;
import tutorial.BossIntroStageTutorial;
import tutorial.SubBossIntroStageTutorial;
import tutorial.Tutorial;
import view.GameFrame;

public class GameWorldMegamanX extends GameWorld {

	private RectangleClose rectangleKhepManHinh1;
	private RectangleClose rectangleKhepManHinh2;

	private List<Tutorial> listTutorial;
	private List<Integer> listSetStageAttackBoss;

	private DrawRectangleOpacity drawRectangeEnd;

	private boolean iStartGame;

	private FrontMap frontMap;

	private BulletManagerMegamanX listBullet;
	private ParticulerManagerMegamanX listObject;
	private WhenDeathManager listWhenDeath;
	private ItemManager listItem;
	private ToolManager listDungCu;

	private RockManHealthBar thanhHpRockMan;

	private SubBossIntroStage subBoss;
	private BossIntroStage boss;

	private RockMan rockMan;

	private boolean isReady;

	private boolean isRemoveBlackHole;
	private WhenBossDeath whenDead;

	private boolean ENDDDDDDDDDDDDDDDDDDD;

	private int yDraw;
	private long timeStarEnd;

	private boolean isVeMenu;

	public GameWorldMegamanX(ControllerGameWorld controller, String name) throws IOException {
		super(controller, name);

		yDraw = 600;

		setInputData(new InputDataMegamanX(this));

		setPhysicalMap(new PhysicalMapMegamanX(0, 0, this));
		setBackGroundMap(new BackGroundMapMegamanX(0, 0, this));
		frontMap = new FrontMap(0, 0, this);
		setCamera(new CameraMegamanX(520, 2537, this, GameFrame.GAME_HEIGHT, GameFrame.GAME_WIDTH));

		listWhenDeath = new WhenDeathManager();
		listBullet = new BulletManagerMegamanX();
		listObject = new ParticulerManagerMegamanX();
		listItem = new ItemManager();
		listDungCu = new ToolManager();

		thanhHpRockMan = new RockManHealthBar(20, 200, this);
		rockMan = new RockMan(0, 2400, this);

//		setCamera(new CameraMegamanX(6800, 1301, this, GameFrame.GAME_HEIGHT, GameFrame.GAME_WIDTH));
//		rockMan = new RockMan(7070, 1250, this);

		// boss
//		setCamera(new CameraMegamanX(8200, 801, this, GameFrame.GAME_HEIGHT, GameFrame.GAME_WIDTH));
//		rockMan = new RockMan(8570, 650, this);

		createObject();

		listTutorial = new ArrayList<Tutorial>();
		createTutorial();
		createStageAttackBoss();

		rectangleKhepManHinh1 = new RectangleClose(0, 0, this, ParticulerObjectMegamanX.DIR_LEFT);
		rectangleKhepManHinh2 = new RectangleClose(0, 290, this, ParticulerObjectMegamanX.DIR_RIGHT);
		rectangleKhepManHinh1.setSize(290);
		rectangleKhepManHinh2.setSize(310);
		rectangleKhepManHinh1.setCurrentSize(290);
		rectangleKhepManHinh2.setCurrentSize(310);
		rectangleKhepManHinh1.setStage(RectangleClose.KHEPLAI);
		rectangleKhepManHinh2.setStage(RectangleClose.KHEPLAI);

		drawRectangeEnd = new DrawRectangleOpacity(Color.WHITE);
		whenDead = new WhenBossDeath(0, 0, this);

		setSTAGE(NORMAL);

	}

	private void createStageAttackBoss() {
		listSetStageAttackBoss = new ArrayList<Integer>();
		listSetStageAttackBoss.add(7685);
		listSetStageAttackBoss.add(8732);
	}

	private void createTutorial() {
		SubBossIntroStageTutorial tutorialSubBoss = new SubBossIntroStageTutorial(this, ATTACKBOSS);
		listTutorial.add(tutorialSubBoss);

		BossIntroStageTutorial tutorialBoss = new BossIntroStageTutorial(this, ATTACKBOSS);
		listTutorial.add(tutorialBoss);

		AfterBossDiedTutorial turtorialAfterBossDied = new AfterBossDiedTutorial(this, WINGAMESWITCHSTAGE);
		listTutorial.add(turtorialAfterBossDied);
	}

	private void createObject() {
		Stairs cauThang1 = new Stairs(2352, 2906, this, 64, 222);
		Stairs cauThang2 = new Stairs(3952, 3448, this, 64, 194);
		Stairs cauThang3 = new Stairs(6224, 3322, this, 64, 384);
		listDungCu.add(cauThang1);
		listDungCu.add(cauThang2);
		listDungCu.add(cauThang3);

		Abyss vucSau1 = new Abyss(6784, 3472, this, 200, 132);
		listDungCu.add(vucSau1);

		BossDoor bossDoor1 = new BossDoor(7645, 1818, this);
		BossDoor bossDoor2 = new BossDoor(8210, 1818, this);
		BossDoor bossDoor3 = new BossDoor(8692, 1222, this);
		listDungCu.add(bossDoor1);
		listDungCu.add(bossDoor2);
		listDungCu.add(bossDoor3);

		RobotBunkerShot robotBanLoCot1 = new RobotBunkerShot(1285, 2935, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot2 = new RobotBunkerShot(1385, 2815, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot3 = new RobotBunkerShot(1590, 2790, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot4 = new RobotBunkerShot(1912, 2858, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot5 = new RobotBunkerShot(2058, 2804, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot6 = new RobotBunkerShot(2248, 2804, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot7 = new RobotBunkerShot(2438, 2728, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot8 = new RobotBunkerShot(2824, 3278, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot9 = new RobotBunkerShot(2884, 3278, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot10 = new RobotBunkerShot(3206, 3220, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot11 = new RobotBunkerShot(3266, 3220, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot12 = new RobotBunkerShot(3452, 3350, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot13 = new RobotBunkerShot(3552, 3350, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot14 = new RobotBunkerShot(3672, 3350, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot15 = new RobotBunkerShot(3802, 3350, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot16 = new RobotBunkerShot(4034, 3884, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot17 = new RobotBunkerShot(4534, 3884, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot18 = new RobotBunkerShot(5034, 3884, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotBunkerShot robotBanLoCot19 = new RobotBunkerShot(5534, 3884, this, ParticulerObjectMegamanX.DIR_LEFT);
		listObject.addObject(robotBanLoCot1);
		listObject.addObject(robotBanLoCot2);
		listObject.addObject(robotBanLoCot3);
		listObject.addObject(robotBanLoCot4);
		listObject.addObject(robotBanLoCot5);
		listObject.addObject(robotBanLoCot6);
		listObject.addObject(robotBanLoCot7);
		listObject.addObject(robotBanLoCot8);
		listObject.addObject(robotBanLoCot9);
		listObject.addObject(robotBanLoCot10);
		listObject.addObject(robotBanLoCot11);
		listObject.addObject(robotBanLoCot12);
		listObject.addObject(robotBanLoCot13);
		listObject.addObject(robotBanLoCot14);
		listObject.addObject(robotBanLoCot15);
		listObject.addObject(robotBanLoCot16);
		listObject.addObject(robotBanLoCot17);
		listObject.addObject(robotBanLoCot18);
		listObject.addObject(robotBanLoCot19);

		MettalT mettalT1 = new MettalT(1428, 2798, this, ParticulerObjectMegamanX.DIR_LEFT);
		MettalT mettalT2 = new MettalT(1542, 2830, this, ParticulerObjectMegamanX.DIR_LEFT);
		MettalT mettalT3 = new MettalT(1674, 2874, this, ParticulerObjectMegamanX.DIR_LEFT);
		MettalT mettalT4 = new MettalT(1810, 2930, this, ParticulerObjectMegamanX.DIR_LEFT);
		MettalT mettalT5 = new MettalT(2222, 3378, this, ParticulerObjectMegamanX.DIR_LEFT);
		MettalT mettalT6 = new MettalT(2302, 3378, this, ParticulerObjectMegamanX.DIR_LEFT);
		MettalT mettalT7 = new MettalT(2362, 3378, this, ParticulerObjectMegamanX.DIR_RIGHT);
		MettalT mettalT8 = new MettalT(2422, 3378, this, ParticulerObjectMegamanX.DIR_RIGHT);
		listObject.addObject(mettalT1);
		listObject.addObject(mettalT2);
		listObject.addObject(mettalT3);
		listObject.addObject(mettalT4);
		listObject.addObject(mettalT5);
		listObject.addObject(mettalT6);
		listObject.addObject(mettalT7);
		listObject.addObject(mettalT8);

		RobotWallCing wallCling1 = new RobotWallCing(2450, 2700, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotWallCing wallCling2 = new RobotWallCing(2100, 3214, this, ParticulerObjectMegamanX.DIR_RIGHT);
		RobotWallCing wallCling3 = new RobotWallCing(3980, 3172, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotWallCing wallCling4 = new RobotWallCing(3632, 3746, this, ParticulerObjectMegamanX.DIR_RIGHT);
		RobotWallCing wallCling5 = new RobotWallCing(6534, 3708, this, ParticulerObjectMegamanX.DIR_LEFT);
		RobotWallCing wallCling6 = new RobotWallCing(6210, 3176, this, ParticulerObjectMegamanX.DIR_RIGHT);
		RobotWallCing wallCling7 = new RobotWallCing(6210, 3528, this, ParticulerObjectMegamanX.DIR_RIGHT);
		listObject.addObject(wallCling1);
		listObject.addObject(wallCling2);
		listObject.addObject(wallCling3);
		listObject.addObject(wallCling4);
		listObject.addObject(wallCling5);
		listObject.addObject(wallCling6);
		listObject.addObject(wallCling7);

		IntroEnemieFlySmall introEnemieFlySmall1 = new IntroEnemieFlySmall(2208, 2690, this);
		IntroEnemieFlySmall introEnemieFlySmall2 = new IntroEnemieFlySmall(2102, 2676, this);
		IntroEnemieFlySmall introEnemieFlySmall3 = new IntroEnemieFlySmall(1782, 2660, this);
		IntroEnemieFlySmall introEnemieFlySmall4 = new IntroEnemieFlySmall(2978, 3198, this);
		IntroEnemieFlySmall introEnemieFlySmall5 = new IntroEnemieFlySmall(3468, 3198, this);
		IntroEnemieFlySmall introEnemieFlySmall6 = new IntroEnemieFlySmall(3912, 3198, this);
		IntroEnemieFlySmall introEnemieFlySmall7 = new IntroEnemieFlySmall(4248, 3654, this);
		IntroEnemieFlySmall introEnemieFlySmall8 = new IntroEnemieFlySmall(4548, 3654, this);
		IntroEnemieFlySmall introEnemieFlySmall9 = new IntroEnemieFlySmall(4848, 3654, this);
		IntroEnemieFlySmall introEnemieFlySmall10 = new IntroEnemieFlySmall(5148, 3654, this);
		IntroEnemieFlySmall introEnemieFlySmall11 = new IntroEnemieFlySmall(5448, 3654, this);
		IntroEnemieFlySmall introEnemieFlySmall12 = new IntroEnemieFlySmall(5748, 3654, this);
		IntroEnemieFlySmall introEnemieFlySmall13 = new IntroEnemieFlySmall(6048, 3654, this);
		IntroEnemieFlySmall introEnemieFlySmall14 = new IntroEnemieFlySmall(6348, 3654, this);
		listObject.addObject(introEnemieFlySmall1);
		listObject.addObject(introEnemieFlySmall2);
		listObject.addObject(introEnemieFlySmall3);
		listObject.addObject(introEnemieFlySmall4);
		listObject.addObject(introEnemieFlySmall5);
		listObject.addObject(introEnemieFlySmall6);
		listObject.addObject(introEnemieFlySmall7);
		listObject.addObject(introEnemieFlySmall8);
		listObject.addObject(introEnemieFlySmall9);
		listObject.addObject(introEnemieFlySmall10);
		listObject.addObject(introEnemieFlySmall11);
		listObject.addObject(introEnemieFlySmall12);
		listObject.addObject(introEnemieFlySmall13);
		listObject.addObject(introEnemieFlySmall14);

		IntroEnemieRobotChicken introEnemieRobotChicken1 = new IntroEnemieRobotChicken(2884, 3278, this,
				ParticulerObjectMegamanX.DIR_LEFT);
		IntroEnemieRobotChicken introEnemieRobotChicken2 = new IntroEnemieRobotChicken(3850, 3340, this,
				ParticulerObjectMegamanX.DIR_LEFT);
		IntroEnemieRobotChicken introEnemieRobotChicken3 = new IntroEnemieRobotChicken(4654, 3872, this,
				ParticulerObjectMegamanX.DIR_LEFT);
		IntroEnemieRobotChicken introEnemieRobotChicken4 = new IntroEnemieRobotChicken(4954, 3872, this,
				ParticulerObjectMegamanX.DIR_LEFT);
		IntroEnemieRobotChicken introEnemieRobotChicken5 = new IntroEnemieRobotChicken(5254, 3872, this,
				ParticulerObjectMegamanX.DIR_LEFT);
		IntroEnemieRobotChicken introEnemieRobotChicken6 = new IntroEnemieRobotChicken(5554, 3872, this,
				ParticulerObjectMegamanX.DIR_LEFT);
		IntroEnemieRobotChicken introEnemieRobotChicken7 = new IntroEnemieRobotChicken(5854, 3872, this,
				ParticulerObjectMegamanX.DIR_LEFT);
		IntroEnemieRobotChicken introEnemieRobotChicken8 = new IntroEnemieRobotChicken(6154, 3872, this,
				ParticulerObjectMegamanX.DIR_LEFT);
		IntroEnemieRobotChicken introEnemieRobotChicken9 = new IntroEnemieRobotChicken(6454, 3872, this,
				ParticulerObjectMegamanX.DIR_LEFT);

		listObject.addObject(introEnemieRobotChicken1);
		listObject.addObject(introEnemieRobotChicken2);
		listObject.addObject(introEnemieRobotChicken3);
		listObject.addObject(introEnemieRobotChicken4);
		listObject.addObject(introEnemieRobotChicken5);
		listObject.addObject(introEnemieRobotChicken6);
		listObject.addObject(introEnemieRobotChicken7);
		listObject.addObject(introEnemieRobotChicken8);
		listObject.addObject(introEnemieRobotChicken9);

		IntroEnemieRobotDropTheThorns introEnemieRobotThaQuaCauGai1 = new IntroEnemieRobotDropTheThorns(6876, 1604,
				this, ParticulerObjectMegamanX.DIR_RIGHT);
		listObject.addObject(introEnemieRobotThaQuaCauGai1);

		subBoss = new SubBossIntroStage(8084, 1634, this);
		listObject.addObject(subBoss);

		boss = new BossIntroStage(9270, 1450, this);
		listObject.addObject(boss);

		listObject.addObject(rockMan);

	}

	@Override
	public void upload() {

		if (isReady) {

			for (int i = 0; i < listSetStageAttackBoss.size(); i++) {
				Integer index = listSetStageAttackBoss.get(i);
				if (rockMan.getX() > index) {
					setSTAGE(GameWorldMegamanX.ATTACKBOSS);
					listSetStageAttackBoss.remove(i);
					getCamera().setLimitX(index - 50);
				}
			}

			switch (getSTAGE()) {
			case NORMAL:

				if (iStartGame) {
					getRockMan().getEffort().unlock();
					thanhHpRockMan.upload();
				}

				if (!getRockMan().isChamVucSau() && rockMan.getStage() != ParticulerObjectMegamanX.DEATH) {
					getCamera().upload();
				}

				listObject.upload();
				listBullet.upload();
				listWhenDeath.upLoad();
				listItem.upLoad();
				listDungCu.upload();

				break;
			case PAUSE:

				break;

			case ATTACKBOSS:
				if (iStartGame) {
					getRockMan().getEffort().unlock();
					thanhHpRockMan.upload();
				}

				listObject.upload();
				listBullet.upload();
				listWhenDeath.upLoad();
				listItem.upLoad();
				listDungCu.upload();

				getCamera().upload();

				if (!subBoss.isReady()) {
					boss.upload();
				}
				break;
			case PLAYTUTORIAL:

				if (listTutorial.size() > 1) {
					subBoss.upload();
				} else {
					boss.upload();
				}

				getAction().getActionKeyForMegamanX().setLock(true);
				rockMan.setSpeedX(0);
				rockMan.setSpeedY(0);

				if (getRockMan().getX() - getCamera().getX() > 150) {
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
										ParticulerObjectMegamanX.DIR_LEFT);
								rectangleKhepManHinh2 = new RectangleClose(0, GameFrame.GAME_HEIGHT - 40, this,
										ParticulerObjectMegamanX.DIR_RIGHT);

								setSTAGE(listTutorial.get(0).getSTAGE());
								listTutorial.remove(0);

								getAction().getActionKeyForMegamanX().setLock(false);
								getCamera().unlock();

								if (listTutorial.size() > 0) {
									subBoss.setReady(true);
									subBoss.setTimeStartAction(System.currentTimeMillis());
								}
							}
						}
					}
				}

				listBullet.upload();
				break;

			case WINGAMESWITCHSTAGE:
				getRockMan().setStage(ParticulerObjectMegamanX.SWITCHMAPLESTORY);

				rockMan.upload();
				listBullet.upload();

				if (getRockMan().getBehurtLeft().getHeSo() > 0.005f) {
					getRockMan().getBehurtLeft().setHeSo(getRockMan().getBehurtLeft().getHeSo() - 0.005f);
					getRockMan().getBehurtRight().setHeSo(getRockMan().getBehurtRight().getHeSo() - 0.005f);
				}

				if (getRockMan().getY() < 1000) {
					if (drawRectangeEnd.getStage() == DrawRectangleOpacity.LOW) {

						try {
							ManagerSound.getInstance().stop("nhacNenMegamanX");
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							e.printStackTrace();
						}
						ManagerSound.getInstance().getListSound().get("nhacNenMapleStory").start();

						getController().getModel().setCurrentStage(2);
					}
				}

				break;

			case PLAYTUTORIALNOTRECTANGLE:

				getAction().getActionKeyForMegamanX().setLock(true);
				rockMan.setSpeedX(0);
				rockMan.setSpeedY(0);

				if (!listTutorial.get(0).isComplete()) {
					listTutorial.get(0).upload();
				} else if (listTutorial.get(0).isComplete()) {

					listTutorial.get(0).removeRectBackGround();
					listTutorial.get(0).upload();

					setSTAGE(listTutorial.get(0).getSTAGE());
					listTutorial.remove(0);

					getAction().getActionKeyForMegamanX().unlock();
					getCamera().unlock();

					float spaceX = 9342 - getRockMan().getX();
					float spaceY = 1000 - getRockMan().getY();

					getRockMan().setSpeedY(-1);

					float speedX = spaceX / spaceY;
					if (getRockMan().getX() > 9342) {
						if (speedX > 0) {
							speedX *= -1;
						}
					} else {
						if (speedX < 0) {
							speedX *= -1;
						}
					}
					getRockMan().setSpeedX(speedX);

					getAction().getActionKeyForMegamanX().lock();

				}
				break;

			case HOIHP:
				getThanhHpRockMan().setStart(false);

				if (thanhHpRockMan.getConSoLamChoRockManKhongTheUpLoad() == 0) {
					rockMan.upload();
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

				thanhHpRockMan.upload();
				getRockMan().getEffort().lock();

				break;
			case ENDGAME:

				listObject.upload();
				listBullet.upload();
				listWhenDeath.upLoad();
				listItem.upLoad();
				listDungCu.upload();

				break;

			case BOSSDEATH:

				listObject.upload();
				listBullet.upload();
				listWhenDeath.upLoad();
				listItem.upLoad();
				listDungCu.upload();

				getAction().getActionKeyForMegamanX().lock();

				break;

			case WINALLGAME:
				if (!ENDDDDDDDDDDDDDDDDDDD) {
					try {
						ManagerSound.getInstance().stopHieuUng("blackHole");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
						e.printStackTrace();
					}

					getCamera().lock();
					rockMan.setSpeedX(0);
					listObject.upload();
					listBullet.upload();

					if (getRockMan().isOutCamera()) {
						if (drawRectangeEnd.getColor() == Color.WHITE) {
							drawRectangeEnd.setChuyenStage(false);
							drawRectangeEnd.setColor(Color.BLACK);
							drawRectangeEnd.setNow(0);
							drawRectangeEnd.setStage(DrawRectangleOpacity.UP);
						} else {
							drawRectangeEnd.upload();
							if (drawRectangeEnd.getNow() == 255 && !ENDDDDDDDDDDDDDDDDDDD) {
								ENDDDDDDDDDDDDDDDDDDD = true;
							}
						}
					} else {
						if (!rockMan.isEndGame()) {
							if (rockMan.getSpeedY() == 0) {
								if (drawRectangeEnd.getStage() == DrawRectangleOpacity.UP) {
									whenDead.upload();
								}

								drawRectangeEnd.upload();

								if (drawRectangeEnd.getStage() == DrawRectangleOpacity.LOW) {
									if (!isRemoveBlackHole) {
										for (int i = 0; i < getListObject().getListObject().size(); i++) {
											if (getListObject().getListObject().get(i).getClass() == BlackHole.class) {
												getListObject().getListObject().remove(i);
												isRemoveBlackHole = true;
												break;
											}
										}
									}
								}

								if (drawRectangeEnd.getStage() == DrawRectangleOpacity.END) {
									if (isRemoveBlackHole && !rockMan.isEndGame()) {
										rockMan.setEndGame(true);
									}
								}
							}
						}
					}
				}
				break;
			default:
				break;
			}

		} else

		{
			rectangleKhepManHinh1.upload();
			rectangleKhepManHinh2.upload();
			if (rectangleKhepManHinh1.getCurrentSize() == 0) {
				isReady = true;
				rectangleKhepManHinh1 = new RectangleClose(0, 0, this, ParticulerObjectMegamanX.DIR_LEFT);
				rectangleKhepManHinh2 = new RectangleClose(0, GameFrame.GAME_HEIGHT - 40, this,
						ParticulerObjectMegamanX.DIR_RIGHT);
			}
		}

	}

	@Override
	public void draw(Graphics2D g2) {
		if (!ENDDDDDDDDDDDDDDDDDDD) {
			getBackGroundMap().draw(g2);
			frontMap.draw(g2);

			if (!isReady) {
				rectangleKhepManHinh1.draw(g2);
				rectangleKhepManHinh2.draw(g2);
			} else {

				listDungCu.draw(g2);
				listObject.draw(g2);
				listBullet.draw(g2);
				listWhenDeath.draw(g2);
				listItem.draw(g2);

				thanhHpRockMan.draw(g2);

				if (getSTAGE() == ENDGAME) {
					drawRectangeEnd.upload();
					drawRectangeEnd.draw(g2);
				}

				if (getSTAGE() == BOSSDEATH) {
					drawRectangeEnd.upload();
					drawRectangeEnd.draw(g2);
					if (drawRectangeEnd.getStage() == DrawRectangleOpacity.END) {
						getAction().getActionKeyForMegamanX().unlock();
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

				if (getSTAGE() == WINGAMESWITCHSTAGE && getRockMan().getY() < 1000) {
					drawRectangeEnd.upload();
					drawRectangeEnd.draw(g2);
				}
			}

			if (getSTAGE() == WINALLGAME) {
				drawRectangeEnd.draw(g2);
			}
		} else {
			g2.setColor(Color.black);
			g2.fillRect(0, 0, 1000, 600);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 30));
			g2.drawString("Mùa xuân năm x676", 100, yDraw);
			g2.drawString("Ác mộng NightMare đã bị xóa xổ kèm theo di chứng là:' những", 100, yDraw + 60);
			g2.drawString("người bị nó dày vỏ cũng mất đi khoảng kí ức về chúng '", 100, yDraw + 90);
			g2.drawString("Ai đã là người anh hùng thầm lặng xóa bỏ nó khỏi thế giới", 100, yDraw + 150);
			g2.drawString("này? Một người anh hùng thầm lặng,... Tên anh ấy ư? Đến TÔI", 100, yDraw + 180);
			g2.drawString("còn không biết ...", 100, yDraw + 210);
			g2.drawString("..........Lưu bút.........", 450, yDraw + 270);
			g2.drawString("- NIGHT MARE- Tôi sẽ quay lại vào tương lai ..hahahaha", 220, yDraw + 300);

			if (yDraw > -310) {
				if (System.nanoTime() - timeStarEnd > 10000000) {
					timeStarEnd = System.nanoTime();
					yDraw--;
				}
			} else {
				if (!isVeMenu) {
					try {
						getController().getGame().getGame().veMenu();
					} catch (IOException e) {
						e.printStackTrace();
					}
					isVeMenu = true;
				}
			}
		}
	}

	public RockMan getRockMan() {
		return rockMan;
	}

	public void setRockMan(RockMan rockMan) {
		this.rockMan = rockMan;
	}

	public BulletManagerMegamanX getListBullet() {
		return listBullet;
	}

	public void setListBullet(BulletManagerMegamanX listBullet) {
		this.listBullet = listBullet;
	}

	public ParticulerManagerMegamanX getListObject() {
		return listObject;
	}

	public void setListObject(ParticulerManagerMegamanX listObject) {
		this.listObject = listObject;
	}

	public WhenDeathManager getListWhenDeath() {
		return listWhenDeath;
	}

	public void setListWhenDeath(WhenDeathManager listWhenDeath) {
		this.listWhenDeath = listWhenDeath;
	}

	public ItemManager getListItem() {
		return listItem;
	}

	public void setListItem(ItemManager listItem) {
		this.listItem = listItem;
	}

	public FrontMap getFrontMap() {
		return frontMap;
	}

	public void setFrontMap(FrontMap frontMap) {
		this.frontMap = frontMap;
	}

	public ToolManager getListDungCu() {
		return listDungCu;
	}

	public void setListDungCu(ToolManager listDungCu) {
		this.listDungCu = listDungCu;
	}

	public RockManHealthBar getThanhHpRockMan() {
		return thanhHpRockMan;
	}

	public void setThanhHpRockMan(RockManHealthBar thanhHpRockMan) {
		this.thanhHpRockMan = thanhHpRockMan;
	}

	public static int getNormal() {
		return NORMAL;
	}

	public static int getPause() {
		return PAUSE;
	}

	public static int getEndgame() {
		return ENDGAME;
	}

	public boolean isiStartGame() {
		return iStartGame;
	}

	public void setiStartGame(boolean iStartGame) {
		this.iStartGame = iStartGame;
	}

	public static int getHoihp() {
		return HOIHP;
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

	public List<Tutorial> getListTutorial() {
		return listTutorial;
	}

	public void setListTutorial(List<Tutorial> listTutorial) {
		this.listTutorial = listTutorial;
	}

	public static int getAttackboss() {
		return ATTACKBOSS;
	}

	public static int getPlaytutorial() {
		return PLAYTUTORIAL;
	}

	public SubBossIntroStage getSubBoss() {
		return subBoss;
	}

	public void setSubBoss(SubBossIntroStage subBoss) {
		this.subBoss = subBoss;
	}

	public List<Integer> getListSetStageAttackBoss() {
		return listSetStageAttackBoss;
	}

	public void setListSetStageAttackBoss(List<Integer> listSetStageAttackBoss) {
		this.listSetStageAttackBoss = listSetStageAttackBoss;
	}

	public DrawRectangleOpacity getDrawRectangeEnd() {
		return drawRectangeEnd;
	}

	public void setDrawRectangeEnd(DrawRectangleOpacity drawRectangeEnd) {
		this.drawRectangeEnd = drawRectangeEnd;
	}

	public BossIntroStage getBoss() {
		return boss;
	}

	public void setBoss(BossIntroStage boss) {
		this.boss = boss;
	}

	public static int getBossdeath() {
		return BOSSDEATH;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public boolean isRemoveBlackHole() {
		return isRemoveBlackHole;
	}

	public void setRemoveBlackHole(boolean isRemoveBlackHole) {
		this.isRemoveBlackHole = isRemoveBlackHole;
	}

	public WhenBossDeath getWhenDead() {
		return whenDead;
	}

	public void setWhenDead(WhenBossDeath whenDead) {
		this.whenDead = whenDead;
	}

}
