<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/logout.js"
	charset="UTF-8">

</script>
<script type="text/javascript" src="JavaScript/Scrool.js">

</script>
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/mainPage.css">
<link rel="stylesheet" href="css/Title.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main menu</title>
</head>
<body>
	<div id="Lock">
		<table class="menu">
			<!-- 1段目 -->
			<tr>
				<th colspan="3">
					<p>Main menu</p>
				</th>
				<th class="userTitle">
					<p>ようこそ ${session.userName}さん</p> <input type="button"
					value="Logout" onClick="logout()" />
				</th>
			</tr>
			<!-- 2段目 -->
			<tr align="center">
				<td colspan="2" class="userTitle">
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
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<table class="menu">
		<!-- 3段目 -->
		<tr align="center"> <!-- class="hiddenBox" -->
			<th colspan="4" id="Direct">Personal chat</th>
		</tr>
		<tr align="center">
			<td class="name">Name</td>
			<td colspan="3" class="message">Massage</td>
		</tr>
		<c:forEach var="list"
			items="${MainPageBean.getLatestMenberMessageBeanList()}"
			varStatus="status">
			<tr align="center">
				<td><a href="/chat/directMessage?toUserNo=${list.getUserNo()}"
					class="Link">${list.getUserName()}</a></td>
				<td colspan="3">${list.getLatestMessage()}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>

	<table class="menu">
		<!-- 4段目 -->
		<tr align="center"><!-- class="hiddenBox" -->
			<th colspan="4" id="Group">Group chat</th>
		</tr>
		<tr align="center">
			<td class="name">Name</td>
			<td colspan="3" class="message">Massage</td>
		</tr>
		<c:forEach var="list"
			items="${MainPageBean.getLatestGroupMessageBeanList()}"
			varStatus="status">
			<tr align="center">
				<td><a href="/chat/groupMessage?toGroupNo=${list.getGroupNo()}"
					class="Link">${list.getGroupName()}</a></td>
				<td colspan="3">${list.getLatestMessage()}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>