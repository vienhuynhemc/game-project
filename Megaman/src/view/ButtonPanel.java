package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JPanel;

import controller.ControllerButtonPanel;
import gameDisplay.PanelStart;

public class ButtonPanel extends JPanel implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PanelStart panel;

	private ControllerButtonPanel controller;

	public ButtonPanel(PanelStart panel) throws IOException {

		this.panel = panel;

		controller = new ControllerButtonPanel(this);

		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		controller.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		controller.mousePress(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		controller.mouseRelease(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		panel.getLableB().repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		controller.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		controller.mouseMove(e);
	}

	public PanelStart getPanel() {
		return panel;
	}

	public void setPanel(PanelStart panel) {
		this.panel = panel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
