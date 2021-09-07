package model;

import java.util.Random;

import control.Action;

public class GameWorld {

	public int[][] array;
	private O[][] arrayO;
	private Random rd;
	private Action action;

	public GameWorld() {
		array = new int[4][4];
		arrayO = new O[4][4];

		int x = 10;
		int y = 10;

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = -1;
				arrayO[i][j] = new O(x, y, -1, 100, 100);
				x += 110;
			}
			x = 10;
			y += 110;
		}
		rd = new Random();

		dien2So();

		action = new Action(this);

	}

	public void action(int j, int k, int i) {

		boolean check = false;

		for (int l = j + 1; l < k; l++) {
			if (array[l][i] != -1) {
				check = true;
			}
		}

		if (!check) {

			if (array[j][i] == array[k][i] && array[j][i] != -1) {
				array[j][i] = array[j][i] + array[k][i];
				array[k][i] = -1;
			}

		}

	}

	public int getSum(int j, int k) {
		int count = 0;
		for (int i = k; i < array.length; i++) {
			if (array[i][j] == -1)
				count++;
		}

		return count;
	}

	public boolean isCompleteDon() {

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == -1) {
					int sum = getSum(j, i);
					if (i != 4 - sum)
						return false;
				}
			}
		}

		return true;

	}

	public void donLen() {
		while (!isCompleteDon()) {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[i].length; j++) {
					if (array[i][j] == -1) {
						for (int j2 = array.length - 1; j2 > i; j2--) {
							if (array[j2 - 1][j] == -1) {
								array[j2 - 1][j] = array[j2][j];
								array[j2][j] = -1;
							}
						}
					}
				}
			}
		}

	}

	public void up() {
		for (int i = 0; i < array[0].length; i++) {
			for (int j = 0; j < array.length; j++) {
				for (int k = j + 1; k < array.length; k++) {
					action(j, k, i);
				}
			}
		}

		donLen();

		dien2So();
	}

	public void dien2So() {

		int soConLai = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == -1)
					soConLai++;
			}
		}

		int max = 0;
		if (soConLai >= 2) {
			int b = rd.nextInt(10);
			if (b % 2 == 0) {
				max = 2;
			} else {
				max = 1;
			}
		} else {
			max = 1;
		}

		int count = 0;
		while (count < max) {
			int i = rd.nextInt(4);
			int j = rd.nextInt(4);

			if (array[i][j] == -1) {
				int a = rd.nextInt(10);
				if (a % 2 == 0) {
					array[i][j] = 4;
				} else {
					array[i][j] = 2;
				}
				count++;
			}
		}
	}

	public int[][] getArray() {
		return array;
	}

	public void setArray(int[][] array) {
		this.array = array;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

	public O[][] getArrayO() {
		return arrayO;
	}

	public void setArrayO(O[][] arrayO) {
		this.arrayO = arrayO;
	}

	public void upload() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				arrayO[i][j].setA(array[i][j]);
			}
		}
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void right() {

	}

	public void left() {

	}

	public void down() {

	}

}
