package bean;

import java.util.ArrayList;

public class GroupMessageBean {
	private String GroupName;

	private ArrayList<String> name = new ArrayList<String>();
	private ArrayList<String> text = new ArrayList<String>();
	private ArrayList<String> number = new ArrayList<String>();
	private ArrayList<String> messageNo = new ArrayList<String>();

	// 消したい会話情報番号
	private String deleteMessageNo;

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
}