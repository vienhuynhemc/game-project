package week_3_MinimaxAlphaBeta;

import java.util.ArrayList;
import java.util.List;

public class Node {

	public static final int MIN = 0;
	public static final int MAX = 1;

	private int name;
	private Integer value;
	private List<Node> neightbours;
	private Node parent;

	public Node(Node node) {
		this.name = node.name;
		this.value = node.value;
		this.neightbours = new ArrayList<Node>();
		for (Node i : node.getNeightbours()) {
			this.neightbours.add(new Node(i));
		}
		this.parent = node.getParent();
	}

	public Node(int name) {
		this.name = name;
		this.value = null;
		neightbours = new ArrayList<Node>();
	}

	public Node(int name, int value) {
		this.name = name;
		this.value = value;
		neightbours = new ArrayList<Node>();
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Node> getNeightbours() {
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

}
