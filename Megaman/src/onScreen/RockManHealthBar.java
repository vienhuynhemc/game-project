package onScreen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;

public class RockManHealthBar extends GameObjectMegamanX {

	private int width;
	private int height;

	private BufferedImage thanhHp;
	private BufferedImage pointHp;
	private List<HealthPoint> listPointHp;

	private boolean isReady;
	private boolean isStart;
	private long timeStart;
	private long timeForRen1HP;

	private int xBegin;
	private int yBegin;

	private int currentPointHp;

	private int conSoLamChoRockManKhongTheUpLoad;

	public RockManHealthBar(float x, float y, GameWorldMegamanX game) {
		super(x, y, game);

		listPointHp = new ArrayList<HealthPoint>();

		width = 28;
		height = 120;

		timeStart = System.nanoTime();
		timeForRen1HP = 60000000;

		currentPointHp = 0;

		xBegin = (int) (getX() + 8);
		yBegin = (int) (getY() + height - 36);

		thanhHp = getGame().getInputData().getListFrame().get("thanhHP").getImage();
		pointHp = getGame().getInputData().getListFrame().get("pointHP").getImage();

		conSoLamChoRockManKhongTheUpLoad = 0;
	}

	@Override
	public void upload() {

		uploadListHp();

	}

	public void uploadListHp() {
		if (!isStart) {

			if (currentPointHp < getGame().getRockMan().getBlood() / 5) {
				if (System.nanoTime() - timeStart > timeForRen1HP) {
					listPointHp.add(new HealthPoint(pointHp, xBegin, yBegin));
					yBegin = yBegin - 4;
					timeStart = System.nanoTime();
					currentPointHp++;
				}
			}

			if (currentPointHp == getGame().getRockMan().getBlood() / 5) {
				isReady = true;
				getGame().setSTAGE(GameWorldMegamanX.NORMAL);
				getGame().setiStartGame(true);
				isStart = true;
				conSoLamChoRockManKhongTheUpLoad = 1;
			}
		} else {
			if (getGame().getRockMan().getBlood() > 0) {
				int pointHp = getGame().getRockMan().getBlood() / 5;
				if (currentPointHp > pointHp) {
					int chenhLech = currentPointHp - pointHp;
					for (int i = 0; i < chenhLech; i++) {
						listPointHp.remove(currentPointHp - 1);
						currentPointHp--;
						yBegin += 4;
					}
				}
			}
		}
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(thanhHp, (int) getX(), (int) getY(), width, height, null);

		drawHpNoTime(g2);

	}

	public void drawHpNoTime(Graphics2D g2) {
		for (int i = 0; i < listPointHp.size(); i++) {
			listPointHp.get(i).draw(g2);
		}
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

	public BufferedImage getThanhHp() {
		return thanhHp;
	}

	public void setThanhHp(BufferedImage thanhHp) {
		this.thanhHp = thanhHp;
	}

	public BufferedImage getPointHp() {
		return pointHp;
	}

	public void setPointHp(BufferedImage pointHp) {
		this.pointHp = pointHp;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public int getConSoLamChoRockManKhongTheUpLoad() {
		return conSoLamChoRockManKhongTheUpLoad;
	}

	public void setConSoLamChoRockManKhongTheUpLoad(int conSoLamChoRockManKhongTheUpLoad) {
		this.conSoLamChoRockManKhongTheUpLoad = conSoLamChoRockManKhongTheUpLoad;
	}

	public List<HealthPoint> getListPointHp() {
		return listPointHp;
	}

	public void setListPointHp(List<HealthPoint> listPointHp) {
		this.listPointHp = listPointHp;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public long getTimeForRen1HP() {
		return timeForRen1HP;
	}

	public void setTimeForRen1HP(long timeForRen1HP) {
		this.timeForRen1HP = timeForRen1HP;
	}

	public int getxBegin() {
		return xBegin;
	}

	public void setxBegin(int xBegin) {
		this.xBegin = xBegin;
	}

	public int getyBegin() {
		return yBegin;
	}

	public void setyBegin(int yBegin) {
		this.yBegin = yBegin;
	}

	public int getCurrentPointHp() {
		return currentPointHp;
	}

	public void setCurrentPointHp(int currentPointHp) {
		this.currentPointHp = currentPointHp;
	}

}
