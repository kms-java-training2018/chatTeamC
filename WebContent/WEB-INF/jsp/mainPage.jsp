<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/logout.js"
	charset="UTF-8">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <input type="button"value="ログアウト" onClick="logout()" />
	<h1>チャット研修プログラム</h1>
	<h2>メインメニュー</h2>
	<br>■会話一覧
	<table border="1">
			<tr align="center">
				<td>名前</td>
    			<td>最新メッセージ</td>
			</tr>
		<c:forEach var="list" items="${MainPageBean.getMemberTalk()}"
			varStatus="status">
			<tr align="center">
				<td><a href="/chat/directMessage?toUserNo=${list.get(2)}" style="color:#0000ff;text-decoration:none">${list.get(0)}</a></td>
    			<td>${list.get(1)}</td>
			</tr>
		</c:forEach>
	</table>
	<br>■グループ一覧
	<table border="1">
			<tr align="center">
				<td>名前</td>
    			<td>最新メッセージ</td>
			</tr>
		<c:forEach var="list" items="${MainPageBean.getGrowp()}"
			varStatus="status">
			<tr align="center">
				<td><a href="/chat/groupMessage?toGroupNo=${list.get(0)}" style="color:#0000ff;text-decoration:none">${list.get(1)}</a></td>
    			<td>${list.get(2)}</td>
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