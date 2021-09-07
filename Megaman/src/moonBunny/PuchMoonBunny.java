package moonBunny;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameWorld.GameWorldMapleStory;
import objectBullet.BulletMapleStory;
import sound.ManagerSound;

public class PuchMoonBunny extends BulletMapleStory {

	private long timeStart;

	public PuchMoonBunny(float x, float y, GameWorldMapleStory game) {
		super(x, y, game, 0, 50, 50, 1000, 5);
		setSpeedX(0);
		setSpeedY(0);

		timeStart = System.nanoTime();

		setTeam(ENEMI_TEAM);
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
		drawRectangleSizeObject(g2);
	}

	@Override
	public void getToDeath() {
		ManagerSound.getInstance().getListSound().get("punchTrung").start();
		try {
			ManagerSound.getInstance().khoiTaoHieuUng("punchTrung");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		getGame().getListEF().addEF(new WhenPunchMoonBunnyDead(getX() - 70, getY() - 60, getGame()));
	}

}
