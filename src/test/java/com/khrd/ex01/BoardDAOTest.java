package com.khrd.ex01;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;
import com.khrd.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDAOTest {
	
	@Autowired
	private BoardDAO dao;
	
	//@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("글을넣어봅시다");
		vo.setContent("후루루루루우루우후울우");
		vo.setWriter("mlmlml");
		dao.insert(vo);
	}
	
	//@Test
	public void testListAll() {
		List<BoardVO> list = dao.listAll();
		
		for(BoardVO b : list) {
			System.out.println(b);
		}
	}
	
	//@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setTitle("수정해볼깍요");
		vo.setContent("호롤롤로");
		vo.setWriter("mlmlml");
		dao.update(vo);
	}
	
	//@Test
	public void testDelete() {
		dao.delete(3);
	}
	
	@Test
	public void testSelectByNo() {
		BoardVO b = dao.selectByNo(4089);
		System.out.println(b);
	}
	
	//@Test
	public void testListPage() {
		dao.listPage(2, 15);
	}

	//@Test
	public void testListCri() {
		dao.listCriteria(new Criteria(2, 5));
	}
	
	//@Test
	public void testListCount() {
		System.out.println("총 게시물 수: " + dao.listCount());
	}
	
	//@Test
	public void testListSearch() {
		SearchCriteria s = new SearchCriteria();
		s.setPage(1);
		s.setPerPageNum(10);
		s.setKeyword("지에스");
		s.setSearchType("t");
		dao.listSearch(s);
	}
	
}//MemberDAOTest
