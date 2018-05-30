package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.LoginBean;

/**
 * ログイン画面ビジネスロジック
 */
public class LoginModel {

	public LoginBean authentication(LoginBean bean) {
		// 初期化
		StringBuilder sb = new StringBuilder();
		String userId = bean.getUserId();
		String password = bean.getPassword();

		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";
		// JDBCドライバーのロード
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 入れなかった場合
			e.printStackTrace();
		}
		// 接続作成
		try {
			conn = DriverManager.getConnection(url, user, dbPassword);

			// SQL作成
			sb.append("SELECT ");
			sb.append(" user_no ");
			sb.append(" ,user_name ");
			sb.append("FROM ");
			sb.append(" m_user ");
			sb.append("WHERE ");
			sb.append(" user_id = '" + userId + "' ");
			sb.append(" AND password = '" + password + "'");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			//ID,パス不一致エラー
			if (!rs.next()) {
				if (!stringLengthCheck(password, 20)) {
					bean.setErrorMessage("20文字以上は受け付けません");
				}else if (!halfSizeCheck(password)) {
					bean.setErrorMessage("半角で入力してください");
				} else {
					bean.setErrorMessage("IDまたはパスワードが一致しませんでした。");
				}
			} else {
				bean.setUserNo(rs.getString("user_no"));
				bean.setUserName(rs.getString("user_name"));
				bean.setErrorMessage("");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// sqlの接続は絶対に切断
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return bean;
	}

	// 入力値のチェック
	public boolean stringLengthCheck(String input, int max) {
		// 何バイト分の長さであるかを取得
		int length = input.getBytes().length;
		if ((int) length > max) { // 最大文字数よりも多かった場合
			return false;
		}
		return true; // 許容内であった場合
	}

	// 半角チェック
	public boolean halfSizeCheck(String input) {
		//半角英数字
		int num;
		try {
			// 半角数字かをチェック
			num = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// 半角数字以外があればエラー
			return false;
		}
		return true;
	}
}
