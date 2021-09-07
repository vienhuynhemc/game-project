package frontMap;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gameObject.GameObjectMegamanX;
import gameWorld.GameWorldMegamanX;

public class FrontMap extends GameObjectMegamanX {

	private List<FrontMapItem> listItemFrontMap;

	public FrontMap(float x, float y, GameWorldMegamanX game) throws IOException {
		super(x, y, game);

		listItemFrontMap = new ArrayList<FrontMapItem>();
		listItemFrontMap.add(new FrontMapItem(992, 2578, getGame(),
				getGame().getInputData().getListFrame().get("intro1").getImage()));
		listItemFrontMap.add(new FrontMapItem(4234, 3946, getGame(),
				getGame().getInputData().getListFrame().get("intro2").getImage()));

	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < listItemFrontMap.size(); i++) {
			if (!listItemFrontMap.get(i).isOutCamera()) {
				listItemFrontMap.get(i).draw(g2);
			}
		}
	}

	@Override
	public void upload() {
	}

	public List<FrontMapItem> getListItemFrontMap() {
		return listItemFrontMap;
	}

	public void setListItemFrontMap(List<FrontMapItem> listItemFrontMap) {
		this.listItemFrontMap = listItemFrontMap;
	}

}
