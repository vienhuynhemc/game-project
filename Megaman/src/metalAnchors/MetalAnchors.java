package metalAnchors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import actionForHuman.Grounding;
import actionForHuman.Jump;
import actionForHuman.Run;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.HunmanMegamanX;
import sound.ManagerSound;
import view.GameFrame;

public class MetalAnchors extends HunmanMegamanX implements Run, Jump, Grounding {

	private Animation left, right;

	private boolean isChamTuong;
	private long timeChamTuong;
	private long timeStartChamTuong;
	private int countChamTuong;

	private boolean isReady;

	public MetalAnchors(float x, float y, GameWorldMegamanX game, float xDen, float yDen) {
		super(x, y, game, 0.1f, 66, 66, 20, 5);

		left = new Animation(getGame().getInputData().getListAnimation().get("moNeoLeft"));
		right = new Animation(getGame().getInputData().getListAnimation().get("moNeoRight"));

		if (xDen > getX()) {
			setDirector(DIR_RIGHT);
		} else {
			setDirector(DIR_LEFT);
		}

		timeChamTuong = 2500;

		setTeam(ENEMI_TEAM);

		initSpeed(xDen, yDen);
	}

	@Override
	public void upload() {

		if (countChamTuong == 10
				|| (getRectangleXuLiVaCham().intersects(getGame().getRockMan().getRectangleXuLiVaCham())
						&& getGame().getRockMan().getStage() != NOBEHURT)) {
			setBlood(0);
		}

		if (getX() >= getGame().getCamera().getLimitX2() + GameFrame.GAME_WIDTH - 100) {
			setSpeedX(0);
		}

		if (!isReady) {
			if (!isChamTuong) {
				setMass(0f);
			} else {

				if (System.currentTimeMillis() - timeStartChamTuong > timeChamTuong) {
					setMass(0.1f);
					isReady = true;
				}
			}
		} else {

			run();
			jump();

			int limitX = 8750;
			int limitX2 = getGame().getCamera().getLimitX2() + GameFrame.getGameWidth() - 150;

			if (getX() <= limitX && getDirector() == DIR_LEFT) {
				countChamTuong++;
				setDirector(DIR_RIGHT);
			}
			if (getX() >= limitX2 && getDirector() == DIR_RIGHT) {
				countChamTuong++;
				setDirector(DIR_LEFT);
			}
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

		if (getSpeedY() == 0) {
			tiepDat();
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (getSpeedX() != 0 && getSpeedY() != 0) {
			if (getDirector() == DIR_LEFT) {
				left.upload(System.nanoTime());
				left.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else if (getDirector() == DIR_RIGHT) {
				right.upload(System.nanoTime());
				right.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		} else {
			if (getDirector() == DIR_LEFT) {
				left.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else if (getDirector() == DIR_RIGHT) {
				right.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
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
	public void jump() {
		if (!getIsJumping()) {

			countChamTuong++;

			setY(getY() - 5);
			setSpeedY(-5);

			setIsJumping(true);
		}
	}

	@Override
	public void tiepDat() {
		setIsJumping(false);
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();

		return new Rectangle(rect.x + 5, rect.y + 5, rect.width - 10, rect.height - 10);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();

		g2.setColor(Color.YELLOW);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
		ManagerSound.getInstance().getListSound().get("bang").start();
		try {
			ManagerSound.getInstance().khoiTaoHieuUng("bang");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
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

	public Animation getLeft() {
		return left;
	}

	public void setLeft(Animation left) {
		this.left = left;
	}

	public Animation getRight() {
		return right;
	}

	public void setRight(Animation right) {
		this.right = right;
	}

	public boolean isChamTuong() {
		return isChamTuong;
	}

	public void setChamTuong(boolean isChamTuong) {
		this.isChamTuong = isChamTuong;
	}

	public long getTimeChamTuong() {
		return timeChamTuong;
	}

	public void setTimeChamTuong(long timeChamTuong) {
		this.timeChamTuong = timeChamTuong;
	}

	public long getTimeStartChamTuong() {
		return timeStartChamTuong;
	}

	public void setTimeStartChamTuong(long timeStartChamTuong) {
		this.timeStartChamTuong = timeStartChamTuong;
	}

	public int getCountChamTuong() {
		return countChamTuong;
	}

	public void setCountChamTuong(int countChamTuong) {
		this.countChamTuong = countChamTuong;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

}
