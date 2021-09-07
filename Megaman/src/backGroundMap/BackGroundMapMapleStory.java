package backGroundMap;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import bossIntroStage.TheFieldFinalBoss;
import gameWorld.GameWorldMapleStory;

public class BackGroundMapMapleStory extends BackGroundMap {

	public BackGroundMapMapleStory(float x, float y, GameWorldMapleStory game) throws IOException {
		super(x, y, game);

		setBackGroundMap(ImageIO.read(new File("data/backGroundMapleStory.png")));
		setImageIntro(ImageIO.read(new File("data/backGroundMapMapleStory.png")));
	}

	@Override
	public void upload() {

	}

	@Override
	public TheFieldFinalBoss getSanDauFinalBoss() {
		return null;
	}

}
