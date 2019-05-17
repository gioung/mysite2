package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.repository.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private DataSource datasource;
	/*@Autowired
	private SqlSession sqlSession;*/

	public boolean insert(GuestbookVo vo) {
		Boolean result = false;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		try {
				conn = datasource.getConnection();
				String sql="insert into guestbook(name,password,contents,reg_date) value(?,?,?,now())";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getContents());
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
	
	public boolean delete(GuestbookVo vo) {
		Boolean result = false;
		Connection conn = null; 
		PreparedStatement pstmt = null;
		try {
				conn = datasource.getConnection();
				String sql="delete from guestbook where no=? and password=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, vo.getNo());
				pstmt.setString(2,vo.getPassword() );
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
	
	/*public List<GuestbookVo> getList(){
		List<GuestbookVo> result=sqlSession.selectList("guestbook.getList");
		return result;
	}*/
}
