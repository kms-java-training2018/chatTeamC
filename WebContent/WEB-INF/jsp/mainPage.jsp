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
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" width="80%" height="60vh"
		cellspacing="0" cellpadding="3">


		<!-- 1段目 -->
		<tr>
			<th colspan="3">
				<p>メインメニュー</p>
			</th>
			<th>
				<p>ようこそ ${session.userName}さん</p> <input type="button" value="ログアウト"
				onClick="logout()" />

			</th>
		</tr>
		<!-- 2段目 -->
		<tr align="center">
			<td colspan="2">
				<form action="/chat/makeGroup" method="POST">
					<input type="submit" value="グループの作成">
				</form>
			</td>
			<td colspan="2">
				<form action="/chat/myPage" method="POST">
					<input type="submit" value="プロフィール画面へ">
				</form>
			</td>
		</tr>
		<!-- 3段目 -->
		<tr align="center">
			<th colspan="4" id="Direct">個人チャット</th>
		</tr>
		<tr align="center">
			<td class="name">名前</td>
			<td colspan="3" class="message">最新メッセージ</td>
		</tr>
		<c:forEach var="list" items="${MainPageBean.getMemberTalk()}"
			varStatus="status">
			<tr align="center">
				<td><a href="/chat/directMessage?toUserNo=${list.get(2)}"
					class="Link">${list.get(0)}</a></td>
				<td colspan="3">${list.get(1)}</td>
			</tr>
		</c:forEach>
		<!-- 4段目 -->
		<tr align="center">
			<th colspan="4" id="Group">グループチャット</th>
		</tr>
		<tr align="center">
			<td class="name">名前</td>
			<td colspan="3" class="message">最新メッセージ</td>
		</tr>
		<c:forEach var="list" items="${MainPageBean.getGrowp()}"
			varStatus="status">
			<tr align="center">
				<td><a href="/chat/groupMessage?toGroupNo=${list.get(0)}"
					class="Link">${list.get(1)}</a></td>
				<td colspan="3">${list.get(2)}</td>
			</tr>
		</c:forEach>
		<!-- 5段目  練習用-->
		<tr>
			<c:forEach var="list" items="${MainPageBean.getGrowp()}"
				varStatus="status">
				<tr align="center">
					<td><a href="/chat/groupMessage?toGroupNo=${list.get(0)}"
						class="Link">${list.get(1)}</a></td>
					<td colspan="3">${list.get(2)}</td>
				</tr>
			</c:forEach>
		</tr>
	</table>
</body>
</html>