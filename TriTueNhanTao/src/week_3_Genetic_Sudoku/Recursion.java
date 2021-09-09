package week_3_Genetic_Sudoku;

import java.util.List;

public class Recursion {

	private int timeSleep;
	private Node root;
	private GUIGenetic gui;

	public Recursion(int number, int timeSleep) throws InterruptedException {
		this.timeSleep = timeSleep;

		root = new Node(number);

		gui = new GUIGenetic();
		gui.setNode(root);
		gui.repaint();
		Thread.sleep(timeSleep);
	}

	public void run() throws InterruptedException {
		int row = 0;
		int col = 0;
		while (root.getRootArray()[row][col]) {
			col++;
			if (col == 9) {
				col = 0;
				row++;
			}
		}
		soduko(row, col);
		root.print();
		System.out.println("-----------END------------------");
	}

	public boolean soduko(int row, int col) throws InterruptedException {
		if (isComplete()) {
			root.print();
			return true;
		} else {
			root.print();
			if (root.getRootArray()[row][col]) {
				boolean checkMain = false;
				if (row == 8 && col == 8)
					return true;
				else {
					int nCol = col + 1;
					int nRow = row;
					if (nCol == 9) {
						nCol = 0;
						nRow++;
					}
					checkMain = soduko(nRow, nCol);
					if (!checkMain) {
						return false;
					} else {
						return true;
					}
				}
			} else {
				List<Integer> way = root.getListInteger(row, col);
				int count = 0;
				for (int i = 0; i < way.size(); i++) {
					boolean check = false;
					root.getState()[row][col] = way.get(i);

					gui.setNode(root);
					gui.repaint();
					Thread.sleep(timeSleep);

					if (root.heuristicOneElement(row, col) == 0) {
						if (row != 8 || col != 8) {
							int nCol = col + 1;
							int nRow = row;
							if (nCol == 9) {
								nCol = 0;
								nRow++;
							}
							check = soduko(nRow, nCol);
							if (check)
								return true;
							else
								count++;
						} else {
							return true;
						}
					} else {
						root.getState()[row][col] = 0;
						count++;
						gui.setNode(root);
						gui.repaint();
						Thread.sleep(timeSleep);
					}
				}
				if (count == way.size()) {
					root.getState()[row][col] = 0;
					gui.setNode(root);
					gui.repaint();
					Thread.sleep(timeSleep);
					return false;
				}
				return false;
			}
		}
	}

	public boolean isComplete() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (root.getState()[i][j] == 0)
					return false;
			}
		}

		if (root.heuristic() != 0)
			return false;
		return true;
	}

}
