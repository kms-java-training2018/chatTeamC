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
		try {
			// もしもセッションが無ければエラー
			if (session.getAttribute("session") != null) {
				// ログインデータ取得
				LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");

				SecessionGroupModel secessionGroupModel = new SecessionGroupModel();
				String groupNo = (String) req.getParameter("toGroupNo");
				System.out.println(groupNo);
				secessionGroupModel.SecessionGroup(loginBean, groupNo);
				//削除できたかどうかを表示
				//メッセージ内容はmodel内でセット
				req.setAttribute("error", loginBean.getErrorMessage());
				MainPageServlet mainPageServlet = new MainPageServlet();
				mainPageServlet.doPost(req, res);

			} else {
				// 情報が無かったためエラー画面に移行
				session.setAttribute("session", null);
				req.setAttribute("error", "ログインしてください");
				req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			}
		} catch (Exception e) {
			session.setAttribute("session", null);
			req.setAttribute("error", "脱退できませんでした");
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
	}
}