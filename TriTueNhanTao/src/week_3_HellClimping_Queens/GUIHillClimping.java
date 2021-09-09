package week_3_HellClimping_Queens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIHillClimping extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage board, cell1, cell2, queen, mainImage;
	private JPanel mainPanel;

	private LocalSearch l;
	private List<Integer> state;

	public GUIHillClimping(LocalSearch l) throws IOException {

		this.l = l;

		state = new ArrayList<Integer>();

		board = ImageIO.read(new File("board.png"));
		cell1 = ImageIO.read(new File("cell1.png"));
		cell2 = ImageIO.read(new File("cell2.png"));
		queen = ImageIO.read(new File("queen.png"));

		mainImage = new BufferedImage(504, 504, BufferedImage.TYPE_INT_RGB);

		add(mainPanel = new JPanel());
		mainPanel.setLayout(null);

		setSize(519, 540);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) mainImage.getGraphics();
		g2.drawImage(board, 0, 0, null);

		int size = 450 / l.getN();
		boolean draw = true;
		int xStart = 28;
		int yStart = 28;
		for (int i = 0; i < l.getN(); i++) {
			for (int j = 0; j < l.getN(); j++) {
				if (draw) {
					draw = false;
					g2.drawImage(cell1, xStart, yStart, size, size, null);
				} else {
					draw = true;
					g2.drawImage(cell2, xStart, yStart, size, size, null);
				}
				xStart += size;
			}
			xStart = 28;
			yStart += size;
			if (l.getN() % 2 == 0) {
				if (draw)
					draw = false;
				else
					draw = true;
			}
		}

		for (int i = 0; i < state.size(); i++)
			g2.drawImage(queen, 28 + i * size, 28 + state.get(i) * size, size, size, null);

		g.drawImage(mainImage, 7, 31, this);
	}

	public void setState(List<Integer> state) {
		this.state = state;
	}

}