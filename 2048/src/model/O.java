package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class O {

	private int a;
	private int w;
	private int h;
	private int x;
	private int y;

	public O(int x, int y, int a, int w, int h) {
		this.a = a;
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, w, h, 15, 15);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		if (a != -1) {
			g.drawString(String.valueOf(a), x + w / 2, y + h / 2);
		}
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

}
