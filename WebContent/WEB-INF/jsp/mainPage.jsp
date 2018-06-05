<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/logout.js"
	charset="UTF-8">

</script>

<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/mainPage.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main menu</title>
</head>
<body>
	<table border="1" align="center" width="80%" height="60vh"
		cellspacing="0" cellpadding="3">


		<!-- 1段目 -->
		<tr>
			<th colspan="3">
				<p>Main menu</p>
			</th>
			<th class="back" >
				<p>ようこそ ${session.userName}さん</p> <input type="button" value="Logout"
				onClick="logout()" />

			</th>
		</tr>
		<!-- 2段目 -->
		<tr align="center">
			<td colspan="2" class="back">
				<form action="/chat/makeGroup" method="POST">
					<input type="submit" value="Group creation">
				</form>
			</td>
			<td colspan="2">
				<form action="/chat/myPage" method="POST">
					<input type="submit" value="Profile">
				</form>
			</td>
		</tr>
	</table>
	<br>
	<br>

	<table border="1" align="center" width="80%" height="60vh"
		cellspacing="0" cellpadding="3">
		<!-- 3段目 -->
		<tr align="center">
			<th colspan="4" id="Direct">Personal chat</th>
		</tr>
		<tr align="center">
			<td class="name">Name</td>
			<td colspan="3" class="message">Massage</td>
		</tr>
		<c:forEach var="list" items="${MainPageBean.getMemberTalk()}"
			varStatus="status">
			<tr align="center">
				<td><a href="/chat/directMessage?toUserNo=${list.get(2)}"
					class="Link">${list.get(0)}</a></td>
				<td colspan="3">${list.get(1)}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>

	<table border="1" align="center" width="80%" height="60vh"
		cellspacing="0" cellpadding="3">
		<!-- 4段目 -->
		<tr align="center">
			<th colspan="4" id="Group">Group chat</th>
		</tr>
		<tr align="center">
			<td class="name">Name</td>
			<td colspan="3" class="message">Massage</td>
		</tr>
		<c:forEach var="list" items="${MainPageBean.getGrowp()}"
			varStatus="status">
			<tr align="center">
				<td><a href="/chat/groupMessage?toGroupNo=${list.get(0)}"
					class="Link">${list.get(1)}</a></td>
				<td colspan="3">${list.get(2)}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>