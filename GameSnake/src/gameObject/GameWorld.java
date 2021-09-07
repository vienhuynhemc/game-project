package gameObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import action.KeyAction;
import conRan.Snake;
import other.FoodSnake;

public class GameWorld {

	public static final int GAME_RUN = 1;
	public static final int GAME_PAUSE = 2;
	public static final int GAME_END = 3;

	private int stage;

	private Snake snake;
	private FoodSnake foodSnake;

	private boolean isClick;
	private long timStartClick;

	private KeyAction keyAction;

	public GameWorld() {

		snake = new Snake(100, 100, this);
		foodSnake = new FoodSnake(this);

		keyAction = new KeyAction(this);

		stage = GAME_RUN;

	}

	public void draw(Graphics2D g2) {
		snake.draw(g2);
		foodSnake.draw(g2);

		if (getStage() == GAME_END) {
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Times New Roman", Font.BOLD, 25));
			g2.drawString("GAME OVER", 170, 250);
		}
	}

	public void upload() {

		if (System.nanoTime() - timStartClick > 10000000) {
			isClick = false;
		}

		switch (stage) {
		case GAME_RUN:
			snake.upload();
			foodSnake.upload();

			if (foodSnake.isDead()) {
				foodSnake = new FoodSnake(this);
			}
			break;
		case GAME_PAUSE:

			break;
		case GAME_END:

			break;
		default:
			break;
		}

	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public KeyAction getKeyAction() {
		return keyAction;
	}

	public void setKeyAction(KeyAction keyAction) {
		this.keyAction = keyAction;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public FoodSnake getFoodSnake() {
		return foodSnake;
	}

	public void setFoodSnake(FoodSnake foodSnake) {
		this.foodSnake = foodSnake;
	}

	public static int getGameRun() {
		return GAME_RUN;
	}

	public static int getGamePause() {
		return GAME_PAUSE;
	}

	public static int getGameEnd() {
		return GAME_END;
	}

	public boolean isClick() {
		return isClick;
	}

	public void setClick(boolean isClick) {
		this.isClick = isClick;
	}

	public long getTimStartClick() {
		return timStartClick;
	}

	public void setTimStartClick(long timStartClick) {
		this.timStartClick = timStartClick;
	}

}
