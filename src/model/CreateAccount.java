package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.ResistAccountBean;

/**
 * グループ作成機能model<br>
 *
 * @author onishi-yasumasa
 * @version 1.0
 */
public class CreateAccount {

	public ResistAccountBean checkData(ResistAccountBean rb) {
		ResistAccountBean newRb = rb;
		CheckCharacter checkChara = new CheckCharacter();
		//rbの中を全て定義
		String password = newRb.getPassword();
		String userId = newRb.getUserId();
		String userName = newRb.getUserName();
		String profile = newRb.getProfile();
		//全ての結果まとめ用boolean
		boolean checkJudge = false;

		//全てチェックに通す
		boolean passResult = checkChara.halfSizeCheck(password);
		boolean passJudgeByte = checkChara.stringLengthCheck(password, 20);
		boolean passExsist = checkChara.spaceCheck(password);

		boolean idResult = checkChara.halfSizeCheck(userId);
		boolean idJudgeByte = checkChara.stringLengthCheck(userId, 20);
		boolean idExsist = checkChara.spaceCheck(userId);

		boolean nameJudgeByte = checkChara.stringLengthCheck(userName, 30);
		boolean nameExsist = checkChara.spaceCheck(userName);

		boolean profileJudgeByte = checkChara.stringLengthCheck(profile, 100);
		boolean profileExsist = checkChara.spaceCheck(profile);

		//結果によってエラーメッセージをセット(id,pass)
		if (passExsist == false || idExsist == false) {
			newRb.setErrorMessage("ID、パスワードが、未記入、または空白のみです");
		} else if (passResult == false || idResult == false) {
			newRb.setErrorMessage("ID、パスワードは半角で入力してください");
		} else if (passJudgeByte == false || idJudgeByte == false) {
			newRb.setErrorMessage("ID、パスワードは20文字以内です");

		}
		//結果によってエラーメッセージをセット(name,profile)
		if (nameJudgeByte == false || profileJudgeByte == false) {
			newRb.setProfileErrorMessage("ユーザーネームは30文字、プロフィールは100文字以内です");
		} else if (nameExsist == false || profileExsist == false) {
			newRb.setProfileErrorMessage("ユーザーネーム、プロフィールが未記入、または空白のみです");
		}

		//全ての結果からbooleanをセット
		if (newRb.getErrorMessage() != null || newRb.getProfileErrorMessage() != null) {
			newRb.setCheckJudge(checkJudge);
		} else {
			checkJudge = true;
			newRb.setCheckJudge(checkJudge);
		}

		return newRb;

	}

	/**
	 * グループ登録を行うメソッド<br>
	 * @return creatJudge メンバー登録成否
	 */
	public ResistAccountBean resistUser(ResistAccountBean rb) {
		// 初期化
		StringBuilder sb = new StringBuilder();
		ResistAccountBean newRb = rb;

		//成功判定用変数
		String resistJudge = "";

		//gbからautherNoを
		String userId = rb.getUserId();
		String password = rb.getPassword();
		String userName = rb.getUserName();
		String profile = rb.getProfile();

		//DB接続
		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";

		// JDBCドライバーのロード
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 入れなかった場合
			//e.printStackTrace();
			resistJudge = "登録できませんでした";
		}
		// 接続作成
		try {
			conn = DriverManager.getConnection(url, user, dbPassword);

			// SQLから全会員情報を取得
			sb.append("insert ");
			sb.append(" into ");
			sb.append(" M_USER( ");
			sb.append("USER_NO ");
			sb.append(", USER_ID ");
			sb.append(", PASSWORD");
			sb.append(", USER_NAME");
			sb.append(", MY_PAGE_TEXT");
			sb.append(", REGIST_DATE");
			sb.append(")");
			sb.append("values");
			sb.append("(USER_SEQ.NEXTVAL");
			sb.append(",'" + userId + "'");
			sb.append(",'" + password + "'");
			sb.append(",'" + userName + "'");
			sb.append(",'" + profile + "'");
			sb.append(", sysdate)");

			// SQL実行してrsにセット
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sb.toString());

			if (rs != 1) {
				resistJudge = "登録できませんでした。";
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			//test
			resistJudge = "登録できませんでした。";

			// sqlの接続は絶対に切断
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
				resistJudge = "登録できませんでした。";
			}
		}
		newRb.setSqlErrorMessage(resistJudge);
		return newRb;
	}

	/**
	 * グループにメンバー登録を行うメソッド<br>
	 *
	 * @param list 選択したユーザー
	 * @return resistJudge メンバー登録成否
	 */

}
