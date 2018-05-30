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

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// 初期化
		MessageCheckBean bean = new MessageCheckBean();
		MessageCheckSendModel model = new MessageCheckSendModel();
		LoginBean loginBean = (LoginBean) req.getAttribute("loginBean");
		/*
		 * セッション情報取得
		 * もしもセッションが無ければエラー
		 * */
		HttpSession session = req.getSession();
		if (session.getAttribute("session") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}

		//相手の会員番号を取得　※メインページにて、相手会員番号を送る"toUserNo"タグをつける必要有り
		bean.setToUserNo(req.getParameter("toUserNo"));
		String toUserNo = bean.getToUserNo();
		// 認証処理
		try {
			bean = model.authentication(bean, loginBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// もしも相手の番号が無い場合はエラーを表示　※仮入力＜modelにて、DB内に相手の番号が存在するかの判断が必要＞
		if (toUserNo == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
		}else {
			req.setAttribute("messageCheckBean", bean);
			req.getRequestDispatcher("/WEB-INF/jsp/directMessage.jsp").forward(req, res);
		}
	}
}
