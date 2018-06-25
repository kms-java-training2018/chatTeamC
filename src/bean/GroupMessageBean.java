package bean;

import java.util.ArrayList;

public class GroupMessageBean {

	// グループの名前
	private String GroupName;

	// 会話情報取得
	private ArrayList<TalkContentBean> talkContentBeanList = new ArrayList<TalkContentBean>();


	// グループの番号
	private String groupNo;
	// グループの作成者番号
	private String registUserNo;

	// 消したい会話情報番号
	private String deleteMessageNo;

	// 送信メッセージ情報
	private String sendMessage;

	// メンバー一覧リスト
	private ArrayList<String> memberList = new ArrayList<String>();


	public String getRegistUserNo() {
		return registUserNo;
	}

	public void setRegistUserNo(String registUserNo) {
		this.registUserNo = registUserNo;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}


	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupNo) {
		GroupName = groupNo;
	}


	public String getDeleteMessageNo() {
		return deleteMessageNo;
	}

	public void setDeleteMessageNo(String string) {
		this.deleteMessageNo = string;
	}


	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}


	public ArrayList<TalkContentBean> getTalkContentBeanList() {
		return talkContentBeanList;
	}

	public void setTalkContentBeanList(TalkContentBean talkContentBeanList) {
		this.talkContentBeanList.add(talkContentBeanList);
	}

	public ArrayList<String> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<String> memberList) {
		this.memberList = memberList;
	}
}