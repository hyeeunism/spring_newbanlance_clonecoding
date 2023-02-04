package com.nb.web.my.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nb.web.my.dto.CouponDTO;
import com.nb.web.my.dto.MbLevelDTO;
import com.nb.web.my.dto.MileageDTO;
import com.nb.web.my.dto.MyDeliveryInfoDTO;
import com.nb.web.my.dto.MyMainDTO;
import com.nb.web.my.dto.MyNbPointDTO;
import com.nb.web.my.dto.MyRestockDTO;
import com.nb.web.my.dto.QuestionDTO;
import com.nb.web.my.dto.SaleCodeDTO;
import com.nb.web.support.dto.StoreDTO;

public interface MyDao {

	public MyMainDTO getMyMainMenuInfo(Connection conn, String id) throws SQLException;
	public List<MyDeliveryInfoDTO> getMemberDeliveryInfo(Connection conn, String id) throws SQLException;
	public void updateMaDefault(Connection conn, String userCode) throws SQLException;
	public int insertMemberDeliveryInfo(Connection conn, MyDeliveryInfoDTO dto) throws SQLException;
	public boolean checkDuplicateDeliveryInfo(Connection conn, MyDeliveryInfoDTO dto ) throws SQLException;
	public int updateMemberDeliveryInfo(Connection conn, MyDeliveryInfoDTO dto) throws SQLException;
	public int deleteMemberDeliveryInfo(Connection conn, int maSeq) throws SQLException;
	public MbLevelDTO getMbLevel(Connection conn, String userCode) throws SQLException;
	public SaleCodeDTO getSaleCode(Connection conn, String userCode) throws SQLException;
	public int getMileage(Connection conn, String userCode) throws SQLException;
	public ArrayList<MileageDTO> selectMileageByDate(Connection conn, String userCode, String sDate, String eDate) throws SQLException;
	public ArrayList<CouponDTO> getCoupon(Connection conn, String userCode) throws SQLException;
	public ArrayList<CouponDTO> selectCouponByDate(Connection conn, String userCode, String sDate, String eDate) throws SQLException;
	public ArrayList<MyNbPointDTO> selectMyNbPointByDate(Connection conn, String userCode, String sDate, String eDate) throws SQLException;
	public MyNbPointDTO getLevel(Connection conn, String userCode) throws SQLException;
	public CouponDTO getUserCoupon(Connection conn, String userCode, String cpCode) throws SQLException;

	// 1:1문의
	List <QuestionDTO> getQuestion(String userCode, @Param("srqtSeq") String srqtseq) throws SQLException;

	// 재입고알림상품 
	List <MyRestockDTO> getRestockPd(String userCode) throws SQLException;

	// 재입고알림상품 삭제
	int deleteRsList(String userCode, String[] delRsList) throws SQLException;
	
	// 1:1문의 등록
	int insertMyQ(QuestionDTO questiondto) throws SQLException;

}

 