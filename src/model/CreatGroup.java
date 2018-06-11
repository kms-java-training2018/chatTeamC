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

	//groupBeanの設定
	GroupBean gb = new GroupBean();

	//MemberBeanの設定
	MemberBean mb = new MemberBean();;

	//作成者の名前
	private String autherName;
	//作成者の番号
	private String autherNo;
	//グループの名前
	private String groupName;

	//MemberBeanのリスト
	ArrayList<MemberBean> memberList;

	/**
	 * グループbeanをセットする<br>
	 * @param groupBean 受け取ったgroupBean
	 */
	public void setGroupBean(GroupBean groupBean) {
		this.gb = groupBean;
	}

	/**
	 * MemberBeanをセットする<br>
	 * @param MemberBean　受け取ったMemberBean
	 */
	public void setMemberBean(MemberBean MemberBean) {
		this.mb = MemberBean;
	}

	/**
	 * グループ名をセットする<br>
	 * @param name 受け取ったグループ名
	 */
	public void setGroupName(String name) {
		this.groupName = name;
	}

	/**
	 * グループ作成者の会員番号をセットする<br>
	 * @param No 受け取った番号
	 */
	public void setAutherNo(String No) {
		this.autherNo = No;
	}

	/**
	 * 受け取った入力文字のバイト数をチェックし、true/falseで返す<br>
	 * 30バイト以内 true<br>
	 * 30バイト異常 false<br>
	 *
	 * @param input 受け取った入力文字
	 * @return judgeByte trueはバイト数OK、falseはバイト数NG
	 */
	public boolean stringLengthCheck(String input) {
		//成否判定
		boolean judgeByte;

		// 何バイト分の長さであるかを取得
		int length = input.getBytes().length;
		// 最大バイト数の設定
		int max = 30;

		judgeByte = (length <= max);

		return judgeByte;

	}

	/**
	 * 全ユーザー一覧を出すメソッド<br>
	 *
	 * @param name 受け取ったグループ作成者名
	 * @return gb 処理したデータを詰めたGroupBean
	 */
	public GroupBean getAllUserListAcquisition(String name) {
		// 初期化
		StringBuilder sb = new StringBuilder();

		//DBから取得できたかの成否変数
		boolean getDataJudge;

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

			// SQL実行してrsにセット
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {

				//				mb.setMembername(rs.getString("user_name"));
				//				mb.setMemberNo(rs.getString("user_name"));
				//				memberList.add(mb);

				/* 行からデータを取得 */
				gb.setUserNo(rs.getString("user_no"));
				gb.setUserName(rs.getString("user_name"));
				gb.setErrorMessage("");
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

		//受け取った作成者userNameをBeanに渡して処理
		this.autherName = name;
		gb.setAuther(autherName);

		//成否判定をBeanに設定
		gb.setGetAllUserListJudge(getDataJudge);

		return gb;

	}

	/**
	 * グループ登録を行うメソッド<br>
	 * @return creatJudge メンバー登録成否
	 */
	public boolean resistGroup() {
		// 初期化
		StringBuilder sb = new StringBuilder();

		//成功判定用変数
		boolean creatJudge;

		//gbからautherNoを
		String autherNo = gb.getAutherNo();

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
	public boolean resistGroupMember(String[] list) {
		// 初期化
		StringBuilder sb = new StringBuilder();

		//成功判定用変数
		boolean resistJudge;

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
				System.out.println(groupNo);
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
			String[] memberName = list;
			if (memberName == null) {

			} else {

				ArrayList<String> memberNo = new ArrayList<String>();
				for (String name : memberName) {
					int i = Integer.parseInt(name);
					memberNo.add(gb.getUserNo().get(i));
				}

				for (int i = 0; i < memberNo.size(); i++) {
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
					System.out.println(memberResistRs);

					//				if (memberResistRs == 1) {
					//					message = i + "回目Resist OK";
					////					System.out.println(message);
					//
					//				} else {
					//
					//				}
					//

					while (rs.next()) {

						/* 行からデータを取得 */
						gb.setUserNo(rs.getString("user_no"));
						gb.setUserName(rs.getString("user_name"));
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
