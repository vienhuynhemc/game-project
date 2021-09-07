package itemTutorial;

import java.awt.Color;
import java.awt.Graphics2D;

import gameObject.GameObject;
import gameObject.Upload;
import gameWorld.GameWorld;
import particulerObject.ParticulerObjectMegamanX;
import view.GameFrame;

public class RectangleClose extends GameObject implements Upload {

	public static final int MORONG = 0;
	public static final int KHEPLAI = 1;

	private int size;
	private int currentSize;

	private boolean isComplete;

	private int director;

	private int stage;

	private int count;

	public RectangleClose(float x, float y, GameWorld game, int director) {
		super(x, y, game);

		currentSize = 0;
		size = 100;

		stage = MORONG;

		count = 0;

		this.director = director;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect((int) getX(), (int) getY(), GameFrame.GAME_WIDTH, currentSize);
	}

	@Override
	public void upload() {

		if (stage == MORONG) {
			if (currentSize == size) {
				isComplete = true;
			}
		} else if (stage == KHEPLAI) {
			if (currentSize == 0) {
				isComplete = true;
			}
		}

		if (stage == MORONG) {
			if (director == ParticulerObjectMegamanX.DIR_LEFT) {
				if (currentSize != size) {
					currentSize++;
				}
			} else if (director == ParticulerObjectMegamanX.DIR_RIGHT) {
				if (currentSize != size) {
					currentSize++;
				}
				setY(getY() - 1);
			}
		} else if (stage == KHEPLAI) {
			if (director == ParticulerObjectMegamanX.DIR_LEFT) {
				if (currentSize != 0) {
					currentSize--;
				}
			} else if (director == ParticulerObjectMegamanX.DIR_RIGHT) {
				if (currentSize != 0) {
					currentSize--;
				}
				setY(getY() + 1);
			}
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public int getDirector() {
		return director;
	}

	public void setDirector(int director) {
		this.director = director;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static int getMorong() {
		return MORONG;
	}

	public static int getKheplai() {
		return KHEPLAI;
	}

}
