package edu.kh.project.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.project.member.model.service.MemberService;
import edu.kh.project.member.model.vo.Member;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		MemberService service = new MemberService();
		try {
			// 파라미터 얻어오기
			String inputEmail = req.getParameter("inputEmail");
			String inputPw = req.getParameter("inputPw");
			
			// Member객체에 파라미터 저장
			Member member = new Member();
			member.setMemberEmail(inputEmail);
			member.setMemberPw(inputPw);
			
			// 로그인 Service 호출 후 결과 반환 받기
			Member loginMember = service.login(member);
			
			// forward를 하는 경우
			// - 요청을 다른 Servlet/JSP로 위임
			// -> 어떤 요청이 위임됐는지 알아야 되기 때문에
			//  ->> /member/login이라는 로그인 기능 서블릿의 주소창이 메인 홈페이지에 계속 남음
			 
			/*
			 * RequestDispatcher dispatcher =
			 * req.getRequestDispatcher("/WEB-INF/views/common/main.jsp");
			 * req.setAttribute("loginMember", loginMember); dispatcher.forward(req, resp);
			 */
			
			// *** redirect(재요청) ***
			// - Servlet이 다른 주소를 요청함
			// - 요청에 대한 응답화면을 직접 만드는 것이 아닌
			//  다른 응답화면을 구현하는 Servlet을 요청하여
			//  대신 화면을 만들게 하는 것.
			
			// forward와 redirect의 차이점
			// 개념적 차이 ex)
			// forward : 카페 -> 커피 주문 -> 캐셔 -> 바리스타
			// redirect : 카페 -> 김밥 주문 -> 캐셔 -> 옆집으로 가세요
			
			// 코드레벨 차이
			// forward : 
			// - 주소창 변화 X
			// - JSP 경로 작성
			// - req, resp가 유지됨
			
			// redirect :
			// - 주소창 변화 O
			// - 요청 주소 작성
			// - req, resp가 유지 X (새로 만들어짐)
			
			// 메인 페이지로 redirect
			// -> 메인 페이지를 요청한 것이기 때문에
			//    주소창에 주소가 메인 페이지 주소로 변함
			
			// request scope에 속성을 추가해도
			// redirect를 하는 경우 request가 다시 생성되어 유지되지 않음
//			req.setAttribute("loginMember", loginMember);
			// 해결 방법 : Session scope 이용
			
			// 1) HttpSession 객체 얻어오기
			HttpSession session = req.getSession();
			
			if(loginMember != null) { // 로그인 성공 시
				
				// 2) Session scope에 속성 추가하기
				session.setAttribute("loginMember", loginMember);
				
				// 3) 세션 만료시간 설정(초 단위로 지정)
				//    (클라이언트가 새로운 요청을 할 때 마다 초기화)
				session.setMaxInactiveInterval(60 * 60);
				
				// ---------------------------------------------------------------
				// 아이디 저장 (Cookie)
				
				/* Cookie란?
				 * - 클라이언트 측(브라우저)에서 관리하는 파일
				 * 
				 * - 쿠키파일에 등록된 주소 요청 시 마다
				 *   자동으로 요청에 첨부되어 서버로 전달됨.
				 * 
				 * - 서버로 전달된 쿠키에
				 * 	 값 추가, 수정, 삭제등을 진행한 후
				 *   다시 클라이언트에게 반환
				 * 
				 * */
				
				// 2) 쿠키 객체 생성
				// - 생성된 쿠키 객체를 resp를 이용해서 클라이언트에게 전달하면
				//   클라이언트 컴퓨터에 파일 형태로 저장됨
				Cookie cookie = new Cookie("saveId", inputEmail);
				
				// 1) 아이디 저장 체크박스가 체크 되었는지 확인
				if(req.getParameter("saveId") != null) { // 체크된 경우
					// 3) 쿠키가 유지될 수 있는 유효기간 설정(초 단위)
					cookie.setMaxAge(60 * 60 * 24 * 30); // 30일
				} else { 								 // 체크되지 않은 경우
					
					// 4) 쿠키의 유효기간을 0초로 설정
					// == 클라이언트에 저장된 saveId 쿠키를 삭제하는 코드
					
					// (같은 key값의 쿠키가 저장되면 덮어쓰기가 일어남)
					// 기존에 있던 쿠키에 덮어쓴 후 바로 삭제 == 기존의 쿠키 삭제
					cookie.setMaxAge(0);
					
				}
				
				// 5) 생성된 쿠키가 적용되어질 요청 주소를 지정
				cookie.setPath("/");  // 메인 페이지 주소(http://localhost/)
									  // == 메인 페이지의 하위 주소 모두 적용
				
				// 6) 설정 완료된 쿠키 객체를 클라이언트에게 전달
				resp.addCookie(cookie);
				
				// ---------------------------------------------------------------
			} else {				  // 로그인 실패 시
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			
			
			resp.sendRedirect("/");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}