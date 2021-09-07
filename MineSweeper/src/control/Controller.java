package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Model;
import view.View;

public class Controller implements MouseListener {

	private View view;
	private Model model;

	public Controller(View view) {
		this.view = view;
		view.addMouseListener(this);
		model = new Model(view, this);
	}

	public void mousePressed(MouseEvent e) {
		model.mousePress(e);
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void mouseReleased(MouseEvent e) {
		model.mouseReleased(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
