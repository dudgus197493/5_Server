<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과 페이지</title>
	<style>
		h1 {
			background-color: yellow;
			color: red;
			
		}
	</style>
</head>
<body>
	<h1>로그인 결과</h1>
	
	<!-- jsp에서 자바코드의 값을 출력하는 방법 -->
	<%= request.getParameter("inputId") %>
	
	<br>
	
	<%= request.getParameter("inputPw") %>
	
	<br>
	
	<%= 2 * 5 + 10 %>
	
	<!-- 자바 코드 작성 영역(출력 X) -->
	<%
		// 자바 주석도 가능 == 자바 코드 작성 영역
		String res = (String)request.getAttribute("r");
					// -> 다운 캐스팅 필요
	%>
	
	<h3 style='color:greenyellow;'><%= res %></h3>
	
	<button type='button' onclick='history.back()'>돌아가기</button>
</body>
</html>