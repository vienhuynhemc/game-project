package gameDisplay;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JButton;

public class ButtonItem extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	private int width;
	private int height;

	private BufferedImage img;

	public ButtonItem(int x, int y, int width, int height, BufferedImage img) throws IOException {

		this.img = img;

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

	public Rectangle getRectangleXuLyVaCham() {
		return new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
