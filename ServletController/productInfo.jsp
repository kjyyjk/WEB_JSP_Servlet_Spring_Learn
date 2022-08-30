<!-- 뷰: productList에서 상품 링크 누를시에 상품 세부 정보를 보여주는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품정보 조회</title>
</head>
<body>
    <h2>상품정보 조회</h2>
    <hr>
    <ul>
        <li>상품코드: ${p.id}</li>
        <li>상품명: ${p.name}</li>
        <li>제조사: ${p.maker}</li>
        <li>가격: ${p.price}</li>
        <li>등록일: ${p.date}</li>  <!-- p라는 이름으로 request scope에 저장된 Product클래스 타입의 객체 -->
    </ul>
</body>
</html>