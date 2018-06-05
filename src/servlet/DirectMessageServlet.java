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
		// 初期化
		MessageCheckBean bean = new MessageCheckBean();
		MessageCheckSendModel model = new MessageCheckSendModel();
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		}
		//自会員番号を取得
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String myLogin = loginBean.getUserNo();
		String error = "";
		//相手の会員番号を取得
		bean.setToUserNo(Integer.parseInt(req.getParameter("toUserNo")));
		Integer toUserNo = (bean.getToUserNo());
		// 会話情報の取得
		try {
			bean = model.authentication(bean, loginBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(bean.getToUserName());  //名前取得用デバッグ
		// もしも相手の番号が無い場合はエラーを表示
		if (toUserNo == 0) {
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		} else {
			req.setAttribute("messageCheckBean", bean);
			req.setAttribute("myLoginNo", myLogin);
			req.setAttribute("error", error);
			session.setAttribute("messageCheckBean", bean); //セッション内へ自分と相手の情報を保存
			req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
		}
		//		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
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
		String error = "";
		// 現在のセッションに入っているmessageCheckBean情報を受け取る
		MessageCheckBean bean = (MessageCheckBean) session.getAttribute("messageCheckBean");
		MessageCheckSendModel model = new MessageCheckSendModel();
		//現在のセッションに入っているloginBean情報を受け取る
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		//メッセージ内容を取得
		String sendMessage = new String(req.getParameter("sendMessage").getBytes("ISO-8859-1"));
		bean.setSendMessage(sendMessage);
		//String sendMessage = bean.getSendMessage();

		//入力チェックの返答
		int bytecheck = 0;
		bytecheck = bean.stringLengthCheck(sendMessage);
		if (bytecheck == 1) {
			error ="文字数オーバーです";
			req.setAttribute("error", error);
			doGet(req, res);
		} else {
			// 会話情報の取得
			try {
				model.sendMessage(bean, loginBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//メッセージ送信処理終了後、doGetに移し、更新させる。
			doGet(req, res);
		}
	}
}
