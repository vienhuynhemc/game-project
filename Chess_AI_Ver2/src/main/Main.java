package main;

import java.io.IOException;

import controller.Controller;
import model.Model;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {

		// <!-- Tạo model riêng biệt tách khỏi view-->
		Model model = new Model();

		/*
		 * <!-- Tham chiếu model tới controller để controller làm cơ quan giao tiếp giữa
		 * model và view-->
		 */
		Controller controller = new Controller(model);
	}

}
