package loadData;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import gameEffect.Animation;
import gameEffect.FrameImage;

public class InputData {

	private Map<String, FrameImage> listImage;
	private Map<String, Animation> listAnimation;

	public InputData() throws IOException {
		listImage = new HashMap<String, FrameImage>();
		listAnimation = new HashMap<String, Animation>();

		loadImage();
		loadAnimation();
		loadAnimationBackGroundMap();
	}

	public void loadAnimationBackGroundMap() throws IOException {

		Animation ani1 = new Animation("b1");
		for (int i = 0; i < 16; i++) {

			String index = i + "";

			ani1.add(
					new FrameImage("b1" + index,
							ImageIO.read(new File(
									"data/b1/0fe43bfb7e534688adf443dc8384ebd98kM9CCskrECxPgJ5-" + index + ".jpg"))),
					100000000);
		}

		listAnimation.put(ani1.getName(), ani1);

		Animation ani2 = new Animation("b2");
		for (int i = 0; i < 15; i++) {

			String index = i + "";

			ani2.add(
					new FrameImage("b2" + index,
							ImageIO.read(new File(
									"data/b2/af4ee9f6340c45c4d26311fdeea830f3iWHXfH3FHKD3f8Og-" + index + ".jpg"))),
					100000000);
		}

		listAnimation.put(ani2.getName(), ani2);

		Animation ani3 = new Animation("b3");
		for (int i = 0; i < 24; i++) {

			String index = i + "";

			ani3.add(
					new FrameImage("b3" + index,
							ImageIO.read(new File(
									"data/b3/ac76e64125604afda3c26c67e991d235ITL2iDXfifErSXaK-" + index + ".jpg"))),
					100000000);
		}

		listAnimation.put(ani3.getName(), ani3);

		Animation ani4 = new Animation("b4");
		for (int i = 0; i < 72; i++) {

			String index = i + "";

			ani4.add(
					new FrameImage("b4" + index,
							ImageIO.read(new File(
									"data/b4/b8ee117d494b4f5f92426e3545948cb4HPKM5AjPcFAgJzK6-" + index + ".jpg"))),
					25000000);
		}

		listAnimation.put(ani4.getName(), ani4);

		Animation ani5 = new Animation("b5");
		for (int i = 0; i < 32; i++) {

			String index = i + "";

			ani5.add(
					new FrameImage("b5" + index,
							ImageIO.read(new File(
									"data/b5/547ebe35fb264ff29ebe9c2896871549cIuonvaech4FY1IM-" + index + ".jpg"))),
					25000000);
		}

		listAnimation.put(ani5.getName(), ani5);

		Animation ani6 = new Animation("b6");
		for (int i = 0; i < 4; i++) {

			String index = i + "";

			ani6.add(new FrameImage("b6" + index, ImageIO.read(new File("data/b6/a-000" + index + ".jpg"))), 100000000);
		}

		listAnimation.put(ani6.getName(), ani6);

		Animation ani7 = new Animation("b7");
		for (int i = 10; i < 49; i++) {

			String index = i + "";

			ani7.add(new FrameImage("b7" + index,
					ImageIO.read(new File(
							"data/b7/Falling girls, le eleganti illustrazioni di Manddy Wyckens _ Collater_al-00"
									+ index + ".jpg"))),
					10000000);

		}

		listAnimation.put(ani7.getName(), ani7);

		Animation ani8 = new Animation("b8");
		for (int i = 10; i < 144; i++) {

			String index = i + "";

			if (i < 100) {

				ani8.add(new FrameImage("b8" + index, ImageIO.read(new File("data/b8/cd2-358c5-00" + index + ".jpg"))),
						10000000);
			} else {

				ani8.add(new FrameImage("b8" + index, ImageIO.read(new File("data/b8/cd2-358c5-0" + index + ".jpg"))),
						10000000);
			}
		}

		listAnimation.put(ani8.getName(), ani8);

		Animation ani9 = new Animation("b9");
		for (int i = 10; i < 39; i++) {

			String index = i + "";

			ani9.add(new FrameImage("b9" + index, ImageIO.read(new File("data/b9/LEMAT WORKS-00" + index + ".jpg"))),
					10000000);
		}

		listAnimation.put(ani9.getName(), ani9);

		Animation ani10 = new Animation("b10");
		for (int i = 0; i < 10; i++) {

			String index = i + "";

			ani10.add(new FrameImage("b10" + index,
					ImageIO.read(new File("data/b10/#Haiku – Lucent-000" + index + ".jpg"))), 50000000);
		}

		listAnimation.put(ani10.getName(), ani10);

		Animation ani11 = new Animation("b11");
		for (int i = 0; i < 73; i++) {

			String index = i + "";

			if (i < 10) {

				ani11.add(new FrameImage("b11" + index,
						ImageIO.read(new File("data/b11/A L T I T X D E-000" + index + ".jpg"))), 10000000);
			} else {
				ani11.add(new FrameImage("b11" + index,
						ImageIO.read(new File("data/b11/A L T I T X D E-00" + index + ".jpg"))), 10000000);
			}
		}

		listAnimation.put(ani11.getName(), ani11);

		Animation ani12 = new Animation("b12");
		for (int i = 0; i < 60; i++) {

			String index = i + "";

			if (i < 10) {

				ani12.add(
						new FrameImage("b12" + index, ImageIO.read(new File("data/b12/join us-000" + index + ".jpg"))),
						10000000);
			} else {
				ani12.add(
						new FrameImage("b12" + index, ImageIO.read(new File("data/b12/join us-00" + index + ".jpg"))),
						10000000);
			}
		}

		listAnimation.put(ani12.getName(), ani12);

		Animation ani13 = new Animation("b13");
		for (int i = 0; i < 48; i++) {

			String index = i + "";

			if (i < 10) {

				ani13.add(new FrameImage("b13" + index,
						ImageIO.read(new File("data/b13/join us_ Photo-000" + index + ".jpg"))), 50000000);
			} else {
				ani13.add(new FrameImage("b13" + index,
						ImageIO.read(new File("data/b13/join us_ Photo-00" + index + ".jpg"))), 50000000);
			}
		}

		listAnimation.put(ani13.getName(), ani13);

		Animation ani14 = new Animation("b14");
		for (int i = 0; i < 51; i++) {

			String index = i + "";

			if (i < 10) {

				ani14.add(
						new FrameImage("b14" + index,
								ImageIO.read(new File("data/b14/The Blooming Love _ Jikook _-000" + index + ".jpg"))),
						10000000);
			} else {
				ani14.add(
						new FrameImage("b14" + index,
								ImageIO.read(new File("data/b14/The Blooming Love _ Jikook _-00" + index + ".jpg"))),
						10000000);
			}
		}

		listAnimation.put(ani14.getName(), ani14);

		Animation ani15 = new Animation("b15");
		for (int i = 0; i < 6; i++) {

			String index = i + "";

			if (i < 10) {

				ani15.add(
						new FrameImage("b15" + index,
								ImageIO.read(new File("data/b15/Los colores de Jungkook-000" + index + ".jpg"))),
					50000000);
			} else {
				ani15.add(
						new FrameImage("b15" + index,
								ImageIO.read(new File("data/b15/Los colores de Jungkook-00" + index + ".jpg"))),
						50000000);
			}
		}

		listAnimation.put(ani15.getName(), ani15);

	}

