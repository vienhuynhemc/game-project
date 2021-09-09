package week_3_HellClimping_Queens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class LocalSearch {

	private int n;
	private GUIHillClimping gui;
	private int timeSleep;
	private int stt;

	public LocalSearch(int n, int timeSleep) throws IOException {
		stt = 1;
		this.n = n;
		this.timeSleep = timeSleep;
		gui = new GUIHillClimping(this);
	}

	public int heuristic(Node node) {
		return node.heuristic();
	}

	public int tryMovingOneQueen(Node node, int x, int y) {
		int oldValue = node.state.get(y);

		node.state.set(y, x);

		int newHeuristic = heuristic(node);

		node.state.set(y, oldValue);

		return newHeuristic;
	}

	public SortedMap<Integer, Node> generateNeighbours(Node node) {

		TreeMap<Integer, Node> sortedMap = new TreeMap<Integer, Node>();

		for (int i = n - 1; i >= 0; i--)
			for (int j = n - 1; j >= 0; j--) {
				List<Integer> newState = new ArrayList<Integer>();

				for (Integer value : node.state)
					newState.add(value);

				newState.set(i, j);
				sortedMap.put(tryMovingOneQueen(node, j, i), new Node(n, newState));
			}

		return sortedMap;
	}

	public void run() throws InterruptedException {

		Node initial = new Node(n);

		// <!-- help draw-->
		gui.setState(initial.state);
		gui.repaint(35, 59, 450, 450);
		Thread.sleep(timeSleep);
		// <!-- -- -->

		if (heuristic(initial) == 0) {
			System.out.println("Goal is: " + initial.state);
			print(initial);
			return;
		}

		System.out.println("Initial state is: " + initial.state + " [Heuristic: " + heuristic(initial) + "]");

		Node node = initial;

		SortedMap<Integer, Node> neighbours = generateNeighbours(node);

		int bestHeuristic = neighbours.firstKey();

		while (bestHeuristic < heuristic(node)) {
			node = neighbours.get(bestHeuristic);
			System.out.println("STT: " + stt++ + " => " + node.state + " [Heuristic: " + heuristic(node) + "]");

			// <!-- help draw-->
			gui.setState(node.state);
			gui.repaint(35, 59, 450, 450);
			Thread.sleep(timeSleep);
			// <!-- -- -->

			neighbours = generateNeighbours(node);
			bestHeuristic = neighbours.firstKey();
		}

		if (heuristic(node) == 0) {
			System.out.println("Goal is: " + node.state);
			print(node);
		} else {
			System.out.println(
					"Cannot find goal state! Best state is: " + node.state + " [Heuristic: " + heuristic(node) + "]");
			System.out.println("------------------------------------------");

			// <!-- not result ? => run again>
			Thread.sleep(timeSleep);
			run();
		}

	}

	public void print(Node initial) {
		int[][] array = new int[n][n];
		for (int i = 0; i < array.length; i++) {
			array[initial.state.get(i)][i] = 1;
		}

		int size = 4 * array.length;
		System.out.print("-");
		for (int j = 0; j < size; j++) {
			System.out.print("-");
		}
		System.out.println();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (j == 0)
					System.out.print("| ");
				if (array[i][j] == 0)
					System.out.print(" ");
				else
					System.out.print("X");
				if (j == array.length - 1)
					System.out.print(" |");
				else
					System.out.print(" | ");
			}
			System.out.println();
			for (int j = 0; j < size; j++) {
				System.out.print("-");
			}
			System.out.print("-");
			System.out.println();
		}
	}

	public int getN() {
		return n;
	}

}
