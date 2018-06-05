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
<link href="css/base.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Other Profile</title>
</head>
<body>

	<br>
	<br>
	<form action="/chat/showProfile" method="POST">
		<table border="1" align="center" width="80%" height="60vh"
			cellspacing="0" cellpadding="3" bgcolor="#dceaf8">

			<!-- 1段目 -->
			<tr>
				<th>
					<p>Profile</p>
				</th>
			</tr>
		</table>
		<br> <br>

		<!-- 2段目 -->
		<table border="1" align="center" width="80%" height="60vh"
			cellspacing="0" cellpadding="3">
			<tr>
				<td  bgcolor="#f7fafd"><br>
					<p>${Name}</p> <br></td>

				<td colspan="2" style="width: 158px; "><br>
					<p>${Profile}</p> <br></td>

			</tr>
		</table>

		<!-- 3段目 -->
		<div align="center">
			<br><br><br><p>
				<a href="#" onClick="window.close();"><input type="submit"
					value="Close"></a>
			</p>
		</div>




	</form>
</body>
</html>