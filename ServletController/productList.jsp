<!-- 뷰: 상품목록을 보여주는 화면jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- JSTL사용 위한. 접두어는 "c"이다 -->
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
    <h2>상품 목록</h2>
    <hr>
    <table border="1"> <!-- border값을 둠으로써 테두리 활성화. 값을 0으로 하면 없어지나 확인해볼 것. -->
        <tr> <th>번호</th> <th>상품명</th> <th>가격</th> </tr>  <!-- 목록 -->
        <c:forEach var="p" varStatus="i" items="${products}"> <!-- 상품명이 products로 전달된다고 가정. -->
            <tr>
                <td>${i.count}</td> <!-- 상품 번호 . 반복 순서로 1 2 3 ...-->
                <td><a href = "/jwbook/pcontrol?action=info&id=${p.id}">${p.name}</a></td> <!--이름 링크 누르면 url매핑으로 컨트롤러 호출.-->
                <td>${p.price}</td> <!-- 상품 가격 -->
            </tr> <!-- 한번 반복이 끝나면 한 행에 해당 상품의 번호, 이름 , 가격이 기재됨. products 리스트 싹 반복한다. -->
        </c:forEach>    
    </table>
</body>
</html>