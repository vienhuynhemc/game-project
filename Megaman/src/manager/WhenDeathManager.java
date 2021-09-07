package manager;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import whenDeath.WhenDeath;

public class WhenDeathManager {

	private List<WhenDeath> listWhenDeath;

	public WhenDeathManager() {
		this.listWhenDeath = Collections.synchronizedList(new LinkedList<WhenDeath>());
	}

	public void addWhenDeath(WhenDeath whenDeath) {
		synchronized (listWhenDeath) {
			listWhenDeath.add(whenDeath);
		}
	}

	public void upLoad() {
		synchronized (listWhenDeath) {
			for (int i = 0; i < listWhenDeath.size(); i++) {
				listWhenDeath.get(i).upload();
				if (listWhenDeath.get(i).isComplete())
					listWhenDeath.remove(i);
			}
		}
	}

	public void draw(Graphics2D g2) {
		synchronized (listWhenDeath) {
			for (int i = 0; i < listWhenDeath.size(); i++) {
				listWhenDeath.get(i).draw(g2);
			}
		}
	}

	public List<WhenDeath> getListWhenDeath() {
		return listWhenDeath;
	}

	public void setListWhenDeath(List<WhenDeath> listWhenDeath) {
		this.listWhenDeath = listWhenDeath;
	}

}
