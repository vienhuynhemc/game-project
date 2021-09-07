package mainPlayerMapleStory;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameWorld.GameWorldMapleStory;
import objectBullet.BulletMapleStory;
import sound.ManagerSound;

public class BulletDamLv3 extends BulletMapleStory {

	private long timeStart;

	public BulletDamLv3(float x, float y, GameWorldMapleStory game, int dir) {
		super(x, y, game, 0, 60, 60, 10, 90);
		// dame cũ là 30
		setSpeedX(0);
		setSpeedY(0);

		setDirector(dir);

		timeStart = System.nanoTime();

		setTeam(LEGUE_TEAM);
	}

	@Override
	public void upload() {
		super.upload();

		if (isPush()) {
			setDame(0);
			setBlood(0);
			getToDeath();
			setStage(DEATH);
		}

		if (System.nanoTime() - timeStart > 100000000) {
			setStage(DEATH);
			setTrung(true);
		}
	}

	@Override
	public void draw(Graphics2D g2) {
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		return getRectangleSizeObject();
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		drawRectangleXuLiVaCham(g2);
	}

	@Override
	public void getToDeath() {
		ManagerSound.getInstance().getListSound().get("punchTrung").start();
		try {
			ManagerSound.getInstance().khoiTaoHieuUng("punchTrung");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		if (getDirector() == DIR_LEFT) {
			getGame().getListEF().addEF(new WhenBulletDamLv1Dead(getX() + 5, getY() + 15, getGame()));
			getGame().getListEF().addEF(new WhenBulletDamLv1Dead(getX() + 10, getY() + 5, getGame()));
			getGame().getListEF().addEF(new WhenBulletDamLv1Dead(getX() + 15, getY(), getGame()));
			getGame().getListEF().addEF(new WhenBulletDamLv1Dead(getX() - 5, getY() - 5, getGame()));
		} else {
			getGame().getListEF().addEF(new WhenBulletDamLv1Dead(getX() + 30, getY() + 5, getGame()));
			getGame().getListEF().addEF(new WhenBulletDamLv1Dead(getX() + 10, getY() + 15, getGame()));
			getGame().getListEF().addEF(new WhenBulletDamLv1Dead(getX() + 20, getY(), getGame()));
			getGame().getListEF().addEF(new WhenBulletDamLv1Dead(getX() + 0, getY() - 5, getGame()));
		}
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

}
