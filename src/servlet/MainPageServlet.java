package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.MainPageBean;
import bean.SessionBean;
import model.MainPageModel;

public class MainPageServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		// セッション情報取得
		HttpSession session = req.getSession();
		// 移動先のアドレス(メインページ)
		String direction = "/WEB-INF/jsp/mainPage.jsp";
		// 初期化
		MainPageModel model = new MainPageModel();
		MainPageBean bean = new MainPageBean();
		//String myName = "";
		//String myProfile = "";

		// もしもセッションが無ければエラー
		if (session.getAttribute("session") != null) {// ログインデータ取得

			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			if ((String) req.getParameter("newProfile") != null) {

				// 名前の設定
				String myName = new String(req.getParameter("myName").getBytes("ISO-8859-1"));
				String myProfile = new String(req.getParameter("myProfile").getBytes("ISO-8859-1"));

				// 名前が何も入っていなかった場合無効な名前としてマイページに戻す
				if (myName.equals("")) {
					req.setAttribute("erorr", "無効な名前です");
					//マイページに戻る
					req.setAttribute("name", myName);
					req.setAttribute("profile", myProfile);
					//req.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp").forward(req, res);
					direction = "/WEB-INF/jsp/myPage.jsp";
				} else {
					// 名前をログインビーンに設定する
					loginBean.setUserName(myName);
					// セッションのほうにも名前を設定しておく
					SessionBean sessionBean = new SessionBean();
					// 名前をセッションビーンに設定する
					sessionBean.setUserName(myName);
					// セッションにセッションビーンを設定する
					session.setAttribute("session", sessionBean);
					// データベースにプロフィールを設定する。
					// 設定してこれなかった場合エラーページに飛ぶ
					if (!(model.newProfile(myName, myProfile, loginBean))) {
						direction = "/WEB-INF/jsp/errorPage.jsp";
					}

					// 認証処理
					try {
						bean = model.mainPageBeanSeting(bean, loginBean);
						req.setAttribute("MainPageBean", bean);
						//req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
					} catch (Exception e) {
						session.setAttribute("session", null);
						// 情報が無かったためエラー画面に移行
						direction = "/WEB-INF/jsp/errorPage.jsp";
						//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
					}
				}
			} else {

				// 認証処理

				try {
					bean = model.mainPageBeanSeting(bean, loginBean);
					req.setAttribute("MainPageBean", bean);
					//req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
				} catch (Exception e) {
					session.setAttribute("session", null);
					// 情報が無かったためエラー画面に移行
					direction = "/WEB-INF/jsp/errorPage.jsp";
					//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
				}
			}

		} else {
			;
			// 情報が無かったためエラー画面に移行
			// とりあえず今はログイン画面に戻るように設定
			session.setAttribute("session", null);
			direction = "/WEB-INF/jsp/errorPage.jsp";
			//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		}
		// 移動先のアドレスに移動
		req.getRequestDispatcher(direction).forward(req, res);
	}
}