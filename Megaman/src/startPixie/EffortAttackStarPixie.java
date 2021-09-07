package startPixie;

import java.awt.Graphics2D;
import java.io.IOException;

import effortMapleStory.EffortMapleStory;
import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;

public class EffortAttackStarPixie extends EffortMapleStory {

	private Animation anime;

	public EffortAttackStarPixie(float x, float y, GameWorldMapleStory game) {
		super(x, y, game);

		anime = getGame().getInputData().getListAnimation().get("efAttackSp");
	}

	@Override
	public void draw(Graphics2D g2) {
		anime.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
	}

	@Override
	public void upload() throws IOException {
		anime.upload(System.nanoTime());
		if (anime.isLastFrame())
			setComplete(true);
	}

}
