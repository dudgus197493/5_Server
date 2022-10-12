# GET / POST
> ### 클라이언트가 서버로 요청을 보내는 방식

## GET
> ### 요청하는 주소위에 전달하려는 값을 `key=value` 형태로 붙여 전달
> ### 데이터를 **HTTP Header**에 포함아여 전송
> *HTTP Body는 보통 빈 상태로 전송*  
> *헤더의 내용 중 Body의 데이터를 설명하는 Content-type헤더필드 포함X*

<br>

## GET 방식 요청
```html
<form method="GET"> <!-- method="GET | POST" 데이터 전달 방식 지정 속성-->
    아이디 : <input type="text" name="userId"><br>
    비밀번호 : <input type="password" name="userPw"><br>
    <button>로그인</button>
</form>
```
### 요청시 queryString 방식으로 요청  
<img src="images/getpost/queryString.png" height="35px"><br>
/서비스 요청 주소/? key=value%key=value&...

- ### 장점
    - 단순함
    - 주소창에 값을 임의로 작성 가능
    - 캐싱 가능(요청 주소 저장 == 북마크, 즐겨찾기, 링크 공유)
- ### 단점
    - 보안에 취약
    - 글자수 제한 존재

<br>

## POST
> ### HTTP Protocol의 Body 부분에 숨겨서 데이터 전달
> *헤더필드 중 Body의 데이터를 설명하는 Content-Type이라는 헤더 필드가 들어가고 어떤 데이터 타입인지 명시해야함*

- ### POST 요청시 URL에 데이터가 표시되지 않음
![queryString](images/getpost/post-URL.png)
/서비스 주소외에 다른 정보가 표시되지 않음

- ### 장점
    - 길이 제한 X
    - 보안성 향상(데이터가 직접적으로 보이지 않음)
- ### 단점
    - 캐싱 불가능
    - 추가적인 문자 인코딩 처리 필요

