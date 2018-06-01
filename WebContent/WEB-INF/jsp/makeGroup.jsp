<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@
page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/chat/WebContent/css/全体.css"
	type="text/css">
<link rel="stylesheet" href="/chat/WebContent/css/font.css"
	type="text/css">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" width="80%" height="60vh"
		cellspacing="0" cellpadding="3">


		<!-- 1段目 -->
		<tr>
			<th colspan="2">
				<p>グループ作成</p>
			</th>
			<th>
				<form action="/chat/makeGroup" method="GET">
					<button type='submit' name='action' value="creat">ログアウトする</button>
				</form>
			</th>
		</tr>
		<!-- 2段目 -->
		<tr>
			<td colspan="3">

				<form action="/chat/makeGroup" method="POST">
					<p>グループの名前</p>
					<input type="text" name="groupName" value=''>
					<p>
						<font size="5" color="red">${error }</font>
					</p>
				</form>
			</td>
		</tr>
		<!-- 3段目 -->
		<tr>
			<td colspan="2" rowspan="2">
				<form action="/chat/makeGroup" method="POST">

					<table border="1">
						<tr>
							<th>全ユーザーリスト</th>
						</tr>

						<c:forEach var="obj" items="${groupBean.userName}"
							varStatus="status">
							<tr>
								<td><input type="checkbox" name="userNo"
									value="${status.index}"> <c:out value="${obj}" /></td>
							</tr>
						</c:forEach>
					</table>
				</form>
			<td>
				<form action="/chat/makeGroup" method="POST">
					<button type='submit' name='action' value="creat">選択したユーザーでグループを作成する</button>
				</form>
			</td>

		</tr>
		<tr>
			<td>
				<form action="/chat/main" method="POST">
					<input type="submit" value="メインメニューに戻る">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>