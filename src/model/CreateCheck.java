package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.ResistAccountBean;

public class CreateCheck {

	//アカウント作成時のid被りチェック
	public ResistAccountBean checkId(ResistAccountBean rb) {

		// 初期化
		StringBuilder sb = new StringBuilder();

		//判定
		boolean result = false;

		//データをもらう
		ResistAccountBean bean = rb;

		//userid
		String userId = rb.getUserId();
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
					//e.printStackTrace();
					result = false;
					rb.setSqlErrorMessage("データベースにアクセスできませんでした");
				}
				// 接続作成
				try {
					conn = DriverManager.getConnection(url, user, dbPassword);

					// SQLから全会員情報を取得
					sb.append("select ");
					sb.append(" user_id ");
					sb.append(" from ");
					sb.append("m_user ");
					sb.append(" where ");
					sb.append("user_id");
					sb.append(" = ");
					sb.append("'"+ userId + "'");
					// SQL実行してrsにセット
					Statement stmt = conn.createStatement();
					int rs = stmt.executeUpdate(sb.toString());

					result = (rs == 0);
					if(result == false) {
						rb.setIdErrorMessage("useridは既に使われています");

					}

				} catch (SQLException e) {
					//e.printStackTrace();
					//test
					result = false;
					rb.setSqlErrorMessage("データベースにアクセスできませんでした");

					// sqlの接続は絶対に切断
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
						//e.printStackTrace();
						result = false;
						rb.setSqlErrorMessage("データベースにアクセスできませんでした");
					}
				}
				rb.setCheckJudge(result);



		return bean;
	}

}
