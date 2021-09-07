package bee;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import metalAnchors.WhenMetalAnchorsDeath;
import particulerObject.ParticulerObjectMegamanX;
import sound.ManagerSound;

public class LittleBee extends ParticulerObjectMegamanX {

	private Animation idleLeft, idleRight;
	private Animation attackLeft, attackRight;

	private boolean isShooting;

	public LittleBee(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0f, 58, 42, 30, 5);

		idleLeft = new Animation(getGame().getInputData().getListAnimation().get("littleBeeIdle"));
		idleRight = new Animation(getGame().getInputData().getListAnimation().get("littleBeeIdle"));
		idleRight.daoNguoc();
		attackLeft = new Animation(getGame().getInputData().getListAnimation().get("littleBeeAttack"));
		attackRight = new Animation(getGame().getInputData().getListAnimation().get("littleBeeAttack"));
		attackRight.daoNguoc();

		setTeam(ENEMI_TEAM);

	}

	@Override
	public void upload() {
		super.upload();

		if (getX() > getGame().getRockMan().getX()) {
			setDirector(DIR_LEFT);
		} else {
			setDirector(DIR_RIGHT);
		}

		if (getRectangleXuLiVaCham().intersects(getGame().getRockMan().getRectangleXuLiVaCham())
				&& getGame().getRockMan().getStage() != NOBEHURT) {
			setBlood(0);
		}

		if (getY() != getGame().getRockMan().getY()) {
			isShooting = false;
			if (getY() < getGame().getRockMan().getY()) {
				setY(getY() + 1);
			} else {
				setY(getY() - 1);
			}
		} else {
			isShooting = true;
			if (getDirector() == DIR_LEFT) {
				setX(getX() - 3);
			} else {
				setX(getX() + 3);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (!isShooting) {
			if (getDirector() == DIR_LEFT) {
				idleLeft.upload(System.nanoTime());
				idleLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else {
				idleRight.upload(System.nanoTime());
				idleRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}

		} else {

			if (getDirector() == DIR_LEFT) {
				attackLeft.upload(System.nanoTime());
				attackLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else {
				attackRight.upload(System.nanoTime());
				attackRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}

		}

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
		g2.drawRect((int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()),
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
		WhenMetalAnchorsDeath whenDeath = new WhenMetalAnchorsDeath(getX(), getY(), getGame());
		getGame().getListWhenDeath().addWhenDeath(whenDeath);
	}

	public Animation getIdleLeft() {
		return idleLeft;
	}

	public void setIdleLeft(Animation idleLeft) {
		this.idleLeft = idleLeft;
	}

	public Animation getIdleRight() {
		return idleRight;
	}

	public void setIdleRight(Animation idleRight) {
		this.idleRight = idleRight;
	}

	public Animation getAttackLeft() {
		return attackLeft;
	}

	public void setAttackLeft(Animation attackLeft) {
		this.attackLeft = attackLeft;
	}

	public Animation getAttackRight() {
		return attackRight;
	}

	public void setAttackRight(Animation attackRight) {
		this.attackRight = attackRight;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

}
