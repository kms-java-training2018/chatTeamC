package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupMessageBean;
import bean.LoginBean;
import bean.MessageCheckBean;
import model.CheckCharacter;
import model.GetTalkMessageModel;
import model.MessageCheckSendModel;

/**
 * グループメッセージ用サーブレット
 */
public class GroupMessageServlet extends HttpServlet {

	/**
	 * 初期表示
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 初期化
		//		MessageCheckBean MCBean = new MessageCheckBean();
		//		MessageCheckSendModel MCSModel = new MessageCheckSendModel();
		// 移動先のアドレス(グループメッセージ)
		String direction = "/WEB-INF/jsp/groupMessage.jsp";
		GroupMessageBean bean = new GroupMessageBean();
		GetTalkMessageModel model = new GetTalkMessageModel();
		// セッション情報取得 (ログインしているかどうか)
		String message;
		HttpSession session = req.getSession();
		// ログインできているか確認
		if (session.getAttribute("session") == null) {
			//ない場合、セッションにunllセットしてエラーページへ
			session.setAttribute("session", null);
			message = "不正なアクセスです。ログインしてください";
			req.setAttribute("error", message);
			direction = "/WEB-INF/jsp/errorPage.jsp";
			// req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		} else {

			// 自会員番号を取得
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			loginBean.setErrorMessage("");
			// 相手の会員番号の取得
			bean.setGroupNo((req.getParameter("toGroupNo")));
			//			MCBean.setToUserNo(Integer.parseInt((req.getParameter("toGroupNo"))));
			//String toGroupNo = (bean.getGroupNo());
			// 会話情報の取得
			try {
				if (!(model.CheckGroupMenber(loginBean, req.getParameter("toGroupNo")))) {
					message = "メンバーではありません";
					req.setAttribute("error", message);
					//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
					direction = "/WEB-INF/jsp/errorPage.jsp";
				} else {
					bean = model.authentication(bean, loginBean, req.getParameter("toGroupNo"));

					// もしも相手の番号が無い場合はエラーを表示
					req.setAttribute("GroupMessageBean", bean);
					req.setAttribute("myLoginNo", loginBean.getUserNo());
					session.setAttribute("GroupMessageBean", bean); //セッション内へ自分と相手の情報を保存
					//				MCBean = MCSModel.getTalkContent(MCBean, loginBean);
				}
			} catch (Exception e) {
				session.setAttribute("session", null);
				message = "相手の会話情報が入手できませんでした。";
				req.setAttribute("error", message);
				e.printStackTrace();
				//req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
				direction = "/WEB-INF/jsp/errorPage.jsp";
			}
		}
		req.getRequestDispatcher(direction).forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//String direction = "/WEB-INF/jsp/groupMessage.jsp";
		//req.getRequestDispatcher(direction).forward(req, res);
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */

		//入力チェックモデル
		CheckCharacter checkChara = new CheckCharacter();
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		} else {
			// 現在のセッションに入っているmessageCheckBean情報を受け取る
			MessageCheckBean bean = new MessageCheckBean(); //= (MessageCheckBean) session.getAttribute("GroupBean");
			String groupNoStr = ((GroupMessageBean) session.getAttribute("GroupMessageBean")).getGroupNo();
			int groupNo = Integer.parseInt(groupNoStr);
			bean.setToUserNo(groupNo);
			MessageCheckSendModel model = new MessageCheckSendModel();
			//現在のセッションに入っているloginBean情報を受け取る
			LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
			//メッセージ内容を取得
			String sendMessage = new String(req.getParameter("sendMessage").getBytes("ISO-8859-1"));
			bean.setSendMessage(sendMessage);
			//String sendMessage = bean.getSendMessage();

			//入力チェックの返答
			//空白処理
			if (checkChara.spaceCheck(sendMessage) == false) {
				req.setAttribute("error", "メッセージを入力してください");
				doGet(req, res);
			} else {
				//文字規格処理
				boolean bytecheck = checkChara.stringSizeCheck(sendMessage, 100);
				if (bytecheck == false) {
					req.setAttribute("error", "文字数オーバーです");
					doGet(req, res);
				} else {
					// 会話情報の取得
					try {
						model.sendGroupMessage(bean, loginBean);
						req.setAttribute("error", loginBean.getErrorMessage());
						// 初期化
						loginBean.setErrorMessage("");
					} catch (Exception e) {
						e.printStackTrace();
					}
					doGet(req, res);
					//			req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
				}
			}
		}
	}
}