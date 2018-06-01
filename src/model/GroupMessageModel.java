package model;

/**
 * グループメッセージ画面ビジネスロジック
 */
public class GroupMessageModel {

//	public LoginBean authentication(String[] No) {
//		// 初期化
//		StringBuilder sb = new StringBuilder();
//		String group_no = bean.getGroup_no();
//		String user_no = bean.getMember_no();
//
//		//GroupMessageBean
//		GroupMessageBean groupMessageBean = new GroupMessageBean();
//
//		// 接続作成
//		try {
//			conn = DriverManager.getConnection(url, user, dbPassword);
//
//			// SQL作成（会員番号、グループ番号）
//			sb.append("SELECT ");
//			sb.append(" group_no,user_no ");
//			sb.append("FROM ");
//			sb.append(" t_group_info ");
//			sb.append(" WHERE ");
//			sb.append(" my_group_no = '" + my_group_no + "' ");
//			sb.append(" de_group_no = '" + de_group_no + "'");
//			sb.append(" my_user_no = '" + my_user_no + "'");
//			sb.append(" de_user_no = '" + de_user_no + "'");
//
//			// SQL実行（会員番号、グループ番号）
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sb.toString());
//
//			if (!rs.next()) {
//				bean.setErrorMessage("会員番号、グループ番号が一致しませんでした。");
//			} else {
//				bean.setUserNo(rs.getString("user_no"));
//				bean.setUserName(rs.getString("user_name"));
//				bean.setErrorMessage("");
//			}
//
//			// SQL作成（グループ会話情報）
//			sb.append("SELECT ");
//			sb.append(" message ");
//			sb.append("FROM ");
//			sb.append(" t_message_info ");
//			sb.append(" WHERE ");
//			sb.append(" group_message = '" + group_message + "' ");
//
//			// SQL実行（グループ会話情報）
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(sb.toString());
//
//			if (!rs.next()) {
//				bean.setErrorMessage("会話情報を取得出来ませんでした。");
//			} else {
//				bean.setUserNo(rs.getString("user_no"));
//				bean.setUserName(rs.getString("user_name"));
//				bean.setErrorMessage("");
//			}
//
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
//
//		return bean;
//
//		// もしもグループ番号、会員番号が取得出来なければエラー
//		if (group_no && user_no.getAttribute)
//			;
//
//		// もしも会話情報が取得出来なければエラー
//		if (group_message.getAttribute)
//			;
//	}
}