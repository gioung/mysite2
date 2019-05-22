package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.repository.vo.BoardVo;


@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public boolean writeContent(BoardVo vo) {
		return boardDao.insert(vo);
	}
	
	public List<BoardVo> getContentList() {
		return boardDao.getList();
	}

}
