package gecko;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import particulerObject.HunmanMegamanX;
import view.GameFrame;

public class APartOfTheGeckoTongue extends HunmanMegamanX {

	public static final int STAGE_NORMAL = 0;
	public static final int STAGE_END = 1;

	private int STAGE;

	private Animation doanThuongLeft, doanThuongRight;
	private Animation ketThucLeft, ketThucRight;
	private Animation ketThucLeftUndo, ketThucRightUndo;

	private boolean isCham;

	private boolean isEndAPartOfGeckoTongue;

	private boolean isComplete;

	public APartOfTheGeckoTongue(float x, float y, GameWorldMegamanX game, int director, int stage) {
		super(x, y, game, 0f, 32, 24, 1000, 5);

		doanThuongLeft = new Animation(getGame().getInputData().getListAnimation().get("motDoanLuoiTacKe"));
		doanThuongRight = new Animation(getGame().getInputData().getListAnimation().get("motDoanLuoiTacKe"));
		doanThuongRight.daoNguoc();
		ketThucLeft = new Animation(getGame().getInputData().getListAnimation().get("ketThucMotDoanLuoiTacKe"));
		ketThucRight = new Animation(getGame().getInputData().getListAnimation().get("ketThucMotDoanLuoiTacKe"));
		ketThucRight.daoNguoc();
		ketThucLeftUndo = new Animation(getGame().getInputData().getListAnimation().get("ketThucMotDoanLuoiTacKeUndo"));
		ketThucRightUndo = new Animation(
				getGame().getInputData().getListAnimation().get("ketThucMotDoanLuoiTacKeUndo"));
		ketThucRightUndo.daoNguoc();

		setTeam(ENEMI_TEAM);
		setDirector(director);
		STAGE = stage;
	}

	@Override
	public void upload() {
		super.upload();

		if (!isCham) {
			if (getGame().getPhysicalMap().danVaChamTuong(getRectangleXuLiVaCham()) != null
					|| getX() >= getGame().getCamera().getLimitX2() + GameFrame.getGameWidth() - 100) {
				isCham = true;
			}
		}
	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		return getRectangleSizeObject();
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();

		g2.setColor(Color.YELLOW);
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
	}

	@Override
	public void draw(Graphics2D g2) {

		if (isEndAPartOfGeckoTongue) {
			if (getDirector() == DIR_LEFT) {
				ketThucLeftUndo.upload(System.nanoTime());
				ketThucLeftUndo.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				isComplete = true;
			} else {
				ketThucRightUndo.upload(System.nanoTime());
				ketThucRightUndo.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				isComplete = true;
			}
		} else if (STAGE == STAGE_NORMAL) {
			if (getDirector() == DIR_LEFT) {
				doanThuongLeft.upload(System.nanoTime());
				doanThuongLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			} else {
				doanThuongRight.upload(System.nanoTime());
				doanThuongRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
			}
		} else {
			if (getDirector() == DIR_LEFT) {
				ketThucLeft.upload(System.nanoTime());
				ketThucLeft.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				isEndAPartOfGeckoTongue = true;
			} else {
				ketThucRight.upload(System.nanoTime());
				ketThucRight.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - getGame().getCamera().getY()));
				isEndAPartOfGeckoTongue = true;
			}
		}
	}

	public boolean isCham() {
		return isCham;
	}

	public void setCham(boolean isCham) {
		this.isCham = isCham;
	}

	public Animation getDoanThuongLeft() {
		return doanThuongLeft;
	}

	public void setDoanThuongLeft(Animation doanThuongLeft) {
		this.doanThuongLeft = doanThuongLeft;
	}

	public Animation getDoanThuongRight() {
		return doanThuongRight;
	}

	public void setDoanThuongRight(Animation doanThuongRight) {
		this.doanThuongRight = doanThuongRight;
	}

	public Animation getKetThucLeft() {
		return ketThucLeft;
	}

	public void setKetThucLeft(Animation ketThucLeft) {
		this.ketThucLeft = ketThucLeft;
	}

	public Animation getKetThucRight() {
		return ketThucRight;
	}

	public void setKetThucRight(Animation ketThucRight) {
		this.ketThucRight = ketThucRight;
	}

	public int getSTAGE() {
		return STAGE;
	}

	public void setSTAGE(int sTAGE) {
		STAGE = sTAGE;
	}

	public boolean isEndAPartOfGeckoTongue() {
		return isEndAPartOfGeckoTongue;
	}

	public void setEndAPartOfGeckoTongue(boolean isEndAPartOfGeckoTongue) {
		this.isEndAPartOfGeckoTongue = isEndAPartOfGeckoTongue;
	}

	public static int getStageNormal() {
		return STAGE_NORMAL;
	}

	public static int getStageEnd() {
		return STAGE_END;
	}

	public Animation getKetThucLeftUndo() {
		return ketThucLeftUndo;
	}

	public void setKetThucLeftUndo(Animation ketThucLeftUndo) {
		this.ketThucLeftUndo = ketThucLeftUndo;
	}

	public Animation getKetThucRightUndo() {
		return ketThucRightUndo;
	}

	public void setKetThucRightUndo(Animation ketThucRightUndo) {
		this.ketThucRightUndo = ketThucRightUndo;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
