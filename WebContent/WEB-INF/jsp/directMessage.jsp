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
<script type="text/javascript" src="JavaScript/Message.js"
	charset="UTF-8">

</script>
<script type="text/javascript" src="JavaScript/logout.js"
	charset="UTF-8">

</script>

<script type="text/javascript" src="JavaScript/Scrool.js"></script>
<script type="text/javascript" src="JavaScript/button.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/Title.css">
<link rel="stylesheet" href="css/Message.css">
<title>個人チャットページ</title>
</head>
<body onload="footerStart();" id="background">
	<div id="Lock">
		<p>ようこそ ${session.userName}さん</p>
		<input type="button" value="ログアウト" onClick="logout()" />
		<h2>
			 <a
				href="/chat/showProfile?toUserNo=${messageCheckBean.getToUserNo()}"
				target=”_blank” class="Link">${messageCheckBean.getToUserName()}</a>さんとの会話部屋
		</h2>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<!--<h2>メッセージ</h2>-->
	<c:forEach var="list"
		items="${messageCheckBean.getTalkContentBeanList()}"
		varStatus="status">
		<form action="/chat/deleteDirectMessage" method="GET" onSubmit="return nidoosi()">
			<table class="torkRange " style="width: 700px; position: relative;">
				<!-- 名前にその人のプロフィールに飛ぶリンクを付ける
					名前（リンク：会員No）：会話情報
					というように結果が出力  -->
				<c:if test="${list.getUserNo() == myLoginNo}">
					<tr align="right">
						<td align="right"><span class="myTork">${list.getUserName()}：${list.getMessage()}<input
								type="button" value="削除"
								onClick="deleteMessage('${list.getMessageNo()}','toUserNo=${messageCheckBean.getToUserNo()}','deleteDirectMessage')" />
						</span></td>
					</tr>
				</c:if>
				<c:if test="${list.getUserNo() != myLoginNo}">
					<tr>
						<td><span class="opponentTork"> <a
								href="/chat/showProfile?toUserNo=${list.getUserNo()}"
								target=”_blank” class="Link">${list.getUserName()} </a>
								：${list.getMessage()}
						</span></td>
					</tr>
				</c:if>
			</table>
			<br>
		</form>
	</c:forEach>
	<form action="/chat/directMessage" method="POST" onSubmit="return nidoosi()">
		<p>送りたいメッセージを書くのです！（｀・ω・´）</p>
		<p>
			<font size="5" color="red">${error}</font>
		</p>

		<input type="hidden" name="deleteMessageNo"
			value="${list.getMessageNo()}"> <input type="hidden"
			name="toUserNo" value="${messageCheckBean.getToUserNo()}"> <input
			type="text" name="sendMessage" size="50"
			align="right"> <input type="submit" class="click"  value="メッセージの送信">
	</form>
	<form action="/chat/main" method="POST" onSubmit="return nidoosi()">
		<input type="submit" value="メインメニューへ戻る">
	</form>

</body>
</html>