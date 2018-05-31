package model;

import bean.LoginBean;

public class MyPageModel {
	/**
	 * 自己紹介画面編集画面ビジネスロジック
	 */
	public void authentication(LoginBean bean) {
		// 初期化
//		StringBuilder sb = new StringBuilder();

		System.out.println(bean.getUserName());

//		Connection conn = null;
//		String url = "jdbc:oracle:thin:@192.168.51.67";
//		String user = "DEV_TEAM_C";
//		String dbPassword = "C_DEV_TEAM";
//		// JDBCドライバーのロード
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			// 入れなかった場合
//			e.printStackTrace();
//		}
//		// 接続作成
//		try {
//			conn = DriverManager.getConnection(url, user, dbPassword);
//
//			// SQL作成
//			sb.append("SELECT");
//			sb.append(" user_no ");
//			sb.append(" ,user_name ");
//			sb.append("FROM ");
//			sb.append(" m_user ");
//			sb.append("WHERE");
//			sb.append(" user_name != '" + bean.getUserName() + "' ");
//
//			// SQL実行
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sb.toString());
//
//			while (rs.next()) {
//				// Listの初期化
//				// bean に送るようのリスト
//				ArrayList<String> setList = new ArrayList<String>();
//				// Listに追加
//				setList.add(rs.getString("user_no"));
//				setList.add(rs.getString("user_name"));
//				// Beanに追加
//				bean.setMember(setList);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			// sqlの接続は絶対に切断
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
