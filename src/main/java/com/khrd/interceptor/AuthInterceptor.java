package com.khrd.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String auth = (String)session.getAttribute("Auth");
		
		if(auth == null) { //아직 로그인 전
			//강제 이동 전에 원래 가려고 했던 주소와 쿼리 저장
			saveDest(request);
			//login 화면으로 강제 이동
			response.sendRedirect(request.getContextPath() + "/auth/login");
			
			return false; //controller진입이 막힘
		}
		
		return super.preHandle(request, response, handler); //글 등록으로 이동
	}
	
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI(); //이동 주소
		String query = request.getQueryString(); //매개변수와 값
		
		if(query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		//post는 이 방법 사용 불가
		if(request.getMethod().equalsIgnoreCase("get")) {
			request.getSession().setAttribute("dest", uri+query); //목적주소 임시저장
		}
		
	}
	
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

}//AuthInterceptor
