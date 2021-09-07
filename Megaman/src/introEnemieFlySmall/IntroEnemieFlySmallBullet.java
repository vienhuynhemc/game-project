package introEnemieFlySmall;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import objectBullet.BulletMegamanX;

public class IntroEnemieFlySmallBullet extends BulletMegamanX {

	private Animation banLeft, banRight;

	private int director;

	public IntroEnemieFlySmallBullet(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0f, 28, 16, 10000, 5);

		this.director = director;

		banLeft = new Animation(getGame().getInputData().getListAnimation().get("introEnemieFlySmallBullet"));
		banLeft.daoNguoc();
		banRight = new Animation(getGame().getInputData().getListAnimation().get("introEnemieFlySmallBullet"));

		setTeam(ENEMI_TEAM);
	}

	@Override
	public void upload() {
		super.upload();

		if (isTrung()) {
			setDame(0);
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (director == DIR_LEFT) {
			drawAndUploadHanhDong(g2, banLeft);
			if (banLeft.isLastFrame())
				setStage(DEATH);
		} else {
			drawAndUploadHanhDong(g2, banRight);
			if (banRight.isLastFrame())
				setStage(DEATH);
		}

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		if (director == DIR_LEFT) {
			if (banLeft.getCurrentFrame() == 0 || banLeft.getCurrentFrame() == 5) {
				return new Rectangle(rect.x + 16, rect.y + 4, rect.width - 16, rect.height - 6);
			} else {
				return rect;
			}
		} else {
			if (banRight.getCurrentFrame() == 0 || banRight.getCurrentFrame() == 5) {
				return new Rectangle(rect.x, rect.y + 4, rect.width - 16, rect.height - 6);
			} else {
				return rect;
			}
		}
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	public boolean isLastFrame() {
		if (director == DIR_LEFT) {
			return banLeft.isLastFrame();
		} else {
			return banRight.isLastFrame();
		}
	}

	@Override
	public void getToDeath() {
	}

	public Animation getBanLeft() {
		return banLeft;
	}

	public void setBanLeft(Animation banLeft) {
		this.banLeft = banLeft;
	}

	public Animation getBanRight() {
		return banRight;
	}

	public void setBanRight(Animation banRight) {
		this.banRight = banRight;
	}

	public int getDirector() {
		return director;
	}

	public void setDirector(int director) {
		this.director = director;
	}

}
