<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>Programmer's Library - Update Book</title>
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="css/add-book-style.css">


<script type="text/javascript" src="<%=request.getContextPath()%>/js/year_validation.js"></script>

</head>
<body>

	<div class="wrapper">
		<div class="header"><%@ include file="header.jsp"%></div>
		<div class="content">
			<br>
			<h2>Update Book</h2>
			<br>
			<form name="input_form" action="list-book.jsp" method="GET"
				onsubmit="return validate_form();">

				<input type="hidden" name="command" value="UPDATE" /> <input
					type="hidden" name="bookId" value="${THE_BOOK.id}" />

				<table>
					<tbody>
						<tr>
							<td><label>Title:</label></td>
							<td><input type="text" name="title"
								value="${THE_BOOK.title}" required value="" /></td>
						</tr>

						<tr>
							<td><label>Author:</label></td>
							<td><input type="text" name="author"
								value="${THE_BOOK.author}" required value="" /></td>
						</tr>

						<tr>
							<td><label>Publisher:</label></td>
							<td><input type="text" name="publisher"
								value="${THE_BOOK.publisher}" required value="" /></td>
						</tr>

						<tr>
							<td><label>Year</label></td>
							<td><input type="number" name="year" value="${THE_BOOK.year}"
								required value="" placeholder="YYYY" /></td>
						</tr>

						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Save" class="save" /> <input type="button"
								value="Back to List"
								onclick="window.location.href='list-book.jsp';"
								class="save" /></td>
						</tr>

					</tbody>
				</table>
			</form>

			<div style="clear: both;"></div>



		</div>
		<div class="footer"><%@ include file="footer.jsp"%></div>
	</div>

</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/input_validation.js"></script>
</html>


















