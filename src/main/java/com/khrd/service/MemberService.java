package com.khrd.service;

import java.util.List;

import com.khrd.domain.MemberVO;

public interface MemberService {
	
	public void insert(MemberVO vo);
	public void update(MemberVO vo);
	public void delete(String id);
	
	public List<MemberVO> selectList();
	public MemberVO selectById(String id);
	public MemberVO selectByIdAndPass(MemberVO vo);
	
}//MemberService
