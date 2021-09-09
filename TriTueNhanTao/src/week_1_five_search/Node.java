package week_1_five_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node> {
	private int state;
	private boolean visited;
	private List<Node> neighbours;// <!-- child node without length from this to that>
	private Map<Node, Integer> neighboursMap; // <!-- child node and length from this to that>
	private Node parent;
	private int way;// <!-- Length of way -->
	private String name;

	public Node(int state) {
		this.state = state;
		this.neighbours = new ArrayList<Node>();
		this.parent = null;
		this.way = 0;
		neighboursMap = new HashMap<Node, Integer>();
		name = "";
	}

	public void reset() {
		setVisited(false);
		for (Node neighbour : neighbours)
			neighbour.reset();

	}

	public void addNeighbours(Node neighbourNode) {
		this.neighbours.add(neighbourNode);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addNeighboursMap(Node node, int space) {
		neighboursMap.put(node, space);
	}

	public List<Node> getNeighbours() {
		return neighbours;
	}

	public int getState() {
		return state;
	}

	public Node getParent() {
		return parent;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public Map<Node, Integer> getNeighboursMap() {
		return neighboursMap;
	}

	public void setNeighboursMap(Map<Node, Integer> neighboursMap) {
		this.neighboursMap = neighboursMap;
	}

	public void setNeighbours(List<Node> neighbours) {
		this.neighbours = neighbours;
	}

	@Override
	public int compareTo(Node o) {
		return this.getState() - o.getState();
	}

	public String toString() {
		return "[" + name + " - " + getState() + " - " + visited + " - " + way + "]";
	}

}
