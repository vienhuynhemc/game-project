package gameObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class LabelLevel extends JLabel {
//level

	private static final long serialVersionUID = 1L;
	private GameWorld game;
	private int level;

	public LabelLevel(GameWorld game) {
		this.game = game;

		level = 1;
	}

	public void draw(Graphics2D g2) {

		g2.setColor(new Color(153, 217, 244));
		g2.fillOval(getX(), getY(), 40, 40);

		g2.setFont(new Font("Time New Roman", Font.BOLD, 20));
		g2.setColor(Color.WHITE);
		if (level < 10) {
			g2.drawString(String.valueOf(level), getX() + 15, getY() + 28);
		} else {
			g2.drawString(String.valueOf(level), getX() + 8, getY() + 28);
		}
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
