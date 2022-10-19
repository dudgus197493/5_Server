# JSTL(Jsp Standard Tag Library)
> ### JSP에서 자주 사용되거나 공통적으로 사용되는 Java 코드를 표기법을 태그화 하여 표준으로 제공
> *if, for, 배열, 컬렉션 길이, 문자열 치환, 숫자 데이터 형식 변경, 데이터 형식 변경, 데이터 파싱, scope 변수 선언*
```Java
// 사용하고자 하는 jstl 라이브러리 명시
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
```

## 변수 선언 `<c:set>`
> ### 변수를 선언하고 값을 초기화 하는 태그
> *원하는 scope의 내장 객체에 값 세팅*

- ### 속성
    - `var` : 변수명 (setAttribute의 key값)
    - `value` : 대입될 값
    - `scope` : 내장 객체 범위 (기본값 : page)

- ### 예시
    ```Java
    <%= pageContext.setAttribute("num1", 10); %> // scriptlet 작성법

    <c:set var="num2" value="20"> // jsp가 서블릿으로 변환될 때 위 스크립틀릿 작성법으로 변환
    <c:set var="num2" value="30" scope="session"> // sessionScope로 변수 선언

    <c:set var="num3" value="${50}" scope="session"> // EL을 사용하여 입력 가능
    ```

<br>

## 변수 삭제 `<c:remove>`
> ### `<c:set>` / `setAttribute()`로 추가된 값 제거
> ### `scope`를 지정하지 않으면 모든 scope의 var지정된 이름을 가진 변수 삭제
- ### 속성
  - `var` : 삭제할 변수명(key)
  - `scope` : 삭제할 내장 객체 범위 (기본값 : 모든 범위)

- ### 예시
    ```Java
    <c:remove var="test"/> // 모든 scope에 test이름을 가진 변수(값) 삭제
    <c:remove var="test" scope="request"/> // request scope에 test 이름을 가진 값 삭제    
    ```

<br>

## 조건문 - if `<c:if>`
> ### 단독 if 문 (else 없음)
- ### 속성
    - `test` : true/false를 검사할 조건식


- ### 주의 사항
    - `test` 속성 값 작성은 무조건 EL 구문이여야만 한다.
    - `test` 속성 값 "" 안에는 공백이 존재해서는 안된다.

- ### 예시 
    ```Java
    <c:if test="${sessionScope.test == 'session'}">
        <h4>sessionScope.test == 'session'</h4>
    </c:if>
    ```

- ### 단독 if문 e단점
    - else 상황에 대한 if문을 별도 작성 필요
    - 효율성 감소 (모든 if문 조건 검사 실행)

<br>

## 조건문 - if ~ else if ~ else `<c:choose>`, `<c:when>`, `<c:otherwise>`
- ### 구성 요소
    - `<c:choose>` : when, otherwise를 감싸는 태그
    - `<c:when>` : if ~ else if 를 나타내는 태그

        - `test` 속성안에 조건식 작성
    - `<c:otherwise>` : else를 나타내는 태그

        - 아무런 속성도 가지지 않음

- ### 예시
    ```Java
    <c:set var="temp2" value="100"/>
	<c:choose>
		<c:when test="${temp2 > 100 }">
			100보다 크다
		</c:when>
		<c:when test="${temp2 < 100 }">
			100보다 작다
		</c:when>
		<c:otherwise>
			100과 같다
		</c:otherwise>
	</c:choose>
    ```

<br>

## 반복문 `<c:forEach>`
- ### 속성
  - `var` : 현재 반복 횟수에 해당하는 변수(int i)
  - `begin` : 반복 시 `var`의 시작 값
  - `end` : 반복이 종료될 `var` 값
  - `step` : 반복 시 마다 `var`의 증가 값(기본값 1)
  - `items` : 반복 접근한 객체(배열, 컬렉션 객체)
  - `varStatus` : 현재 반복 상태와 관련된 정보를 제공하는 변수 선언  
    - `varStatus="변수명"` : `<c:forEach>` 구문 내에서 변수명을 통해 원하는 값을 얻을 수 있음
    - **`varStatus`에서 제공되는 값**
      - `current` : 현재 반복 횟수(현재 `var` 값) or 현재 반복 접근 중인 객체(컬렉션 / 배열 요소)
      - `index` : 현재 몇 번 째 인덱스 인지 반환
      - `count` : 현재 몇 번째 반복 인지 횟수 반환(1부터 시작)
      - `first` : 첫 번째 반복이면 `true`, 아니면 `false`
      - `last` : 마지막 반복이면 `true`, 아니면 `false`

- ### 일반 for문 형식으로 사용
    ```Java
    <table border="1">
		<c:forEach var="n" begin="1" end="10" varStatus="vs">
			<c:choose>			
				/* 첫 번째 반복일 경우 */
				<c:when test="${vs.first }">
					<tr>
						<th class="first"> ${n } </th>
						<td class="first"> ${n }번 게시글 입니다. </td>
					</tr>
				</c:when>
				
				/* 마지막 반복일 경우 */
				<c:when test="${vs.last }">
					<tr>
						<th class="last"> ${n } </th>
						<td class="last"> ${n }번 게시글 입니다. </td>
					</tr>
				</c:when>

				<c:otherwise>
					<tr>
						<th> ${n } </th>
						<td> ${n }번 게시글 입니다. </td>
					</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>
    ```      

- ### 향상된 for문 형식으로 사용
    ```Java
    <c:choose>
   		/* 아무것도 체크하지 않은 경우 */
   		<c:when test="${empty paramValues.lang }"> /* paramValues.lang : lang이라는 이름의 파라미터를 모두 얻어와 배열로 반환*/
   			<h1 style="color:orangered;">체크된 값이 없습니다.</h1>
   		</c:when>
   		<c:otherwise>
            <ul>
                <c:forEach var="chk" items="${paramValues.lang}" varStatus="vs">
                /* items에 파라미터배열을 처음부터 끝까지 반복하며 chk라는 변수로 참조 */
                    <li>
                        index : ${vs.index}<br>
                        count : ${vs.count}<br>
                        current : ${vs.current}<br>
                        first : ${vs.first}<br>
                        last : ${vs.last}<br>

                        체크된 값 : ${chk}
                    </li>
                </c:forEach>
            </ul>
   		</c:otherwise>
   	</c:choose>
    ```
- ### 객체배열 / 컬렉션 반복 접근하기
    ```Java	
   	<table border="1">
   		<c:forEach var="person" items="${requestScope.pList}"> /* items에 pList라는 Attribute를 얻어와 person이라는 변수로 참조 */
   			<tr>
                /* var가 참조하는 값이 객체일 경우 속성 출력 가능 */
                <th>${person.name}</th>
                <td>${person.age}</td>
                <td>${person.address}</td>
            </tr>
   		</c:forEach>
   	</table>
    ```