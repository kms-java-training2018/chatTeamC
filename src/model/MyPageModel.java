package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.LoginBean;
import bean.MyPageBean;

public class MyPageModel {
	/**
	 * 自己紹介画面編集画面ビジネスロジック
	 */
	public MyPageBean authentication(LoginBean loginBean,MyPageBean bean) {
		// 初期化
		StringBuilder sb = new StringBuilder();

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
			sb.append("SELECT");
			sb.append(" USER_NAME, ");
			sb.append(" MY_PAGE_TEXT ");
			sb.append("FROM ");
			sb.append(" M_USER ");
			sb.append("WHERE");
			sb.append(" USER_NO = '" + loginBean.getUserId() + "' ");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				bean.setName(rs.getString("USER_NAME"));
				bean.setMyProfile(rs.getString("MY_PAGE_TEXT"));
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
}
