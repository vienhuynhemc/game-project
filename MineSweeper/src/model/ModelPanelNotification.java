package model;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import view.GameFrame;
import view.PanelNotification;

public class ModelPanelNotification {

	private PanelNotification p;

	public ModelPanelNotification(PanelNotification p) {
		this.p = p;
	}

	public void mouseReleased(MouseEvent e) {
		p.getBt().setStage(ButtonSmile.now);
		p.getBt().repaint();

		int option = JOptionPane.showConfirmDialog(null, "Are you play new game?", "Notification",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			p.getGame().getGameFrame().setVisible(false);
			new GameFrame(p.getGame().getW(), p.getGame().getH(), p.getGame().getBoom());
		}
	}

	public void mousePressed(MouseEvent e) {
		if (p.getGame().getC().getModel().getWorld().isEnd() || p.getGame().getC().getModel().getWorld().isComplete()) {
			p.getGame().getGameFrame().setVisible(false);
			new GameFrame(p.getGame().getW(), p.getGame().getH(), p.getGame().getBoom());
		} else {
			p.getBt().setStage(ButtonSmile.press);
			p.getBt().repaint();
		}
	}

}
