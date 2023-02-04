package com.nb.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nb.web.support.dao.SupportDao;
import com.nb.web.support.dto.FaqDTO;
import com.nb.web.support.dto.NoticeDTO;
import com.nb.web.support.dto.StoreDTO;

@RestController // ajax
@RequestMapping("/support/*")
public class RestSupportController {
	
	@Autowired
	private SupportDao supportdao = null;
	
	// constructor
	public RestSupportController() {
		
	}
	
	// 공지사항 더보기버튼 ajax
	@RequestMapping("notice.json")
	List<NoticeDTO> list(HttpServletRequest request
		, HttpServletResponse response
		, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
	 	, @RequestParam(value = "limitCount", defaultValue = "10") int limitCount
		) throws Exception {
		
		System.out.println("> notice.json AJAX ");
		
		List<NoticeDTO> list = this.supportdao.selectNoticeList(currentPage, limitCount);
	     
	    int totalCount = this.supportdao.getTotalNotice();
	     
		return list;
	}
	

	// 매장 더보기버튼 ajax
		@RequestMapping("store.json")
		List <StoreDTO> list(HttpServletRequest request
				, HttpServletResponse response
				, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			 	, @RequestParam(value = "limitCount", defaultValue = "20") int limitCount
			 	, @RequestParam(value = "searchWord", defaultValue = "") String searchWord
			 	, @RequestParam(value = "storeArea", defaultValue = "") String storeArea
				) throws Exception {
		
			System.out.println("> store.json AJAX ");
		
		int begin = ( currentPage-1)*limitCount + 1;
		int end = begin + limitCount -1;
		int totalCount = this.supportdao.getTotalStore(searchWord, storeArea);
		
		List <StoreDTO> list = this.supportdao.selectAllStore(begin, end, searchWord, storeArea);	
		
		return list;

		}	

		// FAQ 전체 목록
		@RequestMapping("faq.json")
		List<FaqDTO> list(HttpServletRequest request
				, HttpServletResponse response
				, @RequestParam(value = "searchWord", required=false) String searchWord
				, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			 	, @RequestParam(value = "limitCount", defaultValue = "10") int limitCount
				) throws Exception {
			
			System.out.println("> faq.json AJAX ");
			
			List<FaqDTO> list =this.supportdao.searchFaq(searchWord, currentPage, limitCount);
			int totalCount = this.supportdao.getTotalFAQ(searchWord);

		   return list; 
		    	  
		 
		}
}
