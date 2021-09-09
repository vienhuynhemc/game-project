package week_3_MinimaxAlphaBeta;

public class Main {
	public static void main(String[] args) {
		Node root = MinimaxAlphaBeta.createRoot();
		System.out.println("=============");
		System.out.println("initial:");
		System.out.println("=============");
		MinimaxAlphaBeta.printGameTree(root);
		System.out.println("=============");
		System.out.println("action...");
		MinimaxAlphaBeta.action(root);
		System.out.println("=============");
		System.out.println("result: ");
		System.out.println("=============");
		MinimaxAlphaBeta.printGameTree(root);
		System.out.println("*********************************");
		System.out.println("*********************************");
		System.out.println("*********************************");
		Node root2 = MinimaxAlphaBeta.createRoot2();
		System.out.println("=============");
		System.out.println("initial:");
		System.out.println("=============");
		MinimaxAlphaBeta.printGameTree(root2);
		System.out.println("=============");
		System.out.println("action...");
		MinimaxAlphaBeta.action(root2);
		System.out.println("=============");
		System.out.println("result: ");
		System.out.println("=============");
		MinimaxAlphaBeta.printGameTree(root2);
	}
}
