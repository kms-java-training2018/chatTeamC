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
	<h1>チャット研修プログラム</h1>
	<h2>マイページ</h2>
	<form action="/chat/main" method="POST">
		<p>名前</p>
		<input type="text" name = "myName" value="${name}">
		<p>プロフィール</p>
		<input type="text" name = "myProfile" value="${profile}">
		<p></p>
		<input type="submit" value="プロフィールを更新" name = "newProfile">
	</form>
	<form action="/chat/main" method="POST">
		<input type="submit" value="メインメニューに戻る">
	</form>

</body>
</html>