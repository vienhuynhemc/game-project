package tutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameWorld.GameWorldMapleStory;
import itemTutorial.CommentMapleStory;
import particulerObject.ParticulerObjectMegamanX;
import view.GameFrame;

public class BossMapleStoryTutorial extends TutorialMapleStory {

	private int xRectCommentMapleStory;
	private int yRectCommentMapleStory;
	private int sizeX;
	private int sizeY;

	private long timeStartEndOneCommentMapleStory;
	private boolean timeEndIsStart;

	public BossMapleStoryTutorial(GameWorldMapleStory game, int STAGE) {
		super(game, STAGE);

		CommentMapleStory cm1 = new CommentMapleStory(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.ENEMI_TEAM,
				"Lại một tế vật lạc đến chỗ ta hahah/" + "Trong hắn thật ngon làm sao..../"
						+ "Trong tuần vừa rồi ta đã lấy được/" + "rất nhiều sự đau khổ sinh linh,hehe/"
						+ "Đến đây nào con chiên của ta/");
		CommentMapleStory cm2 = new CommentMapleStory(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.LEGUE_TEAM,
				"Hóa ra NightMare có hình dạng như vậy/ sao"
						+ "Trong thật đáng sợ đúng là biết/cách hù dọa người khác ~~/");
		CommentMapleStory cm3 = new CommentMapleStory(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.ENEMI_TEAM,
				"Ngoại hình cao quý của ta đôi mắt tầm/thường của người sao trong thấy/được sự đẹp đẽ của nó/Một vẻ đẹp thiêng liêng/");
		CommentMapleStory cm4 = new CommentMapleStory(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.ENEMI_TEAM,
				"Không vòng vo nữa, người cũng biết/ta là gì rồi chứ, vào việc/chính thôi nào/Ta sẽ lấy nỗi đau của ngươi.....");
		CommentMapleStory cm5 = new CommentMapleStory(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.LEGUE_TEAM,
				"-------------------------------------/---------------------------------------/Chiến nào NIGHMARE......../");
		CommentMapleStory cm6 = new CommentMapleStory(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.ENEMI_TEAM,
				"............................./............................./Đến đây nàoooooooooooo..../");
		getListComment().add(cm1);
		getListComment().add(cm2);
		getListComment().add(cm3);
		getListComment().add(cm4);
		getListComment().add(cm5);
		getListComment().add(cm6);

		xRectCommentMapleStory = getRectComment().x + getRectComment().width / 2;
		yRectCommentMapleStory = getRectComment().y + getRectComment().height / 2;
		sizeX = 0;
		sizeY = 0;
	}

	@Override
	public void skipTutorial() {
		if (isDrawRect()) {
			if (isDrawRectGround()) {
				xRectCommentMapleStory = xDraw;
				yRectCommentMapleStory = yDraw;
				sizeX = 400;
				sizeY = 300;
			} else if (getListComment().size() == 0) {
				xRectCommentMapleStory = (GameFrame.getGameWidth() - 400) / 2;
				yRectCommentMapleStory = getYdraw() + (GameFrame.GAME_HEIGHT - 300) / 2;
				sizeX = 0;
				sizeY = 0;
			}
		}
		if (isDrawString()) {
			if (getListComment().size() > 0) {
				if (!getListComment().get(0).isComplete()) {
					getListComment().get(0).setLength(getListComment().get(0).getComment().length());
				}

				if (isCompleteDrawRect() && isDrawRectGround()) {
					if (getListComment().get(0).isComplete()) {
						timeStartEndOneCommentMapleStory -= 200000000;
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		g2.setColor(new Color(86, 105, 173, getDoTrongSuot()));
		g2.fillRoundRect(xRectCommentMapleStory, yRectCommentMapleStory, sizeX, sizeY, 10, 10);

		if (isCompleteDrawRect() && isDrawRectGround()) {
			if (getListComment().size() == 0) {
				setComplete(true);
			} else {
				if (getListComment().get(0).isComplete()) {

					g2.setColor(Color.ORANGE);
					g2.setFont(new Font("Times New Roman", Font.BOLD, 18));
					if (System.nanoTime() % 3 != 2) {
						g2.drawString("PRESS KEY TO SKIP :3", xDraw + 205, yDraw + 280);
					}
					if (!timeEndIsStart) {
						timeStartEndOneCommentMapleStory = System.nanoTime();
						timeEndIsStart = true;
					}
				}
				getListComment().get(0).use();
				getListComment().get(0).upload();
				getListComment().get(0).draw(g2);
			}
		} else {
			if (isDrawRectGround()) {
				if (xRectCommentMapleStory != getRectComment().x) {
					xRectCommentMapleStory--;
				}
				if (yRectCommentMapleStory != getRectComment().y) {
					yRectCommentMapleStory--;
				}
				if (sizeX != getRectComment().width) {
					sizeX += 2;
				}
				if (sizeY != getRectComment().height) {
					sizeY += 2;
				}
			} else {
				if (xRectCommentMapleStory != (GameFrame.getGameWidth() - 400) / 2) {
					xRectCommentMapleStory++;
				}
				if (yRectCommentMapleStory != getYdraw() + (GameFrame.GAME_HEIGHT - 300) / 2) {
					yRectCommentMapleStory++;
				}
				if (sizeX != 0) {
					sizeX -= 2;
				}
				if (sizeY != 0) {
					sizeY -= 2;
				}
			}
		}
	}

	private boolean isCompleteDrawRect() {
		Rectangle rect = getRectComment();
		if (xRectCommentMapleStory == rect.x && yRectCommentMapleStory == rect.y && sizeX == rect.width
				&& sizeY == rect.height)
			return true;
		return false;
	}

	@Override
	public void upload() {
		super.upload();
		if (getListComment().size() > 0) {
			setDrawString(true);
		} else {
			setDrawString(false);
		}

		if (timeEndIsStart) {
			if (System.nanoTime() - timeStartEndOneCommentMapleStory > 2000000000) {
				getListComment().remove(0);
				timeEndIsStart = false;
			}
		}

	}

	public int getxRectCommentMapleStory() {
		return xRectCommentMapleStory;
	}

	public void setxRectCommentMapleStory(int xRectCommentMapleStory) {
		this.xRectCommentMapleStory = xRectCommentMapleStory;
	}

	public int getyRectCommentMapleStory() {
		return yRectCommentMapleStory;
	}

	public void setyRectCommentMapleStory(int yRectCommentMapleStory) {
		this.yRectCommentMapleStory = yRectCommentMapleStory;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public long getTimeStartEndOneCommentMapleStory() {
		return timeStartEndOneCommentMapleStory;
	}

	public void setTimeStartEndOneCommentMapleStory(long timeStartEndOneCommentMapleStory) {
		this.timeStartEndOneCommentMapleStory = timeStartEndOneCommentMapleStory;
	}

	public boolean isTimeEndIsStart() {
		return timeEndIsStart;
	}

	public void setTimeEndIsStart(boolean timeEndIsStart) {
		this.timeEndIsStart = timeEndIsStart;
	}
}
