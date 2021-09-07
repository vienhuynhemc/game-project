package backGroundMap;

import java.awt.Graphics2D;

import gameEffect.Animation;
import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;

public class AnimationBackGroundMap extends GameObjectMegamanX {

	private Animation anime;

	private int width;
	private int height;

	public AnimationBackGroundMap(float x, float y, GameWorldMegamanX game, Animation anime, int width, int height) {
		super(x, y, game);

		this.width = width;
		this.height = height;
		this.anime = anime;
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

	public boolean isOutCameraBackEnd() {
		if (getGame().getCamera().getX() + getGame().getCamera().getWidth() < getX() * 2)
			return true;
		if (getX() * 2 + getWidth() * 2 < getGame().getCamera().getX()) {
			return true;
		}
		if (getGame().getCamera().getY() > getY() + getHeight() * 2)
			return true;
		if (getGame().getCamera().getY() + getGame().getCamera().getHeight() < getY())
			return true;
		return false;
	}

	public void draw(Graphics2D g2) {
		anime.upload(System.nanoTime());
		anime.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
	}

	@Override
	public void upload() {
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Animation getAnime() {
		return anime;
	}

	public void setAnime(Animation anime) {
		this.anime = anime;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
