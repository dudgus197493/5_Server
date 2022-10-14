<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>학생 조회 결과</title>
    <style type="text/css">
        body {
            display: flex;
            justify-content: center;
        }
        table {
            border : 1px solid black;
            border-collapse: collapse;
        }
        table tr {
            font-size: 20px;
        }

        table > tbody > tr:nth-of-type(2n + 1) {
            background-color: rgb(212, 212, 212);
        }
        table th{
            background-color: #00B890;
            font-size: 25px;
            padding: 15px 0;
        }
        td {
            padding: 10px;
        }
    </style>
</head>
<body>
    <table>
    	<tr>
            <th>학번</th>
            <th>이름</th>
            <th>학과</th>
            <th>주소</th>
    	</tr>


	    <c:forEach var="student" items="${stdList}">
	        <tr>
	            <td>${student.studentNo}</td>
	            <td>${student.studentName}</td>
	            <td>${student.departmentName}</td>
	            <td>${student.studentAddress}</td>
	        </tr>
	   </c:forEach>

    </table>
</body>
</html>