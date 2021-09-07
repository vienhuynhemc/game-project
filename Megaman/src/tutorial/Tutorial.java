package tutorial;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameObject.Draw;
import gameObject.GameObjectMegamanX;
import gameObject.Upload;
import gameWorld.GameWorldMegamanX;
import itemTutorial.Comment;
import sound.ManagerSound;
import view.GameFrame;

public abstract class Tutorial extends GameObjectMegamanX implements Draw,Upload{

	private boolean drawRectGround;

	public static final int xDraw = (GameFrame.GAME_WIDTH - 400) / 2;
	public static final int yDraw = 100;

	private boolean isDrawRect;
	private boolean isDrawString;

	private Rectangle rectComment;

	private List<Comment> listComment;
	private boolean isComplete;

	private int doTrongSuot;

	private int STAGE;

	public Tutorial(GameWorldMegamanX game, int STAGE) {
		super(xDraw, yDraw, game);

		this.STAGE = STAGE;

		listComment = new ArrayList<Comment>();
		rectComment = new Rectangle(xDraw, yDraw, 400, 300);

		drawRectGround = true;

		doTrongSuot = 180;
	}

	public void removeRectBackGround() {
		drawRectGround = false;
	}

	@Override
	public void upload() {

		ManagerSound.getInstance().getListSound().get("comment").start();
		if (ManagerSound.getInstance().getListSound().get("comment")
				.getFramePosition() == ManagerSound.getInstance().getListSound().get("comment")
						.getFrameLength()) {
			try {
				ManagerSound.getInstance().khoiTaoHieuUng("comment");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

		if (listComment.size() == 0) {
			isComplete = true;
		}

		if (isDrawRectGround()) {
			isDrawRect = true;
		} else {
			isDrawRect = false;
		}

	}

	public abstract void skipTutorial();

	public abstract void draw(Graphics2D g2);

	public static int getXdraw() {
		return xDraw;
	}

	public static int getYdraw() {
		return yDraw;
	}

	public List<Comment> getListComment() {
		return listComment;
	}

	public void setListComment(List<Comment> listComment) {
		this.listComment = listComment;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public int getSTAGE() {
		return STAGE;
	}

	public void setSTAGE(int sTAGE) {
		STAGE = sTAGE;
	}

	public Rectangle getRectComment() {
		return rectComment;
	}

	public void setRectComment(Rectangle rectComment) {
		this.rectComment = rectComment;
	}

	public boolean isDrawRectGround() {
		return drawRectGround;
	}

	public void setDrawRectGround(boolean drawRectGround) {
		this.drawRectGround = drawRectGround;
	}

	public boolean isDrawRect() {
		return isDrawRect;
	}

	public void setDrawRect(boolean isDrawRect) {
		this.isDrawRect = isDrawRect;
	}

	public boolean isDrawString() {
		return isDrawString;
	}

	public void setDrawString(boolean isDrawString) {
		this.isDrawString = isDrawString;
	}

	public int getDoTrongSuot() {
		return doTrongSuot;
	}

	public void setDoTrongSuot(int doTrongSuot) {
		this.doTrongSuot = doTrongSuot;
	}

}
