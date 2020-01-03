package com.khrd.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.khrd.util.UploadFileUtils;
   
@Controller
@RequestMapping("/upload/*")
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	@Resource(name="uploadPath") //id로 주입(DI: Dependency Injection)을 받음
	private String uploadPath;
	
	@RequestMapping(value="displayFile", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName){
		logger.info("--------- displayFile ---------");
//		logger.info("fileName: " + fileName);
		ResponseEntity<byte[]> entity = null;
		
		try {
			//확장자
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType type = null;
			
			if(formatName.equalsIgnoreCase("jpg") || formatName.equalsIgnoreCase("jpeg")) {
				type = MediaType.IMAGE_JPEG;
			} else if(formatName.equalsIgnoreCase("png")) {
				type = MediaType.IMAGE_PNG;
			} else if(formatName.equalsIgnoreCase("gif")) {
				type = MediaType.IMAGE_GIF;
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);
			
			//파일을 읽어들임
			InputStream in = new FileInputStream(uploadPath + "/" + fileName);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), //바이트 배열을 리턴
												headers,
												HttpStatus.CREATED);
			in.close(); //InputStream은 사용 완료 후 꼭 close를 해줘야 다른 곳에서 이 파일에 접근 가능함.
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/*---------------------------------------------------------*/
	/*------------------------ DRAG ---------------------------*/
	@RequestMapping(value="drag", method=RequestMethod.GET)
	public String dragGet() {
		logger.info("--------- drag GET ---------");
		return "/upload/dragForm";
	}
	
	@ResponseBody
	@RequestMapping(value="drag", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> dragPost(String writer, List<MultipartFile> files) {
		logger.info("--------- drag POST ---------");
		ResponseEntity<Map<String, Object>> entity = null;
		List<String> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			logger.info("writer: " + writer);
			
			for(MultipartFile f : files) {
				logger.info("size: " + f.getSize() + " / name: " + f.getOriginalFilename());

				//upload처리
				String thumb = UploadFileUtils.uploadFile(uploadPath, f.getOriginalFilename(), f.getBytes());
				list.add(thumb);
			}
			
			map.put("list", list);
			map.put("result", "success");
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();

			map.put("result", "fail");
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	

}//UploadController
