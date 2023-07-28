<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>만다르트 만들기</title>
</head>
<body>
	<body>
	<h2>만다르트</h2>
	<hr>
	<form action="<c:url value="/mandaController"/>"  method ="post">

	
	<table border ="1">
	<c:forEach var="i" begin="0" end="2" step="1">
    			<tr>
    			<c:forEach var="j" begin="0" end="2" step="1">
    				<c:if test="${3*i + j==4}">
    					<td>${firstList[3*i + j]}</td>
    				</c:if>
    				<c:if test="${3*i + j ne 4}">
    					<td>
    						<button name="goal" value="${(firstList[3*i + j])}">
    						${firstList[3*i + j]}</button>
    					</td>
    				</c:if>
    			</c:forEach>
    			</tr>
    </c:forEach>
</table>
<hr>
<c:if test="${!empty param.goal}">
     	<h2>조회 결과</h2>
        <table border="1">
        <c:forEach var="i" begin="0" end="2" step="1">
    			<tr>
    			<c:forEach var="j" begin="0" end="2" step="1">
    				<td>${secondList[3*i + j]}</td>
    			</c:forEach>
    			</tr>
        </c:forEach>
     	</table>
    </c:if>

<!-- <script src = "/jwbook/madang/bookList.js"></script> -->
</body>
</html>