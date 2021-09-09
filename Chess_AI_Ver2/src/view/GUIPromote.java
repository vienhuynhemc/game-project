package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class GUIPromote extends JDialog implements ActionListener {

	/*
	 * <!--Version cho nó khỏi cảnh báo bóng đèn màu vàng chứ không có tác dụng gì
	 * cả -->
	 */
	private static final long serialVersionUID = 1L;

	// <!-- Ánh xạ view -->
	private View view;

	// <!-- Các button tương ứng với các quân cờ -->
	private JButton btQ, btR, btB, btN;

	public GUIPromote(View view) {

		this.view = view;

		createButton();
		createDefault();
	}

	// <-- Tạo 1 số thông số mặc định cho quân cờ -->
	private void createDefault() {
		setTitle("Phong Tốt");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setSize(188, 210);
		setLocationRelativeTo(null);
		setResizable(false);

		// <!-- Không cho tác động out size -->
		setModal(true);
	}

	// <!-- Tạo giao diện chính -->
	public void createButton() {

		// <!-- Cho layout thành null-->
		setLayout(null);

		// <!-- Thêm vào 1 panel, cho panel đó 1 màu, nhớ là layoutnull-->
		JPanel p = new JPanel();
		add(p);
		p.setLayout(null);
		p.setBounds(0, 0, 188, 210);
		p.setBackground(new Color(181, 136, 99));

		// <!-- Add các phần tử vào, phần tử có hình -->
		p.add(btQ = new JButton("", new ImageIcon("img/bQ.png")));
		p.add(btR = new JButton("", new ImageIcon("img/wR.png")));
		p.add(btB = new JButton("", new ImageIcon("img/wB.png")));
		p.add(btN = new JButton("", new ImageIcon("img/bN.png")));

		// <!-- Cho tí màu cho đẹp -->
		Color backGroundButton = new Color(240, 217, 181);
		btQ.setBackground(backGroundButton);
		btR.setBackground(backGroundButton);
		btB.setBackground(backGroundButton);
		btN.setBackground(backGroundButton);

		// <!-- set vị trí và kích cỡ -->
		btQ.setBounds(10, 10, 70, 70);
		btR.setBounds(90, 10, 70, 70);
		btB.setBounds(10, 90, 70, 70);
		btN.setBounds(90, 90, 70, 70);

		// add action cho các button
		btQ.addActionListener(this);
		btR.addActionListener(this);
		btB.addActionListener(this);
		btN.addActionListener(this);
	}

	// <!-- Xử lý sự kiện khi nhấn các button-->
	@Override
	public void actionPerformed(ActionEvent e) {

		// Gán giá trị tương ứng cho từng button;
		if (e.getSource() == btQ)
			view.setValuePromote(4);
		else if (e.getSource() == btR)
			view.setValuePromote(1);
		else if (e.getSource() == btB)
			view.setValuePromote(3);
		else
			view.setValuePromote(2);

		// Gán xong thì ẩn nó đi thôi
		setVisible(false);
	}

}
