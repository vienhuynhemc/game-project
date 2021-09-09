package week_2_bfs_dfs_queens;

public class Recursion {
	/**
	 * 
	 */

	private int[][] arr; // hau = 1;
	private int stt;

	public Recursion(int n) {
		stt = 1;
		arr = new int[n][n];
	}

	public void action() {
		xepHau(0);
	}

	public boolean xepHau(int j) {
		if (isComplete())
			return true;
		else if (j > arr.length - 1)
			return false;
		else {

			for (int i = 0; i < arr.length; i++) {
				if (arr[i][j] == 0 && place(i, j)) {

					arr[i][j] = 1;

					if (isComplete()) {
						System.out.println("Cách: " + stt++);
						for (int i2 = 0; i2 < arr.length; i2++) {
							for (int j2 = 0; j2 < arr.length; j2++) {
								System.out.print(arr[i2][j2] + "  ");
							}
							System.out.println();
						}
						System.out.println();
						arr[i][j] = 0;

						return false;
					}
					boolean check = xepHau(j + 1);

					if (!check)
						arr[i][j] = 0;
				}
			}
			return false;
		}
	}

	// <!-- check complete -->
	public boolean isComplete() {
		int count = 0;
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr.length; j++)
				if (arr[i][j] == 1)
					count++;

		if (count != arr.length)
			return false;

		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[i].length; j++)
				if (arr[i][j] == 1)
					if (!place(i, j))
						return false;

		return true;
	}

	// <!-- check one queen -->
	public boolean place(int i, int j) {
		for (int j2 = 0; j2 < arr.length; j2++)
			if (j2 != i)
				if (arr[j2][j] == 1)
					return false;

		for (int j2 = 0; j2 < arr.length; j2++)
			if (j2 != j)
				if (arr[i][j2] == 1)
					return false;

		for (int j2 = 0; j2 < arr.length; j2++)
			for (int k = 0; k < arr.length; k++)
				if (arr[j2][k] == 1)
					if (j2 != i && i != k)
						if (Math.abs(j2 - i) == Math.abs(k - j))
							return false;

		return true;
	}
}
