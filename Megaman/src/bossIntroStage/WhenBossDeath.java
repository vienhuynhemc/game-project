package bossIntroStage;

import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;
import metalAnchors.WhenMetalAnchorsDeath;
import sound.ManagerSound;

public class WhenBossDeath extends GameObjectMegamanX {

	private boolean isCreate;
	private long timeStart;

	private Random rd;

	public WhenBossDeath(float x, float y, GameWorldMegamanX game) {
		super(x, y, game);

		rd = new Random();

	}

	@Override
	public void upload() {
		if (!isCreate) {

			isCreate = true;
			timeStart = System.nanoTime();

			if (System.nanoTime() % 2 == 0) {
				WhenMetalAnchorsDeath whenDeath = new WhenMetalAnchorsDeath(getX() - 200 + rd.nextInt(400),
						getY() - 200 + rd.nextInt(400), getGame());
				getGame().getListWhenDeath().addWhenDeath(whenDeath);
			} else {
				WhenMetalAnchorsDeath whenDeath = new WhenMetalAnchorsDeath(getX() - 200 - rd.nextInt(400),
						getY() - 200 - rd.nextInt(400), getGame());
				getGame().getListWhenDeath().addWhenDeath(whenDeath);
			}
			ManagerSound.getInstance().getListSound().get("bang").start();
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("bang");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

		if (isCreate)

		{
			if (System.nanoTime() - timeStart > 100000000) {
				isCreate = false;
			}
		}
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

}
