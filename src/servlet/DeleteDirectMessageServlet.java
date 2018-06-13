package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.MessageCheckBean;
import model.DeleteMessageModel;

/**
 * 個別メッセージの削除処理用サーブレット
 *
 * */
public class DeleteDirectMessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */

		//エラーメッセージ用のString
		String message;
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			//ない場合、セッションにnullセットしてエラーページへ
			session.setAttribute("session", null);
			message = "不正なアクセスです。ログインしてくださーい";
			req.setAttribute("error", message);
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			DeleteMessageModel model = new DeleteMessageModel();
			//現在のセッションに入っているloginBean情報を受け取る
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			MessageCheckBean messageCheckBean = (MessageCheckBean) session.getAttribute("messageCheckBean");
			messageCheckBean.getToUserNo();
			//消したいメッセージの会話情報番号を取得
			//※メインページにて、消したいメッセージの会話情報番号を送る"deleteMessageNo"タグをつける必要有り
			int deleteMessageNo = (Integer.parseInt(req.getParameter("deleteMessageNo")));
			try {
				model.deleteMessage(loginBean, deleteMessageNo);
			} catch (Exception e) {
				session.setAttribute("session", null);
				message = "処理中にエラーが発生しました。";
				req.setAttribute("error", message);
				e.printStackTrace();
			}
			//削除できたかどうかを表示。
			//メッセージ内容はmodel内でセット
			req.setAttribute("error", loginBean.getErrorMessage());
			loginBean.setErrorMessage("");
			//		//削除処理終了後、directMessageServletに移し、更新させる。
			DirectMessageServlet directMessageServlet = new DirectMessageServlet();
			directMessageServlet.doGet(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}
}