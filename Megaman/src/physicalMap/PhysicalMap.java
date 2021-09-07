package physicalMap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameObject.Draw;
import gameObject.GameObject;
import gameObject.Upload;
import gameWorld.GameWorld;

public abstract class PhysicalMap extends GameObject implements Draw, Upload {

	private int[][] array;
	private int size;

	public PhysicalMap(float x, float y, GameWorld game) {
		super(x, y, game);
	}

	public void draw(Graphics2D g2) {

		int[][] arrayPhysicalMapMapleStory = getArray();
		int size = getSize();

		for (int i = 0; i < arrayPhysicalMapMapleStory.length; i++) {
			for (int j = 0; j < arrayPhysicalMapMapleStory[i].length; j++) {
				if (arrayPhysicalMapMapleStory[i][j] == 1 || arrayPhysicalMapMapleStory[i][j] == 3) {
					g2.setColor(Color.LIGHT_GRAY);
					g2.fillRect((int) (j * size + getX() - getGame().getCamera().getX()),
							(int) (i * size + getY() - getGame().getCamera().getY()), size, size);
					g2.setColor(Color.GRAY);
					g2.drawRect((int) (j * size + getX() - getGame().getCamera().getX()),
							(int) (i * size + getY() - getGame().getCamera().getY()), size, size);
				}
			}
		}
	}

	public Rectangle vaChamMatDat(Rectangle rect) {

		int[][] arrayPhysicalMapMapleStory = getArray();
		int size = getSize();

		int posX1 = rect.x / size - 2;
		int posX2 = (rect.x + rect.width) / size + 2;
		int posY1 = (rect.y + rect.height) / size;
		int posY2 = posY1 + 2;

		if (posX1 < 0)
			posX1 = 0;
		if (posX2 > arrayPhysicalMapMapleStory[0].length - 1)
			posX2 = arrayPhysicalMapMapleStory[0].length - 1;
		if (posY2 > arrayPhysicalMapMapleStory.length - 1)
			posY2 = arrayPhysicalMapMapleStory.length - 1;
		for (int i = posY1; i <= posY2; i++) {
			for (int j = posX1; j <= posX2; j++) {
				if (arrayPhysicalMapMapleStory[i][j] == 1 || arrayPhysicalMapMapleStory[i][j] == 3) {
					Rectangle r = new Rectangle((int) (j * size + getX()), (int) (i * size + getY()), size, size);
					if (rect.intersects(r))
						return r;
				}
			}
		}
		return null;
	}

	public Rectangle vaChamTrenDau(Rectangle rect) {

		int[][] arrayPhysicalMapMapleStory = getArray();
		int size = getSize();

		int posX1 = rect.x / size - 2;
		int posX2 = (rect.x + rect.width) / size + 2;
		if (posX1 < 0)
			posX1 = 0;
		if (posX2 > arrayPhysicalMapMapleStory[0].length - 1)
			posX2 = arrayPhysicalMapMapleStory[0].length - 1;
		int posY1 = rect.y / size;
		int posY2 = posY1 - 2;
		if (posY2 < 0)
			posY2 = 0;
		for (int i = posY1; i >= posY2; i--) {
			for (int j = posX1; j <= posX2; j++) {
				if (arrayPhysicalMapMapleStory[i][j] == 1 || arrayPhysicalMapMapleStory[i][j] == 3) {
					Rectangle r = new Rectangle((int) (j * size + getX()), (int) (i * size + getY()), size, size);
					if (rect.intersects(r))
						return r;
				}
			}
		}
		return null;
	}

	public Rectangle vaChamBenTrai(Rectangle rect) {

		int[][] arrayPhysicalMapMapleStory = getArray();
		int size = getSize();

		int posX1 = rect.x / size;
		int posX2 = posX1 - 2;
		int posY1 = rect.y / size - 2;
		int posY2 = (rect.y + rect.height) / size + 2;
		if (posX2 < 0)
			posX2 = 0;
		while (posY1 * size <= rect.y) {
			posY1++;
		}
		while (posY2 * size >= rect.y + rect.height) {
			posY2--;
		}
		for (int i = posX1; i > posX2; i--) {
			for (int j = posY1; j < posY2; j++) {
				if (arrayPhysicalMapMapleStory[j][i] == 1 || arrayPhysicalMapMapleStory[j][i] == 3) {
					Rectangle r = new Rectangle((int) (i * size + getX()), (int) (j * size + getY()), size, size);
					if (rect.intersects(r) && r.y + r.width - 1 != rect.y + rect.height) {
						return r;
					}
				}
			}
		}
		return null;
	}

	public Rectangle vaChamBenPhai(Rectangle rect) {

		int[][] arrayPhysicalMapMapleStory = getArray();
		int size = getSize();

		int posX1 = (rect.x + rect.width) / size;
		int posX2 = posX1 + 2;
		int posY1 = rect.y / size - 2;
		int posY2 = (rect.y + rect.height) / size + 2;
		if (posX2 < 0)
			posX2 = 0;
		while (posY1 * size <= rect.y) {
			posY1++;
		}
		while (posY2 * size >= rect.y + rect.height) {
			posY2--;
		}
		for (int i = posX1; i < posX2; i++) {
			for (int j = posY1; j < posY2; j++) {
				if (arrayPhysicalMapMapleStory[j][i] == 1 || arrayPhysicalMapMapleStory[j][i] == 3) {
					Rectangle r = new Rectangle((int) (i * size + getX()), (int) (j * size + getY()), size, size);
					if (rect.intersects(r) && r.y + r.width - 1 != rect.y + rect.height) {
						return r;
					}
				}
			}
		}
		return null;
	}

	public Rectangle danVaChamTuong(Rectangle rect) {

		int[][] arrayPhysicalMapMapleStory = getArray();
		int size = getSize();

		int posX1 = rect.x / getSize() - 2;
		int posX2 = (rect.x + rect.width) / getSize() + 2;
		int posY1 = rect.y / getSize() - 2;
		int posY2 = (rect.y + rect.height) / getSize() + 2;
		if (posX1 < 0)
			posX1 = 0;
		if (posX2 > arrayPhysicalMapMapleStory[0].length - 1)
			posX2 = arrayPhysicalMapMapleStory[0].length - 1;
		if (posY1 < 0)
			posY1 = 0;
		if (posY2 > arrayPhysicalMapMapleStory.length - 1)
			posY2 = arrayPhysicalMapMapleStory.length - 1;
		for (int i = posY1; i < posY2; i++) {
			for (int j = posX1; j < posX2; j++) {
				if (arrayPhysicalMapMapleStory[i][j] == 1 || arrayPhysicalMapMapleStory[i][j] == 3) {
					Rectangle r = new Rectangle((int) (j * size + getX()), (int) (i * size + getY()), size, size);
					if (rect.intersects(r))
						return r;
				}
			}
		}
		return null;
	}

	public int[][] getArray() {
		return array;
	}

	public void setArray(int[][] array) {
		this.array = array;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
