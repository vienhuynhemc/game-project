package gameObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameDisplay.GamePanel;
import loadData.InputData;

public class GameWorld {

	private InputData inputData;

	private Random rd;
	private ButtonClick[][] arrayButton;
	private int[][] array;

	private LabelLevel lbLevel;
	private ButtonHint buttonHind;
	private ProcessBar timer;
	private LabelPoint lbPoint;
	private LabelDiamond lbDiamond;
	private ButtonPause btPause;

	private Move moveLast;
	private Move moveNew;
	private int countClick;

	private BackGround backGround;

	private GamePanel game;

	private int level;
	private boolean isEnd;

	public GameWorld(GamePanel game) throws IOException {
		this.game = game;

		level = 13;

		inputData = new InputData();
		backGround = new BackGround(this);

		rd = new Random();

		arrayButton = new ButtonClick[11][18];
		array = new int[11][18];

		int x = 75;
		int y = 30;
		for (int i = 1; i < arrayButton.length - 1; i++) {
			for (int j = 1; j < arrayButton[i].length - 1; j++) {
				ButtonClick button = new ButtonClick(this);
				button.addMouseListener(game.getCenterPanel());

				button.setBounds(x, y, 52, 52);

				arrayButton[i][j] = button;
				game.getCenterPanel().add(button);

				x += 53;
			}
			y += 53;
			x = 75;
		}
		taoMang();

		getBackGround().setCurrentAnimation(level - 1);

		lbDiamond = new LabelDiamond(this);
		lbDiamond.setBounds(0, 0, 200, 200);

		buttonHind = new ButtonHint(this);
		buttonHind.setBounds(100, 10, 150, 200);
		game.getTopPanel().add(buttonHind);
		buttonHind.addActionListener(game.getTopPanel());

		lbLevel = new LabelLevel(this);
		lbLevel.setBounds(300, 10, 100, 100);
		lbLevel.setLevel(level);

		timer = new ProcessBar(this);
		timer.setBounds(320, -5, 200, 200);
		game.getTopPanel().add(timer);

		lbPoint = new LabelPoint(this);
		lbPoint.setBounds(750, 10, 200, 200);

		btPause = new ButtonPause(this);
		btPause.setBounds(930, 10, 50, 50);
		game.getTopPanel().add(btPause);
		btPause.addActionListener(game.getTopPanel());

		moveLast = new Move(0, 0);
		moveNew = new Move(0, 0);

	}

