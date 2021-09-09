package model;

public class King {

	// <!-- Mảng đánh giá trạng thái chỗ hiện tại tốt hay xấu-->
	// <!-- Cho quân trắng -->
	private static final int[][] evaluteChessPosition = { { -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -30, -40, -40, -50, -50, -40, -40, -30 }, { -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -30, -40, -40, -50, -50, -40, -40, -30 }, { -20, -30, -30, -40, -40, -30, -30, -20 },
			{ -10, -20, -20, -20, -20, -20, -20, -10 }, { 20, 20, 0, 0, 0, 0, 20, 20 },
			{ 20, 30, 10, 0, 0, 10, 30, 20 } };

	// <!-- Cho quân đen -->
	private static final int[][] evaluteChessPositionReverse = { { 20, 30, 10, 0, 0, 10, 30, 20 },
			{ 20, 20, 0, 0, 0, 0, 20, 20 }, { -10, -20, -20, -20, -20, -20, -20, -10 },
			{ -20, -30, -30, -40, -40, -30, -30, -20 }, { -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -30, -40, -40, -50, -50, -40, -40, -30 }, { -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -30, -40, -40, -50, -50, -40, -40, -30 } };

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
	public static int[][] getNextMovesBoard(int[][] board, int i, int j, OriginalCondition orginalCondition) {
		
		/*
		 * <!-- Mảng chứa những vị trí có thể đi của quân cờ truyền vô (i,j) , vị trí
		 * nào có thể đi thì giá trị là 1-->
		 * 
		 */
		int[][] result = new int[8][8];

		// <!-- hover tại chính nó cái đã -->
		result[i][j] = 2;

		// <!-- Vị trí ngẫu nhiên trên bàn cờ -->
		for (int k = i - 1; k < i + 2; k++)
			for (int m = j - 1; m < j + 2; m++)
				if ((k != i || m != j) && k > -1 && k < 8 && m > -1 && m < 8)
					if (board[i][j] == -5) {
						if (board[k][m] >= 0)
							result[k][m] = 1;
					} else {
						if (board[k][m] <= 0)
							result[k][m] = 1;
					}

		/*
		 * <!-- Sau khi ta có những nước đi có thể đi của vua rồi, h chúng ta sẽ loại
		 * trừ bớt những nước đi mà nằm trong thế ăn của quân địch-->
		 */
		for (int d = 0; d < board.length; d++) {
			for (int f = 0; f < board.length; f++)

				// <!-- Vua trắng-->
				if (board[i][j] > 0) {

					// <!-- Có nước đi thì set tiếp -->
					if (result[d][f] == 1) {

						// <!-- Vì thử di chuyển, nên lưu lại giá trị vua đi tới-->

						int old = board[d][f];

						// <!-- Thử di chuyển vua lại chỗ có thể đi xem sao-->
						board[d][f] = 5;
						board[i][j] = 0;

						// <!-- Khai báo 1 boolean giúp tăng tốc độ chạy-->
						boolean isHasPoint = false;

						// <!-- Duyệt qua mảng cờ-->
						for (int m = 0; m < board.length; m++) {
							for (int n = 0; n < board.length; n++)

								// <!-- Nếu quân cờ là địch thì ta làm -->
								if (board[m][n] < 0) {

									// <!-- Lấy mảng nước đi có thể đi của nó -->
									int[][] nextMovesBoard = King.nextMovesBoard(m, n, board[m][n], board);

									// <!-- Kiểm tra xem vị trí của vua thử di chuyển có bị vi phạm hay không -->
									if (isHashPointTo(d, f, nextMovesBoard)) {

										// <!-- Vi phạm thì cho isHasPoint thành true -> break -->
										isHasPoint = true;
										break;
									}
								}

							// <!-- Vi phạm thì out luôn tăng tốc chạy -->
							if (isHasPoint)
								break;
						}

						// <!-- Có vi phạm thì loại nước này khỏi mảng nước có thể đi -->
						if (isHasPoint)
							result[d][f] = 0;

						// <!-- Trả vua về vị trí ban đầu và khôi phục mảng như ban đầu -->
						board[d][f] = old;
						board[i][j] = 5;

					}

					// <!-- Vua đen-->
				} else {

					if (result[d][f] == 1) {

						// <!-- Vì thử di chuyển, nên lưu lại giá trị vua đi tới-->

						int old = board[d][f];

						// <!-- Thử di chuyển vua lại chỗ có thể đi xem sao-->
						board[d][f] = -5;
						board[i][j] = 0;

						// <!-- Khai báo 1 boolean giúp tăng tốc độ chạy-->
						boolean isHasPoint = false;

						// <!-- Duyệt qua mảng cờ-->
						for (int m = 0; m < board.length; m++) {
							for (int n = 0; n < board.length; n++)

								// <!-- Nếu quân cờ là địch thì ta làm -->
								if (board[m][n] > 0) {

									// <!-- Lấy mảng nước đi có thể đi của nó -->
									int[][] nextMovesBoard = King.nextMovesBoard(m, n, board[m][n], board);

									// <!-- Kiểm tra xem vị trí của vua thử di chuyển có bị vi phạm hay không -->
									if (isHashPointTo(d, f, nextMovesBoard)) {

										// <!-- Vi phạm thì cho isHasPoint thành true -> break -->
										isHasPoint = true;
										break;
									}
								}

							// <!-- Vi phạm thì out luôn tăng tốc chạy -->
							if (isHasPoint)
								break;
						}

						// <!-- Có vi phạm thì loại nước này khỏi mảng nước có thể đi -->
						if (isHasPoint)
							result[d][f] = 0;

						// <!-- Trả vua về vị trí ban đầu và khôi phục mảng như ban đầu -->
						board[d][f] = old;
						board[i][j] = -5;

					}
				}
		}

		// <!-- Kiểm tra nhập thành -->
		// <!-- Vua chưa nhập thành thì mới tiếp tục -->
		if (!orginalCondition.isKing()) {

			// <!-- Kiểm tra xem thử vua hiện tại có đang bị chiéu hay không -->
			if (!enemyHasPointToKing(board[i][j], board)) {

				// <!-- Nhập thành cách hậu, quân xe phải chưa gì chuyển-->
				if (!orginalCondition.isLeftRook() && board[i][0] != 0) {

					// <!-- Xem thử có vật cản ở giữa vua và xe hay không>
					int count = 0;
					for (int k = 1; k < 4; k++)
						if (board[i][k] != 0)
							break;
						else
							count++;

					/*
					 * <!-- Xem ra không có vật cản, ta xem thử đường đi của vua có bị trỏ tới từ
					 * quân địch hay không-->
					 */
					if (count == 3) {

						// <!-- đây là 2 vị trí đường đi của vua -->
						int index1 = j - 2;
						int index2 = j - 1;

						/*
						 * <!-- Giờ là lúc xem thử trong các nước đi tiếp theo của các quân cờ địch xem
						 * thử có trỏ đến 2 vị trí kia hay không -->
						 */
						for (int k = 0; k < board.length; k++)
							for (int m = 0; m < board.length; m++)

								// <!-- Vua trắng -->
								if (board[i][j] == 5) {

									// <!-- Đặt 1 con boolean ở đây, nếu ko ok thì boolean này thành true-->
									boolean isError = false;

									// <!-- Duyệt qua bàn cờ -->
									for (int d = 0; d < board.length; d++) {
										for (int f = 0; f < board.length; f++)

											if (board[d][f] < 0) {

												// <!-- Aaaa quân này là quân đen -->
												// <!-- Lấy nước đi tiếp theo thử nó có trỏ tới 2 vị trí kia ko-->
												int[][] nextMovesBoard = nextMovesBoard(d, f, board[d][f], board);

												// <!-- Kiểm tra xem có trỏ tới không -->
												if (isHasPointToTwoIndex(nextMovesBoard, i, index1, index2)) {

													// <!-- Nếu có thì cho isError thành true xong break-->
													isError = true;
													break;
												}

											}

										// <!-- isError đã true thì không set tiếp break luôn tiết kiệm time run-->
										if (isError)
											break;
									}

									// <!-- Nếu như trải va vòng for và isError là flase thì chứng tỏ ok-->
									if (!isError)
										result[i][j - 2] = 1;
								}

								// <!-- Vua đen -->
								else {

									// <!-- Đặt 1 con boolean ở đây, nếu ko ok thì boolean này thành true-->
									boolean isError = false;

									// <!-- Duyệt qua bàn cờ -->
									for (int d = 0; d < board.length; d++) {
										for (int f = 0; f < board.length; f++)

											if (board[d][f] > 0) {

												// <!-- Aaaa quân này là quân trắng -->
												// <!-- Lấy nước đi tiếp theo thử nó có trỏ tới 2 vị trí kia ko-->
												int[][] nextMovesBoard = nextMovesBoard(d, f, board[d][f], board);

												// <!-- Kiểm tra xem có trỏ tới không -->
												if (isHasPointToTwoIndex(nextMovesBoard, i, index1, index2)) {

													// <!-- Nếu có thì cho isError thành true xong break-->
													isError = true;
													break;
												}

											}

										// <!-- isError đã true thì không set tiếp break luôn tiết kiệm time run-->
										if (isError)
											break;
									}

									// <!-- Nếu như trải va vòng for và isError là flase thì chứng tỏ ok-->
									if (!isError)
										result[i][j - 2] = 1;

								}
					}
				}

				if (!orginalCondition.isRightRook()) {
					// <!-- Xem thử có vật cản ở giữa vua và xe hay không>
					int count = 0;
					for (int k = 5; k < 7; k++)
						if (board[i][k] != 0)
							break;
						else
							count++;

					/*
					 * <!-- Xem ra không có vật cản, ta xem thử đường đi của vua có bị trỏ tới từ
					 * quân địch hay không-->
					 */
					if (count == 2) {

						// <!-- đây là 2 vị trí đường đi của vua -->
						int index1 = j + 2;
						int index2 = j + 1;

						/*
						 * <!-- Giờ là lúc xem thử trong các nước đi tiếp theo của các quân cờ địch xem
						 * thử có trỏ đến 2 vị trí kia hay không -->
						 */
						for (int k = 0; k < board.length; k++)
							for (int m = 0; m < board.length; m++)

								// <!-- Vua trắng -->
								if (board[i][j] == 5) {

									// <!-- Đặt 1 con boolean ở đây, nếu ko ok thì boolean này thành true-->
									boolean isError = false;

									// <!-- Duyệt qua bàn cờ -->
									for (int d = 0; d < board.length; d++) {
										for (int f = 0; f < board.length; f++)

											if (board[d][f] < 0) {

												// <!-- Aaaa quân này là quân đen -->
												// <!-- Lấy nước đi tiếp theo thử nó có trỏ tới 2 vị trí kia ko-->
												int[][] nextMovesBoard = nextMovesBoard(d, f, board[d][f], board);

												// <!-- Kiểm tra xem có trỏ tới không -->
												if (isHasPointToTwoIndex(nextMovesBoard, i, index1, index2)) {

													// <!-- Nếu có thì cho isError thành true xong break-->
													isError = true;
													break;
												}

											}

										// <!-- isError đã true thì không set tiếp break luôn tiết kiệm time run-->
										if (isError)
											break;
									}

									// <!-- Nếu như trải va vòng for và isError là flase thì chứng tỏ ok-->
									if (!isError)
										result[i][j + 2] = 1;
								}

								// <!-- Vua đen -->
								else {

									// <!-- Đặt 1 con boolean ở đây, nếu ko ok thì boolean này thành true-->
									boolean isError = false;

									// <!-- Duyệt qua bàn cờ -->
									for (int d = 0; d < board.length; d++) {
										for (int f = 0; f < board.length; f++)

											if (board[d][f] > 0) {

												// <!-- Aaaa quân này là quân trắng -->
												// <!-- Lấy nước đi tiếp theo thử nó có trỏ tới 2 vị trí kia ko-->
												int[][] nextMovesBoard = nextMovesBoard(d, f, board[d][f], board);

												// <!-- Kiểm tra xem có trỏ tới không -->
												if (isHasPointToTwoIndex(nextMovesBoard, i, index1, index2)) {

													// <!-- Nếu có thì cho isError thành true xong break-->
													isError = true;
													break;
												}

											}

										// <!-- isError đã true thì không set tiếp break luôn tiết kiệm time run-->
										if (isError)
											break;
									}

									// <!-- Nếu như trải va vòng for và isError là flase thì chứng tỏ ok-->
									if (!isError)
										result[i][j + 2] = 1;

								}
					}
				}
			}
		}
		// <!-- Trả về mảng result chứa những nước đi có thể đi của quân cờ này-->
		return result;
	}

