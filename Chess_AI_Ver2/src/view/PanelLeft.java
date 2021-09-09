package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Chessman;
import model.Model;

public class PanelLeft extends JPanel implements MouseListener, MouseMotionListener, Runnable {

	/*
	 * <!--Version cho nó khỏi cảnh báo bóng đèn màu vàng chứ không có tác dụng gì
	 * cả -->
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * <!-- Vị trí bắt đầu vẽ bàn cờ (Gốc trên bên trái X--là theo chiều ngang, Gốc
	 * trên bên trái y--là theo chiều dọc)
	 * 
	 * size là kích cỡ chiều dọc = chiều ngang của mỗi ô cờ (Hình vuông) -->
	 */
	private int x, y, size;

	/*
	 * <!-- Màu sắc được khởi tạo ngay từ ban đầu để tránh việc cứ mỗi lần repaint()
	 * thì phải tạo lại màu làm cho trương trình trở nên chậm hơn
	 * 
	 * -->gameFrameWork: màu viền của bàn cờ
	 * 
	 * -->darkCell: màu của ô cờ có màu đà đậm
	 * 
	 * -->lightCell: màu cửa ô cờ có màu đà nhạt
	 * 
	 * -->
	 */

	// <!-- Font này dùng để thông báo game chưa diễn ra-->
	private Font fontGameNotFound;

	// <!-- Các font màu khai báo 1 lần dùng mãi mãi-->
	private Color gameFrameWork, darkCell, lightCell, lightGray, darkGray, warning;

	/*
	 * <!-- Những thuột tính này với mục đích là khi kéo quân cờ, nếu quân cờ đó
	 * đang bay trên đầu ô nào thì ô đó vẽ viền màu vàng-->
	 * 
	 * <!-- xMove, yMove theo thứ tự trái sang phải là dòng ,cột trong mảng 2 chiều,
	 * để biết ta đang di chuyển 1 con khác nằm trên đầu con nào, mặc định là -1 -1
	 * , khi đó ta ko thể vẽ và ngược lại-->
	 */
	private boolean isDrawMove;
	private int xMove, yMove;

	// <!-- Đối tượng này dùng để xử lí việt kéo thả chuột quân cờ của người dùng->
	private Chessman chessman;
	/*
	 * <!-- Khởi tạo hình ảnh, mainImage là hình ảnh chính ta sẽ vẽ tất cả mọi thứ
	 * lên mainImg, rồi sau đó chỉ cần vẽ mainImg lên Panel (Điều này làm cho giao
	 * diện trở lên smooth không bị giậc lag)
	 * 
	 * backGround: là hình nền (ở đây là hình ảnh 1 miếng gỗ (img/background.png))
	 * 
	 * Các quân cờ thì sẽ được viết tắt bời 2 kí tự, kí tự đầu là màu viết tắt VD: w
	 * = white, b = black, còn kí tự thứ 2 là tên quân cờ viết tắt VD Q = queen
	 * 
	 * B = Bishop (Tượng)
	 * 
	 * K = King (Vua)
	 * 
	 * N = Knight (Ngựa) vì K trùng với King nên lấy từ N
	 * 
	 * P = Pawn (Tốt)
	 * 
	 * Q = Queen (Hậu)
	 * 
	 * R = Rock (Xe)
	 * 
	 * -->
	 */
	private BufferedImage mainImg, backGround, bB, bK, bN, bP, bQ, bR, wB, wN, wK, wQ, wR, wP;

	// <!-- Cài này là để 1 biến dùng để chỉnh cho độ rộng nét vẽ của graphics2d -->
	private BasicStroke graphicsLine, borderLine;

	// <!-- Tham chiếu đến View để thông qua view có thể liên lạc với controller -->
	private View view;

	// <!--Mảng vị trí của những quân cờ trên bản cờ-->
	private int[][] board;

	/*
	 * <!-- Mảng chứa những nước đi có thể đi của quần cờ đang được chọn , điều này
	 * được thực hiện khi có quân cờ được chọn từ người dùng từ view, view sẽ thông
	 * báo cho controller, controller chuyển giao cho model xử lý và sau đó sẽ cập
	 * nhập lại cho view để hiển thị cho người dùng -->
	 */
	private int[][] nextMovesBoard;

	/*
	 * <!-- Ánh xạ vị trị i j trong mảng board, sau đó yêu cầu controller lấy thông
	 * qua model lấy về mảng các nước đi có thể đi, mặc địch ban đầu là -1-->
	 */
	private int iNextMoves, jNextMoves;

	// <!-- Giúp tô ô vua màu đỏ khi bị chiếu -->
	private int xMat;
	private int yMat;

	/*
	 * <!-- Biến này có tác dụng xem thử trạng thái hiện tại là đang chơi hay đang ở
	 * chế đệ dừng để mà tùy theo từng trạng thái cõ những cách ứng xử khác nhau-->
	 */
	private boolean isStartGame;

	// <!-- Chế độ chơi nhận được từ panel right thông qua view-->
	private int state;

