<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

					<input type="hidden" name="command" value="ADD" />

					<table>
						<tbody>
						<tr>
							<tr>
								<td valign="bottom"><label>Login:</label></td>
								<td valign="bottom"><input type="text" name="login"
									required value="" /></td>
								<td rowspan="3" align="center"><img
									src="images/addu.jpg" width="448" height="280"></td>
							</tr>
							<tr style="height:20px;">
								<td valign="top"><label>Password:</label></td>
								<td valign="top"><input type="text" name="pass" /></td>
							</tr>
							<tr valign="top">
								<td colspan="2" align="center">
								<br>
								<input type="submit"
									value="Save" class="buttons" /></td>
							</tr>

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