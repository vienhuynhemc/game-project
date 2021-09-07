package poisonFlower;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gameEffect.Animation;
import gameWorld.GameWorldMapleStory;
import healthBarEnemyMapleStory.HealBarEnemies;
import item.LargeHealItemMapleStory;
import item.SmallHealItemMapleStory;
import particulerObject.HumanMapleStory;
import sound.ManagerSound;
import whenDeath.WhenDeadMotLan;

public class PoisonFlower extends HumanMapleStory {

	private Animation il, ir;
	private Animation a1l, a1r;
	private Animation a2l, a2r;
	private Animation bl, br;
	private Animation deadL, deadR;

	private boolean isShooting;
	private long timeStartShooting;

	private long timeStartBehurt;

	private int count;

	private HealBarEnemies hb;

	private Random rd;

	public PoisonFlower(float x, float y, GameWorldMapleStory game, int dir) {
		super(x, y, game, 0.1f, 110, 100, 100, 5);

		hb = new HealBarEnemies("Poison Flower", 100, 100, getX() - getGame().getCamera().getX(),
				getY() - getGame().getCamera().getY(), 160, 30);

		il = new Animation(getGame().getInputData().getListAnimation().get("pfi"));
		ir = new Animation(getGame().getInputData().getListAnimation().get("pfi"));
		ir.daoNguoc();

		a1l = new Animation(getGame().getInputData().getListAnimation().get("pfa1"));
		a1r = new Animation(getGame().getInputData().getListAnimation().get("pfa1"));
		a1r.daoNguoc();

		a2l = new Animation(getGame().getInputData().getListAnimation().get("pfa2"));
		a2r = new Animation(getGame().getInputData().getListAnimation().get("pfa2"));
		a2r.daoNguoc();

		bl = new Animation(getGame().getInputData().getListAnimation().get("pfbehurt"));
		br = new Animation(getGame().getInputData().getListAnimation().get("pfbehurt"));
		br.daoNguoc();

		deadL = new Animation(getGame().getInputData().getListAnimation().get("pfd"));
		deadR = new Animation(getGame().getInputData().getListAnimation().get("pfd"));
		deadR.daoNguoc();

		rd = new Random();

		setDirector(dir);
		setTeam(ENEMI_TEAM);
	}

	@Override
	public void upload() {
		super.upload();

		hb.upload("Poison Flower", 100, getBlood(), getX() - getGame().getCamera().getX(),
				getY() - getGame().getCamera().getY());

		if (getStage() == BEHURT) {
			if (System.currentTimeMillis() - timeStartBehurt > 300) {
				timeStartBehurt = 0;
				setStage(ALIVE);
			}
		}

		if (isShooting) {
			if (System.currentTimeMillis() - timeStartShooting > 2000) {
				isShooting = false;
			}
		}

		if (getX() + 50 < getGame().getMario().getX()) {
			setDirector(DIR_RIGHT);
		} else {
			setDirector(DIR_LEFT);
		}
	}

