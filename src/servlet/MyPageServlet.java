package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
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

		// もしもセッションが無ければエラー
		if (session.getAttribute("session") != null) {// ログインデータ取得
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			System.out.println(loginBean.getUserName());
			// 認証処理
			try {
				model.authentication(loginBean);
			} catch (Exception e) {
				// 情報が無かったためエラー画面に移行
				// とりあえず今はログイン画面に戻るように設定
				session.setAttribute("session", null);
			}
		} else {
			// 情報が無かったためエラー画面に移行
			// TODO とりあえず今はログイン画面に戻るように設定
			session.setAttribute("session", null);
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}

		req.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp").forward(req, res);

	}
}
