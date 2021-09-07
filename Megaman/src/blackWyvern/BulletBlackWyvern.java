package blackWyvern;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;
import objectBullet.BulletMapleStory;
import sound.ManagerSound;

public class BulletBlackWyvern extends BulletMapleStory {

	private Animation al;

	private boolean isDrawRe;

	public BulletBlackWyvern(float x, float y, GameWorldMapleStory game, int dir) {
		super(x, y, game, 0, 440, 542, 1000, 15);

		al = new Animation(getGame().getInputData().getListAnimation().get("bwb"));
		al.setIsRepeat(false);

		setTeam(ENEMI_TEAM);
		setDirector(dir);

		if (getDirector() == DIR_LEFT) {

		} else {
			setX(getX() + 100);
		}
	}

	@Override
	public void upload() {
		super.upload();

		if (isTrung()) {
			setSpeedX(0);
			setSpeedY(0);
			setDame(0);

			if (!isDrawRe) {
				getToDeath();
				isDrawRe = true;
			}

			if (al.isLastFrame()) {
				setStage(DEATH);
			}
		}

		if (al.isLastFrame()) {
			setStage(DEATH);
		}

	}

	@Override
	public void draw(Graphics2D g2) {

		al.upload(System.nanoTime());

		if (al.getCurrentFrame() < 13) {
			al.draw(g2, (int) (getX() + 168 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else if (al.getCurrentFrame() < 15 || al.getCurrentFrame() == 20 || al.getCurrentFrame() == 21) {
			al.draw(g2, (int) (getX() + 144 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else if (al.getCurrentFrame() < 17) {
			al.draw(g2, (int) (getX() + 50 - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else {
			al.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
		}

		if (al.getCurrentFrame() == 13) {
			ManagerSound.getInstance().getListSound().get("trungDanSkill").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("trungDanSkill");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle r = getRectangleSizeObject();
		if (al.getCurrentFrame() < 15) {
			return new Rectangle(r.x + 5, r.y + 5, 0, 0);
		} else {
			return new Rectangle(r.x + 180, r.y + 10, r.width - 360, r.height - 10);
		}
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
		getGame().getListEF().addEF(new BulletBlackWyvernRemove(getGame().getMario().getX() - 60,
				getGame().getMario().getY() - 60, getGame()));
	}

}
