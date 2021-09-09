package model;

public class Model {

	// <!-- Các chế độ của trò chơi -->
	public static final int HUMAN_VS_HUMAN = 0;
	public static final int HUMAN_VS_COMPUTER = 1;
	public static final int COMPUPER_VS_COMPUTER = 2;

	// <!-- Lượt đánh -->
	public static final int WHITE = 0;
	public static final int BLACK = 1;

	// <!-- Các trạng thái của nước đi -->
	public static final int RETURN_BECAUSE_ERROR = 0;
	public static final int NORMALMOVE = 1;
	public static final int CASTLING_LEFT = 2;
	public static final int CASTLING_RIGHT = 3;
	public static final int PROMOTE = 4;

	// <!--Mảng vị trí của những quân cờ trên bản cờ-->
	private int[][] board;

	/*
	 * <!-- Biến này xác đích xem xe với vua 2 bên di chuyển chưa, mục đích chính là
	 * xử lý việc cho nhập thành hay không-->
	 */
	private OriginalCondition orginalConditionWhite;
	private OriginalCondition orginalConditionBlack;

	/*
	 * <!-- Mảng chứa những nước đi có thể đi của quần cờ đang được chọn , điều này
	 * được thực hiện khi có quân cờ được chọn từ người dùng từ view, view sẽ thông
	 * báo cho controller, controller chuyển giao cho model xử lý và sau đó sẽ cập
	 * nhập lại cho view để hiển thị cho người dùng -->
	 */
	private int[][] nextMovesBoard;

	// <!-- Construstor bình thường không nhận vào tham số-->
	public Model() {
		nextMovesBoard = new int[8][8];
		createPrimitiveBoard();
		createOriginalCondition();
	}

	// <!-- reset model -->
	public void reset() {
		nextMovesBoard = new int[8][8];
		createPrimitiveBoard();
		createOriginalCondition();
	}

	// <!--Tạo trạng thái ban đầu của xe-vua mỗi bên -->
	public void createOriginalCondition() {
		orginalConditionBlack = new OriginalCondition();
		orginalConditionWhite = new OriginalCondition();
	}

	/*
	 * <!-- Tạo bàn cờ lúc đầu game, mỗi quân cờ tượng trưng cho 1 số, số 0 là ô
	 * trống, phe màu đen thì thêm dấu trừ phía trước số -->
	 * 
	 * ==> Xe: 1
	 * 
	 * ==> Mã: 2
	 * 
	 * ==> Tượng: 3
	 * 
	 * ==> Hậu: 4
	 * 
	 * ==> Vua: 5
	 * 
	 * ==> Tốt: 6
	 */
	private void createPrimitiveBoard() {
		int[][] primitiveBoard = { { -1, -2, -3, -4, -5, -3, -2, -1 }, { -6, -6, -6, -6, -6, -6, -6, -6 },
		{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 6, 6, 6, 6, 6, 6, 6, 6 }, { 1, 2, 3, 4, 5, 3, 2, 1 } };
//int[][] primitiveBoard = { { -1, -2, -3, -4, -5, -3, -2, -1 }, 
//							{ -6, -6, -6, -6, -6, 0, 0,0 },
//							{ 0, 0, 0, 0, 0, 0, 0, 0 },
//							{ 0, 0, 0, 0, 2, 0, 0, 0 }, 
//							{ 0, 0, 3, 6, 0, 3, 4, 0 },
//							{ 0, 0, 0, 0, 6, 0, 6, -6 }, 
//							{ 6, 6, 6, 0, 0, 0, 0, 6 },
//							{ 1, 2, 0, 0, 5, 0, 0, 1 } };
//int[][] primitiveBoard = { { -1, 0, -3, 0, -5, 0, -1, 0 }, 
//		{ -6, 0, 0, -6, -6, 0, 0,0 },
//		{ 0, -6, -2, 0, 0, -2, 0, -3 },
//		{ 0, -4, 0, -6, 2, 0, 0, 0 }, 
//		{ 0, 0, 3, 6, 0, 3, 4, 0 },
//		{ 0, 0, 0, 0, 6, 0, 6, -6 }, 
//		{ 6, 6, 6, 0, 0, 0, 0, 6 },
//		{ 1, 2, 0, 0, 5, 0, 0, 1 } };
//		int[][] primitiveBoard = { { 0, 0, 0, 0, 0, 0, 0, 0 }, 
//				{ 2, 0, 0, 0, 0, 0, 0,0 },
//				{ 0, 0, 0, 0, -5, 0, 0, 0 },
//				{ 0, 0, 0, -2, -6, 0, 0, 0 }, 
//				{ 0, 6, 0, 0, 0, -4, 0, 0 },
//				{ 0, 5, 6, 0, 0, 0, 0, 0 }, 
//				{ 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 1, 0 } };
		board = primitiveBoard.clone();
	}