	// <!-- Hàm kiểm tra 2 số trong 1 mảng xem vị trí của nó có khác 0 hay ko-->
	public static boolean isHasPointToTwoIndex(int[][] nextMovesBoard, int i, int j1, int j2) {
		for (int k = 0; k < nextMovesBoard.length; k++)
			for (int m = 0; m < nextMovesBoard.length; m++)
				if (nextMovesBoard[k][m] != 0)
					if (k == i && (m == j1 || m == j2))
						return true;

		return false;
	}

	// <!-- Hàm trả về những nước đi có thể đi của quân vua, trừ nước nhập thành-->
	public static int[][] nextMovesBoardWithoutCastling(int i, int j, int[][] board) {

		/*
		 * <!-- Mảng chứa những vị trí có thể đi của quân cờ truyền vô (i,j) , vị trí
		 * nào có thể đi thì giá trị là 1-->
		 * 
		 */
		int[][] result = new int[8][8];

		// <!-- hover tại chính nó cái đã -->
		result[i][j] = 2;

		// <!-- Vị trí ngẫu nhiên trên bàn cờ -->
		for (int k = i - 1; k < i + 2; k++)
			for (int m = j - 1; m < j + 2; m++)
				if ((k != i || m != j) && k > -1 && k < 8 && m > -1 && m < 8)
					if (board[i][j] == -5) {
						if (board[k][m] >= 0)
							result[k][m] = 1;
					} else {
						if (board[k][m] <= 0)
							result[k][m] = 1;
					}

		// <!-- Trả về mảng result chứa những nước đi có thể đi của quân cờ này-->
		return result;
	}

