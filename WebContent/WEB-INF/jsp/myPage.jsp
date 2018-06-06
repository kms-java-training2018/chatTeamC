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

<title>My Page</title>

</head>
<body>

ようこそ ${session.userName}さん<br>
	<input type="button" value="Logout" onClick="logout()" style="width: 79px; height: 27px"><br><br><br>
	<div align="center">
		<table border="1" align="center" width="80%" height="60vh"
			cellspacing="0" cellpadding="3" bgcolor="	#b8f1f1" style="width: 493px; height: 96px">
			<th><br>
				<p>My Page</p> <br></th>
		</table>

		<br> <br>

		<form action="/chat/main" method="POST">
			<p>Name</p>
			<input type="text" name="myName" value="${name}" style="height: 37px; width: 210px">
			<p>${erorr}</p>
			<p><br>Profile</p>
			<input type="text" name="myProfile" value="${profile}" style="width: 210px; height: 37px">
			<p></p>
			<br> <input type="submit" value="Update" name="newProfile" style="width: 90px; "><input type="submit" value="Back" style="width: 90px; ">
		</form>
		<form action="/chat/main" method="POST">

		</form>
	</div>

</body>
</html>