	// <!-- Hàm xử lý cập nhập lại mảng next move từ controller -->
	public void uploadNextMovesBoard(int i, int j, int value) {

		nextMovesBoard = new int[8][8];

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
				nextMovesBoard = King.getNextMovesBoard(board, i, j, orginalConditionBlack);
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
				nextMovesBoard = King.getNextMovesBoard(board, i, j, orginalConditionWhite);
				break;
			case 6:
				nextMovesBoard = Pawn.getNextMovesBoard(board, i, j);
				break;
			}
	}

	public void completePromote(int i, int j, int value) {
		if (i == 0)
			board[i][j] = value;
		else
			board[i][j] = -value;
	}

	// <!-- Di chuyển quân cờ đến vị trí chỉ định-->
	public boolean moveChessPiece(int i, int j, int value, int state) {

		switch (state) {

		// <!-- Trả về do thả sai vị trí >
		case RETURN_BECAUSE_ERROR:
			board[i][j] = value;
			break;

		// <!-- Di chuyển bình thường-->
		case NORMALMOVE:

			// <!-- Di chuyển nó cái đã -->
			board[i][j] = value;

			/*
			 * <!-- Kiểm tra 2 trạng thái vua-xe của 2 bên xem được di chuyển hết chưa, hết
			 * rồi thì không kiểm tra và xử lý nữa-->
			 */
			if (!orginalConditionBlack.isMoveAll() || !orginalConditionWhite.isMoveAll()) {
				switch (value) {

				// xe trắng
				case 1:
					if (board[7][0] == 0)
						if (!orginalConditionWhite.isLeftRook())
							orginalConditionWhite.setLeftRook(true);

					if (board[7][7] == 0)
						if (!orginalConditionWhite.isRightRook())
							orginalConditionWhite.setRightRook(true);

					break;

				// xe đen
				case -1:
					if (board[0][0] == 0)
						if (!orginalConditionBlack.isLeftRook())
							orginalConditionBlack.setLeftRook(true);

					if (board[0][7] == 0)
						if (!orginalConditionBlack.isRightRook())
							orginalConditionBlack.setRightRook(true);

					break;

				// Vua trắng
				case 5:
					if (!orginalConditionWhite.isKing())
						orginalConditionWhite.setKing(true);
					break;

				// Vua đen
				case -5:
					if (!orginalConditionBlack.isKing())
						orginalConditionBlack.setKing(true);
					break;
				}
			}
			break;

		// <!-- Nhập thành cách hậu-->
		case CASTLING_LEFT:

			// <!-- Gán giá trị cho vua cái đã-->
			board[i][j] = value;

			if (value == 5) {

				// <!-- Vua trắng -->

				// <!-- Thay đổi vị trí quân xe -->
				board[i][j + 1] = 1;

				// <!-- Cho originalCondition true hết -->
				orginalConditionWhite.setKing(true);
				orginalConditionWhite.setLeftRook(true);
				orginalConditionWhite.setRightRook(true);
			} else {

				// <!-- Vua đen -->

				// <!-- Thay đổi vị trí quân xe -->
				board[i][j + 1] = -1;

				// <!-- Cho originalCondition true hết -->
				orginalConditionBlack.setKing(true);
				orginalConditionBlack.setLeftRook(true);
				orginalConditionBlack.setRightRook(true);
			}
			board[i][0] = 0;

			break;

		// <!-- Nhập thành cách vua-->
		case CASTLING_RIGHT:

			// <!-- Gán giá trị cho vua cái đã-->
			board[i][j] = value;

			if (value == 5) {

				// <!-- Vua trắng -->

				// <!-- Thay đổi vị trí quân xe -->
				board[i][j - 1] = 1;

				// <!-- Cho originalCondition true hết -->
				orginalConditionWhite.setKing(true);
				orginalConditionWhite.setLeftRook(true);
				orginalConditionWhite.setRightRook(true);
			} else {

				// <!-- Vua đen -->

				// <!-- Thay đổi vị trí quân xe -->
				board[i][j - 1] = -1;

				// <!-- Cho originalCondition true hết -->
				orginalConditionBlack.setKing(true);
				orginalConditionBlack.setLeftRook(true);
				orginalConditionBlack.setRightRook(true);
			}
			board[i][7] = 0;

			break;

		// <!-- Phong tốt-->
		case PROMOTE:
			// <!-- Để trả về view có gì view tạo jdialog -->
			return true;
		}

		return false;
	}

	// <!-- Xem nước đi này có hợp lệ không (trống vua )-->
	public boolean tryMoves(Chessman chessman, int i, int j) {
		int old = board[i][j];
		board[i][j] = chessman.getValue();

		for (int k = 0; k < board.length; k++)
			for (int m = 0; m < board.length; m++)
				if (chessman.getValue() > 0) {
					if (board[k][m] < 0) {
						int[][] nextMovesBoardElement = nextMovesBoardElement(k, m, board[k][m], board);
						if (isHasPointToKing(nextMovesBoardElement)) {
							board[i][j] = old;
							return true;
						}
					}
				} else {
					if (board[k][m] > 0) {
						int[][] nextMovesBoardElement = nextMovesBoardElement(k, m, board[k][m], board);
						if (isHasPointToKing(nextMovesBoardElement)) {
							board[i][j] = old;
							return true;
						}
					}
				}

		board[i][j] = old;
		return false;
	}

	// <!-- Phương thúc kiểm tra mảng nước đi truyền vào có trỏ tới vua không-->
	public boolean isHasPointToKing(int[][] nextMovesBoardElement) {
		for (int i = 0; i < nextMovesBoardElement.length; i++)
			for (int j = 0; j < nextMovesBoardElement.length; j++)
				if (nextMovesBoardElement[i][j] == 1)
					if (board[i][j] == 5 || board[i][j] == -5)
						return true;

		return false;
	}

	// <!-- Trả về nước đi có thể đi của quân cờ truyền vào -->
	public static int[][] nextMovesBoardElement(int i, int j, int value, int[][] board) {

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
				nextMovesBoard = King.getNextMovesBoard(board, i, j, new OriginalCondition(true, true, true));
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
				nextMovesBoard = King.getNextMovesBoard(board, i, j, new OriginalCondition(true, true, true));
				break;
			case 6:
				nextMovesBoard = Pawn.getNextMovesBoard(board, i, j);
				break;
			}

		// <!-- return về mảng nước đi có thể đi -->
		return nextMovesBoard;
	}

	// <!-- Đếm số nước đi của 1 mảng nước đi-->
	public int countMoves(int[][] nextMovesBoard) {
		int count = 0;
		for (int i = 0; i < nextMovesBoard.length; i++)
			for (int j = 0; j < nextMovesBoard.length; j++)
				if (nextMovesBoard[i][j] == 1)
					count++;

		return count;
	}

	// <!-- Kiểm tra game hòa, hòa khi ko bị chiếu mà cũng ko có nước đi-->
	public boolean isTieGame(int turn) {

		boolean isHasMove = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (turn == WHITE) {
					if (board[i][j] > 0) {

						// <!-- Lấy mảng nước đi,nếu có đường đi thì break luôn-->
						if (countMoves(nextMovesBoardElement(i, j, board[i][j], board)) != 0) {
							isHasMove = true;
							break;
						}
					}
				} else {
					if (board[i][j] < 0) {

						// <!-- Lấy mảng nước đi,nếu có đường đi thì break luôn-->
						if (countMoves(nextMovesBoardElement(i, j, board[i][j], board)) != 0) {
							isHasMove = true;
							break;
						}
					}
				}
			}
			if (isHasMove)
				break;
		}

		// <!-- Duyệt qua hết mà không có đường đi thì return true-->
		if (!isHasMove)
			return true;
		return false;
	}

	// <!-- Kiểm tra việc kết thúc trò chơi -->
	public boolean isEndGame(int turn) {

		// <!-- Kiểm tra em vua có nằm trong tầm ngắm địch hay ko -->
		if (enemyHasPointToKing(turn)) {

			// <!-- Có nằm thì tính xem xem em vua chạy thoát được không -->

			// <!-- Tạo biến boolean hỗ trợ viết tăng tốc chạy-->
			boolean isEscape = false;

			/*
			 * <!-- Ý tưởng là duyệt qua tất cả quân cờ lượt TURN, tính ra các nước đi kế
			 * tiếp của nó, rồi thử di chuyển nó đên các nước đi đó, nếu vua thoát khỏi tầm
			 * địch thì dừng, nếu kiểm tra qua hết mà vua vẫn ở trong tầm địch thì END
			 * GAME-->
			 */
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (turn == WHITE) {
						if (board[i][j] > 0) {

							// <!-- Lấy mảng nước đi-->
							int[][] nextMovesBoardElement = nextMovesBoardElement(i, j, board[i][j], board);

							// <!-- Thử đi qua hết, nếu vua escape thì trả về true-->
							if (tryMovesFromArrayNextMoves(nextMovesBoardElement, i, j, board[i][j], turn)) {
								isEscape = true;
								break;
							}
						}
					} else {
						if (board[i][j] < 0) {

							// <!-- Lấy mảng nước đi-->
							int[][] nextMovesBoardElement = nextMovesBoardElement(i, j, board[i][j], board);

							// <!-- Thử đi qua hết, nếu vua escape thì trả về true-->
							if (tryMovesFromArrayNextMoves(nextMovesBoardElement, i, j, board[i][j], turn)) {
								isEscape = true;
								break;
							}
						}
					}
				}

				// <!-- Nếu vua thoát rồi thì break -->
				if (isEscape)
					break;
			}

			// <!-- Không chạy thoát được --> end game -->
			if (!isEscape)
				return true;

		}

		return false;
	}

	/*
	 * <!-- hàm này nhận vào mảng nước đi có thể đi của quân cờ A, vị trí của A hiện
	 * tại,quân cờ và turn-->
	 */
	public boolean tryMovesFromArrayNextMoves(int[][] nextMovesBoardElement, int i, int j, int value, int turn) {

		// <!-- Duyệt qua mảng nước đi -->
		for (int m = 0; m < nextMovesBoardElement.length; m++)
			for (int n = 0; n < nextMovesBoardElement.length; n++) {
				if (nextMovesBoardElement[m][n] == 1) {

					// <!-- Thử di chuyển, nhớ lưu quân cờ bị di chuyển tới-->
					int old = board[m][n];
					board[i][j] = 0;
					board[m][n] = value;

					// <!-- Nếu vua trốn thoát thì return true -->
					if (!enemyHasPointToKing(turn)) {

						// <!-- Return thì return nhưng trả lại như ban đầu rồi return-->
						board[i][j] = value;
						board[m][n] = old;
						return true;
					}

					// <!-- Thử xong thì trả lại như ban đầu-->
					board[i][j] = value;
					board[m][n] = old;
				}
			}

		// <!-- Quân cờ này không thể cứu vãn rồi --> return false -->
		return false;
	}

	// <!-- Hàm kiểm tra xem thử vua nó nằm trong tầm của địch hay không -->
	public boolean enemyHasPointToKing(int turn) {

		// <!-- Duyệt qua mảng -->
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (turn == BLACK) {
					if (board[i][j] > 0) {

						// <!-- Lấy mảng nước đi-->
						int[][] nextMovesBoardElement = nextMovesBoardElement(i, j, board[i][j], board);

						// <!-- Nếu vua nằm trong tầm địch thì return true -->
						if (isHasPointToKing(nextMovesBoardElement))
							return true;
					}
				} else {
					if (board[i][j] < 0) {

						// <!-- Lấy mảng nước đi-->
						int[][] nextMovesBoardElement = nextMovesBoardElement(i, j, board[i][j], board);

						// <!-- Nếu vua nằm trong tầm địch thì return true -->
						if (isHasPointToKing(nextMovesBoardElement))
							return true;
					}
				}
			}
		}
		return false;
	}

	// <!-- Máy di chuyển -->
	public void itIsAITurn(int turn, int aiDepth) {

		// <!-- Tạo root lấy trạng thái hiện tại của bàn cờ -->
		Node root = new Node(board, turn, orginalConditionBlack, orginalConditionWhite, aiDepth, 333);

		// <!-- Tính toán xử lý -->
		long time = System.currentTimeMillis();
		Node nextState = root.handling();
		System.out.println("Thời gian chạy: " + (System.currentTimeMillis() - time)+" milis");

		// <!-- Cập nhập lại 2 đối tượng kiểm tra nhập thành -->
		this.orginalConditionBlack = new OriginalCondition(nextState.getOrginalConditionBlack().isLeftRook(),
				nextState.getOrginalConditionBlack().isRightRook(), nextState.getOrginalConditionBlack().isKing());
		this.orginalConditionWhite = new OriginalCondition(nextState.getOrginalConditionWhite().isLeftRook(),
				nextState.getOrginalConditionWhite().isRightRook(), nextState.getOrginalConditionWhite().isKing());

		// <!-- Cập nhập lại mảng vị trí-->
		int[][] bestWay = nextState.getState();
		for (int i = 0; i < bestWay.length; i++)
			for (int j = 0; j < bestWay.length; j++)
				board[i][j] = bestWay[i][j];
	}

	// <!-- getter -->

	// <!-- get mảng vị trí của những quân cờ trên bàn cờ>
	public int[][] getBoard() {
		return board;
	}

	// <!-- get mảng những nước đi có thể đi của quân cờ đang được chọn>
	public int[][] getNextMovesBoard() {
		return nextMovesBoard;
	}

	// <!-- end getter -->

}
