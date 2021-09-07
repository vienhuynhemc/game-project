package appToTinh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MapCuoi extends JFrame {

	private JPanel mainJPanel;
	private JLabel lb1, lb2, lb3, lb4;
	private Timer time;

	public MapCuoi() {

		add(mainJPanel = createMainPanel());

		time = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lb2.getY() == 50)
					time.stop();
				lb2.setBounds(50, lb2.getY() - 1, 200, 50);
				lb3.setBounds(50, lb3.getY() - 1, 900, 50);
				lb4.setBounds(50, lb4.getY() - 1, 200, 50);
			}
		});
		time.start();

		setTitle("Cảm ơn em <3");
		setSize(505, 310);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private JPanel createMainPanel() {
		mainJPanel = new JPanel();
		mainJPanel.setLayout(new BorderLayout(0, 0));
		ImageIcon hinh = new ImageIcon("image/hinh3.gif");
		lb1 = new JLabel(hinh);
		mainJPanel.add(lb1);

		lb2 = new JLabel("Cảm ơn em vì đã đồng ý ^^");
		lb3 = new JLabel("Chúng mình hãy trân trọng nhau từ lúc này nhé <3");
		lb4 = new JLabel("Anh yêu em <3");

		lb2.setForeground(Color.white);
		lb3.setForeground(Color.white);
		lb4.setForeground(Color.white);

		lb2.setBounds(50, 250, 200, 50);
		lb3.setBounds(50, 280, 900, 50);
		lb4.setBounds(50, 310, 200, 50);

		lb1.add(lb2);
		lb1.add(lb3);
		lb1.add(lb4);

		return mainJPanel;
	}

	public static void main(String[] args) {
		new MapCuoi();
	}
}
