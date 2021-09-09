package week_3_Minimax;

import java.util.ArrayList;
import java.util.List;

public class Node {

	public static final int MIN = 0;
	public static final int MAX = 1;

	private int name;
	private Integer value;
	private List<Node> neightbours;

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

}
