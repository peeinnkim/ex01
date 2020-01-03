package com.khrd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.khrd.domain.BoardVO;
import com.khrd.domain.PageMaker;
import com.khrd.domain.SearchCriteria;
import com.khrd.service.BoardService;
import com.khrd.util.UploadFileUtils;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	
	@Autowired
	BoardService service;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Resource(name="uploadPath") //id로 주입(DI: Dependency Injection)을 받음
	private String uploadPath;
	
	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public void listPage(Model model, SearchCriteria cri) {
		logger.info("----------------------listPage");
		List<BoardVO> list = service.listSearch(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value="registerPage", method=RequestMethod.GET)
	public void registerPageGet() {
		logger.info("----------------------registGet");
	}

	@RequestMapping(value="registerPage", method=RequestMethod.POST)
	public String registerPagePost(BoardVO vo, List<MultipartFile> imageFiles) throws IOException {
		logger.info("----------------------registPost");
		
		ArrayList<String> files = new ArrayList<String>();
		
		for(MultipartFile file: imageFiles) {
			if(!file.isEmpty()) { //파일 추가를 아무것도 안해도 dummy data가 넘어와서 에러발생. 예외처리해줘야함
				logger.info(file.getOriginalFilename());
				logger.info(file.getSize()+"");
				
				String savedName = UploadFileUtils.uploadFile(uploadPath, 
								   file.getOriginalFilename(), 
								   file.getBytes());	
				
				files.add(savedName);
			}
		}
		vo.setFiles(files);
		
		service.regist(vo);
		
		return "redirect:/sboard/listPage";
	}
	
	@RequestMapping(value="removePage", method=RequestMethod.GET)
	public String removePage(Model model, int bno, SearchCriteria cri) {
		logger.info("----------------------remove");
		
		BoardVO b = service.readByNoHasAttach(bno);
		for(String filename : b.getFiles()) {
			UploadFileUtils.deleteFile(uploadPath, filename);
		}
		service.remove(bno);

		return "redirect:/sboard/listPage?page="+cri.getPage();
	}
	
	@RequestMapping(value="modifyPage", method=RequestMethod.GET)
	public void modifyPageGet(Model model, int bno, SearchCriteria cri) {
		logger.info("----------------------modifyPageGet");
		BoardVO b = service.readByNoHasAttach(bno);
		  
		model.addAttribute("boardVO", b);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value="modifyPage", method=RequestMethod.POST)
	public String modifyPagePost(Model model, BoardVO vo, SearchCriteria cri, String[] delFiles, List<MultipartFile> imageFiles) throws IOException {
		logger.info("----------------------modifyPagePost");

		//파일 저장
		ArrayList<String> files = new ArrayList<String>();
			for(MultipartFile file: imageFiles) {
				if(!file.isEmpty()) { //파일 추가를 아무것도 안해도 dummy data가 넘어와서 에러발생. 예외처리해줘야함
					logger.info(file.getOriginalFilename());
					logger.info(file.getSize()+"");
					
					String savedName = UploadFileUtils.uploadFile(uploadPath, 
																  file.getOriginalFilename(), 
																  file.getBytes());	
					files.add(savedName);
				}
			}
		
		//파일 삭제 
		if(delFiles != null) { //파일 삭제를 아무것도 안하면 null이 넘어오니까 예외처리 해줘야함
			for(String filename : delFiles) {
				UploadFileUtils.deleteFile(uploadPath, filename);
			}
		}
		
		vo.setFiles(files);
		service.modifyAttach(vo, delFiles);

		model.addAttribute("bno", vo.getBno());
		model.addAttribute("page", cri.getPage());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/sboard/readPage";
	}
	

	@RequestMapping(value="readPage", method=RequestMethod.GET)
	public void readPage(Model model, int bno, SearchCriteria cri) {
		logger.info("----------------------readPage");
		BoardVO b = service.readByNoHasAttach(bno);
		service.cntIncrease(b);
		System.out.println(b);
		
		model.addAttribute("boardVO", b);
		model.addAttribute("cri", cri);
	}
	
}//SearchBoardController
