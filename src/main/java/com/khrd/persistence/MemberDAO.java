package com.khrd.persistence;

import java.util.List;

import com.khrd.domain.MemberVO;

public interface MemberDAO {

	public void insert(MemberVO vo);
	public List<MemberVO> selectList();
	public void update(MemberVO vo);
	public void delete(String id);
	public MemberVO selectById(String id);
	public MemberVO selectByIdAndPass(MemberVO vo);
	
}//MemberDAO
