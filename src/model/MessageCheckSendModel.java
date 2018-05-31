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
//		Integer userNo = Integer.parseInt(loginBean.getUserNo());
		Integer toUserNo = bean.getToUserNo();
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
			/**
			 *会話情報テーブルから
			 *自会員と送信先会員番号間の
			 *やりとりの内容を取り出す。
			 * */

			/*
			 * SQL文内容（受け取るものをまとめたリスト作成）
			 * 送信者の番号、送信者の名前、メッセージ内容、デリートフラグのリストを作成
			 * */
			sb.append(" SELECT ");
			sb.append(" MU.USER_NO, ");
			sb.append(" MU.USER_NAME, ");
			sb.append(" TM.MESSAGE, ");
			sb.append(" TM.DELETE_FLAG ");
			/*
			 * SQL文内容（受け取り場所）
			 * 会員マスタ、会話情報テーブルから
			 * */
			sb.append(" FROM ");
			sb.append(" T_MESSAGE_INFO TM ");
			sb.append(" ,M_USER MU ");
			/*
			 * SQL文内容（条件）
			 * 『「送信者Noが自分の会員Noと一致かつ、送信先会員が受け取った相手の会員Noと一致」
			 * または「送信者Noが相手の会員Noと一致かつ、送信先会員が自分の会員Noと一致」』
			 * （自分と相手会員間の会話情報のみ受け取る）
			 * かつ『会員Noと送信者が一致している』
			 * （会話情報の重複排除）
			 * かつ『デリートフラグが0（論理削除されていない）』
			 * */
			sb.append(" WHERE ");
			sb.append(" ((TM.SEND_USER_NO = "+ loginBean.getUserNo() +" AND TM.TO_SEND_USER = "+ toUserNo + ") ");
			sb.append(" OR (TM.SEND_USER_NO = " + toUserNo +" AND TM.TO_SEND_USER = " + loginBean.getUserNo() + ")) ");
			sb.append(" AND (MU.USER_NO = SEND_USER_NO) ");
			sb.append(" AND (TM.DELETE_FLAG = 0) ");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				// Listの初期化
				// bean に送るようのリスト
				ArrayList<String> setList = new ArrayList<String>();
				// Listに追加
				setList.add(rs.getString("USER_NAME"));
				setList.add(rs.getString("MESSAGE"));
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
