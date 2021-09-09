package week_2_A_Star_VacuumCleaner;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		A_star test = new A_star(15,50,20,100);
		test.run();
	}

}
