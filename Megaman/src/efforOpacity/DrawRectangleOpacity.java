package efforOpacity;

import java.awt.Color;
import java.awt.Graphics2D;

import view.GameFrame;

public class DrawRectangleOpacity {

	public static final int UP = 0;
	public static final int LOW = 1;
	public static final int END = 2;

	private boolean isChuyenStage;

	private int stage;

	private int now;

	private int space;

	private Color color;

	public DrawRectangleOpacity(Color color) {

		this.color = color;

		stage = 0;

		now = 0;

		space = 1;

		isChuyenStage = true;

	}

	public void upload() {
		if (stage == UP) {
			now += space;
		} else if (stage == LOW) {
			now -= space;
		} else {
			now = 0;
		}

		if (now >= 255)
			if (isChuyenStage)
				setStage(LOW);

		if (getStage() == LOW && now <= 0)
			if (isChuyenStage)
				setStage(END);
	}

	public void draw(Graphics2D g2) {
		if (color == Color.WHITE) {
			if (now > 255) {
				now = 255;
			}
			g2.setColor(new Color(255, 255, 255, now));
		} else if (color == Color.BLACK) {
			if (now > 255) {
				now = 255;
			}
			g2.setColor(new Color(0, 0, 0, now));
		}
		g2.fillRect(0, 0, GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT);
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	public static int getUp() {
		return UP;
	}

	public static int getLow() {
		return LOW;
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public static int getEnd() {
		return END;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isChuyenStage() {
		return isChuyenStage;
	}

	public void setChuyenStage(boolean isChuyenStage) {
		this.isChuyenStage = isChuyenStage;
	}

}
