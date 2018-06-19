package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupBean;
import bean.LoginBean;
import model.CheckCharacter;
import model.CreatGroup;

/**
 * jspからのリクエストを受け取る<br>
 * グループ作成機能のサーブレットクラス<br>
 *
 * @version 1.0
 * @author onishi-yasumasa
 *
 */
public class MakeGroupServlet extends HttpServlet {

	/**
	 * このdoGetメソッドは、直接URL入力でのアクセスを受け取る<br>
	 * セッションに値があれば、/mainpageへ遷移<br>
	 * 無ければエラーページへ送信する<br>
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		//リンク先directionの定義
		String direction = "/WEB-INF/jsp/login.jsp";

		//セッション設定
		HttpSession session = req.getSession();

		//エラーメッセージ用のString
		String message;

		//セッションに値があるかのif
		if (session.getAttribute("session") == null) {
			//ない場合、セッションにunllセットしてエラーページへ
			session.setAttribute("session", null);
			message = "不正なアクセスです。ログインしてくださーい";
			req.setAttribute("error", message);
			direction = "/WEB-INF/jsp/errorPage.jsp";

		} else {
			//あればmakeGroupへ
			direction = "/WEB-INF/jsp/makeGroup.jsp";

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
		//		SessionBean sessionBean = new SessionBean();

		//errorメッセージの設定
		String message;

		//グループビーンの設定(jspに送る用の空ビーン)
		GroupBean groupBean = new GroupBean();
		//今から処理させるモデル
		CreatGroup creatGroup = new CreatGroup();
		//文字入力チェック用モデル
		CheckCharacter checkChara = new CheckCharacter();

		//		セッションに値があるかどうかでif
		if (session.getAttribute("session") != null) {

			//グループ作成画面から来たかどうかの判断if
			if (req.getParameter("action") != null) {

				//				//groupCreateにsessionのbean引き継がせる
				//				creatGroup.setGroupBean((GroupBean) session.getAttribute("groupBean"));
				//ログイン中のユーザー名表示のためのLoginBeanを抜き出してまたreqにのっける
				req.setAttribute("LoginBean", session.getAttribute("LoginBean"));

				//指定されたグループ名をもらう
				String name = new String(req.getParameter("groupName").getBytes("ISO-8859-1"));

				if (checkChara.spaceCheck(name) == false) {
					message = "グループ名を入力してください";

					req.setAttribute("error", message);
					direction = "/WEB-INF/jsp/makeGroup.jsp";

				} else {

					//入力チェックの返答
					boolean byteCheck;
					byteCheck = checkChara.stringLengthCheck(name, 90);
					if (byteCheck == false) {
						message = "文字数オーバーです";

						req.setAttribute("error", message);
						direction = "/WEB-INF/jsp/makeGroup.jsp";

					} else {

						//testチェック用
						//					System.out.println("受け取ったグループ名" + name);

						groupBean = (GroupBean) session.getAttribute("groupBean");

						//モデルにセット
						groupBean.setGroupName(name);

						//グループへ登録
						//結果変数の設定
						boolean creat = false;
						try {
							creat = creatGroup.resistGroup(groupBean);
						} catch (Exception e) {
							message = "グループを作成できませんでした。";
							req.setAttribute("error", message);
							direction = "/WEB-INF/jsp/errorPage.jsp";
						}
						System.out.println("creat" + creat);

						if (creat == true) {

							//選択されたユーザーをreqからもらう

							String SelectNo[];
							SelectNo = req.getParameterValues("userNo");
							groupBean.setSelectMemberList(SelectNo);

							//抜き取った配列をGroupBeanへ送ってグループ作成
							//結果変数の設定
							boolean resist = false;

							try {
								resist = creatGroup.resistGroupMember(groupBean);
							} catch (Exception e) {

								message = "データベースに接続できませんでした。";
								direction = "/WEB-INF/jsp/errorPage.jsp";
							}

							System.out.println(resist);

							if (resist == true) {

								//														direction = "/WEB-INF/jsp/mainPage.jsp";
								MainPageServlet main = new MainPageServlet();
								main.doPost(req, res);
							} else {
								message = "グループのメンバーを登録できませんでした。";
								req.setAttribute("error", message);
								direction = "/WEB-INF/jsp/errorPage.jsp";
							}

						} else {
							direction = "/WEB-INF/jsp/errorPage.jsp";
						}

					}

				}

			} else {

				// ログインデータ取得
				LoginBean lb = (LoginBean) session.getAttribute("loginBean");

				String autherName = lb.getUserName();
				groupBean.setLoginBean(lb);

				//GroupModelの中のGroupBeanを、こちらのGroupBeanに入れる
				groupBean = creatGroup.getAllUserListAcquisition(groupBean);

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

				if (req.getParameter("backMain") == null) {

				} else {
					MainPageServlet main = new MainPageServlet();
					main.doPost(req, res);
				}

			}
		} else {
			message = "ログインしてください";
			req.setAttribute("error", message);
			session.setAttribute("session", null);
			direction = "/WEB-INF/jsp/errorPage.jsp";
		}

		try {
			req.getRequestDispatcher(direction).forward(req, res);
		} catch (Exception e) {

		}

	}

}
