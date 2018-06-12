package bean;

public class LatestGroupMessageBean {
	/** グループの名前 */
	String groupName;

	/** グループナンバー */
	String groupNo;

	/** 最新テキスト */
	String latestMessage;

	/** グループの名前取得 */
	public String getGroupName() {
		return groupName;
	}

	/** グループの名前設定 */
	public void setGroupName(String userName) {
		this.groupName = userName;
	}

	/** グループナンバー設定 */
	public String getGroupNo() {
		return groupNo;
	}

	/** グループナンバー設定 */
	public void setGroupNo(String userNo) {
		this.groupNo = userNo;
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