	public boolean isHetDuong() {
		for (int i = 1; i < array.length - 1; i++) {
			for (int j = 1; j < array[i].length - 1; j++) {
				if (array[i][j] != 0) {
					Move ml = new Move(i, j);
					for (int k = 1; k < array.length - 1; k++) {
						for (int k2 = 1; k2 < array[k].length - 1; k2++) {
							if (array[k][k2] != 0) {
								Move mn = new Move(k, k2);
								if (isConnect(ml, mn) && (i != k || j != k2)) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] != 0)
					count++;
			}
		}
		if (count == 0)
			return false;
		return true;
	}

	public void taoMoiDuong() {
		int[][] arr = new int[array.length][array[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (array[i][j] != 0)
					arr[i][j] = -1;
			}
		}

		int count = 10;
		while (count != 0) {
			count = 0;

			int pikachu = rd.nextInt(22);

			while (pikachu == 0) {
				pikachu = rd.nextInt(22);
			}

			int x = rd.nextInt(10);
			int y = rd.nextInt(17);

			while (arr[x][y] != -1 || x == 0 || x == 10 || y == 0 || y == 17) {
				x = rd.nextInt(10);
				y = rd.nextInt(17);
			}
			arr[x][y] = pikachu;
			x = rd.nextInt(10);
			y = rd.nextInt(17);
			while (arr[x][y] != -1 || x == 0 || x == 10 || y == 0 || y == 17) {
				x = rd.nextInt(10);
				y = rd.nextInt(17);
			}
			arr[x][y] = pikachu;

			for (int i = 1; i < arr.length - 1; i++) {
				for (int j = 1; j < arr[i].length - 1; j++) {
					if (arr[i][j] == -1)
						count++;
				}
			}
		}

		for (int i = 1; i < arr.length - 1; i++) {
			for (int j = 1; j < arr[i].length - 1; j++) {
				arrayButton[i][j].setCurenImage(arr[i][j]);
				array[i][j] = arr[i][j];
			}
		}
	}

	public void hint() {
		for (int i = 1; i < array.length - 1; i++) {
			for (int j = 1; j < array[i].length - 1; j++) {
				if (array[i][j] != 0) {
					Move ml = new Move(i, j);
					for (int k = 1; k < array.length - 1; k++) {
						for (int k2 = 1; k2 < array[k].length - 1; k2++) {
							if (array[k][k2] != 0) {
								Move mn = new Move(k, k2);
								if (isConnect(ml, mn) && (i != k || j != k2)) {
									arrayButton[ml.getX()][ml.getY()].setHint(true);
									arrayButton[mn.getX()][mn.getY()].setHint(true);
									return;
								}
							}
						}
					}
				}
			}
		}
	}

	public boolean isConnectDocNgang(Move moveLast, Move moveNew) {
		if (moveLast.getX() == moveNew.getX()) {
			int start = moveLast.getY();
			int end = moveNew.getY();
			if (moveLast.getY() > moveNew.getY()) {
				start = moveNew.getY();
				end = moveLast.getY();
			}

			int count = 0;
			for (int i = start + 1; i < end; i++) {
				if (array[moveLast.getX()][i] != 0)
					count++;
			}

			if (count == 0) {
				return true;
			}
		}

		if (moveLast.getY() == moveNew.getY()) {
			int start = moveLast.getX();
			int end = moveNew.getX();
			if (moveLast.getX() > moveNew.getX()) {
				start = moveNew.getX();
				end = moveLast.getX();
			}

			int count = 0;
			for (int i = start + 1; i < end; i++) {
				if (array[i][moveLast.getY()] != 0) {
					count++;
				}
			}

			if (count == 0) {
				return true;
			}
		}
		return false;
	}

	public void actionRemove() {
		Move m1 = new Move(moveLast.getX() - 1, moveLast.getY() - 1);
		Move m2 = new Move(moveNew.getX() - 1, moveNew.getY() - 1);

		game.getListAnimation().add(new LongDepTrai(this, m1.getY() * 52 + 25 + m1.getY() * 2 + 25,
				m1.getX() * 52 + 25 + m1.getX() * 2 + 25, 1));
		game.getListAnimation().add(new LongDepTrai(this, m2.getY() * 52 + 25 + m2.getY() * 2 + 25,
				m2.getX() * 52 + 25 + m2.getX() * 2 + 25, 1));
	}

	public boolean isConnect(Move moveLast, Move moveNew) {
		if (isConnectDocNgang(moveLast, moveNew)) {
			if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {
				return true;
			}
		}

		// ngang trái
		for (int i = moveLast.getY(); i >= 0; i--) {
			if (isConnectDocNgang(new Move(moveLast.getX(), i), moveLast)) {

				if (array[moveLast.getX()][i] != 0 && i != moveLast.getY()) {
					break;
				} else {
					if (isConnectDocNgang(new Move(moveLast.getX(), i), new Move(moveNew.getX(), i))) {

						if ((i != moveNew.getY() && array[moveNew.getX()][i] == 0) || i == moveNew.getY()) {

							if (isConnectDocNgang(new Move(moveNew.getX(), i), moveNew)) {
								if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {

									return true;
								}
							}
						}
					}
				}
			}
		}

		// ngang phải
		for (int i = moveLast.getY(); i < array[0].length; i++) {
			if (isConnectDocNgang(new Move(moveLast.getX(), i), moveLast)) {

				if (array[moveLast.getX()][i] != 0 && i != moveLast.getY()) {
					break;
				} else {
					if (isConnectDocNgang(new Move(moveLast.getX(), i), new Move(moveNew.getX(), i))) {

						if ((i != moveNew.getY() && array[moveNew.getX()][i] == 0) || i == moveNew.getY()) {

							if (isConnectDocNgang(new Move(moveNew.getX(), i), moveNew)) {
								if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {

									return true;
								}
							}
						}
					}
				}
			}
		}

		// dọc trên
		for (int i = moveLast.getX(); i >= 0; i--) {
			if (isConnectDocNgang(new Move(i, moveLast.getY()), moveLast)) {

				if (array[i][moveLast.getY()] != 0 && i != moveLast.getX()) {
					break;
				} else {
					if (isConnectDocNgang(new Move(i, moveLast.getY()), new Move(i, moveNew.getY()))) {

						if ((i != moveNew.getX() && array[i][moveNew.getY()] == 0) || i == moveNew.getX()) {

							if (isConnectDocNgang(new Move(i, moveNew.getY()), moveNew)) {
								if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {

									return true;
								}
							}
						}
					}
				}
			}
		}

		// dọc dưới
		for (int i = moveLast.getX(); i < array.length; i++) {
			if (isConnectDocNgang(new Move(i, moveLast.getY()), moveLast)) {

				if (array[i][moveLast.getY()] != 0 && i != moveLast.getX()) {
					break;
				} else {
					if (isConnectDocNgang(new Move(i, moveLast.getY()), new Move(i, moveNew.getY()))) {

						if ((i != moveNew.getX() && array[i][moveNew.getY()] == 0) || i == moveNew.getX()) {

							if (isConnectDocNgang(new Move(i, moveNew.getY()), moveNew)) {
								if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {

									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;

	}

	public boolean isConnect() {

		if (isConnectDocNgang(moveLast, moveNew)) {
			if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {
				array[moveLast.getX()][moveLast.getY()] = 0;
				array[moveNew.getX()][moveNew.getY()] = 0;

				tinhDiem();

				List<Move> listMove = new ArrayList<Move>();
				listMove.add(new Move(moveLast.getX(), moveLast.getY()));
				listMove.add(new Move(moveNew.getX(), moveNew.getY()));

				game.getListLine().add(new Line(listMove));

				actionRemove();

				return true;
			}
		}

		// ngang trái
		for (int i = moveLast.getY(); i >= 0; i--) {
			if (isConnectDocNgang(new Move(moveLast.getX(), i), moveLast)) {

				if (array[moveLast.getX()][i] != 0 && i != moveLast.getY()) {
					break;
				} else {
					if (isConnectDocNgang(new Move(moveLast.getX(), i), new Move(moveNew.getX(), i))) {

						if ((i != moveNew.getY() && array[moveNew.getX()][i] == 0) || i == moveNew.getY()) {

							if (isConnectDocNgang(new Move(moveNew.getX(), i), moveNew)) {
								if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {

									array[moveLast.getX()][moveLast.getY()] = 0;
									array[moveNew.getX()][moveNew.getY()] = 0;

									tinhDiem();

									List<Move> listMove = new ArrayList<Move>();
									listMove.add(new Move(moveLast.getX(), moveLast.getY()));
									listMove.add(new Move(moveLast.getX(), i));
									listMove.add(new Move(moveNew.getX(), i));
									listMove.add(new Move(moveNew.getX(), moveNew.getY()));

									game.getListLine().add(new Line(listMove));

									actionRemove();
									return true;
								}
							}
						}
					}
				}
			}
		}

		// ngang phải
		for (int i = moveLast.getY(); i < array[0].length; i++) {
			if (isConnectDocNgang(new Move(moveLast.getX(), i), moveLast)) {

				if (array[moveLast.getX()][i] != 0 && i != moveLast.getY()) {
					break;
				} else {
					if (isConnectDocNgang(new Move(moveLast.getX(), i), new Move(moveNew.getX(), i))) {

						if ((i != moveNew.getY() && array[moveNew.getX()][i] == 0) || i == moveNew.getY()) {

							if (isConnectDocNgang(new Move(moveNew.getX(), i), moveNew)) {
								if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {

									array[moveLast.getX()][moveLast.getY()] = 0;
									array[moveNew.getX()][moveNew.getY()] = 0;

									tinhDiem();

									List<Move> listMove = new ArrayList<Move>();
									listMove.add(new Move(moveLast.getX(), moveLast.getY()));
									listMove.add(new Move(moveLast.getX(), i));
									listMove.add(new Move(moveNew.getX(), i));
									listMove.add(new Move(moveNew.getX(), moveNew.getY()));

									game.getListLine().add(new Line(listMove));

									actionRemove();
									return true;
								}
							}
						}
					}
				}
			}
		}

		// dọc trên
		for (int i = moveLast.getX(); i >= 0; i--) {
			if (isConnectDocNgang(new Move(i, moveLast.getY()), moveLast)) {

				if (array[i][moveLast.getY()] != 0 && i != moveLast.getX()) {
					break;
				} else {
					if (isConnectDocNgang(new Move(i, moveLast.getY()), new Move(i, moveNew.getY()))) {

						if ((i != moveNew.getX() && array[i][moveNew.getY()] == 0) || i == moveNew.getX()) {

							if (isConnectDocNgang(new Move(i, moveNew.getY()), moveNew)) {
								if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {

									array[moveLast.getX()][moveLast.getY()] = 0;
									array[moveNew.getX()][moveNew.getY()] = 0;

									tinhDiem();

									List<Move> listMove = new ArrayList<Move>();
									listMove.add(new Move(moveLast.getX(), moveLast.getY()));
									listMove.add(new Move(i, moveLast.getY()));
									listMove.add(new Move(i, moveNew.getY()));
									listMove.add(new Move(moveNew.getX(), moveNew.getY()));

									game.getListLine().add(new Line(listMove));

									actionRemove();
									return true;
								}
							}
						}
					}
				}
			}
		}

		// dọc dưới
		for (int i = moveLast.getX(); i < array.length; i++) {
			if (isConnectDocNgang(new Move(i, moveLast.getY()), moveLast)) {

				if (array[i][moveLast.getY()] != 0 && i != moveLast.getX()) {
					break;
				} else {
					if (isConnectDocNgang(new Move(i, moveLast.getY()), new Move(i, moveNew.getY()))) {

						if ((i != moveNew.getX() && array[i][moveNew.getY()] == 0) || i == moveNew.getX()) {

							if (isConnectDocNgang(new Move(i, moveNew.getY()), moveNew)) {
								if (array[moveLast.getX()][moveLast.getY()] == array[moveNew.getX()][moveNew.getY()]) {

									array[moveLast.getX()][moveLast.getY()] = 0;
									array[moveNew.getX()][moveNew.getY()] = 0;

									tinhDiem();

									List<Move> listMove = new ArrayList<Move>();
									listMove.add(new Move(moveLast.getX(), moveLast.getY()));
									listMove.add(new Move(i, moveLast.getY()));
									listMove.add(new Move(i, moveNew.getY()));
									listMove.add(new Move(moveNew.getX(), moveNew.getY()));

									game.getListLine().add(new Line(listMove));

									actionRemove();
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;

	}

	public void tinhDiem() {
		switch (level) {

		case 1:
			lbPoint.setPointNew(lbPoint.getPointNew() + 10);
			break;

		case 2:
			lbPoint.setPointNew(lbPoint.getPointNew() + 15);
			break;

		case 3:
			lbPoint.setPointNew(lbPoint.getPointNew() + 20);
			break;

		case 4:
			lbPoint.setPointNew(lbPoint.getPointNew() + 25);
			break;

		case 5:
			lbPoint.setPointNew(lbPoint.getPointNew() + 30);
			break;

		case 6:
			lbPoint.setPointNew(lbPoint.getPointNew() + 35);
			break;

		case 7:
			lbPoint.setPointNew(lbPoint.getPointNew() + 40);
			break;

		case 8:
			lbPoint.setPointNew(lbPoint.getPointNew() + 45);
			break;

		case 9:
			lbPoint.setPointNew(lbPoint.getPointNew() + 50);
			break;

		case 10:
			lbPoint.setPointNew(lbPoint.getPointNew() + 55);
			break;

		case 11:
			lbPoint.setPointNew(lbPoint.getPointNew() + 60);
			break;

		case 12:
			lbPoint.setPointNew(lbPoint.getPointNew() + 65);
			break;

		case 13:
			lbPoint.setPointNew(lbPoint.getPointNew() + 70);
			break;

		case 14:
			lbPoint.setPointNew(lbPoint.getPointNew() + 75);
			break;

		case 15:
			lbPoint.setPointNew(lbPoint.getPointNew() + 80);
			break;

		default:
			break;

		}
	}

	public void diChuyenPhaiHalfLeft() {
		for (int k = 1; k < array.length - 1; k++) {
			int x = k;

			int count = 0;
			for (int i = 1; i < 9; i++) {
				if (array[x][i] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenPhaiHalfLeft(16, x) != -1 && count != 0) {
				while (!isCompleteDiChuyenPhaiHalfLeft(x)) {
					for (int i = 8; i > 0; i--) {
						if (array[x][i] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenPhaiHalfLeft(i, x);

							if (index != -1) {

								arrayButton[x][i].setCurenImage(arrayButton[x][index].getCurenImage());
								arrayButton[x][i].setVisible(true);
								arrayButton[x][i].setPress(false);
								arrayButton[x][i].setHint(false);

								arrayButton[x][index].setVisible(false);

								swapX(i, index, x);
								break;
							}
						}
					}
				}
			}
		}
	}

	public boolean isCompleteDiChuyenPhaiHalfLeft(int x) {
		int index = 0;
		for (int i = 8; i > 0; i--) {
			if (array[x][i] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 8; i > 0; i--) {
			if (array[x][i] != 0 && i < index) {
				return false;
			}
		}

		return true;
	}

	public int indexKhacKhongDauTienDiCHuyenPhaiHalfLeft(int start, int x) {
		for (int i = start; i > 0; i--) {
			if (array[x][i] != 0)
				return i;
		}

		return -1;
	}

	public void diChuyenPhaiHalf() {
		for (int k = 1; k < array.length - 1; k++) {
			int x = k;

			int count = 0;
			for (int i = 9; i < array[0].length - 1; i++) {
				if (array[x][i] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenPhaiHalf(16, x) != -1 && count != 0) {
				while (!isCompleteDiChuyenPhaiHalf(x)) {
					for (int i = 16; i > 8; i--) {
						if (array[x][i] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenPhaiHalf(i, x);

							if (index != -1) {

								arrayButton[x][i].setCurenImage(arrayButton[x][index].getCurenImage());
								arrayButton[x][i].setVisible(true);
								arrayButton[x][i].setPress(false);
								arrayButton[x][i].setHint(false);

								arrayButton[x][index].setVisible(false);

								swapX(i, index, x);
								break;
							}
						}
					}
				}
			}
		}
	}

	public boolean isCompleteDiChuyenPhaiHalf(int x) {
		int index = 0;
		for (int i = 16; i > 8; i--) {
			if (array[x][i] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 16; i > 8; i--) {
			if (array[x][i] != 0 && i < index) {
				return false;
			}
		}

		return true;
	}

	public int indexKhacKhongDauTienDiCHuyenPhaiHalf(int start, int x) {
		for (int i = start; i > 8; i--) {
			if (array[x][i] != 0)
				return i;
		}

		return -1;
	}

	public void diChuyenPhai() {
		for (int k = 1; k < array.length - 1; k++) {
			int x = k;

			int count = 0;
			for (int i = 1; i < array[0].length - 1; i++) {
				if (array[x][i] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenPhai(16, x) != -1 && count != 0) {
				while (!isCompleteDiChuyenPhai(x)) {
					for (int i = 16; i > 0; i--) {
						if (array[x][i] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenPhai(i, x);

							if (index != -1) {

								arrayButton[x][i].setCurenImage(arrayButton[x][index].getCurenImage());
								arrayButton[x][i].setVisible(true);
								arrayButton[x][i].setPress(false);
								arrayButton[x][i].setHint(false);

								arrayButton[x][index].setVisible(false);

								swapX(i, index, x);
								break;
							}
						}
					}
				}
			}
		}
	}

	public boolean isCompleteDiChuyenPhai(int x) {
		int index = 0;
		for (int i = 16; i > 0; i--) {
			if (array[x][i] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 16; i > 0; i--) {
			if (array[x][i] != 0 && i < index) {
				return false;
			}
		}

		return true;
	}

	public int indexKhacKhongDauTienDiCHuyenPhai(int start, int x) {
		for (int i = start; i > 0; i--) {
			if (array[x][i] != 0)
				return i;
		}

		return -1;
	}

	public void diChuyenTraiHalf() {
		for (int k = 1; k < array.length - 1; k++) {
			int x = k;

			int count = 0;
			for (int i = 1; i < 9; i++) {
				if (array[x][i] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenTraiHalf(1, x) != -1 && count != 0) {
				while (!isCompleteDiChuyenTraiHalf(x)) {
					for (int i = 1; i < 9; i++) {
						if (array[x][i] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenTraiHalf(i, x);

							if (index != -1) {

								arrayButton[x][i].setCurenImage(arrayButton[x][index].getCurenImage());
								arrayButton[x][i].setVisible(true);
								arrayButton[x][i].setPress(false);
								arrayButton[x][i].setHint(false);

								arrayButton[x][index].setVisible(false);

								swapX(i, index, x);
								break;
							}
						}
					}
				}
			}
		}
	}

	public boolean isCompleteDiChuyenTraiHalf(int x) {
		int index = 0;
		for (int i = 1; i < 9; i++) {
			if (array[x][i] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (array[x][i] != 0 && i > index) {
				return false;
			}
		}

		return true;
	}

	public int indexKhacKhongDauTienDiCHuyenTraiHalf(int start, int x) {
		for (int i = start; i < 9; i++) {
			if (array[x][i] != 0)
				return i;
		}

		return -1;
	}

	public void diChuyenTraiHalfRight() {
		for (int k = 1; k < array.length - 1; k++) {
			int x = k;

			int count = 0;
			for (int i = 9; i < 17; i++) {
				if (array[x][i] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenTraiHalfRight(1, x) != -1 && count != 0) {
				while (!isCompleteDiChuyenTraiHalfRight(x)) {
					for (int i = 9; i < 17; i++) {
						if (array[x][i] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenTraiHalfRight(i, x);

							if (index != -1) {

								arrayButton[x][i].setCurenImage(arrayButton[x][index].getCurenImage());
								arrayButton[x][i].setVisible(true);
								arrayButton[x][i].setPress(false);
								arrayButton[x][i].setHint(false);

								arrayButton[x][index].setVisible(false);

								swapX(i, index, x);
								break;
							}
						}
					}
				}
			}
		}
	}

	public boolean isCompleteDiChuyenTraiHalfRight(int x) {
		int index = 0;
		for (int i = 9; i < 17; i++) {
			if (array[x][i] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 9; i < 17; i++) {
			if (array[x][i] != 0 && i > index) {
				return false;
			}
		}

		return true;
	}

	public int indexKhacKhongDauTienDiCHuyenTraiHalfRight(int start, int x) {
		for (int i = start; i < 17; i++) {
			if (array[x][i] != 0)
				return i;
		}

		return -1;
	}

	public void diChuyenTrai() {
		for (int k = 1; k < array.length - 1; k++) {
			int x = k;

			int count = 0;
			for (int i = 1; i < array[0].length - 1; i++) {
				if (array[x][i] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenTrai(1, x) != -1 && count != 0) {
				while (!isCompleteDiChuyenTrai(x)) {
					for (int i = 1; i < array[x].length - 1; i++) {
						if (array[x][i] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenTrai(i, x);

							if (index != -1) {

								arrayButton[x][i].setCurenImage(arrayButton[x][index].getCurenImage());
								arrayButton[x][i].setVisible(true);
								arrayButton[x][i].setPress(false);
								arrayButton[x][i].setHint(false);

								arrayButton[x][index].setVisible(false);

								swapX(i, index, x);
								break;
							}
						}
					}
				}
			}
		}
	}

	public boolean isCompleteDiChuyenTrai(int x) {
		int index = 0;
		for (int i = 1; i < array[x].length; i++) {
			if (array[x][i] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 0; i < array[x].length; i++) {
			if (array[x][i] != 0 && i > index) {
				return false;
			}
		}

		return true;
	}

	public int indexKhacKhongDauTienDiCHuyenTrai(int start, int x) {
		for (int i = start; i < array[x].length - 1; i++) {
			if (array[x][i] != 0)
				return i;
		}

		return -1;
	}

	public void swapX(int i, int index, int x) {
		int a = array[x][i];
		array[x][i] = array[x][index];
		array[x][index] = a;
	}

	public void diChuyenXuong() {
		for (int k = 1; k < array[0].length - 1; k++) {
			int y = k;

			int count = 0;
			for (int i = 1; i < array.length - 1; i++) {
				if (array[i][y] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenXuong(9, y) != -1 && count != 0) {

				while (!isCompleteDiChuyenXuong(y)) {
					for (int i = 9; i > 0; i--) {
						if (array[i][y] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenXuong(i, y);

							if (index != -1) {

								arrayButton[i][y].setCurenImage(arrayButton[index][y].getCurenImage());
								arrayButton[i][y].setVisible(true);
								arrayButton[i][y].setPress(false);
								arrayButton[i][y].setHint(false);

								arrayButton[index][y].setVisible(false);

								swap(i, index, y);
								break;
							}
						}
					}

				}

			}
		}

	}

	public int indexKhacKhongDauTienDiCHuyenXuong(int start, int y) {
		for (int i = start; i > 0; i--) {
			if (array[i][y] != 0)
				return i;
		}

		return -1;
	}

	public boolean isCompleteDiChuyenXuong(int y) {
		int index = 0;
		for (int i = 9; i > 0; i--) {
			if (array[i][y] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 10; i > 0; i--) {
			if (array[i][y] != 0 && i < index) {
				return false;
			}
		}

		return true;
	}

	public void diChuyenXuongHalf() {
		for (int k = 1; k < array[0].length - 1; k++) {
			int y = k;

			int count = 0;
			for (int i = 6; i < array.length - 1; i++) {
				if (array[i][y] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenXuongHalf(9, y) != -1 && count != 0) {

				while (!isCompleteDiChuyenXuongHalf(y)) {
					for (int i = 9; i > 5; i--) {
						if (array[i][y] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenXuongHalf(i, y);

							if (index != -1) {

								arrayButton[i][y].setCurenImage(arrayButton[index][y].getCurenImage());
								arrayButton[i][y].setVisible(true);
								arrayButton[i][y].setPress(false);
								arrayButton[i][y].setHint(false);

								arrayButton[index][y].setVisible(false);

								swap(i, index, y);
								break;
							}
						}
					}

				}

			}
		}

	}

	public int indexKhacKhongDauTienDiCHuyenXuongHalf(int start, int y) {
		for (int i = start; i > 5; i--) {
			if (array[i][y] != 0)
				return i;
		}

		return -1;
	}

	public boolean isCompleteDiChuyenXuongHalf(int y) {
		int index = 0;
		for (int i = 9; i > 5; i--) {
			if (array[i][y] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 9; i > 5; i--) {
			if (array[i][y] != 0 && i < index) {
				return false;
			}
		}

		return true;
	}

	public void diChuyenXuongHalfTop() {
		for (int k = 1; k < array[0].length - 1; k++) {
			int y = k;

			int count = 0;
			for (int i = 1; i < 6; i++) {
				if (array[i][y] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenXuongHalfTop(9, y) != -1 && count != 0) {

				while (!isCompleteDiChuyenXuongHalfTop(y)) {
					for (int i = 5; i > 0; i--) {
						if (array[i][y] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenXuongHalfTop(i, y);

							if (index != -1) {

								arrayButton[i][y].setCurenImage(arrayButton[index][y].getCurenImage());
								arrayButton[i][y].setVisible(true);
								arrayButton[i][y].setPress(false);
								arrayButton[i][y].setHint(false);

								arrayButton[index][y].setVisible(false);

								swap(i, index, y);
								break;
							}
						}
					}

				}

			}
		}

	}

	public int indexKhacKhongDauTienDiCHuyenXuongHalfTop(int start, int y) {
		for (int i = start; i > 0; i--) {
			if (array[i][y] != 0)
				return i;
		}

		return -1;
	}

	public boolean isCompleteDiChuyenXuongHalfTop(int y) {
		int index = 0;
		for (int i = 5; i > 0; i--) {
			if (array[i][y] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 5; i > 0; i--) {
			if (array[i][y] != 0 && i < index) {
				return false;
			}
		}

		return true;
	}

	public void diChuyenLenHalf() {
		for (int k = 1; k < array[0].length - 1; k++) {
			int y = k;

			int count = 0;
			for (int i = 1; i < 6; i++) {
				if (array[i][y] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenLenHalf(1, y) != -1 && count != 0) {
				while (!isCompleteDiChuyenLenHalf(y))
					for (int i = 1; i < 6; i++) {
						if (array[i][y] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenLenHalf(i, y);

							if (index != -1) {

								arrayButton[i][y].setCurenImage(arrayButton[index][y].getCurenImage());
								arrayButton[i][y].setVisible(true);
								arrayButton[i][y].setPress(false);
								arrayButton[i][y].setHint(false);

								arrayButton[index][y].setVisible(false);

								swap(i, index, y);
								break;
							}
						}
					}
			}
		}
	}

	public boolean isCompleteDiChuyenLenHalf(int y) {
		int index = 0;
		for (int i = 1; i < 6; i++) {
			if (array[i][y] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 1; i < 6; i++) {
			if (array[i][y] != 0 && i > index) {
				return false;
			}
		}

		return true;
	}

	public int indexKhacKhongDauTienDiCHuyenLenHalf(int start, int y) {
		for (int i = start; i < 6; i++) {
			if (array[i][y] != 0)
				return i;
		}

		return -1;
	}

	public void diChuyenLenHalfTop() {
		for (int k = 1; k < array[0].length - 1; k++) {
			int y = k;

			int count = 0;
			for (int i = 6; i < 10; i++) {
				if (array[i][y] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenLenHalfTop(1, y) != -1 && count != 0) {
				while (!isCompleteDiChuyenLenHalfTop(y))
					for (int i = 6; i < 10; i++) {
						if (array[i][y] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenLenHalfTop(i, y);

							if (index != -1) {

								arrayButton[i][y].setCurenImage(arrayButton[index][y].getCurenImage());
								arrayButton[i][y].setVisible(true);
								arrayButton[i][y].setPress(false);
								arrayButton[i][y].setHint(false);

								arrayButton[index][y].setVisible(false);

								swap(i, index, y);
								break;
							}
						}
					}
			}
		}
	}

	public boolean isCompleteDiChuyenLenHalfTop(int y) {
		int index = 0;
		for (int i = 6; i < 10; i++) {
			if (array[i][y] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 6; i < 10; i++) {
			if (array[i][y] != 0 && i > index) {
				return false;
			}
		}

		return true;
	}

	public int indexKhacKhongDauTienDiCHuyenLenHalfTop(int start, int y) {
		for (int i = start; i < 10; i++) {
			if (array[i][y] != 0)
				return i;
		}

		return -1;
	}

	public void diChuyenLen() {
		for (int k = 1; k < array[0].length - 1; k++) {
			int y = k;

			int count = 0;
			for (int i = 1; i < array.length - 1; i++) {
				if (array[i][y] == 0)
					count++;
			}

			if (indexKhacKhongDauTienDiCHuyenLen(1, y) != -1 && count != 0) {
				while (!isCompleteDiChuyenLen(y))
					for (int i = 1; i < array.length - 1; i++) {
						if (array[i][y] == 0) {

							int index = indexKhacKhongDauTienDiCHuyenLen(i, y);

							if (index != -1) {

								arrayButton[i][y].setCurenImage(arrayButton[index][y].getCurenImage());
								arrayButton[i][y].setVisible(true);
								arrayButton[i][y].setPress(false);
								arrayButton[i][y].setHint(false);

								arrayButton[index][y].setVisible(false);

								swap(i, index, y);
								break;
							}
						}
					}
			}
		}
	}

	public boolean isCompleteDiChuyenLen(int y) {
		int index = 0;
		for (int i = 1; i < array.length - 1; i++) {
			if (array[i][y] == 0) {
				index = i;
				break;
			}
		}

		for (int i = 0; i < array.length; i++) {
			if (array[i][y] != 0 && i > index) {
				return false;
			}
		}

		return true;
	}

	public int indexKhacKhongDauTienDiCHuyenLen(int start, int y) {
		for (int i = start; i < array.length - 1; i++) {
			if (array[i][y] != 0)
				return i;
		}

		return -1;
	}

	public void swap(int i, int index, int y) {
		int a = array[i][y];
		array[i][y] = array[index][y];
		array[index][y] = a;
	}

	public void hanhXuLevel() {

		switch (level) {

		case 1:

			diChuyenLen();

			break;

		case 2:

			diChuyenXuong();
			break;

		case 3:

			diChuyenTrai();
			break;

		case 4:

			diChuyenPhai();
			break;

		case 5:

			diChuyenLen();
			diChuyenTrai();

			break;

		case 6:

			diChuyenLen();
			diChuyenPhai();

			break;

		case 7:

			diChuyenXuong();
			diChuyenTrai();

			break;

		case 8:

			diChuyenXuong();
			diChuyenPhai();

			break;

		case 9:

			diChuyenXuongHalf();
			diChuyenLenHalf();

			break;

		case 10:

			diChuyenTraiHalf();
			diChuyenPhaiHalf();

			break;

		case 11:

			diChuyenXuongHalf();
			diChuyenLenHalf();
			diChuyenTraiHalf();
			diChuyenPhaiHalf();

			break;

		case 12:

			diChuyenLenHalfTop();
			diChuyenXuongHalfTop();

			break;

		case 13:

			diChuyenTraiHalfRight();
			diChuyenPhaiHalfLeft();

			break;

		case 14:

			diChuyenLenHalfTop();
			diChuyenXuongHalfTop();
			diChuyenTraiHalfRight();
			diChuyenPhaiHalfLeft();

			break;

		default:
			break;

		}
	}

	public void taoMang() {
		int count = 10;
		while (count != 0) {
			count = 0;

			int pikachu = rd.nextInt(22);

			while (pikachu == 0) {
				pikachu = rd.nextInt(22);
			}

			int x = rd.nextInt(10);
			int y = rd.nextInt(17);

			while (array[x][y] != 0 || x == 0 || x == 10 || y == 0 || y == 17) {
				x = rd.nextInt(10);
				y = rd.nextInt(17);
			}
			array[x][y] = pikachu;
			x = rd.nextInt(10);
			y = rd.nextInt(17);
			while (array[x][y] != 0 || x == 0 || x == 10 || y == 0 || y == 17) {
				x = rd.nextInt(10);
				y = rd.nextInt(17);
			}
			array[x][y] = pikachu;

			for (int i = 1; i < array.length - 1; i++) {
				for (int j = 1; j < array[i].length - 1; j++) {
					if (array[i][j] == 0)
						count++;
				}
			}
		}

		for (int i = 1; i < array.length - 1; i++) {
			for (int j = 1; j < array[i].length - 1; j++) {
				arrayButton[i][j].setCurenImage(array[i][j]);
			}
		}
	}

	public void upload() {

		int count = 0;

		for (int i = 1; i < array.length - 1; i++) {
			for (int j = 1; j < array[i].length - 1; j++) {
				if (array[i][j] != 0)
					count++;
			}
		}

		if (count == 0) {
			if (level < 14) {

				level++;
				taoMang();
				lbLevel.setLevel(level);
				lbDiamond.setScore(lbDiamond.getScore() + 50);
				getBackGround().setCurrentAnimation(level - 1);
				timer.setTimeStart(System.currentTimeMillis());
				for (int i = 1; i < array.length - 1; i++) {
					for (int j = 1; j < array[i].length - 1; j++) {
						arrayButton[i][j].setVisible(true);
						arrayButton[i][j].setPress(false);
						arrayButton[i][j].setHint(false);
					}
				}
			} else {
				if (!isEnd) {
					isEnd = true;
				}
			}
		}
	}

	public void newGame() {

		isEnd = false;

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = 0;
			}
		}

		setCountClick(0);

		level = 1;
		taoMang();
		lbLevel.setLevel(level);
		lbPoint.setPoint(0);
		lbPoint.setPointNew(0);
		lbDiamond.setScore(100);
		getBackGround().setCurrentAnimation(level - 1);
		timer.setTimeStart(System.currentTimeMillis());
		timer.setStop(false);
		for (int i = 1; i < array.length - 1; i++) {
			for (int j = 1; j < array[i].length - 1; j++) {
				arrayButton[i][j].setVisible(true);
				arrayButton[i][j].setPress(false);
				arrayButton[i][j].setHint(false);
			}
		}
	}

	///////////// GETER AND SETER///////////////////////////

	public InputData getInputData() {
		return inputData;
	}

	public void setInputData(InputData inputData) {
		this.inputData = inputData;
	}

	public ButtonClick[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonClick[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

	public LabelLevel getLbLevel() {
		return lbLevel;
	}

	public void setLbLevel(LabelLevel lbLevel) {
		this.lbLevel = lbLevel;
	}

	public ButtonHint getButtonHint() {
		return buttonHind;
	}

	public void setButtonHint(ButtonHint buttonHint) {
		this.buttonHind = buttonHint;
	}

	public ProcessBar getTimer() {
		return timer;
	}

	public void setTimer(ProcessBar timer) {
		this.timer = timer;
	}

	public LabelPoint getLbPoint() {
		return lbPoint;
	}

	public void setLbPoint(LabelPoint lbPoint) {
		this.lbPoint = lbPoint;
	}

	public LabelDiamond getLbDiamond() {
		return lbDiamond;
	}

	public void setLbDiamond(LabelDiamond lbDiamond) {
		this.lbDiamond = lbDiamond;
	}

	public ButtonPause getButtonPause() {
		return btPause;
	}

	public void setButtonPause(ButtonPause buttonPause) {
		this.btPause = buttonPause;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

	public int[][] getArray() {
		return array;
	}

	public void setArray(int[][] array) {
		this.array = array;
	}

	public Move getMoveLast() {
		return moveLast;
	}

	public void setMoveLast(Move moveLast) {
		this.moveLast = moveLast;
	}

	public Move getMoveNew() {
		return moveNew;
	}

	public void setMoveNew(Move moveNew) {
		this.moveNew = moveNew;
	}

	public int getCountClick() {
		return countClick;
	}

	public void setCountClick(int countClick) {
		this.countClick = countClick;
	}

	public ButtonHint getButtonHind() {
		return buttonHind;
	}

	public void setButtonHind(ButtonHint buttonHind) {
		this.buttonHind = buttonHind;
	}

	public ButtonPause getBtPause() {
		return btPause;
	}

	public void setBtPause(ButtonPause btPause) {
		this.btPause = btPause;
	}

	public BackGround getBackGround() {
		return backGround;
	}

	public void setBackGround(BackGround backGround) {
		this.backGround = backGround;
	}

	public GamePanel getGame() {
		return game;
	}

	public void setGame(GamePanel game) {
		this.game = game;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

}
