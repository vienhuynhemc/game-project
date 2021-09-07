package whenDeath;

import java.awt.Graphics2D;
import java.io.IOException;

import gameEffect.Animation;
import gameObject.Draw;
import gameObject.GameObjectMapleStory;
import gameObject.Upload;
import gameWorld.GameWorldMapleStory;

public class WhenDeadMotLan extends GameObjectMapleStory implements Draw, Upload {

	private Animation anime;
	private boolean isComplete;

	private long timeStart;
	private boolean isStart;
	private boolean isGanBienMat;

	public WhenDeadMotLan(float x, float y, GameWorldMapleStory game, Animation anime) {
		super(x, y, game);
		this.anime = anime;
	}

	@Override
	public void upload() throws IOException {

		if (isGanBienMat) {
			if (System.nanoTime() - timeStart > 2000000000l) {
				isComplete = true;
			}
		} else {
			if (isStart) {
				if (System.nanoTime() - timeStart > 5000000000l) {
					isGanBienMat = true;
					timeStart = System.nanoTime();
				}
			} else {
				anime.upload(System.nanoTime());
				if (anime.isLastFrame()) {
					if (!isStart) {
						isStart = true;
						timeStart = System.nanoTime();
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (isGanBienMat) {
			if (System.nanoTime() % 3 == 2) {
			} else {
				anime.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		} else {
			anime.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		}
	}

	public Animation getAnime() {
		return anime;
	}

	public void setAnime(Animation anime) {
		this.anime = anime;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isGanBienMat() {
		return isGanBienMat;
	}

	public void setGanBienMat(boolean isGanBienMat) {
		this.isGanBienMat = isGanBienMat;
	}

}
