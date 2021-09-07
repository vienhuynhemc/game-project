package item;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;
import particulerObject.ParticulerObjectMapleStory;

public class LargeHealItemMapleStory extends ItemMapleStory {

	private Animation idle;

	private int healHp;

	public LargeHealItemMapleStory(float x, float y, GameWorldMapleStory game) {
		super(x, y, game, 21, 29, 0.1f, 0f);

		idle = new Animation(getGame().getInputData().getListAnimation().get("il"));
		idle.setHeSo(1f);

		healHp = 30;
	}

	@Override
	public void upload() {
		super.upload();

		if (getGame().getMario().getRectangleXuLiVaCham().intersects(getRectangleXuLiVaCham())) {
			getGame().getMario().setBlood(getGame().getMario().getBlood() + healHp);
			if (getGame().getMario().getBlood() > 100)
				getGame().getMario().setBlood(100);
			setComplete(true);
			getGame().setSTAGE(GameWorldMapleStory.HOIHP);
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		return new Rectangle((int) getX() + 4, (int) getY() + 3, getWidth() - 8, getHeight() - 6);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		g2.drawRect((int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()),
				getWidth(), getHeight());

		drawRectangleXuLiVaCham(g2);
	}

	@Override
	public void draw(Graphics2D g2) {

		if (getStage() == ParticulerObjectMapleStory.FEY && System.nanoTime() % 3 == 2) {
		} else {
			{
				idle.upload(System.nanoTime());
				idle.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		}
	}

	public Animation getIdle() {
		return idle;
	}

	public void setIdle(Animation idle) {
		this.idle = idle;
	}

	public int getHealHp() {
		return healHp;
	}

	public void setHealHp(int healHp) {
		this.healHp = healHp;
	}

}
