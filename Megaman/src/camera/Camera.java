package camera;

import gameObject.GameObject;
import gameObject.Upload;
import gameWorld.GameWorld;

public class Camera extends GameObject implements Upload {

	private int height;
	private int width;

	private int limitX, limitX2;
	private int limitY;

	private boolean isLock;

	private boolean isChanDong;
	private boolean isStartChanDong;
	private float toaDoBatDau;

	public Camera(float x, float y, GameWorld game) {
		super(x, y, game);
	}

	@Override
	public void upload() {

		if (isChanDong) {
			if (toaDoBatDau == 0) {
				isChanDong = false;
				isStartChanDong = false;
			} else {
				setX(getX() + toaDoBatDau);
				if (toaDoBatDau == 20 && !isStartChanDong) {
					isStartChanDong = true;
					toaDoBatDau = -(toaDoBatDau + toaDoBatDau - 1);
				} else {
					if (toaDoBatDau > 0) {
						toaDoBatDau = -(toaDoBatDau - 1);
					} else {
						toaDoBatDau = -(toaDoBatDau + 1);
					}
				}
			}
		}
	}

	public void chanDong() {
		if (!isChanDong) {
			isChanDong = true;
			toaDoBatDau = 20;
		}
	}

	public void lock() {
		isLock = true;
	}

	public void unlock() {
		isLock = false;
	}

	public int getLimitX() {
		return limitX;
	}

	public void setLimitX(int limitX) {
		this.limitX = limitX;
	}

	public int getLimitX2() {
		return limitX2;
	}

	public void setLimitX2(int limitX2) {
		this.limitX2 = limitX2;
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

	public int getLimitY() {
		return limitY;
	}

	public void setLimitY(int limitY) {
		this.limitY = limitY;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
