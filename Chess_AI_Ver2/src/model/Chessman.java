package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Chessman {

	/*
	 * <!-- 2 trạng thái của chessman, đang hoạt động == người dùng đang thao tác
	 * kéo cờ và nược lại-->
	 */
	public static final int ACTIVE = 0;
	public static final int INACTIVE = 1;

	/*
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
	private BufferedImage bB, bK, bN, bP, bQ, bR, wB, wN, wK, wQ, wR, wP;

	// <!-- Trạng thái hiện tại của chessman -->
	private int state;

	// <!-- Size của mỗi quân cờ , nhận vào từ contrustor-->
	private int size;

	/*
	 * <!-- Lưu giá trị để biết quân cờ hiện tại đang là quân cờ gì, để dễ dàn di
	 * chuyển nó-->
	 */
	private int value;

	/*
	 * <!-- Đây là tọa độ x,y của chuột trên màn mình, đc refesh liên tục thông qua
	 * mouse drag, mục đích là vẽ chessman liên tục theo con chuột -->
	 */
	private int x;
	private int y;

	/*
	 * <!-- Lưu vị trí của quân cờ lúc bắt đầu di chuyển nó, đễ lỡ có di chuyển sai
	 * thì trả về cho nhanh-->
	 */
	private int xOld;
	private int yOld;

	/*
	 * <!-- Tham chiếu đến mảng nước đi để vẽ những nước đi có thể đi lúc di chuyển
	 * quân cờ, vì lúc chessman đang active thì hàm paint bên leftpanel ko vẽ mảng
	 * này nữa-->
	 */
	private int[][] nextMovesBoard;

	// <!-- Nét vẽ -->
	private BasicStroke graphicsLine;

	// <!-- countrustor nhận vào size -->
	public Chessman(int size) throws IOException {
		state = 1;
		this.size = size;
		createImg();
		createGraphicsLine();
	}

	// <!-- Tạo độ đậm nét vẽ, tạo 1 lần sử dụng mãi mãi -->
	private void createGraphicsLine() {
		graphicsLine = new BasicStroke(4f);
	}

	// <!-- Được sử dụng liên tục thông qua mouse drag ở trạng thái active-->
	public void reFesh(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * <!-- Nhận vào giá trị x, y để vẽ ngay lập tức nó cái đã, xong tham chiếu tới
	 * mảng nextMovesBoard để vẽ nước đi có thể đi, value xold và yold để lưu giá
	 * trị (biến quân cờ đang tác động là quân cờ gì) và vị trí đẻ dễ dàng thao
	 * tác-->
	 */
	public void on(int x, int y, int value, int[][] nextMovesBoard, int xOld, int yOld) {
		this.x = x;
		this.y = y;
		this.xOld = xOld;
		this.yOld = yOld;
		this.value = value;
		this.nextMovesBoard = nextMovesBoard;
		state = ACTIVE;
	}

	// <!-- Chuyển trạng thái thành ko hoạt động-->
	public void off() {
		state = INACTIVE;
	}

	// <!-- cho người ta biết đang là trạng thái gì->
	public int getState() {
		return state;
	}

	/*
	 * <!-- Ở trạng thái active, hàm này đc gọi, mục đích vẽ quân cờ dính theo con
	 * trỏ chuột-->
	 */
	public void draw(Graphics2D g2) {
		drawChessPieces(g2, value, x, y);
	}

	// <!-- Khởi tạo các hình ảnh cần thiết, tạo 1 lần và sử dụng mãi mãi-->
	private void createImg() throws IOException {

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
	 * <!-- Vẽ mảng nước đi có thể đi, vị ở trạng thái active nên bên panelleft sẽ
	 * ko vẽ mà thằng này vẽ giùm, đặc biệt ô xuất phát sẽ được border 2px solid
	 * yellow-->
	 */
	public void drawRectangleNextsMove(Graphics2D g2, int i, int j, int x, int y, Color lightCell, Color darkGray,
			Color lightGray) {

		// <!-- Trạng thái ko hoạt động thì thoát -->
		if (state == INACTIVE)
			return;

		// <!-- Nếu ô này có nước đi thì ta bắt đầu xét màu đậm nhạt-->
		if (nextMovesBoard[i][j] != 0) {
			if (g2.getColor() == lightCell)
				g2.setColor(darkGray);
			else
				g2.setColor(lightGray);

			// <!-- có màu rồi ta tô màu nên cho ô cái đã-->
			g2.fillRect(x, y, size, size);

			/*
			 * <!-- ==2 có nghĩa là vị trí xuất phát, ta sét lại nét vẽ bự bự lên tí rồi cho
			 * nó màu vàng và sau đó vẽ border-->
			 */
			if (nextMovesBoard[i][j] == 2) {
				g2.setStroke(graphicsLine);
				g2.setColor(Color.YELLOW);
				g2.drawRect(x + 2, y + 2, size - 4, size - 4);
			}
		}
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

	// <!-- Trả về dòng trong mảng board (Vị trí ban đầu trc khi thao tác)-->
	public int getxOld() {
		return xOld;
	}

	// <!-- Trả về cột trong mảng board (Vị trí ban đầu trc khi thao tác)-->
	public int getyOld() {
		return yOld;
	}

	// <!-- Trả về xem quân cờ này là quân cờ gì-->
	public int getValue() {
		return value;
	}

}
