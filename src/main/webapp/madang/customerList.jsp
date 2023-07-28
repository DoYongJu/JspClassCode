<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 목록</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
		<div class="col-12">
			<h2>고객 목록</h2>
			<div class="btn-group" style="float: right;">
			<form action="<c:url value="/ccontrol?action=orderList"/>"
				method="post" id="ordering_form">
				<input type="submit" class="btn btn-success" name="submit"
					id="submit" value="주문리스트 보기">
			</form>

			<form action="<c:url value="/ccontrol?action=bookList"/>"
				method="post" id="book_form">
				<input type="submit" class="btn btn-success" name="submit"
					id="submit" value="책리스트 보기">
			</form>
		</div>
		</div>
		</div>
	</div>
	<div class="container">
		<div class="row"  >
			<div class="col" >
				<table border="1"  >
				<thead class="thead-dark">
					<tr>
						<th>고객번호</th>
						<th>고객명</th>
						<th>주소</th>
						<th>전화번호</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="c" varStatus="i" items="${customers}">
						<tr>
							<td><a href="/jwbook/ccontrol?action=Info&id=${c.id}">${c.id}</a></td>
							<td>${c.name}</td>
							<td>${c.address}</td>
							<td>${c.phone}</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
				<br>
				<form action="<c:url value="/ccontrol"/>" method="get"
					id="goto_form">
					<input type="hidden" name="action" value="Info"> 
					<input type="hidden" name="id" value="-1"> 
					<input type="submit" class="btn btn-outline-success" name="submit" id="add_button"
						value="고객추가">
				</form>

			</div>
		</div>
	</div>
	<script>
		let hasOrdering = $
		{
			hasOrdering
		};
	</script>
	<script src="/jwbook/madang/customerList.js"></script>

</body>
</html>