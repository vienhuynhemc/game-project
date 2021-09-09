package week_3_Genetic_Queens;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		LocalSearchGenetic ls = new LocalSearchGenetic(500);
		ls.run();
	}

}
