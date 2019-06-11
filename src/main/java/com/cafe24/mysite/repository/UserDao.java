package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.repository.vo.UserVo;
@Repository
public class UserDao {
	/*@Autowired
	private DataSource datasource;*/
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserDao() {
		System.out.println("UserDao Constructor");
	}
	
	public int update(UserVo vo) {
		return sqlSession.update( "member.update", vo );
	}
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("member.insert", vo);
		System.out.println(vo);
		return 1 == count;
		
	}
	public UserVo get(Long no) throws UserDaoException{
		return sqlSession.selectOne("member.getByNo", no);
	}
	
	//Login시
	public UserVo get(String email,String password) throws UserDaoException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		UserVo userVo = sqlSession.selectOne("member.getByEmailAndPassword", map);
		
		return userVo;

}
	
	public UserVo get(String email) throws UserDaoException{
		UserVo userVo = sqlSession.selectOne("member.getByEmail", email);
		return userVo;
	}
}
