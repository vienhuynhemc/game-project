package loadData;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import gameEffect.Animation;
import gameEffect.FrameImage;
import gameWorld.GameWorldMapleStory;

public class InputDataMapbleStory extends InputData {

	private int[][] arrayPhySicalMapMapbleStory;

	private GameWorldMapleStory game;

	public InputDataMapbleStory(GameWorldMapleStory game) throws IOException {
		super(game);
		arrayPhySicalMapMapbleStory = new int[80][329];

		loadPhySicalMapMapbleStory();
		loadImage();
		loadImageInnerRage();
		loadAnimation();

		this.game = game;
	}

	public void loadPhySicalMapMapbleStory() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(new File("data/physicalMapMapleStory.txt")));
		String line;
		int i = 0;
		while (true) {
			line = read.readLine();
			if (line == null)
				break;
			StringTokenizer s = new StringTokenizer(line);
			int j = 0;
			while (s.hasMoreElements()) {
				arrayPhySicalMapMapbleStory[i][j] = Integer.parseInt(s.nextToken());
				j++;
			}
			i++;
		}
		read.close();
	}

	public void loadImageInnerRage() throws IOException {
		BufferedImage behurt = ImageIO.read(new File("data/innerRage/hit1_0.png"));
		getListFrame().put("irb", new FrameImage("irb", behurt));

		for (int i = 0; i < 31; i++) {

			if (i < 3) {
				BufferedImage img = ImageIO.read(new File("data/innerRage/attack2.info.hit_" + i + ".png"));
				getListFrame().put("ira2r" + (i + 1), new FrameImage("ira2r" + (i + 1), img));
			}

			if (i < 8) {
				BufferedImage img1 = ImageIO.read(new File("data/innerRage/move_" + i + ".png"));
				getListFrame().put("irr" + (i + 1), new FrameImage("irr" + (i + 1), img1));
				BufferedImage img2 = ImageIO.read(new File("data/innerRage/stand_" + i + ".png"));
				getListFrame().put("iri" + (i + 1), new FrameImage("iri" + (i + 1), img2));
			}

			if (i < 16) {
				BufferedImage img = ImageIO.read(new File("data/innerRage/die1_" + i + ".png"));
				getListFrame().put("ird" + (i + 1), new FrameImage("ird" + (i + 1), img));
			}

			if (i < 11) {
				BufferedImage img = ImageIO.read(new File("data/innerRage/attack1.info.hit_" + i + ".png"));
				getListFrame().put("ira1r" + (i + 1), new FrameImage("ira1r" + (i + 1), img));
			}

			if (i < 30) {
				BufferedImage img = ImageIO.read(new File("data/innerRage/attack1_" + i + ".png"));
				getListFrame().put("ira1" + (i + 1), new FrameImage("ira1" + (i + 1), img));
			}

			if (i < 31) {
				BufferedImage img = ImageIO.read(new File("data/innerRage/attack2_" + i + ".png"));
				getListFrame().put("ira2" + (i + 1), new FrameImage("ira2" + (i + 1), img));
			}
		}
	}

	@Override
	public void loadImage() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(new File("data/loadImageMapleStory.txt")));

		BufferedImage mario = ImageIO.read(new File("data/mario.png"));
		BufferedImage darkStump = ImageIO.read(new File("data/darkStump.png"));
		BufferedImage itemHealing = ImageIO.read(new File("data/itemHealingMapleStory.png"));
		BufferedImage ef = ImageIO.read(new File("data/marioEffort.png"));
		BufferedImage blackHole = ImageIO.read(new File("data/blackHole.png"));
		BufferedImage yetiSmall = ImageIO.read(new File("data/yetiSmall.png"));
		BufferedImage yetiLager = ImageIO.read(new File("data/yetiLager.png"));
		BufferedImage starPixie = ImageIO.read(new File("data/starPixie.png"));
		BufferedImage moonBunny = ImageIO.read(new File("data/moonBunny.png"));
		BufferedImage poisonFlower = ImageIO.read(new File("data/poisonFlower.png"));
		BufferedImage bain = ImageIO.read(new File("data/bain.png"));
		BufferedImage blackWyvern = ImageIO.read(new File("data/blackWyvern.png"));
		BufferedImage zombieLupin = ImageIO.read(new File("data/zombieLupin.png"));
		BufferedImage iref = ImageIO.read(new File("data/innerRageEFBullet.png"));

		String line;
		while (true) {
			line = read.readLine();
			if (line == null)
				break;
			line = line.trim();
			String[] arr = line.split(" ");
			if (arr[0].equals("mario")) {
				BufferedImage image = mario.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("darkStump")) {
				BufferedImage image = darkStump.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("itemHealing")) {
				BufferedImage image = itemHealing.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("ef")) {
				BufferedImage image = ef.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("blackHole")) {
				BufferedImage image = blackHole.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("yetiSmall")) {
				BufferedImage image = yetiSmall.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("yetiLager")) {
				BufferedImage image = yetiLager.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("starPixie")) {
				BufferedImage image = starPixie.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("moonBunny")) {
				BufferedImage image = moonBunny.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("poisonFlower")) {
				BufferedImage image = poisonFlower.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("bain")) {
				BufferedImage image = bain.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("blackWyvern")) {
				BufferedImage image = blackWyvern.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("zombieLupin")) {
				BufferedImage image = zombieLupin.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("iref")) {
				BufferedImage image = iref.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			}

		}
		read.close();
	}

	@Override
	public void loadAnimation() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(new File("data/loadAnimationMapleStory.txt")));
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
	public int[][] getPhysicalMap() {
		return arrayPhySicalMapMapbleStory;
	}

	public void setArrayPhySicalMapMapbleStory(int[][] arrayPhySicalMapMapbleStory) {
		this.arrayPhySicalMapMapbleStory = arrayPhySicalMapMapbleStory;
	}

	@Override
	public GameWorldMapleStory getGame() {
		return game;
	}

	public void setGame(GameWorldMapleStory game) {
		this.game = game;
	}

	public int[][] getArrayPhySicalMapMapbleStory() {
		return arrayPhySicalMapMapbleStory;
	}

}
