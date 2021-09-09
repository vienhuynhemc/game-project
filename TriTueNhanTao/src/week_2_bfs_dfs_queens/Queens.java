package week_2_bfs_dfs_queens;

public class Queens {

	private int n;
	private Node goal;

	public Queens(int n) {
		this.n = n;
	}

	public void dfs() {
		DFS dfs = new DFS();
		this.goal = dfs.dfsUsingStack(new Node(n), n);
	}

	public void bfs() {
		BFS bfs = new BFS();
		this.goal = bfs.bfsUsingQueue(new Node(n), n);
	}

	public void printGoal() {
		if (goal != null) {
			System.out.println(goal.getState() + ":");
			for (int i = 0; i < n; i++) {
				int locationX = goal.getLocationX(i);
				for (int j = 0; j < n; j++) {
					if (j == locationX)
						System.out.print(1 + "  ");
					else
						System.out.print("0" + "  ");
				}
				System.out.println();
			}
		} else
			System.out.println("Không có l�?i giải");
	}
}
