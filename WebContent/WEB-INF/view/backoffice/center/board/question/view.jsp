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
 *				: [20240704172500][pluto@himedia.co.kr][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/backoffice/center/board/notice/view.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/bfc/header.jsp" %>
	<style></style>
	<script>
		function remove() {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action="/console/center/board/remove.web";
			frmMain.submit();
		}
		
		function modifyForm() {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action="/console/center/board/modifyForm.web";
			frmMain.submit();
		}
		
		function goList() {
			var frmMain = document.getElementById("frmMain");
			
			frmMain.action="/console/center/board/list.web";
			frmMain.submit();
		}
	</script>
</head>
<body class="nav-md">
<%@ include file="/include/bfc/navi.jsp" %>
<form id="frmMain" method="POST">
<input type="hidden" id="cd_bbs_type"	name="cd_bbs_type"	value="${boardDto.cd_bbs_type}" />
<input type="hidden" id="seq_bbs"		name="seq_bbs"		value="${boardReplyDto.seq_bbs}" />
<div class="table">
	<section class="right_col">
		<article class="x_panel">
			<table class="table table-striped" style="width: 100%; margin-left: auto; margin-right: auto">
				<tr>
					<th style="width: 150px;">카테고리</th>
					<td>
						<select id="cd_ctg" name="cd_ctg" disabled>
							<option value="0"<c:if test="${boardDto.cd_ctg == '0'}"> selected</c:if>>선택</option>
							<option value="1"<c:if test="${boardDto.cd_ctg == '1'}"> selected</c:if>>가입 및 탈퇴</option>
							<option value="2"<c:if test="${boardDto.cd_ctg == '2'}"> selected</c:if>>상품</option>
							<option value="3"<c:if test="${boardDto.cd_ctg == '3'}"> selected</c:if>>구매</option>
							<option value="4"<c:if test="${boardDto.cd_ctg == '4'}"> selected</c:if>>결제</option>
							<option value="5"<c:if test="${boardDto.cd_ctg == '5'}"> selected</c:if>>배송</option>
							<option value="6"<c:if test="${boardDto.cd_ctg == '6'}"> selected</c:if>>환불</option>
							<option value="9"<c:if test="${boardDto.cd_ctg == '9'}"> selected</c:if>>기타</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						${boardDto.title}
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td style="height:300px;">
						${boardDto.content}
					</td>
				</tr>
				<c:if test="${boardDto.file_orig != ''}">
				<tr>
					<th>첨부 파일</th>
					<td>
						<a href="javascript:download('BbsQuestion', ${boardDto.seq_bbs});">다운로드</a>
					</td>
				</tr>
				</c:if>
				<tr>
					<th>등록 일시</th>
					<td>
						${boardDto.dt_reg}
					</td>
				</tr>
			</table>
			<c:if test="${boardDto.seq_reply > 0}">
				<br/>
				<div style="color: #369; font-size: 10pt; font-weight: bold;">[답변]</div>
				<table class="table table-striped" style="width: 100%; margin-left: auto; margin-right: auto">
					<tr>
						<th style="width: 150px;">제목</th>
						<td>
							${boardReplyDto.title}
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td style="height:300px;">
							${boardReplyDto.content}
						</td>
					</tr>
					<c:if test="${boardReplyDto.file_orig != ''}">
					<tr>
						<th>첨부 파일</th>
						<td>
							<a href="javascript:download('BbsQuestion', ${boardReplyDto.seq_bbs});">다운로드</a>
						</td>
					</tr>
					</c:if>
					<tr>
						<th>등록 일시</th>
						<td>
							${boardReplyDto.dt_reg}
						</td>
					</tr>
				</table>
			</c:if>
			<div class="col-md-12 col-sm-12 col-xs-12" style="text-align: center;">
				<c:if test="${boardDto.seq_reply == 0}">
					<a href="/console/center/board/writeForm.web?cd_bbs_type=3&seq_bbs=${boardDto.seq_bbs}" class="btn btn-primary">답변 등록</a>
				</c:if>
				<c:if test="${boardDto.seq_reply > 0}">
					 <input type="button" value="답변 삭제" style="width:100px" onclick="javascript:remove();" />
					 <input type="button" value="답변 수정" style="width:100px" onclick="javascript:modifyForm();" />
				</c:if>
				 <input type="button" value="목록" style="width:100px" onclick="javascript:goList();"/>
			</div>
		</article>
		<aside></aside>
	</section>
	<footer>
		<%@ include file="/include/bfc/footer.jsp" %>
	</footer>
</div>
</form>
</body>
</html>