	// <!-- Lượt đánh, mặc định bạn đầu là 0 = WHITE(Cờ trắng luôn luôn đi trước)-->
	private int turn;

	/*
	 * <!-- Bởi vì 2 máy đánh với nhau là luân phiên nên bỏ vô while true thì không
	 * thể cập nhập được nên ta dùng thread-->
	 */
	private Thread thread;

	// <!-- Độ sâu của AI khi duyệt minimax -->
	private int aiDepth;

	// <!-- Khởi tạo chỉ nhận vào view -->
	public PanelLeft(View view) throws IOException {
		this.view = view;
		createMat();
		createParameters();
		createColor();
		createFont();
		createDefault();
		createGraphicsLine();
		createImg();
		createIJNextMoves();
		createXYMove();
		createChessman();
		createThread();
	}

	// <!-- Tạo thread hỗ trợ việc máy với máy chơi với nhau -->
	private void createThread() {
		thread = new Thread(this);
	}

	// <!-- Khởi tạo ban đầu ô bị quân cờ khác chiếu là -1 -1 -->
	private void createMat() {
		xMat = -1;
		yMat = -1;
	}

	// <!-- Khởi tạo ban đầu ô đang bị quân cờ khác nằm trên đầu là -1 -1 -->
	private void createXYMove() {
		xMove = -1;
		yMove = -1;
	}

	// <!-- Tạo đối tượng để xử lý việc kéo thả quân cờ-->
	public void createChessman() throws IOException {
		chessman = new Chessman(size);
	}

	// <!-- Khởi tạo I J next moves -->
	private void createIJNextMoves() {
		iNextMoves = -1;
		jNextMoves = -1;
	}

	// <!-- Khởi tạo font -->
	private void createFont() {
		fontGameNotFound = new Font(Font.SANS_SERIF, Font.BOLD, 25);
	}

	// <!-- Khởi tạo các tham số cần thiết cho việc vẽ bàn cờ-->
	private void createParameters() {
		x = 67;
		y = 50;
		size = 70;
	}

	// <!-- Khởi tạo biến set độ rộng của nét vẽ graphics2D-->
	private void createGraphicsLine() {
		graphicsLine = new BasicStroke(20f);
		borderLine = new BasicStroke(4f);
	}

	// <!-- Khởi tạo các hình ảnh cần thiết, tạo 1 lần và sử dụng mãi mãi-->
	private void createImg() throws IOException {

		// <!-- Khởi tạo và vẽ background bàn cờ -->
		mainImg = new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
		backGround = ImageIO.read(new File("img/backGround.jpg"));
		drawFixedChessBoardFrame();

		// <!-- Khởi tạo các hình ảnh quân cờ -->
		bR = ImageIO.read(new File("img/bR.png"));
		bN = ImageIO.read(new File("img/bN.png"));
		bB = ImageIO.read(new File("img/bB.png"));
		bQ = ImageIO.read(new File("img/bQ.png"));
		bK = ImageIO.read(new File("img/bK.png"));
		bP = ImageIO.read(new File("img/bP.png"));
		wR = ImageIO.read(new File("img/wR.png"));
		wN = ImageIO.read(new File("img/wN.png"));
		wB = ImageIO.read(new File("img/wB.png"));
		wQ = ImageIO.read(new File("img/wQ.png"));
		wK = ImageIO.read(new File("img/wK.png"));
		wP = ImageIO.read(new File("img/wP.png"));
	}

	/*
	 * <!-- Khi ta cập nhập lại trạng thái trò trời thì ta phải vẽ lại bàn cờ, việc
	 * này giúp cải tiến điều đó, nó chỉ vẽ lại những thứ nào càn thiết thôi, vì thế
	 * ta vẽ sẵn những thứ cần thiết nhất, sau này chỉ cần vẽ những thứ bị thay
	 * đổi-->
	 */
	private void drawFixedChessBoardFrame() {
		Graphics2D g2 = (Graphics2D) mainImg.getGraphics();
		g2.drawImage(backGround, 0, 0, 700, 700, null);
		g2.setStroke(graphicsLine);
		g2.setColor(gameFrameWork);
		g2.drawRect(60, 44, 573, 573);
	}

	// <!-- Khởi tạo các tham số cần thiết cho panel -->
	private void createDefault() {
		setBounds(0, 0, 700, 700);
	}

	// <!-- Tạo những màu cần thiết cho panel, tọa 1 lần, sử dụng mãi mãi-->
	private void createColor() {
		gameFrameWork = new Color(56, 36, 32);
		darkCell = new Color(181, 136, 99);
		lightCell = new Color(240, 217, 181);
		darkGray = new Color(105, 105, 105);
		lightGray = new Color(169, 169, 169);
		warning = new Color(255, 50, 50);
	}