	// <!-- Trả về nước đi có thể đi của quân cờ truyền vào -->
	public static int[][] nextMovesBoard(int i, int j, int value, int[][] board) {

		int[][] nextMovesBoard = new int[8][8];

		// <!-- Nếu như ô nằm trong bàn cờ, và value phải != 0) mới tiếp tục -->
		if (i != -1 && j != -1 && value != 0)

			/*
			 * <!-- Tương ứng với giá trị mà xác định được quân cờ từ đó giao cho các class
			 * của chính nó phụ trách-->
			 */
			switch (value) {
			case -1:
				nextMovesBoard = Rook.getNextMovesBoard(board, i, j);
				break;
			case -2:
				nextMovesBoard = Knight.getNextMovesBoard(board, i, j);
				break;
			case -3:
				nextMovesBoard = Bishop.getNextMovesBoard(board, i, j);
				break;
			case -4:
				nextMovesBoard = Queen.getNextMovesBoard(board, i, j);
				break;
			case -5:
				nextMovesBoard = King.nextMovesBoardWithoutCastling(i, j, board);
				break;
			case -6:
				nextMovesBoard = Pawn.getNextMovesBoard(board, i, j);
				break;
			case 1:
				nextMovesBoard = Rook.getNextMovesBoard(board, i, j);
				break;
			case 2:
				nextMovesBoard = Knight.getNextMovesBoard(board, i, j);
				break;
			case 3:
				nextMovesBoard = Bishop.getNextMovesBoard(board, i, j);
				break;
			case 4:
				nextMovesBoard = Queen.getNextMovesBoard(board, i, j);
				break;
			case 5:
				nextMovesBoard = King.nextMovesBoardWithoutCastling(i, j, board);
				break;
			case 6:
				nextMovesBoard = Pawn.getNextMovesBoard(board, i, j);
				break;
			}

		// <!-- return về mảng nước đi có thể đi -->
		return nextMovesBoard;
	}

