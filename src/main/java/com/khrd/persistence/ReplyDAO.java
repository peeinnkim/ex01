package com.khrd.persistence;

import java.util.List;

import com.khrd.domain.Criteria;
import com.khrd.domain.ReplyVO;

public interface ReplyDAO {
	
	public void create(ReplyVO vo);
	public void update(ReplyVO vo);
	public void delete(int rno);
	public void deleteByBno(int bno);

	public List<ReplyVO> list(int bno);
	public List<ReplyVO> listCri(int bno, Criteria cri);
	
	public int listCriTotalCount(int bno);
	public int getBno(int rno);
	
}//ReplyDAO