	public void loadImage() throws IOException {
		BufferedImage backGroundButton = ImageIO.read(new File("data/backGroundButton.png"));
		listImage.put("backGroundButton", new FrameImage("backGroundButton", backGroundButton));

		BufferedImage bar = ImageIO.read(new File("data/bar.png"));
		listImage.put("bar", new FrameImage("bar", bar));

		BufferedImage pause = ImageIO.read(new File("data/pause.png"));
		listImage.put("pause", new FrameImage("pause", pause));

		BufferedImage diamond = ImageIO.read(new File("data/diamond.png"));
		BufferedImage longDepTrai = ImageIO.read(new File("data/longDepTrai.png"));
		BufferedImage smoke = ImageIO.read(new File("data/smoke.png"));

		BufferedReader read = new BufferedReader(new FileReader(new File("data/loadImage.txt")));
		String line;
		while (true) {
			line = read.readLine();
			if (line == null)
				break;
			line = line.trim();
			String[] arr = line.split(" ");
			if (arr[0].equals("diamond")) {
				BufferedImage image = diamond.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				listImage.put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("longDepTrai")) {
				BufferedImage image = longDepTrai.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				listImage.put(arr[1], new FrameImage(arr[1], image));
			} else if (arr[0].equals("smoke")) {
				BufferedImage image = smoke.getSubimage(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
						Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
				listImage.put(arr[1], new FrameImage(arr[1], image));
			}
		}
		read.close();

		for (int i = 1; i < 22; i++) {
			FrameImage img = new FrameImage("a" + i, ImageIO.read(new File("data/icon/" + i + ".png")));
			listImage.put("a" + i, img);
		}
	}

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
				anime.add(listImage.get(arr[i]), Double.parseDouble(arr[i + 1]));
			}
			listAnimation.put(name, anime);
		}
		read.close();
	}

	public Map<String, FrameImage> getListImage() {
		return listImage;
	}

	public void setListImage(Map<String, FrameImage> listImage) {
		this.listImage = listImage;
	}

	public Map<String, Animation> getListAnimation() {
		return listAnimation;
	}

	public void setListAnimation(Map<String, Animation> listAnimation) {
		this.listAnimation = listAnimation;
	}

}
