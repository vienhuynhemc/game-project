package tutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameWorld.GameWorldMegamanX;
import itemTutorial.Comment;
import particulerObject.ParticulerObjectMegamanX;
import view.GameFrame;

public class SubBossIntroStageTutorial extends Tutorial {

	private int xRectComment;
	private int yRectComment;
	private int sizeX;
	private int sizeY;

	private long timeStartEndOneComment;
	private boolean timeEndIsStart;

	private Comment cm3;

	public SubBossIntroStageTutorial(GameWorldMegamanX game, int STAGE) {
		super(game, STAGE);

		Comment cm1 = new Comment(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.ENEMI_TEAM,
				"What? Tại sao một con Robot quèn lại ở/" + "đây?/" + "Đây là nhà máy X nơi làm ăn LƯƠNG/"
						+ "Thiện nhất thế giới!/" + "Nhà người là ai mau khai tên? Và đừng/"
						+ "quên nói lí do tại sao ngươi lại ở đây!/");
		Comment cm2 = new Comment(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.LEGUE_TEAM,
				"Ồ Đúng là tuổi trẻ chưa trải sự /đời, dám"
						+ "chê ta là Robot quèn thì đừng hỏi/vì sao nước biển lại mặn!/Còn lí do ư? Bạn là ai mà sao tôi/phải nói cho bạn nghe!/");
		cm3 = new Comment(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.ENEMI_TEAM,
				"A thằng này láo, tui nghe mà tui tức/tui không thèm ẩn thân nữa luôn -_-/Đợi xí tao bay ra tao chơi solo với/mày luôn!/");
		Comment cm4 = new Comment(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.ENEMI_TEAM,
				"Muahahahahaha!/Sợ run người rồi hả nhóc con/Cũng đúng thôi vì CHIỀU CAO/choáng ngợp của ta là niềm ao ước/của bao người/");
		Comment cm5 = new Comment(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.LEGUE_TEAM,
				"Nơi này là nơi khủng bố, mẹ bảo khủng/bố ai cũng to cao nhìn nguy hiểm lắm, /làm tao hồi bé sợ khủng bố kinh khủng/Bây giờ nhờ mày thì nỗi sợ vơi đi phần nào/Giờ thì kêu hết ra đây tao cân/một lượt :)))))))/");
		Comment cm6 = new Comment(xDraw, yDraw, getGame(), ParticulerObjectMegamanX.ENEMI_TEAM,
				"............................./............................./(Giận giữ)/Nếm mùi trảo ta đâyyyyyyy.../)");
		getListComment().add(cm1);
		getListComment().add(cm2);
		getListComment().add(cm3);
		getListComment().add(cm4);
		getListComment().add(cm5);
		getListComment().add(cm6);

		xRectComment = getRectComment().x + getRectComment().width / 2;
		yRectComment = getRectComment().y + getRectComment().height / 2;
		sizeX = 0;
		sizeY = 0;
	}

	@Override
	public void skipTutorial() {
		if (isDrawRect()) {
			if (isDrawRectGround()) {
				xRectComment = xDraw;
				yRectComment = yDraw;
				sizeX = 400;
				sizeY = 300;
			} else if (getListComment().size() == 0) {
				xRectComment = (GameFrame.getGameWidth() - 400) / 2;
				yRectComment = getYdraw() + (GameFrame.GAME_HEIGHT - 300) / 2;
				sizeX = 0;
				sizeY = 0;
			}
		}
		if (isDrawString()) {
			if (getListComment().size() > 0) {
				if (!getListComment().get(0).isComplete()) {
					getListComment().get(0).setLength(getListComment().get(0).getComment().length());
				}
			}

			if (isCompleteDrawRect() && isDrawRectGround()) {
				if (getListComment().size() > 0) {
					if (getListComment().get(0).isComplete()) {
						timeStartEndOneComment -= 200000000;
					}
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		g2.setColor(new Color(86, 105, 173, getDoTrongSuot()));
		g2.fillRoundRect(xRectComment, yRectComment, sizeX, sizeY, 10, 10);

		if (isCompleteDrawRect() && isDrawRectGround()) {
			if (getListComment().size() == 0) {
				setComplete(true);
			} else {
				if (getListComment().get(0).isComplete()) {

					if (getListComment().get(0).equals(cm3)) {
						getGame().getSubBoss().setXuatHien(true);
						getGame().getSubBoss().setPlayTutorial(false);
					}
					g2.setColor(Color.ORANGE);
					g2.setFont(new Font("Times New Roman", Font.BOLD, 18));
					if (System.nanoTime() % 3 != 2) {
						g2.drawString("PRESS KEY TO SKIP :3", xDraw + 205, yDraw + 280);
					}
					if (!timeEndIsStart) {
						timeStartEndOneComment = System.nanoTime();
						timeEndIsStart = true;
					}
				}
				getListComment().get(0).use();
				getListComment().get(0).upload();
				getListComment().get(0).draw(g2);
			}
		} else {
			if (isDrawRectGround()) {
				if (xRectComment != getRectComment().x) {
					xRectComment--;
				}
				if (yRectComment != getRectComment().y) {
					yRectComment--;
				}
				if (sizeX != getRectComment().width) {
					sizeX += 2;
				}
				if (sizeY != getRectComment().height) {
					sizeY += 2;
				}
			} else {
				if (xRectComment != (GameFrame.getGameWidth() - 400) / 2) {
					xRectComment++;
				}
				if (yRectComment != getYdraw() + (GameFrame.GAME_HEIGHT - 300) / 2) {
					yRectComment++;
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
		if (xRectComment == rect.x && yRectComment == rect.y && sizeX == rect.width && sizeY == rect.height)
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
			if (System.nanoTime() - timeStartEndOneComment > 2000000000) {
				getListComment().remove(0);
				timeEndIsStart = false;
			}
		}

	}

	public int getxRectComment() {
		return xRectComment;
	}

	public void setxRectComment(int xRectComment) {
		this.xRectComment = xRectComment;
	}

	public int getyRectComment() {
		return yRectComment;
	}

	public void setyRectComment(int yRectComment) {
		this.yRectComment = yRectComment;
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

	public long getTimeStartEndOneComment() {
		return timeStartEndOneComment;
	}

	public void setTimeStartEndOneComment(long timeStartEndOneComment) {
		this.timeStartEndOneComment = timeStartEndOneComment;
	}

	public boolean isTimeEndIsStart() {
		return timeEndIsStart;
	}

	public void setTimeEndIsStart(boolean timeEndIsStart) {
		this.timeEndIsStart = timeEndIsStart;
	}

}
