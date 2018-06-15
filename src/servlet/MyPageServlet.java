package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.MyPageBean;
import model.CheckCharacter;
import model.MyPageModel;

public class MyPageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		//リンク先directionの定義
		String direction = "/WEB-INF/jsp/errorPage.jsp";

		//セッション設定
		HttpSession session = req.getSession();

		//エラーメッセージ用のString
		String message;
		//セッション情報を初期化の後エラーページへ
		session.setAttribute("session", null);
		message = "不正なアクセスです。ログインしてくださーい";
		req.setAttribute("error", message);
		req.getRequestDispatcher(direction).forward(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// 判断用Model
		CheckCharacter checkCharacter = new CheckCharacter();
		// セッション情報取得
		HttpSession session = req.getSession();
		// 初期化
		MyPageModel model = new MyPageModel();
		MyPageBean bean = new MyPageBean();
		// 移動先のアドレス(マイページ)
		String direction = "/WEB-INF/jsp/myPage.jsp";

		//エラーメッセージ用のString
		String message;

		// もしもセッションが無ければエラー
		if (session.getAttribute("session") == null) {
			session.setAttribute("session", null);
			message = "不正なアクセスです。ログインしてくださーい";
			req.setAttribute("error", message);
			//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			// エラー画面に推移
			direction = "/WEB-INF/jsp/errorPage.jsp";
		} else {
			// ログインデータ取得
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			// 認証処理
			try {
				bean = model.myPageBeanSeting(loginBean, bean);
			} catch (Exception e) {
				// 情報が無かったためエラー画面に移行
				session.setAttribute("session", null);
				message = "不正なアクセスです。ログインしてくださーい";
				req.setAttribute("error", message);
				//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
				direction = "/WEB-INF/jsp/errorPage.jsp";
			}
			if (checkCharacter.spaceCheck(bean.getName())) {
				// 名前とプロフィールを設定しておく
				req.setAttribute("name", bean.getName());
				req.setAttribute("profile", bean.getMyProfile());
			} else {
				// 名前が無かったためエラーページに遷移
				session.setAttribute("session", null);
				message = "名前が存在しませんでした";
				req.setAttribute("error", message);
				//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
				direction = "/WEB-INF/jsp/errorPage.jsp";
			}

		}

		// 移動先のアドレスに移動
		req.getRequestDispatcher(direction).forward(req, res);

	}
}
