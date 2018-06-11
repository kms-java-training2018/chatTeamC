package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.LoginBean;
import bean.ShowProfileBean;

public class ShowProfileModel {

	public ShowProfileBean showProfileSearch(ShowProfileBean bean, LoginBean loginBean, String UserNo) {
		String userId = UserNo; // bean.getUserId();
		//String myPageText = "0001"; // bean.get();

		//
		int Unacquired;

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
			//SQL作成
			sb.append("SELECT ");
			sb.append(" MY_PAGE_TEXT ,USER_NAME ");
			sb.append("FROM ");
			sb.append(" M_USER ");
			sb.append("WHERE ");
			sb.append(" USER_NO in " + userId);

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {

				// Beanに追加
				bean.setName(rs.getString("user_name"));
				bean.setProfile(rs.getString("my_page_text"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			Unacquired = 0;
			bean.setUnacquired(Unacquired); //SQL取得失敗であればbeanに入れてServletに飛ばす

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
