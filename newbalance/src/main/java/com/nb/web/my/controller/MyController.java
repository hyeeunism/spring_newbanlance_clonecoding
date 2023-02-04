package com.nb.web.my.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.nb.web.my.dao.MyDao;
import com.nb.web.my.dto.MyRestockDTO;
import com.nb.web.my.dto.QuestionDTO;

@Controller
@RequestMapping("/my/*")
public class MyController {
	
	@Autowired
	private MyDao myDao = null;
	
	public MyController() {
		
	}
	
	// 1:1문의 목록
	@RequestMapping(value={"searchQuestionList.action"}, method = RequestMethod.GET)
	public String getMyQuestion(
			HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam(name = "srqtSeq", defaultValue = "전체") String srqtSeq
			, Model model) throws Exception {
		
		System.out.println(">  getMyQuestion");

	   List <QuestionDTO> questiondto = this.myDao.getQuestion("M1", srqtSeq);
	   
	   model.addAttribute("srqtSeq" , srqtSeq);
	   model.addAttribute("questiondto", questiondto);

	      return "/my/searchQuestionList.jsp";
	   }
	
	// 1:1문의 등록
	@RequestMapping(value = {"searchQuestionList.action"}, method = RequestMethod.POST)
	public String insertMyQuestion(
			QuestionDTO questiondto
			, HttpServletRequest request)  throws Exception{
		
		System.out.println("> insertMyQuestion");
			
		CommonsMultipartFile multipartFile = questiondto.getQnaFile();
		
		String uploadRealPath = null;
		
		if ( !multipartFile.isEmpty() ) { 
		uploadRealPath = request.getServletContext().getRealPath("/my/upload");

		File saveDir = new File(uploadRealPath);
		if (!saveDir.exists()) saveDir.mkdirs();
										
		System.out.println("> uploadRealPath : " + uploadRealPath);
		
		String originalFilename = multipartFile.getOriginalFilename();
		String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
		
		File dest = new File(uploadRealPath, filesystemName);
		multipartFile.transferTo(dest); 
		
		questiondto.setQnaFile2(filesystemName);
		
		}
		
		questiondto.setUserCode("M1");
		
		
		int rowCount = this.myDao.insertMyQ(questiondto);
		
		if ( rowCount == 1) {
			return "redirect:searchQuestionList.action";
		} else { 
		}
		return "searchQuestionList.jsp?error";
		
	}
	
	// 첨부파일 이름 체크
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
	      int index = 1;      
	      while( true ) {         
	         File f = new File(uploadRealPath, originalFilename);         
	         if( !f.exists() ) return originalFilename;         

	         String fileName = originalFilename.substring(0, originalFilename.length() - 4 );  
	         String ext =  originalFilename.substring(originalFilename.length() - 4 );  

	         originalFilename = fileName+"-"+(index)+ext;

	         index++;
	      }
	   }
	
	//  재입고알림상품 목록
	@RequestMapping("warehousingAlarmProduct.action")
	String getRestockpd(
			HttpServletRequest request
			, HttpServletResponse response
			, Model model
			)throws Exception {
		
		System.out.println("> getRestockpd ");

	    List<MyRestockDTO> myData = null; 
	    
	    myData = this.myDao.getRestockPd("M1");		
  
	    model.addAttribute("restockData", myData); 
				    
		return "/my/warehousingAlarmProduct.jsp";
		
	
	}
	
	//  A/S 처리현황 
	@RequestMapping("searchAsList.action")
	String getAsProcessing(
			HttpServletRequest request
			, HttpServletResponse response
			)throws Exception {
		
		System.out.println("> getAsProcessing");

		return "/my/searchAsList.jsp";
		
	
	}
	
	

}
