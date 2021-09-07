package conRan;

import java.awt.Color;
import java.awt.Graphics2D;

import gameObject.GameObject;
import gameObject.GameWorld;

public class Point extends GameObject {

	private int floatX;
	private int floatY;

	private int floatXQuaKhu;
	private int floatYQuaKhu;

	public Point(int floatX, int floatY, GameWorld game) {
		super(floatX, floatY, game);

		this.floatX = floatX;
		this.floatY = floatY;

		floatXQuaKhu = floatX;
		floatYQuaKhu = floatY;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		g2.fillRect(floatX, floatY, 10, 10);
		g2.setColor(Color.WHITE);
		g2.drawRect(floatX, floatY, 10, 10);
	}

	@Override
	public void upload() {
	}

	public int getFloatX() {
		return floatX;
	}

	public void setFloatX(int floatX) {
		this.floatX = floatX;
	}

	public int getFloatY() {
		return floatY;
	}

	public void setFloatY(int floatY) {
		this.floatY = floatY;
	}

	public int getFloatXQuaKhu() {
		return floatXQuaKhu;
	}

	public void setFloatXQuaKhu(int floatXQuaKhu) {
		this.floatXQuaKhu = floatXQuaKhu;
	}

	public int getFloatYQuaKhu() {
		return floatYQuaKhu;
	}

	public void setFloatYQuaKhu(int floatYQuaKhu) {
		this.floatYQuaKhu = floatYQuaKhu;
	}

}
