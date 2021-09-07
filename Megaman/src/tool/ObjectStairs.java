package tool;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameWorld.GameWorldMapleStory;
import particulerObject.ParticulerObjectMapleStory;

public class ObjectStairs extends ParticulerObjectMapleStory {

	public ObjectStairs(float x, float y, GameWorldMapleStory game) {
		super(x, y, game, 0f, 102, 418, 100000, 0);

		setStage(BEHURT);
	}

	@Override
	public void draw(Graphics2D g2) {
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

}
