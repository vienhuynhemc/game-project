package manager;

import particulerObject.ParticulerObjectMegamanX;
import robotDropTheThonrs.Thorns;

public class BulletManagerMegamanX extends ParticulerManagerMegamanX {

	public BulletManagerMegamanX() {
	}

	@Override
	public void upload() {
		synchronized (getListObject()) {
			for (int i = 0; i < getListObject().size(); i++) {
				if (getListObject().get(i).isOutCamera() || getListObject().get(i).getStage() == ParticulerObjectMegamanX.DEATH) {
					if (getListObject().get(i).getClass() == Thorns.class) {
						if (getListObject().get(i).getY() > 3600) {
							getListObject().remove(i);
						}else {
							getListObject().get(i).upload();
						}
					} else {
						getListObject().remove(i);
					}
				} else if (!getListObject().get(i).isOutCamera()) {
					getListObject().get(i).upload();
				}
			}
		}
	}

}
