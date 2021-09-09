package week_1_five_search;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class UniformCostSearch {

	public void ucsUsingPriorityQueue(Node initial, int goal) {

		// <!-- Check 1-->
		if (initial.getState() == goal)
			System.out.println("Vị trí ban đầu = vị trí đích = " + goal);
		else {

			Comparator<Node> c = new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return o1.getWay() - o2.getWay();
				}
			};

			Node node = null;
			Queue<Node> queue = new PriorityQueue<Node>(c);

			for (Node n : initial.getNeighbours()) {
				// <!-- Check 2-->
				n.setParent(initial);
				n.setWay(n.getState() + initial.getWay());
				queue.add(n);
			}

			// <!-- Check 2 -->
			if (!queue.isEmpty())
				if (queue.peek().getState() == goal)
					node = queue.poll();

			// <!-- not null ? --> goal --> print result -->
			if (node == null) {
				while (!queue.isEmpty()) {
					Node n = queue.poll();
					n.setVisited(true);
					if (n.getState() == goal) {
						node = n;
						break;
					} else {
						for (Node childNode : n.getNeighbours())
							if (!childNode.isVisited()) {
								if (queue.contains(childNode)) {
									Node[] listNode = new Node[queue.size()];
									for (int i = 0; i < listNode.length; i++)
										listNode[i] = queue.poll();

									for (Node element : listNode) {
										if (element == childNode)
											if (childNode.getState() + n.getWay() < element.getWay()) {
												element.setWay(childNode.getState() + n.getState());
												element.setParent(n);
											}

										queue.add(element);
									}
								} else {
									childNode.setParent(n);
									childNode.setWay(childNode.getState() + n.getWay());
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
				int length = node.getWay();
				if (node.getState() == goal) {
					String s = "";
					while (node != initial) {
						s = s.length() == 0 ? node.getState() + "" : node.getState() + " --> " + s;
						node = node.getParent();
					}
					System.out.println(initial.getState() + " --> " + s + " ( Length = " + length + " )");
				}
			} else
				System.out.println("Không có đư�?ng đi từ: " + initial.getState() + " --> " + goal);
		}
	}
}
