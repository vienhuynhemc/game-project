package week_2_A_Star_NPuzzle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[][] state;

	private BufferedImage mainImage, board;

	public GUI(int[][] state) throws IOException {
		this.state = state;

		mainImage = new BufferedImage(504, 504, BufferedImage.TYPE_INT_RGB);

		board = ImageIO.read(new File("board.png"));

		setSize(519, 540);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) mainImage.getGraphics();
		g2.drawImage(board, 0, 0, null);

		int size = 450 / state.length;
		int xStart = 28;
		int yStart = 28;
		g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size / 3));
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state.length; j++) {
				if (state[i][j] != -1) {
					g2.setColor(Color.WHITE);
					g2.fillRect(xStart, yStart, size, size);
					g2.setColor(Color.BLACK);
					g2.drawRect(xStart, yStart, size, size);
					g2.setColor(new Color(34, 149, 254));
					g2.drawString(state[i][j] + "", xStart + size / 3, yStart + (int) (size / 1.5));
				} else {
					g2.setColor(Color.GRAY);
					g2.fillRect(xStart, yStart, size, size);
				}
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
