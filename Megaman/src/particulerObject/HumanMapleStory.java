package particulerObject;

import java.awt.Rectangle;

import gameWorld.GameWorldMapleStory;
import mainPlayerMapleStory.Mario;
import view.GameFrame;

public abstract class HumanMapleStory extends ParticulerObjectMapleStory {
	private boolean isJumping;
	private boolean isDicking;
	private boolean isLeoCauThang;

	public HumanMapleStory(float x, float y, GameWorldMapleStory game, float mass, float width, float height, int blood,
			int dame) {
		super(x, y, game, mass, width, height, blood, dame);
	}

	@Override
	public void upload() {
		super.upload();

		if (!isLeoCauThang) {
			if (getStage() == ALIVE || getStage() == NOBEHURT || getStage() == FEY
					|| (Mario.class == this.getClass() && getStage() == BEHURT)) {

				if (getGame().getPhysicalMap().vaChamBenTrai(getRectangleSizeObject()) != null) {
					setSpeedX(0);

					Rectangle rectTrai = getGame().getPhysicalMap().vaChamBenTrai(getRectangleSizeObject());
					setX(getX() + (rectTrai.x + rectTrai.width - getX()));
				} else if (getGame().getPhysicalMap().vaChamBenPhai(getRectangleSizeObject()) != null) {
					setSpeedX(0);

					Rectangle rectPhai = getGame().getPhysicalMap().vaChamBenPhai(getRectangleSizeObject());
					setX(rectPhai.x - getWidth());
				} else {
					Rectangle future = new Rectangle((int) (getX() + getSpeedX()), (int) getY(), (int) getWidth(),
							(int) getHeight());

					if (getDirector() == ParticulerObjectMegamanX.DIR_LEFT
							&& getGame().getPhysicalMap().vaChamBenTrai(future) == null) {
						setX(getX() + getSpeedX());
					} else if (getDirector() == ParticulerObjectMegamanX.DIR_RIGHT
							&& getGame().getPhysicalMap().vaChamBenPhai(future) == null
							&& getX() + getWidth() < getGame().getCamera().getLimitX2() + GameFrame.GAME_WIDTH - 20) {
						setX(getX() + getSpeedX());
					}
				}

				if (getGame().getPhysicalMap().vaChamMatDat(getRectangleSizeObject()) != null) {
					setSpeedY(0);

					Rectangle rectDuoi = getGame().getPhysicalMap().vaChamMatDat(getRectangleSizeObject());

					setY(rectDuoi.y - getHeight() + 1);

				} else if (getGame().getPhysicalMap().vaChamTrenDau(getRectangleSizeObject()) != null
						&& getGame().getPhysicalMap().vaChamBenTrai(getRectangleSizeObject()) == null) {
					setSpeedY(0);

					Rectangle rectTren = getGame().getPhysicalMap().vaChamTrenDau(getRectangleSizeObject());
					setY(rectTren.y + rectTren.height + 1);
				} else {
					setY(getY() + getSpeedY());
					setSpeedY(getSpeedY() + getMass());
					if (getSpeedY() > 5) {
						setSpeedY(5);
					}
				}

				if ((Mario.class == this.getClass() && getStage() == BEHURT)) {
					setSpeedX(0);
				}
			}
		}
	}

	public boolean getIsJumping() {
		return isJumping;
	}

	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public boolean getIsDicking() {
		return isDicking;
	}

	public void setDicking(boolean isDicking) {
		this.isDicking = isDicking;
	}

	public boolean isLeoCauThang() {
		return isLeoCauThang;
	}

	public void setLeoCauThang(boolean isLeoCauThang) {
		this.isLeoCauThang = isLeoCauThang;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

}
