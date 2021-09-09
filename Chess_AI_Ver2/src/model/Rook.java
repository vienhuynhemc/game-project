package model;

public class Rook {

	// <!-- Mảng đánh giá trạng thái chỗ hiện tại tốt hay xấu-->
	// <!-- Cho quân trắng -->
	private static final int[][] evaluteChessPosition = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 5, 10, 10, 10, 10, 10, 10, 5 },
			{ -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 },
			{ -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 }, { 0, 0, 0, 5, 5, 0, 0, 0 } };

	// <!-- Cho quân đen -->
	private static final int[][] evaluteChessPositionReverse = { { 0, 0, 0, 5, 5, 0, 0, 0 },
			{ -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 },
			{ -5, 0, 0, 0, 0, 0, 0, -5 }, { -5, 0, 0, 0, 0, 0, 0, -5 }, { 5, 10, 10, 10, 10, 10, 10, 5 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 } };

	// <!-- Hàm trả về điểm của vị trí đứng -->
	public static int getScoreEvaluteChessPosition(int i, int j, int value) {
		if (value > 0)
			return evaluteChessPosition[i][j];
		else
			return evaluteChessPositionReverse[i][j];
	}

	/*
	 * Hàm này nhận vào tọa độ quân tượng sau đó xử lý để mảng nước đi có những nước
	 * đi có thể đi của quân cờ này
	 */
	public static int[][] getNextMovesBoard(int[][] board, int i, int j) {

		/*
		 * <!-- Mảng chứa những vị trí có thể đi của quân cờ truyền vô (i,j) , vị trí
		 * nào có thể đi thì giá trị là 1-->
		 * 
		 */
		int[][] result = new int[8][8];

		// <!-- hover tại chính nó cái đã -->
		result[i][j] = 2;

		// <!-- Vị trí ngẫu nhiên trên bàn cờ -->

		// <!-- Sang phải -->
		if (j < 7)
			for (int k = j + 1; k < 8; k++)

				// <!-- Không có quân nào ở ô này -->
				if (board[i][k] == 0)
					result[i][k] = 1;

				// <!-- Quân cờ ở ô này là trắng, lúc này ta set quân ta đang set là quân gì-->
				else if (board[i][k] > 0)
					if (board[i][j] > 0)
						break;
					else {
						result[i][k] = 1;
						break;
					}

				// <!-- Quân cờ ở ô này là đen, lúcn này ta set quân ta đang set là quân gì-->
				else {
					if (board[i][j] < 0)
						break;
					else {
						result[i][k] = 1;
						break;
					}
				}

		// <!-- Qua trái -->
		if (j > 0)
			for (int k = j - 1; k >= 0; k--)

				// <!-- Không có quân nào ở ô này -->
				if (board[i][k] == 0)
					result[i][k] = 1;

				// <!-- Quân cờ ở ô này là trắng, lúc này ta set quân ta đang set là quân gì-->
				else if (board[i][k] > 0)
					if (board[i][j] > 0)
						break;
					else {
						result[i][k] = 1;
						break;
					}

				// <!-- Quân cờ ở ô này là đen, lúcn này ta set quân ta đang set là quân gì-->
				else {
					if (board[i][j] < 0)
						break;
					else {
						result[i][k] = 1;
						break;
					}
				}

		// <!-- Đi lên -->
		if (i > 0)
			for (int k = i - 1; k >= 0; k--)

				// <!-- Không có quân nào ở ô này -->
				if (board[k][j] == 0)
					result[k][j] = 1;

				// <!-- Quân cờ ở ô này là trắng, lúc này ta set quân ta đang set là quân gì-->
				else if (board[k][j] > 0)
					if (board[i][j] > 0)
						break;
					else {
						result[k][j] = 1;
						break;
					}

				// <!-- Quân cờ ở ô này là đen, lúcn này ta set quân ta đang set là quân gì-->
				else {
					if (board[i][j] < 0)
						break;
					else {
						result[k][j] = 1;
						break;
					}
				}

		// <!-- Đi xuống -->
		if (i < 7)
			for (int k = i + 1; k < 8; k++)

				// <!-- Không có quân nào ở ô này -->
				if (board[k][j] == 0)
					result[k][j] = 1;

				// <!-- Quân cờ ở ô này là trắng, lúc này ta set quân ta đang set là quân gì-->
				else if (board[k][j] > 0)
					if (board[i][j] > 0)
						break;
					else {
						result[k][j] = 1;
						break;
					}

				// <!-- Quân cờ ở ô này là đen, lúcn này ta set quân ta đang set là quân gì-->
				else {
					if (board[i][j] < 0)
						break;
					else {
						result[k][j] = 1;
						break;
					}
				}

		// <!-- Trả về mảng result chứa những nước đi có thể đi của quân cờ này-->
		return result;
	}
}
