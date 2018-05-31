package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.MyPageBean;
import model.MyPageModel;

public class MyPageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		req.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// セッション情報取得
		HttpSession session = req.getSession();
		// 初期化
		MyPageModel model = new MyPageModel();
		MyPageBean bean = new MyPageBean();

		// もしもセッションが無ければエラー
		if (session.getAttribute("session") != null) {// ログインデータ取得
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			// 認証処理
			try {
				bean = model.authentication(loginBean, bean);
			} catch (Exception e) {
				// 情報が無かったためエラー画面に移行
				// とりあえず今はログイン画面に戻るように設定
				session.setAttribute("session", null);
			}

			req.setAttribute("name", bean.getName());
			req.setAttribute("profile", bean.getMyProfile());
		} else {
			// 情報が無かったためエラー画面に移行
			// TODO とりあえず今はログイン画面に戻るように設定
			session.setAttribute("session", null);
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}

		req.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp").forward(req, res);

	}
}