	// <!-- Xử lý phạm vi di chuyển>
	public static boolean isHashPointTo(int m, int n, int[][] nextMovesBoard) {

		// <!-- Duyệt qua hết mảng nước đi có thể đi củaa quân địch, bắt đầu kiểm tra-->
		for (int i = 0; i < nextMovesBoard.length; i++)
			for (int j = 0; j < nextMovesBoard.length; j++)

				// <!-- Duyệt đúng vị trí nước có thể đi mới tiếp tục-->
				if (nextMovesBoard[i][j] == 1)

					// <!-- Nếu như i j bằng m n thì có nước đi return true-->
					if (i == m && j == n)
						return true;

		// <!-- Không có nước đi return về false -->
		return false;

	}

	// <!-- Hàm kiểm tra xem thử vua nó nằm trong tầm của địch hay không -->
	public static boolean enemyHasPointToKing(int value, int[][] board) {

		// <!-- Duyệt qua mảng -->
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (value == 5) {
					if (board[i][j] < 0) {

						// <!-- Lấy mảng nước đi-->
						int[][] nextMovesBoardElement = nextMovesBoard(i, j, board[i][j], board);

						// <!-- Nếu vua nằm trong tầm địch thì return true -->
						if (isHasPointToKing(nextMovesBoardElement, board))
							return true;
					}
				} else {
					if (board[i][j] > 0) {

						// <!-- Lấy mảng nước đi-->
						int[][] nextMovesBoardElement = nextMovesBoard(i, j, board[i][j], board);

						// <!-- Nếu vua nằm trong tầm địch thì return true -->
						if (isHasPointToKing(nextMovesBoardElement, board))
							return true;
					}
				}
			}
		}
		return false;
	}

	// <!-- Phương thúc kiểm tra mảng nước đi truyền vào có trỏ tới vua không-->
	public static boolean isHasPointToKing(int[][] nextMovesBoardElement, int[][] board) {
		for (int i = 0; i < nextMovesBoardElement.length; i++)
			for (int j = 0; j < nextMovesBoardElement.length; j++)
				if (nextMovesBoardElement[i][j] == 1)
					if (board[i][j] == 5 || board[i][j] == -5)
						return true;

		return false;
	}

}
