package week_3_Genetic_Queens;

import java.util.ArrayList;
import java.util.List;

public class Node {

	int n;
	List<Integer> state;
	int heuristic;

	public Node(int n) {
		this.n = n;
		this.state = new ArrayList<Integer>();

		while (state.size() < n) {
			state.add(RandomSingleton.getInstance().getRd().nextInt(n));
		}

		this.heuristic = heuristic();
	}

	public Node(int n, List<Integer> state) {
		this.n = n;
		this.state = state;
		this.heuristic = heuristic();
	}

	public int heuristic() {
		int horizontal = 0;
		int diagonal = 0;

		for (int i = 0; i < state.size(); i++)
			for (int j = 0; j < state.size(); j++)
				if (j != i) {
					if (Math.abs(j - i) == Math.abs(state.get(j) - state.get(i)))
						diagonal++;
					if (state.get(j) == state.get(i))
						horizontal++;
				}

		return horizontal + diagonal;
	}

	public String toString() {
		return heuristic + "";
	}

}
