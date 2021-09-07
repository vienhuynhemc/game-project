package bain;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;
import objectBullet.BulletMapleStory;
import sound.ManagerSound;

public class BulletBain extends BulletMapleStory {

	private Animation al, ar;

	public BulletBain(float x, float y, GameWorldMapleStory game, int dir) {
		super(x, y, game, 0, 64, 64, 1000, 15);

		al = new Animation(getGame().getInputData().getListAnimation().get("bb"));
		ar = new Animation(getGame().getInputData().getListAnimation().get("bb"));
		ar.daoNguoc();

		setTeam(ENEMI_TEAM);
		setDirector(dir);

		if (getDirector() == DIR_LEFT) {
			setSpeedX(-10);
		} else {
			setX(getX() + 100);
			setSpeedX(10);
		}
	}

	@Override
	public void upload() {
		super.upload();

		if (isTrung()) {
			setSpeedX(0);
			setSpeedY(0);
			getToDeath();
			setStage(DEATH);
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (getDirector() == DIR_LEFT) {
			al.upload(System.nanoTime());
			al.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
		} else {
			ar.upload(System.nanoTime());
			ar.draw(g2, (int) (getX() - 50 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		}

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle r = getRectangleSizeObject();
		return new Rectangle(r.x + 5, r.y + 5, r.width - 10, r.height - 10);
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
		if (getDirector() == DIR_LEFT) {
			getGame().getListEF().addEF(new BulletBainRemoveLeft(getX() - 60, getY() - 60, getGame()));
		} else {
			getGame().getListEF().addEF(new BulletBainRemoveRight(getX() - 60, getY() - 60, getGame()));
		}
	}

}
