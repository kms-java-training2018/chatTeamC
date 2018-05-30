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
		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// セッション情報取得
		HttpSession session = req.getSession();

		// ログインデータ取得
		LoginBean loginBean = (LoginBean)req.getAttribute("loginBean");
		
		// もしもセッションが無ければエラー
		if (session.getAttribute("session") != null) {
			// 初期化
			MainPageModel model = new MainPageModel();
			MainPageBean bean = new MainPageBean();

			// 認証処理
			try {
				bean = model.authentication(bean,loginBean);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 残りのグループ
			System.out.println("表示するメンバー" + bean.getMember());
			System.out.println("メンバーと最新メッセージ" + bean.getMemberTalk());

			// 取得
			// 失敗したらエラー
			// メンバー表示
			// 最新コメント表示
			// チーム表示
			// 最新コメント表示
			// 失敗したらエラー

		} else {
			// 情報が無かったためエラー画面に移行
			// とりあえず今はログイン画面に戻るように設定
			session.setAttribute("session", null);
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}
		req.setAttribute("loginBean", loginBean);
		req.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp").forward(req, res);
	}
}