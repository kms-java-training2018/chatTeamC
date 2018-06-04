package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 個別メッセージの削除処理用サーブレット
 * */
public class DeleteDirectMessage {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
			req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}

	/**
	 * メッセージ削除の部分
	 * */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}
		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}
}
