package week_3_Genetic_Sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {

	private int[][] state;
	private boolean[][] rootArray;

	int heuristic;

	public Node(int[][] state) {

		this.state = new int[9][9];
		rootArray = new boolean[9][9];

		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state.length; j++) {
				if (state[i][j] != 0) {
					this.state[i][j] = state[i][j];
					rootArray[i][j] = true;
				} else
					this.state[i][j] = RandomSingleton.getInstance().getRd().nextInt(9) + 1;

			}
		this.heuristic = heuristic();
	}

	public Node(int[][] state, boolean[][] rootArray) {
		this.rootArray = rootArray;
		this.state = state;
		this.heuristic = heuristic();
	}

	public Node(int n) {

		state = new int[9][9];
		rootArray = new boolean[9][9];

//		/*
//		 * 1.Fill in the row with different nine numbers.
//		 * 2.Filling in the second row is a change of the beginning of the line by three position.
//		 * 3.The second three full is a change of the lines equals three positions.
//		 * 4.Filling the fourth row is a shift of the third by a slot.
//		 *
//		 *
//		 * line 1: 8 9 3  2 7 6  4 5 1
//		 * line 2: 2 7 6  4 5 1  8 9 3 (shift 3)
//		 * line 3: 4 5 1  8 9 3  2 7 6 (shift 3)
//
//		 * line 4: 5 1 8  9 3 2  7 6 4 (shift 1)
//		 * line 5: 9 3 2  7 6 4  5 1 8 (shift 3)
//		 * line 6: 7 6 4  5 1 8  9 3 2 (shift 3)
//
//		 * line 7: 6 4 5  1 8 9  3 2 7 (shift 1)
//		 * line 8: 1 8 9  3 2 7  6 4 5 (shift 3)
//		 * line 9: 3 2 7  6 4 5  1 8 9 (shift 3)
//		 */

		// <!-- fill all line -->
		for (int i = 0; i < state.length; i++) {
			state[0][i] = RandomSingleton.getInstance().getRd().nextInt(9) + 1;
			while (checkOneElementHorizontal(0, i) != 0) {
				state[0][i] = RandomSingleton.getInstance().getRd().nextInt(9) + 1;
			}
		}

		int start1 = 3;
		int start2 = 6;
		int start3 = 7;
		int start4 = 1;
		int start5 = 4;
		int start6 = 5;
		int start7 = 8;
		int start8 = 2;

		for (int i = 0; i < state.length; i++) {
			state[1][i] = state[0][start1++];
			if (start1 > 8)
				start1 = 0;
			state[2][i] = state[0][start2++];
			if (start2 > 8)
				start2 = 0;
			state[3][i] = state[0][start3++];
			if (start3 > 8)
				start3 = 0;
			state[4][i] = state[0][start4++];
			if (start4 > 8)
				start4 = 0;
			state[5][i] = state[0][start5++];
			if (start5 > 8)
				start5 = 0;
			state[6][i] = state[0][start6++];
			if (start6 > 8)
				start6 = 0;
			state[7][i] = state[0][start7++];
			if (start7 > 8)
				start7 = 0;
			state[8][i] = state[0][start8++];
			if (start8 > 8)
				start8 = 0;
		}

		for (int i = 0; i < rootArray.length; i++) {
			for (int j = 0; j < rootArray.length; j++) {
				rootArray[i][j] = true;
			}
		}

		// <!-- remove n number => sokudo -->
		int count = 0;
		while (count < 81 - n) {
			int row = RandomSingleton.getInstance().getRd().nextInt(9);
			int column = RandomSingleton.getInstance().getRd().nextInt(9);
			while (state[row][column] == 0) {
				row = RandomSingleton.getInstance().getRd().nextInt(9);
				column = RandomSingleton.getInstance().getRd().nextInt(9);
			}
			state[row][column] = 0;
			rootArray[row][column] = false;
			count++;
		}
		this.heuristic = heuristic();
	}

	public int heuristicOneElement(int row, int column) {
		if (state[row][column] == 0 || rootArray[row][column])
			return 0;

		return checkOneElementHorizontal(row, column) + checkOneElementVertical(row, column)
				+ checkOneLementInRectangleNineNumber(row, column);
	}

	public int heuristic() {
		int count = 0;

		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state.length; j++)
				count += heuristicOneElement(i, j);
		return count;
	}

	public int checkOneElementHorizontal(int row, int column) {
		if (state[row][column] == 0)
			return 0;

		int count = 0;

		for (int i = 0; i < state.length; i++)
			if (i != column)
				if (state[row][column] == state[row][i])
					count++;

		return count;
	}

	public int checkOneElementVertical(int row, int column) {
		if (state[row][column] == 0)
			return 0;

		int count = 0;

		for (int i = 0; i < state.length; i++)
			if (i != row)
				if (state[row][column] == state[i][column])
					count++;

		return count;
	}

	public int checkOneLementInRectangleNineNumber(int row, int column) {
		int count = 0;

		int valueRow = row / 3;
		int valueColumn = column / 3;

		for (int i = 0 + valueRow * 3; i <= 2 + valueRow * 3; i++)
			for (int j = 0 + valueColumn * 3; j <= 2 + valueColumn * 3; j++)
				if (i != row || j != column) {
					if (state[row][column] == state[i][j])
						count++;
				}

		return count;
	}

	public void print() {
		System.out.println("-------------------------");
		for (int i = 0; i < state.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < state.length; j++) {
				if (state[i][j] != 0)
					System.out.print(state[i][j] + " ");
				else
					System.out.print("  ");
				if (j % 3 == 2)
					System.out.print("| ");
			}
			System.out.println();
			if (i % 3 == 2)
				System.out.println("-------------------------");
		}
	}

	public void mutaionOneGenRow() {
		int row = RandomSingleton.getInstance().getRd().nextInt(9);
		int index = RandomSingleton.getInstance().getRd().nextInt(9);

		for (int i = 0; i < rootArray.length; i++) {
			if (index != i && !rootArray[row][i]) {
				if (state[row][i] == state[row][index]) {
					List<Integer> way = getListInteger(row, i);
					state[row][i] = way.get(RandomSingleton.getInstance().getRd().nextInt(way.size()));
					this.heuristic = heuristic();
					return;
				}
			}
		}
	}

	public void mutaionOneGenCol() {
		int col = RandomSingleton.getInstance().getRd().nextInt(9);
		int index = RandomSingleton.getInstance().getRd().nextInt(9);
		for (int i = 0; i < rootArray.length; i++) {
			if (index != i && !rootArray[i][col]) {
				if (state[i][col] == state[index][col]) {
					List<Integer> way = getListInteger(i, col);
					state[i][col] = way.get(RandomSingleton.getInstance().getRd().nextInt(way.size()));
					this.heuristic = heuristic();
					return;
				}
			}
		}
	}

	public void mutaionOneGenCross() {
		int row = RandomSingleton.getInstance().getRd().nextInt(9);
		int col = RandomSingleton.getInstance().getRd().nextInt(9);

		int xStart = row / 3 * 3;
		int yStart = col / 3 * 3;

		for (int i = xStart; i < xStart + 3; i++) {
			for (int j = yStart; j < yStart + 3; j++) {
				if ((i != xStart || j != yStart) && !rootArray[i][j]) {
					if (state[i][j] == state[row][col]) {
						List<Integer> way = getListInteger(i, j);
						state[i][j] = way.get(RandomSingleton.getInstance().getRd().nextInt(way.size()));
						this.heuristic = heuristic();
						return;
					}
				}
			}
		}

	}

	// <!-- getter and setter -->

	public int[][] getState() {
		return state;
	}

	public boolean[][] getRootArray() {
		return rootArray;
	}

	public List<Integer> getListInteger(int row, int col) {
		List<Integer> list = new ArrayList<Integer>();

		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < rootArray.length; i++) {
			if (i != col && rootArray[row][i]) {
				set.add(state[row][i]);
			}

			if (i != row && rootArray[i][col]) {
				set.add(state[i][col]);
			}
		}

		int xStart = row / 3 * 3;
		int yStart = col / 3 * 3;

		for (int i = xStart; i < xStart + 3; i++) {
			for (int j = yStart; j < yStart + 3; j++) {
				if ((i != xStart || j != yStart) && rootArray[i][j]) {
					set.add(state[i][j]);
				}
			}
		}

		for (int i = 1; i <= rootArray.length; i++) {
			if (!set.contains(i))
				list.add(i);
		}

		return list;
	}

}
