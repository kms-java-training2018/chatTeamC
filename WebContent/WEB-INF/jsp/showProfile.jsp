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
	<h2>プロフィール</h2>
	<p>${user_name }
	<br>${my_page_text}
	</p>
	<!-- 個人メッセージだけではなく、グループの方にも戻れるように　要変更 -->
	<form action="/chat/directMessage" method="POST">
		<input type="submit" value="閉じる">
	</form>
</body>
</html>