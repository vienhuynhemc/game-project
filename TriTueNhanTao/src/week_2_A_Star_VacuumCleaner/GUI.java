package week_2_A_Star_VacuumCleaner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[][] state;
	boolean isDrawWay;
	int[][] way;
	int[][] stateIgnore;
	Location position;
	int xStartt;
	int yStartt;

	private BufferedImage mainImage, rac, racX, robot, vc;

	public GUI(int[][] state, int xStart, int yStart) throws IOException {
		this.state = state;
		this.xStartt = xStart;
		this.yStartt = yStart;
		stateIgnore = new int[state.length][state.length];
		way = new int[state.length][state.length];
		position = new Location(0, 0);
		mainImage = new BufferedImage(604, 604, BufferedImage.TYPE_INT_RGB);
		rac = ImageIO.read(new File("rac.png"));
		racX = ImageIO.read(new File("racX.png"));
		robot = ImageIO.read(new File("robot.png"));
		vc = ImageIO.read(new File("vc.png"));

		setSize(619, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void igNore(int x, int y) {
		stateIgnore[x][y] = 1;
	}

	public void way(List<Integer> way, Location ll) {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state.length; j++) {
				this.way[i][j] = state[i][j];
			}
		}

		List<Integer> list = new ArrayList<Integer>();
		for (int a : way) {
			list.add(a);
		}
		Location l = new Location(ll.getX(), ll.getY());
		this.way[l.getX()][l.getY()] = 0;
		while (list.size() > 0) {
			switch (list.get(0)) {
			case Board.DIR_LEFT:
				Board.move_left(l);
				break;
			case Board.DIR_RIGHT:
				Board.move_right(l);
				break;
			case Board.DIR_UP:
				Board.move_up(l);
				break;
			case Board.DIR_DOWN:
				Board.move_down(l);
				break;
			}
			this.way[l.getX()][l.getY()] = 20;
			list.remove(0);
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) mainImage.getGraphics();
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, 1000, 1000);

		int size = 550 / state.length;
		int xStart = 28;
		int yStart = 28;

		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state.length; j++) {

				g2.setColor(Color.WHITE);

				if (i == xStartt && j == yStartt) {
					g2.setColor(Color.RED);
				}
				g2.fillRect(xStart, yStart, size, size);

				if (isDrawWay) {
					if (way[i][j] == 20) {
						g2.setColor(Color.GREEN);
						g2.fillRect(xStart, yStart, size, size);
					}
				}

				if (stateIgnore[i][j] != 0) {
					g2.drawImage(racX, xStart, yStart, size, size, null);
				}

				if (state[i][j] == -1) {
					g2.drawImage(rac, xStart, yStart, size, size, null);
				} else if (state[i][j] == 5) {
					g2.drawImage(vc, xStart, yStart, size, size, null);
				} else if (state[i][j] == 10) {
					g2.drawImage(robot, xStart, yStart, size, size, null);
				}

				g2.setColor(Color.GRAY);
				g2.drawRect(xStart, yStart, size, size);
				xStart += size;
			}
			xStart = 28;
			yStart += size;

		}
		g.drawImage(mainImage, 7, 31, this);
	}

	public void setState(int[][] state) {
		this.state = state;
	}

}
