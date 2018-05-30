package bean;

/**
 * DirectMessage,MessageCheck用
 * 相手会員番号を取得するためのBean
 * */
public class MessageCheckBean {
	// 相手会員番号
	private String toUserNo;

	// 相手表示名
	private String toUserName;

	public String getToUserNo() {
		return toUserNo;
	}

	public void setToUserNo(String toUserNo) {
		this.toUserNo = toUserNo;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
}
