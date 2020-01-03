package com.khrd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.khrd.domain.MemberVO;
import com.khrd.service.MemberService;

@RequestMapping("/member/*")
@RestController
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	MemberService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public List<MemberVO> memberList() {
		logger.info("----------memberList----------");
		List<MemberVO> list = service.selectList();
		
		return list;
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public void registMember(@RequestBody MemberVO vo) {
		logger.info("----------registMember----------");
		service.insert(vo);
	}
	
	@RequestMapping(value="{userid}", method=RequestMethod.DELETE)
	public void removeMember(@PathVariable("userid") String userid) {
		logger.info("----------removeMember----------");
		service.delete(userid);
	}

	@RequestMapping(value="{userid}", method=RequestMethod.PUT)
	public void modifyMember(@PathVariable("userid") String userid, @RequestBody MemberVO vo) {
		logger.info("----------modifyMember----------");
		service.update(vo);
	}
	
	        
}//MemberController
