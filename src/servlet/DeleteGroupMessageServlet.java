package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DeleteMessageBean;
import bean.LoginBean;
import model.DeleteMessageModel;

public class DeleteGroupMessageServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// セッション情報取得（ログインしているかどうか
		HttpSession session = req.getSession();
		// ログインできているか確認
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}

		// DeleteMessageBeanの初期化
		DeleteMessageBean bean = new DeleteMessageBean();
		DeleteMessageModel model = new DeleteMessageModel();

		//自会員番号を取得
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		// 会話情報の取得
		try {
			model.authentication(loginBean, bean);
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