package bean;

public class LatestMenberMessageBean {

	/** ユーザーの名前 */
	String userName;

	/** ユーザーナンバー */
	String userNo;

	/** 最新テキスト */
	String latestMessage;

	/** ユーザーの名前取得 */
	public String getUserName() {
		return userName;
	}

	/** ユーザーの名前設定 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** ユーザーナンバー設定 */
	public String getUserNo() {
		return userNo;
	}

	/** ユーザーナンバー設定 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/** 最新テキスト取得 */
	public String getLatestMessage() {
		return latestMessage;
	}

	/** 最新テキスト設定 */
	public void setLatestMessage(String latestMessage) {
		this.latestMessage = latestMessage;
	}

}
