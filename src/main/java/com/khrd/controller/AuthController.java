package com.khrd.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khrd.domain.MemberVO;
import com.khrd.service.MemberService;

@Controller
@RequestMapping("/auth/")
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	MemberService service;
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public void loginGet() {
		logger.info("-------------- login GET");
	}
	
	@RequestMapping(value="loginPost", method=RequestMethod.POST)
	public void loginPost(MemberVO vo, Model model) {
		logger.info("-------------- login POST");
		
		//id와 pw가 일치하는 회원 찾기
		MemberVO dbVO = service.selectByIdAndPass(vo);
		
		
		if(dbVO == null) {
			logger.info("-------------- loginPOST not user---------");
			return;
		}
		model.addAttribute("login", dbVO.getUserid());
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("-------------- logout GET");
		session.invalidate();
		
		return "redirect:/";
	}
	

}//AuthController
