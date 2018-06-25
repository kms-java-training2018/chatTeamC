<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/createAccount.css" type="text/css"
	media="all">
<script type="text/javascript" src="JQuery/jquery-3.3.1.min.js"></script>
<script type="text/JavaScript" src="JavaScript/login.js" charset="UTF-8"></script>
<script type="text/javascript" src="JavaScript/button.js"
	charset="UTF-8">

</script>


<title>Create Account</title>
</head>
<body>
	<div align="center">

		<h1>Create Account</h1>


		<form action="/chat/createAccountServlet" onSubmit="return nidoosi()"
			method="POST" class="login" action="foo">
			<table class='main'>

				<tr>
					<th>
						<p>Member ID(20)</p> <input id="username" class="fontSize"
						type="text" name="userId" value="${loginBean.userId}">
					</th>
					<th>
						<p>Username(30)</p> <input id="username" class="fontSize"
						type="text" name="userName" value="${loginBean.userId}">
					</th>
				</tr>
				<tr>
					<th>
						<p>Password(20)</p> <input id="username" class="fontSize"
						type="text" name="password" value="${loginBean.userId}">

					</th>
					<th>
						<p>Profile(100)</p> <input id="username" class="fontSize"
						type="text" name="profile" value="${loginBean.userId}">
					</th>
				</tr>

				<tr>
					<th>
						<p class='error'>${loginError.errorMessage}</p>
						<p class='error'>${loginError.idErrorMessage}</p>
					</th>
					<th>
						<p class='error'>${loginError.sqlErrorMessage}</p>
						<p class='error'>${loginError.profileErrorMessage}</p>
					</th>
				</tr>
				<tr>
					<th><button type="submit" value="Create" class="temp1">Create</button>
					</th>
					<th>
						<button type="button" onClick="location.href='./login';"
							 class="temp2">Back</button>
					</th>
				</tr>

			</table>
		</form>


	</div>
</body>
</html>