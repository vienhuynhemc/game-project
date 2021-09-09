package week_2_A_Star_VacuumCleaner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class A_star {

	private int timeSleep;
	private Comparator<Node> c;
	private Node root;
	private Location positionVacuum;
	private Location positionTrash;
	private GUI gui;
	private List<Integer> way;
	int trash;
	boolean[][] arrayBoolean;

	public A_star(int size, int wall, int trash, int timeSleep) throws IOException, InterruptedException {
		this.timeSleep = timeSleep;
		this.trash = trash;
		positionVacuum = new Location(RandomSingleton.getInstance().getRd().nextInt(size),
				RandomSingleton.getInstance().getRd().nextInt(size));
		positionTrash = new Location(0, 0);
		int[][] array = new int[size][size];
		array[positionVacuum.getX()][positionVacuum.getY()] = 10;
		int count = 0;
		while (count < trash) {
			int x = RandomSingleton.getInstance().getRd().nextInt(size);
			int y = RandomSingleton.getInstance().getRd().nextInt(size);
			while (array[x][y] != 0) {
				x = RandomSingleton.getInstance().getRd().nextInt(size);
				y = RandomSingleton.getInstance().getRd().nextInt(size);
			}
			array[x][y] = -1;
			count++;
		}
		count = 0;
		while (count < wall) {
			int x = RandomSingleton.getInstance().getRd().nextInt(size);
			int y = RandomSingleton.getInstance().getRd().nextInt(size);
			while (array[x][y] != 0) {
				x = RandomSingleton.getInstance().getRd().nextInt(size);
				y = RandomSingleton.getInstance().getRd().nextInt(size);
			}
			array[x][y] = 5;
			count++;
		}

		root = new Node(array, positionVacuum, positionTrash, -1, new ArrayList<Node>(), null, 0);
		gui = new GUI(root.getState(), positionVacuum.getX(), positionVacuum.getY());
		Thread.sleep(timeSleep);
		gui.setState(root.getState());
		gui.repaint();

		c = new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				int a = 0;
				int b = 0;
				if (o1.heuristic > o2.heuristic)
					a = 1;
				else if (o1.heuristic < o2.heuristic)
					b = 1;
				return (o1.getLevel() + a) - (o2.getLevel() + b);
			}
		};
		way = new ArrayList<Integer>();
	}

	public void run() throws InterruptedException {
		while (trash != 0) {

			getTrashNearVacuum();
			root.setTrash(positionTrash.getX(), positionTrash.getY());
			root.setNeightbours(new ArrayList<Node>());
			root.setMove(-1);

			arrayBoolean = new boolean[root.getState().length][root.getState().length];
			for (int i = 0; i < arrayBoolean.length; i++) {
				for (int j = 0; j < arrayBoolean.length; j++) {
					if (root.getState()[i][j] != 0 && root.getState()[i][j] != -1)
						arrayBoolean[i][j] = true;
				}
			}

			List<Node> arrayState = new ArrayList<Node>();
			arrayState.add(root);
			System.out.println("Vacuum: " + positionVacuum + " ---" + "Trash: " + positionTrash);
			int count = 1;
			while (true) {
				Node node = arrayState.get(0);
				arrayState.remove(0);
				node.getNeighbours(arrayBoolean, root.getState(), positionTrash);
				List<Node> neighbours = node.getNeightbourss();

				System.out.println("STT: " + count++ + " " + node.positionVacuum + " : " + node.positionTrash);

				for (int i = 0; i < neighbours.size(); i++) {
					arrayState.add(neighbours.get(i));
					System.out.println(
							"---> " + neighbours.get(i).positionVacuum + " : " + neighbours.get(i).positionTrash);
					arrayBoolean[neighbours.get(i).positionVacuum.getX()][neighbours.get(i).positionVacuum
							.getY()] = true;

				}
				Collections.sort(arrayState, c);
				if (arrayState.size() == 0)
					break;
				if (arrayState.get(0).heuristic == 0)
					break;
			}

			if (arrayState.size() > 0) {
				getWay(arrayState.get(0));
				gui.way(way, positionVacuum);
				gui.isDrawWay = true;
				gui.repaint();
				Thread.sleep(timeSleep);
				System.out.println("===> way: " + way.size());
				while (way.size() > 0) {
					root.getState()[root.positionVacuum.getX()][root.positionVacuum.getY()] = 0;
					switch (way.get(0)) {
					case Board.DIR_LEFT:
						System.out.print("left -> ");
						Board.move_left(root.positionVacuum);
						break;
					case Board.DIR_RIGHT:
						System.out.print("right -> ");
						Board.move_right(root.positionVacuum);
						break;
					case Board.DIR_UP:
						System.out.print("up -> ");
						Board.move_up(root.positionVacuum);
						break;
					case Board.DIR_DOWN:
						System.out.print("down -> ");
						Board.move_down(root.positionVacuum);
						break;
					}
					root.getState()[root.positionVacuum.getX()][root.positionVacuum.getY()] = 10;
					gui.setState(root.getState());
					gui.repaint();
					Thread.sleep(timeSleep);
					way.remove(0);
				}
				System.out.println();
				gui.isDrawWay = false;
				root.getState()[positionTrash.getX()][positionTrash.getY()] = 10;
				positionVacuum.setX(root.positionVacuum.getX());
				positionVacuum.setY(root.positionVacuum.getY());
			} else {
				root.getState()[positionTrash.getX()][positionTrash.getY()] = 0;
				gui.igNore(positionTrash.getX(), positionTrash.getY());
			}
			System.out.println("----------------------------------------");
			trash--;
			gui.repaint();
			Thread.sleep(timeSleep);
		}
		gui.isDrawWay = false;
		gui.repaint();
		Thread.sleep(timeSleep);
		System.out.println("----------- CLEAN -------- ");
		System.out.println("----------GO BACK -----------");

		positionTrash.setX(gui.xStartt);
		positionTrash.setY(gui.yStartt);

		root.setTrash(positionTrash.getX(), positionTrash.getY());
		root.setNeightbours(new ArrayList<Node>());
		root.setMove(-1);

		arrayBoolean = new boolean[root.getState().length][root.getState().length];
		for (int i = 0; i < arrayBoolean.length; i++) {
			for (int j = 0; j < arrayBoolean.length; j++) {
				if (root.getState()[i][j] != 0)
					arrayBoolean[i][j] = true;
			}
		}

		List<Node> arrayState = new ArrayList<Node>();
		arrayState.add(root);
		while (true) {
			Node node = arrayState.get(0);
			arrayState.remove(0);
			node.getNeighbours(arrayBoolean, root.getState(), positionTrash);
			List<Node> neighbours = node.getNeightbourss();

			for (int i = 0; i < neighbours.size(); i++) {
				arrayState.add(neighbours.get(i));
				arrayBoolean[neighbours.get(i).positionVacuum.getX()][neighbours.get(i).positionVacuum.getY()] = true;
			}
			Collections.sort(arrayState, c);
			if (arrayState.size() == 0)
				break;
			if (arrayState.get(0).heuristic == 0)
				break;
		}

		getWay(arrayState.get(0));
		gui.way(way, positionVacuum);
		gui.isDrawWay = true;
		gui.repaint();
		Thread.sleep(timeSleep);
		while (way.size() > 0) {
			root.getState()[root.positionVacuum.getX()][root.positionVacuum.getY()] = 0;
			switch (way.get(0)) {
			case Board.DIR_LEFT:
				Board.move_left(root.positionVacuum);
				break;
			case Board.DIR_RIGHT:
				Board.move_right(root.positionVacuum);
				break;
			case Board.DIR_UP:
				Board.move_up(root.positionVacuum);
				break;
			case Board.DIR_DOWN:
				Board.move_down(root.positionVacuum);
				break;
			}
			root.getState()[root.positionVacuum.getX()][root.positionVacuum.getY()] = 10;
			gui.setState(root.getState());
			gui.repaint();
			Thread.sleep(timeSleep);
			way.remove(0);
		}
		gui.isDrawWay = false;
		root.getState()[positionTrash.getX()][positionTrash.getY()] = 10;
		positionVacuum.setX(root.positionVacuum.getX());
		positionVacuum.setY(root.positionVacuum.getY());

		System.out.println("----------------------------------------");
		trash--;
		gui.repaint();
		Thread.sleep(timeSleep);
		gui.isDrawWay = false;
		gui.repaint();
		Thread.sleep(timeSleep);
		System.out.println("---=END=--");
	}

	public void getWay(Node node) {
		while (node.getParent() != null) {
			way.add(0, node.getMove());
			node = node.getParent();
		}
	}

	public void getTrashNearVacuum() {
		double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i < root.getState().length; i++) {
			for (int j = 0; j < root.getState().length; j++) {
				if (root.getState()[i][j] == -1) {
					int x = positionTrash.getX();
					int y = positionTrash.getY();
					positionTrash.setX(i);
					positionTrash.setY(j);
					double heu = heuristic();
					if (heu < min) {
						min = heu;
					} else {
						positionTrash.setX(x);
						positionTrash.setY(y);
					}
				}
			}
		}
	}

	public double heuristic() {
		int width = Math.abs(positionTrash.getY() - positionVacuum.getY());
		int height = Math.abs(positionTrash.getX() - positionVacuum.getX());
		return Math.sqrt(width * width + height * height);
	}

}
