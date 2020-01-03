package com.khrd.service;

import java.util.List;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;

public interface BoardService {
	
	public void regist(BoardVO vo);
	public void modify(BoardVO vo);
	public void cntIncrease(BoardVO vo);
	public void remove(int bno);
	
	public BoardVO read(int bno);
	public BoardVO readByNoHasAttach(int bno);
	public int listCount();
	public int listSearchCount(SearchCriteria cri);

	public List<BoardVO> listAll();
	public List<BoardVO> listCriteria(Criteria cri);
	public List<BoardVO> listSearch(SearchCriteria cri);
	
	public void modifyAttach(BoardVO vo, String[] delFiles);
	
	
}//BoardService
