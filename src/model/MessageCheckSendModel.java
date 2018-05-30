package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.LoginBean;
import bean.MessageCheckBean;

/**
 * 設計書03　メッセージ確認・送信機能
 * 飯島
 * */
public class MessageCheckSendModel {
	public MessageCheckBean authentication(MessageCheckBean bean, LoginBean loginBean) {
		// 初期化
		StringBuilder sb = new StringBuilder();
		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";
		String userNo = loginBean.getUserNo();
		String toUserNo = bean.getToUserNo();
//		int noDataFlag = 0;
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
			/*
			 *会話情報テーブルから
			 *自会員と送信先会員番号間の
			 *やりとりの内容を取り出す。
			 * */
			sb.append("SELECT ");
			sb.append(" message");
			sb.append("FROM ");
			sb.append(" t_message_info ");
			sb.append("WHERE ");
			sb.append("((send_user_no == ' " + userNo + "' ) ");
			sb.append("AND (to_send_user_no == ' " + toUserNo + "' )) ");
			sb.append("OR ((send_user_no == ' " + toUserNo + "' ) ");
			sb.append("AND (to_send_user_no == ' " + userNo + "' ))");
//			sb.append("EXCEPTION ");
//			sb.append("WHEN ");
//			sb.append("NO_DATA_FOUND THEN ");
//			sb.append(noDataFlag + " := +1 ");


			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				// Listの初期化
				// bean に送るようのリスト
				ArrayList<String> setList = new ArrayList<String>();
				// Listに追加
				setList.add(rs.getString("user_name"));
				setList.add(rs.getString("message"));
				// Beanに追加
				bean.setTalkContent(setList);
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
