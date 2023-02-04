package com.nb.web.support.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class NoticeDTO {
	
	
	private int notiSeq; 
	private String notiTitle;
	private String notiInsertdate;
	private int notiViews;
	private String notiContent;
	private String notiImg;
	
	private CommonsMultipartFile file; 
	
}

