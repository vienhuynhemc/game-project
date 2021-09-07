package loadData;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameEffect.Animation;
import gameEffect.FrameImage;
import gameWorld.GameWorldLoading;

public class InputDataLoading extends InputData {

	private GameWorldLoading game;

	public InputDataLoading(GameWorldLoading game) throws IOException {
		super(game);

		this.game = game;

		loadImage();
		loadAnimation();

	}

	@Override
	public void loadImage() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(new File("data/loadImageLoading.txt")));

		BufferedImage loading = ImageIO.read(new File("data/loading.png"));
		BufferedImage spriteLoading = ImageIO.read(new File("data/spriteLoading.png"));

		String line;
		while (true) {
			line = read.readLine();
			if (line == null)
				break;
			line = line.trim();
			String[] arr = line.split(" ");
			if (arr[0].equals("loading")) {
				BufferedImage image = loading.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("spriteLoading")) {
				BufferedImage image = spriteLoading.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			}
		}
		read.close();

	}

	@Override
	public void loadAnimation() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(new File("data/loadAnimationLoading.txt")));
		String line;
		while (true) {
			line = read.readLine();
			if (line == null)
				break;
			line = line.trim();
			String[] arr = line.split(" ");
			String name = arr[0];
			Animation anime = new Animation(name);
			for (int i = 1; i < arr.length - 1; i = i + 2) {
				anime.add(getListFrame().get(arr[i]), Double.parseDouble(arr[i + 1]));
			}
			getListAnimation().put(name, anime);
		}
		read.close();
	}

	@Override
	public GameWorldLoading getGame() {
		return game;
	}

	public void setGame(GameWorldLoading game) {
		this.game = game;
	}

	@Override
	public int[][] getPhysicalMap() {
		return null;
	}

}
