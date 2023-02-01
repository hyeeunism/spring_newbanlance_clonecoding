<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/newbalance/common/header.css">
<link rel="stylesheet" href="/newbalance/common/footer.css">
<link rel="stylesheet" href="/newbalance/css/support/noticeReg.css" /> 
<link rel="stylesheet" href="/newbalance/css/support/support.css" /> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<style>
</style>
<jsp:include page="../common/header.jsp"></jsp:include>
</head>
<body>
<div class="container" style="padding-top: 0px;">
		<div class="contents">
			<!-- my_wrap -->
			<div class="my_wrap">
<!-- lnb -->
<div class="lnb">
	<h2>BOARD MANAGER</h2>
		<div class="category">
		<strong class="tit">게시판 관리 페이지</strong>
		<ul>
			<li class=""><a href="/support/notiReg.action">공지사항 페이지 관리</a></li>
			<li class=""><a href="#">상품리뷰 관리</a></li>
			<li class=""><a href="#">1:1문의 관리</a></li>
		</ul>
	</div>
</div>
<!-- // lnb -->
<!-- my_cont -->
	<form action="" name="noticeReg" id="noticeReg" method="post" enctype="multipart/form-data">
		<div class="my_cont">
					<div class="title_area">
                        <h3 class="page_tit">공지사항 등록</h3>
                    </div>
                    <div class="con_my_confirm add_info">
                        <div class="form_area">
                            <fieldset>                                <legend>공지사항 등록 양식</legend>

                                <div class="row">
                                    <label for="notiTitle" class="ftit">제목</label>
                                    <div class="fdata">
                                        <input type="text" id="notiTitle" name="notiTitle" class="ip_text lg" title="제목">
                                    </div>
                                </div>
                                <div class="row">
                                    <label for="notiContent" class="ftit">내용</label>
                                    <div class="fdata">
                                        <textarea id="notiContent" name="notiContent" class="qtextarea" cols="100" rows="10" placeholder="등록할 공지사항 내용을 입력해주세요."></textarea>
                                  </div>
                                </div>
                                <div class="row">
							<label for="notiImg" class="ftit">파일 첨부</label>
							<div class="fdata">
								<div class="qfile">
									<input id="file" type="text" value="" title="파일명" class="ip_text" readonly />
									<em class="btn_ty_form">파일찾기 <input type="file" name="file" title="파일찾기" id="file" onchange="fnLoadFile();"></em>
								</div>
								<em class="ip_info point_g" style="margin-bottom:0">파일 크기 20MB 이하 / jpg, gif 파일만 등록 가능합니다.</em>
							</div>
						</div>
                            </fieldset>
                        </div>
                        <div class="qbtn_area">
                        <a href="javascript:;" class="qbtn_ty_bface lg" id="insertNotice">등록</a>
                        </div>
                    </div>
				</div>
	</form>
					<!-- // my_cont -->
</div>
</div>
</div>
<jsp:include page="/common/footer.jsp"></jsp:include>
<script>

var processingRegist = false;


$(document).ready(function(){

	$("#insertNotice").click(function(){
				
		if(checkValidation() == false) {
			return;				
		} 
		
		alert("공지사항이 등록되었습니다.");

		$("#noticeReg").submit();
	});		
	
	
	
});

function checkValidation() {
	
    if($.trim($("#notiTitle").val()) == "") {
		alert("제목은 필수값입니다.");
		return false;
	}

    if(processingRegist == true) {
		alert("이미 처리 중입니다.");
		return false;
		
	}			

}
</script>
</body>
</html>