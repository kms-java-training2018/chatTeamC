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
 * */
public class DeleteDirectMessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}
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
			e.printStackTrace();
		}
		//削除できたかどうかを表示。
		//メッセージ内容はmodel内でセット
		req.setAttribute("error", loginBean.getErrorMessage());
		loginBean.setErrorMessage("");
//		//削除処理終了後、directMessageServletに移し、更新させる。（エラーメッセージの初期化処理を通ってしまう）
		DirectMessageServlet directMessageServlet = new DirectMessageServlet();
		directMessageServlet.doGet(req, res);
//		//直接個人ページに飛ばすパターン。servlet内のexceptionに飛ぶため、表示に一部
//		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}
}