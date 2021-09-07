package tool;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;

public class BossDoor extends Tool {

	public static final int OPEN = 0;
	public static final int CLOSE = 1;

	private Animation open;
	private Animation close;

	private int stage;

	private boolean isRemovePhysicalMap;

	public BossDoor(float x, float y, GameWorldMegamanX game) {
		super(x, y, game, 96, 32);

		open = new Animation(getGame().getInputData().getListAnimation().get("bossDoorOpen"));
		open.setIsRepeat(false);
		close = new Animation(getGame().getInputData().getListAnimation().get("bossDoorClose"));
		close.setIsRepeat(false);

		close.setCurrentFrame(close.getListImage().size() - 1);

		setStage(CLOSE);

	}

	@Override
	public void draw(Graphics2D g2) {
		if (getStage() == OPEN) {
			open.upload(System.nanoTime());
			open.draw(g2, (int) (getX() - getGame().getCamera().getX()), (int) (getY() - getGame().getCamera().getY()));
		} else if (getStage() == CLOSE) {
			close.upload(System.nanoTime());
			close.draw(g2, (int) (getX() - getGame().getCamera().getX()),
					(int) (getY() - getGame().getCamera().getY()));
		}
	}

	@Override
	public Rectangle getRectangleXuLyVaCham() {
		Rectangle rect = super.getRectangleXuLyVaCham();
		return new Rectangle(rect.x - 15, rect.y, rect.width + 30, rect.height);
	}

	@Override
	public void upload() {
		super.upload();
		if ((getGame().getSTAGE() == GameWorldMegamanX.ATTACKBOSS
				|| getGame().getSTAGE() == GameWorldMegamanX.PLAYTUTORIAL) && isRemovePhysicalMap) {
			setStage(CLOSE);
			close.reset();
			open.reset();
			int count = 0;
			int x = (int) getX() + 10;
			int y = (int) getY() + 12;
			while (count < 4) {
				int floatX = x / 30;
				int floatY = y / 30;
				getGame().getPhysicalMap().getArray()[floatY][floatX] = 1;
				y = y + 30;
				count++;
			}
			isRemovePhysicalMap = false;
		} else if (getGame().getSTAGE() != GameWorldMegamanX.ATTACKBOSS && getStage() == OPEN && open.isLastFrame()) {
			if (!isRemovePhysicalMap) {
				int count = 0;
				int x = (int) getX() + 10;
				int y = (int) getY() + 12;
				while (count < 4) {
					int floatX = x / 30;
					int floatY = y / 30;
					getGame().getPhysicalMap().getArray()[floatY][floatX] = 0;
					y = y + 30;
					count++;
				}
				isRemovePhysicalMap = true;
			}
		}
	}

	public Animation getOpen() {
		return open;
	}

	public void setOpen(Animation open) {
		this.open = open;
	}

	public Animation getClose() {
		return close;
	}

	public void setClose(Animation close) {
		this.close = close;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

}
