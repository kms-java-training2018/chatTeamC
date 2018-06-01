package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.ShowProfileBean;
import model.ShowProfileModel;

public class ShowProfileServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		// セッション情報取得
		HttpSession session = req.getSession();
		// 初期化
		ShowProfileModel model = new ShowProfileModel();
		ShowProfileBean bean = new ShowProfileBean();
		// もしもセッションが無ければエラー
		if (session.getAttribute("session") != null) { // ログインデータ取得
			LoginBean loginBean = (LoginBean) req.getAttribute("loginBean");
			// 認証処理
			try {
				bean = model.authentication(bean, loginBean, req.getParameter("toUserNo"));
			} catch (Exception e) {
				e.printStackTrace();
				// エラーページに移動
				session.setAttribute("session", null);
				req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			}

			System.out.println(bean.getName());
			System.out.println(bean.getProfile());

			// 表示画面に表示する用のBean
			req.setAttribute("Name", bean.getName());
			req.setAttribute("Profile", bean.getProfile());
		}

		req.getRequestDispatcher("/WEB-INF/jsp/showProfile.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/jsp/showProfile.jsp").forward(req, res);
	}
}