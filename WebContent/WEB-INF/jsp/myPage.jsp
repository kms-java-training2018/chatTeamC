<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="JavaScript/logout.js"
	charset="UTF-8">

</script>

<link rel="stylesheet" href="css/Title.css">
<link rel="stylesheet" href="css/myPage.css">

<title>My Page</title>

</head>
<body id="background">
	<div align="center" id="Lock">
		<h2>ようこそ ${session.userName}さん</h2>
		<input type="button" value="Logout" onClick="logout()">
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<table id="myPageTitle">
		<tr>
			<td>
				<p id="myPageName">MyProfile</p>
			</td>
		</tr>
	</table>

	<br>
	<br>
	<form action="/chat/main" method="POST">
		<p class="textTitle">Name</p>
		<input type="text" name="myName" value="${name}" class="text">
		<p>${erorr}</p>
		<p class="textTitle">
			<br>Profile
		</p>
		<input type="text" name="myProfile" value="${profile}" class="text" maxlength="100">
		<p></p>
		<br> <input type="submit" value="Update" name="newProfile"
			class="button"><input type="submit" value="Back"
			class="button">
	</form>
</body>
</html>