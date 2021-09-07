package model;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import control.Controller;
import view.GameFrame;
import view.View;

public class Model {

	private World world;
	private Controller c;

	public Model(View view, Controller c) {
		this.c = c;
		world = new World(8, 8, 10, view);
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public void mousePress(MouseEvent e) {
		c.getView().getP1().getBt().setStage(ButtonSmile.wow);
		c.getView().getP1().getBt().repaint();
		ButtonPlay[][] arrayButton = c.getView().getP2().getArrayButton();
		for (int i = 0; i < arrayButton.length; i++) {
			for (int j = 0; j < arrayButton[i].length; j++) {
				if (e.getButton() == 1 && e.getSource() == arrayButton[i][j] && !world.getArrayCamCo()[i][j]) {

					if (!c.getView().getP1().getTime().isRunning()) {
						c.getView().getP1().getTime().start();
					}

					if (!world.open(i, j)) {

						if (world.isComplete()) {

							c.getView().getP1().getTime().stop();
							c.getView().getP1().getBt().setStage(ButtonSmile.lose);
							c.getView().getP1().getBt().repaint();

							int option = JOptionPane.showConfirmDialog(c.getView(), "You lost, play again?",
									"Notification", JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								c.getView().getGameFrame().setVisible(false);
								new GameFrame(c.getView().getW(), c.getView().getH(), c.getView().getBoom());
							} else {
								world.fullTrue();
							}
						} else if (world.isEnd()) {

							c.getView().getP1().getTime().stop();
							c.getView().getP1().getBt().setStage(ButtonSmile.win);
							c.getView().getP1().getBt().repaint();

							int option = JOptionPane.showConfirmDialog(c.getView(), "You win, play again ?",
									"Notification", JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								c.getView().getGameFrame().setVisible(false);
								new GameFrame(c.getView().getW(), c.getView().getH(), c.getView().getBoom());
							}
						}
					}
				} else if (e.getButton() == 3 && e.getSource() == arrayButton[i][j]) {
					world.camCo(i, j);
				}
				if (e.getClickCount() == 2 && e.getSource() == arrayButton[i][j] && world.getArrayBoolean()[i][j]) {
					if (!world.clickDouble(i, j)) {

						int option = JOptionPane.showConfirmDialog(c.getView(), "You lost, play again?", "Notification",
								JOptionPane.YES_NO_OPTION);

						if (option == JOptionPane.YES_OPTION) {
							c.getView().getGameFrame().setVisible(false);
							new GameFrame(c.getView().getW(), c.getView().getH(), c.getView().getBoom());
						} else {
							world.fullTrue();
						}
					}
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		c.getView().getP1().getBt().setStage(ButtonSmile.now);
		c.getView().getP1().getBt().repaint();
	}

}
