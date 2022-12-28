package view;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;

import controller.Controller;
import model.Chessman;

public class View extends JFrame {

	/*
	 * <!--Version cho nó khỏi cảnh báo bóng đèn màu vàng chứ không có tác dụng gì
	 * cả -->
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * <!-- Controller dùng để lấy thông tin từ người dùng thông qua view và xử lý
	 * chúng thông qua model sau đó trả về view hiển thị kết quả cho người dùng -->
	 */
	private Controller controller;

	/*
	 * <!-- Đây là phần phía bên trái giao diện, nơi hiển thị bàn cờ và tiếp nhận
	 * thao tác chơi cờ cửa người dùng (nếu chọn chế độ người với người hoặc người
	 * với máy)-->
	 */
	private PanelLeft panelLeft;

	/*
	 * <!-- Đây là phần phía bên phải giao diện, nơi hiển thị các lựa chọn như chế
	 * độ chơi, trí thông minh của AI, UNDO, REDO,..v...v.. -->
	 */
	private PanelRight panelRight;

	/*
	 * <!-- Contrustor của view thì chỉ nhận vô controller, nó không liên quan và sẽ
	 * không bao giờ được kết nối trực tiếp tới model -->
	 */

	/*
	 * <!-- Giao diện phong tốt, nó sẽ được hiển thị cho người dùng chọn con cờ mà
	 * nó muốn biến tốt họ thành nó khi tốt của họ đi đến cuối bàn cờ -->
	 */
	private JDialog promote;

	// <!-- Giá trị được trả về khi phong tốt -->
	private int valuePromote;

	public View(Controller controller) throws IOException {
		this.controller = controller;

		createJDialogPromote();
		createPanel();
		setDefault();
	}

	// <!-- Tạo giao diện phong hậu -->
	private void createJDialogPromote() {
		promote = new GUIPromote(this);
	}

	// <!-- Lấy quân cờ từ giao diện phong tốt -->
	public int getChessPromote() {
		promote.setVisible(true);
		return valuePromote;
	}

	// <!-- Khởi tạo các tham số và thuộc tính cho giao diện -->
	private void setDefault() {
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Chess game");
		setResizable(false);
		setVisible(true);
	}

	/*
	 * <!-- Tạo giao diện chính bằng cách thêm chia nhỏ giao diện ra thành phần bên
	 * phải và bên trái để dễ hình dung và tiện lời -->
	 */
	private void createPanel() throws IOException {
		setLayout(null);
		add(panelLeft = new PanelLeft(this));
		add(panelRight = new PanelRight(this));

		panelLeft.addMouseListener(panelLeft);
		panelLeft.addMouseMotionListener(panelLeft);
	}

	/*
	 * <!-- Hàm này sẽ được gọi khi hàm upload() của controller được gọi, mục đích
	 * của nó là cập nhập lại 2 mảng (bàn cở , những nước đi có thể đi) để biết xem
	 * chúng có thay đổi gì không rồi vẽ lại chúng nếu có thay đổi -->
	 */
	public void upload(int[][] board, int[][] nextMovesBoard) {
		panelLeft.upload(board, nextMovesBoard);
	}

	/*
	 * <!-- Hàm này được gọi khi người dùng nhấn nút submit, nó sẽ thiết lập lại chế
	 * độ chơi của panelLeft, VD:Computer vs Computer thì không cho người dùng tác
	 * động lên bàn cờ, ngược lại các chế độ khác thì cho tác động, và nó sẽ xin
	 * hành động của controller tương ứng, VD: người với máy, cứ người chơi đi nước
	 * xong thì nó sẽ thông báo cho controller xin nước đi tiếp theo, controller thì
	 * sẽ gởi dữ liệu tới model để xử lý và trả về kết quả-->
	 */
	public void start(int state, int selectIndex, int aiDepth) {

		// <!-- yêu cầu tới controller phải start>
		controller.start();

		// <!-- khởi động panel left vẽ bàn cờ -->
		panelLeft.start(state, selectIndex, aiDepth);
		panelLeft.repaint();
	}

	/*
	 * <!-- Làm cho trạng thái bàn cờ cửa left không tác động được nữa, phải đợi
	 * panel right nhấn button submit gọi tới start() để trở lại chế độ chơi-->
	 */
	public void reset() {

		// <!-- yêu cầu tới controller phải reset -->
		controller.reset();

		// <!-- reset panel left - nơi vẽ bàn cờ -->
		panelLeft.reset();
		panelLeft.repaint();
	}

	// <!-- Được gọi tới khi end game từ panel left -->
	public void resetWhenEndGame() {
		panelRight.reset();
	}

	// <!-- Nhận được yêu cầu từ người dùng, chuyển nó cho controller xử lý-->
	public void needNewNextMovesBoard(int i, int j, int value) {
		controller.needNewNextMovesBoard(i, j, value);
	}

	// <!-- Trả về màu cờ người chơi chọn trong chế độ người với máy-->
	public int getColorPlayer() {
		return panelRight.getColorPlayer();
	}

	// <!-- Di chuyển quân cừo đến vị trí chỉ định-->
	public void moveChessPiece(int i, int j, int value, int stateMove) {
		controller.moveChessPiece(i, j, value, stateMove);
	}

	// <!-- Yêu cầu tới controller xem nước đi này có hợp lệ không (trống vua )-->
	public boolean tryMoves(Chessman chessman, int i, int j) {
		return controller.tryMoves(chessman, i, j);
	}

	// <!-- Nhận yêu cầu từ con, gởi yêu cầu tới controller check việc end game-->
	public boolean isEndGame(int turn) {
		return controller.isEndGame(turn);
	}

	// <!-- Nhận yêu cầu từ con, gởi yêu cầu tới controller check việc tie game-->
	public boolean isTieGame(int turn) {
		return controller.isTieGame(turn);
	}

	// <!-- Kiểm tra xem vua có bị địch chiếu hay không-->
	public boolean enemyHasPointToKing(int turn) {
		return controller.enemyHasPointToKing(turn);
	}

	// <!-- set lại giá trị phong tốt từ GUIPromote-->
	public void setValuePromote(int valuePromote) {
		this.valuePromote = valuePromote;
	}

	// <!-- Yêu cầu tới controller thực hiện nước đi của máy -->
	public void itIsAITurn(int turn, int aiDepth) {
		controller.itIsAITurn(turn, aiDepth);
	}

}
