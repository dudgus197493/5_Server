package edu.kh.project.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "logoutFilter", urlPatterns = {"/member/signUp", "/member/login"})
public class LogoutFilter extends HttpFilter implements Filter {
       

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("로그아웃 상태 검사 필터 초기화");
	}

	public void destroy() {
		System.out.println("로그아웃 상태 검사 필터 파괴");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		// == session에 loginMember가 null이 아닌지 검사
		
		// Session 객체는 HttpServletRequest에서만 얻어올 수 있다.
		// -> 다운캐스팅 필요
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		

		if(session.getAttribute("loginMember") != null) {							// 로그인 상태인 경우
			session.setAttribute("message", "로그인 상태로는 이용할 수 없습니다.");
			resp.sendRedirect("/");
		} else {																	// 로그아웃 상태인 경우
			chain.doFilter(request, response);
			// sendRedirect는 리턴이 아님
			// 리다이렉트 이후에도 아래 작성된 코드들이 실행됨
		}
	}
}
