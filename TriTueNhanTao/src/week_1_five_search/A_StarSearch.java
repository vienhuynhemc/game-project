package week_1_five_search;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class A_StarSearch {
	public void gbfs(Node initial, Node goal) {
		// <!-- Check 1-->
		if (initial == goal)
			System.out.println("Vị trí ban đầu = vị trí đích");
		else {

			Comparator<Node> c = new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return (o1.getState() + o1.getWay()) - (o2.getState() + o2.getWay());
				}
			};

			Node node = null;
			Queue<Node> queue = new PriorityQueue<Node>(c);

			for (Map.Entry<Node, Integer> n : initial.getNeighboursMap().entrySet()) {
				// <!-- Check 2-->
				n.getKey().setParent(initial);
				n.getKey().setWay(n.getValue());
				queue.add(n.getKey());
			}

			// <!-- Check 2 -->
			if (!queue.isEmpty())
				if (queue.peek() == goal)
					node = queue.poll();

			// <!-- not null ? --> goal --> print result -->
			if (node == null) {
				while (!queue.isEmpty()) {

					Node n = queue.poll();
					n.setVisited(true);
					if (n == goal) {
						node = n;
						break;
					} else {

						for (Map.Entry<Node, Integer> childNode : n.getNeighboursMap().entrySet()) {
							if (!childNode.getKey().isVisited()) {
								if (queue.contains(childNode.getKey())) {
									Node[] listNode = new Node[queue.size()];
									for (int i = 0; i < listNode.length; i++)
										listNode[i] = queue.poll();

									for (Node element : listNode) {
										if (element == childNode.getKey())
											if (childNode.getValue() + n.getWay() < element.getWay()) {
												element.setWay(childNode.getValue() + n.getWay());
												element.setParent(n);
											}

										queue.add(element);
									}
								} else {
									childNode.getKey().setWay(n.getWay() + childNode.getValue());
									childNode.getKey().setParent(n);
									queue.add(childNode.getKey());
								}
							}
						}
					}
				}
			}

			// <!-- Print result -->
			if (node != null) {
				int length = node.getWay();
				if (node == goal) {
					String s = "";
					while (node != initial) {
						s = s.length() == 0 ? node.getName() + "" : node.getName() + " --> " + s;
						node = node.getParent();
					}
					System.out.println(initial.getName() + " --> " + s + " ( Length = " + length + " )");
				}
			} else
				System.out.println("Không có đư�?ng đi từ: " + initial.getName() + " --> " + goal.getName());
		}
	}
}
