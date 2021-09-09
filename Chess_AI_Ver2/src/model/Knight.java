package model;

public class Knight {

	// <!-- Mảng đánh giá trạng thái chỗ hiện tại tốt hay xấu-->
	// <!-- Cho quân trắng -->
	private static final int[][] evaluteChessPosition = { { -50, -40, -30, -30, -30, -30, -40, -50 },
			{ -40, -20, 0, 0, 0, 0, -20, -40 }, { -30, 0, 10, 15, 15, 10, 0, -30 }, { -30, 5, 15, 20, 20, 15, 5, -30 },
			{ -30, 0, 15, 20, 20, 15, 0, -30 }, { -30, 5, 10, 15, 15, 10, 5, -30 }, { -40, -20, 0, 5, 5, 0, -20, -40 },
			{ -50, -40, -30, -30, -30, -30, -40, -50 } };

	// <!-- Cho quân đen -->
	private static final int[][] evaluteChessPositionReverse = { { -50, -40, -30, -30, -30, -30, -40, -50 },
			{ -40, -20, 0, 5, 5, 0, -20, -40 }, { -30, 5, 10, 15, 15, 10, 5, -30 }, { -30, 0, 15, 20, 20, 15, 0, -30 },
			{ -30, 5, 15, 20, 20, 15, 5, -30 }, { -30, 0, 10, 15, 15, 10, 0, -30 }, { -40, -20, 0, 0, 0, 0, -20, -40 },
			{ -50, -40, -30, -30, -30, -30, -40, -50 } };

	// <!-- Hàm trả về điểm của vị trí đứng -->
	public static int getScoreEvaluteChessPosition(int i, int j, int value) {
		if (value > 0)
			return evaluteChessPosition[i][j];
		else
			return evaluteChessPositionReverse[i][j];
	}

	/*
	 * Hàm này nhận vào tọa độ quân mã sau đó xử lý để mảng nước đi có những nước đi
	 * có thể đi của quân cờ này
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

		/*-		X		X
		 *-	X				X
		 * 
		 *-			N				
		 * 
		 *-X				X
		 *-		X		X						   
		 * 
		 * <!-- Mỗi quân mã sẽ có 8 điểm đi tối đa theo thứ tự từ trên xuống sẽ có 4
		 * hàng, mỗi hàng 2 vị trí -->
		 */

		// <!-- 2 nước đi đầu tiên-->
		if (i - 2 > -1) {

			// <!-- chéo trái -->
			if (j - 1 > -1)

				// <!-- Ô trống hoặc cờ địch mới có thể đi nước này -->
				if (board[i][j] == 2) {
					// <!-- Mã trắng -->
					if (board[i - 2][j - 1] <= 0)
						result[i - 2][j - 1] = 1;
				} else {
					// <!-- Mã đen -->
					if (board[i - 2][j - 1] >= 0)
						result[i - 2][j - 1] = 1;
				}

			// <!-- chéo phải -->
			if (j + 1 < 8)

				// <!-- Ô trống hoặc cờ địch mới có thể đi nước này -->
				if (board[i][j] == 2) {
					// <!-- Mã trắng -->
					if (board[i - 2][j + 1] <= 0)
						result[i - 2][j + 1] = 1;
				} else {
					// <!-- Mã đen -->
					if (board[i - 2][j + 1] >= 0)
						result[i - 2][j + 1] = 1;
				}

		}

		// <!-- 2 nước đi tiếp theo-->
		if (i - 1 > -1) {

			// <!-- chéo trái -->
			if (j - 2 > -1)

				// <!-- Ô trống hoặc cờ địch mới có thể đi nước này -->
				if (board[i][j] == 2) {
					// <!-- Mã trắng -->
					if (board[i - 1][j - 2] <= 0)
						result[i - 1][j - 2] = 1;
				} else {
					// <!-- Mã đen -->
					if (board[i - 1][j - 2] >= 0)
						result[i - 1][j - 2] = 1;
				}

			// <!-- chéo phải -->
			if (j + 2 < 8)

				// <!-- Ô trống hoặc cờ địch mới có thể đi nước này -->
				if (board[i][j] == 2) {
					// <!-- Mã trắng -->
					if (board[i - 1][j + 2] <= 0)
						result[i - 1][j + 2] = 1;
				} else {
					// <!-- Mã đen -->
					if (board[i - 1][j + 2] >= 0)
						result[i - 1][j + 2] = 1;
				}

		}

		// <!-- 2 nước đi tiếp theo-->
		if (i + 1 < 8) {

			// <!-- chéo trái -->
			if (j - 2 > -1)

				// <!-- Ô trống hoặc cờ địch mới có thể đi nước này -->
				if (board[i][j] == 2) {
					// <!-- Mã trắng -->
					if (board[i + 1][j - 2] <= 0)
						result[i + 1][j - 2] = 1;
				} else {
					// <!-- Mã đen -->
					if (board[i + 1][j - 2] >= 0)
						result[i + 1][j - 2] = 1;
				}

			// <!-- chéo phải -->
			if (j + 2 < 8)

				// <!-- Ô trống hoặc cờ địch mới có thể đi nước này -->
				if (board[i][j] == 2) {
					// <!-- Mã trắng -->
					if (board[i + 1][j + 2] <= 0)
						result[i + 1][j + 2] = 1;
				} else {
					// <!-- Mã đen -->
					if (board[i + 1][j + 2] >= 0)
						result[i + 1][j + 2] = 1;
				}

		}

		// <!-- 2 nước đi cuối cùng-->
		if (i + 2 < 8) {

			// <!-- chéo trái -->
			if (j - 1 > -1)

				// <!-- Ô trống hoặc cờ địch mới có thể đi nước này -->
				if (board[i][j] == 2) {
					// <!-- Mã trắng -->
					if (board[i + 2][j - 1] <= 0)
						result[i + 2][j - 1] = 1;
				} else {
					// <!-- Mã đen -->
					if (board[i + 2][j - 1] >= 0)
						result[i + 2][j - 1] = 1;
				}

			// <!-- chéo phải -->
			if (j + 1 < 8)

				// <!-- Ô trống hoặc cờ địch mới có thể đi nước này -->
				if (board[i][j] == 2) {
					// <!-- Mã trắng -->
					if (board[i + 2][j + 1] <= 0)
						result[i + 2][j + 1] = 1;
				} else {
					// <!-- Mã đen -->
					if (board[i + 2][j + 1] >= 0)
						result[i + 2][j + 1] = 1;
				}

		}

		// <!-- Trả về mảng result chứa những nước đi có thể đi của quân cờ này-->
		return result;

	}

}
