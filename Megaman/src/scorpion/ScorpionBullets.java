package scorpion;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import actionForHuman.Run;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import metalAnchors.WhenMetalAnchorsDeath;
import particulerObject.HunmanMegamanX;
import sound.ManagerSound;
import view.GameFrame;

public class ScorpionBullets extends HunmanMegamanX implements Run {

	private Animation anime;

	private boolean isChamTuong;
	private long timeStartChamTuong;

	public ScorpionBullets(float x, float y, GameWorldMegamanX game, float xDen, float yDen) {
		super(x, y, game, 0f, 44, 42, 20, 5);

		anime = new Animation(getGame().getInputData().getListAnimation().get("bulletBoCap"));

		setTeam(ENEMI_TEAM);

		if (xDen > getX()) {
			setDirector(DIR_RIGHT);
		} else {
			setDirector(DIR_LEFT);
		}

		if (getX() >= getGame().getCamera().getLimitX2() + GameFrame.GAME_WIDTH - 100) {
			setX(getX() - getWidth() - 50);
		}

		initSpeed(xDen, yDen);
	}

	@Override
	public void upload() {

		if (getRectangleXuLiVaCham().intersects(getGame().getRockMan().getRectangleXuLiVaCham())
				&& getGame().getRockMan().getStage() != NOBEHURT) {
			setBlood(0);
		}

		if (getX() >= getGame().getCamera().getLimitX2() + GameFrame.GAME_WIDTH - 100) {
			setSpeedX(0);
			setSpeedY(0);
		}

		super.upload();

		if (getX() == 0 || getSpeedY() == 0) {
			if (!isChamTuong) {
				isChamTuong = true;
				timeStartChamTuong = System.currentTimeMillis();

				setSpeedX(0);
				setSpeedY(0);
			}
		}

		if (isChamTuong) {
			if (System.currentTimeMillis() - timeStartChamTuong > 2500) {
				ManagerSound.getInstance().getListSound().get("bang").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("bang");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
				setBlood(0);
			}
		}

	}

	@Override
	public void draw(Graphics2D g2) {

		if (getSpeedX() == 0 && getSpeedY() == 0) {
			anime.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		} else {
			anime.upload(System.nanoTime());
			anime.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		}

	}

	@Override
	public void run() {
		if (getDirector() == DIR_RIGHT) {
			setSpeedX(4);
		} else if (getDirector() == DIR_LEFT) {
			setSpeedX(-4);
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		return getRectangleSizeObject();
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();

		g2.setColor(Color.YELLOW);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
		WhenMetalAnchorsDeath whenDeath = new WhenMetalAnchorsDeath(getX(), getY(), getGame());
		getGame().getListWhenDeath().addWhenDeath(whenDeath);
	}

	private void initSpeed(float x, float y) {
		float spaceX = x - getX();
		float spaceY = y - getY();

		float boiSo = spaceX / 4;

		if (spaceX < 25 && spaceX > -25) {
			setSpeedX(0);
		} else if (spaceX < 50 && spaceX > -50) {
			if (getDirector() == DIR_RIGHT) {
				setSpeedX(1);
			} else if (getDirector() == DIR_LEFT) {
				setSpeedX(-1);
			}
		} else if (spaceX < 100 && spaceX > -100) {
			if (getDirector() == DIR_RIGHT) {
				setSpeedX(2);
			} else if (getDirector() == DIR_LEFT) {
				setSpeedX(-2);
			}
		} else if (spaceX < 150 && spaceX > -150) {
			if (getDirector() == DIR_RIGHT) {
				setSpeedX(3);
			} else if (getDirector() == DIR_LEFT) {
				setSpeedX(-3);
			}
		} else {
			if (getDirector() == DIR_RIGHT) {
				setSpeedX(4);
			} else if (getDirector() == DIR_LEFT) {
				setSpeedX(-4);
			}
		}

		float speedY = spaceY / boiSo;
		if (speedY < 0) {
			speedY *= -1;
		}

		setSpeedY(speedY);
	}

	public Animation getAnime() {
		return anime;
	}

	public void setAnime(Animation anime) {
		this.anime = anime;
	}

	public boolean isChamTuong() {
		return isChamTuong;
	}

	public void setChamTuong(boolean isChamTuong) {
		this.isChamTuong = isChamTuong;
	}

	public long getTimeStartChamTuong() {
		return timeStartChamTuong;
	}

	public void setTimeStartChamTuong(long timeStartChamTuong) {
		this.timeStartChamTuong = timeStartChamTuong;
	}

}
