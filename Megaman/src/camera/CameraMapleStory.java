package camera;

import gameWorld.GameWorldMapleStory;
import view.GameFrame;

public class CameraMapleStory extends Camera {

	private GameWorldMapleStory game;

	public CameraMapleStory(float x, float y, GameWorldMapleStory game, int width, int height) {
		super(x, y, game);

		this.game = game;

		setHeight(height);
		setWidth(width);
		setLimitX(130);
		setLimitX2(8720);
	}
	
	@Override
	public void upload() {
		super.upload();
		
		int spaceX = (int) (getGame().getMario().getX() - getX());
		int spaceY = GameFrame.GAME_HEIGHT / 4 - 22;

		if (!isLock()) {
			if (!getGame().getMario().isLeoCauThang()) {
				if (spaceX > GameFrame.GAME_WIDTH / 2 && getGame().getMario().getSpeedX() > 0) {
					if (getX() < getLimitX2()) {
						setX(getX() + getGame().getMario().getSpeedX());
					}
				} else if (getGame().getMario().getSpeedX() < 0 && ((getX() + getWidth())
						- (getGame().getMario().getX() + getGame().getMario().getWidth())) > 500) {
					if (getX() > getLimitX()) {
						setX(getX() + getGame().getMario().getSpeedX());
					}
				}
				if (getGame().getMario().getY() - getY() < spaceY) {
					if (getGame().getMario().getSpeedY() < 0)
						setY(getY() + getGame().getMario().getSpeedY());
					else {
						if (getGame().getMario().getY() - getY() < spaceY) {
							setY(getY() - 1);
						}
					}
				}
				if ((getY() + getHeight())
						- (getGame().getMario().getY() + getGame().getMario().getHeight()) < spaceY) {
					if (getGame().getMario().getSpeedY() > 0)
						setY(getY() + getGame().getMario().getSpeedY());
				}
				if (getGame().getMario().getSpeedY() == 0) {
					int gamePerfert = (int) ((getY() + getHeight()) - spaceY) - 38;
					int space = (int) ((getGame().getMario().getY() + getGame().getMario().getHeight()) - gamePerfert);
					if (space >= 3) {
						setY(getY() + 3);
					} else if (space > 0 && space < 3) {
						setY(getY() + space);
					}
					if (space <= -3) {
						setY(getY() - 3);
					} else if (space > -3 && space < 0) {
						setY(getY() + space);
					}
				}
			} else if (getGame().getMario().isLeoCauThang()) {
				if (getGame().getMario().getX() > getWidth() / 2 + getX()) {
					setX(getX() + 3);
				}
				if (getGame().getMario().getX() < getWidth() / 2 + getX()) {
					setX(getX() - 3);
				}
				if (getGame().getMario().getY() > getHeight() / 2 + getY()) {
					setY(getY() + 3);
				}
				if (getGame().getMario().getY() < getHeight() / 2 + getY()) {
					setY(getY() - 3);
				}
			}

		}
	}

	@Override
	public GameWorldMapleStory getGame() {
		return game;
	}

	public void setGame(GameWorldMapleStory game) {
		this.game = game;
	}

}
