<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 정보조회</title>
</head>
<body>
	<h2>책 정보조회/추가/삭제</h2>
	<hr>
	<form action="<c:url value="/ccontrol"/>"  method ="post" id="book_form">
		<input type="hidden" name="action" value="" id="book_form_action">
		<label>책 번호</label>
		<input type="text" name="id" value="${b.id}">
		<br>
		<label>책 이름</label>
		<input type="text" id="title" name="title" value="${b.title}">
		<br>
		<label>출판사</label>
		<input type="text" name="publisher" value="${b.publisher}">
		<br>
		<label>가격</label>
		<input type="text" name="price" value="${b.price}">
		<br>
		<input type="button" id="update_button" value="수정 / 저장">
		<input type="button" id="delete_button" value="삭제">
	</form>
	<script src= "https://cdn.korzh.com/metroui/v4.5.1/js/metro.min.js"></script>
	<script src = "/jwbook/madang/book.js"></script>
</body>
</html>