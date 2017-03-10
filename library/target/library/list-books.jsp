<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>Programmer's Library - Update Book</title>
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">


</head>
<body>

	<div class="wrapper">
		<div class="header"><%@ include file="header.jsp"%></div>

		<div class="content">
			<br> <br>
			<div class="scrollblock">
				<table>

					<tr>
						<th>Title</th>
						<th>Author</th>
						<th>Publisher</th>
						<th>Year</th>
						<th>Action</th>
					</tr>

					<c:forEach var="tempBook" items="${BOOK_LIST}">

						<!-- set up a link for each book -->
						<c:url var="tempLink" value="list-book.jsp">
							<c:param name="command" value="LOAD" />
							<c:param name="bookId" value="${tempBook.id}" />
						</c:url>

						<!--  set up a link to delete a book -->
						<c:url var="deleteLink" value="list-book.jsp">
							<c:param name="command" value="DELETE" />
							<c:param name="bookId" value="${tempBook.id}" />
						</c:url>

						<tr>
							<td>${tempBook.title}</td>
							<td>${tempBook.author}</td>
							<td>${tempBook.publisher}</td>
							<td>${tempBook.year}</td>
							<td><a href="${tempLink}">Update</a> | <a
								href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false">
									Delete</a></td>
						</tr>

					</c:forEach>

				</table>

			</div>
		</div>
		<div class="footer"><%@ include file="footer.jsp"%></div>
	</div>


</body>

</html>