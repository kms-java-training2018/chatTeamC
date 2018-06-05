package bean;

import java.util.ArrayList;

public class GroupMessageBean {
	// グループの名前
	private String GroupName;
	// 話している人名前
	private ArrayList<String> name = new ArrayList<String>();
	// 話しているメッセージ
	private ArrayList<String> text = new ArrayList<String>();
	// グループの話している人の番号
	private ArrayList<String> number = new ArrayList<String>();
	// 会話番号
	private ArrayList<String> messageNo = new ArrayList<String>();
	// グループの番号
	private String groupNo;

	// 消したい会話情報番号
	private String deleteMessageNo;

	// 送信メッセージ情報
	private String sendMessage;

	//入力チェック
	public int stringLengthCheck(String input) {
		//返すメッセージを設定
		int judgeByte = 0;

		// 何バイト分の長さであるかを取得
		int length = input.getBytes().length;
		// 最大バイト数の設定
		int max = 200;

		if ((int) length > max) { // 最大文字数よりも多かった場合
			judgeByte = 1;
			return judgeByte;
		}
		return judgeByte; // 許容内であった場合
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public ArrayList<String> getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number.add(number);
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupNo) {
		GroupName = groupNo;
	}

	public ArrayList<String> getName() {
		return name;
	}

	public void setName(String name) {
		this.name.add(name);
	}

	public ArrayList<String> getText() {
		return text;
	}

	public void setText(String text) {
		this.text.add(text);
	}

	public String getDeleteMessageNo() {
		return deleteMessageNo;
	}

	public void setDeleteMessageNo(String string) {
		this.deleteMessageNo = string;
	}

	public ArrayList<String> getMessageNo() {
		return messageNo;
	}

	public void setMessageNo(ArrayList<String> messageNo) {
		this.messageNo = messageNo;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
}