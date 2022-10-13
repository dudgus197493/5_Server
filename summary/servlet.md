# 서블릿 (Servlet)
> ### 웹 서비스를 위한 자바 클래스
> ### **요청(Request)**을 처리, 결과를 **응답(Response)**
> ### Servlet클래스의 구현 규칙을 지킨 자바 프로그래밍 기술

<br>

## 서블릿 특징
- 클라이언트의 요청에 대해 **동적으로 작동**하는 웹 애플리케이션 컴포넌트
- 클라이언트 요청에 대한 서버 응답 시 **미리 만들어 놓은 화면(정적)**이 아닌 요층을 받을 때 마다 **알맞은 화면(동적)**을 만들어 응답
- HTML을 사용하여 요청에 응답
- java thread를 이용하여 동작(요청마다 별도 thread 생성)
- MVC Model2패턴에서 Controller로 이용
- http프로토콜 서비스를 지원하는 `javax.servlet.http.HttpServlet` 클래스를 상속

<br>

## 서블릿 단점 
- servlet에 작성한 html 코드 변경 시 재컴파일 해야함

<br>

## 서블릿 상속 관계
- 서블릿 코드를 작성할 클래스는 **반드시** `javax.servlet.http.HttpServlet` 클래스를 상속 받아 메서드를 구현해야 함
- 서블릿 상속 관계도
  - javax.servlet.Servlet 인터페이스
    - javax.servlet.GenericServlet 추상클래스
      - javax.servlet.http.HttpServlet 클래스

<br>


## Servlet Container
> ### 배포를 위한 포트 연결, 웹 서버 통신을 위한 소켓, 입/출력 스트림 생성
> *대표적으로 tomcat*
- ### Servlet
  - 어떤 역할을 수행하는 정의서
- ### Servlet Container
  - 정의된 Servlet을 보고 수행

- ### Servlet Container 역할
    1. 웹 서버와의 통신 지원
        - 서블릿과 웹 서버가 손 쉽게 통신할 수 있게 함
        - 소켓을 만들고 listen, accept 등 복잡한 과정을 API로 제공하여 생략

    2. 서블릿 생명주기(Life Cycle) 관리
        - 서블릿 클래스를 로딩 및 인스턴스화, 초기화 메서드 호출
        - 요청이 들어오면 적절한 서블릿 메서드 호출
        - 서블릿의 생명이 다하면 적절하게 가비지 컬렉션 진행

    3. 멀티쓰레드 지원 및 관리
        - 서블릿 컨테이너는 요청이 올 때마자 새로운 자바 스레드 생성
        - http 서비스 메서드 실행 후 스레드 자동 소멸(직접 관리 필요 x)

    4. 선언적인 보안 관리
     - 서블릿 컨테이너 사용 시 보안에 관련된 내용을 서블릿 or 자바 클래스에 구현하지 않아도 됨
     - xml배포 서술자(DD==web.xml)에 기록하므로 보안에 대해 수정이 필요해도 재컴파일 X

<br>

## Servlet 동작 방식

## 1. HTTP REQUEST
> 브라우저에서 HTTP 방식으로 요청
```html
<form action="/ServletProject1/example1.do">
    <!-- 제출 시 ServletProject1 프로젝트에 있는 
        /example.do라는 서비스를 요청
        단, 요청 시 내부에 작성된 input 태그의 값도 같이 전달
    -->
    이름 : <input type="text" name="inputName"><br>
    나이 : <input type="number" name="inputAge"><br>
    <button type="submit">서버로 제출</button>
</form>
```
`/ServletProject1/example1.do`라는 경로로 form 태그를 사용하여 서버(Servlet Container)에 요청

## 2. 객체 생성
- ### HttpServletRequest
    > 클라이언트의 정보 + 요청 관련 정보(데이터)가 담긴 객체

- ### HttpServletResponse
    > 서버가 클라이언트에게 응답하는 방법을 제공하는 객체

## 3. 서블릿 분석
> ### 배포서술자(DD)인 web.xml은 사용자가 요청한 URL을 분석
> ### 요청 URL과 매핑된 서블릿에 요청 내용 전달
1 번에서 요청한 `/example1.do` 서비스와 매핑된 서블릿을 찾아 내용 전달
```xml
<!-- 특정 클래스를 Servlet으로 등록하고 이름을 지정하는 태그 -->
<servlet>
    <!-- 해당 Servlet을 부르기 위한 이름 지정 -->
    <servlet-name>ex1</servlet-name>
    
    <!-- Servlet으로 등록하고자 하는 클래스의 패키지명 + 클래스명 -->
    <servlet-class>edu.kh.servlet.controller.ServletEx1</servlet-class>
</servlet>

<!-- 요청 주소를 처리할 Servlet을 지정 -->
<servlet-mapping>
    <!-- 요청을 처리할 Servlet의 이름 -->
    <servlet-name>ex1</servlet-name>
    
    <!-- 요청 주소 작성(앞에 프로젝트명(context명) 까지는 제외) -->
    <!-- [/ServletProject1] 제외 -->
    <url-pattern>/example1.do</url-pattern>
</servlet-mapping>
```
> 요청을 전달할 url-pattern( **/example1.do** )이 일치하는 서블릿 이름( **ex1** )을 가진 클래스 ( **edu.kh.servlet.controller.ServletEx1** ) 를 찾음

## 4. service() 메서드 호출
> ### 찾은 서블릿ㅇ서 `init()` 메서드 호출후 `service()`메서드 호출하여 GET, POST 여부에 따라 해당 메서드`doXXX()` 호출

## 5. 동적 페이지 생성 후 HttpServletResponse객체에 응답
```Java
public class ServletEx1 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		// 요청 시 함께 전달된 input 태그의 값(== Parameter)을 얻어오는 방법
		// req.getParameter("input 태그의 name 속성 값");
		// -> String 형태로 입력된 값 반환
		// ** 얻어온 파라미터는 무조건 String **
		
		String inputName = req.getParameter("inputName");
		String inputAge = req.getParameter("inputAge");
		
		System.out.printf("이름 : %s / 나이 : %s", inputName, inputAge);
		
		// resp.getWriter() : 서버가 클라이언트에게 응답할 수 있는 스트림을 얻어옴
		// -> PrintWriter : 출력 전용 스트림
		
		// 문서 형식과 문자 인코딩 지정
		// -> html 문서이고 문자는 UTF-8 형식으로 인코딩 되어있다.
		// --> 브라우저가 해당 내용에 맞게 해석
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print(inputName + "님의 나이는 " + inputAge + "세 입니다.");
	}
}
```
<h2><details>
<summary>응답화면</summary>

![Servlet 응답](images/getpost/response.png)
</details></h2>

## 6. 응답 종료 시 HttpServletRequest, HttpServletResponse 객체 소멸


