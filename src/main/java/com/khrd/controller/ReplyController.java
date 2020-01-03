package com.khrd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.khrd.domain.Criteria;
import com.khrd.domain.PageMaker;
import com.khrd.domain.ReplyVO;
import com.khrd.service.ReplyService;

@RequestMapping("/replies/*")
@RestController
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	@Autowired
	ReplyService service;
	
	
	@RequestMapping(value="all/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> listAll(@PathVariable("bno") int bno) {
		logger.info("----------listAll----------");
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			List<ReplyVO> list = service.listAll(bno);
			entity = new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK); //200
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST); //400 error
		}
		return entity;
	}//listAll
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public ResponseEntity<String> registAdd(ReplyVO vo){
		logger.info("----------registAdd----------");
		logger.info(vo.toString());
		ResponseEntity<String> entity = null;
		
		try {
			service.regist(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//registAdd

	// /replies
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> regist(@RequestBody ReplyVO vo){ //RequestBody -> 브라우저에서 오는 데이터 받기
		logger.info("----------regist----------");
		logger.info(vo.toString());
		ResponseEntity<String> entity = null;
		
		try {
			service.regist(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//regist
	
	@RequestMapping(value="{rno}", method=RequestMethod.PUT)
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") int rno) {
		logger.info("----------modify----------");
		logger.info(vo.toString() + ", " + rno);
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRno(rno);
			service.modify(vo);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//modify
	
	@RequestMapping(value="{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno) {
		logger.info("----------delete----------");
		logger.info("rno: "+rno);
		ResponseEntity<String> entity = null;
		
		try {
			service.remove(rno);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//delete
	
	
	/*---------------------- pagenation ----------------------*/
	@RequestMapping(value="{bno}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listCri(@PathVariable("bno") int bno, @PathVariable("page") int page) {
		logger.info("----------listCri----------");
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria(page, 10);
			List<ReplyVO> list = service.listCri(bno, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.listCriTotalCount(bno));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK); //200
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST); //400 error
		}
		return entity;
	}//listAll
	
	
	
	
}//ReplyController
