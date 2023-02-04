package com.nb.web.my.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.Data;

@Data
public class QuestionDTO {

	private int qnaCode;
	private String userCode;
	private String qnaWritedate;
	private String qnaStatus;
	private int qtSeq;
	private String qtContent;
	private String qnaReplydate;
	private String qnaTitle;
	private String qnaContent;
	private String pdCode;
	private String ordCode;
	private String qnaFile2;
	private String qnaReply;
	private String userEmail;
	private String userName;
	private String userPhone;
	
	private CommonsMultipartFile qnaFile;
	
}
