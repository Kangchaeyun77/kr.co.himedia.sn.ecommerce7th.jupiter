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
 * File			: MemberWeb.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240620143410][pluto@himedia.co.kr][CREATE: Initial Release]
 */
package com.happySteps.front.member.controller;

import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.happySteps.common.component.EmailCmpn;
import com.happySteps.common.dto.EmailDto;
import com.happySteps.front.common.Common;
import com.happySteps.front.member.dto.MemberDto;
import com.happySteps.front.member.service.MemberSrvc;
import com.happySteps.util.security.HSwithSHA;
import com.happySteps.util.security.SKwithAES;
/**
 * @version 1.0.0
 * @author pluto@himedia.co.kr
 * 
 * @since 2024-06-20
 * <p>DESCRIPTION: 회원 컨트롤러</p>
 * <p>IMPORTANT:</p>
 */
@Controller("com.happySteps.front.member.controller.MemberWeb")
public class MemberWeb extends Common {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(MemberWeb.class);
	
	@Autowired
	Properties staticProperties;
	
	@Autowired
	private MessageSourceAccessor dynamicProperties;
	
	@Inject
	private EmailCmpn emailCmpn;
	
	@Inject
	private MemberSrvc memberSrvc;
	
	@RequestMapping(value = "/front/member/emailConfirm.web", method = RequestMethod.POST)
	public ModelAndView emailConfirm(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			EmailDto emailDto = new EmailDto();
			
			emailDto.setSender(dynamicProperties.getMessage("email.sender.mail"));
			emailDto.setTo(new String[] {memberDto.getEmail()});
			emailDto.setSubject("인증 메일");
			emailDto.setMessage("인증 번호" + "???");
			
			emailCmpn.send(emailDto);
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".emailConfirm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
		
	}
			
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-02
	 * <p>DESCRIPTION: 마이페이지 수정 처리</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	
	@RequestMapping(value = "/front/member/modifyProc.web")
	public ModelAndView modifyProc(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto, String _hobbys, String _flg_sms, String _flg_email) {

		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			int seq_mbr = Integer.parseInt(getSession(request, "SEQ_MBR"));
			memberDto.setSeq_mbr(seq_mbr);
			memberDto.setUpdater(seq_mbr);
			
			// SMS 또는 Email 수신 동의 정보가 없을 경우 기본값(N)로 설정
			if (memberDto.getFlg_email() == null || memberDto.getFlg_email().equals("")) memberDto.setFlg_email("N");
			if (memberDto.getFlg_sms() == null || memberDto.getFlg_sms().equals("")) memberDto.setFlg_sms("N");
			
			// 입력한 정보와 기존 정보가 같으면 업데이트를 안 하고 다르면 입력한 정보로 업데이트(시간 포함)
			if (memberDto.getFlg_email().equals(_flg_email)) memberDto.setFlg_email("");
			if (memberDto.getFlg_sms().equals(_flg_sms)) memberDto.setFlg_sms("");
			
			//if(memberDto.getHobbys().equals(_hobbys)) memberDto.setHobbys("");
			
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			memberDto.setPhone(aes.encode(memberDto.getPhone()));
			memberDto.setPost(aes.encode(memberDto.getPost()));
			memberDto.setAddr1(aes.encode(memberDto.getAddr1()));
			memberDto.setAddr2(aes.encode(memberDto.getAddr2()));
			
			if (memberSrvc.update(memberDto)) {
				request.setAttribute("script"	, "alert('수정되었습니다.');");
				request.setAttribute("redirect"	, "/front/myPage/");
			}
			else {
				request.setAttribute("script"	, "alert('시스템 관리자에게 문의하세요!');");
				request.setAttribute("redirect"	, "/");
			}
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param boardDto [게시판 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-08-02
	 * <p>DESCRIPTION: 마이페이지 수정 폼</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/member/modifyForm.web")
	public ModelAndView modifyForm(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			// 대칭키 암호화(AES-256)
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			memberDto.setSeq_mbr(Integer.parseInt(getSession(request, "SEQ_MBR")));
			
			MemberDto _memberDto = memberSrvc.select(memberDto);
			
			_memberDto.setEmail(aes.decode(_memberDto.getEmail()));
			_memberDto.setMbr_nm(aes.decode(_memberDto.getMbr_nm()));
			_memberDto.setPhone(aes.decode(_memberDto.getPhone()));
			_memberDto.setPost(aes.decode(_memberDto.getPost()));
			_memberDto.setAddr1(aes.decode(_memberDto.getAddr1()));
			_memberDto.setAddr2(aes.decode(_memberDto.getAddr2()));
			
			mav.addObject("memberDto", _memberDto);
			
			mav.setViewName("front/member/modifyForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".modifyForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-06-21
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/member/checkDuplicate.json", method = RequestMethod.POST, headers = {"content-type=application/json; charset=UTF-8", "accept=application/json"}, consumes="application/json; charset=UTF-8", produces="application/json; charset=UTF-8")
	public @ResponseBody boolean checkDuplicate(@RequestBody MemberDto memberDto) {
		
		boolean isDuplicate = true;
		
		try {
			// 대칭키 암호화(AES-256)
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			memberDto.setEmail(aes.encode(memberDto.getEmail()));
			
			int count = memberSrvc.selectDuplicate(memberDto);
			
			if (count == 0) isDuplicate = false;
			
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".checkDuplicate()] " + e.getMessage(), e);
		}
		finally {}
		
		return isDuplicate;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @return ModelAndView
	 * 
	 * @since 2024-06-21
	 * <p>DESCRIPTION: 회원 가입 폼</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/member/registerForm.web")
	public ModelAndView registerForm(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			mav.setViewName("front/member/registerForm");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".registerForm()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
	
	/**
	 * @param request [요청 서블릿]
	 * @param response [응답 서블릿]
	 * @param mebmerDto [회원 빈]
	 * @return ModelAndView
	 * 
	 * @since 2024-06-20
	 * <p>DESCRIPTION: 회원 가입 처리</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@RequestMapping(value = "/front/member/registerProc.web", method = RequestMethod.POST)
	public ModelAndView registerProc(HttpServletRequest request, HttpServletResponse response
			, MemberDto memberDto
			, String term_1
			, String term_2
			, String term_3) {
		
		ModelAndView mav = new ModelAndView("redirect:/error.web");
		
		try {
			
			if (term_1 == null || term_1.equals("")) term_1 = "N";
			if (term_2 == null || term_2.equals("")) term_2 = "N";
			if (term_3 == null || term_3.equals("")) term_3 = "N";
			
			//logger.debug(term_1);
			//logger.debug(term_2);
			//logger.debug(term_3);
			
			// [2024-08-07][pluto@himedia.co.kr][TODO: 약관 갯수(JSP)에 무관하게 처리될 수 있도록 개선]
			String[] arrTermAgreement = {term_1, term_2, term_3};
			
			/*
			logger.debug("암호화 전: " + memberDto.getEmail());
			logger.debug("암호화 전: " + memberDto.getPasswd());
			logger.debug("암호화 전: " + memberDto.getMbr_nm());
			logger.debug("암호화 전: " + memberDto.getGender());
			logger.debug("암호화 전: " + memberDto.getPhone());
			logger.debug("암호화 전: " + memberDto.getPost() + " " + memberDto.getAddr1() + " " + memberDto.getAddr2());
			logger.debug(memberDto.getIntro());
			*/
			if (memberDto.getFlg_email() == null || memberDto.getFlg_email().equals("")) memberDto.setFlg_email("N");
			if (memberDto.getFlg_sms() == null || memberDto.getFlg_sms().equals("")) memberDto.setFlg_sms("N");
			
			//logger.debug(memberDto.getFlg_email());
			//logger.debug(memberDto.getFlg_sms());
			
			// 해쉬 암호화(SHA-256)
			memberDto.setPasswd(HSwithSHA.encode(memberDto.getPasswd()));
			//logger.debug("암호화 후(Passwd): " + memberDto.getPasswd());
			
			// 대칭키 암호화(AES-256)
			String staticKey	= staticProperties.getProperty("front.enc.user.aes256.key", "[UNDEFINED]");
			SKwithAES aes		= new SKwithAES(staticKey);
			
			memberDto.setEmail(aes.encode(memberDto.getEmail()));
			memberDto.setMbr_nm(aes.encode(memberDto.getMbr_nm()));
			memberDto.setPhone(aes.encode(memberDto.getPhone()));
			memberDto.setPost(aes.encode(memberDto.getPost()));
			memberDto.setAddr1(aes.encode(memberDto.getAddr1()));
			memberDto.setAddr2(aes.encode(memberDto.getAddr2()));
			//logger.debug("암호화 후(Email): " + memberDto.getEmail());
			//logger.debug("암호화 후(Name): " + memberDto.getName());
			//logger.debug("암호화 후(Phone): " + memberDto.getPhone());
			//logger.debug("암호화 후(Post + Addr1 + Addr2): " + memberDto.getPost() + " " + memberDto.getAddr1() + " " + memberDto.getAddr2());
			
			boolean insert = memberSrvc.insert(memberDto, arrTermAgreement, aes.decode(memberDto.getPost()));
			
			if (insert) {
				logger.debug("가입 성공");
				
				// 가입 축하 이메일 발송
				EmailDto emailDto = new EmailDto();
				
				emailDto.setSender(dynamicProperties.getMessage("email.sender.mail"));
				emailDto.setTo(new String[] {memberDto.getEmail()});
				emailDto.setSubject("가입 축하 메일");
				emailDto.setMessage("<b>가입</b>을 축하합니다.");
				
				emailCmpn.send(emailDto);
				
			}
			else logger.debug("가입 실패");
			
			
			request.setAttribute("redirect"	, "/");
			
			mav.setViewName("forward:/servlet/result.web");
		}
		catch (Exception e) {
			logger.error("[" + this.getClass().getName() + ".registerProc()] " + e.getMessage(), e);
		}
		finally {}
		
		return mav;
	}
}