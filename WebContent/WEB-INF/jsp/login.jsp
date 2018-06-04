<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="chat/JavaScript" src="JavaScript/logout.js"
	charset="UTF-8">
</script>

<link href="css/title.css" rel="stylesheet" type="text/css" media="all">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
</head>
<body>

<h1 class="ribbon">
   <span class="ribbon-content">Login page</span>
</h1>


	<form action="/chat/login" method="POST">
		<p>会員ID</p>
		<input type="text" name="userId" value="${loginBean.userId}">
		<p>パスワード</p>
		<input type="text" name="password" value="${loginBean.password}">
		<br>
		<p>${loginError.errorMessage}</p>

		<input type="submit" value="ログイン">
	</form>
</body>
</html>