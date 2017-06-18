<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>Programmer's Library</title>
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/login.css" rel="stylesheet" type="text/css">

</head>
<body>

	<div class="wrapper">
		<center>
			<div class="header"><%@ include file="header2.jsp"%></div>
		</center>
		<div class="content">
			<div class="loger">


				<form name="input_form" action="Security" method="post">

					<table>
						<tbody>
						<tr>
							<tr>
								<td valign="bottom"><label>Login:</label></td>
								<td valign="bottom"><input type="text" name="login" required value="" /></td>
								<td rowspan="3" align="center"><img
									src="images/control.jpg" width="448" height="280"></td>
							</tr>
							<tr style="height:20px;">
								<td valign="top"><label>Password:</label></td>
								<td valign="top"><input type="password" name="pass" /></td>
							</tr>
							<tr valign="top">
								<td colspan="2" align="center">
								<br>
								<input type="submit"
									value="Login" class="buttons" /> <input type="button"
									value="New User"
									onclick="window.location.href='add-user.jsp';" class="buttons" /></td>
							</tr>


						</tbody>
					</table>
				</form>



			</div>
		</div>

		<center>
			<div class="footer"><%@ include file="footer.jsp"%></div>
		</center>

	</div>
</body>

</html>