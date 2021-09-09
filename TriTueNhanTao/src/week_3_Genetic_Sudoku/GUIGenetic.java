package week_3_Genetic_Sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class GUIGenetic extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage mainImage;

	private Node node;

	public GUIGenetic() {
		mainImage = new BufferedImage(510, 510, BufferedImage.TYPE_INT_RGB);

		setSize(488, 510);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) mainImage.getGraphics();

		// <!-- draw background -->
		g2.setColor(new Color(54, 129, 244));
		g2.fillRect(0, 0, 510, 510);

		// <!-- draw background 9 number -->
		g2.setColor(new Color(235, 236, 254));
		int xStart = 0;
		int yStart = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				g2.fillRect(xStart, yStart, 154, 154);
				xStart += 159;
			}
			xStart = 0;
			yStart += 159;
		}

		// <!-- draw rectangle number -->
		g2.setColor(Color.WHITE);
		xStart = 0;
		yStart = 0;
		int countRow = 0;
		int countCol = 0;
		for (int i = 0; i < node.getState().length; i++) {
			for (int j = 0; j < node.getState().length; j++) {
				g2.fillRect(xStart, yStart, 50, 50);
				xStart += 52;
				countCol++;
				if (countCol == 3) {
					countCol = 0;
					xStart += 3;
				}
			}
			xStart = 0;
			yStart += 52;
			countRow++;
			if (countRow == 3) {
				countRow = 0;
				yStart += 3;
			}
		}

		// <!-- draw state>
		if (node != null) {
			g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));

			xStart = 0;
			yStart = 50;
			countCol = 0;
			countRow = 0;
			for (int i = 0; i < node.getState().length; i++) {
				for (int j = 0; j < node.getState().length; j++) {
					if (node.getRootArray()[i][j])
						g2.setColor(new Color(34, 149, 254));
					else
						g2.setColor(new Color(90, 94, 121));

					g2.drawString(node.getState()[i][j] + "", xStart + 16, yStart - 13);
					xStart += 52;
					countCol++;
					if (countCol == 3) {
						countCol = 0;
						xStart += 3;
					}
				}
				xStart = 0;
				yStart += 52;
				countRow++;
				if (countRow == 3) {
					countRow = 0;
					yStart += 3;
				}
			}
		}

		g.drawImage(mainImage, 7, 31, this);
	}

	public void setNode(Node node) {
		this.node = node;
	}

}
