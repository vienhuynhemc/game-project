package model;

import java.util.ArrayList;
import java.util.List;

public class Node {

	/*
	 * <!-- Điểm của các quân cờ -->
	 * 
	 *
	 * Paw == 100
	 * 
	 * Knight == 320
	 * 
	 * Bishop == 330
	 * 
	 * Rook == 500
	 * 
	 * Queen == 900
	 * 
	 * King == 9000;
	 * 
	 */

	// <!-- Bàn cờ của trạng thái này -->
	private int[][] state;

	// <!-- Để biết lượt đi của quân cờ này là gì -->
	private int turn;

	// <!-- Các trạng thái kế tiếp của trạng thái này -->
	private List<Node> neighbours;

	// <!-- Điểm đánh giá của thế cờ này là bao nhiêu-->
	private int heuristic;

	// <!-- Điểm này sẽ có được thông qua minimax -->
	private Integer bestHeuristic;

	// <!-- Độ sâu của minimax -->
	private int aiDepth;

	// <!-- 2 con để xử lý xem có nước đi nhập thành hay không -->
	private OriginalCondition orginalConditionBlack;
	private OriginalCondition orginalConditionWhite;

	// <!-- Cha của node -->
	private Node parent;

	// <!-- Bộ 4 tham số giúp việc add node con -->
	private int ip;
	private int jp;
	private int ic;
	private int jc;

	// <!-- Tham số này phụ giúp phân biệt root(333) và thường(100)-->
	private int name;

	// <!-- Nhận vào đầy đủ thông số cần thiết -->
	public Node(int[][] board, int turn, OriginalCondition orginalConditionBlack,
			OriginalCondition orginalConditionWhite, int aiDepth, int name) {

		this.turn = turn;
		this.aiDepth = aiDepth;
		this.name = name;

		// <!-- Sao chép 2 đối tượng kiểm tra nhập thành -->
		this.orginalConditionBlack = new OriginalCondition(orginalConditionBlack.isLeftRook(),
				orginalConditionBlack.isRightRook(), orginalConditionBlack.isKing());
		this.orginalConditionWhite = new OriginalCondition(orginalConditionWhite.isLeftRook(),
				orginalConditionWhite.isRightRook(), orginalConditionWhite.isKing());

		// <!-- Sao chép mảng bàn cờ -->
		state = new int[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				state[i][j] = board[i][j];
			}
		}

		// <!-- Không có gì thì cho = null -->
		parent = null;
		bestHeuristic = null;

		// <!-- Khởi tạo mảng hàng xóm -->
		neighbours = new ArrayList<Node>();

