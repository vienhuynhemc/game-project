package manager;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tool.Stairs;
import tool.Tool;
import tool.Abyss;

public class ToolManager {
	private List<Tool> listDungCu;

	public ToolManager() {
		this.listDungCu = Collections.synchronizedList(new ArrayList<Tool>());
	}

	public Stairs isVaChamCauThang(Rectangle rect) {
		synchronized (listDungCu) {
			for (int i = 0; i < listDungCu.size(); i++) {
				if (listDungCu.get(i).getRectangleXuLyVaCham().intersects(rect)) {
					if (listDungCu.get(i).getClass() == Stairs.class) {
						return (Stairs) listDungCu.get(i);
					}
				}
			}
		}
		return null;
	}

	public Abyss isVaChamVucSau(Rectangle rect) {
		synchronized (listDungCu) {
			for (int i = 0; i < listDungCu.size(); i++) {
				if (listDungCu.get(i).getRectangleXuLyVaCham().intersects(rect)) {
					if (listDungCu.get(i).getClass() == Abyss.class) {
						return (Abyss) listDungCu.get(i);
					}
				}
			}
		}
		return null;
	}

	public void add(Tool dungCu) {
		synchronized (listDungCu) {
			listDungCu.add(dungCu);
		}
	}

	public void draw(Graphics2D g2) {
		synchronized (listDungCu) {
			for (int i = 0; i < listDungCu.size(); i++) {
				if (!listDungCu.get(i).isOutCamera()) {
					listDungCu.get(i).draw(g2);
				}
			}
		}
	}

	public void upload() {
		synchronized (listDungCu) {
			for (int i = 0; i < listDungCu.size(); i++) {
				if (!listDungCu.get(i).isOutCamera()) {
					listDungCu.get(i).upload();
				}
			}
		}
	}

	public List<Tool> getListDungCu() {
		return listDungCu;
	}

	public void setListDungCu(List<Tool> listDungCu) {
		this.listDungCu = listDungCu;
	}

}
