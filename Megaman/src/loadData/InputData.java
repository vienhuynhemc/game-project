package loadData;

import java.util.HashMap;

import gameEffect.Animation;
import gameEffect.FrameImage;
import gameWorld.GameWorld;

public abstract class InputData implements LoadData {

	private HashMap<String, FrameImage> listFrame;
	private HashMap<String, Animation> listAnimation;
	private GameWorld game;

	public InputData(GameWorld game) {
		listFrame = new HashMap<String, FrameImage>();
		listAnimation = new HashMap<String, Animation>();
		this.game = game;
	}

	public HashMap<String, FrameImage> getListFrame() {
		return listFrame;
	}

	public void setListFrame(HashMap<String, FrameImage> listFrame) {
		this.listFrame = listFrame;
	}

	public HashMap<String, Animation> getListAnimation() {
		return listAnimation;
	}

	public void setListAnimation(HashMap<String, Animation> listAnimation) {
		this.listAnimation = listAnimation;
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

	public abstract int[][] getPhysicalMap();

}
