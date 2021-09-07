package tool;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;

public class Tool extends GameObjectMegamanX {

	private int width;
	private int height;

	public Tool(float x, float y, GameWorldMegamanX game, int height, int width) {
		super(x, y, game);

		this.width = width;
		this.height = height;
	}

	@Override
	public void upload() {
	}
	
	public boolean isOutCamera() {
		if (getGame().getCamera().getX() + getGame().getCamera().getWidth() < getX())
			return true;
		if (getX() + getWidth() * 2 < getGame().getCamera().getX())
			return true;
		if (getGame().getCamera().getY() > getY() + getHeight() * 2)
			return true;
		if (getGame().getCamera().getY() + getGame().getCamera().getHeight() < getY())
			return true;
		return false;
	}

	public void draw(Graphics2D g2) {
	}

	public Rectangle isVaCham(Rectangle rect) {
		if (rect.intersects(getRectangleXuLyVaCham()))
			return getRectangleXuLyVaCham();
		else
			return null;
	}

	public Rectangle getRectangleXuLyVaCham() {
		return new Rectangle((int) getX(), (int) getY(), width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
