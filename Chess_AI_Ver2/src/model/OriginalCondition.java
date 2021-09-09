package model;

public class OriginalCondition {

	/*
	 * <!-- 3 biến để cho biến xem xe trái, xe phải và vua đã được di chuyển hay
	 * chưa, mục đích kiểm tra việc nhập thành-->
	 */
	private boolean leftRook;
	private boolean rightRook;
	private boolean king;

	// <!-- Contrustor rỗng -->
	public OriginalCondition() {
	}

	// <!-- Contrustor nhận vào full true -->
	public OriginalCondition(boolean leftRook, boolean rightRook, boolean king) {
		this.leftRook = leftRook;
		this.rightRook = rightRook;
		this.king = king;

	}

	// <!-- getter and setter -->

	public boolean isLeftRook() {
		return leftRook;
	}

	public void setLeftRook(boolean leftRook) {
		this.leftRook = leftRook;
	}

	public boolean isRightRook() {
		return rightRook;
	}

	public void setRightRook(boolean rightRook) {
		this.rightRook = rightRook;
	}

	public boolean isKing() {
		return king;
	}

	public void setKing(boolean king) {
		this.king = king;
	}

	// <!-- End getter and setter -->

	// <!-- Kiểm tra xem 3 con đều di chuyển hết chưa -->
	public boolean isMoveAll() {
		return isKing() && isLeftRook() && isRightRook();
	}

	@Override
	public String toString() {
		return "OriginalCondition [leftRook=" + leftRook + ", rightRook=" + rightRook + ", king=" + king + "]";
	}

}
