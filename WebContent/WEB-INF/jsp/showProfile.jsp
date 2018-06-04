<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="chat/JavaScript" src="JavaScript/logout.js"
	charset="UTF-8">
</script>
<link href="css/base.css" rel="stylesheet" type="texr/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>

	<form action="/chat/showProfile" method="POST">
	<table border="1" align="center" width="80%" height="60vh"
		cellspacing="0" cellpadding="3">

		<!-- 1段目 -->
		<tr>
			<th colspan="3">
				<p>Profile</p>
			</th>
		</tr>

		<!-- 2段目 -->
		<tr>
			<td><br><br>
				<p>${Name}</p>
				<br><br>
			</td>

			<td colspan="2" ><br><br><p>${Profile}</p><br><br></td>

		</tr>

		<!-- 3段目 -->
		<tr>
			<th colspan="3">
				<p>
					<a href="#" onClick="window.close();"><input type="submit"
						value="閉じる"></a>
				</p>
			</th>
		</tr>

	</table>
</form>
</body>
</html>