package manager;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import gameObject.Draw;
import gameObject.Upload;
import whenDeath.WhenDeadMotLan;

public class WhenDeadMotLanManager implements Draw, Upload {

	private List<WhenDeadMotLan> listObject;

	public WhenDeadMotLanManager() {
		setListObject(Collections.synchronizedList(new LinkedList<WhenDeadMotLan>()));
	}

	@Override
	public void upload() throws IOException {
		synchronized (listObject) {
			for (int i = 0; i < listObject.size(); i++) {
				listObject.get(i).upload();
				if (listObject.get(i).isComplete())
					listObject.remove(i);
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		synchronized (listObject) {
			for (int i = 0; i < listObject.size(); i++) {
				listObject.get(i).draw(g2);
			}
		}
	}

	public void addWhenDead(WhenDeadMotLan w) {
		synchronized (listObject) {
			listObject.add(w);
		}
	}

	public List<WhenDeadMotLan> getListObject() {
		return listObject;
	}

	public void setListObject(List<WhenDeadMotLan> listObject) {
		this.listObject = listObject;
	}

}
