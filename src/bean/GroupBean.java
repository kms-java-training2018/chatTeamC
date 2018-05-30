package bean;

import java.util.ArrayList;

public class GroupBean {

	/** 会員名 */
	private ArrayList<String> userNameList = new ArrayList<String>();

	/** 会員番号 */
	private ArrayList<String> userNoList = new ArrayList<String>();

	/** エラーメッセージ */
	private String errorMessage;

	/**	グループ作成者のデータ */
	private String autherUserName;
	private String autherUserNo;

	/**	グループ作成者のリスト */
	private String[] autherName;
	private String[] autherNo;

	//作成者のユーザー名を設定、ArrayListから検索し、そのindexからuserNameとuserNoを新しいListに追加
	public void setAuther(String userName) {
		this.autherUserName = userName;
		int autherIndex = userNameList.indexOf("autherUserName");
		//usernameが一応あるかでif
		if (autherIndex > 0) {

			//最初に作成者の名前を作成者名前リストに代入
			autherName[0] = autherUserName;
			//作成者の名前から出したindexから、作成者のNoを全会員Noの値を検索してautherNoリストに設定
			autherUserNo = userNoList.get(autherIndex);
			autherNo[0] = autherUserNo;

			//作成者のデータを全会員一覧から削除
			userNameList.remove(autherIndex);
		}
	}

	//作成者Nameの取得
	public String getAutherName() {
		return autherUserName;
	}

	//作成者Nameの取得
	public String getAutherNo() {
		return autherUserNo;
	}

	//全ユーザー名の取得
	public ArrayList<String> getUserName() {
		return userNameList;
	}

	//全ユーザー名の設定
	public void setUserName(String userName) {
		userNameList.add(userName);
	}

	//全会員番号の取得
	public ArrayList<String> getUserNo() {
		return userNoList;
	}

	//全会員番号の設定
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
