package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DeleteMessageBean;
import bean.LoginBean;
import model.DeleteMessageModel;

/**
 * 個別メッセージの削除処理用サーブレット
 * */
public class DeleteDirectMessage {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}
		//DeleteMessageBeanの初期化
		DeleteMessageBean bean = new DeleteMessageBean();
		DeleteMessageModel model = new DeleteMessageModel();
		//現在のセッションに入っているloginBean情報を受け取る
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		//消したいメッセージの会話情報番号を取得
		//※メインページにて、消したいメッセージの会話情報番号を送る"deleteMessageNo"タグをつける必要有り
		bean.setDeleteMessageNo(Integer.parseInt(req.getParameter("deleteMessageNo")));
		System.out.println(bean.getDeleteMessageNo());  //DeleteNoが受け取れているかの確認
		try {
			model.authentication(loginBean, bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}

	/**
	 * メッセージ削除の部分
	 * */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}
}
