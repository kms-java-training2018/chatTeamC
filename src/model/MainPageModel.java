package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.LatestGroupMessageBean;
import bean.LatestMenberMessageBean;
import bean.LoginBean;
import bean.MainPageBean;

public class MainPageModel {

	/**
	 * 引数 メインページビーン ログインビーン
	 * 送り値 メインページビーン
	 * メインページビーンの内容を設定しサーブレットに返す。
	 */
	public MainPageBean mainPageBeanSeting(MainPageBean bean, LoginBean loginBean) {
		// 初期化
		StringBuilder sb = new StringBuilder();

		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";
		// JDBCドライバーのロード
		try {
			// データベース検索
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 入れなかった場合
			e.printStackTrace();
		}
		// 接続作成
		try {
			// データ取得用
			conn = DriverManager.getConnection(url, user, dbPassword);

			// SQL作成
			sb.append("SELECT");
			sb.append(" user_no ");
			sb.append(" ,user_name ");
			sb.append("FROM ");
			sb.append(" m_user ");
			sb.append("WHERE");
			sb.append(" user_name != '" + loginBean.getUserName() + "' ");
			sb.append(" ORDER BY USER_NO DESC");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				// Listの初期化
				// bean に送るようのリスト
				LatestMenberMessageBean setList = new LatestMenberMessageBean();
				// Listに追加
				setList.setUserNo(rs.getString("user_no"));
				setList.setUserName(rs.getString("user_name"));
				// Beanに追加
				bean.setLatestMenberMessageBeanList(setList);
			}

			// 個人チャットの表示用リスト作成
			for (LatestMenberMessageBean menber : bean.getLatestMenberMessageBeanList()) {

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
				sb.append(" AND E.USER_NO = " + menber.getUserNo() + " ) ");
				sb.append(" OR ");
				sb.append(" ( F.TO_SEND_USER =" + loginBean.getUserNo());
				sb.append(" AND F.SEND_USER_NO = E.USER_NO ");
				sb.append(" AND F.DELETE_FLAG = 0 ");
				sb.append(" AND E.USER_NO = " + menber.getUserNo() + " ) ");
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
				// Beanに追加
				menber.setLatestMessage(text);
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
			sb.append(" WHERE ");
			sb.append(" TG.GROUP_NO = MG.GROUP_NO ");
			sb.append(" AND TG.USER_NO = " + loginBean.getUserNo());
			sb.append(" AND TG.OUT_FLAG = 0");
			sb.append(" ORDER BY MG.GROUP_NO DESC");

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
				// 最新メッセージをセット
				setListText.add(text);
			}

			for (int i = 0; i < number.size(); i++) {
				// 最終的に送る用のリスト
				LatestGroupMessageBean setList = new LatestGroupMessageBean();
				setList.setGroupNo(number.get(i));
				setList.setGroupName(name.get(i));
				setList.setLatestMessage(setListText.get(i));
				bean.setLatestGroupMessageBeanList(setList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// sqlの接続は絶対に切断
			// 想定外のことがおきたときはbeanをnullに
			bean = null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				bean = null;
				e.printStackTrace();
			}
		}
		// 設定したビーンを返す
		return bean;
	}

	/**
	 * プロフィールの更新を行なう、
	 * 返り値で行なえたかの判断を返す。
	 */
	public boolean newProfile(String name, String profile, LoginBean bean) {
		// プロフィールを変更を出来たかどうかの確認用
		boolean changeProfile = true;

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
			// エラー文
			// 入れなかった場合
			e.printStackTrace();
			// プロフィールを更新させない
			changeProfile = false;
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
			int num = stmt.executeUpdate(sb.toString());

			if (num == 0) {
				// データの更新が失敗しました
				System.out.println("更新失敗なのでエラーに遷移します");
				// プロフィールを更新させない
				changeProfile = false;
			}

		} catch (SQLException e) {
			// エラー文
			e.printStackTrace();
			// sqlの接続は絶対に切断
			System.out.println("想定外のデータが送られました。");
			// プロフィールを更新させない
			changeProfile = false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// エラー文
				e.printStackTrace();
				System.out.println("想定外のエラーが発生しました");
				// プロフィールを更新させない
				changeProfile = false;
			}
		}
		// プロフィールが変更できるかどうかを返す
		return changeProfile;
	}
}
