# EL (Expression Lagnuage)
> ### JSP의 표현식을 조금 더 효율적이고 간단히 작성할 수 있도록 고안된 언어
```Java
// 화면에 출력하고자 하는 자바 코드를 ${key} 형식으로 작성 해당 위치에 value 출력

<h1> ${key} </h1>
```

<br>

## EL 특징
1. ### get이라는 단어를 사용하지 않음  
    > EL은 출력용 언어(setting불가) => get을 생략해도 묵시적으로 get
    ```Java
    <% 
        Member member = getAttribute("member");
    %>
    <%= member.getMemberName() %>

    ${member.memberName} // 위 코드와 동일
    // getter를 작성하지 않아도 묵시적으로 getter 사용
    ```
2. ### EL은 null을 빈칸으로 처리
    ```Java
    ${null인 변수} -> 빈칸 출력
    ${NullPointerException 발생 코드} -> 빈칸 출력(예외 발생 X)
    ```
3. ### EL은 null을 빈칸으로 처리
    - `null`로 인한 예외를 띄우지 않음

<br>

## 값 출력
- ### Parameter 
    ```Java
    ${param.(Parameter 키값)}
    ```

- ### Attribute
    ```Java
    ${(Attribute 키값)}
    ```