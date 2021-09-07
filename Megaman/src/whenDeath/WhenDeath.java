package whenDeath;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;

public abstract class WhenDeath extends GameObjectMegamanX {

	private List<Float> listSpeedX;
	private List<Float> listSpeedY;
	private List<Rectangle> listRectangle;
	private HashMap<Integer, Long> timeSurivals;
	private HashMap<Integer, Long> timeStages;
	private HashMap<Integer, Integer> listStage;
	private List<Boolean> listIgnoreRect;
	private List<Boolean> listIgnoreDrawRect;

	private long timeForSurival;
	private long timeForStage;

	private float mass;

	private boolean isComplete;

	public WhenDeath(float x, float y, GameWorldMegamanX game, float mass) {
		super(x, y, game);

		listSpeedX = new ArrayList<Float>();
		listSpeedY = new ArrayList<Float>();
		listRectangle = new ArrayList<Rectangle>();
		listIgnoreRect = new ArrayList<Boolean>();
		listIgnoreDrawRect = new ArrayList<Boolean>();
		listStage = new HashMap<Integer, Integer>();
		timeSurivals = new HashMap<Integer, Long>();
		timeStages = new HashMap<Integer, Long>();

		this.mass = mass;

		timeForSurival = 5000;
		timeForStage = 2000;

		isComplete = false;
	}

	@Override
	public void upload() {
		for (int i = 0; i < listRectangle.size(); i++) {
			if (getGame().getPhysicalMap().vaChamMatDat(listRectangle.get(i)) != null && listIgnoreRect.get(i) == false) {
				listSpeedX.set(i, (float) 0);
				listSpeedY.set(i, (float) 0);
				listRectangle.get(i).y = (int) (getGame().getPhysicalMap().vaChamMatDat(listRectangle.get(i)).getY()
						- listRectangle.get(i).height + 1);
				listIgnoreRect.set(i, true);
				timeSurivals.put(i, System.currentTimeMillis());
				listStage.put(i, ParticulerObjectMegamanX.ALIVE);
			} else if (getGame().getPhysicalMap().vaChamMatDat(listRectangle.get(i)) == null) {
				listRectangle.get(i).x = (int) (listRectangle.get(i).x + listSpeedX.get(i));
				listRectangle.get(i).y = (int) (listRectangle.get(i).y + listSpeedY.get(i));
				listSpeedY.set(i, listSpeedY.get(i) + mass);
			}
		}

		for (Entry<Integer, Long> entry : timeSurivals.entrySet()) {
			if (listStage.get(entry.getKey()) == ParticulerObjectMegamanX.ALIVE) {
				if (System.currentTimeMillis() - entry.getValue() > timeForSurival
						&& !listIgnoreDrawRect.get(entry.getKey())) {
					listStage.put(entry.getKey(), ParticulerObjectMegamanX.FEY);
					timeStages.put(entry.getKey(), System.currentTimeMillis());
				}
			} else if (listStage.get(entry.getKey()) == ParticulerObjectMegamanX.FEY) {
				if (System.currentTimeMillis() - timeStages.get(entry.getKey()) > timeForStage
						&& !listIgnoreDrawRect.get(entry.getKey())) {
					listIgnoreDrawRect.set(entry.getKey(), true);
				}
			}
		}

		int index = 0;
		for (int i = 0; i < listIgnoreDrawRect.size(); i++) {
			if (listIgnoreDrawRect.get(i))
				index++;
		}

		if (index == listIgnoreDrawRect.size())
			isComplete = true;

		if (check())
			isComplete = true;
	}

	public abstract void draw(Graphics2D g2);

	public boolean check() {
		int index = 0;
		for (int i = 0; i < listRectangle.size(); i++) {
			if (isOutCamera(listRectangle.get(i)))
				index++;
		}
		if (index == listRectangle.size())
			return true;
		else
			return false;

	}

	public boolean isOutCamera(Rectangle rect) {
		if (getGame().getCamera().getX() + getGame().getCamera().getWidth() < rect.getX())
			return true;
		if (rect.getX() + rect.getWidth() * 2 < getGame().getCamera().getX())
			return true;
		if (getGame().getCamera().getY() > rect.getY() + rect.getHeight() * 2)
			return true;
		if (getGame().getCamera().getY() + getGame().getCamera().getHeight() < rect.getY())
			return true;
		return false;
	}

	public List<Float> getListSpeedX() {
		return listSpeedX;
	}

	public void setListSpeedX(List<Float> listSpeedX) {
		this.listSpeedX = listSpeedX;
	}

	public List<Float> getListSpeedY() {
		return listSpeedY;
	}

	public void setListSpeedY(List<Float> listSpeedY) {
		this.listSpeedY = listSpeedY;
	}

	public List<Rectangle> getListRectangle() {
		return listRectangle;
	}

	public void setListRectangle(List<Rectangle> listRectangle) {
		this.listRectangle = listRectangle;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public HashMap<Integer, Long> getTimeSurivals() {
		return timeSurivals;
	}

	public void setTimeSurivals(HashMap<Integer, Long> timeSurivals) {
		this.timeSurivals = timeSurivals;
	}

	public List<Boolean> getListIgnoreRect() {
		return listIgnoreRect;
	}

	public void setListIgnoreRect(List<Boolean> listIgnoreRect) {
		this.listIgnoreRect = listIgnoreRect;
	}

	public long getTimeForSurival() {
		return timeForSurival;
	}

	public void setTimeForSurival(long timeForSurival) {
		this.timeForSurival = timeForSurival;
	}

	public List<Boolean> getListIgnoreDrawRect() {
		return listIgnoreDrawRect;
	}

	public void setListIgnoreDrawRect(List<Boolean> listIgnoreDrawRect) {
		this.listIgnoreDrawRect = listIgnoreDrawRect;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public HashMap<Integer, Integer> getListStage() {
		return listStage;
	}

	public void setListStage(HashMap<Integer, Integer> listStage) {
		this.listStage = listStage;
	}

	public long getTimeForStage() {
		return timeForStage;
	}

	public void setTimeForStage(long timeForStage) {
		this.timeForStage = timeForStage;
	}

	public HashMap<Integer, Long> getTimeStages() {
		return timeStages;
	}

	public void setTimeStages(HashMap<Integer, Long> timeStages) {
		this.timeStages = timeStages;
	}

}
