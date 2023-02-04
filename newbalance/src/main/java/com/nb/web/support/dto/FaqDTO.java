package com.nb.web.support.dto;

import lombok.Data;

@Data
public class FaqDTO {
	

private int faqSeq; // 시퀀스
private String faqCategory; // 카테고리
private String faqQuestion; // 질문
private String faqAnswer; // 대답

}
