package loading;

import java.awt.Graphics2D;

import gameEffect.Animation;
import gameObject.Draw;
import gameObject.GameObjectLoading;
import gameWorld.GameWorldLoading;

public class Loading extends GameObjectLoading implements Draw {

	private Animation loading;
	private Animation sprite;

	public Loading(float x, float y, GameWorldLoading game) {
		super(x, y, game);

		loading = new Animation(getGame().getInputData().getListAnimation().get("loading"));
		sprite = new Animation(getGame().getInputData().getListAnimation().get("sprite"));
		loading.setHeSo(0.5f);
		sprite.setHeSo(0.5f);
	}

	@Override
	public void draw(Graphics2D g2) {
		loading.draw(g2, (int) getX(), (int) getY());
		sprite.upload(System.nanoTime());
		sprite.draw(g2, (int) getX() + 2, (int) getY() + 5);
	}

	public Animation getLoading() {
		return loading;
	}

	public void setLoading(Animation loading) {
		this.loading = loading;
	}

	public Animation getSprite() {
		return sprite;
	}

	public void setSprite(Animation sprite) {
		this.sprite = sprite;
	}

}
