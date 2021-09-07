package frontMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;

public class FrontMapItem extends GameObjectMegamanX {

	private BufferedImage image;

	public FrontMapItem(float x, float y, GameWorldMegamanX game, BufferedImage image) {
		super(x, y, game);

		this.image = image;
	}

	public boolean isOutCamera() {
		if (getGame().getCamera().getX() + getGame().getCamera().getWidth() < getX())
			return true;
		if (getX() + getWidth() * 2 < getGame().getCamera().getX()) {
			return true;
		}
		if (getGame().getCamera().getY() > getY() + getHeight() * 2)
			return true;
		if (getGame().getCamera().getY() + getGame().getCamera().getHeight() < getY())
			return true;
		return false;
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(image, (int) (getX() - getGame().getCamera().getX()),
				(int) (getY() - getGame().getCamera().getY() ), getWidth() * 2, getHeight() * 2, null);
	}

	@Override
	public void upload() {
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
