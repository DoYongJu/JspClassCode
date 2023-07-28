<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객정보조회</title>
</head>
<body>
	<h2>고객정보조회/추가</h2>
	<hr>
	<form action="<c:url value="/ccontrol"/>"  method ="post" id="customer_form">
		<input type="hidden" name="action" value="" id="customer_form_action">
		<label>고객 번호</label>
		<input type="text" name="id" value="${c.id}">
		<br>
		<label>고객 이름</label>
		<input type="text" id="name" name="name" value="${c.name}">
		<br>
		<label>주소</label>
		<input type="text" name="address" value="${c.address}">
		<br>
		<label>전화번호</label>
		<input type="text" name="phone" value="${c.phone}">
		<br>
		<input type="button" id="update_button" value="수정 / 저장">
		<input type="button" id="delete_button" value="삭제">
	</form>
	

	<script src= "https://cdn.korzh.com/metroui/v4.5.1/js/metro.min.js"></script>
	<script src = "/jwbook/madang/customer.js"></script>
</body>
</html>