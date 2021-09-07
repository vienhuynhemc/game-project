package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import control.Controller;

public class View extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelNotification p1;
	private PanelPlayer p2;

	private GameFrame gameFrame;

	private Controller c;

	private int w;
	private int h;
	private int boom;

	public View(int w, int h, int boom, GameFrame gameFrame) {

		this.gameFrame = gameFrame;
		c = new Controller(this);
		this.boom = boom;
		this.w = w;
		this.h = h;

		setLayout(new BorderLayout(20, 20));

		add(p1 = new PanelNotification(this), BorderLayout.NORTH);
		add(p2 = new PanelPlayer(this), BorderLayout.CENTER);
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public int getBoom() {
		return boom;
	}

	public void setBoom(int boom) {
		this.boom = boom;
	}

	public PanelNotification getP1() {
		return p1;
	}

	public void setP1(PanelNotification p1) {
		this.p1 = p1;
	}

	public PanelPlayer getP2() {
		return p2;
	}

	public void setP2(PanelPlayer p2) {
		this.p2 = p2;
	}

	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}

}
