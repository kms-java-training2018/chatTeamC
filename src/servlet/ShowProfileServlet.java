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
		ShowProfileBean bean = new ShowProfileBean();//エラーメッセージ用のString
		String message;

		//nullの場合エラー
		if (session.getAttribute("session") == null) {
			message = "不正なアクセスです。ログインしてください";
			req.setAttribute("error", message);
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		} else {
			//loginBean取得
			LoginBean loginBean = (LoginBean) req.getAttribute("loginBean");

			// 認証処理
			try {
				bean = model.showProfileSearch(bean, loginBean, req.getParameter("toUserNo"));
			} catch (Exception e) {
				e.printStackTrace();
				// エラーページに移動
				session.setAttribute("session", null);
				message = "相手の情報を取得できませんでした。";
				req.setAttribute("error", message);
				req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			}

			// 表示画面に表示する用のBean

			req.setAttribute("Name", bean.getName());
			req.setAttribute("Profile", bean.getProfile());
			req.setAttribute("Unacquired", bean);

			if (bean.getUnacquired() == 0) {
				req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			} else {
				req.getRequestDispatcher("/WEB-INF/jsp/showProfile.jsp").forward(req, res);
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/jsp/showProfile.jsp").forward(req, res);
	}

}
