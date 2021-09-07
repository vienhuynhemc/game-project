package manager;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import gameObject.Draw;
import gameObject.Upload;
import mainPlayerMapleStory.Mario;
import particulerObject.ParticulerObjectMapleStory;
import yeti.Yeti;

public class ParticulerManagerMapleStory implements Draw,Upload{
	private List<ParticulerObjectMapleStory> listObject;
	private List<ParticulerObjectMapleStory> listReset;

	public ParticulerManagerMapleStory() {
		listObject = Collections.synchronizedList(new LinkedList<ParticulerObjectMapleStory>());
		listReset = Collections.synchronizedList(new LinkedList<ParticulerObjectMapleStory>());
	}

	public void addObject(ParticulerObjectMapleStory doiTuong) {
		synchronized (listObject) {
			listObject.add(doiTuong);
		}
	}

	public void removeObject(ParticulerObjectMapleStory doiTuong) {
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
				if (!listObject.get(i).isOutCamera() && listObject.get(i).getClass() != Mario.class) {
					listObject.get(i).upload();
					if (listObject.get(i).getStage() == ParticulerObjectMapleStory.DEATH) {
						synchronized (listReset) {
							if (listObject.get(i).getClass() == Yeti.class) {
								Yeti dtg = (Yeti) listObject.get(i);
								dtg.setX(dtg.getxPrimaral());
								dtg.setY(dtg.getyPrimaral());
								dtg.setDirector(dtg.getDirectorPrimaral());
								dtg.setStage(ParticulerObjectMapleStory.ALIVE);
								dtg.setBlood(dtg.getBloodPrimaral());
								dtg.setSTAGE(0);
								listReset.add(dtg);
							} else {
								ParticulerObjectMapleStory dtg = listObject.get(i);
								dtg.setX(dtg.getxPrimaral());
								dtg.setY(dtg.getyPrimaral());
								dtg.setDirector(dtg.getDirectorPrimaral());
								dtg.setStage(ParticulerObjectMapleStory.ALIVE);
								dtg.setBlood(dtg.getBloodPrimaral());
								listReset.add(dtg);
							}
						}
						listObject.remove(i);
					}
				} else if (listObject.get(i).getClass() == Mario.class) {
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

	public List<ParticulerObjectMapleStory> getListObject() {
		return listObject;
	}

	public void setListObject(List<ParticulerObjectMapleStory> listObject) {
		this.listObject = listObject;
	}

	public List<ParticulerObjectMapleStory> getListReset() {
		return listReset;
	}

	public void setListReset(List<ParticulerObjectMapleStory> listReset) {
		this.listReset = listReset;
	}

}
