package manager;

import particulerObject.ParticulerObjectMegamanX;

public class BulletManagerMapleStory extends ParticulerManagerMapleStory {

	public BulletManagerMapleStory() {
	}

	@Override
	public void upload() {
		synchronized (getListObject()) {
			for (int i = 0; i < getListObject().size(); i++) {
				if (getListObject().get(i).isOutCamera()
						|| getListObject().get(i).getStage() == ParticulerObjectMegamanX.DEATH) {
					getListObject().remove(i);
				} else if (!getListObject().get(i).isOutCamera()) {
					getListObject().get(i).upload();
				}
			}
		}
	}

}
