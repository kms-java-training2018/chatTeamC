package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupBean;
import bean.LoginBean;
import bean.SessionBean;
import model.CreatGroup;

/**
 * jsoからのリクエストを受け取る<br>
 * グループ作成機能のサーブレットクラス<br>
 *
 * @version 1.0
 * @author onishi-yasumasa
 *
 */
public class MakeGroupServlet extends HttpServlet {

	/**
	 * このdoGetメソッドは、直接リンクを受け取る<br>
	 * セッションに値があれば、/mainpageへ遷移<br>
	 * 無ければエラーページへ送信する<br>
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		//リンク先directionの定義
		String direction = "/WEB-INF/jsp/login.jsp";

		//セッション設定
		HttpSession session = req.getSession();

		//セッションに値があれば、loginBeanのセット
		if (session.getAttribute("session") != null) {
			req.getRequestDispatcher("/WEB-INF/jsp/makeGroup.jsp").forward(req, res);
			LoginBean bean = new LoginBean();
			bean.setErrorMessage(null);
			bean.setUserId(null);
			bean.setPassword(null);

			//mainページに戻るからのGETかどうか
			if (req.getParameter("main") != null) {
				direction = "/WEB-INF/jsp/mainPage.jsp";

			}
			req.setAttribute("loginBean", bean);
			req.getRequestDispatcher(direction).forward(req, res);

		} else {
			//ない場合、セッションにunllセットしてエラーページへ
			session.setAttribute("session", null);
			direction = "/WEB-INF/jsp/errorPage.jsp";
		}
		try {
			req.getRequestDispatcher(direction).forward(req, res);
		} catch (Exception e) {
		}

	}

	/**
	 *	このdoPostメソッドは、ユーザー一覧をjspに送信する<br>
	 *	送られてきたデータを元に、グループの登録を行う<br>
	 *	エラーの場合はエラーページへ遷移させる<br>
	 *	以下の場合、エラーとする<br>
	 *	<ul>
	 *	<li>DBにグループ登録が出来ない場合</li>
	 *	<li>DBにメンバー登録が出来ない場合</li>
	 *	</ul>
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		String direction = "/WEB-INF/jsp/login.jsp";

		//セッション設定
		HttpSession session = req.getSession();
		SessionBean sessionBean = new SessionBean();

		//グループビーンの設定(jspに送る用の空ビーン)
		GroupBean groupBean = new GroupBean();
		//今から処理させるモデル
		CreatGroup creatGroup = new CreatGroup();

		//		セッションに値があるかどうかでif
		if (session.getAttribute("session") != null) {

			//グループ作成画面から来たかどうかの判断if
			if (req.getParameter("groupName") != null) {

				//groupCreateにsessionのbean引き継がせる
				creatGroup.setGroupBean((GroupBean) session.getAttribute("groupBean"));
				//ログイン中のユーザー名表示のためのLoginBeanを抜き出してまたreqにのっける
				req.setAttribute("LoginBean", session.getAttribute("LoginBean"));

				//指定されたグループ名をもらう
				String name = new String(req.getParameter("groupName").getBytes("ISO-8859-1"));
				//入力チェックメッセージの設定
				String message;
				if (req.getParameter("groupName") == "") {
					message = "グループ名を入力してください";

					req.setAttribute("error", message);
					direction = "/WEB-INF/jsp/makeGroup.jsp";

				} else {

					//入力チェックの返答
					boolean byteCheck;
					byteCheck = creatGroup.stringLengthCheck(name);
					if (byteCheck == false) {
						message = "文字数オーバーです";

						req.setAttribute("error", message);
						direction = "/WEB-INF/jsp/makeGroup.jsp";

					} else {

						//testチェック用
						//					System.out.println("受け取ったグループ名" + name);

						//モデルにセット
						creatGroup.setGroupName(name);

						//グループへ登録
						boolean creat = creatGroup.resistGroup();

						System.out.println("creat" + creat);

						if (creat == true) {

							//選択されたユーザーをreqからもらう

							String SelectNo[];

							SelectNo = req.getParameterValues("userNo");

							//					test選択されたユーザーNoの表示
							//					for (String n1 : SelectNo) {
							//						System.out.print("送られてきたユーザーName：" + n1 + ",");
							//					}

							//抜き取った配列をGroupBeanへ送ってグループ作成
							boolean resist = creatGroup.resistGroupMember(SelectNo);

							System.out.println(resist);

							if (resist == true) {

								//						direction = "/WEB-INF/jsp/mainPage.jsp";
								MainPageServlet main = new MainPageServlet();
								main.doPost(req, res);
							} else {
								direction = "/WEB-INF/jsp/errorPage.jsp";
							}

						} else {
							direction = "/WEB-INF/jsp/errorPage.jsp";
						}

					}
				}

			} else {

				// ログインデータ取得
				sessionBean = (SessionBean) session.getAttribute("session");
				System.out.println(sessionBean.getUserName());
				String autherName = sessionBean.getUserName();

				System.out.println(autherName);
				//GroupModelの中のGroupBeanを、こちらのGroupBeanに入れる
				groupBean = creatGroup.getAllUserListAcquisition(autherName);

				//Beanからユーザー一覧取得できたかの成否を取得
				boolean judge = groupBean.isGetAllUserListJudge();

				//出来なかったらエラーページへ
				if (judge == false) {
					direction = "/WEB-INF/jsp/errorPage.jsp";
				} else {

					//セッションにセットしてjspに送る
					session.setAttribute("groupBean", groupBean);
					session.setAttribute("userName", autherName);

					direction = "/WEB-INF/jsp/makeGroup.jsp";
				}
			}

		} else {
			session.setAttribute("session", null);
			direction = "/WEB-INF/jsp/errorPage.jsp";
		}
		try {
			req.getRequestDispatcher(direction).forward(req, res);
		} catch (Exception e) {
		}

	}

}
