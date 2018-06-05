<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="chat/JavaScript" src="JavaScript/logout.js"
	charset="UTF-8">
</script>

<link rel="stylesheet" href="css/login.css" type="text/css" media="all">


<title>Login Page</title>
</head>
<body>
	<div align="center">

		<h1>Login Page</h1>

		<form action="/chat/login" method="POST">
			<h2>Member ID</h2>
			<input type="text" name="userId" value="${loginBean.userId}">
			<h2>Password</h2>
			<input type="text" name="password" value="${loginBean.password}">
			<br>
			<p>${loginError.errorMessage}</p>

			<input type="submit" value="ログイン">
		</form>
	</div>
</body>
</html>