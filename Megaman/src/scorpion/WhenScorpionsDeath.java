package scorpion;

import java.awt.Graphics2D;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;
import whenDeath.WhenDeath;

public class WhenScorpionsDeath extends WhenDeath {

	private Animation removeLeft, removeRight;;

	private int director;

	public WhenScorpionsDeath(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0.1f);

		removeLeft = new Animation(getGame().getInputData().getListAnimation().get("boCapDeath"));
		removeLeft.setIsRepeat(false);
		removeRight = new Animation(getGame().getInputData().getListAnimation().get("boCapDeath"));
		removeRight.daoNguoc();
		removeRight.setIsRepeat(false);

		this.director = director;
	}

	@Override
	public void upload() {
		if (director == ParticulerObjectMegamanX.DIR_LEFT) {
			if (removeLeft.isLastFrame()) {
				setComplete(true);
			}

		} else if (director == ParticulerObjectMegamanX.DIR_RIGHT) {
			if (removeRight.isLastFrame()) {
				setComplete(true);
			}

		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (director == ParticulerObjectMegamanX.DIR_LEFT) {
			if (!removeLeft.isLastFrame()) {
				removeLeft.upload(System.nanoTime());
				removeLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}

		} else if (director == ParticulerObjectMegamanX.DIR_RIGHT) {
			if (!removeRight.isLastFrame()) {
				removeRight.upload(System.nanoTime());
				removeRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}

		}
	}

}
