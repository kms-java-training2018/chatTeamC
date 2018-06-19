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
<script type="text/javascript" src="JQuery/jquery-3.3.1.min.js">

</script>
<script>

</script>
<script type="text/javascript" src="JavaScript/mainPage.js">

</script>
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/mainPage.css">
<link rel="stylesheet" href="css/Title.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main menu</title>
</head>
<body id="background">
	<div id="Lock">
		<table class="titleMenu">
			<!-- 1段目 -->
			<tr>
				<th colspan="3" class="tableColor">
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
				<td colspan="2" class="tableColor">
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
	<div>
		<table class="menu">
			<!-- 3段目 -->
			<tr align="center">
				<th id="Direct">Personal chat</th>
			</tr>
		</table>
		<table class="menu" id="personalMessage">
			<tr align="center">
				<td class="nameTitle">Name</td>
				<td class="messageTitle">Message</td>
			</tr>
			<c:forEach var="list"
				items="${MainPageBean.getLatestGroupMessageBeanList()}"
				varStatus="status">
				<tr align="center" class="personalChatMessage">
					<td class="name"><a href="/chat/directMessage?toUserNo=${list.getGroupNo()}"
						class="Link">${list.getGroupName()}</a></td>
					<td class="message">${list.getLatestMessage()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<br>

	<table class="menu">
		<!-- 4段目 -->
		<tr align="center">
			<th id="Group">Group chat</th>
		</tr>
	</table>
	<table class="menu" id="groupMessage">
		<tr align="center">
			<td class="nameTitle">Name</td>
			<td class="messageTitle">Message</td>
		</tr>
		<c:forEach var="list"
			items="${MainPageBean.getLatestGroupMessageBeanList()}"
			varStatus="status">
			<tr align="center">
				<td class="name"><a href="/chat/groupMessage?toGroupNo=${list.getGroupNo()}"
					class="Link">${list.getGroupName()}</a></td>
				<td class="message">${list.getLatestMessage()}</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>