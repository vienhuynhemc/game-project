package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Model;

public class PanelRight extends JPanel implements ActionListener {

	/*
	 * <!--Version cho nó khỏi cảnh báo bóng đèn màu vàng chứ không có tác dụng gì
	 * cả -->
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * <!-- backGroundBefore: màu nền của panel, lightGray: màu các label của các
	 * select-->
	 */
	private Color backGroundBefore, lightGray;

	/*
	 * <!--Khởi tạo các radiobutton để người dùng chỉ có thể chọn 1 lựa chọn ( chỉ
	 * có thể chọn 1 chế độ chơi ở 1 thời điểm) -->
	 * 
	 * <!--Gồm 3 chế độ: NGƯỜI VS NGƯỜI, NGƯỜI VỚI MÁY và MÁY VỚI MÁY -->
	 */
	private JRadioButton humanVsHuman, humanVsComputer, computerVsComputer;

	/*
	 * <!-- submit: dùng để bắt đầu chơi, nó sẽ gọi tới view.start() và truyền vào
	 * trạng thái tương ứng -->
	 * 
	 * <!-- reset: làm cho mọi thứ trở lại trạng thái ban đầu -->
	 */
	private JButton submit, reset;

	/*
	 * <!-- Chọn màu nước cờ chỉ có tác dụng với chế độ chơi NGƯỜI VỚI MÁY, lúc này
	 * người chơi có thể chọn loại cờ mình mong muốn TRẮNG hoặc ĐEN -->
	 */
	private JComboBox<String> choiseColor;

	/*
	 * <!-- Chọn độ sâu của AI: độ sâu là số nước tính trước của AI, càng bé thì AI
	 * càng đần độn nhưng tính càng nhanh, người lại càng cao thì càng thông tinh
	 * nhưng tính càng chậm vì số trường hợp xảy ra rất lớn -->
	 * 
	 * <!-- LV-1: Phản xạ, cứ thầy ăn được thì ăn luôn-->
	 * 
	 * <!-- LV-2: Biết chạy khi bị địch đuổi bắt -->
	 * 
	 * <!-- LV-3: Suy tính sâu được các thế cờ như: Chiếu tướng bắt gà cồ-->
	 * 
	 * <!-- LV-4: Tính sâu hơn nữa, ở level này tốc độ tính vẫn chấp nhận được-->
	 * 
	 * <!-- LV-5: Ở lv này AI rất thông minh, nhưng tốc độ tính rất chậm, muốn dùng
	 * được phải cải thiện lại thuật toán-->
	 */
	private JComboBox<Integer> aIdepth;

	// <!-- Tham chiếu tới view để dễ dàng truyền đạt các lựa chọn tới view -->
	private View view;

	/*
	 * <!-- Panel right thì chỉ gồm các thao tác như bắt đầu chơi, chọn độ sâu AI,
	 * chế độ chơi,... không có liên quan đến việc xử lý các quân cờ nên sẽ không
	 * nhận vào bất cứ mảng nào, chỉ tham chiếu đến view để dễ dàng thao tác khi
	 * start hoặc reset -->
	 */
	public PanelRight(View view) {
		this.view = view;
		createColor();
		createDefault();
		createElement();
	}

	// <!-- Khởi tạo các màu chỉ 1 lần duy nhất và sử dụng mãi mãi-->
	private void createColor() {
		backGroundBefore = new Color(0, 159, 227);
		lightGray = new Color(234, 234, 234);
	}

	// <!-- Set các thông số cần thiết cho panel right -->
	private void createDefault() {
		setLayout(null);
		setBounds(700, 0, 300, 700);
		setBackground(backGroundBefore);
	}

