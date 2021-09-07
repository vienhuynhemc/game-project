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
import gameWorld.GameWorldMegamanX;

public class InputDataMegamanX extends InputData {

	private int[][] arrayPhySicalMapIntroStage;
	private GameWorldMegamanX game;

	public InputDataMegamanX(GameWorldMegamanX game) throws IOException {
		super(game);
		this.game = game;
		arrayPhySicalMapIntroStage = new int[135][325];

		loadImage();
		loadAnimation();

		loadPhySicalMapIntroStage();
	}

	@Override
	public void loadImage() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(new File("data/loadImage.txt")));

		BufferedImage rockmansprite = ImageIO.read(new File("data/rockmansprite.png"));
		BufferedImage rockmanspriteVer2 = ImageIO.read(new File("data/rockmanspriteVer2.png"));
		BufferedImage weap = ImageIO.read(new File("data/weaponsAndItems.png"));
		BufferedImage introEnemies = ImageIO.read(new File("data/introEnemies.png"));
		BufferedImage mettalT = ImageIO.read(new File("data/mettalT.png"));
		BufferedImage introStageFront = ImageIO.read(new File("data/introStageMapFront.png"));
		BufferedImage napDanBulletRockMan = ImageIO.read(new File("data/napDanBulletRockMan.png"));
		BufferedImage npc = ImageIO.read(new File("data/npc.png"));
		BufferedImage whenRockManDeathSprite = ImageIO.read(new File("data/whenRockManDeathSpirte.png"));
		BufferedImage bossDoor = ImageIO.read(new File("data/bossDoor.png"));
		BufferedImage subBossIntroStage = ImageIO.read(new File("data/subBossIntroStage.png"));
		BufferedImage bossIntroStage = ImageIO.read(new File("data/bossIntroStage.png"));
		BufferedImage enemiesFinalBoss = ImageIO.read(new File("data/enemiesFinalBoss.png"));
		BufferedImage blackHole = ImageIO.read(new File("data/blackHole.png"));

		String line;
		while (true) {
			line = read.readLine();
			if (line == null)
				break;
			line = line.trim();
			String[] arr = line.split(" ");
			if (arr[0].equals("rockmansprite")) {
				BufferedImage image = rockmansprite.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("rockmanspriteVer2")) {
				BufferedImage image = rockmanspriteVer2.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("weap")) {
				BufferedImage image = weap.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("introEnemies")) {
				BufferedImage image = introEnemies.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("mettalT")) {
				BufferedImage image = mettalT.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("introStageFront")) {
				BufferedImage image = introStageFront.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("napDanBulletRockMan")) {
				BufferedImage image = napDanBulletRockMan.getSubimage(Integer.parseInt(arr[2]),
						Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("npc")) {
				BufferedImage image = npc.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("whenRockManDeathSprite")) {
				BufferedImage image = whenRockManDeathSprite.getSubimage(Integer.parseInt(arr[2]),
						Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("bossDoor")) {
				BufferedImage image = bossDoor.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("subBossIntroStage")) {
				BufferedImage image = subBossIntroStage.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("bossIntroStage")) {
				BufferedImage image = bossIntroStage.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("enemiesFinalBoss")) {
				BufferedImage image = enemiesFinalBoss.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("blackHole")) {
				BufferedImage image = blackHole.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				getListFrame().put(arr[1], new FrameImage(arr[1], image));
			}

			getGame().getController().getModel().getLoading().getNotification().getListNotification()
					.add("Loading FrameImage : " + arr[1]);

		}
		read.close();
	}

	@Override
	public void loadAnimation() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(new File("data/loadAnimation.txt")));
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
			getGame().getController().getModel().getLoading().getNotification().getListNotification()
					.add("Loading Animation : " + name);
		}
		read.close();
	}

	public void loadPhySicalMapIntroStage() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(new File("data/physicalMapIntroStage.txt")));
		String line;
		int i = 0;
		while (true) {
			line = read.readLine();
			if (line == null)
				break;
			StringTokenizer s = new StringTokenizer(line);
			int j = 0;
			while (s.hasMoreElements()) {
				arrayPhySicalMapIntroStage[i][j] = Integer.parseInt(s.nextToken());
				j++;
			}
			i++;
		}
		read.close();
	}

	@Override
	public int[][] getPhysicalMap() {
		return arrayPhySicalMapIntroStage;
	}

	public void setArrayPhySicalMapIntroStage(int[][] arrayPhySicalMapIntroStage) {
		this.arrayPhySicalMapIntroStage = arrayPhySicalMapIntroStage;
	}

	@Override
	public GameWorldMegamanX getGame() {
		return game;
	}

	public void setGame(GameWorldMegamanX game) {
		this.game = game;
	}

	public int[][] getArrayPhySicalMapIntroStage() {
		return arrayPhySicalMapIntroStage;
	}

}
