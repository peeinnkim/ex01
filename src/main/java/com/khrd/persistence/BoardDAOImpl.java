package com.khrd.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "mappers.BoardMapper";
	
	@Override
	public void insert(BoardVO b) {
		sqlSession.insert(namespace + ".insert", b);
	}

	@Override
	public List<BoardVO> listAll() {
		return sqlSession.selectList(namespace + ".listAll");
	}

	@Override
	public void update(BoardVO b) {
		sqlSession.update(namespace + ".update", b);
	}

	@Override
	public void delete(int no) {
		sqlSession.delete(namespace + ".delete", no);
	}

	@Override
	public BoardVO selectByNo(int bno) {
		return sqlSession.selectOne(namespace + ".selectByNo", bno);
	}

	@Override
	public void updateCnt(BoardVO b) {
		sqlSession.update(namespace + ".updateCnt", b);
	}

	@Override
	public List<BoardVO> listPage(int page, int perPage) {
		page = (page - 1) * perPage;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("page", page);
		map.put("perPage", perPage);
		
		return sqlSession.selectList(namespace + ".listPage", map);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) {
		return sqlSession.selectList(namespace + ".listCri", cri);
	}

	@Override
	public int listCount() {
		return sqlSession.selectOne(namespace + ".listCount");
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) {
		return sqlSession.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace + ".listSearchCount", cri);
	}

	@Override
	public int updateReplyCount(int amount, int bno) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("amount", amount);
		map.put("bno", bno);
		
		return sqlSession.update(namespace + ".updateReplyCount", map);
	}

	@Override
	public void addAttach(String fullname) {
		sqlSession.insert(namespace + ".addAttach", fullname);
	}

	@Override
	public BoardVO selectByNoHasAttach(int bno) {
		return sqlSession.selectOne(namespace + ".selectByNoHasAttach", bno);
	}

	@Override
	public void removeAttach(int no) {
		sqlSession.delete(namespace + ".removeAttach", no);
	}

	@Override
	public void removeAttachByName(String name, int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fullName", name);
		map.put("bno", bno);
		
		sqlSession.delete(namespace + ".removeAttachByName", map);
	}

	@Override
	public void addAttachAtMod(String fullName, int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fullName", fullName);
		map.put("bno", bno);
		
		sqlSession.insert(namespace + ".addAttachAtMod", map);
	}

}//BoardDAOImpl
