<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 목록</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
	<h2>도서 목록</h2>
	<form action="<c:url value="/ccontrol?action=List"/>" method="post" id="customer_form">
			<input type="submit" class="btn btn-success" name="submit" id="submit" value="고객리스트 보기" style="float:right;">
	</form>
	<br>
	<hr>
	<table border="1">
			<tr>
			  <th>책 번호</th>
              <th>책 이름</th>
              <th>출판사</th>
              <th>가격</th>
			</tr>
			<c:forEach var ="b" varStatus="i" items="${books}">
			<tr>
				<td><a href="/jwbook/ccontrol?action=bookInfo&id=${b.id}">${b.id}</a></td>
				<td>${b.title}</td>
				<td>${b.publisher}</td>
				<td>${b.price}</td>
			</tr>
			</c:forEach>
		
	</table><br>
	<form action="<c:url value="/ccontrol"/>" method="get" id="goto_form">
			<input type="hidden" name="action" value="bookInfo">
			<input type="hidden" name="id" value="-1">
			<input type="submit" class="btn btn-outline-success" name="submit" id="add_book_button" value="도서추가">
	</form>
	<script>
		let hasOrdering = ${hasOrdering};
	</script>
	<script src = "/jwbook/madang/bookList.js"></script>
</body>
</html>