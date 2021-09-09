package week_3_Genetic_Queens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocalSearchGenetic {

	private int n;
	private Comparator<Node> c;
	private List<Node> community;
	private int stt;

	public LocalSearchGenetic(int n) {
		this.n = n;
		stt = 1;

		// <!-- create -->
		community = new ArrayList<Node>();
		while (community.size() < 99)
			community.add(new Node(n));

		c = new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.heuristic - o2.heuristic;
			}
		};
	}

	public void run() throws IOException {

		// <!-- run until searched a solution -->
		while (true) {

			// <!-- crossover -->
			crossover();

			// <!-- mutation -->
			mutation();

			// <!-- selective>
			selective();

			System.out.print("Generation: " + stt++ + " => ");
			System.out.print("Best: " + community.get(0).heuristic + "# ");
			for (int i = 0; i < community.size(); i++) {
				System.out.print("[" + community.get(0) + "]-");
			}
			System.out.println();
			System.out.println("--------------------------");

			if (community.get(0).heuristic == 0)
				break;
		}

		System.out.println("Goal is: " + community.get(0).state);
		System.out.println("--------------------------");
		System.out.println("See for details => See matrix.txt :3");
		outputFile();
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

		// <!-- 70% and 30% arranged in order -->
		List<Integer> childState1 = new ArrayList<Integer>();
		List<Integer> childState2 = new ArrayList<Integer>();
		List<Integer> childState3 = new ArrayList<Integer>();
		List<Integer> childState4 = new ArrayList<Integer>();
		List<Integer> childState5 = new ArrayList<Integer>();
		List<Integer> childState6 = new ArrayList<Integer>();

		int limit1 = n * 70 / 100;
		int limit2 = n * 30 / 100;

		for (int i = 0; i < n; i++) {

			if (i < limit1) {
				childState1.add(mother.state.get(i));
				childState2.add(father.state.get(i));
			} else {
				childState1.add(father.state.get(i));
				childState2.add(mother.state.get(i));
			}

			if (i < limit2) {
				childState3.add(mother.state.get(i));
				childState4.add(father.state.get(i));
			} else {
				childState3.add(father.state.get(i));
				childState4.add(mother.state.get(i));
			}

			childState5.add(mother.state.get(i));
			childState6.add(father.state.get(i));
		}

		community.add(new Node(n, childState1));
		community.add(new Node(n, childState2));
		community.add(new Node(n, childState3));
		community.add(new Node(n, childState4));

		// <!-- 5% position random and swap among mother and father -->
		List<Integer> listPosisionRandom = new ArrayList<Integer>();

		int limit3 = n * 5 / 100;

		while (listPosisionRandom.size() < limit3) {
			int posision = RandomSingleton.getInstance().getRd().nextInt(n);
			if (!listPosisionRandom.contains(posision))
				listPosisionRandom.add(posision);
		}

		for (Integer i : listPosisionRandom) {
			int value = childState5.get(i);
			childState5.set(i, childState6.get(i));
			childState6.set(i, value);
		}

		community.add(new Node(n, childState5));
		community.add(new Node(n, childState6));

	}

	public void mutation() {
		for (int i = 0; i < 5; i++) {
			int index = RandomSingleton.getInstance().getRd().nextInt(community.size());

			List<Integer> childState = new ArrayList<Integer>();
			List<Integer> childState2 = new ArrayList<Integer>();

			for (int j = 0; j < community.get(index).state.size(); j++) {
				childState.add(community.get(index).state.get(j));
				childState2.add(community.get(index).state.get(j));
			}

			childState.set(RandomSingleton.getInstance().getRd().nextInt(n),
					RandomSingleton.getInstance().getRd().nextInt(n));
			childState2.set(RandomSingleton.getInstance().getRd().nextInt(n),
					RandomSingleton.getInstance().getRd().nextInt(n));

			community.add(new Node(n, childState));
			community.add(new Node(n, childState2));
		}

	}

	public void selective() {

		// <!-- sort -->
		Collections.sort(community, c);

		// <!-- eliminate individuals to have 100 individuals -->
		while (community.size() > 100)
			community.remove(community.size() - 1);

		// <!-- retain only 90% of the qualified individuals of the population, the
		// remaining 40% will be offset by new individuals -->
		for (int i = community.size() * 90 / 100; i < community.size(); i++) {
			community.set(i, new Node(n));
		}

	}

	public void outputFile() throws IOException {
		BufferedWriter w = new BufferedWriter(new FileWriter(new File(
				"E:\\Eclipse Project\\HocKy3_2020_2021\\TriTueNhanTao\\src\\week_3_Genetic_Queens\\matrix.txt")));
		int[][] array = new int[n][n];
		for (int i = 0; i < array.length; i++) {
			array[community.get(0).state.get(i)][i] = 1;
		}

		int size = 4 * array.length;
		w.write("-");
		for (int j = 0; j < size; j++) {
			w.write("-");
		}
		w.newLine();
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (j == 0)
					w.write("| ");
				if (array[i][j] == 0)
					w.write(" ");
				else
					w.write("X");
				if (j == array.length - 1)
					w.write(" |");
				else
					w.write(" | ");
			}
			w.newLine();
			for (int j = 0; j < size; j++) {
				w.write("-");
			}
			w.write("-");
			w.newLine();
		}
		w.close();
	}

}
