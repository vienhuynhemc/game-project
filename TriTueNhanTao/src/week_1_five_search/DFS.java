package week_1_five_search;

import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class DFS {

	public void dfsUsingStack(Node initial, int goal) {

		// <!-- Check 1-->
		if (initial.getState() == goal)
			System.out.println("Vị trí ban đầu = vị trí đích = " + goal);
		else {

			Node node = null;
			Stack<Node> stack = new Stack<Node>();
			initial.setVisited(true);

			Comparator<Node> c = new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return o2.getState() - o1.getState();
				}
			};
			Collections.sort(initial.getNeighbours(), c);
			for (Node n : initial.getNeighbours()) {
				// <!-- Check 2-->
				n.setParent(initial);
				stack.push(n);
			}
			if (stack.peek().getState() == goal)
				node = stack.pop();

			// <!-- not null ? --> goal --> print result -->
			if (node == null) {
				while (!stack.isEmpty()) {
					Node n = stack.pop();
					if (n.getState() == goal) {
						node = n;
						break;
					} else {
						Collections.sort(n.getNeighbours(), c);
						for (Node childNode : n.getNeighbours())
							if (!childNode.isVisited()) {
								childNode.setParent(n);
								childNode.setVisited(true);
								stack.add(childNode);
							}

						if (node != null)
							break;
					}
				}
			}

			// <!-- Print result -->
			if (node != null) {
				if (node.getState() == goal) {
					String s = "";
					while (node != initial) {
						s = s.length() == 0 ? node.getState() + "" : node.getState() + " --> " + s;
						node = node.getParent();
					}
					System.out.println(initial.getState() + " --> " + s);
				}
			} else
				System.out.println("Không có đư�?ng đi từ: " + initial.getState() + " --> " + goal);
		}
	}

}
