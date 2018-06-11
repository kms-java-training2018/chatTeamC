<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@
page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/deleteMessage.js"
	charset="UTF-8">

</script>
<script type="text/javascript" src="JavaScript/logout.js"
	charset="UTF-8">

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人チャットページ</title>
</head>
<body>
	<script type="text/javascript" src="JavaScript/mainPage.js"></script>
	<div>
		<p>ようこそ ${session.userName}さん</p>
		<input type="button" value="ログアウト" onClick="logout()" />
		<h1>
			～ <a
				href="/chat/showProfile?toUserNo=${messageCheckBean.getToUserNo()}"
				target=”_blank”>${messageCheckBean.getToUserName()}</a>さんとの会話部屋～
		</h1>
	</div>
	<h2>メッセージ</h2>
	<c:forEach var="list"
		items="${messageCheckBean.getTalkContentBeanList()}"
		varStatus="status">
		<!-- 名前にその人のプロフィールに飛ぶリンクを付ける
					名前（リンク：会員No）：会話情報
					というように結果が出力  -->
		<form action="/chat/deleteDirectMessage" method="GET">
			<c:if test="${list.getUserNo() == myLoginNo}">
					${list.getUserName()}：${list.getMessage()}
				<input type="button" value="削除"
					onClick="deleteMessage('${list.getMessageNo()}','toUserNo=${messageCheckBean.getToUserNo()}','deleteDirectMessage')" />
			</c:if>
			<c:if test="${list.getUserNo() != myLoginNo}">
				<a href="/chat/showProfile?toUserNo=${list.getUserNo()}"
					target=”_blank”>${list.getUserName()}</a>
					：${list.getMessage()}
					</c:if>
		</form>
	</c:forEach>
	<form action="/chat/directMessage" method="POST">

		<p>送りたいメッセージを書くのです！（｀・ω・´）</p>
		<p>
			<font size="5" color="red">${error}</font>
		</p>

		<input type="hidden" name="deleteMessageNo"
			value="${list.getMessageNo()}"> <input type="hidden"
			name="toUserNo" value="${messageCheckBean.getToUserNo()}"> <input
			type="text" name="sendMessage" value=""> <input type="submit"
			value="メッセージの送信">
	</form>
	<form action="/chat/main" method="POST">
		<input type="submit" value="メインメニューへ戻る">
	</form>
</body>
</html>