package subBossIntroStage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import objectBullet.BulletMegamanX;

public class SubBossIntroStageBullet extends BulletMegamanX {

	private Animation ban;
	private Animation remove;

	public SubBossIntroStageBullet(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0.1f, 30, 30, 1000, 10);

		ban = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageBullet"));
		remove = new Animation(getGame().getInputData().getListAnimation().get("subBossIntroStageBulletRemove"));

		if (getGame().getSubBoss().getDirector() == DIR_LEFT) {
			setSpeedX(-10);
			setDirector(DIR_LEFT);
		} else if (getGame().getSubBoss().getDirector() == DIR_RIGHT) {
			setSpeedX(10);
			setDirector(DIR_RIGHT);
		}

		setTeam(ENEMI_TEAM);
	}

	@Override
	public void upload() {
		super.upload();

		if (isTrung()) {
			setDame(0);
			setSpeedX(0);
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (isChamTuong()) {
			setSpeedX(0);
			setDame(0);
			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY()+8 - getGame().getCamera().getY()));
			if (remove.isLastFrame()) {
				setStage(DEATH);
			}
		} else if (isTrung()) {
			remove.upload(System.nanoTime());
			remove.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() + 8 - getGame().getCamera().getY()));
			if (remove.isLastFrame()) {
				setStage(DEATH);
			}
		} else {
			ban.upload(System.nanoTime());
			ban.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
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
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()), 30,
				30);
	}

	@Override
	public void getToDeath() {
	}

	public Animation getBan() {
		return ban;
	}

	public void setBan(Animation ban) {
		this.ban = ban;
	}

	public Animation getRemove() {
		return remove;
	}

	public void setRemove(Animation remove) {
		this.remove = remove;
	}

}
