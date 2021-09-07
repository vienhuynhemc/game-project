package objectBullet;

import java.awt.Graphics2D;

import gameWorld.GameWorldMegamanX;
import mainPlayerMegamanX.RockMan;
import particulerObject.ParticulerObjectMegamanX;
import sound.ManagerSound;

public abstract class BulletMegamanX extends ParticulerObjectMegamanX {

	private boolean isTrung;

	public BulletMegamanX(float x, float y, GameWorldMegamanX game, float mass, float width, float height, int blood,
			int dame) {
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

						if (getGame().getListObject().getListObject().get(i).getBlood() > 0) {

							setTrung(true);

							if (getGame().getListObject().getListObject().get(i).getClass().equals(RockMan.class)) {

								ManagerSound.getInstance().getListSound().get("rmbehurt").start();
								ManagerSound.getInstance().getListSound().get("rmbehurt")
										.setFramePosition(0);
								getGame().getRockMan().setTimBeginBehurt(System.nanoTime());
								getGame().getRockMan().setStage(BEHURT);

							}
						}
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

}
