package bean;

import java.util.ArrayList;

/**
 * DirectMessage,MessageCheck用
 * 相手会員番号、会話情報を取得するためのBean
 * */
/**
 * @author iijima-naoyuki
 *
 */
public class MessageCheckBean {
	// 相手会員番号
	private int toUserNo;

	// 相手表示名
	private String toUserName;

	// 会話情報取得
	//	private ArrayList<ArrayList<String>> talkContent = new ArrayList<ArrayList<String>>();
	private ArrayList<TalkContentBean> talkContentBeanList = new ArrayList<TalkContentBean>();

	// 送信メッセージ情報
	private String sendMessage;

	// 消したい会話情報番号
	private int deleteMessageNo;

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

	//	/**
	//	 * @return
	//	 */
	//	public ArrayList<ArrayList<String>> getTalkContent() {
	//		return talkContent;
	//	}
	//
	//
	//	/**
	//	 * @param TalkContent
	//	 */
	//	public void setTalkContent(ArrayList<String> TalkContent) {
	//		talkContent.add(TalkContent);
	//	}

	/**
	 * トークの内容を返す
	 */
	public ArrayList<TalkContentBean> getTalkContentBeanList() {
		return talkContentBeanList;
	}

	/**
	 * トークの内容を入れる
	 */
	public void setTalkContentBeanList(TalkContentBean talkContentList) {
		this.talkContentBeanList.add(talkContentList);
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		System.out.println(sendMessage);
		this.sendMessage = sendMessage;
		System.out.println(this.sendMessage);
	}

	public int getDeleteMessageNo() {
		return deleteMessageNo;
	}

	public void setDeleteMessageNo(int deleteMessageNo) {
		this.deleteMessageNo = deleteMessageNo;
	}
}