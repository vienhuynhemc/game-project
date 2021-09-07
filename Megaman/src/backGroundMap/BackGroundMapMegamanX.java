package backGroundMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import bossIntroStage.TheFieldFinalBoss;
import frontMap.FrontMapItem;
import gameWorld.GameWorldMegamanX;

public class BackGroundMapMegamanX extends BackGroundMap {

	private BufferedImage imageIntro;

	private List<AnimationBackGroundMap> listAnimationMap;
	private List<AnimationBackGroundMap> listAnimationBackEnd;

	private List<FrontMapItem> listTv;

	private TheFieldFinalBoss sanDauFinalBoss;

	public BackGroundMapMegamanX(float x, float y, GameWorldMegamanX game) throws IOException {
		super(x, y, game);

		imageIntro = ImageIO.read(new File("data/introStageMap.png"));
		setImageIntro(imageIntro.getSubimage(6, 0, 4860, 2172));

		setBackGroundMap(ImageIO.read(new File("data/introStageMapBackGround.png")));

		listAnimationMap = new ArrayList<AnimationBackGroundMap>();
		listAnimationBackEnd = new ArrayList<AnimationBackGroundMap>();

		listTv = new ArrayList<FrontMapItem>();

		InitAnimationBGMegamanX init = new InitAnimationBGMegamanX(this);
		init.create();

		setGame(game);
		sanDauFinalBoss = new TheFieldFinalBoss(8704, 1434, (GameWorldMegamanX) getGame());
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(getBackGroundMap(), (int) (getX() - (getGame().getCamera().getX() * 50 / 100)),
				(int) (getY() - getGame().getCamera().getY() - 10), getBackGroundMap().getWidth() * 2,
				getBackGroundMap().getHeight() * 2, null);

		for (int i = 0; i < listAnimationBackEnd.size(); i++) {
			if (!listAnimationBackEnd.get(i).isOutCameraBackEnd()) {
				listAnimationBackEnd.get(i).getAnime().upload(System.nanoTime());
				listAnimationBackEnd.get(i).getAnime().draw(g2,
						(int) (listAnimationBackEnd.get(i).getX() - (getGame().getCamera().getX() * 50 / 100)),
						(int) (listAnimationBackEnd.get(i).getY() - getGame().getCamera().getY()));
			}
		}

		g2.drawImage(getImageIntro(), (int) (getX() - getGame().getCamera().getX()),
				(int) (getY() - getGame().getCamera().getY() - 10), getImageIntro().getWidth() * 2,
				getImageIntro().getHeight() * 2, null);

		for (int i = 0; i < listAnimationMap.size(); i++) {
			if (!listAnimationMap.get(i).isOutCamera()) {
				listAnimationMap.get(i).upload();
				listAnimationMap.get(i).draw(g2);
			}
		}

		for (int i = 0; i < listTv.size(); i++) {
			if (!listTv.get(i).isOutCamera()) {
				if (System.nanoTime() % 3 != 2) {
					listTv.get(i).draw(g2);
				}
			}
		}

		if (sanDauFinalBoss.isStart()) {
			sanDauFinalBoss.upload();
			sanDauFinalBoss.draw(g2);
		}
	}

	@Override
	public void upload() {
	}

	public BufferedImage getImageIntro() {
		return imageIntro;
	}

	public void setImageIntro(BufferedImage imageIntro) {
		this.imageIntro = imageIntro;
	}

	public List<AnimationBackGroundMap> getListAnimationMap() {
		return listAnimationMap;
	}

	public void setListAnimationMap(List<AnimationBackGroundMap> listAnimationMap) {
		this.listAnimationMap = listAnimationMap;
	}

	public List<AnimationBackGroundMap> getListAnimationBackEnd() {
		return listAnimationBackEnd;
	}

	public void setListAnimationBackEnd(List<AnimationBackGroundMap> listAnimationBackEnd) {
		this.listAnimationBackEnd = listAnimationBackEnd;
	}

	public List<FrontMapItem> getListTv() {
		return listTv;
	}

	public void setListTv(List<FrontMapItem> listTv) {
		this.listTv = listTv;
	}

	public TheFieldFinalBoss getSanDauFinalBoss() {
		return sanDauFinalBoss;
	}

	public void setSanDauFinalBoss(TheFieldFinalBoss sanDauFinalBoss) {
		this.sanDauFinalBoss = sanDauFinalBoss;
	}

}
