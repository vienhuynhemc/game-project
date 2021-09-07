package gameObject;

import java.awt.Graphics2D;

import javax.swing.JButton;

public class ButtonPause extends JButton {

	private static final long serialVersionUID = 1L;
	private GameWorld game;

	public ButtonPause(GameWorld game) {
		this.game = game;
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(game.getInputData().getListImage().get("pause").getImage(), getX(), getY() - 5, 50, 50, null);
	}

}
