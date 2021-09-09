package controller;

import java.io.IOException;

import model.Chessman;
import model.Model;
import view.View;

public class Controller {

	// <!-- View dùng để nhận thao tác từ người dùng và response-->
	private View view;

	// <!-- Model dùng để sử lý các thao tác từ người dùng -->
	private Model model;

	// <!-- Contrustor nhận vào model tách biệt hoàn toàn với view -->
	public Controller(Model model) throws IOException {
		this.model = model;
		view = new View(this);
	}

	/*
	 * <!-- upload của controller chỉ đơn giản là cập nhập lại 2 mảng cho view vẽ
	 * lại, hiển thị thông tin cho người dùng biết-->
	 */
	public void upload() {
		view.upload(model.getBoard(), model.getNextMovesBoard());
	}

	// <!-- Vì được yêu cầu từ view nên giờ chỉ cần cho view các mảng của model-->
	public void start() {
		upload();
	}

	// <!-- Vì reset được yêu cầu từ view nên giờ chỉ cần reset model-->
	public void reset() {
		model.reset();
	}

	// <!-- Xử lý yêu cầu của người dùng cần mảng nước đi mới-->
	public void needNewNextMovesBoard(int i, int j, int value) {

		// <!-- gọi model xử lý chuyện này -->
		model.uploadNextMovesBoard(i, j, value);

		// <!-- sau đó cập nhập lại cho view -->
		upload();
	}

	// <!-- gọi tới model di chuyển quân cờ đến vị trí chỉ định>
	public void moveChessPiece(int i, int j, int value, int stateMove) {
		boolean move = model.moveChessPiece(i, j, value, stateMove);

		// <!-- move true là phép phong tốt-->
		if (move) {

			// <!-- Lấy quân cờ từ giao diện phong tốt -->
			int newChessPiece = view.getChessPromote();

			// <!-- Lấy được quân cờ rồi thì chuyển giao cho model phong tốt -->
			model.completePromote(i, j, newChessPiece);
		}
	}

	// <!-- Yêu cầu tới model xem nước đi này có hợp lệ không (trống vua )-->
	public boolean tryMoves(Chessman chessman, int i, int j) {
		return model.tryMoves(chessman, i, j);
	}

	// <!-- Nhận yêu cầu từ view, gửi tới model xử lý- ENd game>
	public boolean isEndGame(int turn) {
		return model.isEndGame(turn);
	}

	// <!-- Nhận yêu cầu từ view, gửi tới model xử lý- TIE game>
	public boolean isTieGame(int turn) {
		return model.isTieGame(turn);
	}

	// <!-- Kiểm tra xem vua có bị địch chiếu hay không-->
	public boolean enemyHasPointToKing(int turn) {
		return model.enemyHasPointToKing(turn);
	}

	// <!-- Yêu cầu tới model thực hiện nước đi của máy -->
	public void itIsAITurn(int turn,int aiDepth) {
		model.itIsAITurn(turn,aiDepth);
	}

}
