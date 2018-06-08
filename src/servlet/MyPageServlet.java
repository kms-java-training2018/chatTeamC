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

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// セッション情報取得
		HttpSession session = req.getSession();
		// 初期化
		MyPageModel model = new MyPageModel();
		MyPageBean bean = new MyPageBean();
		// 移動先のアドレス(マイページ)
		String direction = "/WEB-INF/jsp/myPage.jsp";

		// もしもセッションが無ければエラー
		if (session.getAttribute("session") == null) {
			//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			// エラー画面に推移
			direction = "/WEB-INF/jsp/errorPage.jsp";
		} else {
			// ログインデータ取得
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			// 認証処理
			try {
				bean = model.authentication(loginBean, bean);
			} catch (Exception e) {
				// 情報が無かったためエラー画面に移行
				session.setAttribute("session", null);
				//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
				direction = "/WEB-INF/jsp/errorPage.jsp";
			}
			// 名前とプロフィールを設定しておく
			req.setAttribute("name", bean.getName());
			req.setAttribute("profile", bean.getMyProfile());
		}

		req.getRequestDispatcher(direction).forward(req, res);

	}
}
