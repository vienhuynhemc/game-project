package week_3_Genetic_Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocalSearchGenetic {

	private int timeSleep;
	private Comparator<Node> c;
	private List<Node> community;
	private Node root;
	private int stt;
	private GUIGenetic gui;

	public LocalSearchGenetic(int number, int timeSleep) throws InterruptedException {
		this.timeSleep = timeSleep;
		stt = 1;

		// <!-- create -->
		root = new Node(number);
		community = new ArrayList<Node>();
		while (community.size() < 99)
			community.add(new Node(root.getState()));

		gui = new GUIGenetic();
		gui.setNode(community.get(0));
		gui.repaint();
		Thread.sleep(timeSleep);
		c = new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.heuristic - o2.heuristic;
			}
		};
	}

	public void run() throws InterruptedException {

		// <!-- run until searched a solution -->
		int count = 0;
		int best = 0;
		while (true) {
			// <!-- crossover -->
			crossover();

			// <!-- mutation -->
			mutation();

			// <!-- selective>
			selective();

			System.out.print("Generation: " + stt++ + " => ");
			for (Node node : community) {
				System.out.print("[" + node.heuristic + "]-");
			}
			System.out.println("--------------------------");
			for (int k = 0; k < 9; k++) {
				for (int k2 = 0; k2 < 9; k2++) {
					if (community.get(0).heuristicOneElement(k, k2) != 0) {
						System.out.println(k + " :" + k2);
					}
				}
			}
			// <!-- help draw-->
			gui.setNode(community.get(0));
			gui.repaint();
			Thread.sleep(timeSleep);
			// <!-- -- -->

			if (community.get(0).heuristic == 0)
				break;

			if (best != community.get(0).heuristic) {
				best = community.get(0).heuristic;
				count = 0;
			} else {
				count++;
				if (count == 100000) {
					community = new ArrayList<Node>();
					while (community.size() < 99)
						community.add(new Node(root.getState()));
				}
			}
		}

		gui.setNode(community.get(0));
		gui.repaint();

		System.out.println("Goal is: " + Arrays.deepToString(community.get(0).getState()));
		community.get(0).print();
	}

	public void crossover() {
		// <!-- get a father and a mother from list -->
		int posisionMother = RandomSingleton.getInstance().getRd().nextInt(community.size());
		int posisionFather = posisionMother;
		while (posisionFather == posisionMother) {
			posisionFather = RandomSingleton.getInstance().getRd().nextInt(community.size());
		}

		// <!-- father and mother>
		Node mother = community.get(posisionMother);
		Node father = community.get(posisionFather);
		// < ==> >

		// <!-- reproduction -->

		// <!-- 66.6% and 33.3% arranged in order -->
		int[][] childState1 = new int[9][9];
		int[][] childState2 = new int[9][9];
		int[][] childState3 = new int[9][9];
		int[][] childState4 = new int[9][9];

		int limit1 = 3;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (root.getRootArray()[i][j]) {
					childState1[i][j] = root.getState()[i][j];
					childState2[i][j] = root.getState()[i][j];
				} else {
					if (i < limit1) {
						childState1[i][j] = mother.getState()[i][j];
						childState2[i][j] = father.getState()[i][j];
					} else {
						childState2[i][j] = mother.getState()[i][j];
						childState1[i][j] = father.getState()[i][j];
					}
				}
				childState3[i][j] = mother.getState()[i][j];
				childState4[i][j] = father.getState()[i][j];
			}
		}

		community.add(new Node(childState1, root.getRootArray()));
		community.add(new Node(childState2, root.getRootArray()));

		// <!-- ~5% position random and swap among mother and father -->
		int count = 0;

		while (count < 5) {
			int rowPosision = RandomSingleton.getInstance().getRd().nextInt(9);
			int colPosision = RandomSingleton.getInstance().getRd().nextInt(9);
			while (root.getRootArray()[rowPosision][colPosision]) {
				rowPosision = RandomSingleton.getInstance().getRd().nextInt(9);
				colPosision = RandomSingleton.getInstance().getRd().nextInt(9);
			}
			int value = childState3[rowPosision][colPosision];
			childState3[rowPosision][colPosision] = childState4[rowPosision][colPosision];
			childState4[rowPosision][colPosision] = value;
			count++;
		}

		community.add(new Node(childState3, root.getRootArray()));
		community.add(new Node(childState4, root.getRootArray()));
	}

	public void mutation() {

		int index = RandomSingleton.getInstance().getRd().nextInt(community.size());

		int[][] childState = new int[9][9];

		for (int j = 0; j < childState.length; j++) {
			for (int j2 = 0; j2 < childState.length; j2++) {
				childState[j][j2] = community.get(index).getState()[j][j2];
			}
		}

		int rowPosision = RandomSingleton.getInstance().getRd().nextInt(9);
		int colPosision = RandomSingleton.getInstance().getRd().nextInt(9);
		while (root.getRootArray()[rowPosision][colPosision]) {
			rowPosision = RandomSingleton.getInstance().getRd().nextInt(9);
			colPosision = RandomSingleton.getInstance().getRd().nextInt(9);
		}
		List<Integer> listWay = root.getListInteger(rowPosision, colPosision);
		childState[rowPosision][colPosision] = listWay
				.get(RandomSingleton.getInstance().getRd().nextInt(listWay.size()));
		community.add(new Node(childState, root.getRootArray()));

		int[][] childState1 = new int[9][9];

		int indexx = RandomSingleton.getInstance().getRd().nextInt(community.size());
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				childState1[i][j] = community.get(indexx).getState()[i][j];
			}
		}

		Node node1 = new Node(childState1, root.getRootArray());

		switch (RandomSingleton.getInstance().getRd().nextInt(3)) {
		case 0:
			node1.mutaionOneGenCol();
			break;
		case 1:
			node1.mutaionOneGenCross();
			break;
		case 2:
			node1.mutaionOneGenRow();
			break;
		default:
			break;
		}

		node1.mutaionOneGenRow();

		community.add(node1);
	}

	public void selective() {
		// <!-- sort -->
		Collections.sort(community, c);

		// <!-- eliminate individuals to have 100 individuals -->
		while (community.size() > 100)
			community.remove(community.size() - 1);

		// <!-- retain only 90% of the qualified individuals of the population, the
		// remaining 10% will be offset by new individuals -->
		for (int i = community.size() * 90 / 100; i < community.size(); i++) {
			community.set(i, new Node(root.getState()));
		}
	}

}
