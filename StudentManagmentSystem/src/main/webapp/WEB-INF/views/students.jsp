<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Yes JSP is working

${students}


<table>
	<thead>
		<tr>
			<td>Roll no</td>
			<td>Email</td>
			<td>Firstname</td>
			<td>Lastname</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="stu" items="${students}">  
		<tr>
			<td>${stu.id}</td>
			<td>${stu.email}</td>
			<td>${stu.firstName}</td>
			<td>${stu.lastName}</td>
		</tr>
	</c:forEach>  
	
	</tbody>

</table>

</body>
</html>