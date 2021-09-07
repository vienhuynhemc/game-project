package introEnemieFlySmall;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import item.LargeHealItem;
import item.SmallHealItem;
import particulerObject.ParticulerObjectMegamanX;
import sound.ManagerSound;

public class IntroEnemieFlySmall extends ParticulerObjectMegamanX {

	private Animation thanTren, thanDuoi;
	private Animation attack;

	private String[] listAction;
	private HashMap<String, Integer> listTimeForAction;

	private int currentAction;

	private long timeStart;

	private Random rd;

	private int directorRun;

	private boolean isShooting;

	public IntroEnemieFlySmall(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0f, 58, 62, 30, 5);

		thanTren = new Animation(getGame().getInputData().getListAnimation().get("introEnemieFlySmallThanTren"));
		thanDuoi = new Animation(getGame().getInputData().getListAnimation().get("introEnemieFlySmallThanDuoi"));
		attack = new Animation(getGame().getInputData().getListAnimation().get("introEnemieFlySmallAttack"));

		rd = new Random();

		listAction = new String[4];
		listAction[0] = "NONE";
		listAction[1] = "RUN";
		listAction[2] = "ATTACK";
		listAction[3] = "RUNOUT";

		setCurrentAction(0);

		listTimeForAction = new HashMap<String, Integer>();
		listTimeForAction.put("NONE", 1000);
		listTimeForAction.put("RUN", 4000);
		listTimeForAction.put("ATTACK", 1200);
		listTimeForAction.put("RUNOUT", 2000);

		setTeam(ENEMI_TEAM);
		setDirector(DIR_LEFT);
		directorRun = DIR_LEFT;

		setxPrimaral(getX());
		setyPrimaral(getY());
		setBloodPrimaral(getBlood());
		setDirectorPrimaral(getDirector());

