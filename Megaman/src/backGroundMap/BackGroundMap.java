package backGroundMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import bossIntroStage.TheFieldFinalBoss;
import gameObject.Draw;
import gameObject.GameObject;
import gameObject.Upload;
import gameWorld.GameWorld;

public abstract class BackGroundMap extends GameObject implements Upload, Draw {

	private BufferedImage imageIntro, backGroundMap;

	public BackGroundMap(float x, float y, GameWorld game) {
		super(x, y, game);
	}

	@Override
	public void draw(Graphics2D g2) {

		g2.drawImage(backGroundMap, (int) (getX() - (getGame().getCamera().getX() * 50 / 100)),
				(int) (getY() - getGame().getCamera().getY() + 30), backGroundMap.getWidth() * 2,
				backGroundMap.getHeight() * 2, null);

		g2.drawImage(imageIntro, (int) (getX() - getGame().getCamera().getX()),
				(int) (getY() - getGame().getCamera().getY()), imageIntro.getWidth() * 2, imageIntro.getHeight() * 2,
				null);
	}

	public BufferedImage getImageIntro() {
		return imageIntro;
	}

	public void setImageIntro(BufferedImage imageIntro) {
		this.imageIntro = imageIntro;
	}

	public BufferedImage getBackGroundMap() {
		return backGroundMap;
	}

	public void setBackGroundMap(BufferedImage backGroundMap) {
		this.backGroundMap = backGroundMap;
	}

	public abstract TheFieldFinalBoss getSanDauFinalBoss();

}
