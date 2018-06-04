package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.MainPageBean;
import model.MainPageModel;

public class MainPageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req,res);
		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// セッション情報取得
		HttpSession session = req.getSession();
		// 初期化
		MainPageModel model = new MainPageModel();
		MainPageBean bean = new MainPageBean();

		// もしもセッションが無ければエラー
		if (session.getAttribute("session") != null) {// ログインデータ取得
			LoginBean loginBean = (LoginBean)session.getAttribute("loginBean");

			if ((String) req.getParameter("newProfile") != null) {

				String myName = new String (req.getParameter("myName").getBytes("ISO-8859-1"));
				String myProfile = new String (req.getParameter("myProfile").getBytes("ISO-8859-1"));

				model.newProfile(myName,myProfile,loginBean);
			}

			// 認証処理
			try {
				bean = model.authentication(bean,loginBean);
			} catch (Exception e) {
				session.setAttribute("session", null);
				// 情報が無かったためエラー画面に移行
				req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			}
		} else {
			// 情報が無かったためエラー画面に移行
			// とりあえず今はログイン画面に戻るように設定
			session.setAttribute("session", null);
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		}
		// 表示画面に表示する用のBean
	    req.setAttribute("MainPageBean", bean);
		req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
	}
}