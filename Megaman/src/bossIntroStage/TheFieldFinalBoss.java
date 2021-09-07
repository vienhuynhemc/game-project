package bossIntroStage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;

public class TheFieldFinalBoss extends GameObjectMegamanX {

	private BufferedImage img;

	private boolean isComplete;
	private boolean isStart;

	public TheFieldFinalBoss(float x, float y, GameWorldMegamanX game) throws IOException {
		super(x, y, game);

		BufferedImage image = ImageIO.read(new File("data/finalBoss.png"));
		img = image.getSubimage(131, 182, 560, 119);
	}

	@Override
	public void upload() {
		if (getY() < 1234) {
			isComplete = true;
		} else {
			setY(getY() - 1);
		}
	}

	public void start() {
		isStart = true;
	}

	public void draw(Graphics2D g2) {
		if (isStart) {
			g2.drawImage(img, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()), 1120, 238, null);
		}
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	
}
