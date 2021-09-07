package gameObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class LabelPoint extends JLabel {
//tính điểm
	private static final long serialVersionUID = 1L;
	private GameWorld game;
	private int point;
	private int pointNew;
	private long timeStart;
	private boolean isNoReady;

	public LabelPoint(GameWorld game) {
		this.game = game;
	}

	public void upload() {
		if (point != pointNew) {
			isNoReady = true;
		} else {
			isNoReady = false;
		}

		if (isNoReady) {
			if (System.currentTimeMillis() - timeStart > 30) {
				point++;
				timeStart = System.currentTimeMillis();
			}
		}
	}

	public void draw(Graphics2D g2) {

		g2.setColor(new Color(1, 6, 39));
		g2.fillRoundRect(getX(), getY(), 150, 40, 10, 10);

		if (isNoReady) {
			g2.setFont(new Font("Time New Roman", Font.BOLD, 25));
		} else {
			g2.setFont(new Font("Time New Roman", Font.BOLD, 20));
		}
		g2.setColor(Color.WHITE);
		g2.drawString(String.valueOf(point), getX() + 35, getY() + 28);
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getPointNew() {
		return pointNew;
	}

	public void setPointNew(int pointNew) {
		this.pointNew = pointNew;
	}
}
