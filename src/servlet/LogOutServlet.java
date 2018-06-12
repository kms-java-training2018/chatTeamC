package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		// セッション情報取得
		HttpSession session = req.getSession();

		//ログインデータ初期化
		session.setAttribute("session", null);
		session.setAttribute("loginBean", null);
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
	}

//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		req.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp").forward(req, res);
//	}
}
