package model;

public class Bishop {

	// <!-- Mảng đánh giá trạng thái chỗ hiện tại tốt hay xấu-->
	// <!-- Cho quân trắng -->
	private static final int[][] evaluteChessPosition = { { -20, -10, -10, -10, -10, -10, -10, -20 },
			{ -10, 0, 0, 0, 0, 0, 0, -10 }, { -10, 0, 5, 10, 10, 5, 0, -10 }, { -10, 5, 5, 10, 10, 5, 5, -10 },
			{ -10, 0, 10, 10, 10, 10, 0, -10 }, { -10, 10, 10, 10, 10, 10, 10, -10 }, { -10, 5, 0, 0, 0, 0, 5, -10 },
			{ -20, -10, -10, -10, -10, -10, -10, -20 } };

	// <!-- Cho quân đen -->
	private static final int[][] evaluteChessPositionReverse = { { -20, -10, -10, -10, -10, -10, -10, -20 },
			{ -10, 5, 0, 0, 0, 0, 5, -10 }, { -10, 10, 10, 10, 10, 10, 10, -10 }, { -10, 0, 10, 10, 10, 10, 0, -10 },
			{ -10, 5, 5, 10, 10, 5, 5, -10 }, { -10, 0, 5, 10, 10, 5, 0, -10 }, { -10, 0, 0, 0, 0, 0, 0, -10 },
			{ -20, -10, -10, -10, -10, -10, -10, -20 } };

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

		// <!-- Mỗi quân tượng có thể theo hàng chéo -->

		// <!-- Trên trái -->
		/*
		 * <!-- Kiểm tra xem nó có đang ở bên trái cùng hoặc ở hàng đầu tiên hay không?
		 * Nếu ở thì ko xét -->
		 */
		if (j != 0 && i != 0) {
			int row = i - 1;
			int col = j - 1;
			while (row > -1 && col > -1) {

				// <!-- Không có quân nào ở ô này -->
				if (board[row][col] == 0)
					result[row][col] = 1;

				// <!-- Quân cờ ở ô này là trắng, lúc này ta set quân ta đang set là quân gì-->
				else if (board[row][col] > 0)
					if (board[i][j] > 0)
						break;
					else {
						result[row][col] = 1;
						break;
					}

				// <!-- Quân cờ ở ô này là đen, lúc này ta set quân ta đang set là quân gì-->
				else {
					if (board[i][j] < 0)
						break;
					else {
						result[row][col] = 1;
						break;
					}
				}

				row--;
				col--;
			}
		}

		// <!-- Dưới trái -->
		/*
		 * <!-- Kiểm tra xem nó có đang ở bên trái cùng hoặc ở hàng cuối cùng hay không?
		 * Nếu ở thì ko xét -->
		 */
		if (j != 0 && i != 7) {
			int row = i + 1;
			int col = j - 1;
			while (row < 8 && col > -1) {

				// <!-- Không có quân nào ở ô này -->
				if (board[row][col] == 0)
					result[row][col] = 1;

				// <!-- Quân cờ ở ô này là trắng, lúc này ta set quân ta đang set là quân gì-->
				else if (board[row][col] > 0)
					if (board[i][j] > 0)
						break;
					else {
						result[row][col] = 1;
						break;
					}

				// <!-- Quân cờ ở ô này là đen, lúc này ta set quân ta đang set là quân gì-->
				else {
					if (board[i][j] < 0)
						break;
					else {
						result[row][col] = 1;
						break;
					}
				}
				row++;
				col--;
			}
		}

		// <!-- Trên phải -->
		/*
		 * <!-- Kiểm tra xem nó có đang ở bên phải cùng hoặc ở hàng đầu hay không? Nếu ở
		 * thì ko xét -->
		 */
		if (j != 7 && i != 0) {
			int row = i - 1;
			int col = j + 1;
			while (row > -1 && col < 8) {

				// <!-- Không có quân nào ở ô này -->
				if (board[row][col] == 0)
					result[row][col] = 1;

				// <!-- Quân cờ ở ô này là trắng, lúc này ta set quân ta đang set là quân gì-->
				else if (board[row][col] > 0)
					if (board[i][j] > 0)
						break;
					else {
						result[row][col] = 1;
						break;
					}

				// <!-- Quân cờ ở ô này là đen, lúc này ta set quân ta đang set là quân gì-->
				else {
					if (board[i][j] < 0)
						break;
					else {
						result[row][col] = 1;
						break;
					}
				}
				row--;
				col++;
			}
		}

		// <!-- Dưới phải -->
		/*
		 * <!-- Kiểm tra xem nó có đang ở bên phải cùng hoặc ở hàng cuối cùng hay không?
		 * Nếu ở thì ko xét -->
		 */
		if (j != 7 && i != 7) {
			int row = i + 1;
			int col = j + 1;
			while (row < 8 && col < 8) {

				// <!-- Không có quân nào ở ô này -->
				if (board[row][col] == 0)
					result[row][col] = 1;

				// <!-- Quân cờ ở ô này là trắng, lúc này ta set quân ta đang set là quân gì-->
				else if (board[row][col] > 0)
					if (board[i][j] > 0)
						break;
					else {
						result[row][col] = 1;
						break;
					}

				// <!-- Quân cờ ở ô này là đen, lúc này ta set quân ta đang set là quân gì-->
				else {
					if (board[i][j] < 0)
						break;
					else {
						result[row][col] = 1;
						break;
					}
				}
				row++;
				col++;
			}
		}

		// <!-- Trả về mảng result chứa những nước đi có thể đi của quân cờ này-->
		return result;

	}

}