	/*
	 * <!-- Khởi tạo các phần tử và add nó vào panel -->
	 * 
	 * <!-- Chỉnh lại vị trí, kích cỡ cho phù hợp -->
	 * 
	 * <!-- Trang trí cho chúng đẹp hơn -- >
	 * 
	 * <!-- Set trạng thái ban đầu cho một số phần tử đặc thù như button
	 * reset,combobox,..-->
	 * 
	 * <!-- Add action cho các phần tử-->
	 */
	private void createElement() {
		// <!-- Khởi tạo và add các phần tử vào panel -->
		JLabel logo = new JLabel(new ImageIcon("img/logo.png"));
		JLabel labelDepthAI = new JLabel("Chọn độ sâu AI");
		JLabel labelChoiseColor = new JLabel("Chọn màu cờ");
		add(logo);
		add(labelDepthAI);
		add(labelChoiseColor);
		add(humanVsHuman = new JRadioButton("Người với người"));
		add(humanVsComputer = new JRadioButton("Người với máy"));
		add(choiseColor = new JComboBox<String>());
		add(computerVsComputer = new JRadioButton("Máy với máy"));
		add(aIdepth = new JComboBox<Integer>());
		add(submit = new JButton("Bắt đầu"));
		add(reset = new JButton("Khởi tạo lại"));
		ButtonGroup groupRD = new ButtonGroup();
		groupRD.add(humanVsHuman);
		groupRD.add(humanVsComputer);
		groupRD.add(computerVsComputer);
		choiseColor.addItem("TRẮNG");
		choiseColor.addItem("ĐEN");
		aIdepth.addItem(1);
		aIdepth.addItem(2);
		aIdepth.addItem(3);
		aIdepth.addItem(4);
		aIdepth.addItem(5);

		// <!-- set vị trí, kích cỡ cho tất cả các phần tử -->
		logo.setBounds(-195, 0, 600, 314);
		humanVsHuman.setBounds(50, 320, 250, 30);
		humanVsComputer.setBounds(50, 360, 250, 30);
		labelChoiseColor.setBounds(50, 390, 190, 30);
		choiseColor.setBounds(50, 420, 190, 30);
		computerVsComputer.setBounds(50, 460, 250, 30);
		submit.setBounds(50, 510, 190, 30);
		reset.setBounds(50, 550, 190, 30);
		labelDepthAI.setBounds(50, 590, 190, 30);
		aIdepth.setBounds(50, 620, 190, 30);

		// <!-- thêm một chút màu mè cho các phần tử (màu sắc, kích cỡ chữ, .v.v.) -->
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		humanVsHuman.setBackground(backGroundBefore);
		humanVsHuman.setFont(font);
		humanVsHuman.setForeground(Color.WHITE);
		humanVsComputer.setBackground(backGroundBefore);
		humanVsComputer.setFont(font);
		humanVsComputer.setForeground(Color.WHITE);
		computerVsComputer.setBackground(backGroundBefore);
		computerVsComputer.setFont(font);
		computerVsComputer.setForeground(Color.WHITE);
		labelChoiseColor.setForeground(lightGray);
		labelDepthAI.setForeground(lightGray);

		// <!-- set các phần tử mặt định ở trạng thái ban đầu -->
		setDefaultElement();

		// <!-- add action cho các phần tử ta cần xử lý -->
		actionElement();
	}

	/*
	 * <!-- Phương thức này có mục đích là set trạng thái ban đầu của các phần tử,
	 * VD: button khởi tạo lại thì sẽ phải có enabled false vì chúng ta chưa bắt đầu
	 * game mà -->
	 * 
	 * <!-- Chúng ta nên cho 1 selected được chọn trong 3 selected để giảm bớt việc
	 * sử lý khi bấm bắt đầu mà chưa có select nào được chọn -->
	 * 
	 * <!-- button reset tất nhiên phải ở trạng thái enabled false rồi -->
	 */
	private void setDefaultElement() {
		humanVsHuman.setSelected(true);
		choiseColor.setEnabled(false);
		reset.setEnabled(false);
		aIdepth.setSelectedIndex(3);
	}

	// <!-- Add action cho các phần tử -->
	private void actionElement() {
		submit.addActionListener(this);
		reset.addActionListener(this);
		humanVsComputer.addActionListener(this);
		computerVsComputer.addActionListener(this);
		humanVsHuman.addActionListener(this);
	}

