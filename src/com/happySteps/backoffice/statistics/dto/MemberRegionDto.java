/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF happySteps.com.
 * happySteps.com OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 happySteps.com ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 happySteps.com에 있으며,
 * happySteps.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * happySteps.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 happySteps.com All Rights Reserved.
 *
 *
 * Program		: com.happySteps
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: MemberRegionDto.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20241024173400][kbs@happySteps.com][CREATE: Initial Release]
 */
package com.happySteps.backoffice.statistics.dto;

/**
 * @version 1.0.0
 * @author kbs@happySteps.com
 * 
 * @since 2024-08-13
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class MemberRegionDto {
	
	private String region	= "";
	private int sumRegion	= 0;
	
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getSumRegion() {
		return sumRegion;
	}
	public void setSumRegion(int sumRegion) {
		this.sumRegion = sumRegion;
	}
}