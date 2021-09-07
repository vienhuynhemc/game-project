package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import caRo.GameWorld;

public class Panel1 {

	private GameWorld game;
	private JPanel p1;

	public Panel1(GameWorld game) {
		this.setGame(game);

		JScrollPane scp = new JScrollPane(p1 = new JPanel());
		scp.setPreferredSize(new Dimension(600, 710));

		p1.setLayout(new GridLayout(game.arrButton.length, game.arrButton[0].length, 1, 1));
		p1.setBackground(Color.white);
		game.mainPanel.add(scp);
		scp.setBorder(null);

		Dimension bt = new Dimension(30, 30);

		for (int i = 0; i < game.arrButton.length; i++) {
			for (int j = 0; j < game.arrButton[i].length; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						game.arrButton[i][j] = new JButton();
						game.arrButton[i][j].setPreferredSize(bt);
						game.arrButton[i][j].addMouseListener(game);
						game.arrButton[i][j].setBorderPainted(false);
						game.arrButton[i][j].setEnabled(false);
						p1.add(game.arrButton[i][j]);
					} else {
						game.arrButton[i][j] = new JButton();
						game.arrButton[i][j].setPreferredSize(bt);
						game.arrButton[i][j].addMouseListener(game);
						game.arrButton[i][j].setBorderPainted(false);
						game.arrButton[i][j].setEnabled(false);
						p1.add(game.arrButton[i][j]);
					}
				} else {
					if (j % 2 == 0) {
						game.arrButton[i][j] = new JButton();
						game.arrButton[i][j].setPreferredSize(bt);
						game.arrButton[i][j].addMouseListener(game);
						game.arrButton[i][j].setBorderPainted(false);
						game.arrButton[i][j].setEnabled(false);
						p1.add(game.arrButton[i][j]);
					} else {
						game.arrButton[i][j] = new JButton();
						game.arrButton[i][j].setPreferredSize(bt);
						game.arrButton[i][j].addMouseListener(game);
						game.arrButton[i][j].setBorderPainted(false);
						game.arrButton[i][j].setEnabled(false);
						p1.add(game.arrButton[i][j]);
					}
				}
				game.arrEnd[i][j] = true;
			}
		}
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

}
