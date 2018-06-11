package bean;

import java.util.ArrayList;

/**
 * @author onishi-yasumasa
 *
 */


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

	/** ユーザー一覧	 */
	private boolean getAllUserListJudge;


	/**
	 *作成者のユーザー名を設定、ArrayListから検索し、そのindexからuserNameとuserNoを新しいListに追加<br>
	 *
	 * @param userName 受け取った作成者のユーザー名
	 */
	public void setAuther(String userName) {
		this.autherUserName = userName;
		int autherIndex = userNameList.indexOf(autherUserName);
		//usernameが一応あるかでif
		if (autherIndex >= 0) {

			//作成者の名前から出したindexから、作成者のNoを全会員Noの値を検索してautherNoリストに設定
			autherUserNo = userNoList.get(autherIndex);

			//作成者のデータを全会員一覧から削除
			userNameList.remove(autherIndex);
			userNoList.remove(autherIndex);
		}
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
	 * 全ユーザー名のリストを取得<br>
	 * @return userNameList 全ユーザー名のリスト
	 */
	public ArrayList<String> getUserNameList() {
		return userNameList;
	}


	/**
	 * 全ユーザー名を全ユーザー名リストに追加する<br>
	 * @param userName 指定のユーザー名
	 */
	public void setUserName(String userName) {
		userNameList.add(userName);
	}

	/**
	 * 全会員番号リストの取得<br>
	 * @return userNoList 全会員番号リスト
	 */
	public ArrayList<String> getUserNo() {
		return userNoList;
	}


	/**
	 * 全ユーザーの会員番号のリストに追加<br>
	 * @param userNo 指定の会員番号
	 */
	public void setUserNo(String userNo) {
		userNoList.add(userNo);
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
}