		setRd(new Random());
	}

	public void none() {
		setShooting(false);
		setSpeedX(0);
		setSpeedY(0);
	}

	public void run() {
		if (getDirector() == DIR_LEFT) {
			setSpeedX(-2);
		} else {
			setSpeedX(2);
		}
		if (getDirectorRun() == DIR_LEFT) {
			setSpeedY(2);
		} else {
			setSpeedY(-2);
		}
	}

	public void attack() {
		if (!isShooting) {
			setShooting(true);
			setSpeedX(0);
			setSpeedY(0);

			IntroEnemieFlySmallBullet bullet1 = new IntroEnemieFlySmallBullet(getX() - 14, getY() + 32, getGame(),
					DIR_LEFT);
			IntroEnemieFlySmallBullet bullet2 = new IntroEnemieFlySmallBullet(getX() + 44, getY() + 32, getGame(),
					DIR_RIGHT);

			getGame().getListBullet().addObject(bullet1);
			getGame().getListBullet().addObject(bullet2);
		}
	}

	public void runOut() {
		if (getDirector() == DIR_LEFT) {
			setSpeedX(1);
		} else {
			setSpeedX(-1);
		}
		if (getDirectorRun() == DIR_LEFT) {
			setSpeedY(-1);
		} else {
			setSpeedY(1);
		}
	}

	@Override
	public void upload() {

		super.upload();

		if (timeStart == 0) {
			timeStart = System.currentTimeMillis();
		}

		setDirectorAndLocationWhenAddSpeed();

		if (getCurrentAction() == 0) {
			none();
			if (System.currentTimeMillis() - timeStart > listTimeForAction.get("NONE")) {
				currentAction++;
				timeStart = System.currentTimeMillis();
			}

		} else if (getCurrentAction() == 1) {
			run();
			int spaceY = (int) (getY() - getGame().getRockMan().getY());
			int spaceX = (int) (getX() - getGame().getRockMan().getX());
			if (spaceY > -50 && spaceY < 50 && spaceX > -50 && spaceX < 50) {
				currentAction++;
				timeStart = System.currentTimeMillis();
			}
			if (System.currentTimeMillis() - timeStart > listTimeForAction.get("RUN")) {
				currentAction++;
				timeStart = System.currentTimeMillis();
			}
		} else if (getCurrentAction() == 2) {
			int spaceY = (int) (getY() - getGame().getRockMan().getY());
			int spaceX = (int) (getX() - getGame().getRockMan().getX());
			if (spaceY > -50 && spaceY < 50 && spaceX > -50 && spaceX < 50) {
				attack();
				if (System.currentTimeMillis() - timeStart > listTimeForAction.get("ATTACK")) {
					currentAction++;
					timeStart = System.currentTimeMillis();
				}
			} else if (!isShooting) {
				currentAction++;
				timeStart = System.currentTimeMillis();
			} else if (isShooting) {
				if (System.currentTimeMillis() - timeStart > listTimeForAction.get("ATTACK")) {
					currentAction++;
					timeStart = System.currentTimeMillis();
				}
			}
		} else if (getCurrentAction() == 3) {
			if (isShooting) {
				runOut();
				if (System.currentTimeMillis() - timeStart > listTimeForAction.get("RUNOUT")) {
					currentAction = 0;
					timeStart = System.currentTimeMillis();
				}
			} else {
				currentAction = 0;
				timeStart = System.currentTimeMillis();
			}
		}

	}

	public void setDirectorAndLocationWhenAddSpeed() {
		setX(getX() + getSpeedX());
		setY(getY() + getSpeedY());

		if (getX() < getGame().getRockMan().getX()) {
			setDirector(DIR_RIGHT);
		} else {
			setDirector(DIR_LEFT);
		}
		if (getY() > getGame().getRockMan().getY()) {
			setDirectorRun(DIR_RIGHT);
		} else {
			setDirectorRun(DIR_LEFT);
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 11, rect.y + 16, rect.width - 22, rect.height - 16);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();
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
		WhenIntroEnemieFlySmallDeath whenDeath = new WhenIntroEnemieFlySmallDeath(getX(), getY(), getGame());
		getGame().getListWhenDeath().addWhenDeath(whenDeath);
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

	@Override
	public void draw(Graphics2D g2) {

		drawThanTren(g2);
		if (currentAction != 2) {
			drawThanDuoi(g2);
		} else {
			attack.upload(System.nanoTime());
			attack.draw(g2, (int) (getX() + 14 - getGame().getCamera().getX()),
					(int) (getY() + 16 - getGame().getCamera().getY()));
		}

	}

	public void drawThanDuoi(Graphics2D g2) {
		thanDuoi.upload(System.nanoTime());
		if (thanDuoi.getCurrentFrame() == 1) {
			thanDuoi.draw(g2, (int) (getX() + 4 - getGame().getCamera().getX()),
					(int) (getY() + 16 - getGame().getCamera().getY()));
		} else {
			thanDuoi.draw(g2, (int) (getX() + 7 - getGame().getCamera().getX()),
					(int) (getY() + 16 - getGame().getCamera().getY()));
		}
	}

	public void drawThanTren(Graphics2D g2) {
		thanTren.upload(System.nanoTime());
		if (thanTren.getCurrentFrame() == 1 || thanTren.getCurrentFrame() == 4) {
			thanTren.draw(g2, (int) (getX() + 11 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else if (thanTren.getCurrentFrame() == 2 || thanTren.getCurrentFrame() == 3) {
			thanTren.draw(g2, (int) (getX() + 18 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else {
			thanTren.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		}
	}

	public Animation getThanTren() {
		return thanTren;
	}

	public void setThanTren(Animation thanTren) {
		this.thanTren = thanTren;
	}

	public Animation getThanDuoi() {
		return thanDuoi;
	}

	public void setThanDuoi(Animation thanDuoi) {
		this.thanDuoi = thanDuoi;
	}

	public Animation getAttack() {
		return attack;
	}

	public void setAttack(Animation attack) {
		this.attack = attack;
	}

	public String[] getListAction() {
		return listAction;
	}

	public void setListAction(String[] listAction) {
		this.listAction = listAction;
	}

	public HashMap<String, Integer> getListTimeForAction() {
		return listTimeForAction;
	}

	public void setListTimeForAction(HashMap<String, Integer> listTimeForAction) {
		this.listTimeForAction = listTimeForAction;
	}

	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

	public int getDirectorRun() {
		return directorRun;
	}

	public void setDirectorRun(int directorRun) {
		this.directorRun = directorRun;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

}
