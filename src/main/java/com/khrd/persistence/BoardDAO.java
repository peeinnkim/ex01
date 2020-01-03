package com.khrd.persistence;

import java.util.List;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;

public interface BoardDAO {
	
	public void insert(BoardVO b);
	public void update(BoardVO b);
	public void updateCnt(BoardVO b);
	public void delete(int no);
	
	public List<BoardVO> listAll();
	public List<BoardVO> listPage(int page, int perPage);
	public List<BoardVO> listCriteria(Criteria cri);
	public List<BoardVO> listSearch(SearchCriteria cri);
	
	public BoardVO selectByNo(int bno);
	public BoardVO selectByNoHasAttach(int bno);
	public int listCount();
	public int listSearchCount(SearchCriteria cri);
	public int updateReplyCount(int amount, int bno);
	
	public void addAttach(String fullName);
	public void addAttachAtMod(String fullName, int no);
	public void removeAttach(int no);
	public void removeAttachByName(String fullName, int no);
	
}//BoardDAO
