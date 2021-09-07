package gameObject;

import java.awt.Graphics2D;

import gameEffect.Animation;

public class LongDepTrai {

	private Animation anime1, anime2;
	private int x;
	private int y;

	private int type;

	public LongDepTrai(GameWorld game, int x, int y, int type) {
		this.x = x;
		this.y = y;

		this.type = type;

		anime1 = new Animation(game.getInputData().getListAnimation().get("longDepTrai"));
		anime1.setHeSo(0.25f);

		anime2 = new Animation(game.getInputData().getListAnimation().get("smoke"));
		anime2.setHeSo(0.25f);
	}

	public void upload() {
		if (type == 1) {
			anime1.upload(System.nanoTime());
		} else {
			anime2.upload(System.nanoTime());
		}
	}

	public void draw(Graphics2D g2) {
		if (type == 1) {
			anime1.draw(g2, x, y);
		} else {
			anime2.draw(g2, x, y);
		}
	}

	public Animation getAnime() {
		if (type == 1) {
			return anime1;
		} else {
			return anime2;
		}
	}

}
