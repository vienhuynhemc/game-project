package gameObject;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import gameEffect.Animation;

public class BackGround {

	private List<Animation> listAnimation;
	private int currentAnimation;

	public BackGround(GameWorld game) {
		listAnimation = new ArrayList<Animation>();
		for (int i = 1; i < 16; i++) {
			listAnimation.add(game.getInputData().getListAnimation().get("b" + i));
			if (i == 2) {
				listAnimation.get(i - 1).setHeSo(4f);
			}

			if (i == 4) {
				listAnimation.get(i - 1).setHeSo(3.5f);
			}

			if (i == 10) {
				listAnimation.get(i - 1).setHeSo(2.5f);
			}

			if (i == 11) {
				listAnimation.get(i - 1).setHeSo(2.2f);
			}

			if (i == 14) {
				listAnimation.get(i - 1).setHeSo(2.5f);
			}

		}
	}

	public void upload() {
		listAnimation.get(currentAnimation).upload(System.nanoTime());
	}

	public void draw(Graphics2D g2) {
		if (currentAnimation == 0) {
			listAnimation.get(currentAnimation).draw(g2, -8, -380);
		} else if (currentAnimation == 1) {
			listAnimation.get(currentAnimation).draw(g2, 0, -850);
		} else if (currentAnimation == 2) {
			listAnimation.get(currentAnimation).draw(g2, -270, -120);
		} else if (currentAnimation == 4) {
			listAnimation.get(currentAnimation).draw(g2, -10, -320);
		} else if (currentAnimation == 5) {
			listAnimation.get(currentAnimation).draw(g2, -10, -620);
		} else if (currentAnimation == 6) {
			listAnimation.get(currentAnimation).draw(g2, -300, -370);
		} else if (currentAnimation == 10) {
			listAnimation.get(currentAnimation).draw(g2, 0, -200);
		} else if (currentAnimation == 11) {
			listAnimation.get(currentAnimation).draw(g2, 0, -100);
		} else if (currentAnimation == 12) {
			listAnimation.get(currentAnimation).draw(g2, 0, -100);
		} else if (currentAnimation == 13) {
			listAnimation.get(currentAnimation).draw(g2, 0, -300);
		}

		else {
			listAnimation.get(currentAnimation).draw(g2, 0, 0);
		}
	}

	public List<Animation> getListAnimation() {
		return listAnimation;
	}

	public void setListAnimation(List<Animation> listAnimation) {
		this.listAnimation = listAnimation;
	}

	public int getCurrentAnimation() {
		return currentAnimation;
	}

	public void setCurrentAnimation(int currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

}
