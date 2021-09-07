package healthBarEnemyMapleStory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HealBarEnemies {

	private String name;
	private int hpMax;
	private int hpNow;
	private float x;
	private float y;
	private int w;
	private int h;

	public HealBarEnemies(String name, int hpMax, int hpNow, float x, float y, int w, int h) {
		this.name = name;
		this.hpMax = hpMax;
		this.hpNow = hpNow;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void upload(String name, int hpMax, int hpNow, float x, float y) {
		this.name = name;
		this.hpMax = hpMax;
		this.hpNow = hpNow;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(new Color(20, 20, 20, 180));
		g2.fillRoundRect((int) x - 10, (int) y - 30, w, h, 10, 10);
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(3f));
		g2.drawRoundRect((int) x - 5, (int) y - 25, w - 10, h - 10, 10, 10);
		g2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		g2.drawString("[" + name + "] " + hpNow + "/" + hpMax, x, y - 10);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getHpNow() {
		return hpNow;
	}

	public void setHpNow(int hpNow) {
		this.hpNow = hpNow;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
}
