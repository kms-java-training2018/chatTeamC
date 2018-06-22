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
<script type="text/javascript" src="JavaScript/deleteUserMenber.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="JavaScript/button.js"
	charset="UTF-8"></script>
<script type="text/javascript" src="JavaScript/Scrool.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/MessageTitle.css">
<link rel="stylesheet" href="css/Message.css">
<title>グループチャットページ</title>
</head>
<body onload="footerStart();firstscript(${scroll});">
	<div id="Lock">
		<p>ようこそ ${session.userName}さん</p>
		<br> <input type="button" value="ログアウト" onClick="logout()" />

		<h2>${GroupMessageBean.getGroupName()}</h2>
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
		items="${GroupMessageBean.getTalkContentBeanList()}"
		varStatus="status">
		<!-- 名前にその人のプロフィールに飛ぶリンクを付ける
					名前（リンク：会員No）：会話情報
					というように結果が出力  -->
		<form action="/chat/groupMessage" method="GET"
			onSubmit="return nidoosi()">
			<table class="torkRange" style="width: 700px; position: relative;">
				<c:if test="${list.getUserNo() == myLoginNo}">
					<tr align="right">
						<td align="right"><span class="myTork">${list.getUserName()}：${list.getMessage()}
								<input type="button" value="削除"
								onClick="deleteMessage('${list.getMessageNo()}','toGroupNo=${GroupMessageBean.getGroupNo()}','deleteGroupMessage')" />
						</span></td>
					</tr>

					<!-- 会話ログ表示 -->
				</c:if>
				<c:if test="${list.getUserNo() != myLoginNo}">
					<tr>
						<td><span class="opponentTork"> <c:if
									test="${list.getUserName() != '送信者不明'}">
									<a href="/chat/showProfile?toUserNo=${list.getUserNo()}"
										target=”_blank”> ${list.getUserName()}</a>

								</c:if> <c:if test="${list.getUserName() == '送信者不明'}">
							${list.getUserName()}
						</c:if> ：${list.getMessage()}
						</span></td>
					</tr>
				</c:if>
			</table>
		</form>
	</c:forEach>
	<p>
		<font size="5" color="red">${error}</font>
	</p>
	<form action="/chat/groupMessage" method="POST"
		onSubmit="return nidoosi()" name="myForm">
		<input type="hidden" name="toGroupNo"
			value="${GroupMessageBean.getGroupNo()}"> <input
			type="hidden" name="setMessage" id="setMes">
	</form>
	<form action="/chat/groupMessage" method="post"
		onSubmit="return nidoosi()" name="textForm">
		<input type="hidden" id="URL" value="/chat/groupMessage"> <input
			type="hidden" name="toGroupNo"
			value="${GroupMessageBean.getGroupNo()}"><input type="hidden" name="scroll" id="scroll"
			value=""> <input type="text"
			name="sendMessage" oninput="inputText()" id="inText"
			value="${setText}" size="50"> <input type="submit"
			value="メッセージの送信">
	</form>
	<c:if test="${GroupMessageBean.getRegistUserNo() != myLoginNo}">
		<form action="/chat/secessionGroupServlet" method="GET">
			<input type="hidden" name="toGroupNo" value="${myLoginNo}"> <input
				type="button" value="グループの脱退"
				onclick="deleteUserMenber('${GroupMessageBean.getGroupNo()}')">
		</form>
	</c:if>
	<form action="/chat/main" method="POST">
		<input type="submit" value="メインメニューに戻る">
	</form>
</body>
</html>