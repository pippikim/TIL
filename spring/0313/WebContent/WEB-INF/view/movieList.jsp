<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Management System</title>
</head>
<body>
<h1>MMS</h1>
<ul>
<c:forEach items="${movies }" var="movie">
<li><a href="/movie.gm?no=${movie.no }">${movie.no }) ${movie.name } </a>
</li>

</c:forEach>

</ul>
<a href="/movieInsert.gm">movie input</a>
</body>
</html>