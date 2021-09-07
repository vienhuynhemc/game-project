package model;

import java.util.ArrayList;
import java.util.List;

public class MaDiTuan {

	int[][] array;

	private int xBanDau;
	private int yBanDau;

	public MaDiTuan(int x, int y) {
		array = new int[8][8];
		this.xBanDau = x;
		this.yBanDau = y;
	}

	public List<List<Move>> getChuTrinh() {

		List<List<Move>> result = new ArrayList<List<Move>>();

		List<Move> arr = new ArrayList<Move>();

		arr.add(new Move(xBanDau, yBanDau));
		array[xBanDau][yBanDau] = 1;

		duyet(new Move(xBanDau, yBanDau), arr, result, 1);

		return result;

	}

	public void duyet(Move m, List<Move> arr, List<List<Move>> list, int count) {

		System.out.println(list.size());

		if (isFull()) {

			List<Move> l = getArrayDDMaKhongQuanTamMaTran(m.getX(), m.getY());

			for (int i = 0; i < l.size(); i++) {
				if (l.get(i).getX() == xBanDau && l.get(i).getY() == yBanDau) {
					List<Move> newList = new ArrayList<Move>();
					for (int j = 0; j < arr.size(); j++) {
						newList.add(new Move(arr.get(j).getX(), arr.get(j).getY()));
					}
					newList.add(new Move(l.get(i).getX(), l.get(i).getY()));
					list.add(newList);
					return;
				}
			}
		} else {

			List<Move> l = getArrayDDMa(m.getX(), m.getY());

			for (int i = 0; i < l.size(); i++) {

				arr.add(new Move(l.get(i).getX(), l.get(i).getY()));
				array[l.get(i).getX()][l.get(i).getY()] = 1;

				duyet(new Move(l.get(i).getX(), l.get(i).getY()), arr, list, count + 1);

				array[l.get(i).getX()][l.get(i).getY()] = 0;

				while (arr.size() != count) {
					arr.remove(arr.size() - 1);
				}

				if (list.size() > 50) {
					return;
				}
			}
		}
	}

	public boolean isFull() {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 1)
					count++;
			}
		}

		if (count == 64)
			return true;
		return false;
	}

	public List<Move> getArrayDDMaKhongQuanTamMaTran(int x, int y) {
		Move m1 = new Move(x - 2, y - 1);
		Move m2 = new Move(x - 1, y - 2);
		Move m3 = new Move(x + 1, y - 2);
		Move m4 = new Move(x + 2, y - 1);
		Move m5 = new Move(x + 1, y + 2);
		Move m6 = new Move(x + 2, y + 1);
		Move m7 = new Move(x - 2, y + 1);
		Move m8 = new Move(x - 1, y + 2);
		List<Move> l = new ArrayList<Move>();
		l.add(m1);
		l.add(m2);
		l.add(m3);
		l.add(m4);
		l.add(m5);
		l.add(m6);
		l.add(m7);
		l.add(m8);

		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < l.size(); i++) {
			Move m = l.get(i);
			if (m.getX() < 0 || m.getX() > 7 || m.getY() < 0 || m.getY() > 7)
				list.add(i);
		}

		for (int i = 0; i < list.size(); i++) {
			int a = list.get(i);
			l.remove(a);
			for (int j = i + 1; j < list.size(); j++) {
				list.set(j, list.get(j) - 1);
			}
		}

		return l;
	}

	public List<Move> getArrayDDMa(int x, int y) {
		Move m1 = new Move(x - 2, y - 1);
		Move m2 = new Move(x - 1, y - 2);
		Move m3 = new Move(x + 1, y - 2);
		Move m4 = new Move(x + 2, y - 1);
		Move m5 = new Move(x + 1, y + 2);
		Move m6 = new Move(x + 2, y + 1);
		Move m7 = new Move(x - 2, y + 1);
		Move m8 = new Move(x - 1, y + 2);
		List<Move> l = new ArrayList<Move>();
		l.add(m1);
		l.add(m2);
		l.add(m3);
		l.add(m4);
		l.add(m5);
		l.add(m6);
		l.add(m7);
		l.add(m8);

		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < l.size(); i++) {
			Move m = l.get(i);
			if (m.getX() < 0 || m.getX() > 7 || m.getY() < 0 || m.getY() > 7)
				list.add(i);
			else {
				if (array[m.getX()][m.getY()] == 1) {
					list.add(i);
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			int a = list.get(i);
			l.remove(a);
			for (int j = i + 1; j < list.size(); j++) {
				list.set(j, list.get(j) - 1);
			}
		}

		return l;
	}

	public static void main(String[] args) {
		MaDiTuan m = new MaDiTuan(2, 2);
		List<List<Move>> list = m.getChuTrinh();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
