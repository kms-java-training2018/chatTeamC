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

			// 個人チャットの表示用リスト作成
			for (ArrayList<String> menber : bean.getMember()) {

				// bean に送るようのリスト
				ArrayList<String> setList = new ArrayList<String>();
				// テキストの最新番号を初期化
				int maxtext = 0;
				// 挿入するテキストを初期化
				String text = "会話を始めましょう！";

				// 個人チャット
				sb = new StringBuilder();
				// SQL作成
				sb.append("SELECT");
				sb.append(" MU.USER_NAME ");
				sb.append(" ,TM.MESSAGE ");
				sb.append(" ,TM.DELETE_FLAG ");
				sb.append(" ,TM.MESSAGE_NO ");
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
				sb.append(" ( F.SEND_USER_NO = " + loginBean.getUserNo());
				sb.append(" AND F.TO_SEND_USER IS NOT NULL ");
				sb.append(" AND F.TO_SEND_USER = E.USER_NO ");
				sb.append(" AND F.DELETE_FLAG = 0 ");
				sb.append(" AND E.USER_NO = " + menber.get(0) + " ) ");
				sb.append(" OR ");
				sb.append(" ( F.TO_SEND_USER =" + loginBean.getUserNo());
				sb.append(" AND F.SEND_USER_NO = E.USER_NO ");
				sb.append(" AND F.DELETE_FLAG = 0 ");
				sb.append(" AND E.USER_NO = " + menber.get(0) + " ) ");
				sb.append(" GROUP BY ");
				sb.append(" E.USER_NAME ");
				sb.append(" ) INN ");
				sb.append(" ON TM.MESSAGE_NO = INN.G ");
				sb.append(" WHERE NN = MU.USER_NAME ");

				// SQL実行
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sb.toString());

				while (rs.next()) {
					// メッセージが最新でなおかつ削除フラグが立っていなければ
					// 最新文章として登録
					if (maxtext < rs.getInt("MESSAGE_NO")
							&& 0 == rs.getInt("DELETE_FLAG")) {
						maxtext = rs.getInt("MESSAGE_NO");
						text = rs.getString("message");
					}
				}
				// Listに追加
				setList.add(menber.get(1));
				setList.add(text);
				setList.add(menber.get(0));
				// Beanに追加
				bean.setMemberTalk(setList);
			}
			// text
			ArrayList<String> setListText = new ArrayList<String>();
			// 名前
			ArrayList<String> name = new ArrayList<String>();
			// ナンバー
			ArrayList<String> number = new ArrayList<String>();

			// 挿入用テキストの初期化
			String text = "会話を始めましょう！";

			// どのグループメンバーがいるかの設定
			sb = new StringBuilder();
			// SQL作成
			sb.append(" SELECT DISTINCT ");
			sb.append(" MG.GROUP_NAME ");
			sb.append(" , MG.GROUP_NO  ");
			sb.append(" FROM ");
			sb.append(" T_GROUP_INFO TG ");
			sb.append(" , M_GROUP MG ");
			sb.append(" , T_MESSAGE_INFO TM ");
			sb.append(" WHERE ");
			sb.append(" TG.GROUP_NO = MG.GROUP_NO ");
			sb.append(" AND TG.USER_NO = " + loginBean.getUserNo());
			sb.append(" ORDER BY MG.GROUP_NO ");

			// SQL実行
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sb.toString());

			// 項目の追加
			while (rs.next()) {
				// Listの初期化

				// Listに追加
				name.add(rs.getString("GROUP_NAME"));
				number.add(rs.getString("GROUP_NO"));
			}

			for (int i = 0; i < number.size(); i++) {

				// 初期化
				 text = "会話を始めましょう！";

				// コメントがあるかどうかのSQL？
				sb = new StringBuilder();
				// SQL作成
				sb.append(" SELECT  ");
				sb.append(" MU.GROUP_NAME ");
				sb.append(" ,TM.MESSAGE ");
				sb.append(" ,TM.DELETE_FLAG ");
				sb.append(" FROM ");
				sb.append(" M_GROUP MU ");
				sb.append(" ,T_MESSAGE_INFO TM ");
				sb.append(" INNER JOIN ( ");
				sb.append(" SELECT ");
				sb.append(" E.GROUP_NAME NN ");
				sb.append(" , MAX(F.MESSAGE_NO) G ");
				sb.append(" FROM ");
				sb.append(" T_MESSAGE_INFO F ");
				sb.append(" , M_GROUP E ");
				sb.append(" WHERE  F.TO_SEND_GROUP_NO IS NOT NULL ");
				sb.append(" AND F.TO_SEND_GROUP_NO = E.GROUP_NO  ");
				sb.append(" AND F.DELETE_FLAG = 0 ");
				sb.append(" AND E.GROUP_NO = " + number.get(i));
				sb.append(" GROUP BY ");
				sb.append(" E.GROUP_NAME  ");
				sb.append(" ) INN  ");
				sb.append(" ON TM.MESSAGE_NO = INN.G  ");
				sb.append(" WHERE NN = MU.GROUP_NAME  ");
				// SQL実行
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sb.toString());

				// 項目の追加
				while (rs.next()) {
					if (rs.getString("MESSAGE") != null) {
					text = rs.getString("MESSAGE");
					}
				}
				setListText.add(text);
			}

			for (int i = 0; i < number.size(); i++) {
				// 最終的に送る用のリスト
				ArrayList<String> setList = new ArrayList<String>();
				setList.add(number.get(i));
				setList.add(name.get(i));
				setList.add(setListText.get(i));
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

	public void newProfile(String name, String profile, LoginBean bean) {
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
			sb.append(" UPDATE ");
			sb.append(" M_USER ");
			sb.append(" SET ");
			sb.append(" USER_NAME = '" + name + "'");
			sb.append(" ,MY_PAGE_TEXT = '" + profile + "'");
			sb.append(" WHERE ");
			sb.append(" USER_NO = " + bean.getUserNo());

			// SQL実行
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sb.toString());

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
