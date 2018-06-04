<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@
page import="java.util.ArrayList"%>


<!DOCTYPE html PUBLIC dh"-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/logout.js"
	charset="UTF-8">

</script>

<link rel="stylesheet" href="css/base.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body id="bgcolor"></body>
	<form action="/chat/makeGroup" method="POST">
		<table border="1" align="center" width="80%" height="60vh"
			cellspacing="0" cellpadding="3">


			<!-- 1段目 -->
			<tr>
				<th colspan="2">
					<p>グループ作成</p>
				</th>
				<th>
					<p>ようこそ ${session.userName } さん</p> <input type="button" value="ログアウト"
					onClick="logout()" />

				</th>
			</tr>
			<!-- 2段目 -->
			<tr>
				<td colspan="3">
					<p>グループの名前</p> <input type="text" name="groupName" class="hoge"
					value=''>
					<p>
						<font size="5" color="red">${error }</font>
					</p>

				</td>
			</tr>
			<!-- 3段目 -->
			<tr>
				<td colspan="2" rowspan="2">

					<table border="1" align="center" bgcolor="yellow" width="80%">
						<tr>
							<th>全ユーザーリスト</th>
						</tr>
						<tr>

							<c:forEach var="obj" items="${groupBean.userName}"
								varStatus="status">
								<tr class="typeA" align="center">

									<td><input type="checkbox" name="userNo"
										value="${status.index}"> <c:out value="${obj}" /></td>

								</tr>
							</c:forEach>

						</tr>
					</table>
				<td>

					<button type='submit' name='action' value="creat">選択したユーザーでグループを作成する</button>

				</td>

			</tr>
			<tr>
			<td>
			<a href="/chat/main">メインメニューに戻る</a>

				</td>
			</tr>
		</table>
	</form>
</body>
</html>