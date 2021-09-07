package robotDropTheThonrs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import backGroundMap.AnimationBackGroundMap;
import gameEffect.Animation;
import gameWorld.GameWorldMegamanX;
import objectBullet.BulletMegamanX;
import sound.ManagerSound;

public class Thorns extends BulletMegamanX {

	private Animation lanlan;

	private Animation vaTuong;

	private List<AnimationBackGroundMap> listAnimationDraw1Lan;

	public Thorns(float x, float y, GameWorldMegamanX game, int director) {
		super(x, y, game, 0.1f, 84, 84, 1000, 5);

		listAnimationDraw1Lan = new ArrayList<AnimationBackGroundMap>();

		lanlan = new Animation(getGame().getInputData().getListAnimation().get("quaCauGai"));
		vaTuong = new Animation(getGame().getInputData().getListAnimation().get("bigBangSmall"));

		setSpeedY(5);
		setDirector(director);
		setTeam(ENEMI_TEAM);
	}

	@Override
	public void upload() {

		if (getGame().getPhysicalMap().vaChamBenTrai(getRectangleXuLiVaCham()) != null) {
			AnimationBackGroundMap anime = new AnimationBackGroundMap(getX(), getY() + 50, getGame(),
					new Animation(vaTuong), 64, 94);
			listAnimationDraw1Lan.add(anime);
			setDirector(DIR_RIGHT);

			if ((getX() > getGame().getRockMan().getX() && getX() - getGame().getRockMan().getX() < 500)
					|| (getX() < getGame().getRockMan().getX() && getGame().getRockMan().getX() - getX() < 100)) {
				getGame().getCamera().chanDong();
				ManagerSound.getInstance().getListSound().get("bang").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("bang");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		}
		if (getGame().getPhysicalMap().vaChamBenPhai(getRectangleXuLiVaCham()) != null) {
			AnimationBackGroundMap anime = new AnimationBackGroundMap(getX() + 50, getY() + 50, getGame(),
					new Animation(vaTuong), 64, 94);
			listAnimationDraw1Lan.add(anime);
			setDirector(DIR_LEFT);

			if ((getX() > getGame().getRockMan().getX() && getX() - getGame().getRockMan().getX() < 500)
					|| (getX() < getGame().getRockMan().getX() && getGame().getRockMan().getX() - getX() < 100)) {
				getGame().getCamera().chanDong();
				ManagerSound.getInstance().getListSound().get("bang").start();
				try {
					ManagerSound.getInstance().khoiTaoHieuUng("bang");
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		}

		if (getDirector() == DIR_LEFT) {
			setSpeedX(-3);
		} else if (getDirector() == DIR_RIGHT) {
			setSpeedX(3);
		}

		super.upload();

	}

	@Override
	public void draw(Graphics2D g2) {

		drawAndUploadHanhDong(g2, lanlan);

		for (int i = 0; i < listAnimationDraw1Lan.size(); i++) {
			listAnimationDraw1Lan.get(i).draw(g2);
			if (listAnimationDraw1Lan.get(i).getAnime().isLastFrame())
				listAnimationDraw1Lan.remove(i);
		}

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 10, rect.y + 10, rect.width - 20, rect.height - 20);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();

		g2.setColor(Color.YELLOW);
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);

	}

	@Override
	public void getToDeath() {
	}

	public Animation getLanlan() {
		return lanlan;
	}

	public void setLanlan(Animation lanlan) {
		this.lanlan = lanlan;
	}

	public Animation getVaTuong() {
		return vaTuong;
	}

	public void setVaTuong(Animation vaTuong) {
		this.vaTuong = vaTuong;
	}

	public List<AnimationBackGroundMap> getListAnimationDraw1Lan() {
		return listAnimationDraw1Lan;
	}

	public void setListAnimationDraw1Lan(List<AnimationBackGroundMap> listAnimationDraw1Lan) {
		this.listAnimationDraw1Lan = listAnimationDraw1Lan;
	}

}
