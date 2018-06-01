package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.SessionBean;
import model.LoginModel;

/**
 * ログイン画面用サーブレット
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 初期表示
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Beanの初期化
		LoginBean bean = new LoginBean();
		bean.setErrorMessage("");
		bean.setUserId("");
		bean.setPassword("");

		req.setAttribute("loginBean", bean);
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 初期化
		LoginBean bean = new LoginBean();
		LoginModel model = new LoginModel();
		String direction = "/WEB-INF/jsp/login.jsp";

		// パラメータの取得
		String userId = (String) req.getParameter("userId");
		String password = (String) req.getParameter("password");

		bean.setUserId(userId);
		bean.setPassword(password);

		// 認証処理
		try {
			if (!halfSizeCheck(password)) {
				bean.setErrorMessage("半角で入力してください");
			} else if (!stringLengthCheck(password, 20)) {
				bean.setErrorMessage("20文字は受け付けません");
			} else {
				bean = model.authentication(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取得に成功した場合セッション情報をセット
		if ("".equals(bean.getErrorMessage())) {
			SessionBean sessionBean = new SessionBean();
			sessionBean.setUserName(bean.getUserName());
			sessionBean.setUserNo(bean.getUserNo());
			HttpSession session = req.getSession();
			session.setAttribute("session", sessionBean);
			session.setAttribute("loginBean", bean);
			// 行き先を次の画面に
			direction = "/main";
		}
		req.setAttribute("loginError", bean);
		req.getRequestDispatcher(direction).forward(req, res);
	}

	// 入力値のチェック
	public boolean stringLengthCheck(String input, int max) {
		// 何バイト分の長さであるかを取得
		int length = input.getBytes().length;
		if ((int) length > max) { // 最大文字数よりも多かった場合
			return false;
		}
		return true; // 許容内であった場合
	}

	// 半角チェック
	public boolean halfSizeCheck(String input) {
		boolean result = false;

		if (!(input == null) || !(input.length() == 0)) {
			int len = input.length();
			byte[] bytes = input.getBytes();
			if (len == bytes.length) {
				result = true;
			}
		}
		return result;
	}
}
