<%
/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF HIMEDIA.CO.KR.
 * HIMEDIA.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 HIMEDIA.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 himedia.co.kr에 있으며,
 * himedia.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * himedia.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 himedia.co.kr All Rights Reserved.
 *
 *
 * Program		: kr.co.himedia.ecommerce
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240808142000][hyebongssss@himedia.co.kr][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/front/buy/view.jsp" %>
<%@ taglib prefix="c"					uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"					uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"					uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/layoutSubmain.css" />
	<link rel="stylesheet" type="text/css" title="common stylesheet" href="/css/table.css" />
	<%@ include file="/include/front/top.jsp" %>
	<style>
		ul {
		    list-style-type: none;
		    padding: 0;
		    margin: 0;
		}
		body {
		    font-family: Arial, sans-serif;
		    margin: 0;
		    padding: 0;
		}
		
		.product-detail {
		    display: flex;
		    padding: 20px;
		}
		
		.product-image {
		    flex: 1;
		    padding-right: 20px;
		}
		
		.product-image img {
		    max-width: 100%;
		    height: auto;
		}
		
		.product-info {
		    flex: 1;
		}
		
		.product-name {
		    font-size: 24px;
		    margin-bottom: 10px;
		}
		
		.product-description {
		    font-size: 16px;
		    margin-bottom: 20px;
		}
		
		.product-price {
		    font-size: 20px;
		    color: #E74C3C; /* 원하는 가격 색상 */
		}
		
		.button-container {
		    margin-top: 20px;
		    margin: 0 10px;
		}
	</style>
	<script>
		<%-- 
		function modifyForm() {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action = "/seller/product/modifyForm.web";
			frmMain.submit();
		}
		--%>
		function showContent(event, contentId) {
		    event.preventDefault(); // 페이지 이동 방지

		    // 모든 tab-content를 숨김
		    var contents = document.getElementsByClassName('tab-content');
		    for (var i = 0; i < contents.length; i++) {
		        contents[i].style.display = 'none';
		    }

		    // 선택한 tab-content만 표시
		    var selectedContent = document.getElementById(contentId);
		    if (selectedContent) {
		        selectedContent.style.display = 'block';
		    }

		    // 모든 li 태그에서 'on' 클래스를 제거
		    var tabs = document.querySelectorAll('.item_goods_tab li');
		    for (var i = 0; i < tabs.length; i++) {
		        tabs[i].classList.remove('on');
		    }

		    // 현재 선택한 탭에 'on' 클래스 추가
		    event.currentTarget.parentElement.classList.add('on');
		}
	</script>
</head>
<body>
<form id="frmMain" method="POST">
<input type="hidden" name="seq_prd" value="${saleDto.seq_prd}" />
<input type="hidden" name="seq_sle" value="${saleDto.seq_sle}" />
<input type="hidden" name="sle_nm" value="${saleDto.sle_nm}" />
<input type="hidden" name="price" value="${saleDto.price_sale}" />
<input type="hidden" id="cd_ctg_pet" 	name="cd_ctg_pet" value="${saleDto.cd_ctg_pet}"/>
<input type="hidden" name="cd_state_prd" value="${saleDto.cd_state_sle}" />
<div class="container">
	<header>
		<%@ include file="/include/front/header.jsp" %>
	</header>
	<nav>
		<%@ include file="/include/front/gnb_shopping.jsp" %>
	</nav>
	<section class="content">
		<article class="txtCenter">
			<div class="product-detail">
		        <div class="product-image">
		            ${saleDto.img} alt="상품 이미지">
		        </div>
		        <div class="product-info">
		            <h1 class="product-name">${saleDto.sle_nm}</h1>
		            <p class="product-description">${saleDto.desces}</p>
		            <p class="product-price">판매가: ${saleDto.price_sale}</p>
		        </div>
		        
		    </div>
			<div class="button-container" style="text-align:center;padding-top:10px;padding-bottom:10px">
                <button class="button list-button" onclick="#">목록</button>
                <button class="button buy-button" onclick="#">구매</button>
                <button class="button cart-button" onclick="#">장바구니</button>
            </div>
			<div class="item_goods_tab">
                <ul>
                    <li class="on"><a href="#detail">상품상세정보</a></li>
                    <li><a href="#delivery">배송안내</a></li>
                    <li><a href="#exchange">교환 및 반품안내</a></li>
                    <li><a href="#reviews">상품후기 <strong>(0)</strong></a></li>
                    <li><a href="#qna">상품문의 <strong>(0)</strong></a></li>
                </ul>
            </div>
            <div id="detail" class="tab-content">
			    <h3>상품상세정보</h3>
			    <p>여기에 상품의 상세 정보를 입력하세요.${saleDto.desces}</p>
			</div>
			<div id="delivery" class="tab-content" style="display:none;">
			    <h3>배송안내</h3>
			    <p>배송안내
					- 배송비 : 기본배송료는 3,000원 입니다. (도서,산간,오지 일부지역은 배송비가 추가될 수 있습니다)  
					
					            40,000원 이상 구매시 무료배송입니다.
					
					            일부 부피가 큰 제품의 경우 제품당 5,000원의 추가 배송비가 부과됩니다.
					
					 
					
					- 본 상품의 평균 배송일은 2~3일입니다.
					
					  (입금 확인 후) 설치 상품의 경우 다소 늦어질수 있습니다.
					
					  [배송예정일은 주문시점(주문순서)에 따른 유동성이 발생하므로 평균 배송일과는 차이가 발생할 수 있습니다.]
					
					 
					
					- 본 상품의 배송 가능일은 2~3일 입니다. 
					
					  배송 가능일이란 본 상품을 주문 하신 고객님들께 상품 배송이 가능한 기간을 의미합니다. 
					
					  (단, 연휴 및 공휴일은 기간 계산시 제외하며 현금 주문일 경우 입금일 기준 입니다.)</p>
			</div>
			<div id="exchange" class="tab-content" style="display:none;">
			    <h3>교환 및 반품안내</h3>
			    <p>교환 및 반품안내
					- 상품 택(tag)제거 또는 개봉으로 상품 가치 훼손 시에는 상품수령후 7일 이내라도 교환 및 반품이 불가능합니다.
					
					- 저단가 상품, 일부 특가 상품은 고객 변심에 의한 교환, 반품은 고객께서 배송비를 부담하셔야 합니다(제품의 하자,배송오류는 제외)
					
					- 일부 상품은 신모델 출시, 부품가격 변동 등 제조사 사정으로 가격이 변동될 수 있습니다.
					
					- 신발의 경우, 실외에서 착화하였거나 사용흔적이 있는 경우에는 교환/반품 기간내라도 교환 및 반품이 불가능 합니다.
					
					- 수제화 중 개별 주문제작상품(굽높이,발볼,사이즈 변경)의 경우에는 제작완료, 인수 후에는 교환/반품기간내라도 교환 및 반품이 불가능 합니다. 
					
					- 수입,명품 제품의 경우, 제품 및 본 상품의 박스 훼손, 분실 등으로 인한 상품 가치 훼손 시 교환 및 반품이 불가능 하오니, 양해 바랍니다.
					
					- 일부 특가 상품의 경우, 인수 후에는 제품 하자나 오배송의 경우를 제외한 고객님의 단순변심에 의한 교환, 반품이 불가능할 수 있사오니, 각 상품의 상품상세정보를 꼭 참조하십시오. 
					
					환불안내
					- 상품 청약철회 가능기간은 상품 수령일로 부터 7일 이내 입니다.</p>
			</div>
			<div id="reviews" class="tab-content" style="display:none;">
			    <h3>상품후기</h3>
			    <p>여기에 상품후기를 입력하세요.</p>
			</div>
			<div id="qna" class="tab-content" style="display:none;">
			    <h3>상품문의</h3>
			    <p>여기에 상품문의 내용을 입력하세요.</p>
			</div>
		</article>
		<aside></aside>
	</section>
	<footer>
		<%@ include file="/include/seller/footer.jsp" %>
	</footer>
</div>
</form>
</body>
</html>