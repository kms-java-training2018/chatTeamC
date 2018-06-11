package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import model.SecessionGroupModel;

public class SecessionGroupServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// セッション情報取得
		HttpSession session = req.getSession();
		// もしもセッションが無ければエラー
		if (session.getAttribute("session") != null) {
			// ログインデータ取得
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

			SecessionGroupModel secessionGroupMessageModel = new SecessionGroupModel();
			String groupNo = (String) req.getParameter("toGroupNo");
			secessionGroupMessageModel.SecessionGroup(loginBean, groupNo);
			//削除できたかどうかを表示
			//メッセージ内容はmodel内でセット
			req.setAttribute("error", loginBean.getErrorMessage());

		} else {
			// 情報が無かったためエラー画面に移行
			session.setAttribute("session", null);
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		}
		MainPageServlet mainPageServlet = new MainPageServlet();
		mainPageServlet.doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
	}
}