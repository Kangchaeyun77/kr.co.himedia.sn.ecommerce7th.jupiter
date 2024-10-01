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
 * File			: LoginAccessDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240628164457][pluto#himedia.co.kr][CREATE: Initial Release]
 */
package kr.co.himedia.ecommerce.front.login.dto;

/**
 * @version 1.0.0
 * @author pluto#himedia.co.kr
 * 
 * @since 2024-06-28
 * <p>DESCRIPTION: 로그인 접속 빈(Bean)</p>
 * <p>IMPORTANT:</p>
 */
public class LoginAccessDto {
	
	private long seq_mbr;		// 회원 일련번호
	private String dt_login;	// 로그인 일시 [2024-06-28][pluto#himedia.co.kr][TODO:Datetime.getNow("yyyy-MM-dd HH:mm:ss") vs. DATETIME at Oracle]
	private String ip;			// IP
	private String os;			// OS [2024-06-28][pluto#himedia.co.kr][TODO: 코드화(이하 동일)] 
	private String agent;		// Agent [2024-06-28][pluto#himedia.co.kr][TODO: 앱 등 브라우저 이외의 에이전트 정보도 추가]
	private String refer;		// Refer Page
	
	
	public long getSeq_mbr() {
		return seq_mbr;
	}
	public void setSeq_mbr(long seq_mbr) {
		this.seq_mbr = seq_mbr;
	}
	public String getDt_login() {
		return dt_login;
	}
	public void setDt_login(String dt_login) {
		this.dt_login = dt_login;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getRefer() {
		return refer;
	}
	public void setRefer(String refer) {
		this.refer = refer;
	}
}