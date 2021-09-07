package model;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import controller.ControllerGameWorld;
import gameWorld.GameWorld;
import gameWorld.GameWorldLoading;
import gameWorld.GameWorldMapleStory;
import gameWorld.GameWorldMegamanX;

public class ModelGameWorld {

	private GameWorld[] arrayStage;
	private int currentStage;

	private GameWorldMegamanX megamanX;
	private GameWorldMapleStory mapbleStory;
	private GameWorldLoading loading;

	private boolean isLoading;

	private long timeStart;

	private ControllerGameWorld controller;

	public ModelGameWorld(ControllerGameWorld controller) throws IOException {
		this.controller = controller;

		arrayStage = new GameWorld[3];

		arrayStage[1] = megamanX;
		arrayStage[2] = mapbleStory;
		loading = new GameWorldLoading(controller, "Loading");
		arrayStage[0] = loading;
		currentStage = 0;
	}

	public void upload() throws IOException {
		if (arrayStage[currentStage].getSTAGE() == GameWorld.ENDGAME) {
			if (System.currentTimeMillis() - timeStart > 5000) {
				try {
					if (arrayStage[currentStage].getName().equals("Megaman X")) {
						arrayStage[currentStage] = new GameWorldMegamanX(controller, "Megaman X");
					} else {
						arrayStage[currentStage] = new GameWorldMapleStory(controller, "Maple Story");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (!isLoading) {
			arrayStage[currentStage].upload();
		}
	}

	public void draw(Graphics2D g2) {
		arrayStage[currentStage].draw(g2);
	}

	public void actionPress(KeyEvent e) {
		arrayStage[currentStage].getAction().keyPress(e.getKeyCode());
	}

	public void actionRelease(KeyEvent e) {
		arrayStage[currentStage].getAction().keyRelease(e.getKeyCode());
	}

	public GameWorld[] getArrayStage() {
		return arrayStage;
	}

	public void setArrayStage(GameWorld[] arrayStage) {
		this.arrayStage = arrayStage;
	}

	public int getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(int currentStage) {
		this.currentStage = currentStage;
	}

	public GameWorldMegamanX getMegamanX() {
		return megamanX;
	}

	public void setMegamanX(GameWorldMegamanX megamanX) {
		this.megamanX = megamanX;
	}

	public GameWorldMapleStory getMapbleStory() {
		return mapbleStory;
	}

	public void setMapbleStory(GameWorldMapleStory mapbleStory) {
		this.mapbleStory = mapbleStory;
	}

	public GameWorldLoading getLoading() {
		return loading;
	}

	public void setLoading(GameWorldLoading loading) {
		this.loading = loading;
	}

	public boolean isLoading() {
		return isLoading;
	}

	public void setLoading(boolean isLoading) {
		this.isLoading = isLoading;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

}
