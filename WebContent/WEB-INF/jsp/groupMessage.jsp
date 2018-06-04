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
	<h2>${GroupBean.getGroupName()}</h2>

	<c:forEach var="list" items="${GroupBean.getNumber()}"
		varStatus="status"><!-- 名前にその人のプロフィールに飛ぶリンクを付ける
					名前（リンク：会員No）：会話情報
					というように結果が出力  -->
					<form action="/chat/groupMessage" method="POST">
					<c:if test="${GroupBean.getNumber().get(status.index) == myLoginNo}" >
					${GroupBean.getName().get(status.index)}：${GroupBean.getText().get(status.index)}
					<input type="submit" value="削除">
					</c:if>
					<c:if test="${GroupBean.getNumber().get(status.index) != myLoginNo}">
					<a href="/chat/showProfile?toUserNo=${GroupBean.getNumber().get(status.index)}" target=”_blank” >
					${GroupBean.getName().get(status.index)}</a>
					：${GroupBean.getText().get(status.index)}
					</c:if>
				</form>
	</c:forEach>
	<form action="/chat/groupMessage" method="POST">
		<input type="text" name="sendMessage" value="">
		<input type="submit" value="メッセージの送信">
	</form>
	<form action="/chat/main" method="POST">
		<input type="submit" value="メインメニューに戻る">
	</form>
</body>
</html>