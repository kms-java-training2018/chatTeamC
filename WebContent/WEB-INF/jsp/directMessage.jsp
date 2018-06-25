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
<link rel="stylesheet" href="css/MessageTitle.css">
<link rel="stylesheet" href="css/Message.css">
<title>個人チャットページ</title>
</head>
<body onload="firstscript(${scroll});" id="background">
	<div id="Lock">
		<p>ようこそ</p>
		${session.userName}
		<p class="right">さん</p>
		<input type="button" class='temp2' value="log Out" onClick="logout()" />
		<p>
			<b> <a
				href="/chat/showProfile?toUserNo=${messageCheckBean.getToUserNo()}"
				target=”_blank” class="Link">${messageCheckBean.getToUserName()}</a></b>さん
			<br>との会話部屋
		</p>
	</div>
	<!--<h2>メッセージ</h2>-->
	<c:forEach var="list"
		items="${messageCheckBean.getTalkContentBeanList()}"
		varStatus="status">
		<form action="/chat/deleteDirectMessage" method="GET"
			onSubmit="return nidoosi()">
			<table class="torkRange ">
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
		</form>
	</c:forEach>
	<form action="/chat/directMessage" method="POST"
		onSubmit="return nidoosi()" name="myForm">
		<input type="hidden" name="toUserNo"
			value="${messageCheckBean.getToUserNo()}"> <input
			type="hidden" name="setMessage" id="setMes"> <input
			type="hidden" id="scroll" name="scroll" value="0">
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div id="TalkSet">
		<form action="/chat/directMessage" method="POST"
			onSubmit="return nidoosi()" name="textForm">
			<p>送りたいメッセージを書くのです！（｀・ω・´）</p>
			<p>
				<font size="5" color="red">${error}</font>
			</p>
			<input type="hidden" id="URL" value="/chat/directMessage"> <input
				type="hidden" name="toUserNo"
				value="${messageCheckBean.getToUserNo()}"><input type="text"
				name="sendMessage" size="50" oninput="inputText()" id="inText"
				align="right" value="${setText}"> <input type="submit"
				class="click" value="メッセージの送信">
		</form>
		<form action="/chat/main" method="POST" onSubmit="return nidoosi()">
			<input type="submit" value="メインメニューへ戻る">
		</form>
	</div>

	<!--<p id="endMsg"></p>-->

</body>
</html>