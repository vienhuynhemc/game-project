package gameObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class ProcessBar extends JLabel {

	private static final long serialVersionUID = 1L;
	private GameWorld game;
	private long timeStart;
	private long timeEnd;

	private boolean isEnd;

	private boolean isStop;

	public ProcessBar(GameWorld game) {
		this.game = game;

		timeStart = System.currentTimeMillis();
		timeEnd = 600000;
	}

	public void draw(Graphics2D g2) {

		if (!isStop) {
			long timeNow = System.currentTimeMillis() - timeStart;

			int heSo = (int) (timeNow / 30 / 40);
			BufferedImage img = game.getInputData().getListImage().get("bar").getImage().getSubimage(0, 0, 515 - heSo,
					103);

			if (515 - heSo - 100 == 0) {
				isEnd = true;
			}

			g2.drawImage(img, getX(), getY(), 515 - heSo - 100, 50, null);
		}
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public long getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(long timeEnd) {
		this.timeEnd = timeEnd;
	}

	public boolean isStop() {
		return isStop;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
