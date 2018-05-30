package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupBean;
import bean.LoginBean;
import bean.SessionBean;
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
		SessionBean sessionBean = new SessionBean();

		//グループビーンの設定(jspに送る用の空ビーン)
		GroupBean groupBean = new GroupBean();
		//今から処理させるモデル
		GroupCreat groupCreat = new GroupCreat();

//		セッションに値があるかどうかでif
		if (session.getAttribute("session") != null) {

			//グループ作成画面から来たかどうかの判断if
			if(req.getParameter("userNo") != null) {

				//groupCreateにsessionのbean引き継がせる
				groupCreat.setGroupBean((GroupBean)session.getAttribute("groupBean"));


				//指定されたグループ名をもらう
				String name = req.getParameter("groupName");
				//モデルにセット
				groupCreat.setGroupName(name);


				//グループへ登録
				String sucsess =groupCreat.CreatGroup();

				System.out.println(sucsess);

				//選択されたユーザーをreqからもらう
				String userNo[];
				userNo = req.getParameterValues("creat");
				//抜き取った配列をGroupBeanへ送ってグループ作成
				String message = groupCreat.ResistGroup(userNo);

				System.out.println(message);

				direction = "/WEB-INF/jsp/mainPage.jsp";

			}



			// ログインデータ取得
            sessionBean = (SessionBean)session.getAttribute("session");
            System.out.println(sessionBean.getUserName());
			String autherName = sessionBean.getUserName();



			//GroupModelの中のGroupBeanを、こちらのGroupBeanに入れる
			System.out.println("kok");
			//空のビーンに、
			//モデルの中のメソッド(帰ってくるのがビーン)
			//処理させるための情報をモデルに引数として渡して、


			//ビーンで帰ってくるから
			//それを空のビーンにつめる
			groupBean = groupCreat.authentication(autherName);

			//test表示
			ArrayList<String> test = groupBean.getUserName();
			for(String name : test) {
				System.out.println(name);
			}

			//セッションにセットしてjspに送る
			session.setAttribute("groupBean", groupBean);

			direction = "/WEB-INF/jsp/makeGroup.jsp";


		}
		req.getRequestDispatcher(direction).forward(req, res);


	}

}
