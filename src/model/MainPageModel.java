package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.LoginBean;
import bean.MainPageBean;

public class MainPageModel {
	/**
	 * メイン画面ビジネスロジック
	 */
	public MainPageBean authentication(MainPageBean bean, LoginBean loginBean) {
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
			sb.append(" user_no ");
			sb.append(" ,user_name ");
			sb.append("FROM ");
			sb.append(" m_user ");
			sb.append("WHERE");
			sb.append(" user_name != '" + loginBean.getUserName() + "' ");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				// Listの初期化
				// bean に送るようのリスト
				ArrayList<String> setList = new ArrayList<String>();
				// Listに追加
				setList.add(rs.getString("user_no"));
				setList.add(rs.getString("user_name"));
				// Beanに追加
				bean.setMember(setList);
			}
			// 個人チャット
			sb = new StringBuilder();
			// SQL作成
			sb.append("SELECT");
			sb.append(" MU.USER_NAME ");
			sb.append(" ,TM.MESSAGE ");
			sb.append("FROM ");
			sb.append(" M_USER MU ");
			sb.append(" ,T_MESSAGE_INFO TM  ");
			sb.append("INNER JOIN (");
			sb.append(" SELECT ");
			sb.append(" E.USER_NAME NN ");
			sb.append(" , MAX(F.MESSAGE_NO) G  ");
			sb.append(" FROM ");
			sb.append(" T_MESSAGE_INFO F ");
			sb.append(" , M_USER E  ");
			sb.append(" WHERE ");
			sb.append(" ( F.SEND_USER_NO = 1 ");
			sb.append(" AND F.TO_SEND_USER IS NOT NULL ");
			sb.append(" AND F.TO_SEND_USER = E.USER_NO ) ");
			sb.append(" OR ");
			sb.append(" ( F.TO_SEND_USER = 1 ");
			sb.append(" AND F.SEND_USER_NO = E.USER_NO )  ");
			sb.append(" GROUP BY ");
			sb.append(" E.USER_NAME ");
			sb.append(" ) INN ");
			sb.append(" ON TM.MESSAGE_NO = INN.G ");
			sb.append(" WHERE NN = MU.USER_NAME ");

			// SQL実行
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				// Listの初期化
				// bean に送るようのリスト
				ArrayList<String> setList = new ArrayList<String>();
				// Listに追加
				setList.add(rs.getString("user_name"));
				setList.add(rs.getString("message"));
				// Beanに追加
				bean.setMemberTalk(setList);
			}

			// グループデータ/
			sb = new StringBuilder();
			// SQL作成
			sb.append("SELECT");
			sb.append(" user_no ");
			sb.append(" ,user_name ");
			sb.append("FROM ");
			sb.append(" m_user ");
			sb.append("WHERE");
			sb.append(" user_name = '" + loginBean.getUserName() + "' ");

			// SQL実行
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				// Listの初期化
				// bean に送るようのリスト
				ArrayList<String> setList = new ArrayList<String>();
				// Listに追加
				setList.add(rs.getString("user_no"));
				setList.add(rs.getString("user_name"));
				// Beanに追加
				bean.setGrowp(setList);
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
