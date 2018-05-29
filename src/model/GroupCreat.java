package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.GroupBean;

public class GroupCreat {

	public GroupBean authentication() {
		// 初期化
		StringBuilder sb = new StringBuilder();
		;

		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";
		GroupBean gb = new GroupBean();
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

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());


			while (rs.next()) {

				/* 行からデータを取得 */
				gb.setUserNo(rs.getString("user_no"));
				gb.setUserName(rs.getString("user_name"));
				gb.setErrorMessage("");
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
		return gb;
	}

}
