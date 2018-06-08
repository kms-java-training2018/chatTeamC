package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.MessageCheckBean;
import model.MessageCheckSendModel;

public class DirectMessageServlet extends HttpServlet {

	/**
	 * 会話情報の表示の部分
	 * */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		MessageCheckBean bean = new MessageCheckBean();
		// 初期化
		MessageCheckSendModel model = new MessageCheckSendModel();
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		} else {
			//自会員番号を取得
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			String myLogin = loginBean.getUserNo();
			//相手の会員番号を取得
			bean.setToUserNo(Integer.parseInt(req.getParameter("toUserNo")));
			Integer toUserNo = (bean.getToUserNo());
			// 会話情報の取得
			try {
				bean = model.getTalkContent(bean, loginBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// もしも相手の番号が無い場合はエラーを表示
			if (toUserNo == 0) {
				req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			} else {
				req.setAttribute("messageCheckBean", bean);
				req.setAttribute("myLoginNo", myLogin);
				session.setAttribute("messageCheckBean", bean); //セッション内へ自分と相手の情報を保存
				req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
			}
		}
	}

	/**
	 * メッセージ送信
	 * */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		}
		// 現在のセッションに入っているmessageCheckBean情報を受け取る
		MessageCheckBean bean = (MessageCheckBean) session.getAttribute("messageCheckBean");
		MessageCheckSendModel model = new MessageCheckSendModel();
		//現在のセッションに入っているloginBean情報を受け取る
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		//メッセージ内容を取得
		String sendMessage = new String(req.getParameter("sendMessage").getBytes("ISO-8859-1"));
		bean.setSendMessage(sendMessage);

		//入力チェックの返答
		int bytecheck = 0;
		bytecheck = model.stringLengthCheck(sendMessage);
		if (bytecheck == 1) {
			req.setAttribute("error", "文字数オーバーです");
			doGet(req, res);
		} else {
			// 会話情報の取得
			try {
				req.setAttribute("error", "");
				model.sendMessage(bean, loginBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//メッセージ送信処理終了後、doGetに移し、更新させる。
			doGet(req, res);
		}
	}
}
