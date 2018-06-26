package bean;

public class TalkContentBean {
	String userName;
	String message;
	String userNo;
	String messageNo;
	String indicateMember;

	public String getIndicateMember() {
		return indicateMember;
	}

	public void setIndicateMember(String indicateMember) {
		this.indicateMember = indicateMember;
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * @param userNo
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * @return
	 */
	public String getMessageNo() {
		return messageNo;
	}

	/**
	 * @param messageNo
	 */
	public void setMessageNo(String messageNo) {
		this.messageNo = messageNo;
	}
}