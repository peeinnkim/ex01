package com.khrd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;
import com.khrd.persistence.BoardDAO;
import com.khrd.persistence.ReplyDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	@Autowired
	private ReplyDAO rDao;

	@Override
	@Transactional
	public void regist(BoardVO vo) {
		dao.insert(vo);
		
		//첨부파일 넣기
		for(String file : vo.getFiles()) {
			dao.addAttach(file);
		}
	}

	@Override
	public List<BoardVO> listAll() {
		return dao.listAll();
	}

	@Override
	@Transactional
	public void modify(BoardVO vo) {
		dao.update(vo);
		
		//첨부파일 넣기
		for(String file : vo.getFiles()) {
			dao.addAttachAtMod(file, vo.getBno());
		}
	}

	@Override
	@Transactional
	public void remove(int bno) {
		rDao.deleteByBno(bno);
		dao.removeAttach(bno);
		dao.delete(bno);
	}

	@Override
	public BoardVO read(int bno) {
		return dao.selectByNo(bno);
	}

	@Override
	public void cntIncrease(BoardVO vo) {
		dao.updateCnt(vo);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) {
		return dao.listCriteria(cri);
	}

	@Override
	public int listCount() {
		return dao.listCount();
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return dao.listSearchCount(cri);
	}

	@Override
	public BoardVO readByNoHasAttach(int bno) {
		return dao.selectByNoHasAttach(bno);
	}

	@Override
	public void modifyAttach(BoardVO vo, String[] delFiles) {
		//db에 삭제
		if(delFiles != null) {
			for(String file : delFiles) {
				dao.removeAttachByName(file, vo.getBno());
			}
		}
		
		//db에 새로 추가할 file 넣음
		for(String file : vo.getFiles()) {
			dao.addAttachAtMod(file, vo.getBno());
		}
		
		dao.update(vo); //tbl_board
	}

}//BoardServiceImpl
