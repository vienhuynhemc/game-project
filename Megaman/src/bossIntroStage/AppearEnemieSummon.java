package bossIntroStage;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import gameObject.Draw;
import gameObject.Upload;
import gameWorld.GameWorldMegamanX;

public class AppearEnemieSummon implements Upload, Draw {

	private StoneItemCreateEnemieSummon[] listItem;

	private Rectangle rect;

	private boolean isComplete;

	private Random rd;

	public AppearEnemieSummon(float x, float y, GameWorldMegamanX game, Rectangle rect) {
		this.rect = rect;

		rd = new Random();
		int a = 0;

		listItem = new StoneItemCreateEnemieSummon[6];
		a = rd.nextInt(3);
		listItem[0] = new StoneItemCreateEnemieSummon(9200, 1450, game, a, x, y);
		a = rd.nextInt(3);
		listItem[1] = new StoneItemCreateEnemieSummon(8800, 1450, game, a, x, y);
		a = rd.nextInt(3);
		listItem[2] = new StoneItemCreateEnemieSummon(9000, 1450, game, a, x, y);
		a = rd.nextInt(3);
		listItem[3] = new StoneItemCreateEnemieSummon(9600, 1450, game, a, x, y);
		a = rd.nextInt(3);
		listItem[4] = new StoneItemCreateEnemieSummon(9400, 1450, game, a, x, y);
		a = rd.nextInt(3);
		listItem[5] = new StoneItemCreateEnemieSummon(8600, 1450, game, a, x, y);
	}

	@Override
	public void draw(Graphics2D g2) {
		for (int i = 0; i < listItem.length; i++) {
			listItem[i].draw(g2);
		}
	}

	@Override
	public void upload() {

		int count = 0;

		for (int i = 0; i < listItem.length; i++) {
			listItem[i].upload();

			if (listItem[i].getRectangleXuLiVaCham().intersects(rect)) {
				listItem[i].setComplete(true);
			}

			if (listItem[i].isComplete()) {
				count++;
			}
		}

		if (count == listItem.length) {
			isComplete = true;
		}
	}

	public StoneItemCreateEnemieSummon[] getListItem() {
		return listItem;
	}

	public void setListItem(StoneItemCreateEnemieSummon[] listItem) {
		this.listItem = listItem;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

}
