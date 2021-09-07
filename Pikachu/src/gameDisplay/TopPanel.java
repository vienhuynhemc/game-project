package gameDisplay;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gameObject.ButtonHint;
import gameObject.ButtonPause;
import gameObject.LabelDiamond;
import gameObject.LabelLevel;
import gameObject.LabelPoint;
import gameObject.ProcessBar;

public class TopPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private GamePanel game;

	private LabelDiamond lbDiamond;
	private ButtonHint buttonHind;
	private LabelLevel lbLevel;
	private ProcessBar timer;
	private LabelPoint lbPoint;
	private ButtonPause btPause;

	private long timeLeft;
	private boolean isPause;

	public TopPanel(GamePanel game) {

		this.game = game;

		setLayout(null);

		setBackground(new Color(0, 0, 0, 0));

	}

	public void upload() {
		lbDiamond = game.getGameWorld().getLbDiamond();
		buttonHind = game.getGameWorld().getButtonHint();
		lbLevel = game.getGameWorld().getLbLevel();
		timer = game.getGameWorld().getTimer();
		lbPoint = game.getGameWorld().getLbPoint();
		btPause = game.getGameWorld().getButtonPause();

		lbPoint.upload();
	}

	public void draw(Graphics2D g2) {
		if (!isPause) {
			lbDiamond.draw(g2);
			buttonHind.draw(g2);
			timer.draw(g2);
			lbLevel.draw(g2);
			btPause.draw(g2);
			lbPoint.draw(g2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonHind) {
			if (lbDiamond.getScore() > 0) {
				lbDiamond.setScore(lbDiamond.getScore() - 10);
				game.getGameWorld().hint();
			}
		} else if (e.getSource() == btPause) {
			if (!isPause) {
				isPause = true;

				if (timeLeft == 0) {
					timeLeft = System.currentTimeMillis();
				}
				int a = JOptionPane.showConfirmDialog(null, "Pasue Game", "Pause", JOptionPane.DEFAULT_OPTION);
				if (a == JOptionPane.OK_OPTION || a == JOptionPane.CLOSED_OPTION) {
					long time = System.currentTimeMillis() - timeLeft;
					timeLeft = 0;

					timer.setTimeStart(timer.getTimeStart() + time);
					timer.setTimeEnd(timer.getTimeEnd() + time);

					isPause = false;
				}
			}
		}
	}

	public GamePanel getGame() {
		return game;
	}

	public void setGame(GamePanel game) {
		this.game = game;
	}

	public LabelDiamond getLbDiamond() {
		return lbDiamond;
	}

	public void setLbDiamond(LabelDiamond lbDiamond) {
		this.lbDiamond = lbDiamond;
	}

	public ButtonHint getButtonHind() {
		return buttonHind;
	}

	public void setButtonHind(ButtonHint buttonHind) {
		this.buttonHind = buttonHind;
	}

	public LabelLevel getLbLevel() {
		return lbLevel;
	}

	public void setLbLevel(LabelLevel lbLevel) {
		this.lbLevel = lbLevel;
	}

	public ProcessBar getTimer() {
		return timer;
	}

	public void setTimer(ProcessBar timer) {
		this.timer = timer;
	}

	public LabelPoint getLbPoint() {
		return lbPoint;
	}

	public void setLbPoint(LabelPoint lbPoint) {
		this.lbPoint = lbPoint;
	}

	public ButtonPause getBtPause() {
		return btPause;
	}

	public void setBtPause(ButtonPause btPause) {
		this.btPause = btPause;
	}

	public long getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(long timeLeft) {
		this.timeLeft = timeLeft;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
