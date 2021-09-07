package effortMapleStory;

import gameObject.Draw;
import gameObject.GameObjectMapleStory;
import gameObject.Upload;
import gameWorld.GameWorldMapleStory;

public abstract class EffortMapleStory extends GameObjectMapleStory implements Draw, Upload {

	private boolean isComplete;

	public EffortMapleStory(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