	@Override
	public void draw(Graphics2D g2) {

		if (getStage() == BEHURT) {

			if (getDirector() == DIR_LEFT) {
				bl.upload(System.nanoTime());
				bl.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 18 - getGame().getCamera().getY()));

				if (bl.isLastFrame()) {
					if (timeStartBehurt == 0) {
						timeStartBehurt = System.currentTimeMillis();
					}
				}
			} else {
				br.upload(System.nanoTime());
				br.draw(g2, (int) (getX() - getGame().getCamera().getX()),
						(int) (getY() - 18 - getGame().getCamera().getY()));

				if (br.isLastFrame()) {
					if (timeStartBehurt == 0) {
						timeStartBehurt = System.currentTimeMillis();
					}
				}
			}
		} else {

			if (!isShooting) {
				if ((getX() > getGame().getMario().getX() && getX() - getGame().getMario().getX() < 130)
						|| (getX() < getGame().getMario().getX() && getGame().getMario().getX() - getX() < 190)) {
					if (getDirector() == DIR_LEFT) {
						a2l.upload(System.nanoTime());
						if (a2l.getCurrentFrame() == 1) {
							a2l.draw(g2, (int) (getX() - 22 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2l.getCurrentFrame() == 2) {
							a2l.draw(g2, (int) (getX() + 6 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2l.getCurrentFrame() == 3) {

							a2l.draw(g2, (int) (getX() + 34 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2l.getCurrentFrame() == 4) {
							a2l.draw(g2, (int) (getX() + 32 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2l.getCurrentFrame() == 5) {
							if (count == 0) {
								count++;
								getGame().getListBullet()
										.addObject(new GasPoisonFlower(getX() - 140, getY() + 30, getGame()));
								ManagerSound.getInstance().getListSound().get("phunDoc").start();
								try {
									ManagerSound.getInstance().khoiTaoHieuUng("phunDoc");
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
									e.printStackTrace();
								}
							}
							a2l.draw(g2, (int) (getX() - 114 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2l.getCurrentFrame() == 6) {
							a2l.draw(g2, (int) (getX() - 142 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2l.getCurrentFrame() == 7) {
							a2l.draw(g2, (int) (getX() - 130 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2l.getCurrentFrame() == 8 || a2l.getCurrentFrame() == 9) {
							a2l.draw(g2, (int) (getX() - 136 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2l.getCurrentFrame() == 10) {
							a2l.draw(g2, (int) (getX() - 14 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2l.getCurrentFrame() == 11) {
							a2l.draw(g2, (int) (getX() + 14 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else {
							a2l.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						}
						if (a2l.isLastFrame()) {
							count = 0;
							isShooting = true;
							timeStartShooting = System.currentTimeMillis();
						}
					} else {
						a2r.upload(System.nanoTime());
						if (a2r.getCurrentFrame() == 1 || a2r.getCurrentFrame() == 6) {
							a2r.draw(g2, (int) (getX() - 8 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2r.getCurrentFrame() == 2) {
							a2r.draw(g2, (int) (getX() - 14 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2r.getCurrentFrame() == 3) {
							a2r.draw(g2, (int) (getX() - 40 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2r.getCurrentFrame() == 4) {
							a2r.draw(g2, (int) (getX() - 44 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2r.getCurrentFrame() == 5) {
							if (count == 0) {
								count++;
								getGame().getListBullet()
										.addObject(new GasPoisonFlower(getX() + 140, getY() + 30, getGame()));
								ManagerSound.getInstance().getListSound().get("phunDoc").start();
								try {
									ManagerSound.getInstance().khoiTaoHieuUng("phunDoc");
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
									e.printStackTrace();
								}
							}
							a2r.draw(g2, (int) (getX() - 22 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else if (a2r.getCurrentFrame() == 8 || a2r.getCurrentFrame() == 9
								|| a2r.getCurrentFrame() == 10) {
							a2r.draw(g2, (int) (getX() - 6 - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						} else {
							a2r.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - 30 - getGame().getCamera().getY()));
						}

						if (a2r.isLastFrame()) {
							count = 0;
							isShooting = true;
							timeStartShooting = System.currentTimeMillis();
						}
					}

				} else {
					if (getDirector() == DIR_LEFT) {
						a1l.upload(System.nanoTime());
						if (a1l.getCurrentFrame() == 1) {
							a1l.draw(g2, (int) (getX() + 28 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else if (a1l.getCurrentFrame() == 2) {
							a1l.draw(g2, (int) (getX() + 54 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else if (a1l.getCurrentFrame() == 3) {
							if (count == 0) {
								getGame().getListBullet().addObject(
										new BulletPoisonFlower(getX() - 20, getY() + 20, getGame(), getDirector()));
								count++;
								ManagerSound.getInstance().getListSound().get("phun").start();
								try {
									ManagerSound.getInstance().khoiTaoHieuUng("phun");
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
									e.printStackTrace();
								}
							}
							a1l.draw(g2, (int) (getX() - 110 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else if (a1l.getCurrentFrame() == 4) {
							a1l.draw(g2, (int) (getX() - 34 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else if (a1l.getCurrentFrame() == 5) {
							a1l.draw(g2, (int) (getX() - 32 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else {
							a1l.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						}
						if (a1l.isLastFrame()) {
							count = 0;
							isShooting = true;
							timeStartShooting = System.currentTimeMillis();
						}
					} else {
						a1r.upload(System.nanoTime());
						if (a1r.getCurrentFrame() == 1) {
							a1r.draw(g2, (int) (getX() - 4 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else if (a1r.getCurrentFrame() == 2) {
							a1r.draw(g2, (int) (getX() - 26 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else if (a1r.getCurrentFrame() == 3) {
							if (count == 0) {
								getGame().getListBullet().addObject(
										new BulletPoisonFlower(getX(), getY() + 20, getGame(), getDirector()));
								count++;
								ManagerSound.getInstance().getListSound().get("phun").start();
								try {
									ManagerSound.getInstance().khoiTaoHieuUng("phun");
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
									e.printStackTrace();
								}
							}
							a1r.draw(g2, (int) (getX() - 8 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else if (a1r.getCurrentFrame() == 4) {
							a1r.draw(g2, (int) (getX() - 4 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else if (a1r.getCurrentFrame() == 5) {
							a1r.draw(g2, (int) (getX() + 4 - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						} else {
							a1r.draw(g2, (int) (getX() - getGame().getCamera().getX()),
									(int) (getY() - 16 - getGame().getCamera().getY()));
						}

						if (a1r.isLastFrame()) {
							isShooting = true;
							count = 0;
							timeStartShooting = System.currentTimeMillis();
						}
					}
				}

			} else {

				if (getDirector() == DIR_LEFT) {
					il.upload(System.nanoTime());
					if (il.getCurrentFrame() == 3 || il.getCurrentFrame() == 5) {
						il.draw(g2, (int) (getX() - 8 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					} else if (il.getCurrentFrame() == 4) {
						il.draw(g2, (int) (getX() - 12 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					} else {
						il.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					}
				} else {
					ir.upload(System.nanoTime());
					if (ir.getCurrentFrame() == 3 || ir.getCurrentFrame() == 5) {
						ir.draw(g2, (int) (getX() + 8 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					} else if (ir.getCurrentFrame() == 4) {
						ir.draw(g2, (int) (getX() + 12 - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					} else {
						ir.draw(g2, (int) (getX() - getGame().getCamera().getX()),
								(int) (getY() - getGame().getCamera().getY()));
					}
				}
			}
		}

		hb.draw(g2);

	}

	@Override
	public Rectangle getRectangleXuLiVaCham() {
		Rectangle rect = getRectangleSizeObject();
		return new Rectangle(rect.x + 10, rect.y + 10, rect.width - 20, rect.height - 20);
	}

	@Override
	public void drawRectangleXuLiVaCham(Graphics2D g2) {
		Rectangle rect = getRectangleXuLiVaCham();
		g2.setColor(Color.BLACK);
		g2.drawRect((int) (rect.x - getGame().getCamera().getX()), (int) (rect.y - getGame().getCamera().getY()),
				rect.width, rect.height);
	}

	@Override
	public void getToDeath() {
		if (getDirector() == DIR_LEFT) {
			getGame().getListWhenDead().addWhenDead(new WhenDeadMotLan(getX(), getY(), getGame(), deadL));
		} else {
			getGame().getListWhenDead().addWhenDead(new WhenDeadMotLan(getX(), getY(), getGame(), deadR));
		}

		int a = rd.nextInt(100);
		if (a <= 9) {
			LargeHealItemMapleStory item = new LargeHealItemMapleStory(getX(), getY(), getGame());
			item.setTimeStart(System.currentTimeMillis());
			getGame().getListItem().addItem(item);
		} else if (a >= 10 && a <= 29) {
			SmallHealItemMapleStory item = new SmallHealItemMapleStory(getX(), getY(), getGame());
			item.setTimeStart(System.currentTimeMillis());
			getGame().getListItem().addItem(item);
		}
	}

}
