package bean;

import java.util.ArrayList;

/**
 * DirectMessage,MessageCheck用
 * 相手会員番号、会話情報を取得するためのBean
 * */
public class MessageCheckBean {
	// 相手会員番号
	private int toUserNo;

	// 相手表示名
	private String toUserName;

	// 会話情報取得
	private ArrayList<TalkContentBean> talkContentBeanList = new ArrayList<TalkContentBean>();

	// 送信メッセージ情報
	private String sendMessage;

	// 消したい会話情報番号
	private int deleteMessageNo;

	// 相手会員番号存在チェック
	private int checkToUserNo;

	public int getCheckToUserNo() {
		return checkToUserNo;
	}

	public void setCheckToUserNo(int checkToUserNo) {
		this.checkToUserNo = checkToUserNo;
	}

	/**
	 * 現在セットされている相手の会員番号を返す
	 */
	public int getToUserNo() {
		return toUserNo;
	}

	/**
	 * 相手の会員番号をセット
	 */
	public void setToUserNo(int toUserNo) {
		this.toUserNo = toUserNo;
	}

	/**
	 * 現在セットされている相手の名前を返す
	 */
	public String getToUserName() {
		return toUserName;
	}

	/**
	 * 相手の名前をセット
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * トークの内容を返す
	 */
	public ArrayList<TalkContentBean> getTalkContentBeanList() {
		return talkContentBeanList;
	}

	/**
	 * トークの内容をセット
	 */
	public void setTalkContentBeanList(TalkContentBean talkContentList) {
		this.talkContentBeanList.add(talkContentList);
	}

	/**
	 * 送信メッセージの内容を返す
	 */
	public String getSendMessage() {
		return sendMessage;
	}

	/**
	 * 送信メッセージの内容をセット
	 */
	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	/**
	 * 削除するメッセージの会話情報番号を返す
	 */
	public int getDeleteMessageNo() {
		return deleteMessageNo;
	}

	/**
	 * 削除するメッセージの会話情報番号をセット
	 */
	public void setDeleteMessageNo(int deleteMessageNo) {
		this.deleteMessageNo = deleteMessageNo;
	}
}