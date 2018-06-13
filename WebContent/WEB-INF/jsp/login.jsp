<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/login.css" type="text/css" media="all">
<script type="text/javascript" src="JQuery/jquery-3.3.1.min.js"></script>
<script type="text/JavaScript" src="JavaScript/login.js" charset="UTF-8"></script>



<title>Login Page</title>
</head>
<body>
	<div align="center">

		<h1>Login Page</h1>

		<form action="/chat/login" method="POST" class="login" action="foo">
			<h2>Member ID</h2>
			<input id="username" type="text" name="userId" value="${loginBean.userId}">
			<h2>Password</h2>
			<input id="password" type="password" name="password" value="${loginBean.password}">
			<br> <input id="show-ps" type="checkbox" /><label for="show-ps">Show
				password</label> <br>
			<p>${loginError.errorMessage}</p>

			<input type="submit" value="Login">
		</form>
	</div>
      <!--  <form class="login" action="foo">
        <input id="username" type="text" place="ユーザー名" />
        <input id="password" type="password"  place="パスワード"/>
        <input id="submit" type="submit" value="ログイン" />
        <br />
        <input id="show-ps" type="checkbox" /><label for="show-ps">パスワードを表示</label>
    </form>-->

   <!-- <input type="password" id="pswd" name="pswd" />
<input type="text" id="pswd-text" name="pswd-text" style="display:none;" />
<input type="checkbox" id="pswd-toggle" name="pswd-toggle" data-for="pswd" />
<label for="pswd-toggle">パスワードを表示する</label>
 -->

</body>
</html>