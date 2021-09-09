package week_3_MinimaxAlphaBeta;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimaxAlphaBeta {

	public static void action(Node root) {
		minimaxAlphaBeta(root);
	}

	public static int minimaxAlphaBeta(Node root) {
		if (root.getNeightbours().size() == 0)
			return root.getValue();
		else {
			for (Node node : root.getNeightbours()) {
				int newValue = minimaxAlphaBeta(node);
				if (root.getValue() == null) {
					root.setValue(newValue);
				} else {
					if (root.getName() == Node.MAX) {
						if (newValue > root.getValue())
							root.setValue(newValue);
					} else {
						if (newValue < root.getValue())
							root.setValue(newValue);
					}
				}
				Node parentNode = getParentNode(root);
				if (parentNode != null) {
					if (parentNode.getName() == Node.MAX) {
						if (root.getName() == Node.MIN) {
							if (root.getValue() <= parentNode.getValue()) {
								break;
							}
						}
					} else {
						if (root.getName() == Node.MAX) {
							if (root.getValue() >= parentNode.getValue()) {
								break;
							}
						}
					}
				}
			}
			return root.getValue();
		}
	}

	public static Node getParentNode(Node root) {
		int limit = 0;
		Node node = new Node(root);
		while (node.getParent() != null) {
			limit++;
			if (node.getParent().getValue() != null) {
				node = node.getParent();
				break;
			}
			node = node.getParent();

		}
		if (node.getValue() != null) {
			if (limit == 0) {
				return null;
			}
			return node;
		} else {
			return null;
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
		n1.setParent(n3);
		n2.setParent(n3);
		Node n4 = new Node(Node.MAX, 5);
		Node n5 = new Node(Node.MAX);
		Node n31 = new Node(Node.MIN, 6);
		n5.getNeightbours().add(n31);
		n31.setParent(n5);
		Node n6 = new Node(Node.MIN);
		n6.getNeightbours().add(n4);
		n6.getNeightbours().add(n5);
		n4.setParent(n6);
		n5.setParent(n6);
		Node n7 = new Node(Node.MIN, 1);
		Node n8 = new Node(Node.MAX);
		n8.getNeightbours().add(n6);
		n8.getNeightbours().add(n7);
		n6.setParent(n8);
		n7.setParent(n8);
		Node n9 = new Node(Node.MAX, 1);
		Node n10 = new Node(Node.MIN);
		n10.getNeightbours().add(n3);
		n10.getNeightbours().add(n8);
		n10.getNeightbours().add(n9);
		n3.setParent(n10);
		n8.setParent(n10);
		n9.setParent(n10);
		root.getNeightbours().add(n10);
		n10.setParent(root);
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
		n11.setParent(n15);
		n12.setParent(n15);
		n13.setParent(n16);
		n14.setParent(n16);
		Node n18 = new Node(Node.MAX);
		Node n19 = new Node(Node.MIN);
		n18.getNeightbours().add(n15);
		n18.getNeightbours().add(n17);
		n18.getNeightbours().add(n16);
		n19.getNeightbours().add(n18);
		root.getNeightbours().add(n19);
		n15.setParent(n18);
		n17.setParent(n18);
		n16.setParent(n18);
		n18.setParent(n19);
		n19.setParent(root);
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
		n25.setParent(n22);
		n26.setParent(n22);
		n27.setParent(n22);
		Node n28 = new Node(Node.MAX);
		Node n29 = new Node(Node.MAX);
		n28.getNeightbours().add(n20);
		n28.getNeightbours().add(n21);
		n28.getNeightbours().add(n22);
		n29.getNeightbours().add(n23);
		n29.getNeightbours().add(n24);
		n20.setParent(n28);
		n21.setParent(n28);
		n22.setParent(n28);
		n23.setParent(n29);
		n24.setParent(n29);
		Node n30 = new Node(Node.MIN);
		n30.getNeightbours().add(n28);
		n30.getNeightbours().add(n29);
		n28.setParent(n30);
		n29.setParent(n30);
		root.getNeightbours().add(n30);
		n30.setParent(root);
		return root;
	}

	public static Node createRoot2() {
		Node A = new Node(Node.MAX);
		Node E = new Node(Node.MAX);
		Node F = new Node(Node.MAX);
		Node G = new Node(Node.MAX);
		Node H = new Node(Node.MAX);
		Node I = new Node(Node.MAX);
		Node J = new Node(Node.MAX);
		Node B = new Node(Node.MIN);
		Node C = new Node(Node.MIN);
		Node D = new Node(Node.MIN);
		Node K = new Node(Node.MIN, 2);
		Node L = new Node(Node.MIN, 3);
		Node M = new Node(Node.MIN, 5);
		Node N = new Node(Node.MIN, 20);
		Node P = new Node(Node.MIN, 0);
		Node Q = new Node(Node.MIN, 20);
		Node U = new Node(Node.MIN, 20);
		Node V = new Node(Node.MIN, 2);
		Node R = new Node(Node.MIN, 1);
		Node T = new Node(Node.MIN, 20);
		Node S = new Node(Node.MIN, 20);
		A.getNeightbours().add(B);
		A.getNeightbours().add(C);
		A.getNeightbours().add(D);
		B.getNeightbours().add(E);
		B.getNeightbours().add(F);
		E.getNeightbours().add(K);
		E.getNeightbours().add(L);
		F.getNeightbours().add(M);
		F.getNeightbours().add(N);
		C.getNeightbours().add(G);
		C.getNeightbours().add(H);
		G.getNeightbours().add(P);
		H.getNeightbours().add(Q);
		H.getNeightbours().add(U);
		D.getNeightbours().add(I);
		D.getNeightbours().add(J);
		I.getNeightbours().add(V);
		I.getNeightbours().add(R);
		J.getNeightbours().add(T);
		J.getNeightbours().add(S);
		K.setParent(E);
		L.setParent(E);
		M.setParent(F);
		N.setParent(F);
		P.setParent(G);
		Q.setParent(H);
		U.setParent(H);
		V.setParent(I);
		R.setParent(I);
		T.setParent(J);
		S.setParent(J);
		E.setParent(B);
		F.setParent(B);
		G.setParent(C);
		H.setParent(C);
		I.setParent(D);
		J.setParent(D);
		B.setParent(A);
		C.setParent(A);
		D.setParent(A);
		return A;
	}

}
