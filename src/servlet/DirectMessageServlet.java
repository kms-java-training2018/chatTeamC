package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;
import bean.MessageCheckBean;
import model.CheckCharacter;
import model.MessageCheckSendModel;

public class DirectMessageServlet extends HttpServlet {

	// 移動するページ用変数

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

		//エラーメッセージ用のString
		String message;
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			//ない場合、セッションにnullセットしてエラーページへ
			session.setAttribute("session", null);
			message = "不正なアクセスです。ログインしてください";
			req.setAttribute("error", message);
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		} else {
			try {
				// 自会員番号を取得
				LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
				String myLogin = loginBean.getUserNo();
				//同一会員番号かのチェック用変数"userNo"定義
				Integer userNo = Integer.parseInt(myLogin);
				// エラーメッセージの初期化
				loginBean.setErrorMessage("");
				// 相手の会員番号を取得
				bean.setToUserNo(Integer.parseInt(req.getParameter("toUserNo")));
				Integer toUserNo = (bean.getToUserNo());
				//会員番号が一致した場合エラーページへ遷移
				if (userNo == toUserNo) {
					session.setAttribute("session", null);
					message = "不正なアクセスです。";
					req.setAttribute("error", message);
					req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
				}
				// 会話情報の取得
				bean = model.getTalkContent(bean, loginBean);
				// もしも相手の番号が無い場合はエラーを表示
				if (toUserNo == 0) {
					session.setAttribute("session", null);
					message = "相手の番号が不明です。";
					req.setAttribute("error", message);
					req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
				} else {
					//req.setAttribute("error", loginBean.getErrorMessage());
					req.setAttribute("messageCheckBean", bean);
					req.setAttribute("myLoginNo", myLogin);
					session.setAttribute("messageCheckBean", bean); //セッション内へ自分と相手の情報を保存
					req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
				}
			} catch (Exception e) {
				session.setAttribute("session", null);
				message = "相手の会話情報が入手できませんでした。";
				req.setAttribute("error", message);
				e.printStackTrace();
				req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			}
		}
	}
	/**
	 * ○メッセージ送信処理
	 *
	 * */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */

		// 入力中文字設定
		String setMessage = new String(req.getParameter("setMessage").getBytes("ISO-8859-1"));
		if (setMessage != null && req.getParameter("Transmission") == null) {
			System.out.println(setMessage);
			req.setAttribute("setText", setMessage);
			doGet(req, res);
		} else {

			//エラーメッセージ用のString
			String message;
			HttpSession session = req.getSession();
			if (session.getAttribute("session") == null) {
				//ない場合、セッションにnullセットしてエラーページへ
				session.setAttribute("session", null);
				message = "不正なアクセスです。ログインしてください";
				req.setAttribute("error", message);
				req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
			} else {
				// 現在のセッションに入っているmessageCheckBean情報を受け取る
				MessageCheckBean bean = (MessageCheckBean) session.getAttribute("messageCheckBean");
				MessageCheckSendModel model = new MessageCheckSendModel();
				//現在のセッションに入っているloginBean情報を受け取る
				LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
				Integer userNo = Integer.parseInt(loginBean.getUserNo());
				Integer toUserNo = bean.getToUserNo();
				//会員番号が一致した場合エラーページへ遷移
				if (userNo == toUserNo) {
					session.setAttribute("session", null);
					message = "不正なアクセスです。";
					req.setAttribute("error", message);
					req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
				}
				//バイト数チェック用モデルを用意
				CheckCharacter checkCharacter = new CheckCharacter();
				//●メッセージ内容を取得
				String sendMessage = new String(req.getParameter("sendMessage").getBytes("ISO-8859-1"));
				/*
				 * ○シングルクォーテーション表示用処理
				 * シングルクォーテーションを表示させる為に、
				 * 「'」⇒「''」に置換する。
				 * */
				sendMessage = checkCharacter.singleQuotation(sendMessage);
				/*
				 * ○メッセージが空文字（またはスペースのみ）かどうかの判定
				 * checkCharacterのspaceCheckにて、スペースを除いて
				 * 文字が残っている場合trueを返し、elseを通って
				 * メッセージを送信用のモデルに送る。
				 * 文字が残っていない場合、falseを返し、if文を通って
				 * 空文字として判断してエラーメッセージを表示させる。
				 * */

				try {
					if (checkCharacter.spaceCheck(sendMessage) == false) {
						req.setAttribute("error", "文字を入力してください。");
					} else {
						bean.setSendMessage(sendMessage);
						/*
						 * ○入力されたメッセージのサイズをチェック
						 * サイズの大きい文字が用いられ、100文字分のサイズを超えた場合、
						 * エラーメッセージを表示させる。
						 * */
						boolean bytecheck = checkCharacter.stringSizeCheck(sendMessage, 100);
						if (bytecheck == false) {
							req.setAttribute("error", "文字数オーバー");
						} else {
							// 会話情報の取得
							model.sendMessage(bean, loginBean);
							req.setAttribute("error", loginBean.getErrorMessage());
							loginBean.setErrorMessage("");
						}
					}
					doGet(req, res);
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("session", null);
					message = "データの送信に失敗しました";
					req.setAttribute("error", message);
					req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
				}
			}
		}
	}
}
