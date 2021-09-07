package gameObject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import gameEffect.FrameImage;

public class ButtonClick extends JButton {

	private static final long serialVersionUID = 1L;

	private List<FrameImage> listImage;

	private GameWorld game;

	private int curenImage;

	private boolean isIn;// is chuột vào
	private boolean isPress;// is click vào nó
	private boolean isHint;

	public ButtonClick(GameWorld game) {
		this.game = game;

		listImage = new ArrayList<FrameImage>();

		for (int i = 0; i < 21; i++) {
			listImage.add(game.getInputData().getListImage().get("a" + (i + 1)));
		}

	}

	public void upload() {
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(game.getInputData().getListImage().get("backGroundButton").getImage(), getX(), getY() + 52,
				getWidth(), getHeight(), null);

		if (curenImage != 0) {

			g2.drawImage(listImage.get(curenImage - 1).getImage(), getX(), getY() + 52, 45, 45, null);
		}
		if (isIn) {
			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(3f));
			g2.drawRoundRect(getX(), getY() + 52, 52, 52, 10, 10);
		}

		if (isPress) {
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(3f));
			g2.drawRoundRect(getX(), getY() + 52, 52, 52, 10, 10);
		}

		if (isHint) {
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(3f));
			g2.drawRoundRect(getX(), getY() + 52, 52, 52, 10, 10);
		}
	}

	public boolean isIn() {
		return isIn;
	}

	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}

	public List<FrameImage> getListImage() {
		return listImage;
	}

	public void setListImage(List<FrameImage> listImage) {
		this.listImage = listImage;
	}

	public int getCurenImage() {
		return curenImage;
	}

	public void setCurenImage(int curenImage) {
		this.curenImage = curenImage;
	}

	public boolean isPress() {
		return isPress;
	}

	public void setPress(boolean isPress) {
		this.isPress = isPress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GameWorld getGame() {
		return game;
	}

	public void setGame(GameWorld game) {
		this.game = game;
	}

	public boolean isHint() {
		return isHint;
	}

	public void setHint(boolean isHint) {
		this.isHint = isHint;
	}

}
