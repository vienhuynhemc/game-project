package conRan;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import gameObject.GameObject;
import gameObject.GameWorld;

public class Snake extends GameObject {

	public static final int DIR_LEFT = 0;
	public static final int DIR_RIGHT = 1;
	public static final int DIR_UP = 2;
	public static final int DIR_DOWN = 3;

	private List<Point> listPoint;

	private int direction;

	public Snake(int floatX, int floatY, GameWorld game) {
		super(floatX, floatY, game);

		listPoint = new ArrayList<Point>();

		listPoint.add(new Point(floatX, floatY, getGame()));
		listPoint.add(new Point(floatX - 10, floatY, getGame()));
		listPoint.add(new Point(floatX - 20, floatY, getGame()));
		listPoint.add(new Point(floatX - 30, floatY, getGame()));

		direction = DIR_RIGHT;
	}

	@Override
	public void upload() {

		switch (direction) {
		case DIR_LEFT:
			listPoint.get(0).setFloatXQuaKhu(listPoint.get(0).getFloatX());
			listPoint.get(0).setFloatYQuaKhu(listPoint.get(0).getFloatY());
			if (listPoint.get(0).getFloatX() == 0) {
				listPoint.get(0).setFloatX(480);
			} else {
				listPoint.get(0).setFloatX(listPoint.get(0).getFloatX() - 10);
			}
			break;
		case DIR_RIGHT:
			listPoint.get(0).setFloatXQuaKhu(listPoint.get(0).getFloatX());
			listPoint.get(0).setFloatYQuaKhu(listPoint.get(0).getFloatY());
			if (listPoint.get(0).getFloatX() == 480) {
				listPoint.get(0).setFloatX(0);
			} else {
				listPoint.get(0).setFloatX(listPoint.get(0).getFloatX() + 10);
			}
			break;
		case DIR_UP:
			listPoint.get(0).setFloatXQuaKhu(listPoint.get(0).getFloatX());
			listPoint.get(0).setFloatYQuaKhu(listPoint.get(0).getFloatY());
			if (listPoint.get(0).getFloatY() == 0) {
				listPoint.get(0).setFloatY(460);
			} else {
				listPoint.get(0).setFloatY(listPoint.get(0).getFloatY() - 10);
			}
			break;
		case DIR_DOWN:
			listPoint.get(0).setFloatXQuaKhu(listPoint.get(0).getFloatX());
			listPoint.get(0).setFloatYQuaKhu(listPoint.get(0).getFloatY());
			if (listPoint.get(0).getFloatY() == 460) {
				listPoint.get(0).setFloatY(0);
			} else {
				listPoint.get(0).setFloatY(listPoint.get(0).getFloatY() + 10);
			}
			break;
		default:
			break;
		}

		for (int i = 1; i < listPoint.size(); i++) {
			if (listPoint.get(i).getFloatX() == listPoint.get(0).getFloatX()
					&& listPoint.get(i).getFloatY() == listPoint.get(0).getFloatY()) {
				getGame().setStage(GameWorld.GAME_END);
			}
			listPoint.get(i).setFloatXQuaKhu(listPoint.get(i).getFloatX());
			listPoint.get(i).setFloatYQuaKhu(listPoint.get(i).getFloatY());
			listPoint.get(i).setFloatX(listPoint.get(i - 1).getFloatXQuaKhu());
			listPoint.get(i).setFloatY(listPoint.get(i - 1).getFloatYQuaKhu());
		}

	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < listPoint.size(); i++) {
			listPoint.get(i).draw(g2);
		}
	}

	public List<Point> getListPoint() {
		return listPoint;
	}

	public void setListPoint(List<Point> listPoint) {
		this.listPoint = listPoint;
	}

	public int getDircetion() {
		return direction;
	}

	public void setDircetion(int dircetion) {
		this.direction = dircetion;
	}

	public static int getDirLeft() {
		return DIR_LEFT;
	}

	public static int getDirRight() {
		return DIR_RIGHT;
	}

	public static int getDirUp() {
		return DIR_UP;
	}

	public static int getDirDown() {
		return DIR_DOWN;
	}

}
