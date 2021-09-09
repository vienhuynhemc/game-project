package week_1_five_search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public void bfsUsingQueue(Node initial, int goal) {

		// <!-- Check 1-->
		if (initial.getState() == goal)
			System.out.println("Vị trí ban đầu = vị trí đích = " + goal);
		else {

			Node node = null;
			Queue<Node> queue = new LinkedList<Node>();
			initial.setVisited(true);

			Collections.sort(initial.getNeighbours());
			for (Node n : initial.getNeighbours()) {
				// <!-- Check 2-->
				n.setParent(initial);
				if (n.getState() == goal) {
					node = n;
					break;
				}
				n.setVisited(true);
				queue.add(n);
			}

			// <!-- not null ? --> goal --> print result -->
			if (node == null) {
				while (!queue.isEmpty()) {
					Node n = queue.poll();
					if (n.getState() == goal) {
						node = n;
						break;
					} else {
						Collections.sort(n.getNeighbours());
						for (Node childNode : n.getNeighbours()) {
							// <!-- Check 3-->
							if (childNode.getState() == goal) {
								childNode.setParent(n);
								node = childNode;
								break;
							}
							if (!childNode.isVisited()) {
								childNode.setParent(n);
								childNode.setVisited(true);
								queue.add(childNode);
							}
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
