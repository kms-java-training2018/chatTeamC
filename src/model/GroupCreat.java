package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.GroupBean;

public class GroupCreat {

	//groupBeanの設定
	GroupBean gb = new GroupBean();

	//作成者の名前
	private String autherName;
	//作成者の番号
	private String autherNo;
	//グループの名前
	private String groupName;

	//グループビーンの引継ぎ
	public void setGroupBean(GroupBean groupBean) {
		this.gb = groupBean;
	}

	//指定されたグループネームの設定
	public void setGroupName(String name) {
		this.groupName = name;
	}

	//指定されたAutherNoの設定
	public void setAutherNo(String No) {
		this.autherNo = No;
	}

	public void checkinName(String name) {

	}

	//ユーザー一覧を出すメソッド
	public GroupBean authentication(String name) {
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

			// SQLから全会員情報を取得
			sb.append("SELECT ");
			sb.append(" user_no ");
			sb.append(" ,user_name ");
			sb.append("FROM ");
			sb.append(" m_user ");

			// SQL実行してrsにセット
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {

				/* 行からデータを取得 */
				gb.setUserNo(rs.getString("user_no"));
				gb.setUserName(rs.getString("user_name"));
				gb.setErrorMessage("");
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

		//受け取った作成者userNameをBeanに渡して処理
		this.autherName = name;

		//チェック用
		System.out.println("グループ作成者" + autherName);
		gb.setAuther(autherName);

		//test表示
		//		ArrayList<String> test = gb.getUserName();
		//		for(String n1 : test) {
		//			System.out.print("Autherを抜いたはず："+n1+",");
		//	}

		return gb;

	}

	//既存のグループnameか確認するメソッド

	//グループ登録を行うメソッド
	public String CreatGroup() {
		// 初期化
		StringBuilder sb = new StringBuilder();

		//戻り値として渡す成否メッセージを定義
		String CreatCheck;

		//gbからautherNoを
		String autherNo = gb.getAutherNo();

		System.out.println("登録者のNo" + autherNo);
		System.out.println("グループネーム" + groupName);

		//DB接続
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

			// SQLから全会員情報を取得
			sb.append("insert ");
			sb.append(" into ");
			sb.append(" M_GROUP( ");
			sb.append("GROUP_NO ");
			sb.append(", GROUP_NAME ");
			sb.append(", REGIST_USER_NO");
			sb.append(", REGIST_DATE");
			sb.append(")");
			sb.append("values");
			sb.append("(GROUP_SEQ.NEXTVAL");
			sb.append(",'" + groupName + "'");
			sb.append("," + autherNo);
			sb.append(", sysdate)");

			// SQL実行してrsにセット
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sb.toString());

			if (rs == 1) {
				CreatCheck = "Creat ok";
			} else {
				CreatCheck = "Creat no";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			CreatCheck = "Creat no";

			// sqlの接続は絶対に切断
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return CreatCheck;
	}

	//グループにメンバー登録を行うメソッド
	public String ResistGroup(String[] list) {
		// 初期化
		StringBuilder sb = new StringBuilder();

		//戻り値として渡す成否メッセージを定義
		String message = "0";

		//受け取ったStringリストからを登録者Listに設定
		String[] memberName = list;
		ArrayList<String> memberNo = new ArrayList<String>();
		for(String name : memberName) {
			int i = Integer.parseInt(name);
			memberNo.add(gb.getUserNo().get(i));
		}


		//作成者番号を入手
		this.autherNo = gb.getAutherNo();

		//DB接続
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

			// 作成者idとグループ名から、グループ番号を入手
			sb.append("select ");
			sb.append(" MAX(group_no) ");
			sb.append(" from ");
			sb.append("m_group ");
			sb.append(" where ");
			sb.append(" regist_user_no ");
			sb.append(" in ");
			sb.append(autherNo);
			sb.append(" and");
			sb.append(" group_name ");
			sb.append(" = ");
			sb.append("'" + groupName + "'");

			// SQL実行してrsにセット
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			//結果をグループナンバーにセット

			String groupNo = "0";
			while (rs.next()) {
				groupNo = rs.getString("MAX(group_no)");
				System.out.println(groupNo);
			}

			// 会員登録を行うfor文

			for (int i = 0;i < memberNo.size(); i++) {
				StringBuilder sb2 = new StringBuilder();
				sb2.append("insert ");
				sb2.append(" into ");
				sb2.append(" T_GROUP_INFO( ");
				sb2.append("GROUP_NO");
				sb2.append(", USER_NO ");
				sb2.append(", OUT_FLAG");
				sb2.append(", REGIST_DATE");
				sb2.append(")");
				sb2.append(" values ");
				sb2.append("(" + groupNo);
				sb2.append("," + memberNo.get(i));
				sb2.append(",0");
				sb2.append(", sysdate)");

				Statement stmt2 = conn.createStatement();
				int memberResistRs = stmt2.executeUpdate(sb2.toString());

				if (memberResistRs == 1) {
					message = i + "回目Resist OK";

				} else {

				}
			}

			//			while (rs.next()) {
			//
			//				/* 行からデータを取得 */
			//				gb.setUserNo(rs.getString("user_no"));
			//				gb.setUserName(rs.getString("user_name"));
			//				gb.setErrorMessage("");


		} catch (SQLException e) {
			e.printStackTrace();
			message = "Resist no";

			// sqlの接続は絶対に切断
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return message;
	}

}
