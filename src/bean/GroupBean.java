package bean;

import java.util.ArrayList;

public class GroupBean {

	/** 会員名 */
	private ArrayList<String> userNameList = new ArrayList<String>();

	/** 会員番号 */
	private ArrayList<String> userNoList = new ArrayList<String>();

	/** エラーメッセージ */
	private String errorMessage;

	//ユーザー名の取得
	public ArrayList<String> getUserName() {
		return userNameList;
	}

	//ユーザー名の設定
	public void setUserName(String userName) {
		userNameList.add(userName);
	}

	//会員番号の取得
	public ArrayList<String> getUserNo() {
		return userNoList;
	}
	//会員番号の設定
	public void setUserNo(String userNo) {
		userNoList.add(userNo);
	}


	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



}
