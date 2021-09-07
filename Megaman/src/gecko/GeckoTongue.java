package gecko;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;

public class GeckoTongue extends ParticulerObjectMegamanX {

	private List<APartOfTheGeckoTongue> listItem;

	private boolean isComplete;
	private boolean isChamTuong;

	private boolean isShooting;
	private long timeStartShooting;

	private float xStart;
	private float yStart;

	private APartOfTheGeckoTongue end;
	private boolean isStartRemove;
	private long startRemove;

	public GeckoTongue(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0f, 0, 0, 1000, 0);

		listItem = new ArrayList<APartOfTheGeckoTongue>();

		xStart = getX();
		yStart = getY();

		setDirector(director);

		setTeam(ENEMI_TEAM);
	}

	@Override
	public void upload() {
		super.upload();

		for (int i = 0; i < listItem.size(); i++) {
			listItem.get(i).upload();
		}

		if (isStartRemove && !isComplete) {
			if (System.nanoTime() - startRemove > 25000000) {
				getGame().getListObject().getListObject().remove(listItem.get(listItem.size() - 1));
				listItem.remove(listItem.get(listItem.size() - 1));
				startRemove = System.nanoTime();
			}
			if (listItem.size() == 0) {
				isComplete = true;
			}
		} else if (isChamTuong) {
			if (end.isComplete()) {
				isStartRemove = true;
				startRemove = System.nanoTime();
				listItem.remove(listItem.size() - 1);
				isChamTuong = false;
			}
		}

		if (!isChamTuong && !isStartRemove) {
			if (!isShooting) {
				isShooting = true;
				timeStartShooting = System.nanoTime();

				APartOfTheGeckoTongue motDoan = new APartOfTheGeckoTongue(xStart, yStart, getGame(), getDirector(),
						APartOfTheGeckoTongue.STAGE_NORMAL);
				motDoan.upload();

				if (getDirector() == DIR_LEFT) {
					xStart -= 32;
					yStart += 16;
				} else if (getDirector() == DIR_RIGHT) {
					xStart += 32;
					yStart += 16;
				}

				APartOfTheGeckoTongue motDoan2 = new APartOfTheGeckoTongue(xStart, yStart, getGame(), getDirector(),
						APartOfTheGeckoTongue.STAGE_NORMAL);

				if (getDirector() == DIR_LEFT) {
					xStart += 32;
					yStart -= 16;
				} else if (getDirector() == DIR_RIGHT) {
					xStart -= 32;
					yStart -= 16;
				}

				motDoan.upload();
				motDoan2.upload();
				if (motDoan2.isCham()) {

					isChamTuong = true;
					end = new APartOfTheGeckoTongue(xStart, yStart, getGame(), getDirector(),
							APartOfTheGeckoTongue.STAGE_END);
					listItem.add(end);

				} else {

					listItem.add(motDoan);
					getGame().getListObject().addObject(motDoan);

					if (getDirector() == DIR_LEFT) {
						xStart -= 32;
						yStart += 16;
					} else if (getDirector() == DIR_RIGHT) {
						xStart += 32;
						yStart += 16;
					}
				}
			} else {
				if (System.nanoTime() - timeStartShooting > 25000000) {
					isShooting = false;
				}
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		return getRectangleSizeObject();
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();

		g2.setColor(Color.YELLOW);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
	}

	@Override
	public void draw(Graphics2D g2) {
		if (isChamTuong && !isStartRemove) {
			listItem.get(listItem.size() - 1).upload();
			listItem.get(listItem.size() - 1).draw(g2);
		}
	}

	public List<APartOfTheGeckoTongue> getListItem() {
		return listItem;
	}

	public void setListItem(List<APartOfTheGeckoTongue> listItem) {
		this.listItem = listItem;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public long getTimeStartShooting() {
		return timeStartShooting;
	}

	public void setTimeStartShooting(long timeStartShooting) {
		this.timeStartShooting = timeStartShooting;
	}

	public float getxStart() {
		return xStart;
	}

	public void setxStart(float xStart) {
		this.xStart = xStart;
	}

	public float getyStart() {
		return yStart;
	}

	public void setyStart(float yStart) {
		this.yStart = yStart;
	}

	public boolean isChamTuong() {
		return isChamTuong;
	}

	public void setChamTuong(boolean isChamTuong) {
		this.isChamTuong = isChamTuong;
	}

	public APartOfTheGeckoTongue getEnd() {
		return end;
	}

	public void setEnd(APartOfTheGeckoTongue end) {
		this.end = end;
	}

	public boolean isStartRemove() {
		return isStartRemove;
	}

	public void setStartRemove(boolean isStartRemove) {
		this.isStartRemove = isStartRemove;
	}

	public long getStartRemove() {
		return startRemove;
	}

	public void setStartRemove(long startRemove) {
		this.startRemove = startRemove;
	}

}