	// <!-- Vẽ bàn cờ -->
	@Override
	public void paint(Graphics g) {

		// <!-- Lấy bút của mainImg ra để vẽ, vì lấy bút nó nên vẽ gì thì sẽ vẽ trên nó>
		drawFixedChessBoardFrame();
		Graphics2D g2 = (Graphics2D) mainImg.getGraphics();

		if (isStartGame) {

			// <!-- Trạng thái game đang được diễn ra-->
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (i % 2 == 0)
						if (j % 2 == 0)
							g2.setColor(lightCell);
						else
							g2.setColor(darkCell);

					else {
						if (j % 2 == 0)
							g2.setColor(darkCell);
						else
							g2.setColor(lightCell);
					}

					// <!-- Có màu rồi vẽ ô cái rồi tính tiếp -->
					g2.fillRect(x, y, size, size);

					// <!-- Kiểm tra xem nếu người dùng đang thao tác thì vẽ quân cờ thao tác-->
					if (chessman.getState() == Chessman.ACTIVE)
						// <!-- Nếu đang thao tác thì vẽ mảng nước có thể đi bằng chessman -->
						chessman.drawRectangleNextsMove(g2, i, j, x, y, lightCell, lightGray, darkGray);
					else {
						// <!-- Nếu không thì ta sẽ vẽ bằng panel này-->
						// <!-- Nếu ô này là ô có trong mảng next moves thì set lại màu -->
						if (nextMovesBoard[i][j] != 0) {
							if (g2.getColor() == lightCell)
								g2.setColor(lightGray);
							else
								g2.setColor(darkGray);
							// <!-- Nếu nhưu ô này có nước có thể đi thì vẽ lại ô này-->
							g2.fillRect(x, y, size, size);
						}
					}

					/*
					 * <!-- isDrawmove trở thành true khi nào? Khi mouseDrag được gọi và chessman
					 * phải là true->mà chessman true khi nào, khi mousePress nhấn đúng vào 1 quân
					 * cờ theo đúng lượt chơi, isDrawMove thành false khi mouseRelease được gọi-->
					 */
					if (isDrawMove)
						/*
						 * <!-- xMove yMove phải khác -1 hết và ô hiện tại đang xét đúng là ô xMove
						 * yMove mới vẽ -->
						 */
						if (xMove != -1 && yMove != -1 && xMove == i && yMove == j) {
							// <!--Cho màu vàng và set nét vẽ bự lên tí-->
							g2.setColor(Color.YELLOW);
							g2.setStroke(borderLine);
							g2.drawRect(x + 2, y + 2, size - 4, size - 4);
						}

					// <!-- Nếu đang bị chiếu đối lại màu đỏ -->
					if (xMat == i && yMat == j) {
						g2.setColor(warning);
						g2.fillRect(x, y, size, size);
					}

					// <!-- Sau tất cả vẽ quân cờ lên-->
					// <!-- Vẽ các quân cờ -->
					drawChessPieces(g2, board[i][j], x, y);

					x += size;
				}
				y += size;
				x = 67;
			}

