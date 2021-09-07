package bossIntroStage;

import gameObject.Upload;
import gecko.Gecko;
import particulerObject.ParticulerObjectMegamanX;

public class SkillSummonGecko implements Upload {

	private BossIntroStage boss;

	private int countSummon;

	private boolean isComplete;

	private Gecko tacKe;

	private boolean isStartCreate;
	private long timeStartCreate;

	public SkillSummonGecko(BossIntroStage boss) {
		this.boss = boss;
	}

	@Override
	public void upload() {

		float y = boss.getGame().getRockMan().getY() - 200;
		if (y < 900) {
			y = 900;
		}
		float x = createPointX(boss);

		int director = 0;
		if (x > boss.getGame().getRockMan().getX()) {
			director = ParticulerObjectMegamanX.DIR_LEFT;
		} else {
			director = ParticulerObjectMegamanX.DIR_RIGHT;
		}

		if (countSummon < 3) {
			if (tacKe == null) {
				tacKe = new Gecko(x, y, boss.getGame(), director);
				boss.getGame().getListObject().addObject(tacKe);
			} else if (tacKe.isComplete()) {
				if (!isStartCreate) {
					tacKe.setBlood(0);
					countSummon++;
					isStartCreate = true;
					timeStartCreate = System.nanoTime();
				} else {
					if (System.nanoTime() - timeStartCreate > 1000000000) {
						tacKe = new Gecko(x, y, boss.getGame(), director);
						boss.getGame().getListObject().addObject(tacKe);
						isStartCreate = false;
					}
				}
			}
		}

		if (countSummon >= 3) {

			if (tacKe != null) {
				tacKe.upload();
				if (tacKe.isComplete()) {
					tacKe.setBlood(0);
					tacKe = null;
				}
			} else {
				isComplete = true;
			}

		}

	}

	private float createPointX(BossIntroStage boss) {

		float x = boss.getGame().getRockMan().getX() - 400;
		if (x < boss.getGame().getCamera().getLimitX() + 100) {
			x = boss.getGame().getRockMan().getX() + 400;
		}

		return x;

	}

	public BossIntroStage getBoss() {
		return boss;
	}

	public void setBoss(BossIntroStage boss) {
		this.boss = boss;
	}

	public int getCountSummon() {
		return countSummon;
	}

	public void setCountSummon(int countSummon) {
		this.countSummon = countSummon;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public Gecko getTacLe() {
		return tacKe;
	}

	public void setTacLe(Gecko tacLe) {
		this.tacKe = tacLe;
	}

	public Gecko getTacKe() {
		return tacKe;
	}

	public void setTacKe(Gecko tacKe) {
		this.tacKe = tacKe;
	}

	public boolean isStartCreate() {
		return isStartCreate;
	}

	public void setStartCreate(boolean isStartCreate) {
		this.isStartCreate = isStartCreate;
	}

	public long getTimeStartCreate() {
		return timeStartCreate;
	}

	public void setTimeStartCreate(long timeStartCreate) {
		this.timeStartCreate = timeStartCreate;
	}

}
