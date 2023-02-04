package com.nb.web.support.controller;

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

import com.nb.web.support.dao.SupportDao;
import com.nb.web.support.dto.FaqDTO;
import com.nb.web.support.dto.NoticeDTO;
import com.nb.web.support.dto.StoreDTO;

@Controller
@RequestMapping("/support/*")
public class SupportController {
	
	@Autowired
	private SupportDao supportdao = null;
	
	public SupportController() {
		
	}
	
	// 공지사항 목록 보기
	@RequestMapping("notice.action")
	String getNoticeList(HttpServletRequest request
		, HttpServletResponse response
		, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
	 	, @RequestParam(value = "limitCount", defaultValue = "10") int limitCount
		, Model model
		) throws Exception {
		
		System.out.println("> getNoticeList");

		List<NoticeDTO> list = this.supportdao.selectNoticeList(currentPage, limitCount);
	     model.addAttribute("list", list);
	     
	     int totalCount = this.supportdao.getTotalNotice();
	     model.addAttribute("totalCount", totalCount);
	     
		return "/support/notice.jsp";
	}
	
	// 공지사항 상세 보기
	@RequestMapping("noticeDetail.action")
	  public String viewNotice(HttpServletRequest request
			  , HttpServletResponse response
			  , @RequestParam(value = "noti_seq") int seq
			  , Model model
			  ) throws Exception {
		
		  System.out.println("> viewNotice");
		  
		  // 조회수 증가
		  this.supportdao.updateReadCount(seq);

	      NoticeDTO notice = this.supportdao.selectOneNoti(seq);
	      
	      request.setAttribute("notice", notice);
	      
	      return "/support/noticeDetail.jsp";
	   }
	
	// 관리자 : 공지사항 보기
	@RequestMapping("noticeManager.action")
	String getNoticeManager(HttpServletRequest request
		, HttpServletResponse response
		, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
	 	, @RequestParam(value = "limitCount", defaultValue = "10") int limitCount
		, Model model
		) throws Exception {
		
		System.out.println("> getNoticeManager");

		List<NoticeDTO> list = this.supportdao.selectNoticeList(currentPage, limitCount);
	     model.addAttribute("list", list);
	     
	     int totalCount = this.supportdao.getTotalNotice();
	     model.addAttribute("totalCount", totalCount);
	     
		return "/support/noticeManager.jsp";
	}
	
	// 공지사항 등록
	@RequestMapping(value= {"noticeReg.action"}, method=RequestMethod.GET)
	 public String noticeRegmove() throws Exception{
	     return "/support/noticeReg.jsp";
	 }
	   
	@RequestMapping(value = {"noticeReg.action"}, method = RequestMethod.POST)
		  public String noticeReg(HttpServletRequest request
				  , HttpServletResponse response
				  , NoticeDTO noticedto
				  , Model model
				  ) throws Exception {
			
			  System.out.println("> noticeReg");

			  
			     CommonsMultipartFile multipartFile = noticedto.getFile();
			     
			     String uploadRealPath = null; 
			     
			     if ( !multipartFile.isEmpty() ) { 
			        uploadRealPath = request.getSession().getServletContext().getRealPath("/support/img_notice");
			        
			        File saveDir = new File(uploadRealPath);
			        if (!saveDir.exists()) saveDir.mkdirs();

			        System.out.println("> uploadRealPath : " + uploadRealPath);
			        
			        String originalFilename = multipartFile.getOriginalFilename();
			        String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
			        
			        File dest = new File(uploadRealPath, filesystemName);
			        multipartFile.transferTo(dest); 
			        
			        String strData = String.join("/", uploadRealPath, filesystemName); 
			        
			        noticedto.setNotiImg(strData);
			     } 
		
			     int rowCount = this.supportdao.insertNotice(noticedto);
			     
			     if ( rowCount == 1) {
			        return "redirect:notice.action"; // 리다이렉트
			     } else { // 글쓰기 실패하면
			     }
			     return "noticeReg.jsp?error"; // 포워딩
			     
		   }
	
		// 파일 이름 체크
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
	

	//  매장 전체 목록
		@RequestMapping("storeSearch.action")
		String getStoreList(HttpServletRequest request
				, HttpServletResponse response
				, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			 	, @RequestParam(value = "limitCount", defaultValue = "20") int limitCount
			 	, @RequestParam(value = "searchWord", defaultValue = "") String searchWord
			 	, @RequestParam(value = "storeArea", defaultValue = "") String storeArea
				, Model model
				) throws Exception {
		
		System.out.println(">getStoreList ");
		
		int begin = ( currentPage-1)*limitCount + 1;
		int end = begin + limitCount -1;
		int totalCount = this.supportdao.getTotalStore(searchWord, storeArea);
		
		List<StoreDTO> list = this.supportdao.selectAllStore(begin, end, searchWord, storeArea);	
			
		model.addAttribute("list", list);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("storeArea", storeArea);
		
		return "/support/storeSearch.jsp";
		
		
		}	
	
	// 매장 지도 보기
		@RequestMapping("storeMap.action")
		public String viewStoreMap(
				HttpServletRequest request
				, HttpServletResponse response
				, @RequestParam(value = "lat") String lat
				, @RequestParam(value = "lng") String lng
				, @RequestParam(value = "name")String name
				) throws Exception {
			
			System.out.println(">viewStoreMap ");

			return "/support/storeMap.jsp";

			
		}	

		// FAQ 전체 목록
		@RequestMapping("faq.action")
		String getFaqList(HttpServletRequest request
				, HttpServletResponse response
				, @RequestParam(value = "searchWord", required=false) String searchWord
				, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			 	, @RequestParam(value = "limitCount", defaultValue = "10") int limitCount
			 	, Model model
				) throws Exception {
			
			System.out.println(">getFaqList ");
			
		    List<FaqDTO> list =this.supportdao.searchFaq(searchWord, currentPage, limitCount);
			int totalCount = this.supportdao.getTotalFAQ(searchWord);
				
			model.addAttribute("list", list);
			model.addAttribute("totalCount", totalCount);

		   return "/support/faq.jsp"; 
		    	  
		 
		}
}
