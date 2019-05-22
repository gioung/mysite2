package com.cafe24.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.repository.vo.BoardVo;
import com.cafe24.mysite.repository.vo.GuestbookVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BoardVo vo) {
		return (1==sqlSession.insert("board.insert", vo));
	}
	
	public List<BoardVo> getList(){
		List<BoardVo> result = sqlSession.selectList( "board.getList" );
		return result;
	}
}
