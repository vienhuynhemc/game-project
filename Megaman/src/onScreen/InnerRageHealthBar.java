package onScreen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameObject.GameObjectMapleStory;
import gameObject.Upload;
import gameWorld.GameWorldMapleStory;
import sound.ManagerSound;

public class InnerRageHealthBar extends GameObjectMapleStory implements Upload {

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

	public InnerRageHealthBar(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);

		listPointHp = new ArrayList<HealthPoint>();

		width = 28;
		height = 200;

		timeStart = System.nanoTime();
		timeForRen1HP = 60000000;

		currentPointHp = 0;

		xBegin = (int) (getX() + 8);
		yBegin = (int) (getY() + height - 36);

		thanhHp = getGame().getInputData().getListFrame().get("thanhHPBoss").getImage();
		pointHp = getGame().getInputData().getListFrame().get("pointHP").getImage();

	}

	@Override
	public void upload() {

		uploadListHp();

	}

	public void uploadListHp() {
		if (!isStart) {

			ManagerSound.getInstance().getListSound().get("hoiHp").start();
			if (ManagerSound.getInstance().getListSound().get("hoiHp").getFramePosition() == ManagerSound.getInstance()
					.getListSound().get("hoiHp").getFrameLength()) {
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("hoiHp");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}

			if (currentPointHp < getGame().getBoss().getBlood() / 10) {
				if (System.nanoTime() - timeStart > timeForRen1HP) {
					listPointHp.add(new HealthPoint(pointHp, xBegin, yBegin));
					yBegin = yBegin - 4;
					timeStart = System.nanoTime();
					currentPointHp++;
				}
			} else {
				isStart = true;
				isReady = true;
			}
		} else {

			if (getGame().getBoss().getBlood() > 0) {
				int pointHp = getGame().getBoss().getBlood() / 10;
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
