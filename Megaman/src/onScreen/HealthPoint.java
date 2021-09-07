package onScreen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HealthPoint {

	private int posX;
	private int posY;
	private BufferedImage image;

	private int width;
	private int height;

	public HealthPoint(BufferedImage image, int x, int y) {
		this.image = image;
		this.posX = x;
		this.posY = y;

		width = 12;
		height = 4;
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(image, posX, posY, width, height, null);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
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
