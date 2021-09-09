package week_3_Minimax;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Minimax {

	public static void action(Node root) {
		minimax(root);
	}

	public static int minimax(Node root) {
		if (root.getNeightbours().size() == 0)
			return root.getValue();
		else {
			for (Node node : root.getNeightbours()) {
				int newValue = minimax(node);
				if (root.getValue() == null)
					root.setValue(newValue);
				else {
					if (root.getName() == Node.MAX) {
						if (newValue > root.getValue())
							root.setValue(newValue);
					} else {
						if (newValue < root.getValue())
							root.setValue(newValue);
					}
				}
			}
			return root.getValue();
		}
	}

	public static void printGameTree(Node node) {
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Node> qNode = new LinkedList<Node>();
		List<Integer> newLine = new LinkedList<Integer>();
		newLine.add(1);
		q.add(node.getValue());
		qNode.add(node);
		while (!qNode.isEmpty()) {
			int count = 0;
			int size = newLine.get(newLine.size() - 1);
			while (size > 0) {
				size--;
				Node n = qNode.poll();
				for (Node childNode : n.getNeightbours()) {
					count++;
					qNode.add(childNode);
					q.add(childNode.getValue());
				}
			}
			if (count != 0) {
				newLine.add(count);
			}
		}
		for (int i = 0; i < newLine.size(); i++) {
			int size = newLine.get(i);
			while (size > 0) {
				System.out.print(q.poll() + " ");
				size--;
			}
			System.out.println();
		}
	}

	public static Node createRoot() {
		Node root = new Node(Node.MAX);
		Node n1 = new Node(Node.MIN, 4);
		Node n2 = new Node(Node.MIN, 1);
		Node n3 = new Node(Node.MAX);
		n3.getNeightbours().add(n1);
		n3.getNeightbours().add(n2);
		Node n4 = new Node(Node.MAX, 5);
		Node n5 = new Node(Node.MAX, 1);
		Node n6 = new Node(Node.MIN);
		n6.getNeightbours().add(n4);
		n6.getNeightbours().add(n5);
		Node n7 = new Node(Node.MIN, 1);
		Node n8 = new Node(Node.MAX);
		n8.getNeightbours().add(n6);
		n8.getNeightbours().add(n7);
		Node n9 = new Node(Node.MAX, 1);
		Node n10 = new Node(Node.MIN);
		n10.getNeightbours().add(n3);
		n10.getNeightbours().add(n8);
		n10.getNeightbours().add(n9);
		root.getNeightbours().add(n10);
		Node n11 = new Node(Node.MAX, 1);
		Node n12 = new Node(Node.MAX, 8);
		Node n13 = new Node(Node.MAX, 6);
		Node n14 = new Node(Node.MAX, 1);
		Node n15 = new Node(Node.MIN);
		Node n16 = new Node(Node.MIN);
		Node n17 = new Node(Node.MIN, 9);
		n15.getNeightbours().add(n11);
		n15.getNeightbours().add(n12);
		n16.getNeightbours().add(n13);
		n16.getNeightbours().add(n14);
		Node n18 = new Node(Node.MAX);
		Node n19 = new Node(Node.MIN);
		n18.getNeightbours().add(n15);
		n18.getNeightbours().add(n17);
		n18.getNeightbours().add(n16);
		n19.getNeightbours().add(n18);
		root.getNeightbours().add(n19);
		Node n20 = new Node(Node.MIN, 1);
		Node n21 = new Node(Node.MIN, 8);
		Node n22 = new Node(Node.MIN);
		Node n23 = new Node(Node.MIN, 7);
		Node n24 = new Node(Node.MIN, 4);
		Node n25 = new Node(Node.MAX, 2);
		Node n26 = new Node(Node.MAX, 5);
		Node n27 = new Node(Node.MAX, 7);
		n22.getNeightbours().add(n25);
		n22.getNeightbours().add(n26);
		n22.getNeightbours().add(n27);
		Node n28 = new Node(Node.MAX);
		Node n29 = new Node(Node.MAX);
		n28.getNeightbours().add(n20);
		n28.getNeightbours().add(n21);
		n28.getNeightbours().add(n22);
		n29.getNeightbours().add(n23);
		n29.getNeightbours().add(n24);
		Node n30 = new Node(Node.MIN);
		n30.getNeightbours().add(n28);
		n30.getNeightbours().add(n29);
		root.getNeightbours().add(n30);
		return root;
	}

}
