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
	<h1>個人チャットページ</h1>
	<h2>メッセージ</h2>
	あなた：削除ボタンはまだ実装してないよ～（´・ω・`）
	<br>
	<a href="/chat/showProfile" target=”_blank”>あいて</a>：poppo!（｀・ω・´）
	<br> ■会話一覧

	<c:forEach var="list" items="${messageCheckBean.getTalkContent()}"
		varStatus="status">
		<tr align="center">
			<td><br> <!-- 名前にその人のプロフィールに飛ぶリンクを付ける
					名前（リンク：会員No）：会話情報
					というように結果が出力  -->
				<p>
					<a href="/chat/showProfile?toUserNo=${list.get(2)}" target=”_blank”>${list.get(0)}</a>
					：${list.get(1)}

				<form action="/chat/directMessage" method="POST">
					<input type="submit" value="削除">
				</form>
		</tr>
	</c:forEach>

	<form action="/chat/directMessage" method="POST">
		<p>送りたいメッセージを書くのです！（｀・ω・´）</p>
		<input type="text" name="sendMessage" value="">
	</form>
	<form action="/chat/directMessage" method="POST">
		<input type="submit" name="" value="メッセージの送信">
	</form>
	<form action="/chat/main" method="POST">
		<input type="submit" value="メインメニューへ戻る">
	</form>
</body>
</html>