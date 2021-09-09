package week_2_A_Star_NPuzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class A_star {

	private int timeSleep;
	private Comparator<Node> c;
	private Node root;
	private Location positionSpace;
	private GUI gui;
	private List<Integer> way;
	private List<int[][]> overNode;

	public A_star(int size, int timeSleep) throws IOException, InterruptedException {
		this.timeSleep = timeSleep;

		int[][] array = new int[size][size];
		int count = 1;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				array[i][j] = count;
				count++;
				if (i == array.length - 1 && j == array.length - 1)
					array[i][j] = -1;
			}
		}

		positionSpace = new Location(array.length - 1, array.length - 1);
		root = new Node(array, positionSpace, -1, new ArrayList<Node>(), null, 0);
		gui = new GUI(root.getState());
		Board.mixArray(root.getState(), root.getPositionSpace());
		Thread.sleep(timeSleep);
		gui.setState(root.getState());
		gui.repaint();

		c = new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.a_star()- o2.a_star();
			}
		};
		way = new ArrayList<Integer>();
		overNode = new ArrayList<int[][]>();
	}

	public void run() throws InterruptedException {
		List<Node> arrayState = new ArrayList<Node>();
		arrayState.add(root);
		while (arrayState.get(0).heuristic != 0 || !isGoal(arrayState.get(0).getState())) {

			Node node = arrayState.get(0);
			arrayState.remove(0);
			System.out.println("-----------------------------------");
			System.out.println(
					"Heuristic: " + node.heuristic + ",Level: " + node.getLevel() + ", Astar: " + node.a_star());
			System.out.println("sizeOver: " + overNode.size() + ", size: " + arrayState.size());
			printArray(node.getState());
			System.out.println("-----------------------------------");

			node.getNeighbours();
			List<Node> neighbours = node.getNeightbourss();

			for (int i = 0; i < neighbours.size(); i++) {
				if (!isLoop(neighbours.get(i).getState())) {
					overNode.add(neighbours.get(i).getState());
					arrayState.add(neighbours.get(i));
				}
			}

			Collections.sort(arrayState, c);
		}

		getWay(arrayState.get(0));
		System.out.println("===> way: " + way.size());
		while (way.size() > 0) {
			switch (way.get(0)) {
			case Board.DIR_LEFT:
				Board.move_left(root.getState(), root.getPositionSpace());
				break;
			case Board.DIR_RIGHT:
				Board.move_right(root.getState(), root.getPositionSpace());
				break;
			case Board.DIR_UP:
				Board.move_up(root.getState(), root.getPositionSpace());
				break;
			case Board.DIR_DOWN:
				Board.move_down(root.getState(), root.getPositionSpace());
				break;
			}
			gui.setState(root.getState());
			gui.repaint();
			Thread.sleep(timeSleep);
			way.remove(0);
		}

	}

	public void getWay(Node node) {
		while (node.getParent() != null) {
			way.add(0, node.getMove());
			node = node.getParent();
		}
	}

	public boolean isGoal(int[][] state) {
		if (state[state.length - 1][state.length - 1] == -1)
			return true;

		return false;
	}

	public void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean isLoop(int[][] state) {
		if (overNode.size() == 0)
			return false;
		for (int[][] array : overNode) {
			if (checkArray(array, state))
				return true;
		}

		return false;
	}

	public boolean checkArray(int[][] a, int[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if (a[i][j] != b[i][j])
					return false;
			}
		}
		return true;
	}
}
