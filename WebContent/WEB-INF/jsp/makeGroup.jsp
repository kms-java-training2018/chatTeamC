<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC dh"-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JQuery/jquery-3.3.1.min.js"></script>

<script type="text/javascript" src="JavaScript/logout.js"
	charset="UTF-8">

</script>
<script type="text/javascript" src="JavaScript/makeGroup.js"
	charset="UTF-8">

</script>
<script type="text/javascript" src="JavaScript/button.js"
	charset="UTF-8">

</script>

<link rel="stylesheet" href="css/MakeGroup.css">
<!-- <link rel="stylesheet" href="css/base.css"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Group</title>
</head>
<body>
<body id="bgcolor"></body>
<form action="/chat/makeGroup" method="POST" onSubmit="return nidoosi()">
	<table class="main" align="center">

		<!-- 1段目 -->
		<tr>
			<th class="title" colspan="2">
				<p>Create Group Page</p>
			</th>
			<th class="name">

				<p>
					Welcome! ${session.userName } さん <input type="button"
						value="Log Out" align="right" onClick="logout()" />
			</th>
		</tr>

		<!-- 2段目 -->
		<tr>
			<td colspan="3" align="center">

				<p>Group Name</p> <input type="text" name="groupName"
				class="fontSize" value=''>
				<p>
					<font size="5" color="red">${error}</font>
				</p>

			</td>
		</tr>
		<!-- 3段目 -->
		<tr>
			<td colspan="2" rowspan="2">
				<table class="typeB">

					<tr>
						<th>Select Member</th>
					</tr>
					<tr>
						<th><input type="checkbox" id="member_all" name="member_all">Select
							all</th>
					</tr>

					<tr>

						<c:forEach var="obj" items="${groupBean.getMemberList()}"
							varStatus="status">
							<tr>

								<td><input type="checkbox" name="userNo"
									class="member" value="${obj.getMemberNo()}"> <c:out
										value="${obj.getMemberName()}" /></td>

							</tr>
						</c:forEach>
					</tr>
				</table>
			<td>

				<button type="submit" name="action" value="creat" class="temp1"
					>Create group</button>

			</td>
		</tr>
		<tr>
			<td><button type="submit" name="backMain" value="main"
					class="temp1">Return to Main
					Page</button></td>
		</tr>
	</table>
</form>
</body>
</html>