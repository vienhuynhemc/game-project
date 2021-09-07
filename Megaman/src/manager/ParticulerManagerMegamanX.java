package manager;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import gameObject.Draw;
import gameObject.Upload;
import mainPlayerMegamanX.RockMan;
import particulerObject.ParticulerObjectMegamanX;
import robotDropTheThonrs.IntroEnemieRobotDropTheThorns;

public class ParticulerManagerMegamanX implements Draw, Upload {

	private List<ParticulerObjectMegamanX> listObject;
	private List<ParticulerObjectMegamanX> listReset;

	public ParticulerManagerMegamanX() {
		listObject = Collections.synchronizedList(new LinkedList<ParticulerObjectMegamanX>());
		listReset = Collections.synchronizedList(new LinkedList<ParticulerObjectMegamanX>());
	}

	public void addObject(ParticulerObjectMegamanX doiTuong) {
		synchronized (listObject) {
			listObject.add(doiTuong);
		}
	}

	public void removeObject(ParticulerObjectMegamanX doiTuong) {
		synchronized (listObject) {
			listObject.remove(doiTuong);
		}
	}

	public void draw(Graphics2D g2) {
		synchronized (listObject) {
			for (int i = 0; i < listObject.size(); i++) {
				if (!listObject.get(i).isOutCamera()) {
					listObject.get(i).draw(g2);
				}
			}
		}
	}

	public void upload() {
		synchronized (listObject) {
			for (int i = 0; i < listObject.size(); i++) {
				if (!listObject.get(i).isOutCamera() && listObject.get(i).getClass() != RockMan.class) {
					listObject.get(i).upload();
					if (listObject.get(i).getStage() == ParticulerObjectMegamanX.DEATH) {
						synchronized (listReset) {
							ParticulerObjectMegamanX dtg = listObject.get(i);
							dtg.setX(dtg.getxPrimaral());
							dtg.setY(dtg.getyPrimaral());
							dtg.setDirector(dtg.getDirectorPrimaral());
							dtg.setStage(ParticulerObjectMegamanX.ALIVE);
							dtg.setBlood(dtg.getBloodPrimaral());
							listReset.add(dtg);
						}
						listObject.remove(i);
					}
				} else if (listObject.get(i).getClass() == RockMan.class
						|| listObject.get(i).getClass() == IntroEnemieRobotDropTheThorns.class) {
					listObject.get(i).upload();
				}
			}
		}
		synchronized (listReset) {
			for (int i = 0; i < listReset.size(); i++) {
				if (listReset.get(i).isOutCamera()) {
					listObject.add(listReset.get(i));
					listReset.remove(i);
				}
			}
		}
	}

	public List<ParticulerObjectMegamanX> getListObject() {
		return listObject;
	}

	public void setListObject(List<ParticulerObjectMegamanX> listObject) {
		this.listObject = listObject;
	}

	public List<ParticulerObjectMegamanX> getListReset() {
		return listReset;
	}

	public void setListReset(List<ParticulerObjectMegamanX> listReset) {
		this.listReset = listReset;
	}

}
