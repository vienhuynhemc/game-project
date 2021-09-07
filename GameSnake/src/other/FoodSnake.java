package other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import conRan.Point;
import gameObject.GameObject;
import gameObject.GameWorld;

public class FoodSnake extends GameObject {

	private boolean isDead;

	private Random random;

	public FoodSnake(GameWorld game) {
		super(0, 0, game);
		random = new Random();

		khoiTao();

		isDead = false;
	}

	public void khoiTao() {
		int x = random.nextInt(461);
		int y = random.nextInt(461);
		if (x % 10 != 0) {
			if (x > 10) {
				x = x + (10 - x % 10);
			} else {
				x = x - x % 10;
			}
		}
		if (y % 10 != 0) {
			if (y > 10) {
				y = y + (10 - y % 10);
			} else {
				y = y - y % 10;
			}
		}

		Rectangle rect = new Rectangle(x, y, 10, 10);
		while (isIntersectSnake(rect)) {
			x = random.nextInt(461);
			y = random.nextInt(461);
			rect = new Rectangle(x, y, 10, 10);
		}

		setFloatX(rect.x);
		setFloatY(rect.y);

	}

	public boolean isIntersectSnake(Rectangle rect) {
		for (int i = 0; i < getGame().getSnake().getListPoint().size(); i++) {
			Point point = getGame().getSnake().getListPoint().get(i);
			if (point.getFloatX() == getFloatX() && point.getFloatY() == getFloatY()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void upload() {
		Point point = getGame().getSnake().getListPoint().get(0);

		if (point.getFloatX() == getFloatX() && point.getFloatY() == getFloatY()) {
			getGame().getSnake().getListPoint().add(new Point(getFloatX(), getFloatY(), getGame()));
			isDead = true;
		}

	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.fillRect(getFloatX(), getFloatY(), 10, 10);
		g2.setColor(Color.WHITE);
		g2.drawRect(getFloatX(), getFloatY(), 10, 10);
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

}
