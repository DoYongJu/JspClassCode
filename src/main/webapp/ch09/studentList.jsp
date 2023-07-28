<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생정보</title>
</head>
<body>
<h2>학생정보</h2>[<a href="/jwbook/studentControl">새로고침</a>]
<hr>
<table border ="1">
	<tr>
		<th>id</th>
		<th>이름</th>
		<th>대학</th>
		<th>생일</th>
		<th>이메일</th>
	</tr>
	<c:forEach items="${students}" var="ss">
		<tr>
			<td><a href="/jwbook/studentControl?action=List&id=${ss.id}">${ss.id}</a></td>
			<td>${ss.username}</td>
			<td>${ss.univ}</td>
			<td>{ss.birth}</td>
			<td>${ss.email}</td>
		</tr>
	</c:forEach>
</table>
<hr>
<h2>학생 추가</h2>
<hr>
<form action="<c:url value="/studentControl"/>" method="get" id="goto_form">

	<label>이름</label>
	<input type="text" name="username" value="${s.username}"><br>
	<label>대학</label>
	<input type="text" name="univ" value="${s.univ}"><br>
	<label>생일</label>
	<input type="text" name="birth" value="${s.birth}"><br>
	<label>이메일</label>
	<input type="text" name="email" value="${s.email}"><br>

	<input type="hidden" name="action" value="insert"> 
	<input type="hidden" name="id" value="-1"> 
	<button type ="submit">학생 추가등록</button>
	
</form>
</body>
</html>