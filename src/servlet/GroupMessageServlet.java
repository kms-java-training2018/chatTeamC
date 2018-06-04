package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupMessageBean;
import bean.LoginBean;
import model.GroupMessageModel;

/**
 * グループメッセージ用サーブレット
 */
public class GroupMessageServlet extends HttpServlet {

	/**
	 * 初期表示
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Beanの初期化
		GroupMessageBean bean = new GroupMessageBean();
		GroupMessageModel model = new GroupMessageModel();
		// セッション情報取得（ログインしているかどうか
		HttpSession session = req.getSession();
		// ログインできているか確認
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}
		//自会員番号を取得
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		// 会話情報の取得
		try {
			bean = model.authentication(bean, loginBean, req.getParameter("toGroupNo"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("GroupBean", bean);
		req.setAttribute("myLoginNo", loginBean.getUserNo());
		req.getRequestDispatcher("/WEB-INF/jsp/groupMessage.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String direction = "/WEB-INF/jsp/groupMessage.jsp";
		req.getRequestDispatcher(direction).forward(req, res);
	}
}