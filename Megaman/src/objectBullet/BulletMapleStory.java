package objectBullet;

import java.awt.Graphics2D;

import gameWorld.GameWorldMapleStory;
import mainPlayerMapleStory.Mario;
import particulerObject.ParticulerObjectMapleStory;
import sound.ManagerSound;

public abstract class BulletMapleStory extends ParticulerObjectMapleStory {
	private boolean isTrung;
	private boolean isPush;

	public BulletMapleStory(float x, float y, GameWorldMapleStory game, float mass, float width, float height,
			int blood, int dame) {
		super(x, y, game, mass, width, height, blood, dame);
	}

	public abstract void draw(Graphics2D g2);

	public boolean isChamTuong() {
		if (getGame().getPhysicalMap().danVaChamTuong(getRectangleSizeObject()) != null)
			return true;
		else
			return false;
	}

	@Override
	public void upload() {
		super.upload();

		setX(getX() + getSpeedX());
		setY(getY() + getSpeedY());

		for (int i = 0; i < getGame().getListObject().getListObject().size(); i++) {
			if (!getGame().getListObject().getListObject().get(i).isOutCamera() && getGame().getListObject()
					.getListObject().get(i).getRectangleXuLiVaCham().intersects(getRectangleXuLiVaCham())) {
				if (getGame().getListObject().getListObject().get(i).getStage() == ALIVE
						|| getGame().getListObject().getListObject().get(i).getStage() == FEY) {
					if (getGame().getListObject().getListObject().get(i).getTeam() != getTeam()) {

						getGame().getListObject().getListObject().get(i)
								.setBlood(getGame().getListObject().getListObject().get(i).getBlood() - getDame());

						getGame().getListObject().getListObject().get(i).setStage(BEHURT);
						getGame().getListObject().getListObject().get(i).setTimeBeginBehurt(System.nanoTime());

						if (getGame().getListObject().getListObject().get(i).getClass() == Mario.class) {
							ManagerSound.getInstance().getListSound().get("rmbehurt").start();
							ManagerSound.getInstance().getListSound().get("rmbehurt")
									.setFramePosition(0);
						}

						if (getGame().getListObject().getListObject().get(i).getBlood() > 0) {
							setTrung(true);
						}
						isPush = true;
					}
				}
			}
		}
	}

	public boolean isTrung() {
		return isTrung;
	}

	public void setTrung(boolean isTrung) {
		this.isTrung = isTrung;
	}

	public boolean isPush() {
		return isPush;
	}

	public void setPush(boolean isPush) {
		this.isPush = isPush;
	}

}
