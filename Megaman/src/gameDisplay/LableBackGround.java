package gameDisplay;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import view.GameFrame;

public class LableBackGround extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage img;

	public LableBackGround() throws IOException {
		img = ImageIO.read(new File("data/backGroundStart.png"));

		setPreferredSize(new Dimension(GameFrame.GAME_WIDTH, GameFrame.GAME_HEIGHT));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, 1000, 570, null);
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
