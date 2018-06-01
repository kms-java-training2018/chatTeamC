package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GroupMessageBean;
import model.GroupMessageModel;

/**
 * グループメッセージ用サーブレット
 */
public class GroupMessageServlet extends HttpServlet {

	/**
	 * 初期表示
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		// Beanの初期化
		GroupMessageBean bean = new GroupMessageBean();
//		bean.my_group_no("");
//		bean.de_group_no("");
//		bean.my_user_no("");
//		bean.de_user_no("");
//
//		req.setAttribute("GroupMessageBean", bean);
		req.getRequestDispatcher("/WEB-INF/jsp/GroupMessage.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 初期化
		GroupMessageBean bean = new GroupMessageBean();
		GroupMessageModel model = new GroupMessageModel();
		String direction = "/WEB-INF/jsp/GroupMessage.jsp";
//
//		// パラメータの取得
//		String my_group_no = (String) req.getParameter("my_group_no");
//		String de_group_no = (String) req.getParameter("de_group_no");
//		String my_user_no = (String) req.getParameter("my_user_no");
//		String de_user_no = (String) req.getParameter("de_user_no");
//
//		bean.setMy_group_no(my_group_no);
//		bean.setDe_group_no(de_group_no);
//		bean.setMy_user_no(my_user_no);
//		bean.setDe_user_no(de_user_no);
//
//		// 認証処理
//		try {
//			bean = model.authentication(bean);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// 取得に成功した場合セッション情報をセット
//		if ("".equals(bean.getErrorMessage())) {
//			SessionBean sessionBean = new SessionBean();
//			sessionBean.setUserName(bean.getUserName());
//			sessionBean.setUserNo(bean.getUserNo());
//			HttpSession session = req.getSession();
//			session.setAttribute("session", sessionBean);
//
//			// 行き先を次の画面に
//			direction = "/main";
//		}
		req.getRequestDispatcher(direction).forward(req, res);
	}
}