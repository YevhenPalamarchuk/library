<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>Error!!!</title>
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="wrapper">
<center><div class="header"><%@ include file="header2.jsp" %></div></center>
<div class="content">
<br>
<br>
<br>
<br>
<h2><c:out value="${sessionScope.error}"/></h2>
<br>
<br>
<br>
<p class="block2"><img src="images/error.jpg" width="224" height="140"></p>
<br>
<br>
<p class="block3"><input type="button" value="Back" onclick="window.location.href='login.jsp';" class="buttons" /></p>

</div>    
    
<center><div class="footer"><%@ include file="footer.jsp" %></div></center>
    
 </div>
</body>

</html>

