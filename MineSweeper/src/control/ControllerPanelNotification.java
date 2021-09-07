package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.ModelPanelNotification;
import view.PanelNotification;

public class ControllerPanelNotification implements MouseListener {

	private ModelPanelNotification m;

	public ControllerPanelNotification(PanelNotification p) {
		m = new ModelPanelNotification(p);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		m.mouseReleased(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		m.mousePressed(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

}
