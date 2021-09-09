package week_2_A_Star_VacuumCleaner;

import java.util.ArrayList;
import java.util.List;

public class Board {

	public static final int DIR_LEFT = 0;
	public static final int DIR_RIGHT = 1;
	public static final int DIR_UP = 2;
	public static final int DIR_DOWN = 3;

	public static void move_left(Location positionSpace) {
		positionSpace.setY(positionSpace.getY() - 1);
	}

	public static void move_right(Location positionSpace) {
		positionSpace.setY(positionSpace.getY() + 1);
	}

	public static void move_up(Location positionSpace) {
		positionSpace.setX(positionSpace.getX() - 1);
	}

	public static void move_down(Location positionSpace) {
		positionSpace.setX(positionSpace.getX() + 1);
	}

	public static List<Integer> getArrayMove(Location positionSpace, Location trash, int[][] array,
			boolean[][] arrayBoolean) {
		List<Integer> arrayDir = new ArrayList<Integer>();

		if (tryMoving(DIR_LEFT, positionSpace, array, arrayBoolean, trash))
			arrayDir.add(DIR_LEFT);
		if (tryMoving(DIR_RIGHT, positionSpace, array, arrayBoolean, trash))
			arrayDir.add(DIR_RIGHT);
		if (tryMoving(DIR_UP, positionSpace, array, arrayBoolean, trash))
			arrayDir.add(DIR_UP);
		if (tryMoving(DIR_DOWN, positionSpace, array, arrayBoolean, trash))
			arrayDir.add(DIR_DOWN);

		return arrayDir;
	}

	public static boolean tryMoving(int dir, Location p, int[][] array, boolean[][] arrayBoolean, Location trash) {

		int newY = p.getY();
		int newX = p.getX();

		switch (dir) {
		case DIR_LEFT:

			newY = newY - 1;
			if (newY < 0)
				return false;
			if (array[newX][newY] == 5)
				return false;

			break;
		case DIR_RIGHT:

			newY = newY + 1;
			if (newY > array.length - 1)
				return false;
			if (array[newX][newY] == 5)
				return false;

			break;
		case DIR_UP:

			newX = newX - 1;
			if (newX < 0)
				return false;
			if (array[newX][newY] == 5)
				return false;

			break;
		case DIR_DOWN:

			newX = newX + 1;
			if (newX > array.length - 1)
				return false;
			if (array[newX][newY] == 5)
				return false;

			break;
		}
		
		if (newX == trash.getX() && newY == trash.getY())
			return true;

		if (arrayBoolean[newX][newY])
			return false;

		return true;
	}

}
