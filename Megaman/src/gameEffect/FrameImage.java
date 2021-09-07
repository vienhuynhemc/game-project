package gameEffect;

import java.awt.image.BufferedImage;

public class FrameImage {

	private String name;
	private BufferedImage image;

	public FrameImage(String name, BufferedImage image) {
		this.name = name;
		this.image = image;
	}

	public FrameImage(FrameImage neW) {
		this.name = neW.getName();
		this.image = neW.getImage();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
