package gameObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import gameEffect.Animation;

public class LabelDiamond extends JLabel {

	
	private static final long serialVersionUID = 1L;

	private GameWorld game;

	private int score;

	private Animation anime;

	public LabelDiamond(GameWorld game) {
		this.game = game;

		anime = new Animation(game.getInputData().getListAnimation().get("diamond"));
		anime.setHeSo(0.25f);
		
		score = 100;
	}

	public void draw(Graphics2D g2) {
		anime.upload(System.nanoTime());
		anime.draw(g2, getX() + 30, getY() + 20);

		g2.setFont(new Font("Time New Roman", Font.BOLD, 20));
		g2.setColor(Color.WHITE);
		g2.drawString(String.valueOf(score), getX() + 60, getY() + 40);
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

}
