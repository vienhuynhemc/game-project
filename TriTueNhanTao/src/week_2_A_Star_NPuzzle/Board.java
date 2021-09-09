package week_2_A_Star_NPuzzle;

import java.util.ArrayList;
import java.util.List;

public class Board {

	public static final int DIR_LEFT = 0;
	public static final int DIR_RIGHT = 1;
	public static final int DIR_UP = 2;
	public static final int DIR_DOWN = 3;

	public static void mixArray(int[][] array, Location positionSpace) {
		int count = 0;

		while (count < array.length * 100) {
			count++;
			List<Integer> arrayMove = getArrayMove(positionSpace, array);
			int position = RandomSingleton.getInstance().getRd().nextInt(arrayMove.size());

			switch (arrayMove.get(position)) {
			case DIR_LEFT:
				move_left(array, positionSpace);
				break;
			case DIR_RIGHT:
				move_right(array, positionSpace);
				break;
			case DIR_UP:
				move_up(array, positionSpace);
				break;
			case DIR_DOWN:
				move_down(array, positionSpace);
				break;
			}
		}
	}

	public static void move_left(int[][] array, Location positionSpace) {
		array[positionSpace.getX()][positionSpace.getY()] = array[positionSpace.getX()][positionSpace.getY() - 1];
		array[positionSpace.getX()][positionSpace.getY() - 1] = -1;
		positionSpace.setY(positionSpace.getY() - 1);
	}

	public static void move_right(int[][] array, Location positionSpace) {
		array[positionSpace.getX()][positionSpace.getY()] = array[positionSpace.getX()][positionSpace.getY() + 1];
		array[positionSpace.getX()][positionSpace.getY() + 1] = -1;
		positionSpace.setY(positionSpace.getY() + 1);
	}

	public static void move_up(int[][] array, Location positionSpace) {
		array[positionSpace.getX()][positionSpace.getY()] = array[positionSpace.getX() - 1][positionSpace.getY()];
		array[positionSpace.getX() - 1][positionSpace.getY()] = -1;
		positionSpace.setX(positionSpace.getX() - 1);
	}

	public static void move_down(int[][] array, Location positionSpace) {
		array[positionSpace.getX()][positionSpace.getY()] = array[positionSpace.getX() + 1][positionSpace.getY()];
		array[positionSpace.getX() + 1][positionSpace.getY()] = -1;
		positionSpace.setX(positionSpace.getX() + 1);
	}

	public static List<Integer> getArrayMove(Location positionSpace, int[][] array) {
		List<Integer> arrayDir = new ArrayList<Integer>();
		if (positionSpace.getX() == 0 && positionSpace.getY() == 0) {
			arrayDir.add(DIR_DOWN);
			arrayDir.add(DIR_RIGHT);
		} else if (positionSpace.getX() == 0 && positionSpace.getY() == array.length - 1) {
			arrayDir.add(DIR_DOWN);
			arrayDir.add(DIR_LEFT);
		} else if (positionSpace.getX() == array.length - 1 && positionSpace.getY() == 0) {
			arrayDir.add(DIR_UP);
			arrayDir.add(DIR_RIGHT);
		} else if (positionSpace.getX() == array.length - 1 && positionSpace.getY() == array.length - 1) {
			arrayDir.add(DIR_UP);
			arrayDir.add(DIR_LEFT);
		} else if (positionSpace.getX() == 0) {
			arrayDir.add(DIR_DOWN);
			arrayDir.add(DIR_LEFT);
			arrayDir.add(DIR_RIGHT);
		} else if (positionSpace.getX() == array.length - 1) {
			arrayDir.add(DIR_UP);
			arrayDir.add(DIR_RIGHT);
			arrayDir.add(DIR_LEFT);
		} else if (positionSpace.getY() == 0) {
			arrayDir.add(DIR_UP);
			arrayDir.add(DIR_RIGHT);
			arrayDir.add(DIR_DOWN);
		} else if (positionSpace.getY() == array.length - 1) {
			arrayDir.add(DIR_UP);
			arrayDir.add(DIR_LEFT);
			arrayDir.add(DIR_DOWN);
		} else {
			arrayDir.add(DIR_UP);
			arrayDir.add(DIR_LEFT);
			arrayDir.add(DIR_DOWN);
			arrayDir.add(DIR_RIGHT);
		}

		return arrayDir;
	}

}
