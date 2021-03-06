package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.GroupMessageBean;
import bean.GroupProfileBean;
import bean.LoginBean;
import bean.TalkContentBean;

/**
 * グループメッセージ画面ビジネスロジック
 */
public class GetTalkMessageModel {

	public GroupMessageBean authentication(GroupMessageBean bean, LoginBean loginBean, String GroupNo) {

		//Arraylist


		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 入れなかった場合
			e.printStackTrace();
		}

		// 接続作成
		try {
			conn = DriverManager.getConnection(url, user, dbPassword);
			// 初期化
			StringBuilder sb = new StringBuilder();
			//SQL作成
			sb.append(" SELECT GROUP_NAME , REGIST_USER_NO");
			sb.append(" FROM  M_GROUP ");
			sb.append(" WHERE GROUP_NO = " + GroupNo);

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				// Beanに追加
				bean.setGroupName(rs.getString("GROUP_NAME"));
				bean.setRegistUserNo(rs.getString("REGIST_USER_NO"));
			}
			// 初期化
			sb = new StringBuilder();
			sb.append(" SELECT M_USER.USER_NAME,M_USER.USER_NO");
			sb.append(" FROM T_GROUP_INFO inner join M_USER on T_GROUP_INFO.USER_NO = M_USER.USER_NO ");
			sb.append(" WHERE T_GROUP_INFO.GROUP_NO = " + GroupNo);
			sb.append(" AND T_GROUP_INFO.OUT_FLAG = 0 ");
			sb.append(" AND T_GROUP_INFO.USER_NO != " + loginBean.getUserNo());

			// SQL実行
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sb.toString());
			GroupProfileBean setMenber;
			while (rs.next()) {
				setMenber = new GroupProfileBean();
				setMenber.setMemberList(rs.getString("USER_NAME"));
				setMenber.setMemberListNumber(rs.getString("USER_NO"));
				bean.setGroupProfileList(setMenber);
			}

			// 初期化
			sb = new StringBuilder();
			sb.append(" select mu.USER_NAME,mu.USER_NO,migi.message_no,migi.message,migi.out_flag  ");
			sb.append(" from (select mi.message_no,mi.message,mi.send_user_no,gi.out_flag  ");
			sb.append(" from t_message_info mi inner join t_group_info gi  ");
			sb.append(" on mi.TO_SEND_GROUP_NO = gi.group_no  ");
			sb.append(" and mi.SEND_USER_NO = gi.USER_NO  ");
			sb.append(" where mi.TO_SEND_GROUP_NO = " + GroupNo);
			sb.append(" AND mi.delete_flag = 0 ) migi ");
			sb.append(" inner join m_user mu   ");
			sb.append(" on migi.send_user_no = mu.USER_NO  ");
			sb.append(" order by migi.message_no ");

			// SQL実行
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sb.toString());

			while (rs.next()) {
				TalkContentBean talkContentBean = new TalkContentBean();
				// Beanに追加
				if (rs.getString("OUT_FLAG").equals("1")) {
					talkContentBean.setUserName("送信者不明");
				} else {
					talkContentBean.setUserName(rs.getString("USER_NAME"));
				}
				talkContentBean.setMessage(rs.getString("message"));
				talkContentBean.setUserNo(rs.getString("USER_NO"));
				talkContentBean.setMessageNo(rs.getString("message_no"));
				bean.setTalkContentBeanList(talkContentBean);
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

	public boolean CheckGroupMenber(LoginBean loginBean, String GroupNo) {
		// 判定用変数
		boolean checkGroupMenber = false;

		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.51.67";
		String user = "DEV_TEAM_C";
		String dbPassword = "C_DEV_TEAM";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// 入れなかった場合
			e.printStackTrace();
		}
		//SELECT * FROM T_GROUP_INFO WHERE USER_NO = 41 AND GROUP_NO = 224 AND OUT_FLAG = 0
		try {

			conn = DriverManager.getConnection(url, user, dbPassword);
			// 初期化
			StringBuilder sb = new StringBuilder();
			//SQL作成
			sb.append("SELECT * FROM T_GROUP_INFO WHERE USER_NO = " + loginBean.getUserNo() + " AND GROUP_NO = "
					+ GroupNo + " AND OUT_FLAG = 0");

			// SQL実行
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sb.toString());
			while (rs.next()) {
				checkGroupMenber = true;
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

		return checkGroupMenber;
	}
}