<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전화번호부</title>
</head>
<body>
<table border="1">
	<thead>
		<tr>
			<th>no</th>
			<th>name</th>
			<th>phone number</th>
			<th>gender</th>		
			<th>age</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${phonebook }" var="phone">
		<tr>
			<td>${phone.no }</td>
			<td>${phone.name }</td>
			<td>${phone.phone }</td>
			<td>${phone.gender }</td>
			<td>${phone.birthDate }</td>
		</tr>
	
	</c:forEach>
	
	</tbody>

</table>
</body>
</html>