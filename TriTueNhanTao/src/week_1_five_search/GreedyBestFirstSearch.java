package week_1_five_search;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GreedyBestFirstSearch {

	public void gbfs(Node initial, Node goal) {
		// <!-- Check 1-->
		if (initial == goal)
			System.out.println("Vị trí ban đầu = vị trí đích");
		else {

			Comparator<Node> c = new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return o1.getState() - o2.getState();
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

			Stack<Queue<Node>> stack = new Stack<Queue<Node>>();
			stack.push(queue);

			// <!-- not null ? --> goal --> print result -->
			if (node == null) {
				while (!stack.isEmpty()) {

					Queue<Node> q = stack.pop();
					while (q.isEmpty())
						q = stack.pop();

					Node n = q.poll();
					Queue<Node> newQ = new PriorityQueue<Node>(c);
					while (!q.isEmpty())
						newQ.add(q.poll());

					stack.add(newQ);
					n.setVisited(true);
					if (n == goal) {
						node = n;
						break;
					} else {
						Queue<Node> newQChild = new PriorityQueue<Node>(c);
						for (Map.Entry<Node, Integer> childNode : n.getNeighboursMap().entrySet())
							if (!childNode.getKey().isVisited()) {
								childNode.getKey().setWay(n.getWay() + childNode.getValue());
								childNode.getKey().setParent(n);
								newQChild.add(childNode.getKey());
							}

						stack.add(newQChild);
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
