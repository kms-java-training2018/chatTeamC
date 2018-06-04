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
	private ArrayList<ArrayList<String>> talkContent =new ArrayList<ArrayList<String>>();

	public int getToUserNo() {
		return toUserNo;
	}

	public void setToUserNo(int toUserNo) {
		this.toUserNo = toUserNo;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public ArrayList<ArrayList<String>> getTalkContent() {
		return talkContent;
	}

	public void setTalkContent(ArrayList<String> TalkContent) {
		talkContent.add(TalkContent);
	}

}
