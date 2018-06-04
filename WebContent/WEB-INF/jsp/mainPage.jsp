<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <a href="/chat/logOut">ログアウト</a>
	<h1>チャット研修プログラム</h1>
	<h2>メインメニュー</h2>
	<br><div align="right">■会員一覧</div>
	<c:forEach var="list" items="${MainPageBean.getMember()}"
		varStatus="status">
		<div align="right">${list.get(1)}</div>
	</c:forEach>
	<br>■会話一覧
	<table border="1">
		<c:forEach var="list" items="${MainPageBean.getMemberTalk()}"
			varStatus="status">
			<tr align="center">
				<td><br>
    				<a href="/chat/directMessage?toUserNo=${list.get(2)}">${list.get(0)}</a>
					<p>${list.get(1)}</p></td>
			</tr>
		</c:forEach>
	</table>
	<br>■グループ一覧
	<table border="1">
		<c:forEach var="list" items="${MainPageBean.getGrowp()}"
			varStatus="status">
			<tr align="center">
				<td><br> <a href="/chat/groupMessage?toGroupNo=${list.get(0)}">${list.get(1)}</a>
					<p>${list.get(2)}</p></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<form action="/chat/makeGroup" method="POST">
		<input type="submit" value="グループの作成">
	</form>
	<form action="/chat/myPage" method="POST">
		<input type="submit" value="プロフィール画面へ">
	</form>


</body>
</html>