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
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}
		//自会員番号を取得
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String myLogin = loginBean.getUserNo();
		//相手の会員番号を取得　※メインページにて、相手会員番号を送る"toUserNo"タグをつける必要有り
		bean.setToUserNo(Integer.parseInt(req.getParameter("toUserNo")));
		Integer toUserNo = (bean.getToUserNo());
		// 会話情報の取得
		try {
			bean = model.authentication(bean, loginBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// もしも相手の番号が無い場合はエラーを表示　※仮入力＜modelにて、DB内に相手の番号が存在するかの判断が必要＞
		if (toUserNo == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		} else {
			req.setAttribute("messageCheckBean", bean);
			req.setAttribute("myLoginNo", myLogin);
			req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
		}
		//		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}

	/**
	 * メッセージ送信、メッセージ削除の部分
	 * MessageCheckBeanの取り込みに失敗中
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
		// 現在のセッションに入っているmessageCheckBean情報を受け取る※情報を受け取れていない
		MessageCheckBean bean =(MessageCheckBean)session.getAttribute("bean");
		MessageCheckSendModel model = new MessageCheckSendModel();
		//現在のセッションに入っているloginBean情報を受け取る
		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		System.out.println(loginBean.getUserNo());	//loginBean確認１
		System.out.println(bean.getToUserNo());	//MessageCheckBean確認１※エラー発生中
		//メッセージ内容を取得
		String sendMessage = new String(req.getParameter("sendMessage").getBytes("ISO-8859-1"));
		System.out.println(sendMessage);	//sendMessage確認１
		bean.setSendMessage(sendMessage);
		System.out.println(sendMessage + "aaaa");	//デバッグ2
		//String sendMessage = bean.getSendMessage();

		//入力チェックの返答
		int bytecheck = 0;
		bytecheck = bean.stringLengthCheck(sendMessage);
		if (bytecheck == 1) {
			loginBean.setErrorMessage("文字数オーバーです");
			req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
		} else {
			// 会話情報の取得
			try {
				bean = model.sendMessage(bean, loginBean);
			} catch (Exception e) {
				e.printStackTrace();
			}

			req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
		}
	}
}
