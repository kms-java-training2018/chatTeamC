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

public class DeleteGroupMessageServlet extends HttpServlet {
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
		//消したいメッセージの会話情報番号を取得
		//※メインページにて、消したいメッセージの会話情報番号を送る"deleteMessageNo"タグをつける必要有り
		bean.setDeleteMessageNo(Integer.parseInt(req.getParameter("deleteMessageNo")));
		System.out.println(bean.getDeleteMessageNo()); //DeleteNoが受け取れているかの確認
		// 会話情報の取得
		try {
			model.deleteMessage(loginBean, bean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
		GroupMessageServlet groupMessageServlet = new GroupMessageServlet();
		groupMessageServlet.doGet(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String direction = "/WEB-INF/jsp/groupMessage.jsp";
		req.getRequestDispatcher(direction).forward(req, res);
	}
}