package week_2_A_Star_NPuzzle;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int[][] state;
	private Location positionSpace;
	private int move;
	private List<Node> neightbours;
	private Node parent;
	private int level;
	int heuristic;

	public Node(int[][] state, Location positionSpace, int move, List<Node> neightbours, Node parent, int level) {
		this.state = state;
		this.positionSpace = positionSpace;
		this.move = move;
		this.neightbours = neightbours;
		this.parent = parent;
		this.level = level;

		this.heuristic = heuristic();
	}

	public void getNeighbours() {
		List<Integer> arrayMove = Board.getArrayMove(this.positionSpace, this.state);

		for (int i = 0; i < arrayMove.size(); i++) {

			int[][] newState = new int[state.length][state.length];
			Location newPositionSpace = new Location(0, 0);

			for (int k = 0; k < this.state.length; k++) {
				for (int l = 0; l < this.state.length; l++) {
					newState[k][l] = state[k][l];
					if (this.state[k][l] == -1) {
						newPositionSpace.setX(k);
						newPositionSpace.setY(l);
					}
				}
			}

			switch (arrayMove.get(i)) {
			case Board.DIR_LEFT:
				Board.move_left(newState, newPositionSpace);
				break;
			case Board.DIR_RIGHT:
				Board.move_right(newState, newPositionSpace);
				break;
			case Board.DIR_UP:
				Board.move_up(newState, newPositionSpace);
				break;
			case Board.DIR_DOWN:
				Board.move_down(newState, newPositionSpace);
				break;
			}

			neightbours.add(
					new Node(newState, newPositionSpace, arrayMove.get(i), new ArrayList<Node>(), this, level + 1));
		}
	}

	public int a_star() {
		return this.heuristic + this.level;
	}

	public int heuristic() {
		int result = 0;

		for (int i = 0; i < this.state.length; i++)
			for (int j = 0; j < this.state.length; j++)
				if (this.state[i][j] != -1)
					result += this.heuristicOneElement(i, j);

		return result;
	}

	public int heuristicOneElement(int i, int j) {
		int result = 0;

		for (int k = i; k < this.state.length; k++)
			for (int l = 0; l < this.state.length; l++)
				if ((k != i || l != j) && this.state[k][l] != -1) {
					if (k == i) {
						if (l > j)
							if (this.state[k][l] < this.state[i][j])
								result++;
					} else if (this.state[k][l] < this.state[i][j])
						result++;
				}

		return result;
	}

	public int[][] getState() {
		return state;
	}

	public void setState(int[][] state) {
		this.state = state;
	}

	public Location getPositionSpace() {
		return positionSpace;
	}

	public void setPositionSpace(Location positionSpace) {
		this.positionSpace = positionSpace;
	}

	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

	public List<Node> getNeightbourss() {
		return neightbours;
	}

	public void setNeightbours(List<Node> neightbours) {
		this.neightbours = neightbours;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
