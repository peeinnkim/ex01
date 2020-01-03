package com.khrd.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Object loginID = modelAndView.getModel().get("login");
		
		if(loginID != null) {
			//session 영역에 Auth키 만들어서 userid 값 넣음
			request.getSession().setAttribute("Auth", loginID);
			System.out.println("로그인 완료");
			
			Object dest = request.getSession().getAttribute("dest");
			
			if(dest != null) {//이동할 목적지가 저장되어 있을 경우 그곳으로 이동
				response.sendRedirect((String)dest);
			} else { //목적지가 저장되어있지 않을 경우 home으로 이동
				response.sendRedirect(request.getContextPath()); 
			}
		}
	}  

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return super.preHandle(request, response, handler);
	}


}// LoginInterceptor
