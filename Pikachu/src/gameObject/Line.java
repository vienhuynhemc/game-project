package gameObject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class Line {

	private List<Move> listMove;

	private boolean isComplete;

	private long timeStart;

	public Line(List<Move> list) {
		this.listMove = list;

		timeStart = System.currentTimeMillis();
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.YELLOW);

		for (int i = 0; i < listMove.size() - 1; i++) {
			Move m1 = new Move(listMove.get(i).getX() - 1, listMove.get(i).getY() - 1);
			Move m2 = new Move(listMove.get(i + 1).getX() - 1, listMove.get(i + 1).getY() - 1);

			g2.setStroke(new BasicStroke(3f));

			g2.drawLine(m1.getY() * 52 + 65 + m1.getY() * 2 + 25, m1.getX() * 52 + 85 + m1.getX() * 2 + 25,
					m2.getY() * 52 + 65 + m2.getY() * 2 + 25, m2.getX() * 52 + 85 + m2.getX() * 2 + 25);
		}
	}

	public void upload() {
		if (System.currentTimeMillis() - timeStart > 200) {
			isComplete = true;
		}
	}

	public List<Move> getListMove() {
		return listMove;
	}

	public void setListMove(List<Move> listMove) {
		this.listMove = listMove;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

}
