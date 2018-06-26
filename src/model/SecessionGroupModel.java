package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.LoginBean;

public class SecessionGroupModel {
	public void SecessionGroup(LoginBean bean, String groupNo) {
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

			System.out.println("ユーザーナンバー:" + bean.getUserNo());
			System.out.println("グループナンバー:" + groupNo);

			// SQL作成
			sb.append(" UPDATE ");
			sb.append(" T_GROUP_INFO ");
			sb.append(" SET ");
			sb.append(" OUT_FLAG = 1 ");
			sb.append(" WHERE ");
			sb.append(" USER_NO = " + bean.getUserNo());
			sb.append(" AND GROUP_NO = " + groupNo);
			// SQL実行
			Statement stmt = conn.createStatement();
			int num = stmt.executeUpdate(sb.toString());
			System.out.println("更新数:" + num);
			// 脱退の成否を確認
			if (num == 0) {
				bean.setErrorMessage("グループを脱退出来ませんでした");
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
	}
}