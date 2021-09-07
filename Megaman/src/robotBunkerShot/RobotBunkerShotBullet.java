package robotBunkerShot;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import objectBullet.BulletMegamanX;
import sound.ManagerSound;

public class RobotBunkerShotBullet extends BulletMegamanX {

	private Animation dan;
	private Animation remove;

	public RobotBunkerShotBullet(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f, 16, 16, 1000, 5);

		dan = new Animation(getGame().getInputData().getListAnimation().get("bulletRobotBanLoCot"));
		remove = new Animation(getGame().getInputData().getListAnimation().get("bigBangSmall"));

		setTeam(ENEMI_TEAM);
	}

	@Override
	public void upload() {
		super.upload();

		setSpeedY(getSpeedY() + getMass());

		if (isTrung()) {
			setDame(0);
			setSpeedX(0);
			setSpeedY(0);
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (isChamTuong()) {

			setDame(0);
			setSpeedX(0);
			setSpeedY(0);

			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - 60 - getGame().getCamera().getY()));

			if (remove.isLastFrame())
				setStage(DEATH);
		} else if (isTrung()) {

			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - 60 - getGame().getCamera().getY()));

			if (remove.isLastFrame()) {
				setStage(DEATH);
			}

		} else {
			drawAndUploadHanhDong(g2, dan);
		}

		if (remove.getCurrentFrame() == 0 && (isTrung() || isChamTuong())) {
			ManagerSound.getInstance().getListSound().get("bang").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("bang");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

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
	}

	public Animation getDan() {
		return dan;
	}

	public void setDan(Animation dan) {
		this.dan = dan;
	}

	public Animation getRemove() {
		return remove;
	}

	public void setRemove(Animation remove) {
		this.remove = remove;
	}

}
