package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.LoginBean;
import bean.MessageCheckBean;
import bean.TalkContentBean;

/**
 * 設計書03　メッセージ確認・送信機能
 * 飯島
 * */

/**
 * authentication（会話情報取得・表示処理）
 * */
public class MessageCheckSendModel {
	public MessageCheckBean getTalkContent(MessageCheckBean bean, LoginBean loginBean) {
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
			 * 送信者の番号、送信者の名前、会話情報番号、会話内容、デリートフラグのリストを作成
			 * */
			sb.append(" SELECT ");
			sb.append(" MU.USER_NO, ");
			sb.append(" MU.USER_NAME, ");
			sb.append(" TM.MESSAGE_NO, ");
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
			sb.append(" ((TM.SEND_USER_NO = " + loginBean.getUserNo() + " AND TM.TO_SEND_USER = " + toUserNo + ") ");
			sb.append(" OR (TM.SEND_USER_NO = " + toUserNo + " AND TM.TO_SEND_USER = " + loginBean.getUserNo() + ")) ");
			sb.append(" AND (MU.USER_NO = SEND_USER_NO) ");
			sb.append(" AND (TM.DELETE_FLAG = 0) ");
			/*
			 * SQL文内容（並び替え）
			 * 登録日の昇順に並び替える
			 * */
			sb.append(" ORDER BY ");
			sb.append(" TM.REGIST_DATE ASC ");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				// Listの初期化
				// bean に送る用のリスト
				//ArrayList<String> setList = new ArrayList<String>();
				TalkContentBean talkContentBean = new TalkContentBean();
				// Listに追加（発言者名<0>、会話内容<1>、発言者の会員番号<2>、会話情報番号<3>）
				talkContentBean.setUserName(rs.getString("USER_NAME"));
				talkContentBean.setMessage(rs.getString("MESSAGE"));
				talkContentBean.setUserNo(rs.getString("USER_NO"));
				talkContentBean.setMessageNo(rs.getString("MESSAGE_NO"));
				// Beanに追加
				bean.setTalkContentBeanList(talkContentBean);
			}

			//名前取得用
			sb = new StringBuilder();
			sb.append(" SELECT ");
			sb.append(" MU.USER_NAME ");
			sb.append(" FROM ");
			sb.append(" M_USER MU ");
			sb.append(" WHERE ");
			sb.append(" MU.USER_NO = " + toUserNo);

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				bean.setToUserName(rs.getString("USER_NAME"));
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

	/**
	 * sendMessage（メッセージの送信処理）
	 * */
	public MessageCheckBean sendMessage(MessageCheckBean bean, LoginBean loginBean) {
		// 初期化
		StringBuilder sb = new StringBuilder();
		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";
		Integer userNo = Integer.parseInt(loginBean.getUserNo());
		Integer toUserNo = bean.getToUserNo();
		String sendMessage = bean.getSendMessage();
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
			 * SQL文内容（データベースに登録する内容）
			 * 会話情報番号、送信者の会員番号、メッセージ内容、
			 * 送信先の会員番号、デリートフラグ、記入日
			 * */
			sb.append(" INSERT INTO T_MESSAGE_INFO( ");
			sb.append(" MESSAGE_NO, ");
			sb.append(" SEND_USER_NO, ");
			sb.append(" MESSAGE, ");
			sb.append(" TO_SEND_USER, ");
			sb.append(" DELETE_FLAG, ");
			sb.append(" REGIST_DATE ) ");
			/*
			 * SQL文内容（データベースに登録する値）
			 * 会話情報番号（自動採番）
			 * 、送信者の会員番号（userNo変数内）
			 * 、メッセージ内容（sendMessage変数内）
			 * 、送信先の会員番号（toUserNo変数内）
			 * 、デリートフラグ（初期値0）
			 * 、記入日（sysdateにて自動入力）
			 * */
			sb.append(" VALUES ( ");
			sb.append(" MESSAGE_SEQ.NEXTVAL ");
			sb.append(" ," + userNo);
			sb.append(" ,'" + sendMessage + "' ");
			sb.append(" ," + toUserNo);
			sb.append(" ,0 ");
			sb.append(" , sysdate)  ");

			// SQL実行
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sb.toString());

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

	/**
	 * sendMessage（メッセージの送信処理）
	 * */
	public MessageCheckBean sendGroupMessage(MessageCheckBean bean, LoginBean loginBean) {
		// 初期化
		StringBuilder sb = new StringBuilder();
		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";
		Integer userNo = Integer.parseInt(loginBean.getUserNo());
		Integer toUserNo = bean.getToUserNo();
		String sendMessage = bean.getSendMessage();
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
			 * SQL文内容（データベースに登録する内容）
			 * 会話情報番号、送信者の会員番号、メッセージ内容、
			 * 送信先の会員番号、デリートフラグ、記入日
			 * */
			sb.append(" INSERT INTO T_MESSAGE_INFO( ");
			sb.append(" MESSAGE_NO, ");
			sb.append(" SEND_USER_NO, ");
			sb.append(" MESSAGE, ");
			sb.append(" TO_SEND_GROUP_NO, ");
			sb.append(" DELETE_FLAG, ");
			sb.append(" REGIST_DATE ) ");
			/*
			 * SQL文内容（データベースに登録する値）
			 * 会話情報番号（自動採番）
			 * 、送信者の会員番号（userNo変数内）
			 * 、メッセージ内容（sendMessage変数内）
			 * 、送信先の会員番号（toUserNo変数内）
			 * 、デリートフラグ（初期値0）
			 * 、記入日（sysdateにて自動入力）
			 * */
			sb.append(" VALUES ( ");
			sb.append(" MESSAGE_SEQ.NEXTVAL ");
			sb.append(" ," + userNo);
			sb.append(" ,'" + sendMessage + "' ");
			sb.append(" ," + toUserNo);
			sb.append(" ,0 ");
			sb.append(" , sysdate)  ");

			// SQL実行
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sb.toString());

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
