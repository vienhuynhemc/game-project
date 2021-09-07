package blackHole;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;

public class BlackHole extends ParticulerObjectMegamanX {

	private Animation anime;

	public BlackHole(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 0f, 332, 332, 10000, 0);

		anime = getGame().getInputData().getListAnimation().get("blackHole");
	}

	@Override
	public void draw(Graphics2D g2) {
		anime.upload(System.nanoTime());
		anime.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		return getRectangleSizeObject();
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		drawRectangleSizeObject(g2);
	}

	@Override
	public void getToDeath() {
	}

	public Animation getAnime() {
		return anime;
	}

	public void setAnime(Animation anime) {
		this.anime = anime;
	}

}
