package bean;

import java.util.ArrayList;

/**
 * @author onishi-yasumasa
 *
 */


public class GroupBean {


	/** エラーメッセージ */
	private String errorMessage;

	/**	グループ作成者のデータ */
	private String autherUserName;
	private String autherUserNo;

	private MemberBean autherBean;

	/**	グループ名 */
	private String groupName;

	/**	選択したユーザー名 */
	private String[] selectMember;

	/**	ログインビーン */
	private LoginBean loginBean;


	/** ユーザー一覧	 */
	private boolean getAllUserListJudge;

	/** メンバーリストのリスト */
	private ArrayList<MemberBean> memberBeanList = new ArrayList<MemberBean>();

	/** memberBean */
	private MemberBean memberBean;


	/**
	 *作成者のユーザー名を設定、ArrayListから検索し、そのindexからuserNameとuserNoを新しいListに追加<br>
	 *
	 * @param userName 受け取った作成者のユーザー名
	 */
	public void setAuther(MemberBean mb) {
		this.autherUserName = mb.getMemberName();
		this.autherBean = mb;
	}


	public void setMemberList(ArrayList<MemberBean> mb) {
		this.memberBeanList = mb;
	}

	public ArrayList<MemberBean> getMemberList(){
		return memberBeanList;
	}



	/**
	 * グループ作成者ユーザー名の取得<br>
	 * @return autherUserName グループ作成者のユーザー名
	 */
	public String getAutherName() {
		return autherUserName;
	}

	//
	/**
	 * グループ作成者の会員番号の取得<br>
	 * @return autherUserNo グループ作成者の会員番号
	 */
	public String getAutherNo() {
		return autherUserNo;
	}

	/**
	 * エラーメッセージを取得<br>
	 * @return errorMessage エラーメッセージ
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 受け取ったメッセージをエラーメッセージに設定<br>
	 * @param errorMessage 受け取った指定のエラーメッセージ
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	/**
	 * ユーザー取得出来たかどうかの成否判定の取得<br>
	 * @return getAllUserListJudge 成否判定
	 */
	public boolean isGetAllUserListJudge() {
		return getAllUserListJudge;
	}


	/**
	 * ユーザー取得できたかどうかの成否判定の設定<br>
	 * @param getAllUserListJudge 成否判定
	 */
	public void setGetAllUserListJudge(boolean getAllUserListJudge) {
		this.getAllUserListJudge = getAllUserListJudge;
	}



	/**
	 * グループ作成者の情報のセッターゲッター
	 * @return autherBean グループ作者の情報Bean
	 */
	public MemberBean getAutherBean() {
		return autherBean;
	}


	public void setAutherBean(MemberBean autherBean) {
		this.autherBean = autherBean;
	}



	/**
	 * グループ名の設定・取得
	 * @return groupName グループ名
	 */
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	/**
	 * 選択したユーザーの設定・取得
	 * @return selectMember 選択したメンバー
	 */
	public String[] getSelectMemberList() {
		return selectMember;
	}


	public void setSelectMemberList(String[] selectMember) {
		this.selectMember = selectMember;
	}


	/**
	 * LoginBeanの設定・取得
	 * @return loginBean ログインBean
	 */

	public LoginBean getLoginBean() {
		return loginBean;
	}


	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}


	/**
	 * memberBeanの設定・取得
	 * @return memberBean メンバーBean
	 */

	public MemberBean getMemberBean() {
		return memberBean;
	}


	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
}