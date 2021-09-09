package week_2_bfs_dfs_queens;

public class Main {

	public static void main(String[] args) {

		// <!-- Queens recursion -->
		System.out.println("All Queen 10 recursion: ");
		Recursion r = new Recursion(10);
		r.action();
		System.out.println("------------------");

		/*
		 * <!-- Vì em search theo thứ tự alpha beta nên kết quả nó giống nhau, nhưng
		 * thuật toán khác nhau hoàn toàn, cô có thể check thuật toán
		 */
		// <!-- Queens Search -->
		Queens q = new Queens(10);
		q.bfs();
		System.out.println("Queens 10 bfs: ");
		q.printGoal();
		System.out.println("------------------");
		System.out.println("Queens 10 dfs: ");
		q.dfs();
		q.printGoal();
		System.out.println("------------------");

	}

}
