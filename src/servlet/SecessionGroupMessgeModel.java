package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import model.SecessionGroupMessageModel;

public class SecessionGroupMessgeModel extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// セッション情報取得
		HttpSession session = req.getSession();
		// もしもセッションが無ければエラー
		if (session.getAttribute("session") != null) {// ログインデータ取得
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

			SecessionGroupMessageModel secessionGroupMessageModel = new SecessionGroupMessageModel();
			String groupNo = (String)req.getParameter("toGroupNo");
			secessionGroupMessageModel.authentication(loginBean,groupNo);

		} else {
			// 情報が無かったためエラー画面に移行
			// TODO とりあえず今はログイン画面に戻るように設定
			session.setAttribute("session", null);
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}
		MainPageServlet mainPageServlet = new MainPageServlet();
		mainPageServlet.doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
	}
}