		// <!-- Tính điểm luôn -->
		heuristic = heuristic();
	}

	/*
	 * <!-- Construstor hỗ trợ việc lấy parent nên chỉ cần set lại 2 tham số cho
	 * node mới là bestHeuristic và parent-->
	 */
	public Node(Node node, int name) {
		this.name = name;
		this.setBestHeuristic(node.getBestHeuristic());
		this.setParent(node.getParent());
	}

	/*
	 * <!-- Hàm tính các trạng thái kế tiếp của node -->
	 */
	public boolean addNeightbours() {

		boolean isHasNextState = false;

		while (ip != -1 && jp != -1) {

			/*
			 * <!-- Ta tạo mảng nước đi tiếp theo của quân cờ, nếu quân cờ là quân cờ hợp lệ
			 * thì mảng sẽ khác null và ta làm tiếp -->
			 */
			int[][] nextMovesBoard = null;

			// <!-- Gán giá trị cho nextMovesBoard nè -->
			if (turn == Model.BLACK) {
				if (state[ip][jp] < 0)
					nextMovesBoard = getNextMovesBoardOfChessPiece(ip, jp, state, state[ip][jp], orginalConditionBlack,
							orginalConditionWhite);
			} else if (turn == Model.WHITE)
				if (state[ip][jp] > 0)
					nextMovesBoard = getNextMovesBoardOfChessPiece(ip, jp, state, state[ip][jp], orginalConditionBlack,
							orginalConditionWhite);

			// <!-- != null, thì đúng là quân cờ hợp lệ, h ta tạo các trạng thái con -->
			if (nextMovesBoard != null) {

				// <!-- Duyệt qua mảng nước đi -->
				while (ic != -1 && jc != -1) {

					// <!-- Nếu có nước đi thì ... -->
					if (nextMovesBoard[ic][jc] == 1) {

						// <!-- Tạo mảng cho node con -->
						int[][] newArray = new int[8][8];
						for (int k = 0; k < newArray.length; k++)
							for (int k2 = 0; k2 < newArray.length; k2++)
								newArray[k][k2] = state[k][k2];

						// <!-- Tạo 2 đối tượng kiểm tra nhập thành cho node con-->
						OriginalCondition childOrBlack = new OriginalCondition(orginalConditionBlack.isLeftRook(),
								orginalConditionBlack.isRightRook(), orginalConditionBlack.isKing());
						OriginalCondition childOrWhite = new OriginalCondition(orginalConditionWhite.isLeftRook(),
								orginalConditionWhite.isRightRook(), orginalConditionWhite.isKing());

						// <!-- Lưu lại giá trị quân cờ sau đso cho chỗ nó ở thành rỗng -->
						int value = newArray[ip][jp];
						newArray[ip][jp] = 0;

						// <!-- Kiểm tra nước đi và đi để cho nước đi phù hợp -->
						if (value == 5 || value == -5)

							// <!-- Nếu như có xê dịch hơn 2 1 chứng tỏ là nhập thành -->
							if (Math.abs(jc - jp) > 1)

								// <!-- jNew > vị trí j cũ ==> right -->
								if (jc > jp)
									moveChessPiece(ic, jc, ip, jp, value, Model.CASTLING_RIGHT, newArray, childOrBlack,
											childOrWhite);

								// <!-- Ngược lại left -->
								else
									moveChessPiece(ic, jc, ip, jp, value, Model.CASTLING_LEFT, newArray, childOrBlack,
											childOrWhite);

							// <!-- Nếu như không phải nhập thành thì di chuyển bình thường-->
							else
								moveChessPiece(ic, jc, ip, jp, value, Model.NORMALMOVE, newArray, childOrBlack,
										childOrWhite);

						else if (value == 6 || value == -6) {

							// <!-- Xử lý khi là tốt-->
							// <!-- Đi tới cuối bàn cờ thì làm sao đó -->
							if (ic == 0 || ic == 7)
								if (turn == Model.WHITE) {
									moveChessPiece(ic, jc, ip, jp, 1, Model.NORMALMOVE, newArray, childOrBlack,
											childOrWhite);
									// <!-- Tạo node con -->
									Node childNode = new Node(newArray, turn == Model.BLACK ? Model.WHITE : Model.BLACK,
											childOrBlack, childOrWhite, aiDepth, 100);

									// <!-- Thêm vào neightbours -->
									childNode.setParent(this);
									neighbours.add(childNode);

									moveChessPiece(ic, jc, ip, jp, 2, Model.NORMALMOVE, newArray, childOrBlack,
											childOrWhite);
									// <!-- Tạo node con -->
									childNode = new Node(newArray, turn == Model.BLACK ? Model.WHITE : Model.BLACK,
											childOrBlack, childOrWhite, aiDepth, 100);

									// <!-- Thêm vào neightbours -->
									childNode.setParent(this);
									neighbours.add(childNode);
									moveChessPiece(ic, jc, ip, jp, 3, Model.NORMALMOVE, newArray, childOrBlack,
											childOrWhite);
									// <!-- Tạo node con -->
									childNode = new Node(newArray, turn == Model.BLACK ? Model.WHITE : Model.BLACK,
											childOrBlack, childOrWhite, aiDepth, 100);

									// <!-- Thêm vào neightbours -->
									childNode.setParent(this);
									neighbours.add(childNode);

									moveChessPiece(ic, jc, ip, jp, 4, Model.NORMALMOVE, newArray, childOrBlack,
											childOrWhite);
								} else {
									moveChessPiece(ic, jc, ip, jp, -1, Model.NORMALMOVE, newArray, childOrBlack,
											childOrWhite);
									// <!-- Tạo node con -->
									Node childNode = new Node(newArray, turn == Model.BLACK ? Model.WHITE : Model.BLACK,
											childOrBlack, childOrWhite, aiDepth, 100);

									// <!-- Thêm vào neightbours -->
									childNode.setParent(this);
									neighbours.add(childNode);
									moveChessPiece(ic, jc, ip, jp, -2, Model.NORMALMOVE, newArray, childOrBlack,
											childOrWhite);
									// <!-- Tạo node con -->
									childNode = new Node(newArray, turn == Model.BLACK ? Model.WHITE : Model.BLACK,
											childOrBlack, childOrWhite, aiDepth, 100);

									// <!-- Thêm vào neightbours -->
									childNode.setParent(this);
									neighbours.add(childNode);
									moveChessPiece(ic, jc, ip, jp, -3, Model.NORMALMOVE, newArray, childOrBlack,
											childOrWhite);
									// <!-- Tạo node con -->
									childNode = new Node(newArray, turn == Model.BLACK ? Model.WHITE : Model.BLACK,
											childOrBlack, childOrWhite, aiDepth, 100);

									// <!-- Thêm vào neightbours -->
									childNode.setParent(this);
									neighbours.add(childNode);

									moveChessPiece(ic, jc, ip, jp, -4, Model.NORMALMOVE, newArray, childOrBlack,
											childOrWhite);
								}
							else
								moveChessPiece(ic, jc, ip, jp, value, Model.NORMALMOVE, newArray, childOrBlack,
										childOrWhite);

						} else

							// <!-- Khi không phải trường hợp đặc biệt thì di chuyển bình thường-->
							moveChessPiece(ic, jc, ip, jp, value, Model.NORMALMOVE, newArray, childOrBlack,
									childOrWhite);

						// <!-- Sau khi đi xong thì addNode con vào thôi-->

						// <!-- Tạo node con -->
						Node childNode = new Node(newArray, turn == Model.BLACK ? Model.WHITE : Model.BLACK,
								childOrBlack, childOrWhite, aiDepth, 100);

						// <!-- Thêm vào neightbours -->
						childNode.setParent(this);
						neighbours.add(childNode);
						// <!-- -Đã có mục tiêu break ->
						isHasNextState = true;
						break;
					}
					// <!-- Tăng tuần tự-->
					jc++;
					if (jc == 8) {
						jc = 0;
						ic++;
					}
					if (ic == 8) {
						ic = -1;
						jc = -1;
					}
				}
			}

			// <!-- -Đã có mục tiêu break ->
			if (isHasNextState)
				break;
			// <!-- Tăng tuần tự-->
			jp++;
			if (jp == 8) {
				jp = 0;
				ip++;
				if (ip == 8) {
					ip = -1;
					jp = -1;
				}
			}
			ic = 0;
			jc = 0;
		}

		// <!-- Có trạng thái tiếp theo rồi thì out ra thì phải tăng vị trí xét lên-->
		if (isHasNextState) {
			jc++;
			if (jc == 8) {
				jc = 0;
				ic++;
			}
			if (ic == 8) {
				ic = 0;
				jc = 0;
				jp++;
				if (jp == 8) {
					jp = 0;
					ip++;
				}
				if (ip == 8)
					return false;
				else
					return true;

			}
			return true;
		} else
			return false;

	}

	/*
	 * <!-- Tạo ra hết trạng thái con rồi tính điểm cho tất cả trạng thái, lấy trạng
	 * thái tốt nhất trả về -->
	 */
	public Node handling() {

		// <!-- Chạy thuật toán -->
		ArrayList<Long> array = new ArrayList<Long>();
		array.add(0l);
		array.add(0l);
		minimaxAlphaBeta(this, 0, aiDepth + 1, array);
		array.set(1, array.get(1) + getBytes());

		if (aiDepth == 0) {
			Node result = neighbours.get(0);
			int minValue = result.getHeuristic();
			for (int i = 1; i < neighbours.size(); i++) {
				if (turn == Model.BLACK) {
					if (neighbours.get(i).getHeuristic() < minValue) {
						minValue = neighbours.get(i).getHeuristic();
						result = neighbours.get(i);
					}
				} else {
					if (neighbours.get(i).getHeuristic() > minValue) {
						minValue = neighbours.get(i).getHeuristic();
						result = neighbours.get(i);
					}
				}
				array.set(1, array.get(1) + neighbours.get(i).getBytes());
			}

			return result;
		} else

			for (Node node : neighbours) {
				array.set(1, array.get(1) + node.getBytes());
			}

		// <!-- Duyệt qua đống con lấy ra con tương ứng với bestheuristic -->
		for (Node node : neighbours) {
			if (node.getBestHeuristic() != null)
				if (node.getBestHeuristic().equals(bestHeuristic)) {
					return node;
				}
		}

		// <!-- ko bao giờ return null 100% :V --->
		return null;
	}

	public long getBytes() {
		return 256 + 4 + 4 + 4 + 8 + 1 + 16;
	}

	// <!-- Minimax -->
	public Integer minimaxAlphaBeta(Node root, int nowDepth, int limitDepth, ArrayList<Long> array) {

		// <!-- Đủ depth thì dừng -->
		if (nowDepth == limitDepth) {

			// đếm bytes
			return root.getHeuristic();
		} else {

			/*
			 * <!-- Mỗi lần thêm một con mới, rồi xét nó ,nếu có cắt tỉa xảy ra thì ngon ko
			 * thì cứ thêm một con cho tới khi nào ko còn trạng thái con nữa -->
			 */
			while (root.addNeightbours()) {

				// <!-- biến này hỗ trợ root -->
				int count = 0;

				// <!-- Hỗ trợ cắt tỉa -->
				boolean isAlphabeta = false;

				// <!-- Cứ xét 1 node con xong rồi xóa nó đi -->
				while (root.getNeighbours().size() > 0) {

					// <!-- Lấy node con ra-->
					Node node = root.getNeighbours().get(count);

					// đếm node
					array.set(0, array.get(0) + 1);

					// <!-- Tính điểm cho nó -->
					int newValue = minimaxAlphaBeta(node, nowDepth + 1, limitDepth, array);

					// <!-- node hiện tại chưa có điểm thì cứ gán cho nó -->
					if (root.getBestHeuristic() == null)
						root.setBestHeuristic(newValue);
					else {

						// <!-- Có rồi thì xét xem đang ở max hay min thì mới lấy-->
						if (root.getTurn() == Model.BLACK) {
							if (root.getBestHeuristic().compareTo(newValue) > 0)
								root.setBestHeuristic(newValue);

						} else {
							if (root.getBestHeuristic().compareTo(newValue) < 0)
								root.setBestHeuristic(newValue);

						}
					}

					// <!-- Lấy node cha gần nhất có giá trị để cắt tỉa -->
					Node parentNode = getParentNode(root);

					// <!-- Có node cha mới làm -->
					if (parentNode != null)

						// <!-- Nghịch nhau mới cắt tỉa đc -->
						if (parentNode.getTurn() == Model.BLACK) {
							if (root.getTurn() == Model.WHITE)
								if (root.getBestHeuristic().equals(parentNode.getBestHeuristic())
										|| root.getBestHeuristic().compareTo(parentNode.getBestHeuristic()) > 0) {
									isAlphabeta = true;
									break;
								}

						} else {
							if (root.getTurn() == Model.BLACK)
								if (root.getBestHeuristic().equals(parentNode.getBestHeuristic())
										|| root.getBestHeuristic().compareTo(parentNode.getBestHeuristic()) < 0) {
									isAlphabeta = true;
									break;
								}
						}

					// <!-- Cắt tỉa ko xảy ra thì cứ xóa trạng thái con, root thì ko xóa-->
					if (root.getName() == 333) {
						count++;
						if (count == root.getNeighbours().size())
							break;
					} else
						root.getNeighbours().remove(count);

				}

				// <!-- cắt tỉa thì xóa -->
				if (isAlphabeta)
					break;

			}

			// <!-- trả về bestheuristic -->
			if (root.getBestHeuristic() == null)
				return root.getTurn() == Model.BLACK ? 999999 : -999999;
			else
				return root.getBestHeuristic();
		}
	}

	// <!-- Trả về cha của node gần nó nhất có điểm -->
	public Node getParentNode(Node root) {
		int limit = 0;
		Node node = new Node(root, 100);
		while (node.getParent() != null) {
			limit++;
			if (node.getParent().getBestHeuristic() != null) {
				node = node.getParent();
				break;
			}
			node = node.getParent();

		}
		if (node.getBestHeuristic() != null) {
			if (limit == 0)
				return null;

			return node;
		} else
			return null;
	}

	// <!-- Di chuyển quân cờ đến vị trí chỉ định-->
	public void moveChessPiece(int i, int j, int iOld, int jOld, int value, int state, int[][] board,
			OriginalCondition orginalConditionBlack, OriginalCondition orginalConditionWhite) {

		switch (state) {

		case Model.NORMALMOVE:

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
		case Model.CASTLING_LEFT:

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
		case Model.CASTLING_RIGHT:

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
		}

		// <!-- Di chuyển xong thì gán vị trị cũ là 0 -->
		board[iOld][jOld] = 0;
	}

	// <!-- Hàm đánh giá thế cờ -->
	public int heuristic() {

		int scoreOfBlack = 0;
		int scoreOfWhite = 0;

		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state.length; j++)
				if (state[i][j] > 0)
					scoreOfWhite += getScoreOfChessPiece(i, j, state[i][j]);

				else if (state[i][j] < 0)
					scoreOfBlack += getScoreOfChessPiece(i, j, state[i][j]);

		return scoreOfWhite - scoreOfBlack;
	}

	// <!-- Hàm tính điểm của quân cờ -->
	public int getScoreOfChessPiece(int i, int j, int value) {

		int score = 0;

		switch (value) {
		case -1:
			score = Rook.getScoreEvaluteChessPosition(i, j, value);
			score += 500;
			break;
		case -2:
			score = Knight.getScoreEvaluteChessPosition(i, j, value);
			score += 320;
			break;
		case -3:
			score = Bishop.getScoreEvaluteChessPosition(i, j, value);
			score += 330;
			break;
		case -4:
			score = Queen.getScoreEvaluteChessPosition(i, j, value);
			score += 900;
			break;
		case -5:
			score = King.getScoreEvaluteChessPosition(i, j, value);
			score += 9000;
			break;
		case -6:
			score = Pawn.getScoreEvaluteChessPosition(i, j, value);
			score += 100;
			break;
		case 1:
			score = Rook.getScoreEvaluteChessPosition(i, j, value);
			score += 500;
			break;
		case 2:
			score = Knight.getScoreEvaluteChessPosition(i, j, value);
			score += 320;
			break;
		case 3:
			score = Bishop.getScoreEvaluteChessPosition(i, j, value);
			score += 330;
			break;
		case 4:
			score = Queen.getScoreEvaluteChessPosition(i, j, value);
			score += 900;
			break;
		case 5:
			score = King.getScoreEvaluteChessPosition(i, j, value);
			score += 9000;
			break;
		case 6:
			score = Pawn.getScoreEvaluteChessPosition(i, j, value);
			score += 100;
			break;
		}

		return score;
	}

	// <!-- Lấy mảng nước đi có thể đi của quân cờ truyền vào -->
	public int[][] getNextMovesBoardOfChessPiece(int i, int j, int[][] board, int value,
			OriginalCondition orginalConditionBlack, OriginalCondition orginalConditionWhite) {

		int[][] nextMovesBoard = new int[8][8];

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

		return nextMovesBoard;
	}

	// Getter và setter

	public int[][] getState() {
		return state;
	}

	public void setState(int[][] state) {
		this.state = state;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public List<Node> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<Node> neighbours) {
		this.neighbours = neighbours;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public Integer getBestHeuristic() {
		return bestHeuristic;
	}

	public void setBestHeuristic(Integer bestHeuristic) {
		this.bestHeuristic = bestHeuristic;
	}

	public int getAiDepth() {
		return aiDepth;
	}

	public void setAiDepth(int aiDepth) {
		this.aiDepth = aiDepth;
	}

	public OriginalCondition getOrginalConditionBlack() {
		return orginalConditionBlack;
	}

	public void setOrginalConditionBlack(OriginalCondition orginalConditionBlack) {
		this.orginalConditionBlack = orginalConditionBlack;
	}

	public OriginalCondition getOrginalConditionWhite() {
		return orginalConditionWhite;
	}

	public void setOrginalConditionWhite(OriginalCondition orginalConditionWhite) {
		this.orginalConditionWhite = orginalConditionWhite;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

}
