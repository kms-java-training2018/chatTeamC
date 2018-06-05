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
	private ArrayList<ArrayList<String>> talkContent = new ArrayList<ArrayList<String>>();

	// 送信メッセージ情報
	private String sendMessage;

	// 消したい会話情報番号
	private int deleteMessageNo;

	//入力チェック
	public int stringLengthCheck(String input) {
		//返すメッセージを設定
		int judgeByte = 0;

		// 何バイト分の長さであるかを取得
		int length = input.getBytes().length;
		System.out.println(length);
		// 最大バイト数の設定
		int max = 200;

		if ((int) length > max) { // 最大文字数よりも多かった場合
			judgeByte = 1;
			return judgeByte;
		}
		return judgeByte; // 許容内であった場合
	}

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
