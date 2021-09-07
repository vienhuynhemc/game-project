package manager;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import effortMapleStory.EffortMapleStory;
import gameObject.Draw;
import gameObject.Upload;

public class EffortMapleStoryManager implements Draw, Upload {

	private List<EffortMapleStory> listObject;

	public EffortMapleStoryManager() {
		setListObject(Collections.synchronizedList(new LinkedList<EffortMapleStory>()));
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

	public void addEF(EffortMapleStory w) {
		synchronized (listObject) {
			listObject.add(w);
		}
	}

	public List<EffortMapleStory> getListObject() {
		return listObject;
	}

	public void setListObject(List<EffortMapleStory> listObject) {
		this.listObject = listObject;
	}

}
