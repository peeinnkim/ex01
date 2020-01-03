package com.khrd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khrd.domain.MemberVO;
import com.khrd.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO dao;

	@Override
	public void insert(MemberVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(MemberVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public List<MemberVO> selectList() {
		return dao.selectList();
	}

	@Override
	public MemberVO selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public MemberVO selectByIdAndPass(MemberVO vo) {
		return dao.selectByIdAndPass(vo);
	}

	
	
}//MemberServiceImpl
