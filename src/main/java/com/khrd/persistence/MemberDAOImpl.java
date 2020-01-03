package com.khrd.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khrd.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "mappers.MemberMapper";

	@Override
	public void insert(MemberVO vo) {
		sqlSession.insert(namespace + ".insert", vo);
	}//insert

	@Override
	public List<MemberVO> selectList() {
		return sqlSession.selectList(namespace + ".selectList");
	}

	@Override
	public void update(MemberVO vo) {
		sqlSession.update(namespace + ".update", vo);
	}

	@Override
	public void delete(String id) {
		sqlSession.delete(namespace + ".delete", id);
	}

	@Override
	public MemberVO selectById(String id) {
		return sqlSession.selectOne(namespace + ".selectById", id);
	}

	@Override
	public MemberVO selectByIdAndPass(MemberVO vo) {
		return sqlSession.selectOne(namespace + ".selectByIdAndPass", vo);
	}
	

}//MemberDAOImpl
