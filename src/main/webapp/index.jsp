<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<sql:query var="rs" dataSource="jdbc/jwbookdb">
		SELECT id, name, address, phone FROM customers
	</sql:query>
	
	<c:forEach var="customer" items="${rs.rows}">
		${customer.id} / ${customer.name} / ${customer.address} / ${customer.phone}
		<br>
	</c:forEach>
</body>
</html>