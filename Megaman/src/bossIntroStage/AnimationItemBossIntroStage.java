package bossIntroStage;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameEffect.Animation;
import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;

public class AnimationItemBossIntroStage extends GameObjectMegamanX {

	private List<Animation> listAnimation;

	private Random random;

	private List<StoneBossIntroStageItem> listRectangle;

	private List<Float> arraySpeedX;
	private List<Float> arraySpeedY;

	private float mass;

	private long timeStart;

	private long timeForCreate;

	public AnimationItemBossIntroStage(float x, float y, GameWorldMegamanX game) {
		super(x, y, game);

		mass = 0.1f;

		random = new Random();

		arraySpeedX = new ArrayList<Float>();
		arraySpeedX.add(-2f);
		arraySpeedX.add(-1f);
		arraySpeedX.add(0f);
		arraySpeedX.add(1f);
		arraySpeedX.add(2f);

		arraySpeedY = new ArrayList<Float>();
		arraySpeedY.add(-1f);
		arraySpeedY.add(-2f);
		arraySpeedY.add(-3f);
		arraySpeedY.add(-4f);
		arraySpeedY.add(-5f);
		arraySpeedY.add(-6f);

		listAnimation = new ArrayList<Animation>();
		listAnimation.add(new Animation(getGame().getInputData().getListAnimation().get("itemStore1")));
		listAnimation.add(new Animation(getGame().getInputData().getListAnimation().get("itemStore1")));
		listAnimation.add(new Animation(getGame().getInputData().getListAnimation().get("itemStore2")));
		listAnimation.add(new Animation(getGame().getInputData().getListAnimation().get("itemStore2")));
		listAnimation.add(new Animation(getGame().getInputData().getListAnimation().get("itemStore2")));
		listAnimation.add(new Animation(getGame().getInputData().getListAnimation().get("itemStore3")));

		listRectangle = new ArrayList<StoneBossIntroStageItem>();

		timeForCreate = 50000000;
	}

	@Override
	public void upload() {
		if (System.nanoTime() - timeStart > timeForCreate) {
			timeStart = System.nanoTime();

			int speedX = random.nextInt(arraySpeedX.size());
			int speedY = random.nextInt(arraySpeedY.size());
			int animation = random.nextInt(listAnimation.size());

			if (animation == 0 || animation == 1) {
				StoneBossIntroStageItem item1 = new StoneBossIntroStageItem(getX(), getY(), getGame(),
						listAnimation.get(animation), 84, 78, arraySpeedX.get(speedX), arraySpeedY.get(speedY),
						getMass());
				listRectangle.add(item1);

			} else if (animation == 5) {
				StoneBossIntroStageItem item3 = new StoneBossIntroStageItem(getX(), getY(), getGame(),
						listAnimation.get(animation), 54, 22, arraySpeedX.get(speedX), arraySpeedY.get(speedY),
						getMass());
				listRectangle.add(item3);
			} else {
				StoneBossIntroStageItem item2 = new StoneBossIntroStageItem(getX(), getY(), getGame(),
						listAnimation.get(animation), 54, 44, arraySpeedX.get(speedX), arraySpeedY.get(speedY),
						getMass());
				listRectangle.add(item2);
			}
		}

		for (int i = 0; i < listRectangle.size(); i++) {
			listRectangle.get(i).upload();
			if (listRectangle.get(i).isComplete())
				listRectangle.remove(i);
		}
	}

	public void draw(Graphics2D g2) {
		for (StoneBossIntroStageItem item : listRectangle) {
			item.draw(g2);
		}
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public List<Animation> getListAnimation() {
		return listAnimation;
	}

	public void setListAnimation(List<Animation> listAnimation) {
		this.listAnimation = listAnimation;
	}

	public List<StoneBossIntroStageItem> getListRectangle() {
		return listRectangle;
	}

	public void setListRectangle(List<StoneBossIntroStageItem> listRectangle) {
		this.listRectangle = listRectangle;
	}

	public List<Float> getArraySpeedX() {
		return arraySpeedX;
	}

	public void setArraySpeedX(List<Float> arraySpeedX) {
		this.arraySpeedX = arraySpeedX;
	}

	public List<Float> getArraySpeedY() {
		return arraySpeedY;
	}

	public void setArraySpeedY(List<Float> arraySpeedY) {
		this.arraySpeedY = arraySpeedY;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public long getTimeForCreate() {
		return timeForCreate;
	}

	public void setTimeForCreate(long timeForCreate) {
		this.timeForCreate = timeForCreate;
	}

}
