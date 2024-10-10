package com.happySteps.front.community.service;
/**
 * @version 1.0.0
 * @author rkdcodbs77#naver.com
 * 
 * @since 2024-10-09
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT: @Transactional가 적용된 메소드에 절대 try/catch 로직 적용 불가</p>
 */

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.happySteps.front.community.dao.Communitydao;
import com.happySteps.front.community.dto.CommunityDto;

import com.happySteps.front.common.dto.PagingDto;
import com.happySteps.front.common.dto.PagingListDto;

@Service("com.happySteps.front.community.service.CommunitySrvc")
public class CommunitySrvc {
	
	@Inject
	Communitydao communitydao;
	
	/**
	 * @param boardDto [게시판 빈]
	 * @return BoardDto
	 * 
	 * @since 2024-10-10
	 * <p>DESCRIPTION: 보기(답변)</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public CommunityDto selectReply(CommunityDto communityDto) {
		return communitydao.selectReply(communityDto);
	}
	
	/**
	 * @param communityDto [게시판 빈]
	 * @return boolean
	 * 
	 * @since 2024-10-10
	 * <p>DESCRIPTION: 삭제</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@Transactional("txFront")
	public boolean delete(CommunityDto communityDto) {
		
		int result = communitydao.delete(communityDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	/**
	 * @param communityDto [게시판 빈]
	 * @return boolean
	 * 
	 * @since 2024-10-10
	 * <p>DESCRIPTION: 등록</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@Transactional("txFront")
	public boolean insert(CommunityDto communityDto) {
		
		
		// 신규 글 번호(seq_bbs)
		communityDto.setSeq_bbs(communitydao.sequence());
		
		int result = communitydao.insert(communityDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	/**
	 * @param communityDto [게시판 빈]
	 * @return boolean
	 * 
	* @since 2024-10-09
	 * <p>DESCRIPTION: 고객센터 수정</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@Transactional("txFront")
	public boolean update(CommunityDto communityDto) {
		
		int result = communitydao.update(communityDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	/**
	 * @param communityDto [게시판 빈]
	 * @return boolean
	 * 
	* @since 2024-10-09
	 * <p>DESCRIPTION: 삭제(처리)</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@Transactional("txFront")
	public boolean deleteFlag(CommunityDto communityDto) {
		
		int result = communitydao.deleteFlag(communityDto);
		
		if (result == 1) return true;
		else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
	}
	
	/**
	 * @param communityDto [게시판 빈]
	 * @return CommunityDto
	 * 
	 * @since 2024-10-09
	 * <p>DESCRIPTION:보기</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public CommunityDto select(CommunityDto communityDto) {
		return communitydao.select(communityDto);
	}
	
	/**
	 * @param pagingDto [게시판 빈]
	 * @return PagingListDto
	 * 
	 * @since 2024-10-09
	 * <p>DESCRIPTION: 커뮤니티</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public PagingListDto list(PagingDto pagingDto) {
		
		PagingListDto pagingListDto = new PagingListDto();
		
		// 전체 라인(행) 수
		int totalLine = communitydao.count(pagingDto);
		// 전체 페이지 수 = 전체 라인(행) 수 / 페이징할 라인수
		int totalPage = (int) Math.ceil((double)totalLine / (double)pagingDto.getLinePerPage());
		pagingDto.setTotalLine(totalLine);
		pagingDto.setTotalPage(totalPage);
		if (totalPage == 0) pagingDto.setCurrentPage(1);
		
		pagingListDto.setPaging(pagingDto);
		pagingListDto.setList(communitydao.list(pagingDto));
		
		return pagingListDto;
	}
}
