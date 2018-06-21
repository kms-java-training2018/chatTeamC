package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LoginBean;
import bean.ResistAccountBean;
import model.CreateAccount;

/**
 * グループ作成用サーブレット
 */
public class CreateAccountServlet extends HttpServlet {

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
		ResistAccountBean bean = new ResistAccountBean();
		CreateAccount model = new CreateAccount();
		String direction = "/WEB-INF/jsp/login.jsp";


		// パラメータの取得
		bean.setUserId((String) req.getParameter("userId"));
		bean.setPassword((String) req.getParameter("password"));
		bean.setUserName((String) req.getParameter("userName"));
		bean.setProfile((String) req.getParameter("profile"));


		//文字入力チェック
		bean = model.checkData(bean);
		//OKだったら登録、Noだったらエラー
		if(bean.isCheckJudge() == true) {

		try {
				// 認証処理
				bean = model.resistUser(bean);


		} catch (Exception e) {
			direction = "/WEB-INF/jsp/createAccount.jsp";
			bean.setErrorMessage("登録できませんでした。");
		}

		// 取得に成功した場合セッション情報をセット
		if ("".equals(bean.getErrorMessage())) {
			bean.setErrorMessage("会員登録出来ました。");

		}
		}else {
		req.setAttribute("loginError", bean);
		}

		req.getRequestDispatcher(direction).forward(req, res);
	}

}
