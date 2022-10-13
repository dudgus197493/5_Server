# JSP
> ### JSP(Java Server Page) : JAVA 코드가 들어가 있는 HTML 코드
> ### Java의 웹 서버 프로그램 스펙(서블릿)으로 변환되어 서비스

## Servlet과 JSP의 차이점
- ### Servlet
    ```Java
    // 서블릿 코드중 프레젠테이션 로직

    // 클라이언트 응답용 스트링
    PrintWriter out = resp.getWriter();

    out.println("<!DOCTYPE html>"); out.println("<html>");
    
    out.println("<head>"); out.println("<title>로그인 결과 페이지</title>");
    out.println("</head>");
    
    out.println("<body>"); out.println("<h1>로그인 결과</h1>");
    out.printf("<h3 style='color:greenyellow;'> %s </h3>", result);
    out.println("<button type='button' onclick='history.back()'>돌아가기</button>");
    out.println("</body>");
    out.println("</html>");
    ```
  - 자바 소스코드 속에 HTML 코드가 들어가는 형태
  - **HTML 문서를 작성하는데 복잡하고 번거로움**


- ### JSP
    ```Java
	<%= request.getParameter("inputId") %><br>
	<%= request.getParameter("inputPw") %><br>
	<%
		String res = (String)request.getAttribute("r");
	%>
	<h3 style='color:greenyellow;'><%= res %></h3>
	<button type='button' onclick='history.back()'>돌아가기</button>
    ```
  - Servlet과 반대로 HTML소스코드 속에 자바 소스코드(`<% %>`, `<%= %>`가 들어가 있는 형태)
  - 복잡한 Servlet을 간단히 사용 가능
  - **컴파일을 통해 클래스 파일로 변환되어 웹 서버(WAS)에서 실행**

<br>

## JSP 장점
- ### 서블릿보다 쉽고 작성하기 빠름
- ### 디자인 부분(html)과 로직부분(java)으로 이루어짐
- ### 프로그래머가 작성한 서블릿보다 최적화된 서블릿 생성
- ### 웹 애플리케이션 상에 변수의 범위(scope)설정이 쉬움

<br>

## JSP 구성
1. ### 지시어

    > 현재의 JSP페이지를 컨테이너에서 처리하는데 필요한 각종 속성을 기술하는 부분  
    > *보통 소스의 맨 앞 위치*
    ```Java
    <%@ page 속성1="속성값1" 속성2="속성값2" ... %>
    ```
    - 지시어 종류
      - contentType
  
        > MIME 형식 지정 및 캐릭터셋 설정  
        ```Java
        // ex)
        <%@ page contentType="text/html; charset=utf-8" %>    
        ```
      - import

        > 자바 클래스 사용시 JSP내에서 사용할 외부 자바 패키지나 클래스 import
        ```Java
        // ex)
        <%page import="java.sql.*, java.util.*" %>   
        ```
      - errorPage
        > 오류 페이지 관리, 현재 페이지에서 오류 발생 시 호출될 페이지 지정  
        > 에러 발생 시 포워딩(페이지 주소 변화 X, 화면 이동)
        ```Java
        <%@ page errorPage="error.jsp" %>
        ```
      - isErrorPage
        > 오류 페이지 관리, errorPage속성에 설정된 오류 처리 파일  
        > 다른 용도로 사용 X 오직 오류만을 처리하는 페이지로 지정(true)
        > *true로 두면 exception객체 사용 가능*
        ```Java
        <%@ page isErrorPage="true" %>
        ```

2. ### 스크립팅 원소
    > JSP 페이지에서 자바코드를 직접 기술할 수 있게 하는 기능

    - 선언문(declaration)

        ```java
        <%! 자바코드 %>
        ```
    - 스크립틀릿(scriptlet)
        ```java
        <% 자바코드 %>
        <% 
          String memberId = request.getParameter("memberId); // 변수 선언 가능 
        %>
        ``` 
    - 출력식, 표현식(expression)
        ```java
        <%= 자바코드 %>
        <%= memberId %> // 화면에 memberId 출력
        ```
    