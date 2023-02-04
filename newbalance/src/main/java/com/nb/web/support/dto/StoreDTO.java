package com.nb.web.support.dto;

import lombok.Data;

@Data
public class StoreDTO {

	private int storeSeq; // 매장seq
	private int storecateSeq; // 매장분류seq
	private String storecateName; // 매장분류명
	private int storeareaSeq; // 매장지역seq
	private String storeareaName; // 매장지역명
	private String storeName; // 매장 이름
	private String storeAddr; // 매장 주소
	private String storeTel; // 매장 전화번호
	private Double storeLat; // 매장 위도 
	private Double storeLng; // 매장 경도
	
	
}
