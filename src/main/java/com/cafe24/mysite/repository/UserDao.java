package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.repository.vo.UserVo;
@Repository
public class UserDao {
	@Autowired
	private DataSource datasource;
	
	/*@Autowired
	private SqlSession sqlSession;
	*/
	public UserDao() {
		
		
		
		System.out.println("UserDao Constructor");
	}
	
	public boolean update(UserVo vo) {
		Boolean result = false;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		try {
		
				conn = datasource.getConnection();
				String sql="update user set name=?,email=?,password=? "
						+ "where no=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getEmail());
				pstmt.setString(3, vo.getPassword());
				pstmt.setLong(4, vo.getNo());
				int count = pstmt.executeUpdate();
				result = (count == 1);
				
		}catch (SQLException e) {
				// TODO Auto-generated catch block
			 System.out.println("error" + e);
			}
		finally {
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
		}return result;
	}
	
	public boolean insert(UserVo vo) {
		Boolean result = false;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		try {
				conn = datasource.getConnection();
				String sql="insert into user(name,email,password,gender,join_date) value(?,?,?,?,now())";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getEmail());
				pstmt.setString(3, vo.getPassword());
				pstmt.setString(4, vo.getGender());
				int count = pstmt.executeUpdate();
				result = (count == 1);
				
		}catch (SQLException e) {
				// TODO Auto-generated catch block
			 System.out.println("error" + e);
			}
		finally {
				try {
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
		}return result;
		
	}
	public UserVo get(Long no) throws UserDaoException{
	UserVo result =null;
		
		Connection conn = null; 
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			
				conn=datasource.getConnection();
				// sql문 실행
				String sql = "select name,email,password from user where no=?"; //sql 문장의 끝에  ; 을 뺀다
				
				
				//PreparedStatement 객체 생성 
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1,no );
				rs = pstmt.executeQuery();
				
			// 결과 가져오기
				if(rs.next()) {
					String name=rs.getString(1);
					String email = rs.getString(2);
					String password = rs.getString(3);
					
					result = new UserVo();
					result.setName(name);
					result.setEmail(email);
					result.setPassword(password);
					
					
					
				}
				
		}catch (SQLException e) {
				throw new UserDaoException();
			}
		finally {
				try {
					if(conn!=null)
						conn.close();
					if(rs!=null)
							rs.close();
					if(pstmt!=null)
							pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return result;
	}
	
	//Login시
	public UserVo get(String email,String password) throws UserDaoException{
		UserVo result =null;
		
		Connection conn = null; 
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			
				conn=datasource.getConnection();
				// sql문 실행
				String sql = "select no,name from user where email=? and password=?"; //sql 문장의 끝에  ; 을 뺀다
				
				
				//PreparedStatement 객체 생성 
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
				
			// 결과 가져오기
				if(rs.next()) {
					Long no=rs.getLong(1);
					String name = rs.getString(2);
					
					
					result = new UserVo();
					result.setNo(no);
					result.setName(name);
					
					
					
				}
				
		}catch (SQLException e) {
			throw new UserDaoException();
			}
		finally {
				try {
					if(conn!=null)
						conn.close();
					if(rs!=null)
							rs.close();
					if(pstmt!=null)
							pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return result;
	}

}
