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
<script type="text/javascript" src="JavaScript/deleteUserMenber.js"
	charset="UTF-8">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="button" value="ログアウト" onClick="logout()" />
	<h1>チャット研修プログラム</h1>
	<h2>${GroupBean.getGroupName()}</h2>
	<h2>ようこそ ${session.userName}さん</h2>

	<c:forEach var="list" items="${GroupBean.getNumber()}"
		varStatus="status">
		<!-- 名前にその人のプロフィールに飛ぶリンクを付ける
					名前（リンク：会員No）：会話情報
					というように結果が出力  -->
		<form action="/chat/groupMessage" method="GET">
			<c:if test="${GroupBean.getNumber().get(status.index) == myLoginNo}">
					${GroupBean.getName().get(status.index)}：${GroupBean.getText().get(status.index)}
				<input type="button" value="削除"
					onClick="deleteMessage('${GroupBean.getMessageNo().get(status.index)}','toGroupNo=${GroupBean.getGroupNo()}','deleteGroupMessage')">
			</c:if>
			<c:if test="${GroupBean.getNumber().get(status.index) != myLoginNo}">
				<c:if test="${GroupBean.getName().get(status.index) != '送信者不明'}">
					<a
						href="/chat/showProfile?toUserNo=${GroupBean.getNumber().get(status.index)}"
						target=”_blank”> ${GroupBean.getName().get(status.index)}</a>
				</c:if>
				<c:if test="${GroupBean.getName().get(status.index) == '送信者不明'}">
					${GroupBean.getName().get(status.index)}
				</c:if>
			
					：${GroupBean.getText().get(status.index)}
			</c:if>
		</form>
	</c:forEach>
	<p>${error}</p>
	<form action="/chat/groupMessage" method="POST">
		<input type="hidden" name="deleteMessageNo"
			value="${GroupBean.getMessageNo()}"> <input type="hidden"
			name="toGroupNo" value="${GroupBean.getGroupNo()}"> <input
			type="text" name="sendMessage" value=""> <input type="submit"
			value="メッセージの送信">
	</form>
	<c:if test="${GroupBean.getRegistUserNo() != myLoginNo}">
		<form action="/chat/secessionGroupMessgeModel" method="GET">
			<input type="hidden" name="toGroupNo"
				value="${GroupBean.getGroupNo()}"> <input type="button"
				value="グループの脱退"
				onclick="deleteUserMenber('${GroupBean.getGroupNo()}')">
		</form>
	</c:if>
	<form action="/chat/main" method="POST">
		<input type="submit" value="メインメニューに戻る">
	</form>
</body>
</html>