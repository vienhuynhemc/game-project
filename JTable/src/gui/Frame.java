package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel p;

	private JTable table;

	private DefaultTableModel model;

	private JScrollPane scrollPane;

	private JButton btXoa, btQuayLai;

	public Frame() {

		// tạo panel
		add(p = new JPanel());
		p.setLayout(null);
		p.setBackground(Color.WHITE);

		// tạo scroll panel để chứ table
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 560, 400);
		p.add(scrollPane);

		// tạo table và hiển thị nó lên scroll panel
		table = new JTable();
		scrollPane.setViewportView(table);

		// tạo model cho table
		model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// khởi tạo các kiểu dữ liệu cho các cột - ở đây em tạo 2 cột 0-1 , cột 0 là
			// kiểu String cột 1 là boolean (checkbox)
			public java.lang.Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case 0:
					return String.class;
				case 1:
					return Boolean.class;

				default:
					return String.class;
				}
			};
		};

		// set model cho table
		table.setModel(model);

		// add 2 cột cho model
		model.addColumn("Danh sách thời gian biểu");
		model.addColumn("");

		// khởi tạo row cho table ở đây em tạo 10 row;
		for (int i = 0; i < 10; i++) {
			// add row
			model.addRow(new Object[0]);
			// tạo dữ liệu cho cột 0
			model.setValueAt("Thời gian biểu " + i, i, 0);
			// tạo dữ liệu cho cột 1
			model.setValueAt(false, i, 1);
		}

		// action 2 button
		p.add(btXoa = new JButton("Xoa"));
		p.add(btQuayLai = new JButton("Quay Lai"));
		btXoa.setBounds(100, 500, 100, 30);
		btQuayLai.setBounds(400, 500, 100, 30);
		btXoa.setBackground(Color.LIGHT_GRAY);
		btQuayLai.setBackground(Color.LIGHT_GRAY);

		// xóa
		btXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				List<Integer> list = new ArrayList<Integer>();

				for (int i = 0; i < table.getRowCount(); i++) {
					boolean check = Boolean.valueOf(table.getValueAt(i, 1).toString());
					if (check) {
						list.add(i);
					}
				}

				for (int i = 0; i < list.size(); i++) {
					model.removeRow(list.get(i));
					for (int j = i + 1; j < list.size(); j++) {
						list.set(j, list.get(j) - 1);
					}
				}
			}
		});

		// quay lại
		btQuayLai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// to do
			}
		});

		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new Frame();
	}

}
