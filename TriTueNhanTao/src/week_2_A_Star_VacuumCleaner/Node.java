package week_2_A_Star_VacuumCleaner;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int[][] state;
	Location positionVacuum;
	Location positionTrash;
	private int move;
	private List<Node> neightbours;
	private Node parent;
	private int level;
	double heuristic;

	public Node(int[][] state, Location positionVacuum, Location positionTrash, int move, List<Node> neightbours,
			Node parent, int level) {
		this.state = state;
		this.positionVacuum = positionVacuum;
		this.positionTrash = positionTrash;
		this.move = move;
		this.neightbours = neightbours;
		this.parent = parent;
		this.level = level;

		this.heuristic = heuristic();
	}

	public void setTrash(int x, int y) {
		positionTrash.setX(x);
		positionTrash.setY(y);
		this.heuristic = heuristic();
	}

	public void getNeighbours(boolean[][] arrayBoolean, int[][] array, Location trash) {
		List<Integer> arrayMove = Board.getArrayMove(this.positionVacuum, trash, array, arrayBoolean);
		for (int i = 0; i < arrayMove.size(); i++) {
			Location newPositionVacuum = new Location(0, 0);
			newPositionVacuum.setX(this.positionVacuum.getX());
			newPositionVacuum.setY(this.positionVacuum.getY());

			switch (arrayMove.get(i)) {
			case Board.DIR_LEFT:
				Board.move_left(newPositionVacuum);
				break;
			case Board.DIR_RIGHT:
				Board.move_right(newPositionVacuum);
				break;
			case Board.DIR_UP:
				Board.move_up(newPositionVacuum);
				break;
			case Board.DIR_DOWN:
				Board.move_down(newPositionVacuum);
				break;
			}

			int[][] newState = new int[state.length][state.length];
			for (int j = 0; j < newState.length; j++) {
				for (int j2 = 0; j2 < newState.length; j2++) {
					newState[i][j] = state[i][j];
				}
			}
			newState[positionVacuum.getX()][positionVacuum.getY()] = 0;
			newState[newPositionVacuum.getX()][newPositionVacuum.getY()] = 10;

			neightbours.add(new Node(newState, newPositionVacuum, trash, arrayMove.get(i),
					new ArrayList<Node>(), this, level + 1));
		}
	}

	public double heuristic() {
		int width = Math.abs(positionTrash.getY() - positionVacuum.getY());
		int height = Math.abs(positionTrash.getX() - positionVacuum.getX());
		return Math.sqrt(width * width + height * height);
	}

	public int[][] getState() {
		return state;
	}

	public void setState(int[][] state) {
		this.state = state;
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
