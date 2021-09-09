package week_3_HellClimping_Queens;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		LocalSearch ls = new LocalSearch(10,100);
//		LocalSearch ls = new LocalSearch(60, 0);
		ls.run();
	}

}
