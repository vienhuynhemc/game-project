package camera;

import gameWorld.GameWorldMegamanX;
import view.GameFrame;

public class CameraMegamanX extends Camera {

	private GameWorldMegamanX game;

	public CameraMegamanX(float x, float y, GameWorldMegamanX game, int height, int width) {
		super(x, y, game);

		this.game = game;

		setHeight(height);
		setWidth(width);

		lock();

		setLimitX(520);
		setLimitX2(8736);
	}
	
	@Override
	public void upload() {
		super.upload();
		
		int spaceX = (int) (getGame().getRockMan().getX() - getX());
		int spaceY = GameFrame.GAME_HEIGHT / 4 - 22;

		if (!isLock()) {
			if (!getGame().getRockMan().isLeoCauThang()) {
				if (spaceX > GameFrame.GAME_WIDTH / 2 && getGame().getRockMan().getSpeedX() > 0) {
					if (getX() < getLimitX2()) {
						setX(getX() + getGame().getRockMan().getSpeedX());
					}
				} else if (getGame().getRockMan().getSpeedX() < 0 && ((getX() + getWidth())
						- (getGame().getRockMan().getX() + getGame().getRockMan().getWidth())) > 500) {
					if (getX() > getLimitX()) {
						setX(getX() + getGame().getRockMan().getSpeedX());
					}
				}
				if (getGame().getRockMan().getY() - getY() < spaceY) {
					if (getGame().getRockMan().getSpeedY() < 0)
						setY(getY() + getGame().getRockMan().getSpeedY());
					else {
						if (getGame().getRockMan().getY() - getY() < spaceY) {
							setY(getY() - 1);
						}
					}
				}
				if ((getY() + getHeight())
						- (getGame().getRockMan().getY() + getGame().getRockMan().getHeight()) < spaceY) {
					if (getGame().getRockMan().getSpeedY() > 0)
						setY(getY() + getGame().getRockMan().getSpeedY());
				}
				if (getGame().getRockMan().getSpeedY() == 0) {
					int gamePerfert = (int) ((getY() + getHeight()) - spaceY) - 38;
					int space = (int) ((getGame().getRockMan().getY() + getGame().getRockMan().getHeight()) - gamePerfert);
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
			} else if (getGame().getRockMan().isLeoCauThang()) {
				if (getGame().getRockMan().getX() > getWidth() / 2 + getX()) {
					setX(getX() + 3);
				}
				if (getGame().getRockMan().getX() < getWidth() / 2 + getX()) {
					setX(getX() - 3);
				}
				if (getGame().getRockMan().getY() > getHeight() / 2 + getY()) {
					setY(getY() + 3);
				}
				if (getGame().getRockMan().getY() < getHeight() / 2 + getY()) {
					setY(getY() - 3);
				}
			}

		}
	}

	@Override
	public GameWorldMegamanX getGame() {
		return game;
	}

	public void setGame(GameWorldMegamanX game) {
		this.game = game;
	}

}
