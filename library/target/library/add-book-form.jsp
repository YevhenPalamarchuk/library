<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>Programmer's Library - Add Book</title>
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="css/add-book-style.css">

</head>
<body>

	<div class="wrapper">
		<div class="header"><%@ include file="header.jsp"%></div>
		<div class="content">
			<br>
			<h2>Add Book</h2>
			<br>
			<form name="input_form" id="input_form" action="list-book.jsp" method="GET">

				<input type="hidden" name="command" value="ADD" />

				<table>
					<tbody>
						<tr>
							<td><label>Title:</label></td>
							<td><input type="text" name="title" required value="" /></td>
						</tr>

						<tr>
							<td><label>Author:</label></td>
							<td><input type="text" name="author" required value="" /></td>
						</tr>

						<tr>
							<td><label>Publisher:</label></td>
							<td><input type="text" name="publisher" required value="" /></td>
						</tr>

						<tr>
							<td><label>Year:</label></td>
							<td><input type="number" name="year" required value=""
								placeholder="YYYY" /></td>
						</tr>

						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Save" class="save" /> <input type="button"
								value="Back to List"
								onclick="window.location.href='list-book.jsp';" class="save" /></td>

						</tr>

					</tbody>
				</table>
			</form>

			<div style="clear: both;"></div>
		</div>
		<div class="footer"><%@ include file="footer.jsp"%></div>
	</div>

	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/year_validation.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/input_validation.js"></script>
</body>

</html>