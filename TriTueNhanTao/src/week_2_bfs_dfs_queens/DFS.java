package week_2_bfs_dfs_queens;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class DFS {

	public Node dfsUsingStack(Node initial, int goal) {

		// <!-- Check 1-->
		if (goal == 0)
			System.out.println("Đích đên là 0 quân cờ được sắp xếp => End");
		else {

			// <!-- Check 2-->
			if (goal == 1)
				return initial.getNeighbours().get(0);

			Stack<Node> stack = new Stack<Node>();

			Comparator<Node> c = new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return o2.getState().get(o2.getState().size() - 1) - o1.getState().get(o1.getState().size() - 1);
				}
			};

			List<Node> neightbours = initial.getNeighbours();
			Collections.sort(neightbours, c);

			for (Node n : neightbours)
				stack.push(n);

			while (!stack.isEmpty()) {
				Node n = stack.pop();
				if (n.getState().size() == goal)
					return n;
				else {
					List<Node> neightboursChild = n.getNeighbours();
					Collections.sort(neightboursChild, c);
					for (Node childNode : neightboursChild)
						stack.add(childNode);
				}
			}

		}
		return null;
	}

}
