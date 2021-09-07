package gameEffect;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

	private String name;

	private ArrayList<FrameImage> listImage;
	private ArrayList<Boolean> listIgnoreImage;
	private ArrayList<Double> listTimeForFrameImage;

	private int currentFrame;

	private boolean isRepeat;

	private long beginTime;

	private float heSo;

	public Animation(String name) {
		this.name = name;
		listImage = new ArrayList<FrameImage>();
		listIgnoreImage = new ArrayList<Boolean>();
		listTimeForFrameImage = new ArrayList<Double>();
		isRepeat = true;
		currentFrame = 0;

		heSo = 2f;
	}

	public Animation(Animation anime) {
		this.name = anime.getName();
		this.listImage = new ArrayList<FrameImage>();
		this.listIgnoreImage = new ArrayList<Boolean>();
		this.listTimeForFrameImage = new ArrayList<Double>();

		for (int i = 0; i < anime.getListImage().size(); i++) {
			this.listImage.add(new FrameImage(anime.getListImage().get(i)));
		}

		for (int i = 0; i < anime.getListIgnoreImage().size(); i++) {
			this.listIgnoreImage.add(anime.getListIgnoreImage().get(i));
		}

		for (int i = 0; i < anime.getListTimeForFrameImage().size(); i++) {
			this.listTimeForFrameImage.add(anime.getListTimeForFrameImage().get(i));
		}

		this.isRepeat = anime.getIsRepeat();
		this.currentFrame = anime.getCurrentFrame();
		this.heSo = anime.getHeSo();
	}

	public void draw(Graphics2D g2, int posX, int posY) {
		if (currentFrame < listImage.size()) {
			g2.drawImage(listImage.get(currentFrame).getImage(), posX, posY,
					(int) (listImage.get(currentFrame).getImage().getWidth() * heSo),
					(int) (listImage.get(currentFrame).getImage().getHeight() * heSo), null);
		}
	}

	public void upload(long dataTime) {
		if (beginTime == 0)
			beginTime = dataTime;
		else {
			if (dataTime - beginTime > listTimeForFrameImage.get(currentFrame)) {
				nextFrame();
				beginTime = dataTime;
			}
		}
	}

	public void nextFrame() {
		if (currentFrame >= listImage.size() - 1) {
			if (isRepeat)
				currentFrame = 0;
		} else
			currentFrame++;
		if (listIgnoreImage.get(currentFrame))
			nextFrame();
	}

	public boolean isLastFrame() {
		if (currentFrame == listImage.size() - 1)
			return true;
		else
			return false;
	}

	public void add(FrameImage image, double time) {
		listImage.add(image);
		listTimeForFrameImage.add(time);
		listIgnoreImage.add(false);
	}

	public void daoNguoc() {
		for (int i = 0; i < listImage.size(); i++) {
			BufferedImage image = listImage.get(i).getImage();
			AffineTransform af = AffineTransform.getScaleInstance(-1, 1);
			af.translate(-image.getWidth(), 0);
			AffineTransformOp op = new AffineTransformOp(af, AffineTransformOp.TYPE_BILINEAR);
			image = op.filter(image, null);
			listImage.get(i).setImage(image);
		}
	}

	public void reset() {
		currentFrame = 0;
		beginTime = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<FrameImage> getListImage() {
		return listImage;
	}

	public void setListImage(ArrayList<FrameImage> listImage) {
		this.listImage = listImage;
	}

	public ArrayList<Boolean> getListIgnoreImage() {
		return listIgnoreImage;
	}

	public void setListIgnoreImage(ArrayList<Boolean> listIgnoreImage) {
		this.listIgnoreImage = listIgnoreImage;
	}

	public ArrayList<Double> getListTimeForFrameImage() {
		return listTimeForFrameImage;
	}

	public void setListTimeForFrameImage(ArrayList<Double> listTimeForFrameImage) {
		this.listTimeForFrameImage = listTimeForFrameImage;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public boolean getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public float getHeSo() {
		return heSo;
	}

	public void setHeSo(float heSo) {
		this.heSo = heSo;
	}

}
