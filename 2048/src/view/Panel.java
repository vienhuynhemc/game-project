package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameWorld;
import model.O;

public class Panel extends JPanel implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private GameWorld game;

	private JButton bt1, bt2, bt3;

	public Panel() {

		game = new GameWorld();

		setLayout(null);
		JLabel lb = new JLabel(new ImageIcon("116837630_1361638410707021_5638592187175533597_n.png"));
		lb.setBounds(-20, -45, 1000, 600);
		add(lb);

		bt1 = new JButton("BT1");
		bt2 = new JButton("BT2");
		bt3 = new JButton("BT3");

		add(bt1);
		add(bt2);
		add(bt3);

		bt1.setBounds(50, 400, 200, 50);
		bt2.setBounds(680, 400, 200, 50);
		bt3.setBounds(720, 50, 200, 50);

		thread = new Thread(this);
		thread.start();

	}

//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//
//		g.setColor(Color.GREEN);
//		g.fillRect(0, 0, 1000, 1000);
//
//		O[][] arrayO = game.getArrayO();
//
//		for (int i = 0; i < arrayO.length; i++) {
//			for (int j = 0; j < arrayO[i].length; j++) {
//				arrayO[i][j].draw(g);
//			}
//		}

//	}

	public void upload() {
		game.upload();
	}

	@Override
	public void run() {

		while (true) {
			try {
				bt1.repaint();
				bt2.repaint();
				bt3.repaint();

				Thread.sleep(1000 / 80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.getAction().keyPress(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
