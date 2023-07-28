<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 목록</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
  <form action="<c:url value="/ccontrol?action=addOrdering"/>" method="post" id="ordering_form">
	<h2>주문 목록</h2>
	<hr>
	<div class ="container">
		<label>고객</label>
		<br>
		<c:forEach var ="c" varStatus="i" items="${customers}">
		  <input type ="radio" name="customerId" value="${c.id}">${c.name}
		</c:forEach>
	</div>
	<hr>
	<div class ="container">
		<label>도서</label>
		<br>
		<c:forEach var ="b" varStatus="i" items="${books}">
		  <input type ="radio" name="bookId" value="${b.id}">${b.title} (${b.price})
		</c:forEach>
	</div>
	<hr>
	<div class ="container">
		<label>주문 하기</label>
		<br>
		
		<input type="number" name="sellingPrice" id="selling_price" >
		<input type="submit" class="btn btn-outline-success" name="submit" id="submit" value="주문하기">
		
	</div>
	</form>
	<hr>
	<div class ="container">
		<label>주문</label>
		<br>
		<table border="1">
		<tr>
			<td>고객번호</td>
			<td>고객명</td>
			<td>도서번호</td>
			<td>도서명</td>
			<td>판매가격</td>
			<td>주문일자</td>
		</tr>
		<c:forEach var ="o" varStatus="i" items="${ordering}">
		  <tr>
		  <td>${o.customerId}</td>
		  <td>${o.name}</td>
		  <td>${o.bookId}</td>
		  <td>${o.title}</td>
		  <td>${o.sellingPrice}</td>
		  <td>${o.orderingDate}</td>
		  </tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>