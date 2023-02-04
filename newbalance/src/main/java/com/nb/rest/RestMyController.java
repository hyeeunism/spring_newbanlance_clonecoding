package com.nb.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nb.web.my.dao.MyDao;

@RestController // ajax
@RequestMapping("/my/*")
public class RestMyController {
	
	@Autowired
	private MyDao myDao = null;
	
	// constructor
	public RestMyController() {
		
	}
	
	// 재입고알림상품 삭제
	@RequestMapping("delRestockpd.json")
		String  deleteRestorkpd(
				HttpServletRequest request
				, HttpServletResponse response
				, @RequestBody Map<String, String[]> delRsList 
				, Model model
				) throws Exception {
		
		
				System.out.println("> delRestockpd AJAX ");
				
				System.out.println(delRsList);

				int rowCount = this.myDao.deleteRsList("M1", delRsList.get("delRsList"));

				if (rowCount >= 1) {
					model.addAttribute("result", "00");
				}else {
					model.addAttribute("result", "99");
				}

				return "resultJSON.toString()";
		
		}
}
