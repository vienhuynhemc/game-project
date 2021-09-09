package week_2_bfs_dfs_queens;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public Node bfsUsingQueue(Node initial, int goal) {

		// <!-- Check 1-->
		if (goal == 0)
			System.out.println("Đích đên là 0 quân cờ được sắp xếp => End");
		else {

			initial.getNeighbours();
			// <!-- Check 2-->
			if (goal == 1)
				return initial.getNeighbours().get(0);

			Queue<Node> queue = new LinkedList<Node>();

			for (Node n : initial.getNeighbours())
				queue.add(n);

			while (!queue.isEmpty()) {
				Node n = queue.poll();
				if (n.getState().size() == goal)
					return n;
				else {
					n.getNeighbours();
					for (Node childNode : n.getNeighbours())
						queue.add(childNode);
				}
			}
		}
		return null;
	}
}
