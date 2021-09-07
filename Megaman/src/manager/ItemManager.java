package manager;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import item.Item;

public class ItemManager {
	private List<Item> listItem;

	public ItemManager() {
		setListItem(Collections.synchronizedList(new LinkedList<Item>()));
	}

	public void addItem(Item item) {
		synchronized (listItem) {
			listItem.add(item);
		}
	}

	public void upLoad() {
		synchronized (listItem) {
			for (int i = 0; i < listItem.size(); i++) {
				if (!listItem.get(i).isOutCamera()) {
					listItem.get(i).upload();
				}
				if (listItem.get(i).isComplete()) {
					listItem.remove(i);
				}
			}
		}
	}

	public void draw(Graphics2D g2) {
		synchronized (listItem) {
			for (int i = 0; i < listItem.size(); i++) {
				if (!listItem.get(i).isOutCamera()) {
					listItem.get(i).draw(g2);
				}
			}
		}
	}

	public List<Item> getListItem() {
		return listItem;
	}

	public void setListItem(List<Item> listItem) {
		this.listItem = listItem;
	}
	
}
