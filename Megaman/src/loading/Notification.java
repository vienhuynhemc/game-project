package loading;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

import gameObject.Draw;
import gameObject.GameObjectLoading;
import gameObject.Upload;
import gameWorld.GameWorldLoading;

public final class Notification extends GameObjectLoading implements Draw, Upload {

	private ArrayList<String> listNotification;
	private long timeStart;

	public Notification(float x, float y, GameWorldLoading game) {
		super(x, y, game);

		listNotification = new ArrayList<String>();
		listNotification.add("data/backGroundMapMapbleStory.png");
		listNotification.add("data/bossDoor.png");
		listNotification.add("data/bossIntroStage.png");
		listNotification.add("data/enemiesFinalBoss.png");
		listNotification.add("data/finalBoss.png");
		listNotification.add("data/introEnemies.png");
		listNotification.add("data/introStageMap.png");
		listNotification.add("data/introStageMapbackGround.png");
		listNotification.add("data/introStageMapFront.png");
		listNotification.add("data/loading.png");
		listNotification.add("data/mettalT.png");
		listNotification.add("data/napDanBulletRockMan.png");
		listNotification.add("data/npc.png");
		listNotification.add("data/rockmansprite.png");
		listNotification.add("data/rockmanspriteVer2.png");
		listNotification.add("data/spriteLoading.png");
		listNotification.add("data/subBossIntroStage.png");
		listNotification.add("data/weaponsAndItems.png");
		listNotification.add("data/whenRockManDeathSprite.png");
		listNotification.add("data/blackHole.png");
		listNotification.add("data/loadAnimation.txt");
		listNotification.add("data/loadAnimationMapleStory.txt");
		listNotification.add("data/loadAnimationLoading.txt");
		listNotification.add("data/loadImage.txt");
		listNotification.add("data/loadImageMapleStory.txt");
		listNotification.add("data/loadImageLoading.txt");
		listNotification.add("data/physicalMapIntroStage.txt");
		listNotification.add("data/physicalMapMapleStory.txt");
		listNotification.add("data/backGroundStart.txt");
		listNotification.add("data/mainPlayerMapleStory.png");

		timeStart = System.nanoTime();

	}

	@Override
	public void upload() throws IOException {
		if (listNotification.size() > 0) {
			if (!getGame().getController().getGame().getGame().getController().getModelFrame().isPressButton()) {
				getGame().getController().getModel().setLoading(true);
			}
			if (System.nanoTime() - timeStart > 5000000) {
				timeStart = System.nanoTime();
				listNotification.remove(0);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.blue);
		g2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		if (listNotification.size() > 0) {
			g2.drawString(listNotification.get(0), getX(), getY());
		} else {
			g2.drawString("Notification", getX(), getY());
		}
	}

	public ArrayList<String> getListNotification() {
		return listNotification;
	}

	public void setListNotification(ArrayList<String> listNotification) {
		this.listNotification = listNotification;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

}
