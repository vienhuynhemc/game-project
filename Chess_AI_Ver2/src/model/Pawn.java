package model;

public class Pawn {

	// <!-- Mảng đánh giá trạng thái chỗ hiện tại tốt hay xấu-->
	// <!-- Cho quân trắng -->
	private static final int[][] evaluteChessPosition = { { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 50, 50, 50, 50, 50, 50, 50, 50 }, { 10, 10, 20, 30, 30, 20, 10, 10 }, { 5, 5, 10, 25, 25, 10, 5, 5 },
			{ 0, 0, 0, 20, 20, 0, 0, 0 }, { 5, -5, -10, 0, 0, -10, -5, 5 }, { 5, 10, 10, -20, -20, 10, 10, 5 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 } };

	// <!-- Cho quân đen -->
	private static final int[][] evaluteChessPositionReverse = { { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 5, 10, 10, -20, -20, 10, 10, 5 }, { 5, -5, -10, 0, 0, -10, -5, 5 }, { 0, 0, 0, 20, 20, 0, 0, 0 },
			{ 5, 5, 10, 25, 25, 10, 5, 5 }, { 10, 10, 20, 30, 30, 20, 10, 10 }, { 50, 50, 50, 50, 50, 50, 50, 50 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 } };

	// <!-- Hàm trả về điểm của vị trí đứng -->
	public static int getScoreEvaluteChessPosition(int i, int j, int value) {
		if (value > 0)
			return evaluteChessPosition[i][j];
		else
			return evaluteChessPositionReverse[i][j];
	}

	/*
	 * Hàm này nhận vào tọa độ quân tốt sau đó xử lý để mảng nước đi có những nước
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

		switch (board[i][j]) {

		// <!-- Tốt trắng -->
		case 6:

			// <!-- Vị trí bàn đầu nè, được đi tối đa 2 ô-->
			if (i == 6) {

				// <!-- Đi tới 1 ô -->
				if (board[5][j] == 0)
					result[5][j] = 1;

				// <!-- Đi tới 2 ô -->
				if (board[4][j] == 0 && board[5][j] == 0)
					result[4][j] = 1;

				// <!-- Kiểm tra xem có phải ở phía bên trái cùng -->
				if (j - 1 > -1)
					// <!-- Ăn chéo trái nếu có cờ địch ở đó -->
					if (board[5][j - 1] < 0)
						result[5][j - 1] = 1;

				// <!-- Kiểm tra xem có phải ở phía bên phải cùng -->
				if (j + 1 < 8)
					// <!-- Ăn chéo phải nếu có cờ địch ỏ đó-->
					if (board[5][j + 1] < 0)
						result[5][j + 1] = 1;
			} else {

				// <!-- Vị trí ngẫu nhiên trên bàn cờ -->

				// <!-- Kiểm tra xem nó có ở cuối bàn cờ chưa -->
				if (i - 1 > -1) {

					// <!-- đi tới 1 ô-->
					if (board[i - 1][j] == 0)
						result[i - 1][j] = 1;

					// <!-- Kiểm tra xem có phải ở phía bên trái cùng -->
					if (j - 1 > -1)
						// <!-- Ăn chéo trái nếu có địch ở đó -->
						if (board[i - 1][j - 1] < 0)
							result[i - 1][j - 1] = 1;

					// <!-- Kiểm tra xem có phải ở phía bên phải cùng -->
					if (j + 1 < 8)
						// <!-- Ăn chéo phải nếu có cờ địch ỏ đó-->
						if (board[i - 1][j + 1] < 0)
							result[i - 1][j + 1] = 1;
				}
			}

			break;

		// <!-- Tốt đen -->
		case -6:

			// <!-- Vị trí bàn đầu nè, được đi tối đa 2 ô-->
			if (i == 1) {

				// <!-- Đi tới 1 ô -->
				if (board[2][j] == 0)
					result[2][j] = 1;

				// <!-- Đi tới 2 ô -->
				if (board[3][j] == 0 && board[2][j] == 0)
					result[3][j] = 1;

				// <!-- Kiểm tra xem có phải ở phía bên trái cùng -->
				if (j - 1 > -1)
					// <!-- Ăn chéo trái nếu có cờ địch ở đó -->
					if (board[2][j - 1] > 0)
						result[2][j - 1] = 1;

				// <!-- Kiểm tra xem có phải ở phía bên phải cùng -->
				if (j + 1 < 8)
					// <!-- Ăn chéo phải nếu có cờ địch ỏ đó-->
					if (board[2][j + 1] > 0)
						result[2][j + 1] = 1;
			} else {

				// <!-- Vị trí ngẫu nhiên trên bàn cờ -->

				// <!-- Kiểm tra xem nó có ở cuối bàn cờ chưa -->
				if (i + 1 < 8) {

					// <!-- đi tới 1 ô-->
					if (board[i + 1][j] == 0)
						result[i + 1][j] = 1;

					// <!-- Kiểm tra xem có phải ở phía bên trái cùng -->
					if (j - 1 > -1)
						// <!-- Ăn chéo trái nếu có địch ở đó -->
						if (board[i + 1][j - 1] > 0)
							result[i + 1][j - 1] = 1;

					// <!-- Kiểm tra xem có phải ở phía bên phải cùng -->
					if (j + 1 < 8)
						// <!-- Ăn chéo phải nếu có cờ địch ỏ đó-->
						if (board[i + 1][j + 1] > 0)
							result[i + 1][j + 1] = 1;
				}
			}

			break;
		}

		// <!-- Trả về mảng result chứa những nước đi có thể đi của quân cờ này-->
		return result;
	}

}
