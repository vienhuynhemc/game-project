package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.ButtonPlay;

public class PanelPlayer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private View game;

	private ButtonPlay[][] arrayButton;

	public PanelPlayer(View game) {
		this.game = game;

		setLayout(new GridLayout(game.getW(), game.getH()));

		arrayButton = game.getC().getModel().getWorld().getArrayButton();

		setBorder(BorderFactory.createLoweredBevelBorder());
		for (int i = 0; i < arrayButton.length; i++) {
			for (int j = 0; j < arrayButton[i].length; j++) {
				add(arrayButton[i][j] = new ButtonPlay());
				arrayButton[i][j].addMouseListener(game.getC());
			}
		}
	}

	public ButtonPlay[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonPlay[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

	public View getGame() {
		return game;
	}

	public void setGame(View game) {
		this.game = game;
	}

}
