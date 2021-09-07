package appToTinh;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChuongTrinhChinh extends JFrame {

	private JPanel mainPanel;
	private JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9;
	private JButton bt1, bt2;
	private static boolean k = false;

	public ChuongTrinhChinh() {

		add(mainPanel = creatMainPanel());

		// Hiển thị JFrame
		setTitle("<3<3<3<3<3 ^^");
		setSize(1080, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int hoi = JOptionPane.showConfirmDialog(null, "Tấm lòng của anh đấy :(( , em nỡ ?",
						"Hix :( tell me why?", JOptionPane.YES_NO_OPTION);
				if (hoi == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Hì hì sao em tử chối tấm lòng anh được <3");
				} else {
					JOptionPane.showMessageDialog(null, "Hì hì em quyết định của em sáng suốt đấy ^^");
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {

				setCursor(new Cursor(Cursor.HAND_CURSOR));
				int x = arg0.getX();
				int y = arg0.getY();
				if (x > 750 && y > 470) {
					k = true;
				}
				if (k == true) {
					bt2.setBounds(x, y, 230, 60);
				}

				if (x > 700 && y > 470) {
					lb6.setBounds(0, 0, 210, 210);
					lb5.setBounds(1000, 1000, 210, 210);
					lb4.setBounds(1000, 1000, 210, 210);
					lb7.setBounds(250, 50, 0, 0);
					lb8.setBounds(250, 50, 0, 0);
					lb9.setBounds(250, 50, 500, 50);

				}
				if (y > 470 && x < 400) {
					lb5.setBounds(0, 0, 210, 210);
					lb6.setBounds(1000, 1000, 210, 210);
					lb4.setBounds(1000, 1000, 210, 210);
					lb7.setBounds(250, 50, 0, 0);
					lb8.setBounds(250, 50, 500, 50);
					lb9.setBounds(250, 50, 0, 0);
				}
				if (y < 470) {
					lb4.setBounds(0, 0, 210, 210);
					lb5.setBounds(1000, 1000, 210, 210);
					lb6.setBounds(1000, 1000, 210, 210);
					lb7.setBounds(250, 50, 500, 50);
					lb8.setBounds(250, 50, 0, 0);
					lb9.setBounds(250, 50, 0, 0);
				}

			}
		});

	}

	private JPanel creatMainPanel() {
		mainPanel = new JPanel();
		ImageIcon hinh = new ImageIcon("Image/hinh2.gif");
		mainPanel.setLayout(new BorderLayout(0, 0));
		lb1 = new JLabel(hinh);

		// button
		bt1 = new JButton(new ImageIcon("image/buttonyes.png"));
		bt2 = new JButton(new ImageIcon("image/buttonno.png"));
		bt1.setBounds(100, 500, 230, 60);
		mainPanel.add(bt1);
		bt2.setBounds(770, 500, 230, 60);
		mainPanel.add(bt2);
		mainPanel.add(lb1);
		lb2 = new JLabel("EM CÓ MUỐN SAU NÀY TÊN EM CÓ TRONG");
		lb3 = new JLabel("GIA PHẢ NHÀ ANH KHÔNG? <3 <3");
		lb2.setForeground(Color.WHITE);
		lb3.setForeground(Color.WHITE);
		lb2.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lb3.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lb2.setBounds(120, 200, 900, 60);
		lb3.setBounds(200, 300, 900, 60);
		lb1.add(lb2);
		lb1.add(lb3);

		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MapCuoi();
				setVisible(false);

			}
		});
		bt1.setToolTipText("Thông tin sẽ được chuyển về anh nếu như em quyết định nhấn nha ^^!!");
		lb4 = new JLabel(new ImageIcon("image/cute1.png"));
		lb5 = new JLabel(new ImageIcon("image/cute1cuoi.png"));
		lb6 = new JLabel(new ImageIcon("image/cute1khoc.png"));
		lb1.add(lb4);
		lb1.add(lb5);
		lb1.add(lb6);
		lb4.setBounds(0, 0, 210, 210);
		lb5.setBounds(0, 0, 210, 210);
		lb6.setBounds(0, 0, 210, 210);
		lb7 = new JLabel("Do you have a answer for me ? ^^");
		lb8 = new JLabel("Kimochiiiiiiiiiiiiii~~~~~");
		lb9 = new JLabel("Đừng màaaaaaaaaaaa :((");
		lb7.setBounds(250, 50, 500, 20);
		lb7.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lb7.setForeground(Color.white);
		lb8.setBounds(250, 50, 0, 0);
		lb8.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lb8.setForeground(Color.white);
		lb9.setBounds(250, 50, 0, 0);
		lb9.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lb9.setForeground(Color.white);
		lb1.add(lb7);
		lb1.add(lb8);
		lb1.add(lb9);

		return mainPanel;
	}

	public static void main(String[] args) {
		new ChuongTrinhChinh();
	}

}
