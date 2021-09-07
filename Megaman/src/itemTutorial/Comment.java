package itemTutorial;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.FontUIResource;

import gameObject.Draw;
import gameObject.GameObjectMegamanX;
import gameObject.Upload;
import gameWorld.GameWorldMegamanX;
import particulerObject.ParticulerObjectMegamanX;

public class Comment extends GameObjectMegamanX implements Draw,Upload{

	private int team;
	private String comment;

	private long timeStart;
	private long timeQuaChu;

	private boolean isUse;

	private int length;

	private boolean isComplete;

	public Comment(float x, float y, GameWorldMegamanX game, int team, String comment) {
		super(x, y, game);

		this.team = team;

		this.comment = comment;
		length = 0;

		timeQuaChu = 50000000;

		isUse = false;
		isComplete = false;

	}

	public void use() {
		if (!isUse) {
			timeStart = System.nanoTime();
			isUse = true;
		}
	}

	public void draw(Graphics2D g2) {

		g2.setFont(new FontUIResource("Times New Roman", Font.BOLD, 20));

		if (team == ParticulerObjectMegamanX.ENEMI_TEAM) {
			g2.setColor(new Color(249, 99, 49));
		} else if (team == ParticulerObjectMegamanX.LEGUE_TEAM) {
			g2.setColor(new Color(80, 225, 72));
		}

		String subString = comment.substring(0, length);
		List<String> listString = new ArrayList<String>();
		String newString = "";
		for (int i = 0; i < subString.length(); i++) {
			if (subString.charAt(i) == '/' || i == subString.length() - 1) {
				listString.add(newString);
				newString = "";
			} else {
				newString += subString.charAt(i);
			}
		}

		int y = (int) getY() + 30;
		for (int i = 0; i < listString.size(); i++) {
			g2.drawString(listString.get(i), getX() + 30, y);
			y = y + 30;
		}
	}

	@Override
	public void upload() {
		if (System.nanoTime() - timeStart > timeQuaChu) {
			timeStart = System.nanoTime();
			if (length < comment.length()) {
				length++;
			}
		}

		if (length == comment.length()) {
			isComplete = true;
		}
	}

	@Override
	public boolean equals(Object obj) {
		Comment cm = (Comment) obj;
		return this.getComment().equals(cm.getComment());
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public long getTimeQuaChu() {
		return timeQuaChu;
	}

	public void setTimeQuaChu(long timeQuaChu) {
		this.timeQuaChu = timeQuaChu;
	}

	public boolean isUse() {
		return isUse;
	}

	public void setUse(boolean isUse) {
		this.isUse = isUse;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
