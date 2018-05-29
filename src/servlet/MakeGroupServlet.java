package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupBean;
import bean.LoginBean;
import model.GroupCreat;

public class MakeGroupServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		req.getRequestDispatcher("/WEB-INF/jsp/makeGroup.jsp").forward(req, res);
		LoginBean bean = new LoginBean();
		bean.setErrorMessage("");
		bean.setUserId("");
		bean.setPassword("");

		req.setAttribute("loginBean", bean);
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {



		String direction = "/WEB-INF/jsp/login.jsp";


		//セッション設定
		HttpSession session = req.getSession();

		//セッションに値があるかどうかでif
		if (session.getAttribute("session") != null) {
			//グループビーンの設定
			GroupBean groupBean = new GroupBean();
			GroupCreat groupCreate = new GroupCreat();
			//GroupModelの中のGroupBeanを、こちらのGroupBeanに入れる

			System.out.println("kok");
			groupBean = groupCreate.authentication();
			//セッションにセットする
			session.setAttribute("groupBean", groupBean);

			direction = "/WEB-INF/jsp/makeGroup.jsp";


		}
		req.getRequestDispatcher(direction).forward(req, res);


	}

}
