<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/login.css" type="text/css" media="all">
<script type="text/javascript" src="JQuery/jquery-3.3.1.min.js"></script>
<script type="text/JavaScript" src="JavaScript/login.js" charset="UTF-8"></script>



<title>Create Account</title>
</head>
<body>
	<div align="center">

		<h1>Create Account</h1>

		<form action="/chat/createAccountServlet" onSubmit="return nidoosi()" method="POST" class="login"
			action="foo">
			<h2>Member ID(20)</h2>
			<input id="username" type="text" name="userId"
				value="${loginBean.userId}">
			<h2>Password(20)</h2>
			<input id="username" type="text" name="password"
				value="${loginBean.userId}">
			<h2>Username(30)</h2>
			<input id="username" type="text" name="userName"
				value="${loginBean.userId}">
			<h2>Profile(100)</h2>
			<input id="username" type="text" name="profile"
				value="${loginBean.userId}">


			<p>${loginError.errorMessage}</p>
			<p>${loginError.profileErrorMessage}</p>
			<p>${loginError.sqlErrorMessage}</p>
			<p>${loginError.idErrorMessage}</p>



			 <input type="submit" value="Create" style="height: 27px; ">     <button type="button" onClick="location.href='./login';" style="height: 27px; ">Back</button>
		</form>

	</div>
</body>
</html>