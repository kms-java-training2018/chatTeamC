package bean;

public class DeleteMessageBean {
	// 消したい会話情報番号
	private int deleteMessageNo;
	private int secessionMessageNo;

	public int getDeleteMessageNo() {
		return deleteMessageNo;
	}

	public void setDeleteMessageNo(int deleteMessageNo) {
		this.deleteMessageNo = deleteMessageNo;

	}

	public int getSecessionMessageNo() {
		return secessionMessageNo;
	}

	public void setSecessionMessageNo(int secessionMessageNo) {
		this.secessionMessageNo = secessionMessageNo;
	}
}