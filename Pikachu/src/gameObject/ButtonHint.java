package gameObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JButton;

import gameEffect.Animation;

public class ButtonHint extends JButton {

	private static final long serialVersionUID = 1L;

	private GameWorld game;

	private Animation anime;

	public ButtonHint(GameWorld game) {
		this.game = game;

		anime = new Animation(game.getInputData().getListAnimation().get("diamond"));
		anime.setHeSo(0.25f);

	}

	public void draw(Graphics2D g2) {
		g2.setColor(new Color(255, 132, 66));
		g2.fillRoundRect(getX(), getY(), 150, 40, 20, 20);

		g2.setFont(new Font("Time New Roman", Font.BOLD, 20));
		g2.setColor(Color.WHITE);
		g2.drawString("HINT", getX() + 10, getY() + 30);
		g2.drawString("10", getX() + 100, getY() + 30);
		anime.upload(System.nanoTime());
		anime.draw(g2, getX() + 70, getY() + 10);
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

}
