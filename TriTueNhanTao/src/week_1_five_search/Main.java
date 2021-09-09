package week_1_five_search;

public class Main {

	public static Node[] getArrayNode() {
		Node nodeS = new Node(30);
		nodeS.setName("S");
		Node nodeK = new Node(26);
		nodeK.setName("K");
		Node nodeC = new Node(20);
		nodeC.setName("C");
		Node nodeH = new Node(16);
		nodeH.setName("H");
		Node nodeF = new Node(8);
		nodeF.setName("F");
		Node nodeG1 = new Node(0);
		nodeG1.setName("G1");
		Node nodeE = new Node(6);
		nodeE.setName("E");
		Node nodeG2 = new Node(0);
		nodeG2.setName("G2");
		Node nodeD = new Node(10);
		nodeD.setName("D");
		Node nodeB = new Node(25);
		nodeB.setName("B");
		Node nodeA = new Node(22);
		nodeA.setName("A");

		nodeS.addNeighboursMap(nodeK, 6);
		nodeS.addNeighboursMap(nodeC, 19);
		nodeS.addNeighboursMap(nodeB, 8);
		nodeK.addNeighboursMap(nodeA, 13);
		nodeK.addNeighboursMap(nodeH, 10);
		nodeH.addNeighboursMap(nodeS, 9);
		nodeH.addNeighboursMap(nodeF, 7);
		nodeH.addNeighboursMap(nodeG1, 19);
		nodeF.addNeighboursMap(nodeG1, 10);
		nodeE.addNeighboursMap(nodeG1, 7);
		nodeC.addNeighboursMap(nodeE, 12);
		nodeD.addNeighboursMap(nodeG2, 9);
		nodeA.addNeighboursMap(nodeB, 8);
		nodeA.addNeighboursMap(nodeD, 15);
		nodeB.addNeighboursMap(nodeA, 7);
		nodeB.addNeighboursMap(nodeC, 11);

		Node[] arrayNode = { nodeS, nodeG1, nodeG2 };

		return arrayNode;
	}

	public static void main(String[] args) {

		// <!-- bfs, dfs and ucs-->
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		System.out.println("bfs, dfs and ucs");
		System.out.println("-----------------------------------------------");

		Node node40 = new Node(40);
		Node node10 = new Node(10);
		Node node20 = new Node(20);
		Node node30 = new Node(30);
		Node node60 = new Node(60);
		Node node50 = new Node(50);
		Node node70 = new Node(70);

		node40.addNeighbours(node10);
		node40.addNeighbours(node20);
		node10.addNeighbours(node30);
		node20.addNeighbours(node10);
		node20.addNeighbours(node30);
		node20.addNeighbours(node60);
		node20.addNeighbours(node50);
		node30.addNeighbours(node60);
		node60.addNeighbours(node70);
		node50.addNeighbours(node70);

		System.out.println("BFS: 40 ----> 70");
		BFS bfs = new BFS();
		bfs.bfsUsingQueue(node40, 70);
		node40.reset();
		System.out.println();

		System.out.println("DFS: 40 ----> 70");
		DFS dfs = new DFS();
		dfs.dfsUsingStack(node40, 70);
		node40.reset();
		System.out.println();

		System.out.println("UCS: 40 ----> 70");
		UniformCostSearch ucs = new UniformCostSearch();
		ucs.ucsUsingPriorityQueue(node40, 70);
		node40.reset();
		System.out.println();

		// <!-- greedy best first search && a* search>
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		System.out.println("greedy best first search && a* search");
		System.out.println("-----------------------------------------------");

		Node[] arrayNode = getArrayNode();

		GreedyBestFirstSearch gbfs = new GreedyBestFirstSearch();
		System.out.println("GBFS: Node-S ----> Node-G1");
		gbfs.gbfs(arrayNode[0], arrayNode[1]);
		arrayNode = getArrayNode();
		System.out.println();
		System.out.println("GBFS: Node-S ----> Node-G2");
		gbfs.gbfs(arrayNode[0], arrayNode[2]);
		arrayNode = getArrayNode();
		System.out.println();

		A_StarSearch as = new A_StarSearch();
		System.out.println("A*: Node-S ----> Node-G1");
		as.gbfs(arrayNode[0], arrayNode[1]);
		arrayNode = getArrayNode();
		System.out.println();
		System.out.println("A*: Node-S ----> Node-G2");
		as.gbfs(arrayNode[0], arrayNode[2]);
		arrayNode = getArrayNode();
		System.out.println();
	}
}
