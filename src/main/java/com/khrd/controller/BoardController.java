package com.khrd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.PageMaker;
import com.khrd.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	BoardService service;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	public void registGet() {
		logger.info("----------------------registGet");
	}

	@RequestMapping(value="register", method=RequestMethod.POST)
	public String registPost(BoardVO vo) {
		logger.info("----------------------registPost");
		
		service.regist(vo);
		
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="listAll", method=RequestMethod.GET)
	public void listAll(Model model) {
		logger.info("----------------------listAll");
		List<BoardVO> list = service.listAll();
		
		model.addAttribute("list", list);
	}

	@RequestMapping(value="listCri", method=RequestMethod.GET)
	public void listCri(Model model, Criteria cri) {
		logger.info("----------------------listCri");
		List<BoardVO> list = service.listCriteria(cri);
		
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value="read", method=RequestMethod.GET)
	public void read(Model model, int bno) {
		logger.info("----------------------read");
		BoardVO b = service.read(bno);
		service.cntIncrease(b);
		
		model.addAttribute("boardVO", b);
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public void modifyGet(Model model, int bno) {
		logger.info("----------------------modifyGet");
		BoardVO b = service.read(bno);
		
		model.addAttribute("boardVO", b);
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modifyPost(BoardVO vo) {
		logger.info("----------------------modifyPost");
		service.modify(vo);
		
		return "redirect:/board/read?bno="+vo.getBno();
	}
	
	@RequestMapping(value="remove", method=RequestMethod.GET)
	public String remove(Model model, int bno) {
		logger.info("----------------------remove");
		service.remove(bno);
		
		return "redirect:/board/listAll";
	}
	
	/*---------------- Page ----------------*/
	
	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public void listPage(Model model, Criteria cri) {
		logger.info("----------------------listPage");
		List<BoardVO> list = service.listCriteria(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount());
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="removePage", method=RequestMethod.GET)
	public String removePage(Model model, int bno, Criteria cri) {
		logger.info("----------------------remove");
		service.remove(bno);
		
		return "redirect:/board/listPage?page="+cri.getPage();
	}
	
	@RequestMapping(value="modifyPage", method=RequestMethod.GET)
	public void modifyPageGet(Model model, int bno, Criteria cri) {
		logger.info("----------------------modifyPageGet");
		BoardVO b = service.read(bno);
		
		model.addAttribute("boardVO", b);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value="modifyPage", method=RequestMethod.POST)
	public String modifyPagePost(BoardVO vo, Criteria cri) {
		logger.info("----------------------modifyPagePost");
		service.modify(vo);
		
		return "redirect:/board/readPage?bno="+vo.getBno()+"&page="+cri.getPage();
	}
	

	@RequestMapping(value="readPage", method=RequestMethod.GET)
	public void readPage(Model model, int bno, Criteria cri) {
		logger.info("----------------------readPage");
		BoardVO b = service.read(bno);
		service.cntIncrease(b);
		
		model.addAttribute("boardVO", b);
		model.addAttribute("cri", cri);
	}
	

}//BoardController
