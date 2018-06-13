package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.GroupBean;
import bean.MemberBean;

/**
 * グループ作成機能model<br>
 *
 * @author onishi-yasumasa
 * @version 1.0
 */
public class CreatGroup {


	/**
	 * 全ユーザー一覧を出すメソッド<br>
	 *
	 * @param name 受け取ったグループ作成者名
	 * @return gb 処理したデータを詰めたGroupBean
	 */
	public GroupBean getAllUserListAcquisition(GroupBean gb) {
		// 初期化
		StringBuilder sb = new StringBuilder();

		//DBから取得できたかの成否変数
		boolean getDataJudge;

		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";

		//memberBeanのセット
		MemberBean mb = new MemberBean();

		//memberBeanListのセット
		ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();

		// JDBCドライバーのロード
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 入れなかった場合
			e.printStackTrace();
			getDataJudge = false;
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
			sb.append(" WHERE ");
			sb.append(" user_name != '" + gb.getLoginBean().getUserName() + "' ");
			sb.append(" ORDER BY USER_NO");

			// SQL実行してrsにセット
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {

				mb = new MemberBean();
				mb.setMemberName(rs.getString("user_name"));
				mb.setMemberNo(rs.getString("user_no"));
				memberList.add(mb);


			}

		} catch (SQLException e) {
			e.printStackTrace();
			getDataJudge = false;
			// sqlの接続は絶対に切断
		} finally {
			try {
				conn.close();
				getDataJudge = true;
				gb.setGetAllUserListJudge(getDataJudge);
			} catch (SQLException e) {
				e.printStackTrace();
				getDataJudge = false;
			}
		}

		//MemberBeanListをセット
		gb.setMemberList(memberList);


		//受け取った作成者userNameをBeanに渡して処理
		mb = new MemberBean();
		mb.setMemberName(gb.getLoginBean().getUserName());
		mb.setMemberNo(gb.getLoginBean().getUserNo());
		gb.setAuther(mb);

		//成否判定をBeanに設定
		gb.setGetAllUserListJudge(getDataJudge);



		return gb;

	}

	/**
	 * グループ登録を行うメソッド<br>
	 * @return creatJudge メンバー登録成否
	 */
	public boolean resistGroup(GroupBean gb) {
		// 初期化
		StringBuilder sb = new StringBuilder();

		//成功判定用変数
		boolean creatJudge;

		//gbからautherNoを
		String autherNo = gb.getLoginBean().getUserNo();

		//gbからGroupNameを
		String groupName = gb.getGroupName();

		//		//test
		//		System.out.println("登録者のNo" + autherNo);
		//		System.out.println("グループネーム" + groupName);

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
			creatJudge = false;
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

			creatJudge = (rs == 1);

		} catch (SQLException e) {
			e.printStackTrace();
			//test
			creatJudge = false;

			// sqlの接続は絶対に切断
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				creatJudge = false;
			}
		}
		return creatJudge;
	}

	/**
	 * グループにメンバー登録を行うメソッド<br>
	 *
	 * @param list 選択したユーザー
	 * @return resistJudge メンバー登録成否
	 */
	public boolean resistGroupMember(GroupBean gb) {
		// 初期化
		StringBuilder sb = new StringBuilder();

		//成功判定用変数
		boolean resistJudge;

		//作成者番号を入手
		String autherNo = gb.getAutherBean().getMemberNo();
		//グループ名を入手
		String groupName = gb.getGroupName();

		//memberBeanのセット
		MemberBean mb = new MemberBean();

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
			resistJudge = false;
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
			}

			//作成者のメンバー登録を行う文
			StringBuilder sb1 = new StringBuilder();
			sb1.append("insert ");
			sb1.append(" into ");
			sb1.append(" T_GROUP_INFO( ");
			sb1.append("GROUP_NO");
			sb1.append(", USER_NO ");
			sb1.append(", OUT_FLAG");
			sb1.append(", REGIST_DATE");
			sb1.append(")");
			sb1.append(" values ");
			sb1.append("(" + groupNo);
			sb1.append("," + autherNo);
			sb1.append(",0");
			sb1.append(", sysdate)");

			Statement stmt1 = conn.createStatement();
			int AutherResistRs = stmt1.executeUpdate(sb1.toString());

			resistJudge = (AutherResistRs == 1);

			// 会員登録を行うfor文

			//受け取ったStringリストからを登録者Listに設定
			String[] memberNo = gb.getSelectMemberList();
			if (memberNo == null) {
				resistJudge = false;
			} else {

				ArrayList<String> resistNo = new ArrayList<String>();
				for (String name : memberNo) {

					resistNo.add(name);
				}

				for (int i = 0; i < resistNo.size(); i++) {
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
					sb2.append("," + resistNo.get(i));
					sb2.append(",0");
					sb2.append(", sysdate)");

					Statement stmt2 = conn.createStatement();
					int memberResistRs = stmt2.executeUpdate(sb2.toString());
					if(memberResistRs == 1) {
						resistJudge = true;
					}

					while (rs.next()) {

						/* 行からデータを取得 */
						mb.setMemberNo(rs.getString("user_no"));
						mb.setMemberName(rs.getString("user_name"));
						gb.setErrorMessage("");
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			resistJudge = false;

			// sqlの接続は絶対に切断
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resistJudge;
	}

}