			// <!-- vẽ chessman ( hỗ trợ kéo thả của người dùng )-->
			// <!-- kiểm tra nếu state là đang hoạt động thì mới vẽ>
			if (chessman.getState() == Chessman.ACTIVE)
				chessman.draw(g2);

		} else {

			// <!-- Trạng thái game chưa được diễn ra>

			// <!-- Game chưa diễn ra nên vẽ full ô toàn là màu xám-->
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (i % 2 == 0)
						if (j % 2 == 0)
							g2.setColor(Color.LIGHT_GRAY);
						else
							g2.setColor(Color.GRAY);

					else {
						if (j % 2 == 0)
							g2.setColor(Color.GRAY);
						else
							g2.setColor(Color.LIGHT_GRAY);
					}
					g2.fillRect(x, y, size, size);
					x += size;
				}
				y += size;
				x = 67;
			}

			// <!-- Vẽ thêm String thông báo là game chưa diễn ra-->
			g2.setColor(Color.WHITE);
			g2.setFont(fontGameNotFound);
			g2.drawString("Bấm \"Bắt đầu\" để chơi game", 177, 337);
		}

		// <!-- Sau khi vẽ mọi thứ lên mainImg, h là lúc vẽ mainImg lên panel-->
		g.drawImage(mainImg, 0, 0, 700, 700, null);

		// <!-- Trả tham số vẽ lại = ban đầu để hỗ trợ tiện lợi việt repaint-->
		x = 67;
		y = 50;
	}

	// <!-- reset bằng cách trả isStartGame thành false-->
	@SuppressWarnings("deprecation")
	public void reset() {

		// <!-- reset thì dừng thread lại -->
		thread.stop();

		isStartGame = false;
		xMat = -1;
		yMat = -1;
	}

	// <!-- start bằng cách chuyển isStartGame thành true, nhận vào chế độ chơ --i>
	public void start(int state, int selectIndex, int aiDepth) {
		isStartGame = true;
		this.state = state;
		this.aiDepth = aiDepth;
		turn = Model.WHITE;

		/*
		 * <!-- Start mà trạng thái ban đầu là người với máy mà người lại cờ đen thì máy
		 * đi trước-->
		 */
		if (state == Model.HUMAN_VS_COMPUTER && selectIndex == 1) {
			view.itIsAITurn(turn, aiDepth);
			if (turn == Model.WHITE)
				turn = Model.BLACK;
			else
				turn = Model.WHITE;
		}

		// <!-- Máy với mày thì cho thread start-->
		if (state == Model.COMPUPER_VS_COMPUTER) {

			// <!-- Tạo mới thread xong start -->
			createThread();
			thread.start();
		}

	}

	// <!-- upload 2 mảng là đường đi có thể đi và bàn cờ vị trí các quân cờ -->
	public void upload(int[][] board, int[][] nextMovesBoard) {
		this.board = board;
		this.nextMovesBoard = nextMovesBoard;
		repaint();
	}

	/*
	 * <!-- Hàm nhận vào các tham số. g2: bút vẽ của mainImg, value: giá trị để biết
	 * xem quân cờ đó là quân cờ gì, x và y là tọa độ để vẽ quân cờ đó lên bàn cờ
	 * -->
	 */
	private void drawChessPieces(Graphics2D g2, int value, int x, int y) {
		switch (value) {
		case 1:
			g2.drawImage(wR, x, y, size, size, null);
			break;
		case 2:
			g2.drawImage(wN, x, y, size, size, null);
			break;
		case 3:
			g2.drawImage(wB, x, y, size, size, null);
			break;
		case 4:
			g2.drawImage(wQ, x, y, size, size, null);
			break;
		case 5:
			g2.drawImage(wK, x, y, size, size, null);
			break;
		case 6:
			g2.drawImage(wP, x, y, size, size, null);
			break;
		case -1:
			g2.drawImage(bR, x, y, size, size, null);
			break;
		case -2:
			g2.drawImage(bN, x, y, size, size, null);
			break;
		case -3:
			g2.drawImage(bB, x, y, size, size, null);
			break;
		case -4:
			g2.drawImage(bQ, x, y, size, size, null);
			break;
		case -5:
			g2.drawImage(bK, x, y, size, size, null);
			break;
		case -6:
			g2.drawImage(bP, x, y, size, size, null);
			break;
		}
	}

	// <!-- các action liên quan đến chuột -->
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/*
	 * x = 67;
	 * 
	 * y = 50;
	 * 
	 * size = 70;
	 * 
	 * ===> Ngang: 67 ---> 627
	 * 
	 * ===> Dọc: 50 ---> 610
	 */
	@Override
	public void mousePressed(MouseEvent e) {

		// <!-- Hiện tại là trạng thái game chạy ? Bước tiếp theo : STOP>
		if (isStartGame)

			// <!-- Phải là ở chế độ người vs người, người với máy mới có chức năng này-->
			if (state == Model.HUMAN_VS_COMPUTER || state == Model.HUMAN_VS_HUMAN) {

				// <!-- lấy vị trí chuột hiện hành>
				int xMouse = e.getX();
				int yMouse = e.getY();

				// <!-- Kiểm tra xem có out phạm vi hay ko,nếu out thì thôi, ko thì tiếp tục -->
				if (xMouse <= 626 && xMouse >= 68 && yMouse >= 51 && yMouse <= 609) {

					// <!-- lấy ra iNew và jNew -->
					int iNew = (yMouse - 50) / size;
					int jNew = (xMouse - 67) / size;

					/*
					 * <!-- Ở đây xảy ra 2 trường hợp, 1 là chế độ người và người ta phải luân phiên
					 * check cả 2 phía, trường hợp còn lại là người với máy chỉ check 1 phía là
					 * người chơi-->
					 */
					switch (state) {

					// <!-- Trường hợp chế độ người với người -->
					case Model.HUMAN_VS_HUMAN:

						/*
						 * <!-- Ta kiểm tra nếu như lượt đi đang là cho quân trắng thì rê vô quân trắng
						 * mới được gởi đi yêu cầu và tương tự với trường hợp lượt đi đang là cho quân
						 * đen -->
						 */
						if (turn == Model.WHITE) {
							if (board[iNew][jNew] > 0) {
								chessman.on(xMouse - 35, yMouse - 35, board[iNew][jNew], nextMovesBoard, iNew, jNew);
								board[iNew][jNew] = 0;
								repaint();
							}
						} else {
							if (board[iNew][jNew] < 0) {
								chessman.on(xMouse - 35, yMouse - 35, board[iNew][jNew], nextMovesBoard, iNew, jNew);
								board[iNew][jNew] = 0;
								repaint();
							}
						}
						break;

					// <!-- Trường hợp chế độ người với máy -->
					case Model.HUMAN_VS_COMPUTER:

						/*
						 * <!-- Ở trường hợp này ta lại gặp 2 trường hợp con nữa là màu của người chơi
						 * chọn, vì ta cho người dùng chọn màu cờ tùy thích trong chế độ người với
						 * máy-->
						 */
						switch (view.getColorPlayer()) {

						/*
						 * <!-- Trường hợp người dùng chọn quân màu trắng thì khi rê vô quân màu trắng
						 * mới được gởi đi yêu cầu và lượt đi hiện tại phải là lượt đi của quân trắng
						 * -->
						 */
						case Model.WHITE:
							if (turn == Model.WHITE)
								if (board[iNew][jNew] > 0) {
									chessman.on(xMouse - 35, yMouse - 35, board[iNew][jNew], nextMovesBoard, iNew,
											jNew);
									board[iNew][jNew] = 0;
									repaint();
								}
							break;

						/*
						 * <!-- Trường hợp người dùng chọn quân màu đen thì khi rê vô quân màu đen mới
						 * được gởi đi yêu cầu và lượt đi hiện tại phải là lượt cửa quân đen -->
						 */
						case Model.BLACK:
							if (turn == Model.BLACK)
								if (board[iNew][jNew] < 0) {
									chessman.on(xMouse - 35, yMouse - 35, board[iNew][jNew], nextMovesBoard, iNew,
											jNew);
									board[iNew][jNew] = 0;
									repaint();
								}
						}
						break;
					}
				}
			}
	}

	/*
	 * <!-- Sự kiện nhả chuột chỉ có tác dụng khi chessman đang ở trạng thái active
	 * (chessmain ở trạng thái active được thì trc đó mousePress đã trải qua kiểm
	 * tra rất nheieuf mới cho nó thành active)-->
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

		// <!-- Kiểm tra trạng thái chessman, active thì làm tiếp-->
		if (chessman.getState() == Chessman.ACTIVE) {

			// <!-- Hỗ trợ việc di chuyển thành không chưa mà cập nhập bảng nước đi-->
			boolean isCompleteMove = false;

			// <!-- Lấy vị trí chuột ra nè -->
			int xMouse = e.getX();
			int yMouse = e.getY();

			// <!-- Kiểm tra xem có out phạm vi hay ko,nếu out thì thôi, ko thì tiếp tục -->
			if (xMouse <= 626 && xMouse >= 68 && yMouse >= 51 && yMouse <= 609) {

				// <!-- lấy ra iNew và jNew( tạo độ chuột nhả ra trong mảng 8x8) -->
				int iNew = (yMouse - 50) / size;
				int jNew = (xMouse - 67) / size;

				// <!-- ==1 là có nước đi và ko phải điểm xuất phát-->
				if (nextMovesBoard[iNew][jNew] == 1) {

					/*
					 * <!-- Ta phải kiểm tra di chuyển quân này xong liệu vua của ta có nằm trong
					 * tầm ngắm của địch hay không, nếu đúng thì ko cho đi-->
					 */

					if (!view.tryMoves(chessman, iNew, jNew)) {

						// <!-- Cho nó đi tới và đổi turn-->
						// <!-- Vì ở đây có 2 nước đi đặc biệt là phong tốt và nhập thành-->

						// <!-- Xử lý khi là vua -->
						if (chessman.getValue() == 5 || chessman.getValue() == -5)

							// <!-- Nếu như có xê dịch hơn 2 1 chứng tỏ là nhập thành -->
							if (Math.abs(jNew - chessman.getyOld()) > 1)

								// <!-- jNew > vị trí j cũ ==> right -->
								if (jNew > chessman.getyOld())
									view.moveChessPiece(iNew, jNew, chessman.getValue(), Model.CASTLING_RIGHT);

								// <!-- Ngược lại left -->
								else
									view.moveChessPiece(iNew, jNew, chessman.getValue(), Model.CASTLING_LEFT);

							// <!-- Nếu như không phải nhập thành thì di chuyển bình thường-->
							else
								view.moveChessPiece(iNew, jNew, chessman.getValue(), Model.NORMALMOVE);

						else if (chessman.getValue() == 6 || chessman.getValue() == -6) {

							// <!-- Xử lý khi là tốt-->
							// <!-- Đi tới cuối bàn cờ thì làm sao đó -->
							if (iNew == 0 || iNew == 7)
								if (turn == Model.WHITE)
									view.moveChessPiece(iNew, jNew, chessman.getValue(), Model.PROMOTE);
								else
									view.moveChessPiece(iNew, jNew, chessman.getValue(), Model.PROMOTE);
							else
								view.moveChessPiece(iNew, jNew, chessman.getValue(), Model.NORMALMOVE);

						} else

							// <!-- Khi không phải trường hợp đặc biệt thì di chuyển bình thường-->
							view.moveChessPiece(iNew, jNew, chessman.getValue(), Model.NORMALMOVE);

						// <!-- Đổi turn -->
						if (turn == Model.WHITE)
							turn = Model.BLACK;
						else
							turn = Model.WHITE;

						// <!-- hehe đã di chuyển thành công nước đi-->
						isCompleteMove = true;

					} else {
						// <!--Quân cờ đi bị vi phạm là di chuyển xong vua bị chiếu -->
						view.moveChessPiece(chessman.getxOld(), chessman.getyOld(), chessman.getValue(),
								Model.RETURN_BECAUSE_ERROR);
					}

				} else
					// <!-- Khi thả ra ở chỗ không phải nước có thể đi sẽ trả nó về vị trí cũ-->
					view.moveChessPiece(chessman.getxOld(), chessman.getyOld(), chessman.getValue(),
							Model.RETURN_BECAUSE_ERROR);

			} else
				// <!-- Khi thả ra ở chỗ tầm bậy ngoài bàn cờ sẽ trả nó về vị trí cũ-->
				view.moveChessPiece(chessman.getxOld(), chessman.getyOld(), chessman.getValue(),
						Model.RETURN_BECAUSE_ERROR);

			// <!-- Vì sẽ off chessman nên ko cho vẽ move nữa -->
			isDrawMove = false;
			xMove = -1;
			yMove = -1;

			// <!-- Chuyển trạng thái của chessman thành INACTIVE-->
			chessman.off();

			/*
			 * <!--Di chuyển xong thì ta yêu cầu cập nhập lại bảng nước đi và kiểm tra trạng
			 * thái thắng thua -->
			 */
			if (isCompleteMove) {

				// <!-- Cập nhập đường đi -->
				view.needNewNextMovesBoard(0, 0, 0);

				// <!-- Cập nhập tướng bị chiếu -->
				if (view.enemyHasPointToKing(turn)) {
					for (int i = 0; i < board.length; i++)
						for (int j = 0; j < board.length; j++)
							if (turn == Model.BLACK) {
								if (board[i][j] == -5) {
									xMat = i;
									yMat = j;
								}
							} else {
								if (board[i][j] == 5) {
									xMat = i;
									yMat = j;
								}
							}
				} else {
					xMat = -1;
					yMat = -1;
				}

				// <!-- Kiểm tra hết game và hòa game -->
				// <!-- Vì có nguy cơ hết game nên cứ repaint cái cho chắc :V -->
				repaint();

				// <!-- Gọi tới controller kiểm tra end game -->
				if (view.isEndGame(turn)) {

					// <!-- Nếu end thì hiện ra thông báo -->
					String title = "";
					if (turn == Model.BLACK)
						title = "Quân trắng";
					else
						title = "Quân đen";

					// <!-- Hiện ra thông báo người thắng -->
					int a = JOptionPane.showConfirmDialog(null, title + " thắng !", "Kết thúc game",
							JOptionPane.DEFAULT_OPTION);

					// <!-- reset() -->
					if (a == JOptionPane.OK_OPTION || a == JOptionPane.CLOSED_OPTION)
						view.resetWhenEndGame();

				}

				// <!-- Gọi tới controller kiểm tra cờ hòa -->
				if (view.isTieGame(turn)) {

					// <!-- Nếu end thì hiện ra thông báo -->
					// <!-- Hiện ra thông báo cờ hòa -->
					int a = JOptionPane.showConfirmDialog(null, "Cờ hòa !", "Kết thúc game",
							JOptionPane.DEFAULT_OPTION);

					// <!-- reset() -->
					if (a == JOptionPane.OK_OPTION || a == JOptionPane.CLOSED_OPTION)
						view.resetWhenEndGame();

				}

				// <!-- Nếu ở chế độ người với máy thì sau khi người đi xong thì tới máy đi-->
				if (state == Model.HUMAN_VS_COMPUTER) {

					// Máy di chuyển
					view.itIsAITurn(turn, aiDepth);
					if (turn == Model.WHITE)
						turn = Model.BLACK;
					else
						turn = Model.WHITE;

					/*
					 * Xong rồi kiểm tra các thế xem có bị hòa hay chiếu hết hay gì không như người
					 * chơi luôn
					 */

					// <!-- Cập nhập đường đi -->
					view.needNewNextMovesBoard(0, 0, 0);

					// <!-- Cập nhập tướng bị chiếu -->
					if (view.enemyHasPointToKing(turn)) {
						for (int i = 0; i < board.length; i++)
							for (int j = 0; j < board.length; j++)
								if (turn == Model.BLACK) {
									if (board[i][j] == -5) {
										xMat = i;
										yMat = j;
									}
								} else {
									if (board[i][j] == 5) {
										xMat = i;
										yMat = j;
									}
								}
					} else {
						xMat = -1;
						yMat = -1;
					}

					// <!-- Kiểm tra hết game và hòa game -->
					// <!-- Vì có nguy cơ hết game nên cứ repaint cái cho chắc :V -->
					repaint();

					// <!-- Gọi tới controller kiểm tra end game -->
					if (view.isEndGame(turn)) {

						// <!-- Nếu end thì hiện ra thông báo -->
						String title = "";
						if (turn == Model.BLACK)
							title = "Quân trắng";
						else
							title = "Quân đen";

						// <!-- Hiện ra thông báo người thắng -->
						int a = JOptionPane.showConfirmDialog(null, title + " thắng !", "Kết thúc game",
								JOptionPane.DEFAULT_OPTION);

						// <!-- reset() -->
						if (a == JOptionPane.OK_OPTION || a == JOptionPane.CLOSED_OPTION)
							view.resetWhenEndGame();

					}

					// <!-- Gọi tới controller kiểm tra cờ hòa -->
					if (view.isTieGame(turn)) {

						// <!-- Nếu end thì hiện ra thông báo -->
						// <!-- Hiện ra thông báo cờ hòa -->
						int a = JOptionPane.showConfirmDialog(null, "Cờ hòa !", "Kết thúc game",
								JOptionPane.DEFAULT_OPTION);

						// <!-- reset() -->
						if (a == JOptionPane.OK_OPTION || a == JOptionPane.CLOSED_OPTION)
							view.resetWhenEndGame();

					}
				}

			}

			// <!-- vẽ lại-->
			repaint();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// <!--Sau khi press mà move thì dragg sẽ được gọi -->
	@Override
	public void mouseDragged(MouseEvent e) {

		/*
		 * <!-- Kiểm tra xem thử chessman được kích hoạt ở mouse press chưa , kích hoạt
		 * rồi thì tiếp tục-->
		 */
		if (chessman.getState() == Chessman.ACTIVE) {

			// <!-- Xử lý vẽ đè trên đâu những tối tượng đang bị chuột rê lên đầu -->
			if (!isDrawMove)
				// <!-- false thì chuyển thành true -->
				isDrawMove = true;
			else {
				// <!-- true rồi thì lấy tọa độ chuột -->
				int xMouse = e.getX();
				int yMouse = e.getY();

				// <!-- Kiểm tra xem có out phạm vi hay ko,nếu out thì thôi, ko thì tiếp tục -->
				if (xMouse <= 626 && xMouse >= 68 && yMouse >= 51 && yMouse <= 609) {

					// <!-- lấy ra iNew và jNew (i j trong mảng 8x8)-->
					int iNew = (yMouse - 50) / size;
					int jNew = (xMouse - 67) / size;

					// <!-- Nếu như có thay đổi vị trí ô trong mảng thì cập nhập lại ko thì thôi-->
					if (iNew != xMove || jNew != yMove) {
						xMove = iNew;
						yMove = jNew;
					}
				} else {
					// <-- outrange thì cho thành -1 -1 -->
					xMove = -1;
					yMove = -1;
				}
			}

			/*
			 * <!-- refesh lại tọa độ của chessman mỗi khi di chuyển để chessman luôn vẽ
			 * theo đúng tọa độ của chuột-->
			 */
			chessman.reFesh(e.getX() - 35, e.getY() - 35);

			// <!-- refesh chessman thì repaint lại luôn cho hiển thị-->
			repaint();
		}
	}

	/*
	 * x = 67;
	 * 
	 * y = 50;
	 * 
	 * size = 70;
	 * 
	 * ===> Ngang: 67 ---> 627
	 * 
	 * ===> Dọc: 50 ---> 610
	 * 
	 * Khởi tạo iNextMoves = 0
	 * 
	 * Khởi tạo jNextMoves = 0
	 * 
	 * Nếu ra khỏi phạm vi i j nextMoves ta mới yêu cầu controller cấp lại
	 * nextMovesBoard
	 */
	@Override
	public void mouseMoved(MouseEvent e) {

		// <!-- Hiện tại là trạng thái game chạy ? Bước tiếp theo : STOP>
		if (isStartGame)

			// <!-- Phải là ở chế độ người vs người, người với máy mới có chức năng này-->
			if (state == Model.HUMAN_VS_COMPUTER || state == Model.HUMAN_VS_HUMAN) {

				// <!-- lấy vị trí chuột hiện hành>
				int xMouse = e.getX();
				int yMouse = e.getY();

				// <!-- Kiểm tra xem có out phạm vi hay ko,nếu out thì thôi, ko thì tiếp tục -->
				if (xMouse > 626 || xMouse < 68 || yMouse < 51 || yMouse > 609) {

					// <!-- Xử khi rê chuột ra khỏi quân cờ-->
					if (iNextMoves != -1 || jNextMoves != -1) {
						iNextMoves = -1;
						jNextMoves = -1;
						view.needNewNextMovesBoard(iNextMoves, jNextMoves, 0);
					}
				} else {

					/*
					 * <!-- Kiểm tra xem vị trí chuột hiện tại có cùng ô hiện hành không
					 * (ijnextmove) nếu không thuộc thì ta mới thực hiệp tiếp -->
					 */
					if (differentFromTheOldLocation(xMouse, yMouse)) {

						// <!-- lấy ra iNew và jNew rồi gán lại giá trị cho iNextMoves và jNextMoves -->
						int iNew = (yMouse - 50) / size;
						int jNew = (xMouse - 67) / size;
						iNextMoves = iNew;
						jNextMoves = jNew;

						// <!-- Không có quân cờ nào hoặc của quân địch thì cập nhập lại ngay -->
						if (board[iNextMoves][jNextMoves] == 0) {
							view.needNewNextMovesBoard(iNextMoves, jNextMoves, 0);
							return;
						} else {
							if (turn == Model.BLACK) {
								if (board[iNextMoves][jNextMoves] > 0)
									view.needNewNextMovesBoard(iNextMoves, jNextMoves, 0);
							} else {
								if (board[iNextMoves][jNextMoves] < 0)
									view.needNewNextMovesBoard(iNextMoves, jNextMoves, 0);
							}
						}

						/*
						 * <!-- Ở đây xảy ra 2 trường hợp, 1 là chế độ người và người ta phải luân phiên
						 * check cả 2 phía, trường hợp còn lại là người với máy chỉ check 1 phía là
						 * người chơi-->
						 */
						switch (state) {

						// <!-- Trường hợp chế độ người với người -->
						case Model.HUMAN_VS_HUMAN:
							/*
							 * <!-- Ta kiểm tra nếu như lượt đi đang là cho quân trắng thì rê vô quân trắng
							 * mới được gởi đi yêu cầu và tương tự với trường hợp lượt đi đang là cho quân
							 * đen -->
							 */
							if (turn == Model.WHITE) {
								if (board[iNew][jNew] > 0)
									view.needNewNextMovesBoard(iNew, jNew, board[iNew][jNew]);
							} else {
								if (board[iNew][jNew] < 0)
									view.needNewNextMovesBoard(iNew, jNew, board[iNew][jNew]);
							}
							break;

						// <!-- Trường hợp chế độ người với máy -->
						case Model.HUMAN_VS_COMPUTER:

							/*
							 * <!-- Ở trường hợp này ta lại gặp 2 trường hợp con nữa là màu của người chơi
							 * chọn, vì ta cho người dùng chọn màu cờ tùy thích trong chế độ người với
							 * máy-->
							 */
							switch (view.getColorPlayer()) {

							/*
							 * <!-- Trường hợp người dùng chọn quân màu trắng thì khi rê vô quân màu trắng
							 * mới được gởi đi yêu cầu và lượt đi hiện tại phải là lượt đi của quân trắng
							 * -->
							 */
							case Model.WHITE:
								if (turn == Model.WHITE)
									if (board[iNew][jNew] > 0)
										view.needNewNextMovesBoard(iNew, jNew, board[iNew][jNew]);
								break;

							/*
							 * <!-- Trường hợp người dùng chọn quân màu đen thì khi rê vô quân màu đen mới
							 * được gởi đi yêu cầu và lượt đi hiện tại phải là lượt cửa quân đen -->
							 */
							case Model.BLACK:
								if (turn == Model.BLACK)
									if (board[iNew][jNew] < 0)
										view.needNewNextMovesBoard(iNew, jNew, board[iNew][jNew]);
								break;
							}
							break;
						}
					}
				}
			}
	}

	// <!-- -Truyền vô tọa độ chuột hiện tại, xem nó có khác i j nextmoves->
	private boolean differentFromTheOldLocation(int xMouse, int yMouse) {
		int iNew = (yMouse - 50) / size;
		int jNew = (xMouse - 67) / size;
		return iNew != iNextMoves || jNew != jNextMoves;
	}

	// <!-- Runable chạy khi chế độ máy với máy được gọi -->
	@Override
	public void run() {
		while (true) {
			try {

				// Ngủ một khoảng thời gian
				Thread.sleep(200);

				// Máy di chuyển
				view.itIsAITurn(turn, aiDepth);
				if (turn == Model.WHITE)
					turn = Model.BLACK;
				else
					turn = Model.WHITE;

				/*
				 * Xong rồi kiểm tra các thế xem có bị hòa hay chiếu hết hay gì không như người
				 * chơi luôn
				 */

				// <!-- Cập nhập đường đi -->
				view.needNewNextMovesBoard(0, 0, 0);

				// <!-- Cập nhập tướng bị chiếu -->
				if (view.enemyHasPointToKing(turn)) {
					for (int i = 0; i < board.length; i++)
						for (int j = 0; j < board.length; j++)
							if (turn == Model.BLACK) {
								if (board[i][j] == -5) {
									xMat = i;
									yMat = j;
								}
							} else {
								if (board[i][j] == 5) {
									xMat = i;
									yMat = j;
								}
							}
				} else {
					xMat = -1;
					yMat = -1;
				}

				// <!-- Kiểm tra hết game và hòa game -->
				// <!-- Vì có nguy cơ hết game nên cứ repaint cái cho chắc :V -->
				repaint();

				// <!-- Gọi tới controller kiểm tra end game -->
				if (view.isEndGame(turn)) {

					// <!-- Nếu end thì hiện ra thông báo -->
					String title = "";
					if (turn == Model.BLACK)
						title = "Quân trắng";
					else
						title = "Quân đen";

					// <!-- Hiện ra thông báo người thắng -->
					int a = JOptionPane.showConfirmDialog(null, title + " thắng !", "Kết thúc game",
							JOptionPane.DEFAULT_OPTION);

					// <!-- reset() -->
					if (a == JOptionPane.OK_OPTION || a == JOptionPane.CLOSED_OPTION)
						view.resetWhenEndGame();

				}

				// <!-- Gọi tới controller kiểm tra cờ hòa -->
				if (view.isTieGame(turn)) {

					// <!-- Nếu end thì hiện ra thông báo -->
					// <!-- Hiện ra thông báo cờ hòa -->
					int a = JOptionPane.showConfirmDialog(null, "Cờ hòa !", "Kết thúc game",
							JOptionPane.DEFAULT_OPTION);

					// <!-- reset() -->
					if (a == JOptionPane.OK_OPTION || a == JOptionPane.CLOSED_OPTION)
						view.resetWhenEndGame();

				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
