package com.nb.web.support.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nb.web.my.dto.QuestionDTO;
import com.nb.web.support.dto.FaqDTO;
import com.nb.web.support.dto.NoticeDTO;
import com.nb.web.support.dto.StoreDTO;

public interface SupportDao {
	
	// 공지사항 조회수 증가
	int updateReadCount(int seq) throws SQLException;
	
	// 공지사항 목록
	List<NoticeDTO> selectNoticeList(int currentPage, int limitCount) throws SQLException;
	
	// 공지사항 상세보기
	NoticeDTO selectOneNoti(int seq) throws SQLException;
	
	// 공지사항 totalCount
	int getTotalNotice()  throws SQLException;
	
	// 공지사항 등록
	int insertNotice(NoticeDTO noticedto) throws SQLException;
	
	// 전체 매장 목록
	List<StoreDTO> selectAllStore(int currentPage, int limitCount, @Param("searchWord") String searchWord, @Param("storeArea") String storeArea) throws SQLException;
	
	// 전체 매장 totalCount
	int getTotalStore(@Param("searchWord") String searchWord, @Param("storeArea") String storeArea) throws SQLException;
	
	// 전체 FAQ 목록
	List<FaqDTO> searchFaq(@Param("searchWord") String searchWord, int currentPage, int limitCount) throws SQLException;
		
	// FAQ totalCount
	int getTotalFAQ(@Param("searchWord") String searchWord) throws SQLException;
	

}