package week_2_bfs_dfs_queens;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int n;
	private List<Integer> state;
	private List<Node> neighbours;

	public Node(int n) {
		this.n = n;
		this.state = new ArrayList<Integer>();
		this.neighbours = new ArrayList<Node>();
	}

	public Node(int n, List<Integer> state) {
		this.n = n;
		this.state = state;
		this.neighbours = new ArrayList<Node>();
	}

	public void addNeighbours(Node neighbourNode) {
		this.neighbours.add(neighbourNode);
	}

	public boolean isValid(List<Integer> state) {
		if (state.size() == 1)
			return true;

		// <!-- row -->
		for (int i = 0; i < state.size() - 1; i++)
			if (state.get(i) == state.get(state.size() - 1))
				return false;

		// <!-- cross -->
		for (int i = 0; i < state.size() - 1; i++)
			if (Math.abs(i - (state.size() - 1)) == Math.abs(state.get(i) - state.get(state.size() - 1)))
				return false;

		return true;
	}

	private List<Integer> place(int x) {
		List<Integer> testList = new ArrayList<Integer>();

		for (Integer location : state)
			testList.add(location);

		testList.add(x);

		return isValid(testList) ? testList : null;
	}

	public List<Node> getNeighbours() {
		if (state.size() == n)
			return null;

		List<Node> neighboursList = new ArrayList<Node>();

		for (int i = 0; i < n; i++) {
			List<Integer> validState = place(i);

			if (validState != null)
				neighboursList.add(new Node(n, validState));
		}
		return neighboursList;
	}

	public int getLocationX(int value) {
		for (int i = 0; i < state.size(); i++)
			if (state.get(i) == value)
				return i;

		return -1;
	}

	// <!-- Getter and setter >

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public List<Integer> getState() {
		return state;
	}

	public void setState(List<Integer> state) {
		this.state = state;
	}

	public void setNeighbours(List<Node> neighbours) {
		this.neighbours = neighbours;
	}

}