	/*
	 * <!-- Xử lý sự kiện cho các phần tử-->
	 * 
	 * <!-- Vì chọn màu cờ chỉ có tác dụng ở chế độ NGƯỜI VỚI MÁY nên ta xử khi chọn
	 * vào radio NGƯỜI VỚI MÁY thì mới cho combobox choisecolor về trạng thái
	 * enabled true còn các chế độ khác thì là false-->
	 * 
	 * <!-- Khi nhấn submit thì sẽ enabled tất cả mọi thứ thành false thế chỉ có
	 * reset chuyển từ trạng thái false thành true, và tất nhiên điều không thể
	 * thiếu là gọi tới hàm start() của view để view thông báo cập nhập lại cho
	 * panelRight -->
	 * 
	 * <!-- Rest thì sẽ enabled tất cả mọi thứ thành true và trả về trạng thái bạn
	 * đầu của chúng, bản thân nó thì enabled thành false và cuối cùng sẽ gọi tới
	 * hàm reset() của view-->
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// <!-- Xử lý chọn màu cờ chỉ có tác dụng ở chế độ NGƯỜI VỚI MÁY-->
		if (humanVsComputer.isSelected())
			choiseColor.setEnabled(true);
		if (computerVsComputer.isSelected() || humanVsHuman.isSelected())
			choiseColor.setEnabled(false);

		// <!-- Xử lý hành động khi nhấn nút BẮT ĐẦU (submit)-->
		if (e.getSource() == submit) {

			// <!-- enabled mọi thứ thành false,reset thành true-->
			humanVsHuman.setEnabled(false);
			humanVsComputer.setEnabled(false);
			computerVsComputer.setEnabled(false);
			choiseColor.setEnabled(false);
			aIdepth.setEnabled(false);
			submit.setEnabled(false);
			reset.setEnabled(true);

			// <!-- Gọi tới hàm start() của view và truyền vào trạng thái chơi tương ứng-->
			if (humanVsHuman.isSelected())
				view.start(Model.HUMAN_VS_HUMAN, choiseColor.getSelectedIndex(), aIdepth.getSelectedIndex());
			else if (humanVsComputer.isSelected())
				view.start(Model.HUMAN_VS_COMPUTER, choiseColor.getSelectedIndex(), aIdepth.getSelectedIndex());
			else
				view.start(Model.COMPUPER_VS_COMPUTER, choiseColor.getSelectedIndex(), aIdepth.getSelectedIndex());
		}

		// <!-- Xử lý hành động khi nhấn nút KHỞI TẠO LẠI (reset)-->
		if (e.getSource() == reset) {

			/*
			 * <!-- Enabled mọi thứ thành true và trả lại trạng thái ban đầu của chúng, bản
			 * thân enabled thành false-->
			 */
			humanVsHuman.setSelected(true);
			humanVsHuman.setEnabled(true);
			humanVsComputer.setEnabled(true);
			computerVsComputer.setEnabled(true);
			choiseColor.setEnabled(false);
			aIdepth.setEnabled(true);
			submit.setEnabled(true);
			reset.setEnabled(false);
			aIdepth.setSelectedIndex(3);
			choiseColor.setSelectedIndex(0);

			// <!-- Gọi tới hàm reset() của view (reset: xóa cờ, enabled false bàn cờ)-->
			view.reset();
		}
	}

	// <!-- reset when end game -->
	public void reset() {

		humanVsHuman.setSelected(true);
		humanVsHuman.setEnabled(true);
		humanVsComputer.setEnabled(true);
		computerVsComputer.setEnabled(true);
		choiseColor.setEnabled(false);
		aIdepth.setEnabled(true);
		submit.setEnabled(true);
		reset.setEnabled(false);
		aIdepth.setSelectedIndex(3);
		choiseColor.setSelectedIndex(0);

		// <!-- Gọi tới hàm reset() của view (reset: xóa cờ, enabled false bàn cờ)-->
		view.reset();
	}

	// <!-- Trả về màu cờ người chơi chọn trong chế độ người với máy-->
	public int getColorPlayer() {
		return choiseColor.getSelectedIndex();
	}

}
