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
		GroupMessageBean bean = new GroupMessageBean();
		GetTalkMessageModel model = new GetTalkMessageModel();
		// セッション情報取得 (ログインしているかどうか)
		HttpSession session = req.getSession();
		// ログインできているか確認
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
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
				bean = model.authentication(bean, loginBean, req.getParameter("toGroupNo"));
				//				MCBean = MCSModel.getTalkContent(MCBean, loginBean);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// もしも相手の番号が無い場合はエラーを表示
			req.setAttribute("GroupMessageBean", bean);
			req.setAttribute("myLoginNo", loginBean.getUserNo());
			session.setAttribute("GroupMessageBean", bean); //セッション内へ自分と相手の情報を保存
			req.getRequestDispatcher("/WEB-INF/jsp/groupMessage.jsp").forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//String direction = "/WEB-INF/jsp/groupMessage.jsp";
		//req.getRequestDispatcher(direction).forward(req, res);
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, res);
		} else {
			// 現在のセッションに入っているmessageCheckBean情報を受け取る
			MessageCheckBean bean = new MessageCheckBean(); //= (MessageCheckBean) session.getAttribute("GroupBean");
			String groupNoStr = ((GroupMessageBean)session.getAttribute("GroupMessageBean")).getGroupNo();
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
			int bytecheck = 0;
			bytecheck = model.stringLengthCheck(sendMessage);
			if (bytecheck == 1) {
				req.setAttribute("error", "文字のデータサイズオーバーです");
				doGet(req, res);
			} else {
				// 会話情報の取得
				try {
					model.sendGroupMessage(bean, loginBean);
					req.setAttribute("error", loginBean.getErrorMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				doGet(req, res);
				//			req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
			}
		}
	}
}