package week_3_Minimax;

public class Main {
	public static void main(String[] args) {
		Node root = Minimax.createRoot();
		System.out.println("=============");
		System.out.println("initial:");
		System.out.println("=============");
		Minimax.printGameTree(root);
		System.out.println("=============");
		System.out.println("action...");
		Minimax.action(root);
		System.out.println("=============");
		System.out.println("result: ");
		System.out.println("=============");
		Minimax.printGameTree(root);
	}
}
