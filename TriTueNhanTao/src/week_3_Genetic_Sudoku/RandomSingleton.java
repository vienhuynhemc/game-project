package week_3_Genetic_Sudoku;

import java.util.Random;

public class RandomSingleton {

	private static RandomSingleton rds;
	private Random rd;

	private RandomSingleton() {
		rd = new Random();
	}

	public static RandomSingleton getInstance() {
		if (rds == null)
			rds = new RandomSingleton();
		return rds;
	}

	public Random getRd() {
		return rd;
	}

}
