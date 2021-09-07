package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import caRo.GameWorld;

public class Panel2 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p6, p4, p5, p7, p8, p9;
	private JLabel lb1;
	public JLabel lb2;
	private JLabel lbHinh2;;
	private JButton btAbout, btExit;
	public JButton btNewGame;
	private JButton btOption;

	private GameWorld swing;

	public Panel2(GameWorld swing) {

		this.swing = swing;

		setThongSo();

		create();

		setDeleteBackGroundandBorder();

		action();

		setBorder();

	}

	public void action() {
		btNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				swing.panel3.newMatch();
			}
		});
		btExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				swing.panel3.exit();
			}
		});
		btOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// dinggggggggg
				if (swing.panel3.effor == false) {
					try {
						swing.getSound().musicDing();
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ee) {
						ee.printStackTrace();
					}
				}

				swing.panel3.t.stop();
				swing.createSetting();
			}
		});
		btAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});
	}

	public void setBorder() {
		p6.setBorder(new BevelBorder(BevelBorder.LOWERED));
		p8.setBorder(new BevelBorder(BevelBorder.LOWERED));
		p5.setBorder(new BevelBorder(BevelBorder.RAISED, Color.white, Color.black));
	}

	public void setDeleteBackGroundandBorder() {
		btNewGame.setContentAreaFilled(false);
		btNewGame.setBorderPainted(false);
		btOption.setContentAreaFilled(false);
		btOption.setBorderPainted(false);
		btAbout.setContentAreaFilled(false);
		btAbout.setBorderPainted(false);
		btExit.setContentAreaFilled(false);
		btExit.setBorderPainted(false);
	}

	public void create() {
		add(p6 = new JPanel());
		p6.setBackground(new Color(255, 234, 184));
		p6.add(lb1 = new JLabel(new ImageIcon("image/hinh1.png")));
		lb1.setPreferredSize(new Dimension(300, 300));

		// tạo p4

		add(p4 = new JPanel());
		p4.setBackground(new Color(255, 234, 184));
		p4.setLayout(new GridLayout(1, 2));
		p4.add(p7 = new JPanel());
		p7.setLayout(new BoxLayout(p7, BoxLayout.Y_AXIS));
		p7.add(p8 = new JPanel());
		p7.add(p9 = new JPanel());
		p8.setBackground(new Color(255, 234, 184));
		p9.setBackground(new Color(255, 234, 184));
		p8.add(lb2 = new JLabel());
		p9.add(setLbHinh2(new JLabel(new ImageIcon("image/lbHinh2.png"))));
		p7.setBackground(new Color(255, 234, 184));
		lb2.setIcon(new ImageIcon("image/xLabel.png"));

		// tạo p5

		p4.add(p5 = new JPanel());
		p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));
		p5.setBackground(new Color(255, 234, 184));
		p5.add(btNewGame = new JButton());
		p5.add(btOption = new JButton());
		p5.add(btAbout = new JButton());
		p5.add(btExit = new JButton());
		btNewGame.setToolTipText("Nhấn để làm ván mới");
		btOption.setToolTipText("Nhấn để cài đặt");
		btAbout.setToolTipText("Nhấn để xem hướng dẫn và thông tin phát triển");
		btExit.setToolTipText("Nhấn để thoát");
//		btNewGame.setIcon(new ImageIcon("image/newGame.png"));
		btOption.setIcon(new ImageIcon("image/option.png"));
		btAbout.setIcon(new ImageIcon("image/help.png"));
		btExit.setIcon(new ImageIcon("image/exit.png"));
	}

	public void setThongSo() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(new Color(255, 234, 184));
		setPreferredSize(new Dimension(350, 710));
	}

	public void about() {

		if (swing.panel3.effor == false) {
			try {
				swing.getSound().musicDing();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		}

		swing.panel3.t.stop();
		String s = "";
		s = s + "1. Game có bàn cờ là 20x20 ( Có thể thay đổi trong phần option).\n";
		s = s + "2. Để bắt đầu game, trước hết bạn phải nhập tên của 2 người chơi vào 2 ô textField ở bên phải màn hình, sau đó nhất Start hoặc phím ENTER để bắt đầu chơi.\n";
		s = s + "3. Người nào tạo được 5 ô cùng hàng(chéo , ngang , dọc) và không bị người kia chặn 2 đầu thì sẽ thắng.\n";
		s = s + "4. Thời gian suy nghĩ là 20 giây ( Có thể thay đổi trong option) cho mỗi người , hết thời gian suy nghĩ mà không đưa ra nước đi thì bị xử thua ván đó.\n";
		s = s + "5. Ai thua thì ván sau sẽ được đánh trước để đảm bảo công bằng :D.\n";
		s = s + "6. Nhấn vào reset để khởi tạo lại chò chơi (Nhập tên người chơi mới, đặt lại số điểm,....).\n";
		s = s + "7. Game có tích hợp Undo ( Ctrl Z ) và Redo ( Ctrl Y ) để dùng khi đánh nhầm chẳng hạn :D.\n";
		s = s + "8. Bạn có thể cài đặt âm lượng nhạc nền hay nhạc hiệu ứng to lên hay nhỏ xuống trong phần cài đặt.\n";
		s = s + "9. Một số phím tắt trong game:\n - Ctrl + Z : Undo.\n - Ctrl + Y : Redo.\n - Ctrl + X : Bật tắt nhạc nền.\n - Ctrl + C : Bật tắt âm thanh hiệu ứng.\n - Ctrl + Q : Thoát game.\n - Ctrl + R : Tạo mới trò chơi.\n - Ctrl + N : New game.\n - Ctrl + M : New match.\n - Ctrl + A : Hướng dẫn.\n";
		s = s + "10. Nếu có góp ý nào hoặc lỗi thì mong các bạn gởi qua cho mình :((  :\n   - Link fb: https://www.facebook.com/hvvien2k.\n   -  Gmail: 18130281@st.hcmuaf.edu.vn.\n :>";
		int a = JOptionPane.showConfirmDialog(null, s, "About ?", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, new ImageIcon("image/about.png"));
		if (a == JOptionPane.OK_OPTION) {
			if (swing.panel3.effor == false) {
				try {
					swing.getSound().musicPop();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
			if (swing.panel3.statrr == true) {
				int count = 0;
				for (int i = 0; i < swing.arr.length; i++) {
					for (int j = 0; j < swing.arr[i].length; j++) {
						if (swing.arr[i][j] != 0)
							count++;
					}
				}
				if (count != 0) {
					swing.panel3.t.start();
				}
			}
		} else {
			if (swing.panel3.effor == false) {
				try {
					swing.getSound().musicPop();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
			if (swing.panel3.statrr == true) {
				int count = 0;
				for (int i = 0; i < swing.arr.length; i++) {
					for (int j = 0; j < swing.arr[i].length; j++) {
						if (swing.arr[i][j] != 0)
							count++;
					}
				}
				if (count != 0) {
					swing.panel3.t.start();
				}
			}
		}
	}

	public JLabel getLbHinh2() {
		return lbHinh2;
	}

	public JLabel setLbHinh2(JLabel lbHinh2) {
		this.lbHinh2 = lbHinh2;
		return lbHinh2;
	}

}
