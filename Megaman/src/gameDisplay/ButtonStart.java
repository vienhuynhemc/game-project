package gameDisplay;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ButtonStart {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage imgPress;
	private BufferedImage imgRelease;
	private BufferedImage imgPressPaint;
	private BufferedImage imgReleasePaint;

	private boolean isPress;

	private boolean isIn;

	private int x;
	private int y;
	private int width;
	private int height;

	public ButtonStart(int x, int y, int width, int height) throws IOException {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		BufferedImage img = ImageIO.read(new File("data/buttonStart.png"));

		imgRelease = img.getSubimage(2, 13, 722, 300);
		imgPress = img.getSubimage(2, 327, 722, 300);
		imgReleasePaint = img.getSubimage(2, 669, 722, 300);
		imgPressPaint = img.getSubimage(2, 983, 722, 300);

	}

	public void draw(Graphics g) {
		if (!isPress) {
			if (isIn) {
				g.drawImage(imgReleasePaint, x, y, width, height, null);
			} else {
				g.drawImage(imgRelease, x, y, width, height, null);
			}
		} else {
			if (isIn) {
				g.drawImage(imgPressPaint, x, y, width, height, null);
			} else {
				g.drawImage(imgPress, x, y, width, height, null);
			}
		}
	}

	public Rectangle getRectangleXuLyVaCham() {
		return new Rectangle(x, y, width, height);
	}

	public BufferedImage getImgPress() {
		return imgPress;
	}

	public void setImgPress(BufferedImage imgPress) {
		this.imgPress = imgPress;
	}

	public BufferedImage getImgRelease() {
		return imgRelease;
	}

	public void setImgRelease(BufferedImage imgRelease) {
		this.imgRelease = imgRelease;
	}

	public boolean isPress() {
		return isPress;
	}

	public void setPress(boolean isPress) {
		this.isPress = isPress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BufferedImage getImgPressPaint() {
		return imgPressPaint;
	}

	public void setImgPressPaint(BufferedImage imgPressPaint) {
		this.imgPressPaint = imgPressPaint;
	}

	public BufferedImage getImgReleasePaint() {
		return imgReleasePaint;
	}

	public void setImgReleasePaint(BufferedImage imgReleasePaint) {
		this.imgReleasePaint = imgReleasePaint;
	}

	public boolean isIn() {
		return isIn;
	}

	public void setIn(boolean isIn) {
		this.isIn = isIn;
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
