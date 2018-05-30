package bean;

import java.util.ArrayList;

public class MainPageBean {

	/** メンバー一覧 */
	private ArrayList<ArrayList<String>> member = new ArrayList<ArrayList<String>>();

	/** メンバー一覧 + 会話 */
	private ArrayList<ArrayList<String>> memberTalk = new ArrayList<ArrayList<String>>();

	/** グループ一覧 */
	private ArrayList<ArrayList<String>> Growp = new ArrayList<ArrayList<String>>();

	public ArrayList<ArrayList<String>> getMember() {
		return member;
	}

	public void setMember(ArrayList<String> member) {
		this.member.add(member);
	}

	public ArrayList<ArrayList<String>> getMemberTalk() {
		return memberTalk;
	}

	public void setMemberTalk(ArrayList<String> memberTalk) {
		this.memberTalk.add(memberTalk);
	}

	public ArrayList<ArrayList<String>> getGrowp() {
		return Growp;
	}

	public void setGrowp(ArrayList<String> growp) {
		Growp.add(growp);
	}

}
