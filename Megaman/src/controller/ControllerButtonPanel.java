package controller;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;

import model.ModelButtonPanel;
import view.ButtonPanel;

public class ControllerButtonPanel {

	private ButtonPanel buttonPanel;
	private ModelButtonPanel model;

	public ControllerButtonPanel(ButtonPanel buttonPanel) throws IOException {
		this.buttonPanel = buttonPanel;

		model = new ModelButtonPanel(this);
	}

	public void draw(Graphics g) {
		model.draw(g);
	}

	public void mousePress(MouseEvent e) {
		model.mousePress(e);
	}

	public void mouseRelease(MouseEvent e) {
		model.mouseReleased(e);
	}

	public void mouseMove(MouseEvent e) {
		model.mouseMoved(e);
	}

	public void mouseDragged(MouseEvent e) {
		model.mouseDragged(e);
	}

	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	public ModelButtonPanel getModel() {
		return model;
	}

}
