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

<link rel="stylesheet" href="css/MakeGroup.css">
<!-- <link rel="stylesheet" href="css/base.css"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body id="bgcolor"></body>
<form action="/chat/makeGroup" method="POST">
	<table border="1" align="center" width="80%" height="60vh"
		cellspacing="0" cellpadding="3" class="typeA">

		<!-- 1段目 -->
		<tr>
			<th class="title" colspan="2" >
				<p>Creat Group Page</p>
			</th>
			<th class="name">

				<p>
					Welcome! ${session.userName } さん <input type="button" value="Log Out"
						align="right" onClick="logout()" />
			</th>
		</tr>

		<!-- 2段目 -->
		<tr>
			<td colspan="3" align="center">

				<p>Group Name</p> <input type="text" name="groupName" class="fontSize"
				value=''>
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
						<th><input type="checkbox" id="member_all" name="member_all">Select all
						</th>
					</tr>

					<tr>

						<c:forEach var="obj" items="${groupBean.getMemberList()}"
							varStatus="status">
							<tr>

								<td class="typeB"><input type="checkbox"
									name="userNo" class="member" value="${obj.getMemberNo()}">
									<c:out value="${obj.getMemberName()}" /></td>

							</tr>
						</c:forEach>





					</tr>
				</table>
			<td align="center">


				<button type='submit' name="action" value="creat"
					style="width: 200px; height: 40px;">Create group</button>

			</td>
		</tr>
		<tr>
			<td align="center"><button type='submit' name="backMain" value="main"
					style="width: 200px; height: 40px;">Return to Main Page</button></td>
		</tr>
	</table>
</form>
</body>
</html>