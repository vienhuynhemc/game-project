package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import control.ControllerPanelNotification;
import model.ButtonSmile;
import model.LableNumber;

public class PanelNotification extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel p11, p12, p13;

	private LableNumber lbTime, lbBoom;

	private View game;

	private ButtonSmile bt;

	private Timer time;
	private int nowTime;

	private ControllerPanelNotification c;

	public PanelNotification(View game) {
		this.game = game;

		lbTime = game.getC().getModel().getWorld().getLbTime();
		lbBoom = game.getC().getModel().getWorld().getLbBoom();

		bt = game.getC().getModel().getWorld().getButtonSmile();
		setLayout(new BorderLayout());

		setBorder(BorderFactory.createLoweredBevelBorder());

		add(p11 = new JPanel(), BorderLayout.WEST);
		add(p12 = new JPanel(), BorderLayout.EAST);
		add(p13 = new JPanel(), BorderLayout.CENTER);

		p11.add(lbBoom = new LableNumber(this, "000"));
		updateLbBoom();

		p12.add(lbTime = new LableNumber(this, "000"));

		time = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nowTime++;
				updateLbTime();
			}
		});

		p13.add(bt = new ButtonSmile());

		bt.addMouseListener(c);

	}

	public void updateLbTime() {
		if (nowTime > 999) {
			lbTime.setNumber("voCuc");
		} else {
			String cTime = String.valueOf(nowTime);
			if (cTime.length() == 1) {
				lbTime.setNumber("00" + cTime);
			} else if (cTime.length() == 2) {
				lbTime.setNumber("0" + cTime);
			} else {
				lbTime.setNumber(cTime);
			}

			lbTime.repaint();
		}
	}

	public void updateLbBoom() {
		String boom = String.valueOf(game.getBoom() - game.getC().getModel().getWorld().getCo());
		if (boom.length() == 1) {
			lbBoom.setNumber("00" + boom);
		} else if (boom.length() == 2) {
			lbBoom.setNumber("0" + boom);
		} else {
			lbBoom.setNumber("0" + boom);
		}
		lbBoom.repaint();
	}

	public View getGame() {
		return game;
	}

	public void setGame(View game) {
		this.game = game;
	}

	public Timer getTime() {
		return time;
	}

	public void setTime(Timer time) {
		this.time = time;
	}

	public ButtonSmile getBt() {
		return bt;
	}

	public void setBt(ButtonSmile bt) {
		this.bt = bt;
	}

}
